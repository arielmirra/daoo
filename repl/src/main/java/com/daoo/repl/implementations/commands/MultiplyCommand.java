package com.daoo.repl.implementations.commands;

public class MultiplyCommand extends BinaryArithmeticCommand {
    @Override
    Double calculate(Double term1, Double term2) {
        return term1 * term2;
    }
}
