package server.device.dummy;

import Smarthome.PrzyczlapnikI;
import com.zeroc.Ice.Current;

public class Przyczlapnik extends DummyDevice implements PrzyczlapnikI {
    public Przyczlapnik(String name) {
        super(name);
    }

    @Override
    public String getCzlapCzlap(Current current) {
        System.out.println("Przyczlapnik.getCzlapCzlap called by " + current.id.name + ", category: " + current.id.category);
        return "Czlap czlap czlap!";
    }
}
