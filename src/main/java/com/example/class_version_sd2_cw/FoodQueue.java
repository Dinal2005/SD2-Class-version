package com.example.class_version_sd2_cw;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class FoodQueue {
    private int queue_size;
    private List<Customers> Queue;

    public List<Customers> getQueue() {
        return Queue;
    }

    public FoodQueue(int queue_size) {
        this.queue_size = queue_size;
        this.Queue = new ArrayList<>();
    }

    public void addcustomer(Customers customers){
        if (Queue.size() < queue_size) {
            Queue.add(customers);
            System.out.println("Customer added successfully.\n");
        } else {
            System.out.println("Queue is full. Customer cannot be added.");
        }
    }
    public void remove(int remove_index){
        Queue.remove(remove_index);
        System.out.println("Customer Removed Successfully..");
        System.out.println();
    }

    public List<Customers> bubblesort_customer(){
        List<Customers> sort_customer = new ArrayList<>(Queue);
        for (int i = 0; i < sort_customer.size()-1; i++){
            if (sort_customer.get(i).getFirst_Name().charAt(0) > sort_customer.get(i+1).getFirst_Name().charAt(0)){
                Customers temp = sort_customer.get(i);
                sort_customer.set(i, sort_customer.get(i+1));
                sort_customer.set(i+1,temp);
            }
        }
        return sort_customer;
    }

    public Customers get(int index) {
        return Queue.get(index);
    }

    public int getQueue_size() {
        return queue_size;
    }
}



