package com.daoo.repl.implementations;

import com.daoo.repl.implementations.commands.PushCommand;
import daoo.repl.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EnvironmentImpl implements Environment {
    private List<Factory<Operand>> operandFactories;
    private List<Factory<Command>> commandFactories;
    private OperandStack operandStack;
    private Map<String, Operand> variables;

    public EnvironmentImpl() {
        this.operandFactories = new ArrayList<>();
        this.commandFactories = new ArrayList<>();
        this.operandStack = new OperandStackImpl();
        this.variables = new HashMap<>();
    }

    public EnvironmentImpl(List<Factory<Operand>> operandFactories, List<Factory<Command>> commandFactories) {
        this.operandFactories = operandFactories;
        this.commandFactories = commandFactories;
        this.operandStack = new OperandStackImpl();
        this.variables = new HashMap<>();
    }

    @Override
    public void addOperandFactory(@NotNull Factory<Operand> factory) {
        operandFactories.add(factory);
    }

    @Override
    public void addCommandFactory(@NotNull Factory<Command> factory) {
        commandFactories.add(factory);
    }

    @NotNull
    @Override
    public Command evaluate(@NotNull String input) {
        if (variables.containsKey(input)) return push(variables.get(input));
        if (isVar(input)) {
            return saveVar(input);
        } else {
            return matchFactory(input);
        }
    }

    private Command push(Operand operand) {
        return new PushCommand(operand);
    }

    private Command matchFactory(String input) {
        for (Factory<Operand> factory : operandFactories)
            if (factory.test(input)) return push(factory.apply(input));

        for (Factory<Command> factory : commandFactories)
            if (factory.test(input)) return factory.apply(input);

        return Command.EMPTY_COMMAND;
    }

    private Command saveVar(String line) {
        final String[] terms = line.split("=");
        final String varName = terms[0];
        final Operand varValue = getOperand(terms[1]);
        variables.put(varName, varValue);
        return push(varValue);
    }

    private Operand getOperand(String term) {
        final List<Factory<Operand>> factories =
                operandFactories.stream().filter(f -> f.test(term)).collect(Collectors.toList());
        if (factories.size() > 0) {
            return factories.get(0).apply(term);
        } else {
            throw new RuntimeException("Invalid variable expression");
        }
    }

    private boolean isVar(String line) {
        final Pattern pattern = Pattern.compile("\\w\\s*=\\s*\\w", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }

    @Override
    public void execute(@NotNull Command command) {
        operandStack = command.execute(operandStack);
    }

    @Override
    public void undo(@NotNull Command command) {
        operandStack = command.undo();
    }

    @NotNull
    @Override
    public Environment copy() {
        return new EnvironmentImpl(operandFactories, commandFactories);
    }

    @Override
    public OperandStack stack() {
        return operandStack;
    }
}
