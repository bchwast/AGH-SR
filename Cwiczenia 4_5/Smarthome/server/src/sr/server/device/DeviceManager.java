package server.device;

import Smarthome.DeviceInfo;
import Smarthome.DeviceManagerI;
import com.zeroc.Ice.Current;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager implements DeviceManagerI {

    private final List<DeviceInfo> devices = new ArrayList<>();

    public DeviceManager() {
        devices.add(new DeviceInfo("Andrzej", "Bulbulator", 1));
        devices.add(new DeviceInfo("Bozydar", "Bulbulator", 1));
        devices.add(new DeviceInfo("Michal", "MusicPlayer", 1));
        devices.add(new DeviceInfo("Tobiasz", "CoffeeMaker", 1));
        devices.add(new DeviceInfo("Bronislaw", "Przyczlapnik", 2));
        devices.add(new DeviceInfo("Jaroslaw", "Radio", 2));
        devices.add(new DeviceInfo("Sebastian", "TeaMaker", 2));
    }

    @Override
    public List<DeviceInfo> getDeviceList(Current current) {
        System.out.println("DeviceManager.getDeviceList called by " + current.id.name + ", category: " + current.id.category);
        return devices;
    }
}
