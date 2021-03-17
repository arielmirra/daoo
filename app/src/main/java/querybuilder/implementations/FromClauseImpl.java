package querybuilder.implementations;

import daoo.query.Table;
import daoo.query.clause.FromClause;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.stream.Stream;

public class FromClauseImpl implements FromClause {
    private final Table table;

    public FromClauseImpl(Table table) {
        this.table = table;
    }

    @Override
    public Stream<Table> component() {
        return Stream.of(this.table);
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
