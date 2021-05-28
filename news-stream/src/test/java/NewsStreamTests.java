import implementations.ClarinProvider;
import implementations.LaNacionProvider;
import implementations.NewsChangePublisher;
import interfaces.Resource;
import interfaces.ResourceChange;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Flow;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NewsStreamTests {

    @Test
    void fetchNewsLaNacion() {
        var provider = new LaNacionProvider(Duration.ofSeconds(5));
        var resources = provider.resources();
        assertNotNull(resources);
        for (Resource r : resources) System.out.println(r);
    }

    @Test
    void fetchNewsClarin() {
        var provider = new ClarinProvider(Duration.ofSeconds(5));
        var resources = provider.resources();
        assertNotNull(resources);
        for (Resource r : resources) System.out.println(r);
    }

    @Test
    void resourceStreamTest() {
        var provider = new LaNacionProvider(Duration.ofSeconds(5));
        var stream = new NewsChangePublisher(provider);

        stream.subscribe(new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("NewsStreamTests.onSubscribe");
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(ResourceChange item) {
                System.out.println("NewsStreamTests.onNext");
                System.out.println(item);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("NewsStreamTests.onError");
            }

            @Override
            public void onComplete() {
                System.out.println("NewsStreamTests.onComplete");
            }
        });

        System.out.println();
    }
}
