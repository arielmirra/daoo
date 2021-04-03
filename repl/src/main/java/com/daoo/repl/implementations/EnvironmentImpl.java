package com.daoo.repl.implementations;

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
        // todo: return push command for operands or commands testing the input with the factories
        return null;
    }

    @Override
    public void execute(@NotNull Command command) {

    }

    @Override
    public void undo(@NotNull Command command) {

    }

    @NotNull
    @Override
    public Environment copy() {
        return this;
    }

    @Override
    public OperandStack stack() {
        return operandStack;
    }
}
