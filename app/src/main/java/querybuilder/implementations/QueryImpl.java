package querybuilder.implementations;

import daoo.query.Clause;
import daoo.query.Query;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class QueryImpl implements Query {
    private final Clause<?>[] clauses;

    public QueryImpl(Clause<?>[] clauses) {
        this.clauses = clauses;
    }

    @Override
    public Iterable<Clause<?>> getClauses() {
        Iterable<Clause<?>> iterableClauses = Arrays.asList(clauses);
        return iterableClauses;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
