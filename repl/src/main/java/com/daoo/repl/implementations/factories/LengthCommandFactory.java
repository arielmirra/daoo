package com.daoo.repl.implementations.factories;

import com.daoo.repl.implementations.commands.LengthCommand;
import daoo.repl.Command;
import daoo.repl.Factory.CommandFactory;
import org.jetbrains.annotations.NotNull;

public class LengthCommandFactory implements CommandFactory {
    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return new LengthCommand();
    }

    @Override
    public boolean test(@NotNull String line) {
        return line.equals("length");
    }
}
