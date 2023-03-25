package pl.agh.edu.ii.f1stats.source;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Service
public class DataSourceService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DataSourceService.class);
    private static final String BASE_URL = "https://ergast.com/api/f1/";

    @Async
    public CompletableFuture<String> getRaceStatsFromYear(String driver, int year) throws RuntimeException {
        String query = BASE_URL + year + "/drivers/" + driver + "/results.json";
        return getStringCompletableFuture(query);
    }

    @Async
    public CompletableFuture<String> getQualifyingStatsFromYear(String driver, int year) throws RuntimeException {
        String query = BASE_URL + year + "/drivers/" + driver + "/qualifying.json";
        return getStringCompletableFuture(query);
    }

    private CompletableFuture<String> getStringCompletableFuture(String query) {
        logger.debug("Getting stats with query: " + query);
        return HttpClient.newHttpClient()
                .sendAsync(HttpRequest.newBuilder()
                        .uri(URI.create(query))
                        .timeout(Duration.ofSeconds(30))
                        .build(), HttpResponse.BodyHandlers.ofString())
                .thenApplyAsync(response -> {
                    if (response.statusCode() != 200) {
                        return "ERRRRRRRRRRRRRRRRR";
                    }
                    return response.body();
                });
    }

}
