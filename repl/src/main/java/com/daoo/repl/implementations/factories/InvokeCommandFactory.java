package com.daoo.repl.implementations.factories;

import com.daoo.repl.implementations.ReplRegistry;
import com.daoo.repl.implementations.commands.InvokeCommand;
import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.Factory;
import org.jetbrains.annotations.NotNull;

public class InvokeCommandFactory implements Factory.CommandFactory {
    private Environment environment;
    private ReplRegistry registry;

    public InvokeCommandFactory(Environment environment, ReplRegistry registry) {
        this.environment = environment;
        this.registry = registry;
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return new InvokeCommand(line, registry, environment);
    }

    @Override
    public boolean test(@NotNull String line) {
        return registry.declarations.keySet().stream().anyMatch(line.trim()::equals);
    }
}
