package com.example.class_version_sd2_cw;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static FoodQueue Cashier_01,Cashier_02,Cashier_03;

    private static  final int max_burger_stock = 50;
    private static final int low_stock_msg = 10;
    public static int stock_count = max_burger_stock;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] ags) {
        Cashier_01 = new FoodQueue(2);
        Cashier_02 = new FoodQueue(3);
        Cashier_03 = new FoodQueue(5);
        System.out.println();
        System.out.println("Welcome to \" Foodies Fave Food center \"");
        System.out.println();
        menu_display();
    }
    //making the menu and the switch to represent the inputs for each option
    private static void menu_display() {
        String choice;
        do {
            System.out.println("*****************");
            System.out.println("*  Food Center  *");
            System.out.println("*****************");
            System.out.println("100 or VFQ: View all Queues.");
            System.out.println("101 or VEQ: View all Empty Queues.");
            System.out.println("102 or ACQ: Add customer to a Queue.");
            System.out.println("103 or RCQ: Remove a customer from a Queue. (From a specific location)");
            System.out.println("104 or PCQ: Remove a served customer.");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order.");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file.");
            System.out.println("108 or STK: View Remaining burgers Stock.");
            System.out.println("109 or AFS: Add burgers to Stock.");
            System.out.println("999 or EXT: Exit the Program.");
            System.out.print("Enter your choice: ");
            choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "100", "VFQ":
                    view_all_queues();
                    break;
                case "101", "VEQ":
                    System.out.println();
                    System.out.println("Empty Slots = \"X\"");
                    System.out.println("Reserved Slots = \"O\"");
                    view_all_queues();
                    System.out.println("Empty Slots = \"X\"");
                    System.out.println("Reserved Slots = \"O\"");
                    break;
                case "102", "ACQ":
                    add_customer_queue();
                    break;
                case "103", "RCQ":
                    remove_customers_queue();
                    break;
                case "104", "PCQ":
                    remove_served_customer();
                    break;
                case "105", "VCS":
                    //all_cashier_customer_names = new  String[10];
                    //view_sorted_customer();
                    break;
                case "106", "SPD":
                    //store_program_data();
                    break;
                case "107", "LPD":
                    //load_program_data();
                    break;
                case "108", "STK":
                    //view_remaining_stock();
                    break;
                case "109", "AFS":
                    //add_burgers_to_stock();
                    break;
                case "999", "EXT":
                    System.out.println("Exiting the program.....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice... Please try again...");
                    break;
            }
        } while (choice != "999" && choice != "EXT");// making a default if the user have havent enter the exit commant then loop the menue
    }
    //making the below sub method to display the cashier in vertical
    private static void view_all_queues() {
        System.out.println("*****************");
        System.out.println("*   Cashiers    *");
        System.out.println("*****************");

        String [][] Queue_List = new String[5][3];
        ArrayList<FoodQueue> cashiers = new ArrayList<>();
        cashiers.add(Cashier_01);
        cashiers.add(Cashier_02);
        cashiers.add(Cashier_03);

        int column = 0;
        for (int i = 0; i < 3;i++) {
            FoodQueue foodQueue = cashiers.get(column);
            for(int j = 0; j < 5;j++){
                try {
                    if (foodQueue.get(j)!= null){
                        Queue_List[j][column] = "O";
                    }else if(j < foodQueue.getQueue_size()){
                        Queue_List[j][column] = "X";
                    }else {
                        Queue_List[j][column] = " ";
                    }
                }catch(Exception error){
                    if(j < foodQueue.getQueue_size()){
                        Queue_List[j][column] = "X";
                    }else {
                        Queue_List[j][column] = " ";
                    }
                }
            }column++;
        }
        for (String[] list:Queue_List){
            for (String string:list){
                System.out.print(" "+ string + "     ");
            }
            System.out.println();
        }
    }

    private static void add_customer_queue(){
        FoodQueue queue_size_min = null;
        if (Cashier_01.getQueue().size() < Cashier_01.getQueue_size()){
            queue_size_min = Cashier_01;
        }
        if( Cashier_02.getQueue().size() < Cashier_02.getQueue_size() && Cashier_02.getQueue().size() < Cashier_03.getQueue().size() ){
            queue_size_min = Cashier_02;
        }else if(Cashier_03.getQueue().size() < Cashier_03.getQueue_size() && Cashier_03.getQueue().size() < Cashier_01.getQueue().size()){
            queue_size_min = Cashier_03;
        } else if (Cashier_02.getQueue().size() == Cashier_03.getQueue().size() && Cashier_02.getQueue().size() < Cashier_02.getQueue_size()) {
            queue_size_min = Cashier_02;
        }else if(Cashier_02.getQueue().size() == Cashier_02.getQueue_size()){
            queue_size_min = Cashier_03;
        }
        System.out.print("Enter the Customer First Name: ");
        String customer_first_name = scanner.nextLine();

        System.out.print("Enter the Customer Last Name: ");
        String customer_last_name = scanner.nextLine();

        System.out.print("Enter the Burger count: ");
        int burger_count = scanner.nextInt();
        try{
            Customers newcustomer = new Customers(customer_first_name,customer_last_name,burger_count);
            queue_size_min.addcustomer(newcustomer);
        }catch (NullPointerException error){
            System.out.println("Cashiers are full");
        }
    }
    private static void remove_customers_queue(){
        System.out.print("Enter the Cashier (1-3): ");
        int cashier = scanner.nextInt();

        System.out.print("Enter the Cashier Position: ");
        int position = scanner.nextInt();

        switch (cashier){
            case 1:
                if(!Cashier_01.getQueue().isEmpty()){
                    if (Cashier_01.get(position -1)!= null){
                        Cashier_01.remove(position -1);
                    }else{
                        System.out.println("There is no Customer in the position you entered..");
                    }
                }else {
                    System.out.println("Cashier 01 is Empty");
                }
                break;
            case 2:
                if(!Cashier_02.getQueue().isEmpty()){
                    if (Cashier_02.get(position -1)!= null){
                        Cashier_02.remove(position -1);
                    }else{
                        System.out.println("There is no Customer in the position you entered..");
                    }
                }else {
                    System.out.println("Cashier 02 is Empty");
                }
                break;
            case 3:
                if(!Cashier_03.getQueue().isEmpty()){
                    if (Cashier_03.get(position -1)!= null){
                        Cashier_03.remove(position -1);
                    }else{
                        System.out.println("There is no Customer in the position you entered..");
                    }
                }else {
                    System.out.println("Cashier 03 is Empty");
                }
                break;
        }
    }
    private static void remove_served_customer(){
        System.out.print("Enter the Cashier (1-3): ");
        int cashier = scanner.nextInt();


        switch (cashier){
            case 1:
                if (!Cashier_01.getQueue().isEmpty()){
                    Customers customer_name = Cashier_01.get(0);
                    Cashier_01.remove(0);
                    stock_count -= customer_name.getBurger_Count();
                }else{
                    System.out.println("Cashier 01 is Empty...");
                }
                break;
            case 2:
                if (!Cashier_02.getQueue().isEmpty()){
                    Customers customer_name = Cashier_02.get(0);
                    Cashier_02.remove(0);
                    stock_count -= customer_name.getBurger_Count();
                }else{
                    System.out.println("Cashier 02 is Empty...");
                }
                break;
            case 3:
                if (!Cashier_03.getQueue().isEmpty()){
                    Customers customer_name = Cashier_03.get(0);
                    Cashier_03.remove(0);
                    stock_count -= customer_name.getBurger_Count();
                }else{
                    System.out.println("Cashier 03 is Empty...");
                }
                break;
        }
    }
}
