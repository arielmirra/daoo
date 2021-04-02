package com.daoo.sqlqueryutils.implementations.visitors;

import com.google.gson.*;
import daoo.query.*;
import daoo.query.clause.*;
import org.jetbrains.annotations.NotNull;
import com.daoo.sqlqueryutils.NumericCompoundExpression;

public class JsonVisitor implements QueryVisitor {
    private JsonObject json = new JsonObject();

    public String print(@NotNull Query query) {
        // todo: build an serialize a JSON Object.
        return "";
    }

    @Override
    public void visit(@NotNull Column<?> column) {

    }

    @Override
    public void visit(@NotNull Table table) {

    }

    @Override
    public void visit(@NotNull Constant<?> constant) {
        final var value = constant.getValue();
        if (value instanceof Integer) {}
        if (value instanceof String) {}

    }

    private void visit(Expression<?> exp) {
        if (exp instanceof NumericCompoundExpression) this.visit((NumericCompoundExpression) exp);
        if (exp instanceof Criteria) this.visit((Criteria) exp);
        if (exp instanceof Column<?>) this.visit((Column<?>) exp);
        if (exp instanceof Constant) this.visit((Constant<?>) exp);
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
}
