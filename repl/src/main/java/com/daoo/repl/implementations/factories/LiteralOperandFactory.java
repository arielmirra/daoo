package com.daoo.repl.implementations.factories;

import daoo.repl.Factory;
import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

public class LiteralOperandFactory implements Factory<Operand> {
    private final String STRING_TOKEN = "\"";

    @NotNull
    @Override
    public Operand apply(@NotNull String line) {
        return new StringOperand(line);
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.startsWith(STRING_TOKEN) && line.endsWith(STRING_TOKEN);
    }

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
            return value;
        }
    }
}
