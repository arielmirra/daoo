package com.daoo.repl;

import com.daoo.repl.implementations.OperandStackImpl;
import com.daoo.repl.implementations.factories.BinaryArithmeticCommandFactory;
import com.daoo.repl.implementations.factories.LengthCommandFactory;
import com.daoo.repl.implementations.factories.LiteralOperandFactory;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import daoo.repl.Command;
import daoo.repl.Environment;
import daoo.repl.OperandStack;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class ReplPropertyTesting {

    @Property
    public void doubleOperandFactory(@InRange(min = "-100", max = "100") Double testDouble) {

    }

    @Property
    public void literalOperandFactory(String testString) {
        LiteralOperandFactory factory = new LiteralOperandFactory();
        assertTrue(factory.test(makeLiteral(testString)));
    }

    @Property
    public void binaryArithmeticCommandFactory(String testString, double testDouble) {
        BinaryArithmeticCommandFactory factory = new BinaryArithmeticCommandFactory();
        // identified failing strings like "" and "\n"
        if (!testString.equals("") && !testString.equals("\n")) {
            final String varLiteral = testString + "=" + makeLiteral(testString);
            assertTrue(factory.test(varLiteral));

            final String varDouble = testString + "=" + testDouble;
            assertTrue(factory.test(varDouble));
        }
    }

    @Property
    public void LengthCommandFactory(String testString, double testDouble) {
        LengthCommandFactory factory = new LengthCommandFactory();
        final Command lengthCommand = factory.apply(testString);
        final OperandStack resultStack = lengthCommand.execute(new OperandStackImpl());
        resultStack.pop().element().as(double.class);

        if (!testString.equals("length")) assertFalse(factory.test(testString));
    }

    private String makeLiteral(String testString) {
        return "\"" + testString + "\"";
    }

    private String repl(String testString) {
        // TODO: test repl
        return null;
    }

    private Environment env() {
        // TODO: create full env to pass to repl
        return null;
    }

}
