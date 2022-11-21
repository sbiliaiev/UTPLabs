package firstVersion;

import java.time.LocalDateTime;

public class Request extends Message {
    private final int a;
    private final int b;
    private static volatile int lastId = 0;
    private final int id = lastId++;

    public Request(int a, int b, Priorities priority, Requestor creator, LocalDateTime timeCreated) {
        super(priority, creator, timeCreated);
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Request{" +
            "id=" + id +
            ", a=" + a +
            ", b=" + b +
            ", priority=" + priority +
            ", creator=" + creator +
            ", timeCreated=" + timeCreated +
            "} " + super.toString();
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getId() {
        return id;
    }
}
