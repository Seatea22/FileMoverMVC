package com.api.filemovermvc;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileMoveController {
    public TextField extensionField;
    public Button extensionButton;
    private FileMoveModel model = new FileMoveModel();
    public MenuItem openSourceButton;
    public MenuItem openDestButton;
    public Button submitButton;
    public ListView<File> sourceList;
    public ListView<File> destinationList;
    public Label sourceName;
    public Label sourceLastMod;
    public Label sourceFileExt;
    public Label destName;
    public Label destLastMod;
    public Label destExtensions;

    public boolean listSelected(String listView) {
        // listView is source or dest
        if(listView.toLowerCase().equals("source"))
            return (!sourceList.getItems().isEmpty() && sourceList.getSelectionModel().getSelectedIndex() != -1);
        else if (listView.toLowerCase().equals("dest"))
            return (!destinationList.getItems().isEmpty() && destinationList.getSelectionModel().getSelectedIndex() != -1);
        else {
            System.out.println("input error");
            return false;
        }

    }

    public void submitPressed(ActionEvent actionEvent) {
    }

    public void extensionSubmit(ActionEvent actionEvent) {
        model.updateDirectoryArgs(getSelectedFile("dest"), getArgsFromField());
    }

    public ArrayList<String> getArgsFromField() {
        return new ArrayList<>(Arrays.asList(extensionField.getText().split(",")));
    }

    public void openSourcePressed(ActionEvent actionEvent) {
        ArrayList<File> files = model.openSource();
        updateSourceView(files);
    }

    public void openDestPressed(ActionEvent actionEvent) {
        HashMap<File, ArrayList<String>> destination = model.loadDestination("config.csv");
        updateDestinationView(destination);
    }

    public File getSelectedFile(String listView) {
        if (listView.toLowerCase().equals("source"))
            return sourceList.getSelectionModel().getSelectedItem();
        else if (listView.toLowerCase().equals("dest"))
            return destinationList.getSelectionModel().getSelectedItem();
        else
            return null;
    }

    public void sourceViewClicked(MouseEvent mouseEvent) {
        if (listSelected("source")){
            int index = sourceList.getSelectionModel().getSelectedIndex();
            File[] fileList = model.getSourceFileList();
            sourceName.setText(fileList[index].getName());
            sourceLastMod.setText(String.valueOf(fileList[index].lastModified()));
            String ext = getExtension(fileList[index].getName());

            if (!ext.isEmpty())
                sourceFileExt.setText(getExtension(fileList[index].getName()));
            else sourceFileExt.setText("directory");
        }
    }

    public void destListClicked(MouseEvent mouseEvent) {
        if (listSelected("dest")){
            File selectedItem = destinationList.getSelectionModel().getSelectedItem();
            destName.setText(selectedItem.getName());
            destLastMod.setText(String.valueOf(selectedItem.lastModified()));
            destExtensions.setText(Arrays.toString(new ArrayList[]{model.getDestinationMap().get(selectedItem)}));
        }
    }

    public void updateSourceView(ArrayList<File> files) {
        if(!sourceList.getItems().isEmpty())
            sourceList.getItems().clear();
        if (files != null)
            sourceList.getItems().addAll(files);
        else {
            model.loadSourceDirectory();
            sourceList.getItems().addAll(model.getSourceFileList());
        }
    }

    public void updateDestinationView(HashMap<File, ArrayList<String>> files) {
        if(!destinationList.getItems().isEmpty())
            destinationList.getItems().clear();
        if (files != null) {
            files.forEach((k, v) -> destinationList.getItems().add(k));
            System.out.println("hi");
            System.out.println(destinationList);
        }
        else model.getDestinationMap().forEach((k, v) -> destinationList.getItems().add(k));
    }

    public String getExtension(String filename) {
        String extension = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) extension = filename.substring(i+1);
        return extension;
    }


}