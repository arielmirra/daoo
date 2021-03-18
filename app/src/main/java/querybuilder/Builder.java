package querybuilder;

import daoo.query.*;
import daoo.query.clause.*;
import querybuilder.implementations.*;
import querybuilder.implementations.visitors.QueryValidatorVisitor;

public class Builder {

    public static TableImpl table(final String name) {
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
        private QueryValidatorVisitor validator = new QueryValidatorVisitor();
        private SelectClause select;
        private FromClause from;
        private WhereClause where;
        private OrderByClause orderBy;
        private GroupByClause groupBy;

        public QueryBuilder select(Column<?>... columns) {
            this.select = new SelectClauseImpl(columns);
            return this;
        }

        public QueryBuilder from(final Table table) {
            this.from = new FromClauseImpl(table);
            return this;
        }

        public QueryBuilder where(final Criteria criteria) {
            this.where = new WhereClauseImpl(criteria);
            return this;
        }

        public QueryBuilder orderBy(Column<?> col) {
            this.orderBy = new OrderByClauseImpl(col);
            return this;
        }

        public QueryBuilder groupBy(Column<?> col) {
            this.groupBy = new GroupByClauseImpl(col);
            return this;
        }

        public Query build() {
            // TODO: Build the actual Query object.
            // TODO: Call the QueryValidatorVisitor to inspect Query validity.

            ClauseImpl<?>[] clauses = {
                    new ClauseImpl<SelectClause>(select),
                    new ClauseImpl<FromClause>(from),
                    new ClauseImpl<WhereClause>(where),
                    new ClauseImpl<OrderByClause>(orderBy),
                    new ClauseImpl<GroupByClause>(groupBy),
            };

            QueryImpl query = new QueryImpl(clauses);

            validator.visit(query);

            return query;
        }
    }

}
