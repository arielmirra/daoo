package implementations;

import interfaces.ChangeType;
import interfaces.Resource;
import interfaces.ResourceChange;
import interfaces.ResourceProvider;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/** periodically publishes ResourceChanges from a ResourceProvider */
public class ResourceStream extends PeriodicPublisher<ResourceChange> {

    private ResourceChangeSupplier resourceChangeSupplier;

    public ResourceStream(ResourceProvider provider) {
        super(
                Executors.newFixedThreadPool(1),
                5,
                createSupplier(provider),
                provider.interval().getSeconds(),
                TimeUnit.SECONDS
                );
    }

    private static Supplier<Stream<ResourceChange>> createSupplier(ResourceProvider provider) {
        return  new Supplier<>() {
            @Override
            public Stream<ResourceChange> get() {
                return StreamSupport.stream(provider.resources().spliterator(), false).map(resource -> new ResourceChange() {
                    @Override
                    public Resource resource() {
                        return resource;
                    }

                    @Override
                    public ChangeType type() {
                        return ChangeType.ADD;
                    }
                });
            }
        };
    }

    private static Supplier<Stream<ResourceChange>> createResourceChangeSupplier(ResourceProvider provider) {
        return new ResourceChangeSupplier(provider);
    }
}
