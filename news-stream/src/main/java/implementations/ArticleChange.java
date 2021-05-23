package implementations;

import interfaces.ChangeType;
import interfaces.Resource;
import interfaces.ResourceChange;

public class ArticleChange implements ResourceChange {
    private Article article;
    private ChangeType changeType;

    public ArticleChange(Article article, ChangeType changeType) {
        this.article = article;
        this.changeType = changeType;
    }

    @Override
    public Resource resource() {
        return article;
    }

    @Override
    public ChangeType type() {
        return changeType;
    }

    @Override
    public String toString() {
        return article.source() + " " + changeType + ": " + article.label();
    }
}
