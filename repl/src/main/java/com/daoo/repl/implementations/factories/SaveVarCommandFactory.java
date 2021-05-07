package com.daoo.repl.implementations.factories;

import com.daoo.repl.implementations.commands.SaveVarCommand;
import daoo.repl.Command;
import daoo.repl.Factory.CommandFactory;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class SaveVarCommandFactory implements CommandFactory {
    // env?
    // registry?

    @NotNull
    @Override
    public Command apply(@NotNull String line) {
        return new SaveVarCommand(line);
    }

    @Override
    public boolean test(@NotNull String line) {
        return isVar(line);
    }

    private boolean isVar(String line) {
        final String varPattern = "\\w\\s*=\\s*\\w";
        return Pattern.matches(varPattern, line);
    }
}
