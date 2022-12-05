package entities.university;

import entities.person.Nationality;
import entities.person.NationalityLocaleMap;
import entities.person.Teacher;
import java.text.Collator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Department {
    private final String name;
    private final TreeSet<Teacher> employees;

    public Department(String name, TreeSet<Teacher> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public TreeSet<Teacher> getEmployees() {
        return employees;
    }

    public List<Teacher> filterByNationality(Nationality nationality) {
        return FilterUtils.filterByNationality(employees, nationality);
    }
}
