import entities.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @Test
    void parse_wrongFileName_throwsFileNotFoundException() {
        File wrongFile = new File("wong_pathname.txt");
        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            InputParser.parse(wrongFile);
        });
    }

    @Test
    void parse_badPatternFormat_throwsParseException() {
        File file = new File("/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab4/test/data/bad-parsing-data.txt");
        Exception exception = assertThrows(PatternSyntaxException.class, () -> {
            InputParser.parse(file);
        });
    }

    @Test
    void parse_emptyFile_returnsEmptyList() throws FileNotFoundException, ParseException {
        File file = new File("/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab4/test/data/empty-data.txt");
        List<Person> list = InputParser.parse(file);
        assertEquals(0, list.size());
    }

    @Test
    void parse_correctPattern_returnsPersonList() throws FileNotFoundException, ParseException {
        String[] names = {"John", "Mark", "John", "Alex", "Brad", "Johnny", "Kratos"};
        File file = new File("/Users/sbiliaiev/Documents/University/UTP/UTPLabs/lab4/test/data/person-data.txt");
        List<Person> list = InputParser.parse(file);
        assertEquals(7, list.size());
        int index = 0;
        for (Person person : list) {
            assertEquals(names[index++], person.getFirstName());
        }
    }
}