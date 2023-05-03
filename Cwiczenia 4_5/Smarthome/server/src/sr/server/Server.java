package server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;
import server.device.DeviceManager;
import server.device.beverage.CoffeeMaker;
import server.device.beverage.TeaMaker;
import server.device.dummy.Bulbulator;
import server.device.dummy.Przyczlapnik;
import server.device.speaker.MusicPlayer;
import server.device.speaker.Radio;


public class Server {

    private ObjectAdapter createAdapter1(Communicator communicator) {
        ObjectAdapter adapter = communicator.createObjectAdapter("Adapter1");

        Bulbulator bulbulatorServant = new Bulbulator("bulbulator");
        Bulbulator bulbulatorServant2 = new Bulbulator("bulbulator2");
        MusicPlayer musicPlayerServant = new MusicPlayer("musicPlayer");
        CoffeeMaker coffeeMakerServant = new CoffeeMaker("coffeMaker");

        adapter.add(bulbulatorServant, new Identity("Andrzej", "Bulbulator"));
        adapter.add(bulbulatorServant2, new Identity("Bozydar", "Bulbulator"));
        adapter.add(musicPlayerServant, new Identity("Michal", "MusicPlayer"));
        adapter.add(coffeeMakerServant, new Identity("Tobiasz", "CoffeeMaker"));

        return adapter;
    }

    private ObjectAdapter createAdapter2(Communicator communicator) {
        ObjectAdapter adapter = communicator.createObjectAdapter("Adapter2");

        Przyczlapnik przyczlapnikServant = new Przyczlapnik("przyczlapnik");
        Radio radioServant = new Radio("radio");
        TeaMaker teaMakerServant = new TeaMaker("teaMaker");

        adapter.add(przyczlapnikServant, new Identity("Bronislaw", "Przyczlapnik"));
        adapter.add(radioServant, new Identity("Jaroslaw", "Radio"));
        adapter.add(teaMakerServant, new Identity("Sebastian", "TeaMaker"));

        return adapter;
    }

    public void run(String[] args) {
        int type = Integer.parseInt(args[1].split("=")[1]);
        Communicator communicator = null;
        int status = 0;


        try {
            communicator = Util.initialize(new String[]{args[0]});

            ObjectAdapter adapter = type == 1 ? createAdapter1(communicator) : createAdapter2(communicator);

            DeviceManager deviceManagerServant = new DeviceManager();
            adapter.add(deviceManagerServant, new Identity("Donald", "deviceManager"));

            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            } catch (Exception e) {
                e.printStackTrace(System.err);
                status = 1;
            }
        }
        System.exit(status);
    }

    public static void main(String[] args) {
        Server app = new Server();
        app.run(args);
    }
}
