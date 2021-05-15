package com.daoo.repl.implementations.factories;

import com.daoo.repl.implementations.ReplRegistry;
import com.daoo.repl.implementations.commands.DeclareCommand;
import daoo.repl.Command;
import daoo.repl.Factory;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class DeclareCommandFactory implements Factory.CommandFactory {
    private ReplRegistry registry;

    public DeclareCommandFactory(ReplRegistry registry) {
        this.registry = registry;
    }

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return new DeclareCommand(line, registry);
    }


    @Override
    public boolean test(@NotNull String line) {
        final String pattern = "[A-z0-9\\(\\) ]+=[A-z0-9\\+\\-\\*/ ]+";
        return Pattern.matches(pattern, line);
    }
}
