package practice;

import java.util.Date;
import java.util.concurrent.TimeUnit;

class Customer implements Runnable {
    String name;
    Date inTime;
 
    BarberShop shop;
 
    public Customer(BarberShop shop) {
        this.shop = shop;
    }
 
    public String getName() {
        return name;
    }
 
    public Date getInTime() {
        return inTime;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
 
    public void run() {
        goForHairCut();
    }
    private synchronized void goForHairCut() {
        shop.addCustomer(this);
    }
}
 
class CustomerGenerator implements Runnable {
    BarberShop shop;
 
    public CustomerGenerator(BarberShop shop) {
        this.shop = shop;
    }
 
    public void run() {
        while(true) {
            Customer customer = new Customer(shop);
            customer.setInTime(new Date());
            Thread customerThread = new Thread(customer);
            customer.setName("Customer Thread "+customerThread.getId());
            customerThread.start();
 
            try {
                TimeUnit.SECONDS.sleep((long)(Math.random()*10));
            }
            catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}