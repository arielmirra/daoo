package com.daoo.sqlqueryutils.implementations;

import daoo.query.*;
import com.daoo.sqlqueryutils.NumericCompoundExpression;

public class StrColumn extends ColumnImpl<String> {

    public StrColumn(String name) {
        super(name);
    }

    public Criteria startsWith(String str) {
        return new Criteria(DefaultOperator.LIKE, this, Constant.constant(str + "%"));
    }

    public NumericCompoundExpression length() {
        return new NumericCompoundExpression(DefaultOperator.STRING_LENGTH, this);
    }
}
