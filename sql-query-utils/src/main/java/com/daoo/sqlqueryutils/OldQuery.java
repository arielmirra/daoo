package com.daoo.sqlqueryutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OldQuery {
    private List<String> columnNames;
    private final String table;
    private final String condition;
    private final String groupByColumns;
    private final String orderByColumns;

    private OldQuery(Builder builder) {
        this.columnNames = builder.columnNames;
        this.table = builder.table;
        this.condition = builder.condition;
        this.groupByColumns = builder.groupByColumn;
        this.orderByColumns = builder.orderByColumn;
    }


    public static class Builder {
        private List<String> columnNames = new ArrayList<>();
        private String table = "";
        private String condition = "";
        private String groupByColumn = "";
        private String orderByColumn = "";

        public Builder select(final String columnNames) {
            // to-do: each clause should be a Clause Object that also has a builder and determines possible chained clauses
            if (!this.columnNames.isEmpty()) duplicatedClause("SELECT");
            this.columnNames = Arrays.asList(columnNames.replaceAll("\\s", "").split(","));
            return this;
        }

        public Builder from(final String table) {
            if (!this.table.isEmpty()) duplicatedClause("FROM");
            this.table = table;
            return this;
        }

        public Builder where(final String condition) {
            // to-do: create Condition with it's builder and provide it here instead of String
            if (!this.condition.isEmpty()) duplicatedClause("WHERE");
            this.condition = condition;
            return this;
        }

        public Builder groupBy(final String groupByColumn) {
            if (!this.groupByColumn.isEmpty()) duplicatedClause("GROUP BY");
            validateColumn(groupByColumn);
            this.groupByColumn = groupByColumn;
            return this;
        }

        public Builder orderBy(final String orderByColumn) {
            if (!this.orderByColumn.isEmpty()) duplicatedClause("ORDER BY");
            validateColumn(orderByColumn);
            this.orderByColumn = orderByColumn;
            return this;
        }

        public String build() {
            StringBuilder sb = new StringBuilder();

            if (!columnNames.isEmpty()) {
                final String columns = columnNames.toString();
                final String names = columns.substring(1,columns.length() - 1);
                sb.append("SELECT ").append(names);
            }
            else fail("Required 'select' clause not provided");

            if (!table.isEmpty()) sb.append(" FROM ").append(table);
            else fail("Required 'from' clause not provided");

            if (!condition.isEmpty()) sb.append(" WHERE ").append(condition);

            if (!orderByColumn.isEmpty()) sb.append(" ORDER BY ").append(orderByColumn);

            if (!groupByColumn.isEmpty()) sb.append(" GROUP BY ").append(groupByColumn);

            return sb.append(';').toString();
        }

        private void duplicatedClause(final String clause) {
            fail(String.format(clause, "There can't be multiple %s clauses"));
        }

        private void validateColumn(final String column) {
            if (!columnNames.contains(column) && !columnNames.contains("*"))
                fail(String.format(column, "%s column is not in the table"));
        }

        private void fail(final String message) {
            throw new RuntimeException(message);
        }
    }
}
