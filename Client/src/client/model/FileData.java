/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author heliof
 */
public class FileData {
    public static ObservableList proto;
    String location = "C:\\Users\\heliof\\Desktop\\bola\\pooII-cliente\\Client\\src\\client\\images";
    
   // List<String> list = new ArrayList<String>();
   // ObservableList<String> observableList = FXCollections.observableList(list);  
        
    
    public int countFiles(){
        int count = new File(location).list().length;
        return count;
    }
    
    public ObservableList<String> proto(){
        List<String> results = new ArrayList<String>();

        File[] files = new File(location).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        
        ObservableList<String> observableList = FXCollections.observableList(results);  
        return observableList;
    }
    
}
