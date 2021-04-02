package com.daoo.sqlqueryutils.implementations;

import daoo.query.Column;
import daoo.query.clause.GroupByClause;

import java.util.stream.Stream;

public class GroupByClauseImpl implements GroupByClause {
    private final Column<?> col;

    public GroupByClauseImpl(Column<?> col) {
        this.col = col;
    }

    @Override
    public Stream<Column<?>> component() {
        return Stream.of(col);
    }
}
