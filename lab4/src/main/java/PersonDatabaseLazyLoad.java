import comparators.BirthdateComparator;
import comparators.FirstNameComparator;
import entities.Person;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PersonDatabaseLazyLoad {

	private final List<Person> people;
	private List<Person> sortedByFirstName;
	private List<Person> sortedBySurnameFirstNameAndBirthdate;
	private List<Person> sortedByBirthdate;
	private Map<Date, List<Person>> bornOnDay;

	public PersonDatabaseLazyLoad(List<Person> people) { //
		this.people = people;
	}

	public List<Person> sortedByFirstName() { //
		// external rule for ordering (based on Comparator --- FirstNameComparator)
		if (sortedByFirstName != null) {
			return sortedByFirstName;
		}
		sortedByFirstName = new ArrayList<>(people);
		sortedByFirstName.sort(new FirstNameComparator());
		return sortedByFirstName;
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() { //
		// natural order (Comparable)
		if (sortedBySurnameFirstNameAndBirthdate != null) {
			return sortedBySurnameFirstNameAndBirthdate;
		}
		sortedBySurnameFirstNameAndBirthdate = new ArrayList<>(people);
		Collections.sort(sortedBySurnameFirstNameAndBirthdate);
		return sortedBySurnameFirstNameAndBirthdate;
	}

	
	public List<Person> sortedByBirthdate() { //
		// external rule for ordering (based on Comparator --- BirthdateComparator)
		if (sortedByBirthdate != null) {
			return sortedByBirthdate;
		}
		sortedByBirthdate = new ArrayList<>(people);
		sortedByBirthdate.sort(new BirthdateComparator());
		return sortedByBirthdate;
	}
	
	public List<Person> bornOnDay(Date date) { //
		if (bornOnDay != null) {
			return bornOnDay.get(date);
		}
		bornOnDay = people.stream().collect(Collectors.groupingBy(Person::getBirthdate));
		return bornOnDay.get(date);
	}
}