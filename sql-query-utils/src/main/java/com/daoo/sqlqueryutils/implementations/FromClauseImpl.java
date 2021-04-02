package com.daoo.sqlqueryutils.implementations;

import daoo.query.Table;
import daoo.query.clause.FromClause;

import java.util.stream.Stream;

public class FromClauseImpl implements FromClause {
    private final Table table;

    public FromClauseImpl(Table table) {
        this.table = table;
    }

    @Override
    public Stream<Table> component() {
        return Stream.of(this.table);
    }
}
