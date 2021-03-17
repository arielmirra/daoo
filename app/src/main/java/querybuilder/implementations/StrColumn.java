package querybuilder.implementations;

import daoo.query.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class StrColumn implements Column<String> {
    private final String name;

    public StrColumn(String name) {
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

    public Criteria startsWith(String str) {
        return new Criteria(DefaultOperator.LIKE, Constant.constant(str + "%"));
    }

    public Integer length() {
        return name.length();
    }
}
