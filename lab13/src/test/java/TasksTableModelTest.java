import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TasksTableModelTest {
    private TasksTableModel tableModel;

    @BeforeEach
    void setUp() {
        tableModel = new TasksTableModel();
    }

    @Test
    void testAddTask() {
        tableModel.addTask();
        assertEquals(1, tableModel.getRowCount());
        assertEquals("Task1", tableModel.getValueAt(0, 0));
    }

    @Test
    void testAddTask_maxTasks() {
        for (int i = 0; i < TasksTableModel.MAX_TASKS; i++) {
            tableModel.addTask();
        }
        assertEquals(TasksTableModel.MAX_TASKS, tableModel.getRowCount());
    }

    @Test
    void testAddTask_replaceFinishedTask() {
        for (int i = 0; i < TasksTableModel.MAX_TASKS; i++) {
            tableModel.addTask();
        }
        tableModel.addTask();
        assertEquals(TasksTableModel.MAX_TASKS+1, tableModel.getRowCount());
    }

    @Test
    void testGetValueAt() {
        tableModel.addTask();
        assertEquals("Pending", tableModel.getValueAt(0, 1).toString());
        assertNull(tableModel.getValueAt(0, 2));
    }

    @Test
    void testGetColumnName() {
        assertEquals("Name", tableModel.getColumnName(0));
        assertEquals("Status", tableModel.getColumnName(1));
        assertEquals("Result", tableModel.getColumnName(2));
    }
}