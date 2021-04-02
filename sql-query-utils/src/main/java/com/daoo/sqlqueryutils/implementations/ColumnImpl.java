package com.daoo.sqlqueryutils.implementations;

import daoo.query.Column;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public abstract class ColumnImpl<T extends Comparable<T>> implements Column<T> {
    private String name;

    public ColumnImpl(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(@NotNull Visitor visitor) {
        visitor.visit(this);
    }
}
