import comparators.BirthdateComparator;
import comparators.FirstNameComparator;
import entities.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private final List<Person> people;

	public PersonDatabase(List<Person> people) { //
		this.people = people;
	}

	public List<Person> sortedByFirstName() { //
		// external rule for ordering (based on Comparator --- FirstNameComparator)
		List<Person> sortedList = new ArrayList<>(people);
		sortedList.sort(new FirstNameComparator());
		return sortedList;
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() { //
		// natural order (Comparable)
		List<Person> sortedList = new ArrayList<>(people);
		Collections.sort(sortedList);
		return sortedList;
	}

	
	public List<Person> sortedByBirthdate() { //
		// external rule for ordering (based on Comparator --- BirthdateComparator)
		List<Person> sortedList = new ArrayList<>(people);
		sortedList.sort(new BirthdateComparator());
		return sortedList;
	}
	
	public List<Person> bornOnDay(Date date) { //
		Map<Date, List<Person>> map = people.stream().collect(Collectors.groupingBy(Person::getBirthdate));
		return map.get(date);
	}
}