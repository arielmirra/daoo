package querybuilder.implementations.visitors;

import daoo.query.*;
import daoo.query.clause.*;
import daoo.query.visitor.Visitor;
import org.jetbrains.annotations.NotNull;

public class JsonVisitor implements Visitor {


    @Override
    public void visit(@NotNull Query query) {

    }

    @Override
    public void visit(@NotNull Column<?> column) {

    }

    @Override
    public void visit(@NotNull Table table) {

    }

    @Override
    public void visit(@NotNull Constant<?> constant) {

    }

    @Override
    public void visit(@NotNull CompoundExpression<?> expression) {

    }

    @Override
    public void visit(@NotNull Clause<?> clause) {

    }

    @Override
    public void visit(@NotNull SelectClause clause) {

    }

    @Override
    public void visit(@NotNull FromClause clause) {

    }

    @Override
    public void visit(@NotNull WhereClause clause) {

    }

    @Override
    public void visit(@NotNull OrderByClause clause) {

    }

    @Override
    public void visit(@NotNull GroupByClause clause) {

    }

    @Override
    public void visit(@NotNull HavingClause clause) {

    }

    @Override
    public void visit(@NotNull UsingClause clause) {

    }
}
