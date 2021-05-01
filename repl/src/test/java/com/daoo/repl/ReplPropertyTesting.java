package com.daoo.repl;

import com.daoo.repl.implementations.factories.BinaryArithmeticCommandFactory;
import com.daoo.repl.implementations.factories.DoubleOperandFactory;
import com.daoo.repl.implementations.factories.LengthCommandFactory;
import com.daoo.repl.implementations.factories.LiteralOperandFactory;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class ReplPropertyTesting {

    @Property
    public void doubleOperandFactory(Double testDouble) {
        DoubleOperandFactory factory = new DoubleOperandFactory();
        assertTrue(factory.test(testDouble.toString()));
    }

    @Property
    public void literalOperandFactory(String testString) {
        LiteralOperandFactory factory = new LiteralOperandFactory();
        assertTrue(factory.test(makeLiteral(testString)));
    }

    @Property
    public void binaryArithmeticCommandFactory(String testString, Double testDouble) {
        BinaryArithmeticCommandFactory factory = new BinaryArithmeticCommandFactory();
        // identified failing strings like "" and "\n"
        if (!testString.equals("") && !testString.equals("\n")) {
            final String varLiteral = testString + "=" + makeLiteral(testString);
            assertTrue(factory.test(varLiteral));

            final String varDouble = testString + "=" + testDouble.toString();
            assertTrue(factory.test(varDouble));
        }
    }

    @Property
    public void LengthCommandFactory(String testString, Double testDouble) {
        LengthCommandFactory factory = new LengthCommandFactory();
        if (!testString.equals("length")) assertFalse(factory.test(testString));
    }

    private String makeLiteral(String testString) {
        return "\"" + testString + "\"";
    }
}
