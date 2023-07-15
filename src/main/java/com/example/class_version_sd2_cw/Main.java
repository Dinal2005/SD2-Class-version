package com.example.class_version_sd2_cw;

import javafx.application.Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static FoodQueue Cashier_01,Cashier_02,Cashier_03;

    private static  final int max_burger_stock = 50;
    private static final int low_stock_msg = 10;
    public static int stock_count = max_burger_stock;
    private static final int price_per_burger = 650;
    private static int Cashier_01_income = 0;
    private static int Cashier_02_income = 0;
    private static int Cashier_03_income = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Cashier_01 = new FoodQueue(2);
        Cashier_02 = new FoodQueue(3);
        Cashier_03 = new FoodQueue(5);
        System.out.println();
        System.out.println("Welcome to \" Foodies Fave Food center \"");
        System.out.println();
        //making the menu and the switch to represent the inputs for each option
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
            System.out.println("110 or IFQ: View income of the cashiers.");
            System.out.println("112 or GUI: View GUI program.");
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
                    view_sorted_customer();
                    break;
                case "106", "SPD":
                    store_program_data();
                    break;
                case "107", "LPD":
                    load_program_data();
                    break;
                case "108", "STK":
                    view_remaining_stock();
                    break;
                case "109", "AFS":
                    add_burgers_to_stock();
                    break;
                case "110", "IFQ":
                    view_income_of_each_queue();
                    break;
                case "112", "GUI":
                    Application.launch(HelloApplication.class, args);
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
        if(Cashier_02.getQueue().size() < Cashier_02.getQueue_size() && Cashier_02.getQueue().size() < Cashier_03.getQueue().size() ){
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
            Customers newcustomer = new Customers(customer_first_name.substring(0,1).toLowerCase()+customer_first_name.substring(1),customer_last_name.substring(0,1).toLowerCase()+customer_last_name.substring(1),burger_count);
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
    private static void remove_served_customer() {
        if (stock_count > 0) {
            System.out.print("Enter the Cashier (1-3): ");
            int cashier = scanner.nextInt();

            if (stock_count > 0) {
                System.out.println();
                if (cashier == 1) {
                    if (!Cashier_01.getQueue().isEmpty()) {
                        Customers customer_name = Cashier_01.get(0);
                        int requestedBurgerCount = customer_name.getBurger_Count();

                        if (requestedBurgerCount <= stock_count) {
                            Cashier_01.remove(0);
                            stock_count -= requestedBurgerCount;
                            Cashier_01_income = requestedBurgerCount * price_per_burger;
                            System.out.println("Thank you for ordering From \"Foodies Fave Food center\"");
                            System.out.println("Please Come Again!!!!");
                        } else {
                            System.out.println("Please add burgers, cannot serve " + requestedBurgerCount + " burgers.");
                        }
                    } else {
                        System.out.println("Cashier 01 is Empty...");
                    }
                } else if (cashier == 2) {
                    if (!Cashier_02.getQueue().isEmpty()) {
                        Customers customer_name = Cashier_02.get(0);
                        int requestedBurgerCount = customer_name.getBurger_Count();

                        if (requestedBurgerCount <= stock_count) {
                            Cashier_02.remove(0);
                            stock_count -= requestedBurgerCount;
                            Cashier_02_income = requestedBurgerCount * price_per_burger;
                            System.out.println("Thank you for ordering From \"Foodies Fave Food center\"");
                            System.out.println("Please Come Again!!!!");
                        } else {
                            System.out.println("Please add burgers, cannot serve " + requestedBurgerCount + " burgers.");
                        }
                    } else {
                        System.out.println("Cashier 02 is Empty...");
                    }
                } else if (cashier == 3) {
                    if (!Cashier_03.getQueue().isEmpty()) {
                        Customers customer_name = Cashier_03.get(0);
                        int requestedBurgerCount = customer_name.getBurger_Count();

                        if (requestedBurgerCount <= stock_count) {
                            Cashier_03.remove(0);
                            stock_count -= requestedBurgerCount;
                            Cashier_03_income = requestedBurgerCount * price_per_burger;
                            System.out.println("Thank you for ordering From \"Foodies Fave Food center\"");
                            System.out.println("Please Come Again!!!!");
                        } else {
                            System.out.println("Please add burgers, cannot serve " + requestedBurgerCount + " burgers.");
                        }
                    } else {
                        System.out.println("Cashier 03 is Empty...");
                    }
                }
            }
            if (stock_count < low_stock_msg) {
                System.out.println();
                System.out.println("Warning: Low stock! Remaining burger count is: " + stock_count);
            }
        } else {
            System.out.println();
            System.out.println("Stock is: " + stock_count + " Please add Burgers to the stock..");
            System.out.println();
        }
    }

    //making the option to see the customers in the 3 queues in alphabetical order
    public static void view_sorted_customer() {
        List<Customers> sort_customer = new ArrayList<>();

        sort_customer.addAll(Cashier_01.bubblesort_customer());
        sort_customer.addAll(Cashier_02.bubblesort_customer());
        sort_customer.addAll(Cashier_03.bubblesort_customer());

        bubbleSort(sort_customer);

        System.out.println("Sorted Customer List:");
        for (Customers customer : sort_customer) {
            System.out.println(customer.getFirst_Name() + " " + customer.getLast_Name());
        }
    }

    public static void bubbleSort(List<Customers> customers) {
        int n = customers.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (customers.get(j).getFirst_Name().charAt(0) > customers.get(j + 1).getFirst_Name().charAt(0)) {
                    Customers temp = customers.get(j);
                    customers.set(j, customers.get(j + 1));
                    customers.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    private static void store_program_data(){
        try {
            FileWriter output_file = new FileWriter("Output.txt");//textfile name and filewritter assigning
            output_file.write("Current Burger Stock is:\n" +stock_count + "\n\n");//writing the burger stock
            System.out.println();
            output_file.write("Cashier 01 customer name:\n");
            for (int i = 0; i < Cashier_01.getQueue_size();i++){
                try {
                    Customers customers = Cashier_01.get(i);
                    output_file.write(customers.getFirst_Name()+" " +customers.getLast_Name()+ " "+ customers.getBurger_Count());
                    output_file.write(System.lineSeparator());
                }catch (Exception error){
                    output_file.write("Empty Slot");
                    output_file.write(System.lineSeparator());
                }
            }
            output_file.write(System.lineSeparator());
            output_file.write("Cashier 02 customer name:\n");
            for (int i = 0; i < Cashier_02.getQueue_size();i++){
                try {
                    Customers customers = Cashier_02.get(i);
                    output_file.write(customers.getFirst_Name()+" " +customers.getLast_Name()+ " "+ customers.getBurger_Count());
                    output_file.write(System.lineSeparator());
                }catch (Exception error){
                    output_file.write("Empty Slot");
                    output_file.write(System.lineSeparator());
                }
            }
            output_file.write(System.lineSeparator());
            output_file.write("Cashier 03 customer name:\n");
            for (int i = 0; i < Cashier_03.getQueue_size();i++){
                try {
                    Customers customers = Cashier_03.get(i);
                    output_file.write(customers.getFirst_Name()+" " +customers.getLast_Name()+ " "+ customers.getBurger_Count());
                    output_file.write(System.lineSeparator());
                }catch (Exception error){
                    output_file.write("Empty Slot");
                    output_file.write(System.lineSeparator());
                }
            }
            output_file.write(System.lineSeparator());
            output_file.close();
            System.out.println("Successfully stored the data into the text file...");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file");
        }
    }


    private static void load_program_data() {
        File file = new File("Output.txt");
        String name = "";
        String firstName = "";
        String lastName = "";
        int burgerCount = 0;
        int count = 1;
        int customerCount = 0;

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Cashier 01 customer name")) {
                    while (scanner.hasNext()) {
                        name = scanner.next();
                        if (!name.equals("Empty")) {
                            if (count == 1) {
                                firstName = name;
                                count++;
                            } else if (count == 2) {
                                lastName = name;
                                count++;
                            } else if (count == 3) {
                                burgerCount = Integer.parseInt(name);
                                Customers customer = new Customers(firstName, lastName, burgerCount);
                                Cashier_01.addcustomer(customer);
                                count = 1;

                                if (customerCount < 1) {
                                    customerCount++;
                                } else {
                                    customerCount = 0;
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }
            }

            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Cashier 02 customer name")) {
                    while (scanner.hasNext()) {
                        name = scanner.next();
                        if (!name.equals("Empty")) {
                            if (count == 1) {
                                firstName = name;
                                count++;
                            } else if (count == 2) {
                                lastName = name;
                                count++;
                            } else if (count == 3) {
                                burgerCount = Integer.parseInt(name);
                                Customers customer = new Customers(firstName, lastName, burgerCount);
                                Cashier_02.addcustomer(customer);
                                count = 1;

                                if (customerCount < 2) {
                                    customerCount++;
                                } else {
                                    customerCount = 0;
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }
            }

            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Cashier 03 customer name")) {
                    while (scanner.hasNext()) {
                        name = scanner.next();
                        if (!name.equals("Empty")) {
                            if (count == 1) {
                                firstName = name;
                                count++;
                            } else if (count == 2) {
                                lastName = name;
                                count++;
                            } else if (count == 3) {
                                burgerCount = Integer.parseInt(name);
                                Customers customer = new Customers(firstName, lastName, burgerCount);
                                Cashier_03.addcustomer(customer);
                                count = 1;

                                if (customerCount < 4) {
                                    customerCount++;
                                } else {
                                    customerCount = 0;
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }
            }

            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Current Burger Stock is")) {
                    burgerCount = Integer.parseInt(scanner.next());
                    stock_count = burgerCount;
                    break;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Program data loaded successfully.");
    }



    //making the option to view the remaining burger stock
    private static void view_remaining_stock(){
        System.out.println();
        System.out.println("Remaining burger stock balance is: " + stock_count);
        System.out.println();
    }

    //making the option to add burgers to the current burger stock
    private static void add_burgers_to_stock(){
        System.out.println();
        System.out.print("Enter the number of burgers to add : ");
        int burger_add = scanner.nextInt();

        if(burger_add < 0){
            System.out.println("Invalid number of burgers... please try again...");
            System.out.println();
            return;
        }
        int new_stock_count = stock_count + burger_add;
        if(new_stock_count >= 51){
            System.out.println("Maximum stock count exceeded. Maximum stock is 50. Current stock count it: " +stock_count);
            System.out.println();
            return;
        }
        stock_count += burger_add;
        System.out.println();
        System.out.println(burger_add + " burgers added to the burger stock... Remaining burgers are: " + stock_count);
        System.out.println();
    }

    private static void view_income_of_each_queue(){
        System.out.println("Cashier 01 Income is: " +"Rs. " + Cashier_01_income);
        System.out.println("Cashier 02 Income is: " +"Rs. " + Cashier_02_income);
        System.out.println("Cashier 03 Income is: " +"Rs. " + Cashier_03_income);
    }
}
