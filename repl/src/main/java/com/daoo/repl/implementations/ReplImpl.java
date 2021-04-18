package com.daoo.repl.implementations;

import com.daoo.repl.Repl;
import com.daoo.repl.implementations.factories.BinaryArithmeticCommandFactory;
import com.daoo.repl.implementations.factories.DoubleOperandFactory;
import com.daoo.repl.implementations.factories.LengthCommandFactory;
import com.daoo.repl.implementations.factories.LiteralOperandFactory;
import daoo.repl.Command;
import daoo.repl.Environment;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class ReplImpl extends Repl {
    private final Stack<Command> undos;
    private final Stack<Command> redos;

    public ReplImpl(@NotNull Environment environment) {
        super(environment);
        this.undos = new Stack<>();
        this.redos = new Stack<>();
    }

    @Override
    public void loop(@NotNull InputStream input, @NotNull OutputStream output) {
        final PrintStream out = new PrintStream(output);
        final Scanner scanner = new Scanner(input);
        String line;
        while ((line = scanner.nextLine()) != null) {
            switch (line) {
                case "undo" -> undo(out);
                case "redo" -> redo(out);
                case "" -> {continue;}
                default -> {
                    final Command c = environment.evaluate(line);
                    undos.push(c);
                    environment.execute(c);
                }
            }
            out.println(environment.stack().peek().print());
        }

    }

    private void undo(PrintStream out) {
        final Command c = undos.pop();
        environment.undo(c);
        redos.push(c);
    }

    private void redo(PrintStream out) {
        final Command c = redos.pop();
        environment.execute(c);
        undos.push(c);
    }

    public static void main(String[] args) {
        final Environment env = new EnvironmentImpl();
        env.addOperandFactory(new DoubleOperandFactory());
        env.addOperandFactory(new LiteralOperandFactory());
        env.addCommandFactory(new BinaryArithmeticCommandFactory());
        env.addCommandFactory(new LengthCommandFactory());

        final Repl repl = new ReplImpl(env);
        repl.loop(System.in, System.out);
    }
}
