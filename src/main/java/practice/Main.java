package practice;
 
 
public class Main {
 
    public static void main(String a[]) {
        BarberShop shop = new BarberShop();
 
        Barber barber = new Barber(shop);
        CustomerGenerator customerGenerator = new CustomerGenerator(shop);
 
        Thread barberThread = new Thread(barber);
        Thread customerGeneratorThread = new Thread(customerGenerator);
        customerGeneratorThread.start();
        barberThread.start();
    }
}
 

 