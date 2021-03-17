package querybuilder;

import daoo.query.CompoundExpression;
import daoo.query.Expression;
import daoo.query.Operator;

abstract class AbstractCompoundExpression<T> implements CompoundExpression<T> {
    private final Operator operator;
    private final Expression<?>[] operands;

    AbstractCompoundExpression(Operator operator, Expression<?>[] operands) {
        this.operator = operator;
        this.operands = operands;
    }

    @Override
    public Operator getOperator() {
        return null;
    }

    @Override
    public Expression<?>[] getOperands() {
        return null;
    }
}
