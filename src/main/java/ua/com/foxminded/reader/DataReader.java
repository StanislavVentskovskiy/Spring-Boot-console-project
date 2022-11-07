package ua.com.foxminded.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;


@Service
public class DataReader {
    private static final Logger LOG = LoggerFactory.getLogger(DataReader.class);

    public HashMap<String,String> readGeneratedData(Path pathToGeneratedData) throws IOException {
        LOG.info("Enter method readGeneratedData()");
        HashMap<String, String> readedData = new HashMap<>();
        try(Stream<String> startingDataStream = Files.lines(pathToGeneratedData)) {
            startingDataStream.forEach(line -> {
                String[] splitLine = line.split("-");
                String key = splitLine[0];
                String value = splitLine[1];
                readedData.put(key,value);
            });
        } catch (IOException e) {
            LOG.error("Error reading data from file");
            throw new IOException(e);
        }
        LOG.info("Leave method readGeneratedData()");
        return readedData;
    }
}
