/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

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
import client.model.Files;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author heliof
 */
public class MainController {

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

    Files file;
    String fileName;

    //Flag para testes sem o servidor
    Boolean flagStandalone = false;

    //Flag para o temporizador
    private boolean flagTempo;
    private Conexao con;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {

        file = new Files();

        if (flagStandalone) {
            con = new Conexao("localhost", 3000);

            con.conectar();

            if (con.statusDaConexao()) {
                System.out.println("deu bom");
            } else {
                System.out.println("deu ruim");
            }
        }

        do {
            onFileDef();
        } while (!populateIt());
    }

    @FXML
    public void onSend() {
        bottomHandler(true);

        /*
        *   Pego o item selecionado da lista e coloco
        *   no texto de qtd. de erros
         */
        serverFaults.setText(fileList.getSelectionModel().selectedItemProperty().getValue().toString());

        /*
        *Define a Task de contagem de tempo e atualização na tela
         */
        flagTempo = true;
        Task taskTempo = new Task<Void>() {
            public Void call() {
                final long tempoInicial = System.currentTimeMillis();
                while (flagTempo) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(new Runnable() {
                        long tempoMillis;
                        float tempoSec;

                        public void run() {
                            tempoMillis = System.currentTimeMillis() - tempoInicial;
                            tempoSec = tempoMillis / 1000F;
                            tempoDecorrido.setText(Float.toString(tempoSec));
                        }
                    });

                }
                return null;
            }
        };

        new Thread(taskTempo).start(); //Inicia a thread com o contador de tempo

        this.response(true);
        this.response(false);

        flagTempo = false; //Encerra o loop da thread de contagem de tempo
        taskTempo.cancel(); //Encerra a task de contagem de tempo

        bottomHandler(false);
    }

    @FXML
    public void onInfo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("UFCSPA - Programação Orientada a Objetos II");
        alert.setHeaderText("Aplicação cliente");
        alert.setContentText("Autores:"
                + "\n" + "Gabriel Ramos dos Santos"
                + "\n" + "Hélio Francisco das Neves Silveira Jr.");

        alert.showAndWait();
    }

    @FXML
    public void onReconnect() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reconectando...");
        alert.setHeaderText(null);

        //TODO
        /*
        * Implementar a reconexão aqui
        * !
        * !
         */
        //Caso sem conexão com o servidor, não checar status
        if (flagStandalone) {
            if (con.statusDaConexao()) {
                alert.setContentText("Conectado!");
            } else {
                alert.setContentText("Ooops, verifique a conexão com o servidor e"
                        + " sua conexão com a internet.");
            }
        } else {
            alert.setContentText("Ooops, verifique a conexão com o servidor e"
                    + " sua conexão com a internet.");
        }

        alert.showAndWait();
    }

    @FXML
    public void onIpDef() {
        TextInputDialog dialog = new TextInputDialog("192.168.0.1");
        dialog.setTitle("Conexão");
        dialog.setHeaderText("Configuração do Conexão");
        dialog.setContentText("IP:");

        Optional<String> result = dialog.showAndWait();

        //TODO
        /*
        *   Implementar o getter e setter para o Ip
        *   !
         */
        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    @FXML
    public void onFileDef() {
        TextInputDialog dialog = new TextInputDialog(file.getPath());
        dialog.setTitle("Arquivos");
        dialog.setHeaderText("Configuração do Caminho");
        dialog.setContentText("Caminho:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(path -> file.setPath(path.replace('/', '\\')));
    }

    //Função para alternar o inferior da GUI
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
        }
    }

    /*
    * função de resposta do servidor
    * status: true para sucesso, false para falha
     */
    public void response(Boolean status) {
        if (status) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucesso!");
            alert.setHeaderText(null);
            alert.setContentText("Transação bem-sucedida!");

            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha no envio!");
            alert.setContentText("Ooops, verifique a conexão com o servidor e"
                    + " sua conexão com a internet.");

            alert.showAndWait();
        }
    }

    //Função para popular a lista
    public Boolean populateIt() {
        try {
            fileList.setItems(file.getFilesNames());
            return true;
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Cuidado!");
            alert.setHeaderText("Arquivo Inválido");
            alert.setContentText("¹Verifique o caminho de arquivo inserido"
                    + " e tente novamente."
                    + "\n" + "²Verifique se a pasta images existe.");

            alert.showAndWait();
            return false;
        }
    }

    //Função para adquirir o tempo decorrido
    public Label getTempoDecorrido() {
        return tempoDecorrido;
    }
}
