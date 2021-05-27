package implementations;

import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Publisher that publishes objects of type T periodically.
 * Requires a Supplier<Stream<T>>
 */
abstract class PeriodicPublisher<T> extends SubmissionPublisher<T> {

    final ScheduledFuture<?> periodicTask;
    final ScheduledExecutorService scheduler;

    PeriodicPublisher(Executor executor, int maxBufferCapacity,
                      Supplier<Stream<T>> supplier,
                      long period, TimeUnit unit) {
        super(executor, maxBufferCapacity);
        scheduler = new ScheduledThreadPoolExecutor(1);
        periodicTask = scheduler.scheduleAtFixedRate(
                () -> supplier.get().forEach(this::submit), 0, period, unit);
    }

    public void close() {
        periodicTask.cancel(false);
        scheduler.shutdown();
        super.close();
    }
}
