package firstVersion;

import java.time.LocalDateTime;

public class Response extends Message {
    private final int sum;

    public Response(int sum, Priorities priority, Requestor creator, LocalDateTime timeCreated) {
        super(priority, creator, timeCreated);
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Response {" +
            "sum=" + sum +
            ", priority=" + priority +
            ", creator=" + creator +
            ", timeCreated=" + timeCreated +
            '}';
    }

    public int getSum() {
        return sum;
    }
}
