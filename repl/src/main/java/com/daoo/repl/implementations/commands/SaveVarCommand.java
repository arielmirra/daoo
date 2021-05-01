package com.daoo.repl.implementations.commands;

import com.daoo.repl.implementations.operands.DoubleOperand;
import com.daoo.repl.implementations.operands.StringOperand;
import daoo.repl.Command;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class SaveVarCommand implements Command {
    private OperandStack previous = null;
    private String line;

    public SaveVarCommand(String line) {
        this.line = line;
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        previous = stack;
        return previous.push(saveVar(line));
    }

    @Override
    public OperandStack undo() {
        return previous;
    }

    private Operand saveVar(String line) {
        final String[] terms = line.split("=");
        final String varName = terms[0].trim();
        final Operand varValue = getOperand(terms[1].trim());
        // TODO how to save var? where should the vars map be?
        return null;
    }

    private Operand getOperand(String term) {
        if (isDouble(term)) return new DoubleOperand(Double.parseDouble(term));
        else if (isLiteral(term)) return new StringOperand(term);
        else throw new RuntimeException("Invalid variable expression");
    }

    private boolean isLiteral(String term) {
        final String STRING_TOKEN = "\"";
        return term.startsWith(STRING_TOKEN) && term.endsWith(STRING_TOKEN);
    }

    private boolean isDouble(String term) {
        String decimalPattern = "([0-9]*)\\.([0-9]*)";
        return Pattern.matches(decimalPattern, term);
    }
}
