package implementations;

import interfaces.Resource;
import interfaces.ResourceProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class NewsProvider implements ResourceProvider {

    String URL;
    String source;
    Duration duration;
    List<Article> resources = new ArrayList<>();

    public NewsProvider(String URL, String source, Duration duration) {
        this.URL = URL;
        this.source = source;
        this.duration = duration;
    }

    @Override
    public Iterable<? extends Resource> resources() {
        try {
            System.out.println("NewsProvider.resources");
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
