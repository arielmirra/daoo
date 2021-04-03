package com.daoo.repl.implementations.commands;

import com.daoo.repl.implementations.operands.DoubleOperand;
import daoo.repl.Command;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public abstract class BinaryArithmeticCommand implements Command {
    private OperandStack previous = null;

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        previous = stack;
        final OperandStack.Result pop = stack.pop();
        final Double term1 = getDouble(pop);
        final Double term2 = getDouble(pop.tail().pop());
        final Double result = calculate(term1, term2);
        return previous.push(new DoubleOperand(result));
    }

    @Override
    public OperandStack undo() {
        return previous;
    }

    public Double getDouble(OperandStack.Result result) {
        return result.element().as(Double.class);
    }

    abstract Double calculate(Double term1, Double term2);
}
