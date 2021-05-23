import implementations.ClarinProvider;
import implementations.LaNacionProvider;
import interfaces.Resource;
import org.junit.jupiter.api.Test;

import java.time.Duration;

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
}
