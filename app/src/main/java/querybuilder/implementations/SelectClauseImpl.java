package querybuilder.implementations;

import daoo.query.Column;
import daoo.query.clause.SelectClause;

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
}
