package implementations;

import interfaces.ResourceChange;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class NewsProcessor extends SubmissionPublisher<ResourceChange> implements Flow.Processor<ResourceChange, ResourceChange> {

    public List<ResourceChange> consumedElements = new ArrayList<>();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        // subscriptions are used for request() and back-pressure, not doing that for now.
    }

    @Override
    public void onNext(ResourceChange item) {
        consumedElements.add(item);

    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}
