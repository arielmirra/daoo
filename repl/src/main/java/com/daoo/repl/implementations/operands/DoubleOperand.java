package com.daoo.repl.implementations.operands;

import daoo.repl.Operand;
import org.jetbrains.annotations.NotNull;

public class DoubleOperand implements Operand {
    private final Double value;

    public DoubleOperand(Double value) {
        this.value = value;
    }

    @Override
    public <T> T as(@NotNull Class<T> type) {
        if(Double.class.equals(type)) {
            //noinspection unchecked
            return (T) value;
        }
        throw new ClassCastException();
    }

    @Override
    public String print() {
        return String.valueOf(value);
    }
}
