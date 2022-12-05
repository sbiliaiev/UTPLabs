import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parsers.BasicParser;
import parsers.JarParser;
import parsers.ZipParser;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    static String existingFileName;
    static String existingFileContent;
    static String unExistingFileName;
    static String unExistingFileContent;
    static String filePathBasic;
    static String filePathZip;
    static String filePathJar;
    static String notFound;

    @BeforeAll
    public static void beforeAll() {
        existingFileName = "hello_world";
        existingFileContent = "hello world";
        unExistingFileName = "hello_world_fake";
        unExistingFileContent = "hello world fake";
        filePathBasic = "/Users/sbiliaiev/Documents/University/UTP/UTPLabs/TestData";
        filePathZip = filePathBasic + "/archive.zip";
        filePathJar = filePathBasic + "/archive.jar";
        notFound = "Not found";
    }

    @Test
    void shouldFindGeneralFile() throws FileNotFoundException {
        assertEquals(existingFileName + ".txt", BasicParser.findFileByName(filePathBasic, existingFileName));
        assertEquals(existingFileName + ".txt", BasicParser.findFileByContent(filePathBasic, existingFileContent));
    }

    @Test
    void shouldNotFindGeneralFile() throws FileNotFoundException {
        assertEquals(notFound, BasicParser.findFileByName(filePathBasic + "/fake", existingFileName));
        assertEquals(notFound, BasicParser.findFileByContent(filePathBasic + "/fake", existingFileContent));
    }

    @Test
    void shouldFindZipFile() throws IOException {
        assertEquals(existingFileName + ".txt", ZipParser.findFileByName(filePathZip, existingFileName));
        assertTrue(ZipParser.findFileByContent(filePathZip, existingFileContent).size() > 0);
    }

    @Test
    void shouldNotFindZipFile() throws IOException {
        assertEquals(notFound, ZipParser.findFileByName(filePathZip, unExistingFileName));
        assertEquals(0, ZipParser.findFileByContent(filePathZip, unExistingFileContent).size());
    }

    @Test
    void shouldFindJarFile() throws IOException {
        assertEquals(existingFileName + ".txt", JarParser.findFileByName(filePathJar, existingFileName));
        assertTrue(JarParser.findFileByContent(filePathJar, existingFileContent).size() > 0);
    }

    @Test
    void shouldNotFindJarFile() throws IOException {
        assertEquals(notFound, JarParser.findFileByName(filePathJar, unExistingFileName));
        assertEquals(0, JarParser.findFileByContent(filePathJar, unExistingFileContent).size());
    }
}
