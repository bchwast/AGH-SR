package server.device.speaker;

import Smarthome.InvalidSongException;
import Smarthome.MusicPlayerI;
import Smarthome.Song;
import com.zeroc.Ice.Current;

public class MusicPlayer extends Speaker implements MusicPlayerI {

    private Song song;

    public MusicPlayer(String name) {
        super(name);
        song = new Song("Darude", "Sandstorm");
    }

    @Override
    public Song getSong(Current current) {
        System.out.println("MusicPlayer.getSong called by " + current.id.name + ", category: " + current.id.category);
        return song;
    }

    @Override
    public boolean setSong(Song song, Current current) throws InvalidSongException {
        System.out.println("MusicPlayer.setSong(" + song + ") called by " + current.id.name + ", category: " + current.id.category);
        if (song == null) {
            throw new InvalidSongException("Song cannot be null");
        }
        if (song.artist == null || song.artist.isEmpty()) {
            throw new InvalidSongException("Song artist cannot be null or empty");
        }
        if (song.title == null || song.title.isEmpty()) {
            throw new InvalidSongException("Song title cannot be null or empty");
        }
        this.song = song;
        return true;
    }
}
