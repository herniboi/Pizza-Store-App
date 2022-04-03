package thisduts;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

/**
 * Controller for the CurrentOrderView.fxml file
 * @author Henry Lin, Andy Li
 */
public class CurrOrderController {
    @FXML
    private TextField phoneNumber, salesTax, orderTotal, subTotal;
    @FXML
    private ListView pizzaList;

    public static final DecimalFormat FORMAT = new DecimalFormat( "#0.00" );
    private MenuController mainController;
    private static final int INDEX = 1;
    private static final int EMPTY = 0;

    /**
     * Connects with the main controller, and allows access to protected variables in the main.
     * Populates all the text fields and list views.
     *
     * @param controller is the main controller
     */
    public void setMainController(MenuController controller) {
        mainController = controller;
        phoneNumber.setText(mainController.order.getPNumber());
        subTotal.setText(FORMAT.format(mainController.order.getSubTotal()));
        salesTax.setText(FORMAT.format(mainController.order.getSalesTax()));
        orderTotal.setText(FORMAT.format(mainController.order.getTotal()));
        for (int i = 0; i < mainController.order.getSize(); i++) {
            pizzaList.getItems().add(mainController.order.getPizza(i));
        }
    }

    /**
     * Places the order of all pizzas added.
     * Displays an alert if the user has not added
     * any pizzas to the order.
     *
     * @param event
     */
    @FXML
    void placeOrder(ActionEvent event) {
        if (mainController.order.getSize() == EMPTY){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("User has not ordered a Pizza.");
            alert.setContentText("Please order a pizza!");
            alert.showAndWait();
            return;
        }
        mainController.allOrders.add(mainController.order);
        mainController.order = new Order();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notice!!");
        alert.setHeaderText("Your order has been placed!");
        alert.showAndWait();
        phoneNumber.clear();
        pizzaList.getItems().clear();
        subTotal.clear();
        salesTax.clear();
        orderTotal.clear();
    }

    /**
     * Removes the pizza selected in the list view from the current order.
     *
     * @param event
     */
    @FXML
    void removePizza(ActionEvent event) {
        String temp = pizzaList.getSelectionModel().getSelectedItems().toString();
        temp = temp.substring(INDEX, temp.length() - INDEX);
        pizzaList.getItems().remove(temp);
        mainController.order.removePizza(temp);
        subTotal.setText(FORMAT.format(mainController.order.getSubTotal()));
        salesTax.setText(FORMAT.format(mainController.order.getSalesTax()));
        orderTotal.setText(FORMAT.format(mainController.order.getTotal()));
    }
}
