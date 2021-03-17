package querybuilder.implementations;

import daoo.query.Column;
import daoo.query.clause.SelectClause;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public class SelectClauseImpl implements SelectClause {
    private final Column<?>[] columns;

    public SelectClauseImpl(Column<?>... columns) {
        this.columns = columns.clone();
    }

    @Override
    public Stream<Column<?>> component() {
        return Stream.of(this.columns);
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
