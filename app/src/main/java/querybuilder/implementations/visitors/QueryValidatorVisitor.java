package querybuilder.implementations.visitors;

import daoo.query.*;
import daoo.query.clause.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class QueryValidatorVisitor implements Visitor {
    @Override
    public void visit(@NotNull Query query) {

    }

    @Override
    public void visit(@NotNull Column<?> column) {

    }

    @Override
    public void visit(@NotNull Table table) {

    }

    @Override
    public void visit(@NotNull Constant<?> constant) {

    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {

    }

    @Override
    public void visit(@NotNull Clause<?> clause) {

    }

    @Override
    public void visit(@NotNull SelectClause clause) {

    }

    @Override
    public void visit(@NotNull FromClause clause) {

    }

    @Override
    public void visit(@NotNull WhereClause clause) {

    }

    @Override
    public void visit(@NotNull OrderByClause clause) {

    }

    @Override
    public void visit(@NotNull GroupByClause clause) {

    }

    @Override
    public void visit(@NotNull HavingClause clause) {

    }

    @Override
    public void visit(@NotNull UsingClause clause) {

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
