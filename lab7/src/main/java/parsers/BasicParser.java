package parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BasicParser {
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
