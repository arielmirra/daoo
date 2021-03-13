package querybuilder;

public class Query {
    private final String columnNames;
    private final String table;
    private final String condition;
    private final String groupByColumns;
    private final String orderByColumns;

    private Query(Builder builder) {
        this.columnNames = builder.columnNames;
        this.table = builder.table;
        this.condition = builder.condition;
        this.groupByColumns = builder.groupByColumns;
        this.orderByColumns = builder.orderByColumns;
    }


    public static class Builder {
        private String columnNames = "";
        private String table = "";
        private String condition = "";
        private String groupByColumns = "";
        private String orderByColumns = "";

        public Builder select(final String columnNames) {
            this.columnNames = columnNames;
            return this;
        }

        public Builder from(final String table) {
            this.table = table;
            return this;
        }

        public Builder where(final String condition) {
            this.condition = condition;
            return this;
        }

        public Builder groupBy(final String groupByColumns) {
            this.groupByColumns = groupByColumns;
            return this;
        }

        public Builder orderBy(final String orderByColumns) {
            this.orderByColumns = orderByColumns;
            return this;
        }

        public String build() {
            StringBuilder sb = new StringBuilder();

            if (!columnNames.isEmpty()) sb.append("SELECT ").append(columnNames);
            else fail("Required 'select' clause not provided");

            if (!table.isEmpty()) sb.append(" FROM ").append(table);
            else fail("Required 'from' clause not provided");

            if (!condition.isEmpty()) sb.append(" WHERE ").append(condition);

            if (!orderByColumns.isEmpty()) sb.append(" ORDER BY ").append(orderByColumns);

            if (!groupByColumns.isEmpty()) sb.append(" GROUP BY ").append(groupByColumns);

            return sb.append(';').toString();
        }

        private void fail(final String message) {
            throw new RuntimeException(message);
        }
    }
}
