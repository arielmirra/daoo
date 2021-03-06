package com.daoo.sqlqueryutils;

import daoo.query.*;
import daoo.query.clause.*;
import com.daoo.sqlqueryutils.implementations.*;
import com.daoo.sqlqueryutils.implementations.visitors.QueryValidatorVisitor;

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
            ClauseImpl<?>[] clauses = {
                    new ClauseImpl<>(select),
                    new ClauseImpl<>(from),
                    new ClauseImpl<>(where),
                    new ClauseImpl<>(orderBy),
                    new ClauseImpl<>(groupBy),
            };

            QueryImpl query = new QueryImpl(clauses);

            validator.visit(query);

            return query;
        }
    }

}
