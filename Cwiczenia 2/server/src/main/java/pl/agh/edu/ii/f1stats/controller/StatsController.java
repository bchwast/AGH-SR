package pl.agh.edu.ii.f1stats.controller;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pl.agh.edu.ii.f1stats.exception.UnauthorizedException;
import pl.agh.edu.ii.f1stats.model.ErrorResponse;
import pl.agh.edu.ii.f1stats.model.Response;
import pl.agh.edu.ii.f1stats.service.StatsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/")
    public String rootResponse() {
        return "Hello world";
    }

    @GetMapping("/results/{driver}/{fromYear}/{toYear}")
    public ResponseEntity<Object> sampleResponse(@PathVariable String driver, @PathVariable int fromYear, @PathVariable int toYear,
                                                   @RequestHeader("Authorization") String token) {
        try {
            Response response = statsService.getStats(driver, fromYear, toYear, token);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(401).body(new ErrorResponse("Unauthorized", 401, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new ErrorResponse("Bad request", 400, e.getMessage()));
        } catch (JSONException e) {
            if (e.getMessage().equals("Value ERRRRRRRRRRRRRRRRR of type java.lang.String cannot be converted to JSONObject")) {
                return ResponseEntity.status(503).body(new ErrorResponse("Service Unavailable", 503, "External server error"));
            }
            return ResponseEntity.status(500).body(new ErrorResponse("Internal server error", 500, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorResponse("Internal server error", 500, e.getMessage()));
        }
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public ResponseEntity<ErrorResponse> handleNoToken(MissingRequestHeaderException e) {
        return ResponseEntity.status(401).body(new ErrorResponse("Unauthorized", 401, "Missing token"));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleWrongType(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(400).body(new ErrorResponse("Bad request", 400, "Wrong type of argument"));
    }

}
