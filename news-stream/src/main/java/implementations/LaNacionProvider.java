package implementations;

import org.jsoup.nodes.Document;

import java.time.Duration;

public class LaNacionProvider extends NewsProvider {
    private final String source = "https://www.lanacion.com.ar";

    public LaNacionProvider(Duration duration) {
        super("https://www.lanacion.com.ar", "LA NACION", duration);
    }

    @Override
    public void getArticles(Document doc) {
        doc.select("article section .com-title a")
                .forEach(article -> {
                    var link = source + article.attr("href");
                    var title = article.text();
                    resources.add(new Article(link, title, source));
                });
    }
}
