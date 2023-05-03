package server.device.speaker;

import Smarthome.InvalidRadioStationException;
import Smarthome.RadioI;
import Smarthome.RadioStation;
import com.zeroc.Ice.Current;

import java.util.List;

import static Smarthome.RadioStation.*;

public class Radio extends Speaker implements RadioI {

    private RadioStation station;

    public Radio(String name) {
        super(name);
        station = RadioMaryja;
    }

    @Override
    public RadioStation getStation(Current current) {
        System.out.println("Radio.getStation called by " + current.id.name + ", category: " + current.id.category);
        return station;
    }

    @Override
    public void setStation(RadioStation station, Current current) throws InvalidRadioStationException {
        System.out.println("Radio.setStation(" + station + ") called by " + current.id.name + ", category: " + current.id.category);
        if (station == null) {
            throw new InvalidRadioStationException("Station cannot be null");
        }
        this.station = station;
    }

    @Override
    public List<RadioStation> getRadioStations(Current current) {
        return List.of(RadioMaryja, RadioLosSantos, NightFM, WestCoastClassics, TheBlueArk, EnclaveRadio);
    }
}
