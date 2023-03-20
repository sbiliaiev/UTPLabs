import java.awt.*;
import javax.swing.*;

public class TasksFrame extends JFrame {

    private static final int REFRESH_RATE = 200;

    private TasksTableModel model = new TasksTableModel();

    public TasksFrame() {
        super("Task generator");

        JTable table = new JTable(model);
        table.getColumn("Status").setCellRenderer(new StatusCellRenderer());
        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll);

        JButton addButton = new JButton("Add task");
        addButton.addActionListener(e -> addTask());
        add(addButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    void addTask() {
        model.addTask();
    }

    public void run() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(REFRESH_RATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                model.fireTableDataChanged();
            }
        }).start();
    }

}