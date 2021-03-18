package querybuilder.implementations.visitors;

import daoo.query.*;
import daoo.query.clause.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class QueryValidatorVisitor implements Visitor {
    @Override
    public void visit(@NotNull Query query) {
        query.getClauses().forEach(c -> {
            if (c.component() instanceof SelectClause) {
                SelectClause visitable = (SelectClause) c.component();
                this.visit(visitable);
            }
            // todo: pattern match all possible types and visit them, save the data, then validate the query
        });
    }

    @Override
    public void visit(@NotNull Column<?> column) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull Table table) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull Constant<?> constant) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull Clause<?> clause) {
        this.visit(clause.component());
    }

    @Override
    public void visit(@NotNull SelectClause clause) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull FromClause clause) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull WhereClause clause) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull OrderByClause clause) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull GroupByClause clause) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull HavingClause clause) {
        throw new RuntimeException("visiting..");
    }

    @Override
    public void visit(@NotNull UsingClause clause) {
        throw new RuntimeException("visiting..");
    }

    public void visit(@NotNull Object obj) {
        throw new RuntimeException("visiting a not visitable object");
    }

//    private void duplicatedClause(final String clause) {
//        fail(String.format(clause, "There can't be multiple %s clauses"));
//    }
//
//    private void validateColumn(final String column) {
//        if (!columnNames.contains(column) && !columnNames.contains("*"))
//            fail(String.format(column, "%s column is not in the table"));
//    }
}
