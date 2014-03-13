package com.dev9;

import static org.fest.assertions.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorTest {

    private Calculator calculator = new Calculator();

    private static final Logger LOG = LoggerFactory.getLogger(CalculatorTest.class);

    @BeforeMethod(alwaysRun = true)
    public void resetCalc() {
        calculator.resetCalc();
    }

    @Test(groups={"disabled"})
    public void testAdd() {
        assertThat(calculator.value()).isZero();
        calculator.add(5);
        assertThat(calculator.value()).isEqualTo(5);
    }

    @Test(groups={"test", "broken"})
    public void testSubtract() {
        assertThat(calculator.value()).isZero();
        calculator.subtract(5);
        assertThat(calculator.value()).isEqualTo(-5);

    }

    @Test(groups={"test"})
    public void testMultiply() {
        assertThat(calculator.value()).isZero();

        calculator.add(5).multiply(2);

        assertThat(calculator.value()).isEqualTo(10);

    }

    @Test
    public void testDivide() {
        assertThat(calculator.value()).isZero();

        calculator.add(10).divide(2);

        assertThat(calculator.value()).isEqualTo(5);

    }

    /**
     *
     * @see <a href=
     *      "http://stackoverflow.com/questions/14137989/java-division-by-zero-doesnt-throw-an-arithmeticexception-why"
     *      >Why doesn't divide by zero return an error?</a>
     *      doubles and floats will actually return Infinity and not an exception
     *      ints and longs will throw an exception
     * */
    @Test
    public void testDivideByZero() {
        calculator.add(1).divide(0);
        LOG.info("Divide by zero results in... {}", calculator.value());
        assertThat(calculator.value()).isEqualTo(Double.POSITIVE_INFINITY);
    }

}
