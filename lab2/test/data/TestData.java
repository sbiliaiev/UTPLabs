package data;

import employee.Manager;
import employee.Trainee;
import employee.Worker;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TestData {
    private static int employeeCounter = 0;

    /**
     * Create a HR structure which resembles the below one:
     *
     * Director (an instance of Manager class (Director does not have a manager)
     * |- Manager01
     *      |- Worker
     *      |- Worker
     *      |- Trainee
     *      |- ...
     * |- Manager02
     *      |- ...
     * |- ...
     * |- Worker
     * |- Worker
     * |- Trainee
     */
    public static Manager generateTestData() {
        final Manager director = generateDirector();

        final Manager manager1 = generateManager(director);
        final Manager manager2 = generateManager(director);

        final Worker worker1 = generateWorker(manager1);
        final Worker worker2 = generateWorker(manager1);
        final Trainee trainee1 = generateTrainee(manager1);

        final Worker worker3 = generateWorker(director);
        final Worker worker4 = generateWorker(director);
        final Trainee trainee2 = generateTrainee(director);

        director.addSubordinate(manager1);
        director.addSubordinate(manager2);
        manager1.addSubordinate(worker1);
        manager1.addSubordinate(worker2);
        manager1.addSubordinate(trainee1);
        director.addSubordinate(worker3);
        director.addSubordinate(worker4);
        director.addSubordinate(trainee2);

        return director;
    }

//    public static void main(String[] args) {
//        final Manager director = generateTestData();
//
//        System.out.println(director.toString());
//
//        director.getAllSubordinates().forEach(s -> {
//            System.out.println(s.toString());
//        });
//    }

    public static Manager generateDirector() {
        return generateManager(null);
    }

    public static Manager generateManager(Manager manager) {
        return new Manager(
            generateName(),
            "Sparrow",
            "1965-12-20",
            new BigDecimal(28000.0),
            manager,
            "2020-03-06",
            new BigDecimal(15000.0));
    }

    public static Worker generateWorker(Manager manager) {
        return new Worker(
            generateName(),
            "Sparrow",
            "1965-12-20",
            new BigDecimal(28000.0),
            manager,
            "2020-03-06",
            new BigDecimal(15000.0));
    }

    public static Trainee generateTrainee(Manager manager) {
        return new Trainee(
            generateName(),
            "Sparrow",
            "1965-12-20",
            new BigDecimal(28000.0),
            manager,
            LocalDate.now().toString(),
            1);
    }

    public static String generateName() {
        return "Jack" + employeeCounter++;
    }
}
