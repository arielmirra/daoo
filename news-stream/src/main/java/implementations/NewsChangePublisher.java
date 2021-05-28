package implementations;

import interfaces.ResourceChange;

import java.util.concurrent.*;

/**
 * periodically publishes ResourceChanges from a ResourceProvider
 */
public class NewsChangePublisher extends SubmissionPublisher<ResourceChange> {

    private final ScheduledExecutorService scheduler;
    private final NewsChangeProvider newsChangeProvider;
    private final ScheduledFuture<?> periodicTask;

    public NewsChangePublisher(NewsProvider provider) {
        super(Executors.newFixedThreadPool(1), 5);
        scheduler = new ScheduledThreadPoolExecutor(1);
        newsChangeProvider = new NewsChangeProvider(provider);

        // schedule a submit with the provider interval
        periodicTask = scheduler.scheduleAtFixedRate(
                () -> newsChangeProvider.getChanges().forEach(this::submit), 0, provider.interval().getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public void close() {
        periodicTask.cancel(false);
        scheduler.shutdown();
        super.close();
    }
}
