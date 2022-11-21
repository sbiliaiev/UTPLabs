package uglyCode;

import java.time.LocalDateTime;

public class Response extends Message {

    public Response(int a, Priorities priority, Requestor creator, LocalDateTime timeCreated) {
        super(a, priority, creator, timeCreated);
    }

    @Override
    public String toString() {
        return "uglyCode.Message{" +
            "a=" + a +
            ", priority=" + priority +
            ", creator=" + creator +
            ", timeCreated=" + timeCreated +
            '}';
    }
}
