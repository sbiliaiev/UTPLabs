package entities.university;

import entities.person.Nationality;
import entities.person.Student;
import entities.person.Teacher;
import java.util.List;
import java.util.TreeSet;

public class Subject {
    private final String name;
    private final Department superviser;
    private final Teacher lecturer;
    private final TreeSet<Student> students;

    public Subject(String name, Department superviser, Teacher lecturer, TreeSet<Student> students) {
        this.name = name;
        this.superviser = superviser;
        this.lecturer = lecturer;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public Department getSuperviser() {
        return superviser;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public TreeSet<Student> getStudents() {
        return students;
    }

    public List<Student> filterByNationality(Nationality nationality) {
        return FilterUtils.filterByNationality(students, nationality);
    }
}
