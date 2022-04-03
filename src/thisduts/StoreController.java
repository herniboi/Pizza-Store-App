package thisduts;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Controller for the StoreOrdersView.fxml
 * @author Andy Li, Henry Lin
 */
public class StoreController {
    @FXML private ComboBox phoneNumber;
    @FXML private TextField orderTotal;
    @FXML private ListView orders;

    private MenuController mainController;
    public static final DecimalFormat df2 = new DecimalFormat( "#0.00" );

    /**
     * Connects this controller with the main controller.
     * Also populates the text field and list view.
     *
     * @param controller is the main controller
     */
    public void setMainController(MenuController controller) {
        mainController = controller;
        phoneNumber.getItems().removeAll(phoneNumber.getItems());
        for (int i = 0; i < mainController.allOrders.getSize(); i++) {
            phoneNumber.getItems().add(mainController.allOrders.getPNumber(i));
        }
        phoneNumber.getSelectionModel().selectFirst();
        display();
    }

    /**
     * removes an order from the list of all orders
     *
     * @param event
     */
    @FXML
    void removeOrder(ActionEvent event){
        for (int i = 0; i < mainController.allOrders.getSize(); i++) {
            if (mainController.allOrders.getPNumber(i).equals(phoneNumber.getValue())){
                mainController.allOrders.remove(i);
            }
        }
        phoneNumber.getItems().removeAll(phoneNumber.getItems());
        for (int i = 0; i < mainController.allOrders.getSize(); i++) {
            phoneNumber.getItems().add(mainController.allOrders.getPNumber(i));
        }
        phoneNumber.getSelectionModel().selectFirst();
        orders.getItems().clear();
        orderTotal.clear();
        display();
    }

    /**
     * exports a file for printing
     *
     * @param event
     */
    @FXML
    void exportFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", ".txt"),
                new FileChooser.ExtensionFilter("All Files", ".*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        mainController.allOrders.Export(targetFile);
        //write code to write to the file.
    }

    /**
     * When a phone number is selected in the combo box
     * the proper items are displaayed
     *
     * @param event
     */
    @FXML
    void selectOrder(ActionEvent event) {
        orders.getItems().clear();
        display();
    }

    /**
     * displays all items of the currently selected order
     */
    private void display() {
        for (int i = 0; i < mainController.allOrders.getSize(); i++){
            if (mainController.allOrders.getPNumber(i).equals(phoneNumber.getValue())){
                orderTotal.setText(df2.format(mainController.allOrders.getPrice(i)));
                break;
            }
        }
        for (int i = 0; i < mainController.allOrders.getSize(); i++) {
            if (mainController.allOrders.getPNumber(i).equals(phoneNumber.getValue())){
                for (int j = 0; j < mainController.allOrders.getOrderSize(i); j++) {
                    orders.getItems().add(mainController.allOrders.getPizza(i, j));
                }
            }
        }
    }
}
