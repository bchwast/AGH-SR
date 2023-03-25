package pl.agh.edu.ii.f1stats.model;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final String type;
    private final int status;
    private final String message;
    private final String timestamp = String.valueOf(LocalDateTime.now());

    public ErrorResponse(String type, int status, String message) {
        this.type = type;
        this.status = status;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
