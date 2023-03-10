package io.dpopkov.anylogictickets;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String origin;
    @JsonProperty("origin_name")
    private String originName;
    private String destination;
    @JsonProperty("destination_name")
    private String destinationName;
    @JsonProperty("departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate departureDate;
    @JsonProperty("departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    private LocalTime departureTime;
    @JsonProperty("arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate arrivalDate;
    @JsonProperty("arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    private LocalTime arrivalTime;
    private String carrier;
    private int stops;
    private int price;

    public LocalDateTime getDeparture() {
        return LocalDateTime.of(departureDate, departureTime);
    }

    public LocalDateTime getArrival() {
        return LocalDateTime.of(arrivalDate, arrivalTime);
    }

    public Duration getFlightDuration() {
        return Duration.between(getDeparture(), getArrival());
    }
}
