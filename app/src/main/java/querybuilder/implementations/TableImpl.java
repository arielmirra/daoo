package querybuilder.implementations;

import daoo.query.Column;
import daoo.query.Table;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TableImpl implements Table {
    private final String name;
    private List<Column<?>> columns;

    public TableImpl(final String name) {
        this.name = name;
    }

    @Override
    public <Col extends Column<Type>, Type extends Comparable<Type>> Col col(Col col) {
        return null;
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
}
