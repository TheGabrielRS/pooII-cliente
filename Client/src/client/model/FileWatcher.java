/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author heliof
 */
public class FileWatcher implements Runnable {

    private Files file;
    private int oldCount;
    private BooleanProperty flag;

    public FileWatcher(Files objFile) {
        this.file = objFile;
        this.oldCount = this.file.getCountFiles();
        this.flag = new SimpleBooleanProperty(false);
    }

    public BooleanProperty getFlag() {
        return flag;
    }

    public Files getFile() {
        return file;
    }

    public int getInitCount() {
        return oldCount;
    }
    
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000);
                file.getFilesNames();
                int newCount = file.getCountFiles();
                if (newCount != oldCount) {
                    this.flag.set(true);
                    this.oldCount = newCount;
                    this.flag.set(false);
                }
            } catch (Exception e) {
            }
        }
    }

}
