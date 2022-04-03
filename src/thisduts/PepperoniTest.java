package thisduts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PepperoniTest {

    //@org.junit.jupiter.api.Test
    @Test
    void price() {
        PizzaMaker pizzaMaker = new PizzaMaker();
        Pizza pizza = pizzaMaker.createPizza("Pepperoni");
        pizza.size = Size.Small;

        //Test Case 1: Default Pepperoni pizza of small size returns 8.99
        Assertions.assertEquals(pizza.price(), 8.99);

        //Test Case 1b: Pepperoni pizza with 2 toppings and small size returns 8.99 + 1.49
        pizza.addToppings(Topping.Mushroom);
        Assertions.assertEquals(pizza.price(), 8.99 + 1.49);

        //Test Case 1c: Pepperoni pizza with 3 toppings and small size returns 8.99 + 2 * 1.49
        pizza.addToppings(Topping.Cheese);
        Assertions.assertEquals(pizza.price(), 8.99 + 2 * 1.49);

        //Test Case 1d: Pepperoni pizza with 4 toppings and small size returns 8.99 + 3 * 1.49
        pizza.addToppings(Topping.Chicken);
        Assertions.assertEquals(pizza.price(), 8.99 + 3 * 1.49);

        //Test Case 1e: Pepperoni pizza with 5 toppings and small size returns 8.99 + 4 * 1.49
        pizza.addToppings(Topping.Onion);
        Assertions.assertEquals(pizza.price(), 8.99 + 4 * 1.49);

        //Test Case 1f: Pepperoni pizza with 6 toppings and small size returns 8.99 + 5 * 1.49
        pizza.addToppings(Topping.Sausage);
        Assertions.assertEquals(pizza.price(), 8.99 + 5 * 1.49);

        //Test Case 1g: Pepperoni pizza with 7 toppings and small size returns 8.99 + 6 * 1.49
        pizza.addToppings(Topping.Beef);
        Assertions.assertEquals(pizza.price(), 8.99 + 6 * 1.49);

        //Test Case 2: Default Pepperoni pizza of medium size returns 10.99
        pizza = pizzaMaker.createPizza("Pepperoni");
        pizza.size = Size.Medium;
        Assertions.assertEquals(pizza.price(), 10.99);

        //Test Case 2b: Pepperoni pizza with 2 toppings and medium size returns 10.99 + 1.49
        pizza.addToppings(Topping.Mushroom);
        Assertions.assertEquals(pizza.price(), 10.99 + 1.49);

        //Test Case 2c: Pepperoni pizza with 3 toppings and medium size returns 10.99 + 2 * 1.49
        pizza.addToppings(Topping.Cheese);
        Assertions.assertEquals(pizza.price(), 10.99 + 2 * 1.49);

        //Test Case 2d: Pepperoni pizza with 4 toppings and medium size returns 10.99 + 3 * 1.49
        pizza.addToppings(Topping.Chicken);
        Assertions.assertEquals(pizza.price(), 10.99 + 3 * 1.49);

        //Test Case 2e: Pepperoni pizza with 5 toppings and medium size returns 10.99 + 4 * 1.49
        pizza.addToppings(Topping.Onion);
        Assertions.assertEquals(pizza.price(), 10.99 + 4 * 1.49);

        //Test Case 2f: Pepperoni pizza with 6 toppings and medium size returns 10.99 + 5 * 1.49
        pizza.addToppings(Topping.Sausage);
        Assertions.assertEquals(pizza.price(), 10.99 + 5 * 1.49);

        //Test Case 2g: Pepperoni pizza with 7 toppings and medium size returns 10.99 + 6 * 1.49
        pizza.addToppings(Topping.Beef);
        Assertions.assertEquals(pizza.price(), 10.99 + 6 * 1.49);

        //Test Case 3: Default Pepperoni pizza of large size returns 12.99
        pizza = pizzaMaker.createPizza("Pepperoni");
        pizza.size = Size.Large;
        Assertions.assertEquals(pizza.price(), 12.99);

        //Test Case 3b: Pepperoni pizza with 2 toppings and large size returns 12.99 + 1.49
        pizza.addToppings(Topping.Mushroom);
        Assertions.assertEquals(pizza.price(), 12.99 + 1.49);

        //Test Case 3c: Pepperoni pizza with 3 toppings and large size returns 12.99 + 2 * 1.49
        pizza.addToppings(Topping.Cheese);
        Assertions.assertEquals(pizza.price(), 12.99 + 2 * 1.49);

        //Test Case 3d: Pepperoni pizza with 4 toppings and large size returns 12.99 + 3 * 1.49
        pizza.addToppings(Topping.Chicken);
        Assertions.assertEquals(pizza.price(), 12.99 + 3 * 1.49);

        //Test Case 3e: Pepperoni pizza with 5 toppings and large size returns 12.99 + 4 * 1.49
        pizza.addToppings(Topping.Onion);
        Assertions.assertEquals(pizza.price(), 12.99 + 4 * 1.49);

        //Test Case 3f: Pepperoni pizza with 6 toppings and large size returns 12.99 + 5 * 1.49
        pizza.addToppings(Topping.Sausage);
        Assertions.assertEquals(pizza.price(), 12.99 + 5 * 1.49);

        //Test Case 3g: Pepperoni pizza with 7 toppings and large size returns 12.99 + 6 * 1.49
        pizza.addToppings(Topping.Beef);
        Assertions.assertEquals(pizza.price(), 12.99 + 6 * 1.49);
    }
}