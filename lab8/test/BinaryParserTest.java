import entities.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryParserTest {
    @Test
    void shouldGetCollectionAndWriteToBinary() throws IOException, ParseException {
        PersonDatabase database = new PersonDatabase(
            InputParser.parse(
                new File("/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab4/test/data/person-data.txt")
            )
        );

        String binaryPath = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab8/test/data/persons.data";

        BinaryParser.writeToBinary(database.sortedByFirstName(), binaryPath);

        List<Person> parsedList = BinaryParser.readFromBinary(binaryPath);

        Assertions.assertEquals(parsedList, database.sortedByFirstName());
    }

    @Test
    void shouldThrowFileNotFoundException() {
        FileNotFoundException thrown = Assertions.assertThrows(FileNotFoundException.class, () -> {
            List<Person> parsedList = BinaryParser.readFromBinary("some broken file");
        });
    }

    @Test
    void shouldThrowIOExceptionDuringParse() {
        IOException thrown = Assertions.assertThrows(IOException.class, () -> {
            List<Person> parsedList = BinaryParser.readFromBinary("/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab8/test/data/broken-data.bin");
        });
    }
}