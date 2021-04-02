package com.daoo.sqlqueryutils;

import daoo.query.Expression;
import daoo.query.Operator;

public class NumericCompoundExpression extends AbstractCompoundExpression<Integer> implements NumericFunctions {

    public NumericCompoundExpression(Operator operator, Expression<?>... operands) {
        super(operator, operands);
    }

}
