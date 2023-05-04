package server.device.speaker;

import Smarthome.InvalidVolumeException;
import Smarthome.SpeakerI;
import com.zeroc.Ice.Current;
import server.device.Device;

public class Speaker extends Device implements SpeakerI {

    protected int volume;
    protected boolean isPlaying;

    public Speaker(String name) {
        super(name);
        volume = 30;
        isPlaying = false;
    }

    @Override
    public boolean play(Current current) {
        System.out.println("Speaker.play called by " + current.id.name + ", category: " + current.id.category);
        isPlaying = true;
        return true;
    }

    @Override
    public boolean stop(Current current) {
        System.out.println("Speaker.stop called by " + current.id.name + ", category: " + current.id.category);
        isPlaying = false;
        return true;
    }

    @Override
    public int getVolume(Current current) {
        System.out.println("Speaker.getVolume called by " + current.id.name + ", category: " + current.id.category);
        return volume;
    }

    @Override
    public boolean setVolume(int volume, Current current) throws InvalidVolumeException {
        System.out.println("Speaker.setVolume(" + volume + ") called by " + current.id.name + ", category: " + current.id.category);
        if (volume < 0 || volume > 100) {
            throw new InvalidVolumeException("Volume must be between 0 and 100!");
        }
        this.volume = volume;
        return true;
    }
}
