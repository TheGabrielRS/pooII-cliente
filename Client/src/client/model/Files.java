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
 * @author helio
 */
public class Files {
    private int count;
    private String path = "C:\\Users\\helio\\Documents\\GitHub\\pooII-cliente\\Client\\src\\client\\images";

    public String getPath() {
        return this.path;
    }

    public void setPath(String p) {
        this.path = p;
    }

    public int getCountFiles() {
        return this.count;
    }
        
    void setCountFiles(int count) {
        this.count = count;
    } 

    public ObservableList<String> getFilesNames() {
        List<String> results = new ArrayList<>();

        File[] files = new File(path).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().endsWith(".bmp") || file.getName().endsWith(".jpeg")) {
                    results.add(file.getName());
                }
            }
        }

        ObservableList<String> observableList = FXCollections.observableList(results);
        
        this.setCountFiles(observableList.size());
        
        return observableList;
    }

}
