package querybuilder.implementations;

import daoo.query.Clause;
import daoo.query.visitor.Visitable;

public class ClauseImpl<T extends Visitable> implements Clause<T> {
    private final T component;

    public ClauseImpl(T component) {
        this.component = component;
    }

    @Override
    public T component() {
        return this.component;
    }
}
