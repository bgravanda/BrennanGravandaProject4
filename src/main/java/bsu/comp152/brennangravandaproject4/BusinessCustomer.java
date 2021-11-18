package bsu.comp152.brennangravandaproject4;

import java.util.ArrayList;

//The following code is for the customer type Business Customer
public class BusinessCustomer extends Customer {
    private double purchaseOrderBalance;
    private int PersonID;
    private String CustName;

    public BusinessCustomer(String customerName, Integer custID) {
        super(customerName, custID);
    }
// pays for the order with tax
    public double payForOrder(ArrayList<merchandiseItem> itemsInOrder){
        double sum = 0.0;
        for (var item : itemsInOrder){
            if(item.getType() == ItemType.WICFood) {
                sum += item.getPrice();
            }
            //Adds tax to clothing
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
        purchaseOrderBalance = purchaseOrderBalance + sum;
        return sum;
    }
//arranges the delivery
    public void arrangeDelivery(){

        System.out.println("this order will be for "+getName()+" and it will be payed with a purchase order");
    }
    //applies the discount
    public double payOutstandingBalance(){
        if (purchaseOrderBalance >= 1000) {
            var discountAmount = purchaseOrderBalance * .05;
            purchaseOrderBalance = purchaseOrderBalance - discountAmount;

        }
        var orderBalance = purchaseOrderBalance;
        purchaseOrderBalance = 0;
        return orderBalance;
    }


}
