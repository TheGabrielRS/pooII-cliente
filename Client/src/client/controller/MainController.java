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
import client.App;
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
//import client.model.FileData;
import javafx.scene.control.Button;
import client.model.FileWatcher;
import client.model.FileWatcher;
import client.model.Files;
import java.io.File;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

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
    private boolean reconnect;

     // Use Java Collections to create the List.
    List<String> list = new ArrayList<String>();
 
     // Now add observability by wrapping it with ObservableList.
    ObservableList<String> observableList = FXCollections.observableList(list);  
    
    private FileWatcher fileWatcher;
    private Files file;
    private Integer counting;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
//        populateIt();
//        fileList.setItems(observableList);
        this.file = new Files();
        this.serverSends.setText("0");
        this.serverFaults.setText("0");
        this.startCon("localhost", 3000); //Inicia a Thread de Conexão
    }
    
    @FXML
    public void onSend(){
        bottomHandler(true);
        this.objConexao.getMensagem().set(this.fileList.getItems().toString());
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
    public void onReconnect() {
        this.reconnect = true;
        objConexao.endSocket();
        this.startCon(objConexao.getComputerName(), 3000); //Reinicia a Thread com os valores passados pelo usuário
    }
    
    public void alertOnReconnect(boolean success){
        if(this.reconnect){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reconectando...");
        alert.setHeaderText(null);
        if(success)
            alert.setContentText("Conexão realizada com sucesso!");
        else
            alert.setContentText("Não foi possível reconectar!");
        alert.showAndWait();
        }
        this.reconnect = false;
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
    @FXML
    public void onIpDef() {
        TextInputDialog dialog = new TextInputDialog(objConexao.getComputerName());
        dialog.setTitle("Conexão");
        dialog.setHeaderText("Configuração do Conexão");
        dialog.setContentText("IP:");

        Optional<String> result = dialog.showAndWait();

        this.reconnect = true;
        objConexao.endSocket();
        result.ifPresent(computerName -> this.startCon(computerName, 3000));
        this.serverIP.setText(objConexao.getComputerName());
    }

    @FXML
    public void onFileDef() {
        try {
            Stage stage = App.getPrimaryStage();

            final DirectoryChooser directoryChooser = new DirectoryChooser();
            final File selectedDirectory;
            selectedDirectory = directoryChooser.showDialog(stage);
            if (selectedDirectory != null) {
                selectedDirectory.getAbsolutePath();
            }
            file.setPath(selectedDirectory.toString());
            this.populateIt();
            this.counting = file.getCountFiles();
            System.out.println(this.counting.toString());
            this.startFileWatcherInteger();
        } catch (Exception e) {
        }
    }
    
    /*
    * função de resposta do servidor
    * status, true para sucesso, false para falha
    */
    public void response(boolean status) {
        if (status) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText(null);
            alert.setContentText("Transação bem-sucedida!");
            serverCount(true);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha no envio!");
        alert.setContentText("Ooops, confira a conexão com o servidor e"
                    + " sua conexão com a internet.");
            serverCount(false);
            alert.showAndWait();
        }
    }
    
    public void populateIt() {
        this.fileList.setItems(this.file.getFilesNames());
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
        if(this.conexao != null && this.conexao.isAlive()){ //Caso haja uma thread de Conexão ela será interrompida
            this.conexao.interrupt();
        }
        
        //TODO review
        //serverIP.setText("hahah"); //Atualiza o label de acordo com o host indicado
        
        this.objConexao = new Conexao(host, port); //Instancia o objeto para que possa ser definido o listener responsável
        //Define listener para verificação de status da conexão Cliente/Servidor
        this.objConexao.getStatusConexao().addListener(new ChangeListener<Number>(){
            public void changed(final ObservableValue<? extends Number> observable,
          final Number oldValue, final Number newValue){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (newValue.intValue() == 1) {
                            serverStatus.setText("Conectado");
                            serverStatus.setTextFill(Paint.valueOf(Color.GREEN.toString()));
                            btnEnviar.setDisable(false);
                            alertOnReconnect(true);
                        }else{
                            serverStatus.setText("Desconectado");
                            serverStatus.setTextFill(Paint.valueOf(Color.RED.toString()));
                            btnEnviar.setDisable(true);
                            alertOnReconnect(false);
                        }
                    }
                });
            }
        });
        
        this.objConexao.getMensagem().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observable,
                    final String oldValue, final String newValue) {
                Platform.runLater(()->{response(objConexao.sendFile());});
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

        this.objConexao.getAcabouTransacao().addListener(new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> observable,
                    final Boolean oldValue, final Boolean newValue) {
                if (newValue) {
                    flagTempo = false; //Encerra o loop da thread de contagem de tempo
                    taskTempo.cancel(); //Encerra a task de contagem de tempo
                }
            }
        });
        
        this.conexao = new Thread(objConexao); //define a Thread de Conexão com os devidos parâmetros 
        this.conexao.setDaemon(true);
        this.conexao.start(); //Inicia a Thread de Conexão
        Platform.runLater(()->{
            serverIP.setText(this.objConexao.getComputerName());
            serverSends.setText("0");
            serverFaults.setText("0");
        });
    }

    public void serverCount(boolean response) {
        Integer s;
        Integer f;
        if (response) {
            s = 1 + (Integer.parseInt(serverSends.getText().toString()));
            serverSends.setText(s.toString());
        } else {
            f = 1 + (Integer.parseInt(serverFaults.getText().toString()));
            serverFaults.setText(f.toString());
        }
    }
    
    public void startFileWatcherInteger(){
        this.fileWatcher = new FileWatcher(this.file);
        this.fileWatcher.getNewCount().addListener(new ChangeListener<Number>(){
            public void changed(final ObservableValue<? extends Number> observable,
                    final Number oldValue, final Number newValue){
               
                int adds = (fileWatcher.getFile().getCountFiles()
                        - fileWatcher.getInitCount());
                int rmv = (fileWatcher.getInitCount() 
                        - fileWatcher.getFile().getCountFiles());
                
                if(adds < 0)
                    adds = 0;
                if(rmv < 0)
                    rmv = 0;              
                
                objConexao.getMensagem().set("fileWatcher:" +
                        adds
                        + ":"
                        + rmv);
                
                System.out.println("MSG:" 
                        + adds
                        + ":"
                        + rmv);
                
                System.out.println("deletou");
                Platform.runLater(() -> {
                    populateIt();
                });
            }
        });
        Thread t = new Thread(this.fileWatcher);
        t.setDaemon(true);
        t.start();
    }
}