package com.daoo.repl.implementations.factories;

import com.daoo.repl.implementations.commands.*;
import daoo.repl.Command;
import daoo.repl.Factory.CommandFactory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BinaryArithmeticCommandFactory implements CommandFactory {
    private Map<String, BinaryArithmeticCommand> commandMap;

    public BinaryArithmeticCommandFactory() {
        commandMap = new HashMap<>();
        commandMap.put("+", new SumCommand());
        commandMap.put("-", new SubtractCommand());
        commandMap.put("*", new MultiplyCommand());
        commandMap.put("/", new DivideCommand());
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return commandMap.get(line);
    }

    @Override
    public boolean test(@NotNull String line) {
        return commandMap.keySet().stream().anyMatch(line::equals);
    }
}
