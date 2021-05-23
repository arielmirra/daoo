package implementations;

import interfaces.ResourceChange;

import java.util.concurrent.*;

public class ResourcePublisher extends SubmissionPublisher<ResourceChange> {

    private NewsProvider provider;
    private ScheduledExecutorService executor;

    public ResourcePublisher(NewsProvider provider) {
        super(Executors.newScheduledThreadPool(1), 5); // how to schedule publisher submit??
        this.provider = provider;
    }

    @Override
    public int submit(ResourceChange item) {
        return super.submit(item);
    }

    @Override
    public void subscribe(Flow.Subscriber<? super ResourceChange> subscriber) {
        super.subscribe(subscriber);
//        if(this.executor == null) {
//            executor = Executors.newScheduledThreadPool(1);
//            executor.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    submit()
//                }
//            })
//        }
    }
}
