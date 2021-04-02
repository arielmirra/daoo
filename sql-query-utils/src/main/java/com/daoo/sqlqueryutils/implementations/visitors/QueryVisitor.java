package com.daoo.sqlqueryutils.implementations.visitors;

import daoo.query.*;
import daoo.query.clause.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public interface QueryVisitor extends Visitor {

    @Override
    default void visit(@NotNull Query query) {
        query.getClauses().forEach(c -> {
            if (c.component() instanceof Column<?>) this.visit((Column<?>) c.component());
            if (c.component() instanceof Table) this.visit((Table) c.component());
            if (c.component() instanceof Constant<?>) this.visit((Constant<?>) c.component());
            if (c.component() instanceof CompoundExpression<?>) this.visit((CompoundExpression<?>) c.component());
            if (c.component() instanceof Clause<?>) this.visit((Clause<?>) c.component());
            if (c.component() instanceof SelectClause) this.visit((SelectClause) c.component());
            if (c.component() instanceof FromClause) this.visit((FromClause) c.component());
            if (c.component() instanceof WhereClause) this.visit((WhereClause) c.component());
            if (c.component() instanceof OrderByClause) this.visit((OrderByClause) c.component());
            if (c.component() instanceof GroupByClause) this.visit((GroupByClause) c.component());
            if (c.component() instanceof HavingClause) this.visit((HavingClause) c.component());
            if (c.component() instanceof UsingClause) this.visit((UsingClause) c.component());
        });
    }
}
