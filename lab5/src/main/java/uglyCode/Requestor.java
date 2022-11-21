package uglyCode;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Random;

public class Requestor implements Runnable {
//    private volatile static int RID;
//    private int id = RID++;
    private int id;
    private static int a;
    private static int b;
    //creates random for the enum
    private static final Random random = new Random();
    PriorityQueue<Message> MyQueue;
    PriorityQueue<Message> generalQueue;

    public Requestor(int id, PriorityQueue<Message> generalQueue) {
        System.out.println("HERE" + id);
        this.id = id;
        this.generalQueue = generalQueue;
        this.MyQueue = new PriorityQueue<>();
    }

//    public int generateNumber() {
//        // TODO
//    }

    public Priorities generatePriority() {
        return Priorities.values()[random.nextInt(Priorities.values().length)];
    }

    synchronized public void createRequest() {
        generalQueue.add(new Request(1, 2, generatePriority(), this, LocalDateTime.now()));
    }

    public void run() {
//        while (true) {
            createRequest();
            while (true) {
                Response response = null;
                synchronized (MyQueue) {
                    response = (Response) MyQueue.poll();
                }
                if (response != null) {
                    System.out.println(response);
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        }
    }

    @Override
    public String toString() {
        return "uglyCode.Requestor{" +
            "id=" + id +
            '}';
    }
}

