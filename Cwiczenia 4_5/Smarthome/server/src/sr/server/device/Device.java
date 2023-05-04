package server.device;

import Smarthome.DeviceI;
import Smarthome.SmarthomeException;
import com.zeroc.Ice.Current;

public class Device implements DeviceI {

    protected final String name;
    protected boolean state;

    public Device(String name) {
        this.name = name;
    }

    @Override
    public boolean getState(Current current) {
        System.out.println("Device " + name + " is " + (state ? "on" : "off, current id: " + current.id.name + ", category: " + current.id.category));
        return state;
    }

    @Override
    public boolean turnOn(Current current) throws SmarthomeException {
        System.out.println("Device " + name + " turned on, current id: " + current.id.name + ", category: " + current.id.category);
        if (state) {
            throw new SmarthomeException("Device is already on!");
        }
        state = true;
        return true;
    }

    @Override
    public boolean turnOff(Current current) throws SmarthomeException {
        System.out.println("Device " + name + " turned off, current id: " + current.id.name + ", category: " + current.id.category);
        if (!state) {
            throw new SmarthomeException("Device is already off!");
        }
        state = false;
        return true;
    }
}
