package implementations;

import org.jsoup.nodes.Document;

import java.time.Duration;

public class ClarinProvider extends NewsProvider {

    public ClarinProvider(Duration duration) {
        super("https://www.clarin.com", "CLARIN", duration);
    }

    @Override
    public void getArticles(Document doc) {
        System.out.println("ClarinProvider.getArticles");
        doc.select("article a")
                .forEach(article -> {
                    if (article.select("h2").size() > 0) {
                        var link = article.attr("href");
                        var title = article.select("h2").first().text();
                        resources.add(new Article(URL + link, title, source));
                    }
                });
    }
}
