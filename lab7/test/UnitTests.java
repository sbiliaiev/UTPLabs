import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import parsers.BasicParser;
import parsers.ZipParser;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    @Test
    void shouldFindGeneralFile() throws FileNotFoundException {
        String filePath = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/TestData";
        String existingFileName = "hello_world";
        String existingFileContent = "hello world";

        assertEquals(existingFileName + ".txt", BasicParser.findFileByName(filePath, existingFileName));
        assertEquals(existingFileName + ".txt", BasicParser.findFileByContent(filePath, existingFileContent));
    }

    @Test
    void shouldNotFindGeneralFile() throws FileNotFoundException {
        String filePath = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/TestData/fake";
        String existingFileName = "hello_world";
        String existingFileContent = "hello world";

        assertEquals("Not found", BasicParser.findFileByName(filePath, existingFileName));
        assertEquals("Not found", BasicParser.findFileByContent(filePath, existingFileContent));
    }

    @Test
    void shouldFindZipFile() throws IOException {
        String filePath = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/TestData/archive.zip";
        String existingFileName = "hello_world";
        String existingFileContent = "hello world";

        assertEquals(existingFileName + ".txt", ZipParser.findFileByName(filePath, existingFileName));
        assertTrue(ZipParser.findFileByContent(filePath, existingFileContent).size() > 0);
    }

    @Test
    void shouldNotFindZipFile() throws IOException {
        String filePath = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/TestData/archive.zip";
        String existingFileName = "hello_world_fake";
        String existingFileContent = "hello world fake";

        assertEquals("Not found", ZipParser.findFileByName(filePath, existingFileName));
        assertEquals(0, ZipParser.findFileByContent(filePath, existingFileContent).size());
    }

    @Test
    void shouldFindJarFile() throws IOException {
        String filePath = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/TestData/archive.jar";
        String existingFileName = "hello_world";
        String existingFileContent = "hello world";

        assertEquals(existingFileName + ".txt", ZipParser.findFileByName(filePath, existingFileName));
        assertTrue(ZipParser.findFileByContent(filePath, existingFileContent).size() > 0);
    }

    @Test
    void shouldNotFindJarFile() throws IOException {
        String filePath = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/TestData/archive.zip";
        String existingFileName = "hello_world_fake";
        String existingFileContent = "hello world fake";

        assertEquals("Not found", ZipParser.findFileByName(filePath, existingFileName));
        assertEquals(0, ZipParser.findFileByContent(filePath, existingFileContent).size());
    }
}
