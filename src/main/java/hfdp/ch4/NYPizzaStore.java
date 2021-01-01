package hfdp.ch4;

public class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (type.equals("veggie")) {

        } else if (type.equals("clam")) {

        } else if (type.equals("pepperoni")) {

        } else
            return null;
        return null;
    }
}
