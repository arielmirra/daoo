package com.daoo.repl.implementations;

import daoo.repl.Operand;
import daoo.repl.OperandStack;
import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;
import java.util.Stack;

public class OperandStackImpl implements OperandStack, Cloneable {
    private Stack<Operand> operandStack;

    public OperandStackImpl() {
        this.operandStack = new Stack<>();
    }

    public OperandStackImpl(Stack<Operand> operandStack) {
        this.operandStack = operandStack;
    }

    @Override                 // EmptyStackException?
    public Result pop() throws NoSuchElementException {
        Stack<Operand> tempStack = clone();
        return new ResultImpl(tempStack.pop(), new OperandStackImpl(tempStack));
    }

    @Override
    public Operand peek() throws NoSuchElementException {
        return operandStack.peek();
    }

    @Override
    public OperandStack push(@NotNull Operand operand) {
        Stack<Operand> tempStack = clone();
        tempStack.push(operand);
        return new OperandStackImpl(tempStack);
    }

    @Override
    public boolean isEmpty() {
        return operandStack.isEmpty();
    }

    @Override
    protected Stack<Operand> clone() {
        return (Stack<Operand>) operandStack.clone();
    }

    public class ResultImpl implements Result {
        private Operand element;
        private OperandStack tail;

        public ResultImpl(Operand element, OperandStack tail) {
            this.element = element;
            this.tail = tail;
        }

        @Override
        public Operand element() {
            return element;
        }

        @Override
        public OperandStack tail() {
            return tail;
        }
    }
}


