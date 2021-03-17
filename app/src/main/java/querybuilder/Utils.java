package querybuilder;

import daoo.query.Column;
import daoo.query.Criteria;
import daoo.query.Query;
import daoo.query.Table;
import querybuilder.implementations.IntColumn;
import querybuilder.implementations.StrColumn;
import querybuilder.implementations.TableImpl;

import java.util.Arrays;

public class Utils {

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
            this.table = table;
            return this;
        }

        public QueryBuilder orderBy(Column<?> col) {
            if (!Arrays.stream(columns).anyMatch(c -> c.equals(col))) {
                this.orderByColumn = col;
                return this;
            } else throw new RuntimeException("Column provided is not in the table");
        }

        public QueryBuilder groupBy(Column<?> col) {
            if (!Arrays.stream(columns).anyMatch(c -> c.equals(col))) {
                this.groupByColumn = col;
                return this;
            } else throw new RuntimeException("Column provided is not in the table");
        }

        public Query build() {
            // TODO: Build the actual Query object.
            return null;
        }
    }

}
