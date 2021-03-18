package querybuilder;

import daoo.query.Column;
import daoo.query.Criteria;
import daoo.query.Query;
import daoo.query.Table;
import querybuilder.implementations.IntColumn;
import querybuilder.implementations.StrColumn;
import querybuilder.implementations.TableImpl;

import java.util.Arrays;

public class Builder {

    public static Table table(final String name) {
        return new TableImpl(name);
    }

    public static StrColumn string(final String name) {
        return new StrColumn(name);
    }

    public static IntColumn integer(final String name) {
        return new IntColumn(name);
    }

    public static QueryBuilder query() {
        return new QueryBuilder();
    }


    public static class QueryBuilder {
        private Column<?>[] columns;
        private Table table;
        private Column<?> orderByColumn;
        private Column<?> groupByColumn;

        public QueryBuilder select(Column<?>... columns) {
            this.columns = columns.clone();
            return this;
        }

        public QueryBuilder from(final Table table) {
            this.table = table;
            return this;
        }

        public QueryBuilder where(final Criteria criteria) {
            return this;
        }

        public QueryBuilder orderBy(Column<?> col) {
            this.orderByColumn = col;
            return this;
        }

        public QueryBuilder groupBy(Column<?> col) {
            this.groupByColumn = col;
            return this;
        }

        public Query build() {
            // TODO: Build the actual Query object.
            // TODO: Call the QueryValidatorVisitor to inspect Query validity.
            return null;
        }
    }

}
