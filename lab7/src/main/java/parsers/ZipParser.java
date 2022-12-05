package parsers;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipParser {
    private static ZipEntry entry;

    public static String findFileByName(String filePath, String fileName) throws IOException {
        ZipFile zipFile = new ZipFile(filePath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
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
        ZipFile zipFile = new ZipFile(filePath);
        return zipFile
            .stream()
            .filter(zip -> {
                String line = null;
                try {
                    Scanner scanner = new Scanner(zipFile.getInputStream(zip));
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
