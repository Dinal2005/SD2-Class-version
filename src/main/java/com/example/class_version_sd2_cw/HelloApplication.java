// T.J.D.I.FERNANDO
//20220536(IIT_ID)
//w2000072(UOW_ID)
package com.example.class_version_sd2_cw;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Homepage.fxml"));//referenced https://www.youtube.com/watch?v=nmpRP8mT2nU
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Foodies Fave Queue Management System!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}