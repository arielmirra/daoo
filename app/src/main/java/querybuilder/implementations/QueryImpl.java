package querybuilder.implementations;

import daoo.query.Clause;
import daoo.query.Query;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class QueryImpl implements Query {

    @Override
    public Iterable<Clause<?>> getClauses() {
        return null;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
