package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public final class Person implements Comparable<Person> {
	private static int idCounter = 0;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	private final int id;
	private final String firstName;
	private final String surname;
	private final Date birthdate;
	
	public Person(String firstName, String surname, String birthdate) throws ParseException {
		this.firstName = firstName;
		this.surname = surname;
		this.birthdate = formatter.parse(birthdate);;
		this.id = idCounter++;
	}

	public Person(String firstName, String surname, String birthdate, int id) throws ParseException {
		this.firstName = firstName;
		this.surname = surname;
		this.birthdate = formatter.parse(birthdate);
		this.id = id;
	}

	public Person(String firstName, String surname, Date birthdate, int id) throws ParseException {
		this.firstName = firstName;
		this.surname = surname;
		this.birthdate = birthdate;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person person = (Person) o;
		return id == person.id && firstName.equals(person.firstName) && surname.equals(person.surname) && birthdate.equals(person.birthdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(formatter, id, firstName, surname, birthdate);
	}

	@Override
	public int compareTo(Person otherPerson) {
		int res = surname.compareTo(otherPerson.getSurname());
		if (res == 0) {
			res = firstName.compareTo(otherPerson.getFirstName());
		}
		if (res == 0) {
			res = birthdate.compareTo(otherPerson.getBirthdate());
		}
		return res;
	}

	@Override
	public String toString() {
		return "Person{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", surname='" + surname + '\'' +
			", birthdate=" + birthdate +
			"}\n";
	}
}