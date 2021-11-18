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
    public void LoadList(){   //Loads the merchandise items into an observable list
        ObservableList<merchandiseItem> ItemList = FXCollections.observableArrayList();
        ListControl.setItems(ItemList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadList();
        ListControl.getSelectionModel().selectedItemProperty().addListener
                (new ChangeListener<merchandiseItem>() {
                    @Override
                    public void changed(ObservableValue<? extends merchandiseItem> observableValue, merchandiseItem merchandiseItem, merchandiseItem t1) {
                        NameField.setText(t1.getName());
                        PriceField.setText(t1.getPrice());
                        ItemTypeField.setText(t1.getType());
                    }
                });
    }
}