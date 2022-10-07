package com.api.filemovermvc;

import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileMoveModel {
    private File sourceDirectory;
    private File[] sourceFileList;

    private HashMap<File, String[]> destinationMap = new HashMap<>();

    public ArrayList<File> openSource() {
        DirectoryChooser fileChooser = new DirectoryChooser();
        sourceDirectory = fileChooser.showDialog(null);
        return loadDirectory();
    }

    public ArrayList<File> loadDirectory() {
        ArrayList<File> returnedArray = new ArrayList<>();
        if(sourceDirectory != null) {
            sourceFileList = sourceDirectory.listFiles();
            if (sourceFileList != null) {
                for (File file : sourceFileList)
                    returnedArray.add(file.getAbsoluteFile());
            } else {
                System.out.println("Folder is empty");
            }
        } else {
            System.out.println("Source directory is null");
        }
        return returnedArray;
    }
}
