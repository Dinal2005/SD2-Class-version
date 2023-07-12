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
        System.out.println("Customer Removed Successfully..\n");
    }

    public Customers get(int index) {
        return Queue.get(index);
    }

    public int getQueue_size() {
        return queue_size;
    }
}



