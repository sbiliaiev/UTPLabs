package parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class BasicParser {

    public static String findFileByNameSeqStream(String folderPath, String fileName) {
        try (Stream<Path> stream = Files.find(
            Path.of(folderPath),
            Integer.MAX_VALUE,
            (path, attr) ->
                attr.isRegularFile() && path.toString().endsWith(".txt"))) {
            return stream
                .sequential()
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(path -> path.equalsIgnoreCase(fileName))
                .findFirst()
                .orElse("Not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findFileByNameParallelStream(String folderPath, String fileName) {
        try (Stream<Path> stream = Files.find(
            Path.of(folderPath),
            Integer.MAX_VALUE,
            (path, attr) ->
                attr.isRegularFile() && path.toString().endsWith(".txt"))) {
            return stream
                .parallel()
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(path -> path.equalsIgnoreCase(fileName))
                .findFirst()
                .orElse("Not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findFileByContentSeqStream(String filePath, String fileContent) {
        try (Stream<String> stream = Files.lines(Path.of(filePath))) {
            return stream
                .sequential()
                .filter(line -> line.equalsIgnoreCase(fileContent))
                .findFirst()
                .orElse("Not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findFileByContentParaleltream(String filePath, String fileContent) {
        try (Stream<String> stream = Files.lines(Path.of(filePath))) {
            return stream
                .parallel()
                .filter(line -> line.equalsIgnoreCase(fileContent))
                .findFirst()
                .orElse("Not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findFileByName(String filePath, String fileName) {
        File[] files = new File(filePath).listFiles((fileDir, name) -> name.startsWith(fileName));
        if (files != null) {
            // TODO
            // Files.walk()
            for (File file : files) {
                if (file.isFile()) {
                    return file.getName();
                }
            }
        }
        return "Not found";
    }

    public static String findFileByContent(String filePath, String fileContent) throws FileNotFoundException {
        File[] files = new File(filePath).listFiles((fileDir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                String line = null;
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    if (line.contains(fileContent)) {
                        return file.getName();
                    }
                }
            }
        }
        return "Not found";
    }
}
