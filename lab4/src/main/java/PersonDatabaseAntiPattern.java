import comparators.BirthdateComparator;
import comparators.FirstNameComparator;
import entities.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PersonDatabaseAntiPattern {

	private final List<Person> sortedByFirstName;
	private final List<Person> sortedBySurnameFirstNameAndBirthdate;
	private final List<Person> sortedByBirthdate;
	private final Map<Date, List<Person>> bornOnDay;

	public PersonDatabaseAntiPattern(List<Person> people) { //
		List<Person> list = new ArrayList<>(people);

		list.sort(new FirstNameComparator());
		sortedByFirstName = new ArrayList<>(list);

		Collections.sort(list);
		sortedBySurnameFirstNameAndBirthdate = new ArrayList<>(list);

		list.sort(new BirthdateComparator());
		sortedByBirthdate = new ArrayList<>(list);

		bornOnDay = people.stream().collect(Collectors.groupingBy(Person::getBirthdate));
	}

	public List<Person> sortedByFirstName() { //
		// external rule for ordering (based on Comparator --- FirstNameComparator)
		return sortedByFirstName;
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() { //
		// natural order (Comparable)
		return sortedBySurnameFirstNameAndBirthdate;
	}

	
	public List<Person> sortedByBirthdate() { //
		// external rule for ordering (based on Comparator --- BirthdateComparator)
		return sortedByBirthdate;
	}
	
	public List<Person> bornOnDay(Date date) { //
		return bornOnDay.get(date);
	}
}