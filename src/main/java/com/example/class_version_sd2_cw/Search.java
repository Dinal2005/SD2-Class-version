// T.J.D.I.FERNANDO
//20220536(IIT_ID)
//w2000072(UOW_ID)
package com.example.class_version_sd2_cw;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Search implements Initializable {
    private static FoodQueue cashier1, cashier2, cashier3;

    @FXML
    private TextField Search_box;
    @FXML
    private ListView list_output;

    @FXML
    public void Back_page(ActionEvent event) throws IOException {
        Parent nextPage = FXMLLoader.load(getClass().getResource("hello-view.fxml")); // referenced https://www.youtube.com/watch?v=nmpRP8mT2nU
        Scene currentScene = ((Button) event.getSource()).getScene();
        currentScene.setRoot(nextPage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cashier1 = Main.Cashier_01;
        cashier2 = Main.Cashier_02;
        cashier3 = Main.Cashier_03;
    }

    @FXML
    private void Find() {
        list_output.getItems().clear();
        String name = Search_box.getText();
        String name2;
        if (!name.equals(" ")) {
            name2 = name.substring(0, 1).toLowerCase() + name.substring(1);
            for (Customers customers : cashier1.getQueue()) {
                if (customers.getFirst_Name().contains(name2) && name2.substring(0, 1).equals(customers.getFirst_Name().substring(0, 1))) {
                    list_output.getItems().add(customers.getFirst_Name() + " " + customers.getLast_Name() + " " + String.valueOf(customers.getBurger_Count()) + " Burgers" + "    In Cashier01 Row: " + String.valueOf(cashier1.getQueue().indexOf(customers) + 1));
                }
            }
            for (Customers customers : cashier2.getQueue()) {
                if (customers.getFirst_Name().contains(name2) && name2.substring(0, 1).equals(customers.getFirst_Name().substring(0, 1))) {
                    list_output.getItems().add(customers.getFirst_Name() + " " + customers.getLast_Name() + " " + String.valueOf(customers.getBurger_Count()) + " Burgers" + "    In Cashier02 Row: " + String.valueOf(cashier2.getQueue().indexOf(customers) + 1));
                }
            }
            for (Customers customers : cashier3.getQueue()) {
                if (customers.getFirst_Name().contains(name2) && name2.substring(0, 1).equals(customers.getFirst_Name().substring(0, 1))) {
                    list_output.getItems().add(customers.getFirst_Name() + " " + customers.getLast_Name() + " " + String.valueOf(customers.getBurger_Count()) + " Burgers" + "    In Cashier03 Row: " + String.valueOf(cashier3.getQueue().indexOf(customers) + 1));
                }
            }
        }
    }
}
