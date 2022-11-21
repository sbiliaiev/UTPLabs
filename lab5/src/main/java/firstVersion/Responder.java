package firstVersion;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class Responder implements Runnable {

    private final PriorityQueue<Message> generalQueue;

    public Responder(PriorityQueue<Message> generalQueue) {
        this.generalQueue = generalQueue;
    }

    public void run() {
        while (true) {
            Request request = pollRequest();
            if (request != null) {
                calculateResponse(request);
            }
            sleep();
        }
    }

    private Request pollRequest() {
        Request request = null;
        synchronized (generalQueue) {
            request = (Request) generalQueue.poll();
        }
        return request;
    }

    private void calculateResponse(Request request) {
        System.out.println("Calculating response...");
        int sum = request.getA() + request.getB();
        Response response = new Response(sum, request.getPriority(), request.getCreator(), LocalDateTime.now());
        request.creator.notifyResponse(response);
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
