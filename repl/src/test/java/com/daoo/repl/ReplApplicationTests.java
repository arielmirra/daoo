package com.daoo.repl;

import com.daoo.repl.implementations.EnvironmentImpl;
import com.daoo.repl.implementations.ReplImpl;
import com.daoo.repl.implementations.factories.BinaryArithmeticCommandFactory;
import com.daoo.repl.implementations.factories.DoubleOperandFactory;
import com.daoo.repl.implementations.factories.LengthCommandFactory;
import com.daoo.repl.implementations.factories.LiteralOperandFactory;
import daoo.repl.Environment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplApplicationTests {
    Repl repl;

    @BeforeEach
    void setup() {
        final Environment e = new EnvironmentImpl();
        e.addOperandFactory(new DoubleOperandFactory());
        e.addOperandFactory(new LiteralOperandFactory());
        e.addCommandFactory(new BinaryArithmeticCommandFactory());
        e.addCommandFactory(new LengthCommandFactory());

//        repl = new ReplImpl(e);
    }

    @Test
    void test() throws Exception {
        String text = tapSystemOut(() -> {
            CompletableFuture.runAsync(() -> repl.loop(System.in, System.out));
            System.out.println("test");
        });
        assertEquals("test", text.trim());
    }

}
