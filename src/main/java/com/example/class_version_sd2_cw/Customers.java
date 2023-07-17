// T.J.D.I.FERNANDO
//20220536(IIT_ID)
//w2000072(UOW_ID)
package com.example.class_version_sd2_cw;

public class Customers {
    private String First_Name; // setting up instance variable
    private String Last_Name;
    private int Burger_Count;

    //constructed the instance variable
    public Customers(String first_Name, String last_Name, int burger_Count) {
        First_Name = first_Name;
        Last_Name = last_Name;
        Burger_Count = burger_Count;
    }

    //getters
    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public int getBurger_Count() {
        return Burger_Count;
    }
}
