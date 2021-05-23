package implementations;

import interfaces.Resource;

public class Article implements Resource {

    private String link;
    private String label;
    private String source;

    public Article(String link, String label, String source) {
        this.link = link;
        this.label = label;
        this.source = source;
    }

    @Override
    public String link() {
        return link;
    }

    @Override
    public String label() {
        return label;
    }

    public String source() {
        return source;
    }

    @Override
    public String toString() {
        return label + " --> " + link;
    }

    @Override
    public boolean equals(Object obj) {
        var other = (Article) obj;
        return other.source.equals(this.source) && other.label.equals(this.label) && other.link.equals(this.link);
    }
}
