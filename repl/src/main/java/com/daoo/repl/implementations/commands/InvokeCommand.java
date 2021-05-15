package com.daoo.repl.implementations.commands;

import com.daoo.repl.implementations.ReplRegistry;
import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class InvokeCommand implements Command {
    private OperandStack previous = null;
    private ReplRegistry registry;
    private Environment environment;
    private String key;
    private String value;

    public InvokeCommand(String line, ReplRegistry registry, Environment environment) {
        this.registry = registry;
        this.environment = environment;
        this.key = line.trim();
    }

    @Override
    public OperandStack execute(@NotNull OperandStack stack) {
        this.previous = stack;
        if (isVar(key)) invokeVar();
        else if (isFunction(key)) invokeFunction();
        return environment.stack();
    }

    private boolean isVar(String key) {
        final String pattern = "[A-z0-9 ]+";
        return Pattern.matches(pattern, key);
    }

    private boolean isFunction(String key) {
        final String pattern = "[A-z0-9\\(\\) ]+";
        return Pattern.matches(pattern, key);
    }

    private void invokeVar() {
        final String value = registry.declarations.get(key);
        final Command command = environment.evaluate(value);
        environment.execute(command);
    }

    private void invokeFunction() {
        final String value = registry.declarations.get(key);
        final Environment tempEnv = environment.copy();

        // get function terms
        final String[] terms = value.split(" ");
        final String operator = terms[terms.length - 1];
        final String term2 = terms[terms.length - 2];
        final String term1 = terms[terms.length - 3];

        // apply function in temp environment
        tempEnv.execute(tempEnv.evaluate(term1));
        tempEnv.execute(tempEnv.evaluate(term2));
        tempEnv.execute(tempEnv.evaluate(operator));
        var result = tempEnv.stack().peek().as(Double.class);

        // save result into original environment
        environment.execute(environment.evaluate(result.toString()));
    }

    @Override
    public OperandStack undo() {
        registry.declarations.remove(key);
        return previous;
    }
}
