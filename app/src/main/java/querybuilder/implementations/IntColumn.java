package querybuilder.implementations;

import daoo.query.Column;
import daoo.query.Constant;
import daoo.query.Criteria;
import daoo.query.DefaultOperator;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class IntColumn implements Column<Integer> {
    private final String name;

    public IntColumn(String name) {
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

    public Criteria between(final Integer min, final Integer max) {
        return new Criteria(DefaultOperator.BETWEEN, Constant.constant(min), Constant.constant(max));
    }

    public Criteria lt(final Integer value) {
        return new Criteria(DefaultOperator.LT, Constant.constant(value));
    }
}
