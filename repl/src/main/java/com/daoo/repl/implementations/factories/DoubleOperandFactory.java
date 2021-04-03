package com.daoo.repl.implementations.factories;

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
}
