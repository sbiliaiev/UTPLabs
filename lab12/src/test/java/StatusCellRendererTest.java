import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StatusCellRendererTest {

    @Test
    void testGetTableCellRendererComponent() {
        JTable table = new JTable();
        StatusCellRenderer renderer = new StatusCellRenderer();

        // Test PENDING status
        Component component = renderer.getTableCellRendererComponent(table, Status.Pending, false, false, 0, 0);
        assertEquals("Pending", ((JLabel) component).getText());
        assertEquals(Status.Pending.getBackgroundColor(), component.getBackground());
        assertEquals(Status.Pending.getFontColor(), component.getForeground());

        // Test RUNNING status
        component = renderer.getTableCellRendererComponent(table, Status.Running, false, false, 0, 0);
        assertEquals("Running", ((JLabel) component).getText());
        assertEquals(Status.Running.getBackgroundColor(), component.getBackground());
        assertEquals(Status.Running.getFontColor(), component.getForeground());

        // Test ACCOMPLISHED status
        component = renderer.getTableCellRendererComponent(table, Status.Accomplished, false, false, 0, 0);
        assertEquals("Accomplished", ((JLabel) component).getText());
        assertEquals(Status.Accomplished.getBackgroundColor(), component.getBackground());
        assertEquals(Status.Accomplished.getFontColor(), component.getForeground());

        // Test FAILED status
        component = renderer.getTableCellRendererComponent(table, Status.Failed, false, false, 0, 0);
        assertEquals("Failed", ((JLabel) component).getText());
        assertEquals(Status.Failed.getBackgroundColor(), component.getBackground());
        assertEquals(Status.Failed.getFontColor(), component.getForeground());
    }
}
