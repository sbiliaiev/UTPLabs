import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TasksFrameTest {

    @Mock
    private TasksTableModel tableModel;

    @Test
    void testAddTask() {
        TasksFrame frame = mock(TasksFrame.class);
        frame.addTask();
        verify(tableModel, times(1)).addTask();
    }

    @Test
    @Timeout(value = 1)
    void testRun() {
        TasksFrame frame = new TasksFrame();
        frame.run();
        verify(tableModel, atLeast(1)).fireTableDataChanged();
    }
}