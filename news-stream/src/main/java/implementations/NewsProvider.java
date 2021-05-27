package implementations;

import interfaces.Resource;
import interfaces.ResourceChange;
import interfaces.ResourceProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class NewsProvider implements ResourceProvider {

    String URL;
    String source;
    Duration duration;
    List<Resource> resources = new ArrayList<>();

    public NewsProvider(String URL, String source, Duration duration) {
        this.URL = URL;
        this.source = source;
        this.duration = duration;
    }

    @Override
    public Iterable<Resource> resources() {
        try {
            Document doc = Jsoup.connect(URL).get();
            this.getArticles(doc);
            return resources;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Duration interval() {
        return duration;
    }

    public abstract void getArticles(Document doc);
}
