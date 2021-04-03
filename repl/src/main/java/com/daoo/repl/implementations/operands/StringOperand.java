package com.daoo.repl.implementations.operands;

import daoo.repl.Operand;
import org.jetbrains.annotations.NotNull;

public class StringOperand implements Operand {
    private final String value;

    public StringOperand(String value) {
        this.value = value;
    }

    @Override
    public <T> T as(@NotNull Class<T> type) {
        if(String.class.equals(type)) {
            //noinspection unchecked
            return (T) value;
        }
        throw new ClassCastException();
    }

    @Override
    public String print() {
        // print as a string
        return "\"" + value + "\"";
    }
}
