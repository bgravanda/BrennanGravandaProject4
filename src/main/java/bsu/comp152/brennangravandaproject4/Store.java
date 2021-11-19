package bsu.comp152.brennangravandaproject4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


public class Store {

    private ArrayList<Order> allOrders;
    private ArrayList<Customer> allCustomers;
    private static int nextID = 1005;
    private ArrayList<merchandiseItem>allItems;
    private ArrayList<ItemType>allItemTypes;
    private double revenue;
    private double itemPrice;
    private ItemType itemType;



//Reads in all the customers in Customers.txt
    public Store() throws IOException {
        allOrders = new ArrayList<Order>();
        allCustomers = new ArrayList<Customer>();
        var filename = "Customers.txt";
        var filePath = Paths.get(filename);
        var allLines = Files.readAllLines(filePath);
        for (var line : allLines) {
            var splitLine = line.split(",");
            var customerName = splitLine[0];
            var customerID = Integer.parseInt(splitLine[1]);
            var customerType = splitLine[2];
            if (customerType.equals("ResidentialCustomer")) {
                var newCustomer = new ResidentialCustomer(customerName, customerID);
                allCustomers.add(newCustomer);
            }
            else if (customerType.equals("BusinessCustomer")) {
                var newCustomer = new BusinessCustomer(customerName, customerID);
                allCustomers.add(newCustomer);
            }
            else if (customerType.equals("TaxExemptCustomer")){
                var newCustomer = new TaxExemptCustomer(customerName,customerID);
                allCustomers.add(newCustomer);
            }

        }
        //reads all items in from Items.txt
        allItems = new ArrayList<merchandiseItem>();
        allItemTypes = new ArrayList<ItemType>();
        var filename2 = "Items.txt";
        var filePath2 = Paths.get(filename2);
        var allLines2 = Files.readAllLines(filePath2);
        for ( var line : allLines2){

            var splitLine = line.split(",");
            var itemName = splitLine[0];
            itemType = ItemType.valueOf(splitLine[1]);
             itemPrice = Double.parseDouble(splitLine[2]);
            var newItem = new merchandiseItem(itemName,itemPrice,itemType);
            allItems.add(newItem);


        }

    }
    public ArrayList<merchandiseItem>getAllItems(){
        return allItems;
    }
//Gives the customer a menu
    public void runStore() {
        var menuReader = new Scanner(System.in);
        while (true) {
            printMenu();
            var userChoice = menuReader.nextInt();
            switch (userChoice) {
                case 1:
                    System.exit(0);
                case 2:
                    addCustomer(menuReader);//initiates add customer
                    break;
                case 3:
                    Optional<Customer> current = selectCustomer(menuReader);//starts a search for the customer in the array list
                    if (current.isPresent())
                        ManageCustomerMenu(menuReader, current.get());
                    else
                        System.out.println("No customer exists with that ID");
                    break;
                default:
                    System.out.println("Please choose one of the menu options");
                case 4:
                    for(var cust :allCustomers){//gets the revenue of the store
                        var rev = cust.payOutstandingBalance();
                        revenue = rev + revenue;
                    }
                    System.out.println("The revenue is"+revenue);

            }
        }
    }
//Once a customer is selected this gives more options to do with customer
    private void ManageCustomerMenu(Scanner menuReader, Customer currentCustomer) {

        while (true) {
            printManageCustomerMenu(currentCustomer);
            var customerChoice = menuReader.nextInt();
            switch (customerChoice) {
                case 1:
                   makeOrder(currentCustomer, menuReader);
                    break;
                case 2:
                    var shipping = new ShippingAddress(menuReader);
                    currentCustomer.addAddress(shipping);
                    System.out.println(currentCustomer.getName()+" has an address of "+shipping.getAddress());
                    break;
                case 3:
                    return;
            }
        }
    }
//Creates a cart and selects address and initiates
    private void makeOrder(Customer CustOrder, Scanner Input) {
        var cart = new ArrayList<merchandiseItem>();
        while (true){//has the customer create a cart
            for (var list: allItems){
                System.out.println(list);

            }
            System.out.println("select an item starting with 0 if you are done adding items type a negative number");


            var item = Input.nextInt();
        if (item<0) {
            break;
        }
            var curItem = allItems.get(item);
            cart.add(curItem);
        }
        Input.nextLine();
        var custadd=CustOrder.SelectAddress(Input);//selects the address for the order
        System.out.println(custadd.toString());
        var currentOrder = new Order(custadd, CustOrder,cart);
        System.out.println(currentOrder.getOrderedBy()+"'s order will be sent to "+currentOrder.getDestination());
        var pay = CustOrder.payForOrder(cart);
        CustOrder.arrangeDelivery();
        revenue= revenue+pay;//adds the amount to the revenue of the store
    }

//adds a customer and gives a customer ID also prompts the customer to select a type
    private void addCustomer(Scanner inputReader) {
        System.out.println("What is the new Customer's name:");
        inputReader.nextLine();
        var customerName = inputReader.nextLine();
        var custID = nextID;
        nextID++;
        System.out.println(customerName+" has an ID of "+custID);
        //has the customer select what type they are
        System.out.println("Say if you are a Residential Customer type 1, a Business Customer type 2, and for Tax Exempt Customer type 3");
        var customerType = inputReader.nextLine();
        if (customerType.equals("1")) {
            var newCustomer = new ResidentialCustomer(customerName, custID);
            allCustomers.add(newCustomer);
        }
        else if (customerType.equals("2")) {
            var newCustomer = new BusinessCustomer(customerName, custID);
            allCustomers.add(newCustomer);
        }
        else if (customerType.equals("3")){
            var newCustomer = new TaxExemptCustomer(customerName,custID);
            allCustomers.add(newCustomer);
        }

    }
//searches for a customer in array list that matches
    private Optional<Customer> selectCustomer(Scanner reader) {
        System.out.println("Customer ID of customer to select");
        var idToFind = reader.nextInt();
        for (var currentCustomer : allCustomers) {
            if (currentCustomer.getID() == idToFind)
                return Optional.of(currentCustomer);

        }
        return Optional.empty();
    }

//First menu
    private void printMenu() {
        System.out.println("*************************");
        System.out.println("What would you like to do next(select the number):");
        System.out.println("[1] Exit Program");
        System.out.println("[2] add customer");
        System.out.println("[3] Select a customer");
        System.out.println("[4] get revenue");
        System.out.println("we'll add more soon");
        System.out.println("********************************");
        System.out.println("Enter choice:");
    }
//second menu
    private void printManageCustomerMenu(Customer currentCustomer) {
        System.out.println("**************************************");
        System.out.println("What do you want to do with "+currentCustomer.getName()+" ?");
        System.out.println("[1] Make an order");
        System.out.println("[2] Add an address");
        System.out.println("[3] Return to main menu");
        System.out.println("**************************");
        System.out.println("Enter Choice:");


    }
}




