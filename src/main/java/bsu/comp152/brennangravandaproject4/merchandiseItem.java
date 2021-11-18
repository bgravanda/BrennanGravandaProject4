package bsu.comp152.brennangravandaproject4;

import bsu.comp152.brennangravandaproject4.ItemType;

public class merchandiseItem {
    private ItemType taxibleType;
    private String Name;
    private double price;

    public merchandiseItem(String Name, double price, ItemType type){
        taxibleType = type;
        this.Name=Name;
        this.price = price;

    }
    //gets item name
    public String getName(){

        return Name;
    }
    //gets item price
    public double getPrice(){
        return price;
    }
    //gets item type
    public ItemType getType(){

        return taxibleType;
    }
    //puts the merchandise item to a string
    public String toString(){
        return Name;
    }
}
