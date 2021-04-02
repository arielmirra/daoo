package com.daoo.sqlqueryutils.implementations.visitors;

import daoo.query.*;
import daoo.query.clause.*;
import org.jetbrains.annotations.NotNull;
import com.daoo.sqlqueryutils.NumericCompoundExpression;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class QueryValidatorVisitor implements QueryVisitor {
    private SelectClause select;
    private FromClause from;
    private WhereClause where;
    private OrderByClause orderBy;
    private GroupByClause groupBy;
    private HavingClause having;
    private UsingClause using;

    private List<Column<?>> selectColumns;
    private List<Table> fromTables;

    @Override
    public void visit(@NotNull Column<?> column) {
        if (!selectColumns.contains(column))
            fail(String.format("%s column is not in selected columns", column.getName()));
    }

    @Override
    public void visit(@NotNull Table table) {

    }

    @Override
    public void visit(@NotNull Constant<?> constant) {

    }

    private void visit(Expression<?> exp) {
        if (exp instanceof NumericCompoundExpression) this.visit((NumericCompoundExpression) exp);
        if (exp instanceof Criteria) this.visit((Criteria) exp);
        if (exp instanceof Column<?>) this.visit((Column<?>) exp);
        if (exp instanceof Constant) this.visit((Constant<?>) exp);
    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {
       Arrays.stream(expression.getOperands()).sequential().forEach(this::visit);
    }

    @Override
    public void visit(@NotNull Clause<?> clause) {

    }

    @Override
    public void visit(@NotNull SelectClause clause) {
        if(this.select == null) {
            this.select = clause;
            this.selectColumns = clause.component().collect(toList());
        }
        else duplicatedClause("SELECT");
    }

    @Override
    public void visit(@NotNull FromClause clause) {
        if(this.from == null) this.from = clause;
        else duplicatedClause("FROM");
        this.fromTables = clause.component().collect(toList());
    }

    @Override
    public void visit(@NotNull WhereClause clause) {
        this.where = clause;
        this.visit(clause.component());
    }

    @Override
    public void visit(@NotNull OrderByClause clause) {
        if(this.orderBy == null) this.orderBy = clause;
        else duplicatedClause("ORDER BY");
        this.visit(clause.component().findFirst().get());
    }

    @Override
    public void visit(@NotNull GroupByClause clause) {
        if(this.groupBy == null) this.groupBy = clause;
        else duplicatedClause("GROUP BY");
        this.visit(clause.component().findFirst().get());
    }

    @Override
    public void visit(@NotNull HavingClause clause) {
        if(this.having == null) this.having = clause;
        else duplicatedClause("HAVING");
        // validate criteria
    }

    @Override
    public void visit(@NotNull UsingClause clause) {
        this.using = clause;
        this.visit(clause.component().findFirst().get());
    }

    private void duplicatedClause(final String clause) {
        fail(String.format("There can't be multiple %s clauses", clause));
    }

    private void fail(final String msg) {
        throw new RuntimeException(msg);
    }
}
