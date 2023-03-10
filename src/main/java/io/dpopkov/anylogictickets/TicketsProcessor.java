package io.dpopkov.anylogictickets;

import org.apache.commons.math.stat.descriptive.rank.Percentile;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketsProcessor {

    public Duration averageFlightDuration(List<Ticket> tickets, String originCode, String destinationCode) {
        if (tickets == null || tickets.size() == 0) {
            return Duration.ZERO;
        }
        List<Ticket> filtered = tickets.stream()
                .filter(t -> t.getOrigin().equals(originCode) && t.getDestination().equals(destinationCode))
                .collect(Collectors.toList());
        Duration totalDuration = filtered.stream()
                .map(Ticket::getFlightDuration)
                .reduce(Duration.ZERO, Duration::plus);
        return totalDuration.dividedBy(filtered.size());
    }

    public Duration percentile90FlightDuration(List<Ticket> tickets, String originCode, String destinationCode) {
        List<Ticket> filtered = tickets.stream()
                .filter(t -> t.getOrigin().equals(originCode) && t.getDestination().equals(destinationCode))
                .collect(Collectors.toList());
        return percentileFlightDuration(filtered, 90);
    }

    public long percentile90FlightDurationInMinutes(List<Ticket> tickets, String originCode, String destinationCode) {
        List<Ticket> filtered = tickets.stream()
                .filter(t -> t.getOrigin().equals(originCode) && t.getDestination().equals(destinationCode))
                .collect(Collectors.toList());
        return percentileInMinutes(filtered, 90);
    }

    Duration percentileFlightDuration(List<Ticket> tickets, double quantileValue) {
        List<Ticket> sorted = tickets.stream()
                .sorted(Comparator.comparing(Ticket::getFlightDuration))
                .collect(Collectors.toList());
        int index = (int) Math.ceil(quantileValue / 100.0  * sorted.size());
        return sorted.get(index - 1).getFlightDuration();
    }

    long percentileInMinutes(List<Ticket> tickets, double quantileValue) {
        Percentile percentile = new Percentile(quantileValue);
        final double[] minutes = tickets.stream()
                .sorted(Comparator.comparing(Ticket::getFlightDuration))
                .mapToDouble(t -> (double) t.getFlightDuration().toMinutes())
                .toArray();
        return (long) percentile.evaluate(minutes);
    }
}
