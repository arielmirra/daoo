package querybuilder;

import daoo.query.Constant;
import daoo.query.Criteria;
import daoo.query.DefaultOperator;
import daoo.query.Expression;

public interface NumericFunctions extends Expression<Integer> {

    default Criteria lt(final Expression<Integer> exp) {
        return new Criteria(DefaultOperator.LT, this, exp);
    }

    default Criteria between(final Expression<Integer> min, final Expression<Integer> max) {
        return new Criteria(DefaultOperator.BETWEEN, this, Constant.constant(min), Constant.constant(max));
    }

    default Criteria between(final int min, final int max) {
        return new Criteria(DefaultOperator.BETWEEN, this, Constant.constant(min), Constant.constant(max));
    }
}
