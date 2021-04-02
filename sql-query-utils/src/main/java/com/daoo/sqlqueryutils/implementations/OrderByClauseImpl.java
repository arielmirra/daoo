package com.daoo.sqlqueryutils.implementations;

import daoo.query.Column;
import daoo.query.clause.OrderByClause;

import java.util.stream.Stream;

public class OrderByClauseImpl implements OrderByClause {
    private final Column<?> col;

    public OrderByClauseImpl(Column<?> col) {
        this.col = col;
    }

    @Override
    public Stream<Column<?>> component() {
        return Stream.of(col);
    }
}
