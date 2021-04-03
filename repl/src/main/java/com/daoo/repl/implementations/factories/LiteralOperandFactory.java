package com.daoo.repl.implementations.factories;

import com.daoo.repl.implementations.operands.StringOperand;
import daoo.repl.Factory;
import daoo.repl.Operand;
import org.jetbrains.annotations.NotNull;

public class LiteralOperandFactory implements Factory<Operand> {
    private final String STRING_TOKEN = "\"";

    @NotNull
    @Override
    public Operand apply(@NotNull String line) {
        // remove "", save only the text
        return new StringOperand(line.substring(1, line.length() - 1));
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.startsWith(STRING_TOKEN) && line.endsWith(STRING_TOKEN);
    }
}
