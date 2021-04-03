package com.daoo.repl.implementations.factories;

import com.daoo.repl.implementations.operands.DoubleOperand;
import daoo.repl.Factory;
import daoo.repl.Operand;
import org.jetbrains.annotations.NotNull;

public class DoubleOperandFactory implements Factory<Operand> {
    @NotNull
    @Override
    public Operand apply(@NotNull String line) {
        return new DoubleOperand(Double.parseDouble(line));
    }

    @Override
    public boolean test(@NotNull String line) {
        try {
            Double.parseDouble(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
