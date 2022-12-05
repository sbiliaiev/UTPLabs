import data.GenerateDepartment;
import data.GenerateStudent;
import data.GenerateStudentGroup;
import data.GenerateSubject;
import data.GenerateTeacher;
import entities.person.Student;
import entities.person.Teacher;
import entities.university.Department;
import entities.university.StudentGroup;
import entities.university.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversityTest {
//
//    @BeforeAll
//    void beforeAll() {
//
//    }

    @Test
    void generateBigAmountOfData() {
        List<Department> departmentList = new ArrayList<>();
        List<Subject> subjectList = new ArrayList<>();

        // generate departments
        for (int i = 0; i < 3; i++) {
            TreeSet<Teacher> teacherTreeSet = GenerateTeacher.generateAmount(4);
            Department department = GenerateDepartment.generate(teacherTreeSet);
            departmentList.add(department);

            for (int j = 0; j < 4; j++) {
                TreeSet<Student> studentTreeSet = GenerateStudent.generateAmount(20);
                StudentGroup studentGroup = GenerateStudentGroup.generate(studentTreeSet);
                Subject subject = GenerateSubject.generate(department, teacherTreeSet.first(), studentTreeSet);
                subjectList.add(subject);
            }
        }

        assertEquals(3, departmentList.size());
        assertEquals(12, subjectList.size());
    }

    @Test
    void correctNumberOfStudentsInGroup() {
        TreeSet<Student> studentTreeSet = GenerateStudent.generateAmount(20);
        StudentGroup studentGroup = GenerateStudentGroup.generate(studentTreeSet);

        assertEquals(20, studentGroup.getStudents().size());
    }

    @Test
    void correctNumberOfTeachersInDepartment() {
        TreeSet<Teacher> teacherTreeSet = GenerateTeacher.generateAmount(20);
        Department department = GenerateDepartment.generate(teacherTreeSet);

        assertEquals(20, department.getEmployees().size());
    }

    @Test
    void correctPeopleInSubject() {
        TreeSet<Teacher> teacherTreeSet = GenerateTeacher.generateAmount(20);
        Department department = GenerateDepartment.generate(teacherTreeSet);
        TreeSet<Student> studentTreeSet = GenerateStudent.generateAmount(20);
        Subject subject = GenerateSubject.generate(department, teacherTreeSet.first(), studentTreeSet);

        assertEquals(department, subject.getSuperviser());
        assertEquals(teacherTreeSet, subject.getSuperviser().getEmployees());
        assertEquals(teacherTreeSet.first(), subject.getLecturer());
        assertEquals(studentTreeSet, subject.getStudents());
    }
}
