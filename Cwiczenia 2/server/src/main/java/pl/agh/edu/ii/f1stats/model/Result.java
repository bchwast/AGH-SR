package pl.agh.edu.ii.f1stats.model;

import java.io.Serializable;

public class Result implements Serializable {

    private final String constructorName;
    private final String raceName;
    private final String trackName   ;
    private final String qualifyingTime;
    private final int gridPosition;
    private final int finishingPosition;
    private final int positionsGained;
    private final String season;
    private final int raceNumber;
    private final String raceDate;

    public Result(String constructorName, String raceName, String trackName, String qualifyingTime,
                  int gridPosition, int finishingPosition, String season, int raceNumber, String raceDate) {
        this.constructorName = constructorName;
        this.raceName = raceName;
        this.trackName = trackName;
        this.qualifyingTime = qualifyingTime;
        this.gridPosition = gridPosition;
        this.finishingPosition = finishingPosition;
        this.positionsGained = gridPosition - finishingPosition;
        this.season = season;
        this.raceNumber = raceNumber;
        this.raceDate = raceDate;
    }

    public String getConstructorName() {
        return constructorName;
    }

    public String getRaceName() {
        return raceName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getQualifyingTime() {
        return qualifyingTime;
    }

    public int getGridPosition() {
        return gridPosition;
    }

    public int getFinishingPosition() {
        return finishingPosition;
    }

    public int getPositionsGained() {
        return positionsGained;
    }

    public String getSeason() {
        return season;
    }

    public int getRaceNumber() {
        return raceNumber;
    }

    public String getRaceDate() {
        return raceDate;
    }
}
