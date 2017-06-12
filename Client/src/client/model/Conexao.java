/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.net.Socket;

/**
 *
 * @author gabrielsa
 */
public class Conexao {
    
    private String computerName;
    private int serverPort;
    private Socket con;
    

    public Conexao(String computerName, int serverPort) {
        this.computerName = computerName;
        this.serverPort = serverPort;
    }
    
    public boolean conectar(){
        try{
            this.con = new Socket(computerName,serverPort);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean statusDaConexao(){
        return this.con.isConnected();
    }    
}
