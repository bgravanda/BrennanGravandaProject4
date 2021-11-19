package bsu.comp152.brennangravandaproject4;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StoreDisplayController implements Initializable {
    @FXML
    private TextField NameField;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField ItemTypeField;
    @FXML
    private ChoiceBox ItemTypeChoice;
    @FXML
    private ListView<merchandiseItem> ListControl;
    private merchandiseItem List;
    private String name;
    private double price;
    private ItemType items;

    public void LoadList()throws IOException {   //Loads the merchandise items into an observable list
        var StoreItems = new Store();
        var itemsInList = StoreItems.getAllItems();
        List = new merchandiseItem(name,price,items);
        ObservableList<merchandiseItem> ItemList = FXCollections.observableArrayList(itemsInList);
        ListControl.setItems(ItemList);

    }
    public void onAddButtonClicked(){  //Adds new Item to list
        ListControl = new ListView<merchandiseItem>();
        var itemName = NameField.getText();
        var itemPrice = PriceField.getText();
        var itemType = ItemTypeField.getText();
        double itemPrice2 = Double.parseDouble(itemPrice);
        ItemType itemType2 = ItemType.valueOf(itemType);
        var newItem = new merchandiseItem(itemName,itemPrice2,itemType2);
        ListControl.add(newItem);

    }
    public void setItemTypeChoice(){
        ChoiceBox ItemTypeChoice = new ChoiceBox<>();
        ItemTypeChoice.getItems().add("General Merchandise");
        ItemTypeChoice.getItems().add("WIC Food");
        ItemTypeChoice.getItems().add("Clothing");
    }
    public void onSaveButtonClicked(){  //saves the edits to the items

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {//fills in all the text fields upon selection
        try {
            LoadList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ListControl.getSelectionModel().selectedItemProperty().addListener
                (new ChangeListener<merchandiseItem>() {
                    @Override
                    public void changed(ObservableValue<? extends merchandiseItem> observableValue, merchandiseItem merchandiseItem, merchandiseItem t1) {
                        NameField.setText(t1.getName());
                        PriceField.setText(String.valueOf(t1.getPrice()));
                        ItemTypeField.setText(String.valueOf(t1.getType()));
                    }
                });
    }
}