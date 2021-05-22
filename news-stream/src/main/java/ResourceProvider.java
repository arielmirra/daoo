import java.time.Duration;

public interface ResourceProvider {
    Iterable<Resource> resources();
    Duration interval();
}
