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

public class HelloController implements Initializable {
    @FXML
    private Label stock;
    @FXML
    private Label First_name;
    @FXML
    private Label Last_name;
    @FXML
    private Label Burger_count;
    @FXML
    private VBox cashier1;
    @FXML
    private VBox cashier2;
    @FXML
    private VBox cashier3;
    private Stage home;
    private Scene scene;
    private Parent startup;

    public void SearchPage(ActionEvent event) throws IOException {
        startup = FXMLLoader.load(HelloApplication.class.getResource("search.fxml"));
        home = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(startup);
        home.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FoodQueue cashier_01 = Main.Cashier_01;
        FoodQueue cashier_02 = Main.Cashier_02;
        FoodQueue cashier_03 = Main.Cashier_03;
        int Burger_stock = Main.stock_count;
        stock.setText(String.valueOf(Burger_stock));

        for (int i = 0; i < cashier_01.getQueue().size(); i++){
            cashier1.getChildren().get(i).setVisible(true);

            int finalI=i;
            cashier1.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                First_name.setText(cashier_01.getQueue().get(finalI).getFirst_Name());
                Last_name.setText(cashier_01.getQueue().get(finalI).getLast_Name());
                Burger_count.setText(String.valueOf(cashier_01.getQueue().get(finalI).getBurger_Count()));
            });
        }
        for (int i = 0; i < cashier_02.getQueue().size(); i++){
            cashier2.getChildren().get(i).setVisible(true);

            int finalI=i;
            cashier2.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                First_name.setText(cashier_02.getQueue().get(finalI).getFirst_Name());
                Last_name.setText(cashier_02.getQueue().get(finalI).getLast_Name());
                Burger_count.setText(String.valueOf(cashier_02.getQueue().get(finalI).getBurger_Count()));
            });
        }
        for (int i = 0; i < cashier_03.getQueue().size(); i++){
            cashier3.getChildren().get(i).setVisible(true);

            int finalI=i;
            cashier3.getChildren().get(i).setOnMouseClicked(mouseEvent -> {
                First_name.setText(cashier_03.getQueue().get(finalI).getFirst_Name());
                Last_name.setText(cashier_03.getQueue().get(finalI).getLast_Name());
                Burger_count.setText(String.valueOf(cashier_03.getQueue().get(finalI).getBurger_Count()));
            });
        }
    }
}