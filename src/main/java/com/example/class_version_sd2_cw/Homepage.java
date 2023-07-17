// T.J.D.I.FERNANDO
//20220536(IIT_ID)
//w2000072(UOW_ID)
package com.example.class_version_sd2_cw;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Homepage implements Initializable{
    @FXML
    public void MainPage(ActionEvent event) throws IOException {
        Parent nextPage = FXMLLoader.load(getClass().getResource("hello-view.fxml")); //referenced https://www.youtube.com/watch?v=nmpRP8mT2nU
        Scene currentScene = ((Button) event.getSource()).getScene();
        currentScene.setRoot(nextPage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
