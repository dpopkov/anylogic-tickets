package io.dpopkov.anylogictickets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class FileProcessor {

    private final TicketListReader reader = new TicketListReader();
    private final TicketsProcessor processor = new TicketsProcessor();
    private final PrintStream out;

    public FileProcessor(PrintStream out) {
        this.out = out;
    }

    public void processFile(String path, String originCode, String destinationCode) throws IOException {
        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            out.println("Processing file " + path);
            TicketList ticketList = reader.read(inputStream);
            Duration average = processor.averageFlightDuration(ticketList.getTickets(), originCode, destinationCode);
            out.println("Average flight duration is " + format(average));
            long percentile90minutes = processor.percentile90FlightDurationInMinutes(ticketList.getTickets(),
                    originCode, destinationCode);
            out.println("Percentile 90 flight duration in minutes is " + percentile90minutes);
        }
    }

    private String format(Duration duration) {
        return String.format("%d days %d hours %d minutes (or %d minutes in total)",
                duration.toDays(),
                duration.toHoursPart(),
                duration.toMinutesPart(),
                duration.toMinutes());
    }
}
