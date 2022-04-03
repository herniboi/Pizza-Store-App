package thisduts;

/**
 * Hawaiian pizza class with methods specific to this type of pizza.
 * @author Henry Lin, Andy Li
 */
public class Hawaiian extends Pizza {
    /**
     * Constructor method that adds the 2 essential Hawaiian toppings.
     */
    public Hawaiian() {
        toppings.add(Topping.Pineapple);
        toppings.add(Topping.Ham);
    }

    private static final double SMALL = 10.99;
    private static final double MEDIUM = 12.99;
    private static final double LARGE = 14.99;
    private static final double TOPPING_PRICE = 1.49;
    private static final int MIN_TOPPING = 2;
    private static final int HAWAIIAN_TYPE = 2;

    /**
     * Calculates the price of the Hawaiian pizza.
     * @return the price of the pizza.
     */
    @Override
    public double price() {
        double price;
        if (size.equals(Size.Small)) {
            price = SMALL;
        } else if (size.equals(Size.Medium)) {
            price = MEDIUM;
        } else {
            price = LARGE;
        }
        return price + (toppings.size() - MIN_TOPPING) * TOPPING_PRICE;
    }

    /**
     * Adds a topping to the pizza.
     * @param topping to be added to the pizza.
     */
    @Override
    public void addToppings(Topping topping){
        toppings.add(topping);
    }

    /**
     * Removes a topping to the pizza.
     * @param topping to be added to the pizza.
     */
    @Override
    public void removeToppings(Topping topping){
        toppings.remove(topping);
    }

    /**
     * Checks the type of the pizza
     * @return the type of pizza
     */
    @Override
    public int checkType(){
        return HAWAIIAN_TYPE;
    }

    /**
     * Gets all the toppings and lists them in a String for printing
     * @return the string of the toppings.
     */
    @Override
    public String getToppings(){
        String getToppings = new String();
        for (int i = 0 ; i < toppings.size(); i++){
            getToppings = getToppings.concat(toppings.get(i).toString() + ", ");
        }
        return getToppings;
    }

    /**
     * Gets the size and turns it into a strings.
     * @return the string of the size.
     */
    @Override
    public String getSize(){
        String getSize = new String();
        getSize = getSize.concat(size.toString() + ", ");
        return getSize;
    }

    /**
     * Checks if two Hawaiian pizzas are the same.
     * @param pizza to be compared to
     * @return whether or not the two pizzas are equal.
     */
    @Override
    public boolean equals(Pizza pizza) {
        if (this.size != pizza.size){
            return false;
        } else if (pizza.checkType() != HAWAIIAN_TYPE) {
            return false;
        } else if (this.toppings.size() != pizza.toppings.size()) {
            return false;
        }
        for (int i = 0; i < pizza.toppings.size(); i++) {
            if (!this.toppings.get(i).equals(pizza.toppings.get(i))){
                return false;
            }
        }
        return true;
    }
}
