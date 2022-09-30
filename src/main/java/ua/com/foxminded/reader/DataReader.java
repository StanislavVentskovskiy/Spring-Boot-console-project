package ua.com.foxminded.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;

public class DataReader {
    public HashMap<String,String> readGeneratedData(Path pathToGeneratedData) throws IOException {
        HashMap<String, String> readedData = new HashMap<>();
        try(Stream<String> startingDataStream = Files.lines(pathToGeneratedData)) {
            startingDataStream.forEach(line -> {
                String[] splitLine = line.split("-");
                String key = splitLine[0];
                String value = splitLine[1];
                readedData.put(key,value);
            });
        } catch (IOException e) {
            throw new IOException(e);
        }

        return readedData;
    }
}
