package com.api.filemovermvc;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileMoveController {
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



    public void submitPressed(ActionEvent actionEvent) {
    }

    public void openSourcePressed(ActionEvent actionEvent) {
        ArrayList<File> files = model.openSource();
        if(!sourceList.getItems().isEmpty())
            sourceList.getItems().clear();
        sourceList.getItems().addAll(files);
    }

    public void openDestPressed(ActionEvent actionEvent) {
        HashMap<File, ArrayList<String>> destination = model.loadDestination("config.csv");
        if(!destinationList.getItems().isEmpty())
            destinationList.getItems().clear();
        destination.forEach((k, v) -> destinationList.getItems().add(k));
    }

    public void sourceViewClicked(MouseEvent mouseEvent) {
        if (!sourceList.getItems().isEmpty() && sourceList.getSelectionModel().getSelectedIndex() != -1) {
            int index = sourceList.getSelectionModel().getSelectedIndex();
            File[] fileList = model.getSourceFileList();
            sourceName.setText(fileList[index].getName());
            sourceLastMod.setText(String.valueOf(fileList[index].lastModified()));
            String ext = model.getExtension(fileList[index].getName());

            if (!ext.isEmpty())
                sourceFileExt.setText(model.getExtension(fileList[index].getName()));
            else sourceFileExt.setText("directory");
        }
    }

    public void destListClicked(MouseEvent mouseEvent) {
        if (!destinationList.getItems().isEmpty() && destinationList.getSelectionModel().getSelectedIndex() != -1) {
            File selectedItem = destinationList.getSelectionModel().getSelectedItem();
            destName.setText(selectedItem.getName());
            destLastMod.setText(String.valueOf(selectedItem.lastModified()));
            destExtensions.setText(Arrays.toString(new ArrayList[]{model.getDestinationMap().get(selectedItem)}));
        }
    }
}