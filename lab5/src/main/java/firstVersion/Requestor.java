package firstVersion;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Random;

public class Requestor implements Runnable {

    private int id;
    private static int a;
    private static int b;
    private static final Random random = new Random();
    private final PriorityQueue<Message> responseQueue;
    private final PriorityQueue<Message> generalQueue;

    public Requestor(int id, PriorityQueue<Message> generalQueue) {
        this.id = id;
        this.generalQueue = generalQueue;
        this.responseQueue = new PriorityQueue<>();
    }

    @Override
    public String toString() {
        return "Requestor {" +
            "id=" + id +
            '}';
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            createRequest();
        }
        awaitForResponse();
    }

    public void notifyResponse(Response response) {
        synchronized (responseQueue) {
            responseQueue.add(response);
        }
    }

    private int generateNumber() {
        return random.nextInt(9) + 1;
    }

    private Priorities generatePriority() {
        return Priorities.values()[random.nextInt(Priorities.values().length)];
    }

    private void createRequest() {
        Request request = new Request(generateNumber(), generateNumber(), generatePriority(), this, LocalDateTime.now());
        System.out.println("Creating request " + request);
        synchronized (generalQueue) {
            generalQueue.add(request);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void awaitForResponse() {
        while (true) {
            Response response = null;
            synchronized (responseQueue) {
                response = (Response) responseQueue.poll();
            }
            if (response != null) {
                System.out.println("Received response= " + response);
            }
//            sleep();
        }
    }
}

