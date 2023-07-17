// T.J.D.I.FERNANDO
//20220536(IIT_ID)
//w2000072(UOW_ID)
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

    //assigning the queue size to the arraylist
    public FoodQueue(int queue_size) {
        this.queue_size = queue_size;
        this.Queue = new ArrayList<>();
    }

    public void addcustomer(Customers customers){
            Queue.add(customers);
    }
    public void remove(int remove_index){
        Queue.remove(remove_index);
        System.out.println("Customer Removed Successfully..");
        System.out.println();
    }

    //sort main function
    public List<Customers> bubblesort_customer() {
        List<Customers> sortedCustomers = new ArrayList<>(Queue);

        int n = sortedCustomers.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                String currentFirstName = sortedCustomers.get(j).getFirst_Name();
                String nextFirstName = sortedCustomers.get(j + 1).getFirst_Name();

                if (currentFirstName.charAt(0) > nextFirstName.charAt(0)) {
                    // Swap elements
                    Customers temp = sortedCustomers.get(j);
                    sortedCustomers.set(j, sortedCustomers.get(j + 1));
                    sortedCustomers.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return sortedCustomers;
    }


    public Customers get(int index) {
        return Queue.get(index);
    }

    public int getQueue_size() {
        return queue_size;
    }
}



