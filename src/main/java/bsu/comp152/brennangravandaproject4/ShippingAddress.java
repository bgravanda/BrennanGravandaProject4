package bsu.comp152.brennangravandaproject4;

import java.util.Scanner;

public class ShippingAddress {
    private String AddressLine1;
    private String State;
    private String City;
    private String PostalCode;
    private String Address;
//creates the address
    public ShippingAddress(Scanner inputReader) {
        inputReader.nextLine();
        System.out.println("What is the address?");
        AddressLine1 = inputReader.nextLine();
        System.out.println("what is the city?");
        City = inputReader.nextLine();
        System.out.println("What is the state?");
        State = inputReader.nextLine();
        System.out.println("what is the postal code?");
        PostalCode = inputReader.nextLine();
        Address = AddressLine1+","+City+","+State+","+PostalCode;
    }
    //gets the address
    public String getAddress(){
        return Address;

    }
    //creates the address to a string
    public String toString(){
        return Address;
    }

}
