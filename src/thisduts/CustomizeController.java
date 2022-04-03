package thisduts;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Arrays;

import static thisduts.PizzaMaker.createPizza;

/**
 * Controller for the CurrentOrderView.fxml file
 * @author Henry Lin, Andy Li
 */
public class CustomizeController {
    @FXML private ComboBox pizzaSize;
    @FXML private ImageView pizzaImage;
    @FXML private ListView additionalToppings, selectedToppings;
    @FXML private Label name;
    @FXML private TextField price;

    private MenuController mainController;
    private Pizza pizza;
    public static final DecimalFormat FORMAT = new DecimalFormat( "#0.00" );

    private static final int DELUXE_TYPE = 1;
    private static final int HAWAIIAN_TYPE = 2;
    private static final int PEPPERONI_TYPE = 3;
    private static final int MAX_TOPPING = 7;
    private static final int INDEX = 1;
    /**
     * Changes the size of the pizza when a different option
     * in the combo box selected.
     *
     * @param event
     */
    @FXML
    void changeSize(ActionEvent event){
        if (pizzaSize.getValue().equals("small")){
            pizza.size = Size.Small;
        } else if (pizzaSize.getValue().equals("medium")){
            pizza.size = Size.Medium;
        } else{
            pizza.size = Size.Large;
        }
        price.setText(FORMAT.format(pizza.price()));
    }

    /**
     * Makes the window display the correct items for a
     * deluxe pizza.
     */
    private void createDeluxe() {
        Arrays.asList(Topping.values()).forEach(Topping -> {
            if (Topping == thisduts.Topping.Pepperoni || Topping == thisduts.Topping.Sausage || Topping == thisduts.Topping.Onion ||
                    Topping == thisduts.Topping.Mushroom || Topping == thisduts.Topping.GreenPepper)
                selectedToppings.getItems().add(Topping.toString(Topping));
            else
                additionalToppings.getItems().add(Topping.toString(Topping));
        });
        name.setText("Deluxe");
        pizzaImage.setImage(new Image("/thisduts/pics/deluxe.jpg"));
    }

    /**
     * Makes the window display the correct items for a
     * pepperoni pizza.
     */
    private void createPepperoni(){
        Arrays.asList(Topping.values()).forEach(Topping -> {
            if (Topping == thisduts.Topping.Pepperoni)
                selectedToppings.getItems().add(Topping.toString(Topping));
            else
                additionalToppings.getItems().add(Topping.toString(Topping));
        });
        name.setText("Pepperoni");
        pizzaImage.setImage(new Image("thisduts/pics/pepperoni.jpg"));
    }

    /**
     * Makes the window display the correct items for a
     * hawaiian pizza.
     */
    private void createHawaiian(){
        Arrays.asList(Topping.values()).forEach(Topping -> {
            if (Topping == thisduts.Topping.Pineapple || Topping == thisduts.Topping.Ham)
                selectedToppings.getItems().add(Topping.toString(Topping));
            else
                additionalToppings.getItems().add(Topping.toString(Topping));
        });
        name.setText("Hawaiian");
        pizzaImage.setImage(new Image("thisduts/pics/hawaiian.jpg"));
    }

    /**
     * Connects this controller with the main controller.
     * Also creates new pizzas to track what the user selects.
     *
     * @param controller is the main controller.
     */
    public void setMainController(MenuController controller) {
        mainController = controller;
        pizzaSize.getItems().removeAll(pizzaSize.getItems());
        pizzaSize.getItems().addAll("small", "medium", "large");
        pizzaSize.getSelectionModel().selectFirst();
        if (mainController.lastClicked == DELUXE_TYPE) {
            createDeluxe();
            pizza = createPizza("Deluxe");
            pizza.size = Size.Small;
        } else if (mainController.lastClicked == HAWAIIAN_TYPE) {
            createHawaiian();
            pizza = createPizza("Hawaiian");
            pizza.size = Size.Small;
        } else {
            createPepperoni();
            pizza = createPizza("Pepperoni");
            pizza.size = Size.Small;
        }
        price.setText(FORMAT.format(pizza.price()));
    }

    /**
     * Moves a topping from the left list view to right list view.
     * Also adds a specified topping to the created pizza.
     *
     * @param event
     */
    @FXML
    void addTopping(ActionEvent event) {
        String temp;
        if (pizza.toppings.size() == MAX_TOPPING) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("7 is the maximum number of toppings.");
            alert.setContentText("Please add the pizza to order or remove a topping.");
            alert.showAndWait();
            return;
        }

        if(!additionalToppings.getSelectionModel().isEmpty()) {
            temp = moveTopping(additionalToppings, selectedToppings);
            if (temp.equals("")){
                return;
            }
            pizza.addToppings(toTopping(temp));
            price.setText(FORMAT.format(pizza.price()));
        }else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("A topping was not selected.");
            alert.setContentText("Please select a topping.");
            alert.showAndWait();
        }
    }

    /**
     * Moves a topping from the right list view to left list view.
     * Also removes a specified topping to the created pizza.
     *
     * @param event
     */
    @FXML
    void removeTopping(ActionEvent event) {
        String temp;
        if(!selectedToppings.getSelectionModel().isEmpty()) {
            temp = moveTopping(selectedToppings, additionalToppings);
            if (temp.equals("")) {
                return;
            }
            pizza.removeToppings(toTopping(temp));
            price.setText(FORMAT.format(pizza.price()));
        }else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("A topping was not selected.");
            alert.setContentText("Please select a topping.");
            alert.showAndWait();
        }
    }

    /**
     * Changes a topping from a String to the enum form.
     *
     * @param topping is the topping in String form
     * @return the topping enum form
     */
    private Topping toTopping(String topping){
        if (topping.equals("Black Olives")){
            return Topping.BlackOlives;
        } else if (topping.equals("Green Pepper")) {
            return Topping.GreenPepper;
        } else if (topping.equals("Pineapple")) {
            return Topping.Pineapple;
        } else if (topping.equals("Ham")) {
            return Topping.Ham;
        } else if (topping.equals("Pepperoni")) {
            return Topping.Pepperoni;
        } else if (topping.equals("Sausage")) {
            return Topping.Sausage;
        } else if (topping.equals("Chicken")) {
            return Topping.Chicken;
        } else if (topping.equals("Beef")) {
            return Topping.Beef;
        } else if (topping.equals("Onion")) {
            return Topping.Onion;
        } else if (topping.equals("Cheese")) {
            return Topping.Cheese;
        } else {
            return Topping.Mushroom;
        }
    }

    /**
     * Moves the topping from current list to the target list.
     * Prevents the user from removing essential toppings.
     *
     * @param currentList is the list to remove from
     * @param targetList is the list to add to
     * @return
     */
    private String moveTopping(ListView currentList, ListView targetList) {
        String temp = currentList.getSelectionModel().getSelectedItems().toString();
        temp = temp.substring(INDEX, temp.length() - INDEX);
        if (mainController.lastClicked == DELUXE_TYPE){
            if (temp.equals("Green Pepper") || temp.equals("Pepperoni") || temp.equals("Mushroom")
                    || temp.equals("Sausage") || temp.equals("Onion")){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning!!");
                alert.setHeaderText("An essential topping cannot be removed!");
                alert.setContentText("Please select another topping.");
                alert.showAndWait();
                return "";
            }
        } else if (mainController.lastClicked == HAWAIIAN_TYPE && (temp.equals("Pineapple") || temp.equals("Ham"))) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("An essential topping cannot be removed!");
            alert.setContentText("Please select another topping.");
            alert.showAndWait();
            return "";
        } else if (mainController.lastClicked == PEPPERONI_TYPE && temp.equals("Pepperoni")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("An essential topping cannot be removed!");
            alert.setContentText("Please select another topping.");
            alert.showAndWait();
            return "";
        }
        currentList.getItems().remove(temp);
        targetList.getItems().add(temp);
        return temp;
    }

    /**
     * Adds the created pizza to the current order
     * @param event
     */
    @FXML
    void addOrder(ActionEvent event){
        mainController.order.addToOrder(pizza);
        Pizza temp = pizza;
        if (mainController.lastClicked == DELUXE_TYPE) {
            pizza = createPizza("Deluxe");
        } else if (mainController.lastClicked == HAWAIIAN_TYPE) {
            pizza = createPizza("Hawaiian");
        } else {
            pizza = createPizza("Pepperoni");
        }

        if (pizzaSize.getValue().equals("small")){
            pizza.size = Size.Small;
        } else if (pizzaSize.getValue().equals("medium")){
            pizza.size = Size.Medium;
        } else{
            pizza.size = Size.Large;
        }

        for(int i = pizza.toppings.size(); i < temp.toppings.size(); i++) {
            pizza.addToppings(temp.toppings.get(i));
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notice!!");
        alert.setHeaderText("The pizza was added to your order.");
        alert.showAndWait();
    }
}

