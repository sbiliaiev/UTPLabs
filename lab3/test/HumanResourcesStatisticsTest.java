import employee.Employee;
import employee.Manager;
import employee.Trainee;
import employee.Worker;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import payroll.PayrollEntry;

import static org.junit.jupiter.api.Assertions.*;

class HumanResourcesStatisticsTest {
    static Manager director;
    static Manager manager1;
    static Manager manager2;
    static Manager manager3;

    static Worker worker1;
    static Worker worker2;
    static Worker worker3;
    static Worker worker4;
    static Worker worker5;
    static Worker worker6;
    static Worker worker7;
    static Trainee trainee1;
    static Trainee trainee2;
    static Trainee trainee3;
    static Trainee trainee4;
    static Trainee trainee5;
    static Trainee trainee6;
    static Trainee trainee7;

    @BeforeAll
    static void beforeAll() {
        // Director -> Manager1, Manager2, Manager3, Worker6, Worker7, Trainee6, Trainee7

        director = new Manager(
            "John",
            "The Director",
            "1965-12-20",
            new BigDecimal("20000.0"),
            null,
            "2019-03-06",
            new BigDecimal("5000.0")
        );

        manager1 = new Manager(
            "Viktor",
            "The Manager 1",
            "1975-12-20",
            new BigDecimal("10000.0"),
            director,
            "2020-03-06",
            new BigDecimal("3000.0")
        );

        manager2 = new Manager(
            "Paul",
            "The Manager 2",
            "1965-12-10",
            new BigDecimal("16000.0"),
            director,
            "2020-06-06",
            new BigDecimal("4000.0")
        );

        manager3 = new Manager(
            "Carl",
            "The Manager 3",
            "1965-12-25",
            new BigDecimal("13000.0"),
            director,
            "2020-09-06",
            new BigDecimal("3500.0")
        );

        worker6 = new Worker(
            "Worker 6",
            "Artificial",
            "1965-12-20",
            new BigDecimal("2000.0"),
            director,
            "2020-03-06",
            new BigDecimal("15000.0")
        );

        worker7 = new Worker(
            "Worker 7",
            "Alexander",
            "1965-12-20",
            new BigDecimal("2000.0"),
            director,
            "2020-03-06",
            new BigDecimal("15000.0")
        );

        trainee6 = new Trainee(
            "Trainee 6",
            "Albert",
            "1965-12-20",
            new BigDecimal("28000.0"),
            director,
            "2022-10-20",
            23
        );

        trainee7 = new Trainee(
            "Trainee 7",
            "Crystal",
            "1965-12-20",
            new BigDecimal("28000.0"),
            director,
            "2022-10-20",
            23
        );

        director.addSubordinate(manager1);
        director.addSubordinate(manager2);
        director.addSubordinate(manager3);
        director.addSubordinate(worker6);
        director.addSubordinate(worker7);
        director.addSubordinate(trainee6);
        director.addSubordinate(trainee7);

        // Manager1 -> Worker1, Worker2, Worker3, Trainee1
        worker1 = new Worker(
            "Worker 1",
            "Alexander",
            "1965-12-10",
            new BigDecimal("2000.0"),
            manager1,
            "2020-03-06",
            new BigDecimal("15000.0")
        );

        worker2 = new Worker(
            "Worker 2",
            "Douglas",
            "1965-12-20",
            new BigDecimal("2000.0"),
            manager1,
            "2020-05-06",
            new BigDecimal("15000.0")
        );

        worker3 = new Worker(
            "Worker 3",
            "Douglas",
            "1965-12-20",
            new BigDecimal("2000.0"),
            manager1,
            "2020-03-06",
            new BigDecimal("15000.0")
        );

        trainee1 = new Trainee(
            "Trainee 1",
            "Albert",
            "1965-12-20",
            new BigDecimal("17000.0"),
            manager1,
            "2022-10-20",
            23
        );

        manager1.addSubordinate(worker1);
        manager1.addSubordinate(worker2);
        manager1.addSubordinate(worker3);
        manager1.addSubordinate(trainee1);

        // Manager2 -> Worker4, Trainee2, Trainee3
        worker4 = new Worker(
            "Worker 4",
            "Douglas",
            "1965-12-20",
            new BigDecimal("2000.0"),
            manager2,
            "2020-03-06",
            new BigDecimal("15000.0")
        );

        trainee2 = new Trainee(
            "Trainee 2",
            "Albert",
            "1965-12-20",
            new BigDecimal("28000.0"),
            manager2,
            "2022-10-20",
            23
        );

        trainee3 = new Trainee(
            "Trainee 3",
            "Albert",
            "1965-12-20",
            new BigDecimal("28000.0"),
            manager2,
            "2022-10-20",
            23
        );

        manager2.addSubordinate(worker4);
        manager2.addSubordinate(trainee2);
        manager2.addSubordinate(trainee3);

        // Manager3 -> Worker5, Trainee4, Trainee5
        worker5 = new Worker(
            "Worker 5",
            "Douglas",
            "1965-12-20",
            new BigDecimal("2000.0"),
            manager3,
            "2020-03-06",
            new BigDecimal("15000.0")
        );

        trainee4 = new Trainee(
            "Trainee 4",
            "Albert",
            "1965-12-20",
            new BigDecimal("28000.0"),
            manager3,
            "2022-10-20",
            23
        );

        trainee5 = new Trainee(
            "Trainee 5",
            "Albert",
            "1965-12-20",
            new BigDecimal("28000.0"),
            manager3,
            "2022-10-20",
            23
        );

        manager3.addSubordinate(worker5);
        manager3.addSubordinate(trainee4);
        manager3.addSubordinate(trainee5);

        System.out.println(director);
    }

    @Test
    void payroll() {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(worker1);
        list.add(worker2);

        List<PayrollEntry> payrolList = HumanResourcesStatistics.payroll(list);
        payrolList.forEach(p -> {
            assertEquals(p.getSalaryPlusBonus(), new BigDecimal("17000.0"));
        });
    }

    @Test
    void subordinatesPayroll() {
        List<PayrollEntry> payrolList = HumanResourcesStatistics.subordinatesPayroll(manager1);
        payrolList.forEach(p -> {
            assertEquals(p.getSalaryPlusBonus(), new BigDecimal("17000.0"));
        });
    }

    @Test
    void allSubordinatesPayroll() {
        List<PayrollEntry> payrolList = HumanResourcesStatistics.allSubordinatesPayroll(director);

        assertEquals(payrolList.size(), 17);
    }

    @Test
    void bonusTotal() {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(worker1);
        list.add(worker2);

        BigDecimal totalBonus = HumanResourcesStatistics.bonusTotal(list);

        assertEquals(totalBonus, new BigDecimal("30000.0"));
    }

    @Test
    void longestSeniority() {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(worker1);
        list.add(worker2);

        HumanResourcesStatistics.longestSeniority(list);
    }

    @Test
    void highestSalaryWithoutBonus() {
    }

    @Test
    void highestSalaryIncludingBonus() {
    }

    @Test
    void surnameBeginsWithA() {
    }

    @Test
    void earnMoreThan1000() {
    }
}