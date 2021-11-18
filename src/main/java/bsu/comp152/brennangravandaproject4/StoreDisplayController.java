package bsu.comp152.brennangravandaproject4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class StoreDisplayController implements Initializable {
    @FXML
    private TextField NameField;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField ItemTypeField;
    @FXML
    private ListView<merchandiseItem> ListControl;
    private merchandiseItem List;
    public void LoadList(){   //Loads the merchandise items into an observable list

    }
}