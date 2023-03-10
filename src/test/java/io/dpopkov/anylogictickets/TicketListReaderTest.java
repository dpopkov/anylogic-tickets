package io.dpopkov.anylogictickets;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketListReaderTest {

    @Test
    void canReadTicketList() throws IOException {
        try (InputStream in = TicketListReaderTest.class.getClassLoader().getResourceAsStream("ticket-list.json")) {
            final TicketListReader reader = new TicketListReader();
            final TicketList list = reader.read(in);
            assertEquals(2, list.getTickets().size());

            final Ticket ticket1 = list.getTickets().get(0);
            assertEquals("VVO", ticket1.getOrigin());
            assertEquals("Владивосток", ticket1.getOriginName());
            assertEquals("TLV", ticket1.getDestination());
            assertEquals("Тель-Авив", ticket1.getDestinationName());
            assertEquals(LocalDate.parse("2018-05-12"), ticket1.getDepartureDate());
            assertEquals(LocalTime.parse("16:20"), ticket1.getDepartureTime());
            assertEquals(LocalDate.parse("2018-05-12"), ticket1.getArrivalDate());
            assertEquals(LocalTime.parse("22:10"), ticket1.getArrivalTime());
            assertEquals(3, ticket1.getStops());
            assertEquals(12400, ticket1.getPrice());

            final Ticket ticket2 = list.getTickets().get(1);
            assertEquals(LocalDate.parse("2018-05-12"), ticket2.getDepartureDate());
            assertEquals(LocalTime.parse("17:20"), ticket2.getDepartureTime());
            assertEquals(LocalDate.parse("2018-05-12"), ticket2.getArrivalDate());
            assertEquals(LocalTime.parse("23:50"), ticket2.getArrivalTime());
            assertEquals(1, ticket2.getStops());
            assertEquals(13100, ticket2.getPrice());
        }
    }
}
