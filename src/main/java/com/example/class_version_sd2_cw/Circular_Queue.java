package com.example.class_version_sd2_cw;

import java.util.ArrayList;

public class Circular_Queue {
    private int size;
    private int front;
    private int rear;
    private final ArrayList<Customers> queue = new ArrayList<>();

    public  Circular_Queue(int size) {
        this.size = size;
        this.front = this.rear = -1;
    }

    public boolean isFull() {
        return (front == 0 && rear == size - 1) || (rear == (front - 1) % (size - 1));
    }

    public boolean isEmpty() {
        return (front == -1);
    }

    public void enQueue(Customers value) {
        if (isFull()) {
            System.out.println("Queue Full!");
        } else if (front == -1) {
            front = 0;
            rear = 0;
            queue.add(rear, value);
        } else if (rear == size - 1 && front != 0) {
            rear = 0;
            queue.set(rear, value);
        } else {
            rear = (rear + 1);
            if (front <= rear) {
                queue.add(rear, value);
            } else {
                queue.set(rear, value);
            }
        }
    }

    public Customers deQueue() {
        if (isEmpty()) {
            System.out.println("Queue Empty!");
            return null;
        }
        Customers temp = queue.get(front);

        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        } else {
            front = front + 1;
        }
        return temp;
    }

    public void removeCustomer(Customers customer) {
        if (queue.isEmpty()) {
            System.out.println("Queue Empty!");
            return;
        }

        int index = queue.indexOf(customer);
        if (index != -1) {
            queue.remove(index);
            System.out.println("Customer removed from the waiting list.");
        } else {
            System.out.println("Customer not found in the waiting list.");
        }
    }

    public ArrayList<Customers> getQueue() {
        return queue;
    }
}
