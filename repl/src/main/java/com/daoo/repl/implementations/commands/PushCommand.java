package com.daoo.repl.implementations.commands;

import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public class PushCommand implements Command {
    private final Operand operand;
    private OperandStack previous = null;

    public PushCommand(Operand operand) {
        this.operand = operand;
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        previous = stack;
        return previous.push(operand);
    }

    @Override
    public OperandStack undo() {
        return previous;
    }
}
