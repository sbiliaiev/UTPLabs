package entities.university;

import entities.person.Nationality;
import entities.person.Student;
import java.util.List;
import java.util.TreeSet;

public class StudentGroup {
    private final String name;
    private final TreeSet<Student> students;

    public StudentGroup(String name, TreeSet<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public TreeSet<Student> getStudents() {
        return students;
    }

    public List<Student> filterByNationality(Nationality nationality) {
        return FilterUtils.filterByNationality(students, nationality);
    }
}
