package uglyCode;

import java.time.LocalDateTime;

public class Request extends Message {
    private static int id = 0;
    private int a = 0;
    private int b = 0;
    private int lastId = id++;

    public Request(int a, int b, Priorities priority, Requestor creator, LocalDateTime timeCreated) {
        super(a, b, priority, creator, timeCreated);
    }

    @Override
    public String toString() {
        return "uglyCode.Request{" +
            "a=" + a +
            ", b=" + b +
            ", lastId=" + lastId + creator + get_priority() +
            '}';
    }
}
