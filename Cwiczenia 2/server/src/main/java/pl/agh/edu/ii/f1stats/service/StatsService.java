package pl.agh.edu.ii.f1stats.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.agh.edu.ii.f1stats.exception.UnauthorizedException;
import pl.agh.edu.ii.f1stats.model.Response;
import pl.agh.edu.ii.f1stats.model.Result;
import pl.agh.edu.ii.f1stats.source.DataSourceService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StatsService {

    private static final Logger logger = LoggerFactory.getLogger(StatsService.class);
    private final DataSourceService dataSourceService;

    public StatsService(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }
    public Response getStats(String driver, int fromYear, int toYear, String token) throws RuntimeException, JSONException, ExecutionException, InterruptedException {
        validateToken(token);
        validateRequest(driver, fromYear, toYear);
        List<Result> results = new ArrayList<>();
        String firstName = null;
        String lastName = null;
        for (int year = fromYear; year <= toYear; year++) {
            JSONArray raceResponse = new JSONObject(dataSourceService.getRaceStatsFromYear(driver, year).get()).getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
            JSONArray qualifyingResponse = new JSONObject(dataSourceService.getQualifyingStatsFromYear(driver, year).get()).getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
            int i = 0;
            int j = 0;
            while (i < raceResponse.length()) {
                JSONObject race = raceResponse.getJSONObject(i);
                JSONObject qualifying = null;
                if (j < qualifyingResponse.length()) qualifying = qualifyingResponse.getJSONObject(j);

                String qualifyingTime = "";
                if (qualifying != null && qualifying.getInt("round") == race.getInt("round")) {
                    JSONObject qualifyingResult = qualifying.getJSONArray("QualifyingResults").getJSONObject(0);
                    qualifyingTime = qualifyingResult.has("Q3") ? qualifyingResult.getString("Q3") : (qualifyingResult.has("Q2") ? qualifyingResult.getString("Q2") : qualifyingResult.getString("Q1"));
                    j++;
                }
                JSONObject raceResult = race.getJSONArray("Results").getJSONObject(0);
                if (firstName == null|| lastName == null) {
                    firstName = raceResult.getJSONObject("Driver").getString("givenName");
                    lastName = raceResult.getJSONObject("Driver").getString("familyName");
                }
                results.add(new Result(
                        raceResult.getJSONObject("Constructor").getString("name"),
                        race.getString("raceName"),
                        race.getJSONObject("Circuit").getString("circuitName"),
                        qualifyingTime,
                        raceResult.getInt("grid"),
                        raceResult.getInt("position"),
                        race.getString("season"),
                        race.getInt("round"),
                        race.getString("date"))
                );
                i++;
            }
        }
        if (results.isEmpty()) {
            throw new IllegalArgumentException("No results found for given parameters, wrong driver name or driver did not race in selected years");
        }
        logger.debug("Results obtained successfully for driver: {} from year: {} to year: {}", driver, fromYear, toYear);
        return new Response(firstName, lastName, results);
    }

    private void validateToken(String token) throws UnauthorizedException {
        if (!token.equals("F1_ENJOYER")) {
            throw new UnauthorizedException("Invalid token");
        }
    }

    private void validateRequest(String driver, int fromYear, int toYear) {
        if (fromYear > toYear) {
            throw new IllegalArgumentException("From year cannot be greater than to year");
        }
        if (fromYear < 1950 || toYear > 2023) {
            throw new IllegalArgumentException("Years must be between 1950 and 2023");
        }
    }
}
