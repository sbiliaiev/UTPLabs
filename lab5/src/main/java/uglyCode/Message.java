package uglyCode;

import java.time.LocalDateTime;

public abstract class Message implements Comparable<Message> {
    protected Priorities priority;
    protected Requestor creator;
    protected LocalDateTime timeCreated;

    public Message(Priorities priority, Requestor creator, LocalDateTime timeCreated) {
        this.priority = priority;
        this.creator = creator;
        this.timeCreated = timeCreated;
    }

    public Priorities getPriority() {
        return priority;
    }

    public Requestor getCreator() {
        return creator;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    @Override
    public int compareTo(Message o) {
        return this.priority.compareTo(o.priority);
    }
}
