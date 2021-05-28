package implementations;

import interfaces.Resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NewsChangeProvider {

    private NewsProvider newsProvider;
    private Map<String, Article> actualState = new HashMap<>();

    public NewsChangeProvider(NewsProvider newsProvider) {
        this.newsProvider = newsProvider;
    }

    public Set<ArticleChange> getChanges() {
        Iterable<? extends Resource> resources = newsProvider.resources();
        final Set<Article> incomingArticles = new HashSet<>();
        resources.forEach(resource -> incomingArticles.add((Article) resource));

        final Set<ArticleChange> result = getChanges(incomingArticles);

        refreshState(incomingArticles);

        return result;
    }

    private void refreshState(Set<Article> incomingArticles) {
        actualState.clear();
        incomingArticles.forEach(article -> actualState.put(article.id(), article));
    }

    private Set<ArticleChange> getChanges(Set<Article> incomingArticles) {
        final Set<ArticleChange> changes = new HashSet<>();

        // check for added or modified articles
        incomingArticles.forEach(article -> {
            if (actualState.containsKey(article.id())) {
                var previousArticle = actualState.get(article.id());
                if (!previousArticle.equals(article)) changes.add(ArticleChange.modified(article));
            } else {
                changes.add(ArticleChange.added(article));
            }
        });

        // check for removed articles
        actualState
                .values()
                .stream()
                .filter(ar -> !incomingArticles.contains(ar))
                .map(ArticleChange::removed)
                .forEach(changes::add);

        return changes;
    }
}
