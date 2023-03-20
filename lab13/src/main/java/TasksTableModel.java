import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.swing.table.AbstractTableModel;

public class TasksTableModel extends AbstractTableModel {

    private static final int MAX_THREADS = 5;
    public static final int MAX_TASKS = 20;
    private static final String[] HEADERS = {"Name", "Status", "Result"};

    private List<Task> tasks = new ArrayList<>();
    private Executor executor = Executors.newFixedThreadPool(MAX_THREADS);;

    public void addTask() {
        if (tasks.size() <= MAX_TASKS) {
            generateTask();
        } else {
            Task finishedTask = findFinishedTask();
            if (finishedTask != null) {
                tasks.remove(finishedTask);
                generateTask();
            }
        }
    }

    private Task findFinishedTask() {
        for (Task task : tasks) {
            if (task.isDone())
                return task;
        }
        return null;
    }

    private void generateTask() {
        Task task = new Task();
        tasks.add(task);
        executor.execute(task.getFutureTask());
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return HEADERS.length;
    }

    @Override
    public String getColumnName(int column) {
        return HEADERS[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Task task = tasks.get(row);
        switch (column) {
            case 0:
                return task.getName();
            case 1:
                return task.getStatus();
            case 2:
                return task.getResult();
        }
        return null;
    }

}
