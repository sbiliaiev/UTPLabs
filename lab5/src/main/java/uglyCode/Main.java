package uglyCode;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        PriorityQueue<Message> pr = new PriorityQueue<>();
        for (int i = 0; i < 5; i++) {
            new Thread(new Requestor(i, pr)).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(new Responder(pr)).start();
        }

    }

}
