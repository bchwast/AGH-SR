package server.device.beverage;

import Smarthome.*;
import com.zeroc.Ice.Current;

import java.util.List;

import static Smarthome.CoffeeType.*;

public class CoffeeMaker extends BeverageMaker implements CoffeeMakerI {

    private Coffee coffee;

    public CoffeeMaker(String name) {
        super(name);
        coffee = new Coffee(Espresso, 5);
    }

    @Override
    public Coffee getCurrentCoffee(Current current) {
        System.out.println("CoffeeMaker.getCurrentCoffee() called by " + current.id.name + ", category: " + current.id.category);
        return coffee;
    }

    @Override
    public boolean setCoffee(Coffee coffee, Current current) throws InvalidCoffeeException {
        System.out.println("CoffeeMaker.setCoffee(" + coffee + ") called by " + current.id.name + ", category: " + current.id.category);
        if (coffee == null) {
            throw new InvalidCoffeeException("Coffee cannot be null");
        }
        if (coffee.type == null) {
            throw new InvalidCoffeeException("Coffee type cannot be null");
        }
        if (coffee.strength < 0 || coffee.strength > 10) {
            throw new InvalidCoffeeException("Coffee strength must be between 0 and 10");
        }
        this.coffee = coffee;
        return true;
    }

    @Override
    public Coffee makeCoffee(Current current) throws WaterUnderflowException {
        System.out.println("CoffeeMaker.makeCoffee() called by " + current.id.name + ", category: " + current.id.category);
        if (coffee.type == Espresso && waterLevel < 10) {
            throw new WaterUnderflowException("Not enough water for espresso");
        }
        if (waterLevel < 20) {
            throw new WaterUnderflowException("Not enough water for coffee");
        }

        waterLevel -= coffee.type == Espresso ? 10 : 20;
        return coffee;
    }

    @Override
    public List<CoffeeType> getCoffeeTypes(Current current) {
        return List.of(Espresso, Americano, Latte, Cappuccino);
    }
}
