package server.device.dummy;

import Smarthome.BulbulatorI;
import com.zeroc.Ice.Current;

public class Bulbulator extends DummyDevice implements BulbulatorI {
    public Bulbulator(String name) {
        super(name);
    }

    @Override
    public String getBulbul(Current current) {
        System.out.println("Bulbulator.getBulbul called by " + current.id.name + ", category: " + current.id.category);
        return "Bul bul bul!";
    }
}
