/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author gabrielsa
 */
public class Conexao implements Runnable{
    
    private String computerName;
    private int serverPort;
    private Socket con;
    private IntegerProperty statusConexao;
    

    public Conexao(String computerName, int serverPort) {
        this.computerName = computerName;
        this.serverPort = serverPort;
        this.statusConexao = new SimpleIntegerProperty(-1);
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
}
