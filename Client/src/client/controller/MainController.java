/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> parent of a49ad45... enviando lista inteira
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of a49ad45... enviando lista inteira
=======

import client.App;
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
<<<<<<< HEAD
import client.App;
>>>>>>> a49ad45... enviando lista inteira
=======

import client.App;
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> parent of a49ad45... enviando lista inteira
import java.util.concurrent.atomic.AtomicInteger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Paint;
//import client.model.FileData;
import javafx.scene.control.Button;
=======
=======
import client.model.FileWatcher;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> a64c381... thread de att dos arquivos
=======
import client.model.FileWatcher;
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> a64c381... thread de att dos arquivos
=======
import client.model.FileWatcher;
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
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

>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
/**
 * FXML Controller class
 *
 * @author heliof
 */
public class MainController {
<<<<<<< HEAD
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of a49ad45... enviando lista inteira
    
=======

    @FXML
    Label serverIp;
    @FXML
    Label serverStatus;
    @FXML
    Label serverSends;
    @FXML
    Label serverFaults;
    @FXML
    ListView fileList;
    @FXML
    Label tempoDecorrido;
    @FXML
    Label tempoDecorridoPreT;
    @FXML
    Label tempoDecorridoPosT;
    @FXML
    ProgressBar progressBar;
    @FXML
    Button btnEnviar;

<<<<<<< HEAD
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
>>>>>>> a49ad45... enviando lista inteira
=======
<<<<<<< HEAD
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
    //Flag para o temporizador
    private boolean flagTempo;
    //Task para o temporizador
    Task taskTempo;
    private Thread conexao;
    private Conexao objConexao;
    private boolean reconnect;
<<<<<<< HEAD
<<<<<<< HEAD
    
<<<<<<< HEAD
=======
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> parent of a49ad45... enviando lista inteira
     // Use Java Collections to create the List.
    List<String> list = new ArrayList<String>();
 
     // Now add observability by wrapping it with ObservableList.
    ObservableList<String> observableList = FXCollections.observableList(list);  
    
<<<<<<< HEAD
<<<<<<< HEAD
=======

=======
>>>>>>> a49ad45... enviando lista inteira
=======
=======
=======
>>>>>>> helio

>>>>>>> parent of a49ad45... enviando lista inteira
    private FileWatcher fileWatcher;
    private Files file;
    private Integer counting;

<<<<<<< HEAD
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
<<<<<<< HEAD
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        // TODO
<<<<<<< HEAD
<<<<<<< HEAD
        populate();
        fileList.setItems(observableList);
<<<<<<< HEAD
        this.startCon("localhost",3000); //Inicia a Thread de Gerenciamento com os valores padrões
        
=======
        file = new Files();
=======
        this.startCon("localhost",3000); //Inicia a Thread de Gerenciamento com os valores padrões
//        populateIt();
//        fileList.setItems(observableList);
        this.file = new Files();
>>>>>>> a49ad45... enviando lista inteira
=======
        this.startCon("localhost",3000); //Inicia a Thread de Gerenciamento com os valores padrões
        
=======
=======
>>>>>>> helio
        file = new Files();
>>>>>>> parent of a49ad45... enviando lista inteira
        serverSends.setText("0");
        serverFaults.setText("0");

        this.startCon("localhost", 3000); //Inicia a Thread de Gerenciamento com os valores padrões
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
=======

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of a49ad45... enviando lista inteira
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
        serverIp.setText(objConexao.getComputerName());

>>>>>>> 6015c61... contador e arquivos
=======
<<<<<<< HEAD
>>>>>>> a49ad45... enviando lista inteira
=======
        serverIp.setText(objConexao.getComputerName());

>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
    }
    
    @FXML
<<<<<<< HEAD
    public void onSend(){
        bottomHandler(true);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of a49ad45... enviando lista inteira
                
        this.objConexao.getMensagem().set((String)fileList.getSelectionModel().getSelectedItem());
=======

        this.objConexao.getMensagem().set((String) fileList.getSelectionModel().getSelectedItem());
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
    public void onSend() {
<<<<<<< HEAD
        bottomHandler(true);   
        
        this.objConexao.getMensagem().set(file.getFilesNames().toString());
>>>>>>> 6015c61... contador e arquivos
=======
        bottomHandler(true);

        //this.objConexao.getMensagem().set(file.getFilesNames().toString());
        this.objConexao.getMensagem().set((String) fileList.getSelectionModel().getSelectedItem());
<<<<<<< HEAD
>>>>>>> a64c381... thread de att dos arquivos

=======
        this.objConexao.getMensagem().set(this.fileList.getItems().toString());
>>>>>>> a49ad45... enviando lista inteira
=======
<<<<<<< HEAD
>>>>>>> a64c381... thread de att dos arquivos
=======
>>>>>>> helio

>>>>>>> parent of a49ad45... enviando lista inteira
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
<<<<<<< HEAD
    public void onReconnect(){
=======
    public void onReconnect() {
<<<<<<< HEAD
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
<<<<<<< HEAD
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
        this.reconnect = true;
        objConexao.endSocket();
        this.startCon(objConexao.getComputerName(), 3000); //Reinicia a Thread com os valores passados pelo usuário
    }
<<<<<<< HEAD
<<<<<<< HEAD
    
<<<<<<< HEAD
=======
   
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> parent of a49ad45... enviando lista inteira
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
=======
=======
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira

    public void alertOnReconnect(boolean success) {
        if (this.reconnect) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Reconectando...");
            alert.setHeaderText(null);
            if (success) {
                alert.setContentText("Conexão realizada com sucesso!");
            } else {
                alert.setContentText("Não foi possível reconectar!");
            }
            alert.showAndWait();
        }
        this.reconnect = false;
    }

    public void bottomHandler(boolean key) {
        if (key) {
            tempoDecorrido.setTextFill(Color.BLACK);
            tempoDecorridoPreT.setTextFill(Color.BLACK);
            tempoDecorridoPosT.setTextFill(Color.BLACK);
            progressBar.setDisable(!key);
            progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);;
        } else {
            progressBar.setDisable(key);
            progressBar.setProgress(0);
<<<<<<< HEAD
=======
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> parent of a49ad45... enviando lista inteira
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
        serverIp.setText(objConexao.getComputerName());
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
            startFileWatcherInteger();
        } catch (Exception e) {
<<<<<<< HEAD
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
<<<<<<< HEAD
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
        }
    }
    
    @FXML
    public void onIpDef(){
        
    }
    @FXML
    public void onFileDef(){
        
    }
    
    /*
    * função de resposta do servidor
    * status, true para sucesso, false para falha
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    */
    public void response(boolean status){
        if(status){
=======
     */
=======
    */
>>>>>>> a49ad45... enviando lista inteira
=======
    */
    public void response(boolean status){
        if(status){
=======
=======
>>>>>>> helio
     */
>>>>>>> parent of a49ad45... enviando lista inteira
    public void response(boolean status) {
        if (status) {
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
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
<<<<<<< HEAD
<<<<<<< HEAD
            alert.setContentText("Ooops, confira a conexão com o servidor e" 
=======
            alert.setContentText("Ooops, confira a conexão com o servidor e"
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
            alert.setContentText("Ooops, confira a conexão com o servidor e"
<<<<<<< HEAD
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
                    + " sua conexão com a internet.");
            serverCount(false);
            alert.showAndWait();
        }
    }
<<<<<<< HEAD
    
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
                        if(newValue.intValue() == 1){
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of a49ad45... enviando lista inteira
=======

    //Função para popular a lista
    public void populateIt() {
        fileList.setItems(file.getFilesNames());
    }

    public void startCon(String host, int port) {
        if (this.conexao != null) {
            //Caso haja uma thread de Conexão ela será interrompida
            this.conexao.interrupt();
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> parent of a49ad45... enviando lista inteira
        }

        //Instancia o objeto para que possa ser definido o listener responsável
        this.objConexao = new Conexao(host, port);

        //Define listener para verificação de status da conexão Cliente/Servidor
        this.objConexao.getStatusConexao().addListener(new ChangeListener<Number>() {
            public void changed(final ObservableValue<? extends Number> observable,
                    final Number oldValue, final Number newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (newValue.intValue() == 1) {
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
<<<<<<< HEAD
=======
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> parent of a49ad45... enviando lista inteira
                            serverStatus.setText("Conectado");
                            serverStatus.setTextFill(Paint.valueOf(Color.GREEN.toString()));
                            btnEnviar.setDisable(false);
                            alertOnReconnect(true);
<<<<<<< HEAD
                        }else{
=======
                        } else {
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
                            serverStatus.setText("Desconectado");
                            serverStatus.setTextFill(Paint.valueOf(Color.RED.toString()));
                            btnEnviar.setDisable(true);
                            alertOnReconnect(false);
                        }
<<<<<<< HEAD
<<<<<<< HEAD
                        
=======
                        serverIp.setText(objConexao.getComputerName());
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
=======
>>>>>>> 6015c61... contador e arquivos
                    }
                });
            }
        });
<<<<<<< HEAD
        
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
=======

        this.objConexao.getMensagem().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observable,
                    final String oldValue, final String newValue) {
                Platform.runLater(()->{response(objConexao.sendFile());});
            }
        });

        this.objConexao.getTempoInicial().addListener(new ChangeListener<Number>() {
            public void changed(final ObservableValue<? extends Number> observable,
                    final Number oldValue, final Number newValue) {
                /*
                Define a Task de contagem de tempo e atualização na tela
                 */
                flagTempo = true;
                taskTempo = new Task<Void>() {
                    public Void call() {
                        while (flagTempo) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            Platform.runLater(new Runnable() {
                                float tempoMillis;
                                float tempoSec;

                                public void run() {
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
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
<<<<<<< HEAD
                
            }
        });
        
        this.objConexao.getAcabouTransacao().addListener(new ChangeListener<Boolean>(){
            public void changed(final ObservableValue<? extends Boolean> observable,
          final Boolean oldValue, final Boolean newValue){
                if(newValue){
=======

            }
        });

        this.objConexao.getAcabouTransacao().addListener(new ChangeListener<Boolean>() {
            public void changed(final ObservableValue<? extends Boolean> observable,
                    final Boolean oldValue, final Boolean newValue) {
                if (newValue) {
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
                    flagTempo = false; //Encerra o loop da thread de contagem de tempo
                    taskTempo.cancel(); //Encerra a task de contagem de tempo
                }
            }
        });
<<<<<<< HEAD
        
        this.conexao = new Thread(objConexao); //define a Thread de Conexão com os devidos parâmetros 
        this.conexao.setDaemon(true);
        this.conexao.start(); //Inicia a Thread de Conexão
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of a49ad45... enviando lista inteira
        
        
    }
    
}
=======

        this.conexao = new Thread(objConexao); //define a Thread de Conexão com os devidos parâmetros 
        this.conexao.setDaemon(true);
        this.conexao.start(); //Inicia a Thread de Conexão
<<<<<<< HEAD
=======
        Platform.runLater(()->{
            serverIP.setText(this.objConexao.getComputerName());
        });
>>>>>>> a49ad45... enviando lista inteira
=======
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
=======
>>>>>>> helio
        }

        //Instancia o objeto para que possa ser definido o listener responsável
        this.objConexao = new Conexao(host, port);

        //Define listener para verificação de status da conexão Cliente/Servidor
        this.objConexao.getStatusConexao().addListener(new ChangeListener<Number>() {
            public void changed(final ObservableValue<? extends Number> observable,
                    final Number oldValue, final Number newValue) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (newValue.intValue() == 1) {
                            serverStatus.setText("Conectado");
                            serverStatus.setTextFill(Paint.valueOf(Color.GREEN.toString()));
                            btnEnviar.setDisable(false);
                            alertOnReconnect(true);
                        } else {
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

        this.objConexao.getTempoInicial().addListener(new ChangeListener<Number>() {
            public void changed(final ObservableValue<? extends Number> observable,
                    final Number oldValue, final Number newValue) {
                /*
                Define a Task de contagem de tempo e atualização na tela
                 */
                flagTempo = true;
                taskTempo = new Task<Void>() {
                    public Void call() {
                        while (flagTempo) {
                            try {
                                Thread.sleep(500);
                            } catch (Exception e) {
                            }
                            Platform.runLater(new Runnable() {
                                float tempoMillis;
                                float tempoSec;

                                public void run() {
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
>>>>>>> parent of a49ad45... enviando lista inteira
    }

<<<<<<< HEAD
=======
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
<<<<<<< HEAD
<<<<<<< HEAD

=======

>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
//    public void startFileWatcher() {
//        this.fileWatcher = new FileWatcher(this.file);
//        this.fileWatcher.getFlag().addListener(new ChangeListener<Boolean>() {
//            public void changed(final ObservableValue<? extends Boolean> observable,
//                    final Boolean oldValue, final Boolean newValue) {
//                if (newValue) {
//                    objConexao.getMensagem().set("fileWatcher");
//                    objConexao.getMensagem().set(fileWatcher.getFile().getCountFiles() 
//                            + ":" 
//                            + fileWatcher.getInitCount());
//                    
//                    System.out.println("deletou");
//                    Platform.runLater(() -> {
//                        populateIt();
//                    });
//                }
//            }
//        });
//        Thread t = new Thread(this.fileWatcher);
//        t.setDaemon(true);
//        t.start();
//    }
<<<<<<< HEAD
=======
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> parent of a49ad45... enviando lista inteira
    
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
>>>>>>> 8bbb963... novo seletor de arquivos, realizando conexão
