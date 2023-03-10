package io.dpopkov.anylogictickets;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Main {

    private static final String DEFAULT_INPUT_FILE_PATH = "tickets.json";
    private static final String DEFAULT_ORIGIN = "VVO";
    private static final String DEFAULT_DESTINATION = "TLV";

    public static void main(String[] args) {
        String path = DEFAULT_INPUT_FILE_PATH;
        if (args.length > 0) {
            path = args[0];
        }
        String originCode = DEFAULT_ORIGIN;
        if (args.length > 1) {
            originCode = args[1];
        }
        String destinationCode = DEFAULT_DESTINATION;
        if (args.length > 2) {
            destinationCode = args[2];
        }
        FileProcessor processor = new FileProcessor(System.out);
        try {
            processor.processFile(path, originCode, destinationCode);
        } catch (NoSuchFileException notFoundEx) {
            System.err.println("File not found: " + notFoundEx.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
