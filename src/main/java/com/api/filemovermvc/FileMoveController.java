package com.api.filemovermvc;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

import java.io.File;

public class FileMoveController {

    private FileMoveModel model = new FileMoveModel();
    public MenuItem openSourceButton;
    public MenuItem openDestButton;
    public Button submitButton;
    public ListView<File> sourceList;
    public ListView destinationList;
    public Label infoBox;



    public void submitPressed(ActionEvent actionEvent) {
    }

    public void openSourcePressed(ActionEvent actionEvent) {
        sourceList.getItems().addAll(model.openSource());
    }
}