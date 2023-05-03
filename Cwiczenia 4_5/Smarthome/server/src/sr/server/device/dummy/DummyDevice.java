package server.device.dummy;

import Smarthome.DummyDeviceI;
import com.zeroc.Ice.Current;
import server.device.Device;

import java.util.List;

public class DummyDevice extends Device implements DummyDeviceI {

    protected List<String> facts = List.of("Zaraza!", "Ależ wieje!", "Bywaj!", "Pokaż mi swoje towary!", "Dla Temerii zrobię wszystko");

    public DummyDevice(String name) {
        super(name);
    }

    @Override
    public String tellMeSomething(Current current) {
        System.out.println("DummyDevice.tellMeSomething called by " + current.id.name + ", category: " + current.id.category);
        return facts.get((int) (Math.random() * facts.size()));
    }

    @Override
    public String whatIsThis(String something, Current current) {
        System.out.println("DummyDevice.whatIsThis(" + something + ") called by " + current.id.name + ", category: " + current.id.category);
        return "This is " + something + ".";
    }
}
