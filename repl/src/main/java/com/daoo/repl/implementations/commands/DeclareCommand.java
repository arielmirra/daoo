package com.daoo.repl.implementations.commands;

import com.daoo.repl.implementations.ReplRegistry;
import com.daoo.repl.implementations.operands.DoubleOperand;
import com.daoo.repl.implementations.operands.StringOperand;
import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class DeclareCommand implements Command {
    private OperandStack previous = null;
    private ReplRegistry registry;
    private String key;
    private String value;

    public DeclareCommand(String line, ReplRegistry registry) {
        this.registry = registry;
        final String[] terms = line.split("=");
        this.key = terms[0].trim();
        this.value = terms[1].trim();
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        this.previous = stack;
        registry.declarations.put(key, value);
        return stack;
    }

    @Override
    public OperandStack undo() {
        registry.declarations.remove(key);
        return previous;
    }
}
