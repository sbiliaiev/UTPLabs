package uglyCode;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

public class Responder implements Runnable {

    private PriorityQueue<Message> generalQueue;

    public Responder(PriorityQueue<Message> generalQueue) {
        this.generalQueue = generalQueue;
    }

    public void run() {
        while (true) {
            Request request = null;
            synchronized (generalQueue) {
                request = (Request) generalQueue.poll();
            }
            if (request != null) {
                int a = request.get_a() + request.get_b();
                Response response = new Response(a, request.get_priority(), request.get_creator(), LocalDateTime.now());
                synchronized (request.creator.MyQueue) {
                    request.creator.MyQueue.add(response);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
