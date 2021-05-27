package implementations;

import interfaces.Resource;
import interfaces.ResourceChange;
import interfaces.ResourceProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ResourceChangeSupplier implements Supplier<Stream<ResourceChange>> {

    private Map<String, Resource> resourceMap = new HashMap<>();
    private ResourceProvider provider;

    public ResourceChangeSupplier(ResourceProvider provider) {
        this.provider = provider;
    }

    Set<ResourceChange> resolveChanges(Set<Resource> resources) {
        // TODO return a set of ResourceChanges comparing the actual resourceMap with the provided resources (and update the resourceMap)
        return null;
    }

    @Override
    public Stream<ResourceChange> get() {
        return null;
    }
}
