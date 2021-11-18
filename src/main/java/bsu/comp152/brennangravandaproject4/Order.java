package bsu.comp152.brennangravandaproject4;

import java.util.ArrayList;

public class Order {
    private ShippingAddress destination;
    private Customer orderedBy;
    private ArrayList<merchandiseItem>cartForOrder;
    public Order(ShippingAddress dest, Customer orderer,ArrayList<merchandiseItem> cart){
        destination = dest;
        orderedBy = orderer;
        cartForOrder= cart ;

    }
    //gets who ordered the items
    public String getOrderedBy(){
        var newOrder = orderedBy.toString();
        return newOrder;
    }
    //gets the destination of the package
    public String getDestination(){
        var newDestination=destination.toString();
        return newDestination;
    }

}
