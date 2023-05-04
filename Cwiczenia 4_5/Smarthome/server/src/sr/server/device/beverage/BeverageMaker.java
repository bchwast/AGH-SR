package server.device.beverage;

import Smarthome.BeverageMakerI;
import Smarthome.WaterOverflowException;
import com.zeroc.Ice.Current;
import server.device.Device;

public class BeverageMaker extends Device implements BeverageMakerI {

    protected int waterLevel = 0;
    protected final int maxWaterLevel = 100;

    public BeverageMaker(String name) {
        super(name);
    }

    @Override
    public int getWaterLevel(Current current) {
        System.out.println("BeverageMaker.getWaterLevel called by " + current.id.name + ", category: " + current.id.category);
        return waterLevel;
    }

    @Override
    public boolean addWater(int amount, Current current) throws WaterOverflowException {
        System.out.println("BeverageMaker.addWater(" + amount + ") called by " + current.id.name + ", category: " + current.id.category);
        if (waterLevel + amount > maxWaterLevel) {
            throw new WaterOverflowException("Too much water!");
        }
        waterLevel += amount;
        return true;
    }
}
