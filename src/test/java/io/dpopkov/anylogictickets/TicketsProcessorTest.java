package io.dpopkov.anylogictickets;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketsProcessorTest {

    private final LocalDate today = LocalDate.now();

    @Test
    void testAverageFlightDuration() {
        Ticket twoHours = Ticket.builder()
                .origin("A")
                .departureDate(today)
                .departureTime(LocalTime.of(1, 15))
                .destination("B")
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(3, 15))
                .build();
        Ticket fourHours = Ticket.builder()
                .origin("A")
                .departureDate(today)
                .departureTime(LocalTime.of(11, 25))
                .destination("B")
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(15, 25))
                .build();
        Ticket dummy = Ticket.builder()
                .origin("not-A")
                .destination("not-B")
                .build();
        TicketsProcessor processor = new TicketsProcessor();
        Duration average = processor.averageFlightDuration(List.of(twoHours, fourHours, dummy), "A", "B");
        assertEquals(Duration.ofHours(3), average);
    }

    @Test
    void testPercentile() {
        Ticket threeHours = Ticket.builder()
                .departureDate(today)
                .departureTime(LocalTime.of(0, 15))
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(3, 15))
                .build();
        Ticket fiveHours = Ticket.builder()
                .departureDate(today)
                .departureTime(LocalTime.of(10, 25))
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(15, 25))
                .build();
        Ticket sevenHours = Ticket.builder()
                .departureDate(today)
                .departureTime(LocalTime.of(1, 15))
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(8, 15))
                .build();
        TicketsProcessor processor = new TicketsProcessor();
        Duration duration = processor.percentileFlightDuration(List.of(threeHours, fiveHours, sevenHours), 50);
        assertEquals(Duration.ofHours(5), duration);
    }

    @Test
    void testPercentileInMinutes() {
        Ticket threeHours = Ticket.builder()
                .departureDate(today)
                .departureTime(LocalTime.of(0, 15))
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(3, 15))
                .build();
        Ticket fiveHours = Ticket.builder()
                .departureDate(today)
                .departureTime(LocalTime.of(10, 25))
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(15, 25))
                .build();
        Ticket sevenHours = Ticket.builder()
                .departureDate(today)
                .departureTime(LocalTime.of(1, 15))
                .arrivalDate(today)
                .arrivalTime(LocalTime.of(8, 15))
                .build();
        TicketsProcessor processor = new TicketsProcessor();
        long minutes = processor.percentileInMinutes(List.of(threeHours, fiveHours, sevenHours), 50);
        assertEquals(300, minutes);
    }
}
