package bsu.comp152.brennangravandaproject4;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Customer {
    private int customerID;
    private String name;
    private ArrayList<ShippingAddress> addresses;
    public abstract double payForOrder(ArrayList<merchandiseItem> itemsInOrder);
//adds an address
    public void addAddress(ShippingAddress newAddress){
        addresses.add(newAddress);


    }


    public Customer(String customerName, Integer custID) {
        customerID = custID;
        name = customerName;
        addresses = new ArrayList<ShippingAddress>();
    }
//gets name of customer
    public String getName(){
        return name;

    }
//gets the customer ID
    public int getID(){
        return customerID;

    }
    //allows customer to select an address
    public ShippingAddress  SelectAddress(Scanner Select){
        for(var location: addresses) {
            System.out.println(location);
        }
            System.out.println("Select an address starting with 0");
        var Input = Select.nextInt();
        var addy = addresses.get(Input);
        return addy;
    }
//puts the customer and ID into string
    public String toString() {
        return customerID+" "+name;
    }
    //returns to 0
    public double payOutstandingBalance(){
        return 0.0;
    }
    //arranges the delivery
    public void arrangeDelivery(){
        System.out.println(name+"'s order will be delivered any time");
    }


}

