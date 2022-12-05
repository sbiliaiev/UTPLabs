package parsers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarParser {
    public static String findFileByName(String filePath, String fileName) {
        File[] files = new File(filePath).listFiles((fileDir, name) -> name.startsWith(fileName));
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    return file.getName();
                }
            }
        }
        return "Not found";
    }

    public static List<ZipEntry> findFileByContent(String filePath, String fileContent) throws IOException {
        ZipFile zipFile = new ZipFile(filePath);
        return zipFile
            .stream()
            .parallel()
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
