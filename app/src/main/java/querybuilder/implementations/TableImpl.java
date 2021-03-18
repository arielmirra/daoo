package querybuilder.implementations;

import daoo.query.Column;
import daoo.query.Table;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableImpl implements Table {
    private final String name;
    private List<Column<?>> columns;

    public TableImpl(final String name) {
        this.name = name;
        this.columns = new ArrayList<>();
    }

    public TableImpl(final String name, Column<?>... columns) {
        this.name = name;
        this.columns = Arrays.asList(columns.clone());
    }

    @Override
    public <Col extends Column<Type>, Type extends Comparable<Type>> Col col(Col col) {
        this.columns.add(col);
        return col;
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
