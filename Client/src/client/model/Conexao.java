<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author gabrielsa
 */
public class Conexao implements Runnable{
    
    private String computerName;
    private int serverPort;
    private Socket con;
    private IntegerProperty statusConexao;
    private StringProperty mensagem;
    private FloatProperty tempoInicial;
    private BooleanProperty acabouTransacao;

    public Conexao(String computerName, int serverPort) {
        this.computerName = computerName;
        this.serverPort = serverPort;
        this.statusConexao = new SimpleIntegerProperty(-1);
        this.mensagem = new SimpleStringProperty("");
        this.tempoInicial = new SimpleFloatProperty(0);
        this.acabouTransacao = new SimpleBooleanProperty(false);
    }
    
    public void run(){
        try{
            this.con = new Socket(computerName,serverPort);
            System.out.println("Conexão estabelecida - "+ Thread.currentThread().getId());
            this.statusConexao.setValue(1);    
        }catch(UnknownHostException e){
            System.out.println("Host inválido");
            this.statusConexao.setValue(0);
            Thread.currentThread().interrupt();
        }catch(IOException e){
            System.out.println("Conexão não estabelecida/encerrada");
            this.statusConexao.setValue(0);
            Thread.currentThread().interrupt();
        }
        while(this.statusConexao.get() == 1){
            try{
                this.con.getInputStream().read();
            }catch(UnknownHostException e){
                System.out.println("Host inválido");
                this.statusConexao.setValue(0);
                Thread.currentThread().interrupt();
            }catch(IOException e){
                System.out.println("Conexão não estabelecida/encerrada");
                this.statusConexao.setValue(0);
                Thread.currentThread().interrupt();
            }
        }
        if(Thread.currentThread().isInterrupted())
            System.out.println("parou tudo");
    }
    
    public IntegerProperty getStatusConexao() {
        return statusConexao;
    }

    public StringProperty getMensagem() {
        return mensagem;
    }

    public FloatProperty getTempoInicial() {
        return tempoInicial;
    }

    public BooleanProperty getAcabouTransacao() {
        return acabouTransacao;
    }
    
    public boolean sendFile(){
        try{
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            this.tempoInicial.set(System.currentTimeMillis());
            out.write(mensagem.get());
            out.newLine();
            out.flush();
            this.acabouTransacao.set(true);
            this.acabouTransacao.set(false);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public void endSocket(){
        try{
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            out.write("c53255317bb11707d0f614696b3ce6f221d0e2f2");
            out.newLine();
            out.flush();
            con.close();
            System.out.println("matou");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of a49ad45... enviando lista inteira
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author gabrielsa
 */
public class Conexao implements Runnable{
    
    private String computerName;
    private int serverPort;
    private Socket con;
    private IntegerProperty statusConexao;
    private StringProperty mensagem;
    private FloatProperty tempoInicial;
    private BooleanProperty acabouTransacao;

    public Conexao(String computerName, int serverPort) {
        this.computerName = computerName;
        this.serverPort = serverPort;
        this.statusConexao = new SimpleIntegerProperty(-1);
        this.mensagem = new SimpleStringProperty("");
        this.tempoInicial = new SimpleFloatProperty(0);
        this.acabouTransacao = new SimpleBooleanProperty(false);
    }
    
    public void run(){
        try{
            this.con = new Socket(computerName,serverPort);
            System.out.println("Conexão estabelecida - "+ Thread.currentThread().getId());
            this.statusConexao.setValue(1);    
        }catch(UnknownHostException e){
            System.out.println("Host inválido");
            this.statusConexao.setValue(0);
            Thread.currentThread().interrupt();
        }catch(IOException e){
            System.out.println("Conexão não estabelecida/encerrada");
            this.statusConexao.setValue(0);
            Thread.currentThread().interrupt();
        }
        while(this.statusConexao.get() == 1){
            try{
                this.con.getInputStream().read();
            }catch(UnknownHostException e){
                System.out.println("Host inválido");
                this.statusConexao.setValue(0);
                Thread.currentThread().interrupt();
            }catch(IOException e){
                System.out.println("Conexão não estabelecida/encerrada");
                this.statusConexao.setValue(0);
                Thread.currentThread().interrupt();
            }
        }
        if(Thread.currentThread().isInterrupted())
            System.out.println("parou tudo");
    }
    
    public IntegerProperty getStatusConexao() {
        return statusConexao;
    }

    public StringProperty getMensagem() {
        return mensagem;
    }

    public FloatProperty getTempoInicial() {
        return tempoInicial;
    }

    public BooleanProperty getAcabouTransacao() {
        return acabouTransacao;
    }
    
    public String getComputerName() {
        return computerName;
    }
    
    public boolean sendFile(){
        try{
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            this.tempoInicial.set(System.currentTimeMillis());
            out.write(mensagem.get());
            out.newLine();
            out.flush();
            this.acabouTransacao.set(true);
            this.acabouTransacao.set(false);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public void endSocket(){
        try{
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
            out.write("c53255317bb11707d0f614696b3ce6f221d0e2f2");
            out.newLine();
            out.flush();
            con.close();
            System.out.println("matou");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
<<<<<<< HEAD
}
>>>>>>> 6015c61... contador e arquivos
=======
>>>>>>> a49ad45... enviando lista inteira
=======
>>>>>>> a49ad45... enviando lista inteira
=======
<<<<<<< HEAD
}
>>>>>>> 6015c61... contador e arquivos
=======
}
>>>>>>> helio
>>>>>>> parent of a49ad45... enviando lista inteira
