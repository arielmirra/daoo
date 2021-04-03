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
        return new ResultImpl(operandStack.pop(), new OperandStackImpl(operandStack));
    }

    @Override
    public Operand peek() throws NoSuchElementException {
        return operandStack.peek();
    }

    @Override
    public OperandStack push(@NotNull Operand operand) {
        Stack<Operand> tempStack = (Stack<Operand>) operandStack.clone();
        tempStack.push(operand);
        return new OperandStackImpl(tempStack);
    }

    @Override
    public boolean isEmpty() {
        return operandStack.isEmpty();
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


