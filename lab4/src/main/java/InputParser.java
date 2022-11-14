import entities.Person;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class InputParser {
	
	// 1. Use regular expresssions (Pattern) for validating input data
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd"
	//    SimpleDateFormat format "yyyy-MM-dd"

	private static final String regExp = "^[a-zA-Z]+\\s[a-zA-Z]+\\s\\d{4}-\\d{2}-\\d{2}$";
	private static final Pattern pattern = Pattern.compile(regExp);


	public static List<Person> parse(File file) throws FileNotFoundException, ParseException { //
		Scanner scanner = new Scanner(file);
		List<Person> list = new ArrayList<>();
		int index = 1;
		while(scanner.hasNextLine()){
			//process each line
			String line = scanner.nextLine();
			Matcher matcher = pattern.matcher(line);
			if (!matcher.matches()) {
				throw new PatternSyntaxException("The input string does not match the pattern", regExp, -1);
			}
			String[] splitStr = line.split("\\s+");
			list.add(new Person(splitStr[0], splitStr[1], splitStr[2], index++));
		}
		scanner.close();
		return list;
	}
}