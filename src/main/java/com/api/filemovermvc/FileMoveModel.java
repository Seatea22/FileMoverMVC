package com.api.filemovermvc;

import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FileMoveModel {
    private File sourceDirectory;
    private File[] sourceFileList;
    private HashMap<File, ArrayList<String>> destinationMap = new HashMap<>();

    public HashMap<File, ArrayList<String>> getDestinationMap() {
        return destinationMap;
    }
    public File[] getSourceFileList() {
        return sourceFileList;
    }
    public ArrayList<File> openSource() {
        DirectoryChooser fileChooser = new DirectoryChooser();
        sourceDirectory = fileChooser.showDialog(null);
        return loadSourceDirectory();
    }

    public ArrayList<File> loadSourceDirectory() {
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

    public HashMap<File, ArrayList<String>> loadDestination(String configFileName) {
        DirectoryChooser fileChooser = new DirectoryChooser();
        File destDir = fileChooser.showDialog(null);
        File[] files = destDir.listFiles();
        if (files != null) {
            boolean configFound = false;
            for(File file: files) {
                if (file.getName().equals(configFileName)) {
                    ArrayList<String> args = checkConfig(file);
                    destinationMap.put(destDir, args);
                    configFound = true;
                }
            }
            if (!configFound) destinationMap.put(destDir, null);
        }
        return destinationMap;
    }

    public ArrayList<String> checkConfig(File file) {
        ArrayList<String> args = new ArrayList<>();
        try {
            Scanner reader = new Scanner(file);
            reader.useDelimiter(",");
            while (reader.hasNext()) {
                args.add(reader.next());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return args;
    }

    public void updateDirectoryArgs(File current, ArrayList<String> args) {
        destinationMap.put(current, args);
    }
}

