package com.daoo.repl.implementations.commands;

import com.daoo.repl.implementations.operands.DoubleOperand;
import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public class LengthCommand implements Command {
    private OperandStack previous = null;

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        previous = stack;
        final Operand element = stack.pop().element();
        final Double result = (double) element.as(String.class).length();
        return previous.push(new DoubleOperand(result));
    }

    @Override
    public OperandStack undo() {
        return previous;
    }
}
