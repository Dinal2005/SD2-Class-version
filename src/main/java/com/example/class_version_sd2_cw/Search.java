package com.example.class_version_sd2_cw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Search implements Initializable{
    private Stage home;
    private Scene scene;
    private Parent startup;

    public void Back_page(ActionEvent event) throws IOException {
        startup = FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
        home = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(startup);
        home.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
