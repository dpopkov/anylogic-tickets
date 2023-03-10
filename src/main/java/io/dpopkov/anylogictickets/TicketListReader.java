package io.dpopkov.anylogictickets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;

public class TicketListReader {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public TicketList read(InputStream inputStream) throws IOException {
        return mapper.readValue(inputStream, TicketList.class);
    }
}
