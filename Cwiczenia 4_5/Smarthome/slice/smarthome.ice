module Smarthome {

    exception SmarthomeException {
        string message;
    };

    interface DeviceI {
        idempotent bool getState();
        bool turnOn() throws SmarthomeException;
        bool turnOff() throws SmarthomeException;
    };


    exception InvalidVolumeException extends SmarthomeException {};

    interface SpeakerI extends DeviceI {
        idempotent bool play();
        idempotent bool stop();
        idempotent int getVolume();
        idempotent bool setVolume(int volume) throws InvalidVolumeException;
    };

    enum RadioStation {
        RadioMaryja,
        RadioLosSantos,
        NightFM,
        WestCoastClassics,
        TheBlueArk,
        EnclaveRadio
    };
    exception InvalidRadioStationException extends SmarthomeException {};
    ["java:type:java.util.ArrayList<RadioStation>"]
    sequence <RadioStation> radioStations;

    interface RadioI extends SpeakerI {
        idempotent RadioStation getStation();
        idempotent bool setStation(RadioStation station) throws InvalidRadioStationException;
        idempotent radioStations getRadioStations();
    };

    struct Song {
        string artist;
        string title;
    };
    exception InvalidSongException extends SmarthomeException {};

    interface MusicPlayerI extends SpeakerI {
        idempotent Song getSong();
        idempotent bool setSong(Song song) throws InvalidSongException;
    };


    interface DummyDeviceI extends DeviceI {
        string tellMeSomething();
        string whatIsThis(string something);
    };

    interface BulbulatorI extends DummyDeviceI {
        string getBulbul();
    };

    interface PrzyczlapnikI extends DummyDeviceI {
        string getCzlapCzlap();
    };


    exception WaterOverflowException extends SmarthomeException {};
    exception WaterUnderflowException extends SmarthomeException {};

    interface BeverageMakerI extends DeviceI {
        idempotent int getWaterLevel();
        bool addWater(int amount) throws WaterOverflowException;
    };

    enum CoffeeType {
        Espresso,
        Cappuccino,
        Latte,
        Americano
    };

    struct Coffee {
        CoffeeType type;
        int strength;
    };
    exception InvalidCoffeeException extends SmarthomeException {};
    ["java:type:java.util.ArrayList<CoffeeType>"]
    sequence <CoffeeType> coffeeTypes;

    interface CoffeeMakerI extends BeverageMakerI {
        idempotent Coffee getCurrentCoffee();
        idempotent bool setCoffee(Coffee coffee) throws InvalidCoffeeException;
        idempotent Coffee makeCoffee() throws WaterUnderflowException;
        idempotent coffeeTypes getCoffeeTypes();
    };

    enum TeaType {
        Black,
        Green,
        White,
        Fruit
    };

    struct Tea {
        TeaType type;
        int strength;
    };
    exception InvalidTeaException extends SmarthomeException {};
    ["java:type:java.util.ArrayList<TeaType>"]
    sequence <TeaType> teaTypes;

    interface TeaMakerI extends BeverageMakerI {
        idempotent Tea getCurrentTea();
        idempotent bool setTea(Tea tea) throws InvalidTeaException;
        idempotent Tea makeTea() throws WaterUnderflowException;
        idempotent teaTypes getTeaTypes();
    };


    struct DeviceInfo {
        string name;
        string type;
        int server;
    };

    ["java:type:java.util.ArrayList<DeviceInfo>"]
    sequence <DeviceInfo> deviceList;

    interface DeviceManagerI {
        idempotent deviceList getDeviceList();
    };
};
