package com.daoo.repl.implementations.factories;

import daoo.repl.Command;
import daoo.repl.Factory.CommandFactory;
import org.jetbrains.annotations.NotNull;

public class LengthCommandFactory implements CommandFactory {
    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return null;
    }

    @Override
    public boolean test(@NotNull String line) {
        return false;
    }
}
