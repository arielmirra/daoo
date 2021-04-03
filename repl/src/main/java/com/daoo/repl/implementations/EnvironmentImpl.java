package com.daoo.repl.implementations;

import com.daoo.repl.implementations.commands.PushCommand;
import daoo.repl.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentImpl implements Environment {
    private List<Factory<Operand>> operandFactories;
    private List<Factory<Command>> commandFactories;
    private OperandStack operandStack;

    public EnvironmentImpl() {
        this.operandFactories = new ArrayList<>();
        this.commandFactories = new ArrayList<>();
        this.operandStack = new OperandStackImpl();
    }

    public EnvironmentImpl(List<Factory<Operand>> operandFactories, List<Factory<Command>> commandFactories) {
        this.operandFactories = operandFactories;
        this.commandFactories = commandFactories;
        this.operandStack = new OperandStackImpl();
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
        for (Factory<Operand> factory : operandFactories)
            if (factory.test(input)) return new PushCommand(factory.apply(input));

        for (Factory<Command> factory : commandFactories)
            if (factory.test(input)) return factory.apply(input);

        return Command.EMPTY_COMMAND;
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
