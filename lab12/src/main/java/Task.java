import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Integer> {

    private static final int TIME_MIN = 2;
    private static final int TIME_MAX = 10;
    private static final int TIME_DIFF = TIME_MAX - TIME_MIN;
    private static final int FAIL_CHANCE = 3; // -> 1/3

    private Random random = new Random();
    private static int nextID = 1;

    private String name;
    private Status status = Status.Pending;
    private Integer result = null;

    private FutureTask<Integer> futureTask;

    public Task() {
        name = generateName();
        futureTask = new FutureTask<>(this);
    }

    private static String generateName() {
        return "Task" + nextID++;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getResult() {
        return result;
    }

    public FutureTask<Integer> getFutureTask() {
        return futureTask;
    }

    public boolean isDone() {
        return futureTask.isDone();
    }

    private boolean isFail() {
        return random.nextInt(FAIL_CHANCE) == 0;
    }

    @Override
    public Integer call() throws Exception {
        status = Status.Running;
        int time = TIME_MIN + random.nextInt(TIME_DIFF);
        TimeUnit.SECONDS.sleep(time);
        if (isFail()) {
            result = null;
            status = Status.Failed;
        } else {
            result = random.nextInt();
            status = Status.Accomplished;
        }
        return result;
    }

}