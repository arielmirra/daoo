package implementations;

import interfaces.ResourceChange;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Flow;

public class NewsAggregatorPublisher implements Flow.Processor<ResourceChange, ResourceChange> {

    private Set<Flow.Subscription> subscriptions = new HashSet<>();
    private Set<Flow.Subscriber> subscribers = new HashSet<>();

    @Override
    public void subscribe(Flow.Subscriber<? super ResourceChange> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriptions.add(subscription);
        subscription.request(1);
    }

    @Override
    public void onNext(ResourceChange item) {
        subscribers.forEach(subscriber -> subscriber.onNext(item));
        subscriptions.forEach(subscription -> subscription.request(1));
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("NewsAggregatorPublisher.onError");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("NewsAggregatorPublisher.onComplete");
    }
}
