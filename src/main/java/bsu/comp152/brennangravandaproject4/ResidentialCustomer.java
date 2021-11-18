package bsu.comp152.brennangravandaproject4;

import bsu.comp152.brennangravandaproject4.Customer;
import bsu.comp152.brennangravandaproject4.ItemType;
import bsu.comp152.brennangravandaproject4.merchandiseItem;

import java.util.ArrayList;

public class ResidentialCustomer extends Customer {
    public ResidentialCustomer(String customerName, Integer custID) {
        super(customerName, custID);
    }
// gets the sum and applies tax
    public double payForOrder(ArrayList<merchandiseItem> itemsInOrder) {
        double sum = 0.0;
        for (var item : itemsInOrder){
            if(item.getType() == ItemType.WICFood) {
                sum += item.getPrice();
            }
            //applies tax to clothing
            if(item.getType()==ItemType.Clothing){
                sum += item.getPrice();
                if(sum>=175){
                    var taxedAmount = sum-175;
                    var tax = taxedAmount*.0625;
                    sum = sum + tax;
                }
            }
            //adds tax to general items
            if(item.getType()==ItemType.GeneralMerchandise){
                sum += item.getPrice();
                var tax = sum*.0625;
                sum = sum+tax;
            }
        }
        return sum;
    }
}