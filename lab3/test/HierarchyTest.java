import data.TestData;
import employee.Manager;
import employee.Worker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HierarchyTest {

    @Test
    void directorHasSubordinates() {
        Manager director = TestData.generateManager(null);
        director.addSubordinate(TestData.generateWorker(director));
        director.addSubordinate(TestData.generateWorker(director));
        director.addSubordinate(TestData.generateTrainee(director));
        director.addSubordinate(TestData.generateTrainee(director));

        assertEquals(director.getSubordinates().size(), 4);
    }

    @Test
    void directorHasManagers() {
        Manager director = TestData.generateManager(null);
        director.addSubordinate(TestData.generateManager(director));
        director.addSubordinate(TestData.generateManager(director));
        director.addSubordinate(TestData.generateManager(director));
        director.addSubordinate(TestData.generateManager(director));

        assertEquals(director.getSubordinates().size(), 4);
    }

    @Test
    void directorHasManagersWithSubordinates() {
        Manager director = TestData.generateManager(null);

        Manager manager1 = TestData.generateManager(director);
        manager1.addSubordinate(TestData.generateWorker(manager1));
        manager1.addSubordinate(TestData.generateTrainee(manager1));
        manager1.addSubordinate(TestData.generateWorker(manager1));

        Manager manager2 = TestData.generateManager(director);
        manager2.addSubordinate(TestData.generateWorker(manager2));
        manager2.addSubordinate(TestData.generateTrainee(manager2));
        manager2.addSubordinate(TestData.generateWorker(manager2));

        Manager manager3 = TestData.generateManager(director);
        manager3.addSubordinate(TestData.generateWorker(manager3));
        manager3.addSubordinate(TestData.generateTrainee(manager3));
        manager3.addSubordinate(TestData.generateWorker(manager3));

        director.addSubordinate(manager1);
        director.addSubordinate(manager2);
        director.addSubordinate(manager3);

        assertEquals(director.getSubordinates().size(), 3);
        director.getSubordinates().forEach(manager -> {
            assertEquals(((Manager) manager).getSubordinates().size(), 3);
        });

        assertEquals(director.getAllSubordinates().size(), 12);
    }

    @Test
    void cannotAssignSubordinateToWrongManager() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Manager director = TestData.generateDirector();
            Manager manager1 = TestData.generateManager(director);
            Manager manager2 = TestData.generateManager(director);

            Worker worker = TestData.generateWorker(manager1);
            manager2.addSubordinate(worker);
        });

        String expectedMessage = "Trying to register illegal employee";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void cannotAssignSubordinateManagerAsSubordinateToNonDirectorManager() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Manager director = TestData.generateDirector();
            Manager manager1 = TestData.generateManager(director);
            Manager manager2 = TestData.generateManager(director);

            manager1.addSubordinate(manager2);
        });

        String expectedMessage = "Trying to register a manager subordinate for a manager";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

}
