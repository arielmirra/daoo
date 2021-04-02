package com.daoo.sqlqueryutils.implementations.visitors;

import daoo.query.*;
import daoo.query.clause.*;
import org.jetbrains.annotations.NotNull;
import com.daoo.sqlqueryutils.NumericCompoundExpression;

public class SqlVisitor implements QueryVisitor {
    StringBuilder sb = new StringBuilder();

    public String print(@NotNull Query query) {
        this.visit(query);
        if (sb.charAt(sb.length() - 1) == ' ') sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public void visit(@NotNull Column<?> column) {
        sb.append(column.getName());
    }

    @Override
    public void visit(@NotNull Table table) {
        sb.append(table.getName());
    }

    @Override
    public void visit(@NotNull Constant<?> constant) {
        sb.append(constant.getValue().toString());
    }

    private void visit(Expression<?> exp) {
        if (exp instanceof NumericCompoundExpression) this.visit((NumericCompoundExpression) exp);
        if (exp instanceof Criteria) this.visit((Criteria) exp);
        if (exp instanceof Column<?>) this.visit((Column<?>) exp);
        if (exp instanceof Constant) this.visit((Constant<?>) exp);
    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {
        final String[] template = expression.getOperator().getTemplate();
        final int arity = expression.getOperator().getArity();
        var operands = expression.getOperands();

        sb.append("(");
        sb.append(template[0]);
        for (int i = 0; i < arity; i++) {
            this.visit(operands[i]);
            sb.append(template[i + 1]);
        }
        sb.append(")");
    }

    @Override
    public void visit(@NotNull Clause<?> clause) {

    }

    @Override
    public void visit(@NotNull SelectClause clause) {
        sb.append("SELECT ");
        clause.component().forEach(c -> {
            this.visit(c);
            sb.append(", ");
        });
        removeComma();
    }

    @Override
    public void visit(@NotNull FromClause clause) {
        sb.append("FROM ");
        clause.component().forEach(c -> {
            this.visit(c);
            sb.append(", ");
        });
        removeComma();
    }

    @Override
    public void visit(@NotNull WhereClause clause) {
        sb.append("WHERE ");
        this.visit(clause.component());
        sb.append(" ");
    }

    @Override
    public void visit(@NotNull OrderByClause clause) {
        sb.append("ORDER BY ");
        clause.component().forEach(c -> {
            this.visit(c);
            sb.append(", ");
        });
        removeComma();
    }

    @Override
    public void visit(@NotNull GroupByClause clause) {
        sb.append("GROUP BY ");
        clause.component().forEach(c -> {
            this.visit(c);
            sb.append(", ");
        });
        removeComma();
    }

    @Override
    public void visit(@NotNull HavingClause clause) {
        sb.append("HAVING ");
        this.visit(clause.component());
    }

    @Override
    public void visit(@NotNull UsingClause clause) {
        sb.append("USING ");
        clause.component().forEach(c -> {
            this.visit(c);
            sb.append(", ");
        });
        removeComma();
    }

    private void removeComma() {
        sb.delete(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1);
    }
}
