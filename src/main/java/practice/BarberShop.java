package practice;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class BarberShop {
    int chairsNumber;
    List<Customer> listOfCustomers;
 
    public BarberShop() {
        chairsNumber = 3;
        listOfCustomers = new LinkedList<Customer>();
    }
 
    public void cutHair() {
        Customer customer;
        System.out.println("Barber waiting for lock.");
        synchronized (listOfCustomers) {
 
            while(listOfCustomers.size()==0) {
                System.out.println("Barber is waiting for customer.");
                try {
                    listOfCustomers.wait();
                }
                catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Barber found a customer in the queue.");
            customer = (Customer)((LinkedList<?>)listOfCustomers).poll();
        }
        long duration=0;
        try {    
            System.out.println("Cuting hair of Customer : "+customer.getName());
            duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        }
        catch(InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Completed Cuting hair of Customer : "+customer.getName() + " in "+duration+ " seconds.");
    }
 
    public void addCustomer(Customer customer) {
        System.out.println("Customer : "+customer.getName()+ " entering the shop at "+customer.getInTime());
 
        synchronized (listOfCustomers) {
            if(listOfCustomers.size() == chairsNumber) {
                System.out.println("No chair available for customer "+customer.getName());
                System.out.println("Customer "+customer.getName()+"Exists...");
                return ;
            }
 
            ((LinkedList<Customer>)listOfCustomers).offer(customer);
            System.out.println("Customer : "+customer.getName()+ " got the chair.");
             
            if(listOfCustomers.size()==1)
                listOfCustomers.notify();
        }
    }
}