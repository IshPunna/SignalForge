import java.util.ArrayDeque;
import java.util.Queue;

public class AlertQueue {

    private final Queue<SignalResult> alerts;
    private final int capacity;

    public AlertQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Alert queue capacity must be positive."
            );
        }

        this.alerts = new ArrayDeque<>();
        this.capacity = capacity;
    }

    public boolean addAlert(SignalResult result) {
        if (result == null || alerts.size() >= capacity) {
            return false;
        }

        return alerts.offer(result);
    }

    public SignalResult removeNextAlert() {
        return alerts.poll();
    }

    public SignalResult peekNextAlert() {
        return alerts.peek();
    }

    public boolean isEmpty() {
        return alerts.isEmpty();
    }

    public int size() {
        return alerts.size();
    }
}