package parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarParser {

    public static String findFileByNameSeqStream(String folderPath, String fileName) {
        try (Stream<Path> stream = Files.find(
            Path.of(folderPath),
            Integer.MAX_VALUE,
            (path, attr) ->
                attr.isRegularFile() && path.toString().endsWith(".jar"))) {
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
                attr.isRegularFile() && path.toString().endsWith(".jar"))) {
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
        try (JarFile jFile = new JarFile(filePath)) {
            return jFile
                .stream()
                .sequential()
                .filter(jarEntry -> !jarEntry.isDirectory())
                .map(JarEntry::getName)
                .filter(line -> line.equalsIgnoreCase(fileContent))
                .findFirst()
                .orElse("Not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findFileByContentParaleltream(String filePath, String fileContent) {
        try (JarFile jFile = new JarFile(filePath)) {
            return jFile
                .stream()
                .parallel()
                .filter(jarEntry -> !jarEntry.isDirectory())
                .map(JarEntry::getName)
                .filter(line -> line.equalsIgnoreCase(fileContent))
                .findFirst()
                .orElse("Not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String findFileByName(String filePath, String fileName) throws IOException {
        ZipFile jarFile = new ZipFile(filePath);
        Enumeration<? extends ZipEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            System.out.println(entry);
            if (entry.getName().startsWith(fileName)) {
                return entry.getName();
            }
        }
        return "Not found";
    }

    public static List<ZipEntry> findFileByContent(String filePath, String fileContent) throws IOException {
        ZipFile jarFile = new ZipFile(filePath);
        return jarFile
            .stream()
            .parallel()
            .filter(zip -> {
                String line = null;
                try {
                    Scanner scanner = new Scanner(jarFile.getInputStream(zip));
                    String s = scanner.findWithinHorizon(fileContent, 0);
                    if (s != null) {
                        line = s;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return line != null;
            }).collect(Collectors.toList());
    }
}
