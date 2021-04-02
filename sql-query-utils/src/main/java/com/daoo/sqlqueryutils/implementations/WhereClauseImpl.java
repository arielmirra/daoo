package com.daoo.sqlqueryutils.implementations;

import daoo.query.Criteria;
import daoo.query.clause.WhereClause;

public class WhereClauseImpl implements WhereClause {
    private final Criteria criteria;

    public WhereClauseImpl(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Criteria component() {
        return this.criteria;
    }
}
