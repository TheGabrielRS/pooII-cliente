/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;

import javafx.application.Platform;
import javafx.concurrent.Task;
import client.model.Conexao;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Paint;
import client.model.FileData;
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author heliof
 */
public class MainController {
    @FXML Label serverIP;
    @FXML Label serverStatus;
    @FXML Label serverSends;
    @FXML Label serverFaults;
    @FXML ListView fileList;
    @FXML Label tempoDecorrido;
    @FXML Label tempoDecorridoPreT;
    @FXML Label tempoDecorridoPosT;
    @FXML ProgressBar progressBar;
    @FXML Button btnEnviar;
    
    //Flag para o temporizador
    private boolean flagTempo;
    //Task para o temporizador
    Task taskTempo;
    private Thread conexao;
    private Conexao objConexao;
    
     // Use Java Collections to create the List.
    List<String> list = new ArrayList<String>();
 
     // Now add observability by wrapping it with ObservableList.
    ObservableList<String> observableList = FXCollections.observableList(list);  
    
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
        populate();
        fileList.setItems(observableList);
        this.startCon("localhost",3000); //Inicia a Thread de Gerenciamento com os valores padrões
        
    }
    
    @FXML
    public void onSend(){
        bottomHandler(true);
                
        this.objConexao.getMensagem().set((String)fileList.getSelectionModel().getSelectedItem());

        bottomHandler(false);
    }
    
    @FXML
    public void onInfo(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("UFCSPA - Programação Orientada a Objetos II");
        alert.setHeaderText("Aplicação cliente");
        alert.setContentText("Autores:" + 
                "\n" + "Gabriel Ramos dos Santos" +
                "\n" + "Hélio Francisco das Neves Silveira Jr.");

        alert.showAndWait();    
    }
    
    @FXML
    public void onReconnect(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reconectando...");
        this.startCon("localhost", 3000); //Reinicia a Thread com os valores passados pelo usuário
        alert.setHeaderText(null);
        alert.setContentText("Pronto!");
        
        alert.showAndWait();
    }
    
    public void bottomHandler(boolean key){
        if(key){
            tempoDecorrido.setTextFill(Color.BLACK);
            tempoDecorridoPreT.setTextFill(Color.BLACK);
            tempoDecorridoPosT.setTextFill(Color.BLACK);
            progressBar.setDisable(!key);
            progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);;
        } else {
            progressBar.setDisable(key);
            progressBar.setProgress(0);
        }
    }
    
    /*
    * função de resposta do servidor
    * status, true para sucesso, false para falha
    */
    public void response(boolean status){
        if(status){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText(null);
            alert.setContentText("Transação bem-sucedida!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha no envio!");
            alert.setContentText("Ooops, confira a conexão com o servidor e" 
                    + " sua conexão com a internet.");

            alert.showAndWait();
        }
    }
    
    //Função genérica para popular a lista
    public void populate(){
        list.add("nudes.jpg");
        list.add("bolo+de+leite+18.rar");
        list.add("receita_dieta.zip");
        list.add("receita_dieta2.zip");
        list.add("receita_dieta3.zip");
        list.add("receita_dieta4.zip");
        list.add("receita_dieta5.zip");
        list.add("receita_dieta6.zip");
        list.add("organela.jpeg");
        list.add("SsdasDIAJNONSDssd.bat");
    }
    
    public void startCon(String host, int port){
        if(this.conexao != null){ //Caso haja uma thread de Conexão ela será interrompida
            this.conexao.interrupt();
        }
        
        this.serverIP.setText(host); //Atualiza o label de acordo com o host indicado
        
        this.objConexao = new Conexao(host, port); //Instancia o objeto para que possa ser definido o listener responsável
        //Define listener para verificação de status da conexão Cliente/Servidor
        this.objConexao.getStatusConexao().addListener(new ChangeListener<Number>(){
            public void changed(final ObservableValue<? extends Number> observable,
          final Number oldValue, final Number newValue){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(newValue.intValue() == 1){
                            serverStatus.setText("Conectado");
                            serverStatus.setTextFill(Paint.valueOf(Color.GREEN.toString()));
                            btnEnviar.setDisable(false);
                        }else{
                            serverStatus.setText("Desconectado");
                            serverStatus.setTextFill(Paint.valueOf(Color.RED.toString()));
                            btnEnviar.setDisable(true);
                        }
                        
                    }
                });
            }
        });
        
        this.objConexao.getMensagem().addListener(new ChangeListener<String>(){
            public void changed(final ObservableValue<? extends String> observable,
          final String oldValue, final String newValue){
                response(objConexao.sendFile());
            }
        });
        
        this.objConexao.getTempoInicial().addListener(new ChangeListener<Number>(){
            public void changed(final ObservableValue<? extends Number> observable,
          final Number oldValue, final Number newValue){
                /*
                Define a Task de contagem de tempo e atualização na tela
                */
                flagTempo = true;
                taskTempo = new Task<Void>(){
                    public Void call(){
                        while(flagTempo){
                            try{
                                Thread.sleep(500);
                            }catch(Exception e){
                            }
                            Platform.runLater(new Runnable(){
                                float tempoMillis;
                                float tempoSec;
                                public void run(){
                                    tempoMillis = System.currentTimeMillis() - newValue.floatValue();
                                    tempoSec = tempoMillis / 1000F;
                                    tempoDecorrido.setText(Float.toString(tempoSec));
                                }
                            });
                        }
                        return null;
                    }
                };
                new Thread(taskTempo).start(); //Inicia a thread com o contador de tempo
                
            }
        });
        
        this.objConexao.getAcabouTransacao().addListener(new ChangeListener<Boolean>(){
            public void changed(final ObservableValue<? extends Boolean> observable,
          final Boolean oldValue, final Boolean newValue){
                if(newValue){
                    flagTempo = false; //Encerra o loop da thread de contagem de tempo
                    taskTempo.cancel(); //Encerra a task de contagem de tempo
                }
            }
        });
        
        this.conexao = new Thread(objConexao); //define a Thread de Conexão com os devidos parâmetros 
        this.conexao.setDaemon(true);
        this.conexao.start(); //Inicia a Thread de Conexão
        
        
    }
    
}
