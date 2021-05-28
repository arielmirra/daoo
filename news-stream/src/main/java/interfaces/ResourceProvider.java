package interfaces;

import java.time.Duration;

public interface ResourceProvider {
    Iterable<? extends Resource> resources();
    Duration interval();
}
