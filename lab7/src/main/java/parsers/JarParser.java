package parsers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarParser {
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
