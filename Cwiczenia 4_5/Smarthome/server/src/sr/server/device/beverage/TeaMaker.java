package server.device.beverage;

import Smarthome.*;
import com.zeroc.Ice.Current;

import java.util.List;

import static Smarthome.TeaType.*;

public class TeaMaker extends BeverageMaker implements TeaMakerI {

    private Tea tea;

    public TeaMaker(String name) {
        super(name);
        tea = new Tea(Black, 5);
    }

    @Override
    public Tea getCurrentTea(Current current) {
        System.out.println("TeaMaker.getCurrentTea() called by " + current.id.name + ", category: " + current.id.category);
        return tea;
    }

    @Override
    public boolean setTea(Tea tea, Current current) throws InvalidTeaException {
        System.out.println("TeaMaker.setTea(" + tea + ") called by " + current.id.name + ", category: " + current.id.category);
        if (tea == null) {
            throw new InvalidTeaException("Tea cannot be null");
        }
        if (tea.type == null) {
            throw new InvalidTeaException("Tea type cannot be null");
        }
        if (tea.strength < 0 || tea.strength > 10) {
            throw new InvalidTeaException("Tea strength must be between 0 and 10");
        }
        this.tea = tea;
        return true;
    }

    @Override
    public Tea makeTea(Current current) throws WaterUnderflowException {
        System.out.println("TeaMaker.makeTea() called by " + current.id.name + ", category: " + current.id.category);
        if (waterLevel < 20) {
            throw new WaterUnderflowException("Not enough water for tea");
        }

        waterLevel -= 20;
        return tea;
    }

    @Override
    public List<TeaType> getTeaTypes(Current current) {
        return List.of(Black, Green, White, Fruit);
    }
}
