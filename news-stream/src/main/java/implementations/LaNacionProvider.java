package implementations;

import org.jsoup.nodes.Document;

import java.time.Duration;

public class LaNacionProvider extends NewsProvider {

    public LaNacionProvider(Duration duration) {
        super("https://www.lanacion.com.ar", "LA NACION", duration);
    }

    @Override
    public void getArticles(Document doc) {
        doc.select("article div .com-title a")
                .forEach(article -> {
                    var link = article.attr("href");
                    var title = article.attr("title");
                    resources.add(new Article(link, title, source));
                });
    }
}
