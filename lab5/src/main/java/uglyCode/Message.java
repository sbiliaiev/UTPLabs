package uglyCode;

import java.time.LocalDateTime;

public abstract class Message implements Comparable<Message> {
    protected int a;
    protected int b;
    protected Priorities priority;
    protected Requestor creator;
    protected LocalDateTime timeCreated;

    //uglyCode.Request template
    public Message(int a, int b, Priorities priority, Requestor creator, LocalDateTime timeCreated) {
        this.a = a;
        this.b = b;
        this.priority = priority;
        this.creator = creator;
        this.timeCreated = timeCreated;
    }

    //uglyCode.Response template
    public Message(int a, Priorities priority, Requestor creator, LocalDateTime timeCreated) {
        this.a = a;
        this.priority = priority;
        this.creator = creator;
        this.timeCreated = timeCreated;
    }

    public int get_a() {
        return a;
    }

    public int get_b() {
        return b;
    }

    public Priorities get_priority() {
        return priority;
    }

    public Requestor get_creator() {
        return creator;
    }

    public LocalDateTime get_timeCreated() {
        return timeCreated;
    }

    @Override
    public int compareTo(Message o) {
        return this.priority.compareTo(o.priority);
    }

    @Override
    public String toString() {
        return "uglyCode.Message{" +
            "a=" + a +
            ", b=" + b +
            ", priority=" + priority +
            ", creator=" + creator +
            ", timeCreated=" + timeCreated +
            '}';
    }
}
