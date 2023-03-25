package pl.agh.edu.ii.f1stats.model;

import java.util.List;

public class Response {

    private final String firstName;
    private final String lastName;
    private final List<Result> results;
    private final int numberOfWins;
    private final int numberOfPoles;
    private final int numberOfPodiums;

    public Response(String firstName, String lastName, List<Result> results) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.results = results;
        this.numberOfWins = results.stream().mapToInt(Result::getFinishingPosition).filter(x -> x == 1).sum();
        this.numberOfPoles = results.stream().mapToInt(Result::getGridPosition).filter(x -> x == 1).sum();
        this.numberOfPodiums = results.stream().mapToInt(Result::getFinishingPosition).filter(x -> x <= 3).map(x -> 1).sum();
    }

    public List<Result> getResults() {
        return results;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public int getNumberOfPoles() {
        return numberOfPoles;
    }

    public int getNumberOfPodiums() {
        return numberOfPodiums;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
