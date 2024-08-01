package com.sep.f7;

import com.sep.f7.PowerCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test class for the {@link PowerCalculator} class.
 * <p>
 * This class contains unit tests to verify the correctness and robustness of the
 * power function implemented in the {@link PowerCalculator} class. The tests cover
 * various scenarios including positive and negative bases, integer and fractional exponents,
 * zero bases, special cases, and edge cases to ensure the implementation behaves as expected.
 * </p>
 * <p>
 * The test cases are written using the JUnit framework and cover the following scenarios:
 * <ul>
 *   <li>Positive and negative exponents</li>
 *   <li>Fractional exponents</li>
 *   <li>Zero base with varying exponents</li>
 *   <li>Large and small values</li>
 *   <li>Special cases (e.g., 0^0, negative base with fractional exponent)</li>
 * </ul>
 * Each test case uses assertions to compare the actual output of the power function
 * to the expected output calculated using Java's built-in {@link Math#pow(double, double)} method.
 *
 * <p>
 * The tests ensure that the power function handles edge cases correctly, providing
 * reliable and precise results for all valid inputs and appropriate responses for invalid inputs.
 * </p>
 *
 * @see PowerCalculator
 */
class PowerCalculatorTest {
    PowerCalculator powerCalculator = new PowerCalculator();

    @Test
    void calculatePower_withLargeValues_returnsExpectedResult() {
        double x = 99.0;
        double y = 38.0;
        double result = powerCalculator.calculatePower(x, y);
        assertTrue(result > 0);
    }

    @Test
    void calculatePower_withSmallValues_returnsExpectedResult() {
        double x = 0.2;
        double y = 1.4;
        double result = powerCalculator.calculatePower(x, y);
        assertTrue(result > 0);
    }

    @Test
    void calculatePower_withPositiveIntegerExponent_returnsExpectedResult() {
        assertEquals(Math.pow(2.0, 10.0), powerCalculator.calculatePower(2.0, 10.0), 1e-12);
        assertEquals(Math.pow(4.0, 3.0), powerCalculator.calculatePower(4.0, 3.0), 1e-12);
        assertEquals(Math.pow(99.0, 0.0), powerCalculator.calculatePower(99.0, 0.0), 1e-12);
        assertEquals(Math.pow(0.0, 0.0), powerCalculator.calculatePower(0.0, 0.0), 1e-12);
        assertEquals(Math.pow(0.0, 5.0), powerCalculator.calculatePower(0.0, 5.0), 1e-12);
        assertEquals(Math.pow(5.0, 0.0), powerCalculator.calculatePower(5.0, 0.0), 1e-12);
    }

    @Test
    void calculatePower_withNegativeIntegerExponent_returnsExpectedResult() {
        assertEquals(0.0009765625, powerCalculator.calculatePower(2.0, -10.0), 1e-12);
        assertEquals(0.015625, powerCalculator.calculatePower(4.0, -3.0), 1e-12);
        assertEquals(1.0, powerCalculator.calculatePower(99.0, -0.0), 1e-12);
        assertEquals(1.0, powerCalculator.calculatePower(0.0, -0.0), 1e-12);
        assertEquals(Double.POSITIVE_INFINITY, powerCalculator.calculatePower(0.0, -5.0), 1e-12);
        assertEquals(1.0, powerCalculator.calculatePower(5.0, -0.0), 1e-12);
    }

    @Test
    void calculatePower_withFractionalExponent2_returnsExpectedResult() {
        assertEquals(Math.pow(4.0, 0.5), powerCalculator.calculatePower(4.0, 0.5), 1e-12);
        assertEquals(Math.pow(16.0, -0.5), powerCalculator.calculatePower(16.0, -0.5), 1e-12);
    }

    @Test
    void calculatePower_withVerySmallFractionExponent3_returnsExpectedResult() {
        assertEquals(Math.pow(2.0, 0.1), powerCalculator.calculatePower(2.0, 0.1), 1e-12);
        assertEquals(Math.pow(5.0, -0.3), powerCalculator.calculatePower(5.0, -0.3), 1e-12);
    }

    @Test
    void calculatePower_withFractionalExponent4_returnsExpectedResult() {
        assertEquals(Math.pow(2.0, 0.1), powerCalculator.calculatePower(2.0, 0.1), 1e-12);
        assertEquals(Math.pow(5.0, 0.75), powerCalculator.calculatePower(5.0, 0.75), 1e-12);
        assertEquals(Math.pow(16.0, -0.5), powerCalculator.calculatePower(16.0, -0.5), 1e-12);
    }

    @Test
    void calculatePower_withVerySmallFractionExponent_returnsExpectedResult() {
        assertEquals(Math.pow(2.0, 0.0001), powerCalculator.calculatePower(2.0, 0.0001), 1e-12);
        assertEquals(Math.pow(5.0, -0.0003), powerCalculator.calculatePower(5.0, -0.0003), 1e-12);
    }

    @Test
    void calculatePower_withVerySmallXValues_returnsExpectedResult() {
        assertEquals(1.0, powerCalculator.calculatePower(1e-10, 0.0), 1e-12);
        assertEquals(Math.pow(1e-6, 3.0), powerCalculator.calculatePower(1e-6, 3.0), 1e-12);
    }

    @Test
    void calculatePower_withNegativeSmallFractions_returnsExpectedResult() {
        assertEquals(Math.pow(-2.5, 2.5), powerCalculator.calculatePower(-2.5, 2.5), 1e-12);
        assertEquals(Math.pow(-2.0, 0.5), powerCalculator.calculatePower(-2.0, 0.5), 1e-12);
        assertEquals(Math.pow(-0.9, -0.9), powerCalculator.calculatePower(-0.9, -0.9), 1e-12);
    }

    @Test
    void calculatePower_withPositiveSmallFractions_returnsExpectedResult() {
        assertEquals(Math.pow(0.9, -5.0), powerCalculator.calculatePower(0.9, -5.0), 1e-12);
        assertEquals(Math.pow(10.0, -0.5), powerCalculator.calculatePower(10.0, -0.5), 1e-12);
        assertEquals(Math.pow(1.8, 0.5), powerCalculator.calculatePower(1.8, 0.5), 1e-12);
    }

    @Test
    void calculatePower_withNegativeLargeFractions_returnsExpectedResult() {
        assertEquals(Math.pow(-2.0, -2.0), powerCalculator.calculatePower(-2.0, -2.0), 1e-12);
        assertEquals(Math.pow(-1.5, -0.5), powerCalculator.calculatePower(-1.5, -0.5), 1e-12);
        assertEquals(Math.pow(-2.5, 0.5), powerCalculator.calculatePower(-2.5, 0.5), 1e-12);
    }

    @Test
    void calculatePower_withSpecialCases_returnsExpectedResult() {
        assertEquals(1.0, powerCalculator.calculatePower(0.0, 0.0), 1e-12);
        assertEquals(Double.POSITIVE_INFINITY, powerCalculator.calculatePower(0.0, -5.0), 1e-12);
        assertEquals(0.0, powerCalculator.calculatePower(0.001, 1000.0), 1e-12);
        assertEquals(0.0, powerCalculator.calculatePower(0.00001, 5.0), 1e-12);
    }

    @Test
    void calculatePower_withNegativeBasePositiveEvenExponent_returnsExpectedResult() {
        assertEquals(Math.pow(-3.0, 2.0), powerCalculator.calculatePower(-3.0, 2.0), 1e-12);
    }

    @Test
    void calculatePower_withNegativeBasePositiveOddExponent_returnsExpectedResult() {
        assertEquals(Math.pow(-3.0, 3.0), powerCalculator.calculatePower(-3.0, 3.0), 1e-12);
    }

    @Test
    void calculatePower_withNegativeBaseNegativeEvenExponent_returnsExpectedResult() {
        assertEquals(Math.pow(-3.0, -2.0), powerCalculator.calculatePower(-3.0, -2.0), 1e-12);
    }

    @Test
    void calculatePower_withNegativeBaseNegativeOddExponent_returnsExpectedResult() {
        assertEquals(Math.pow(-3.0, -3.0), powerCalculator.calculatePower(-3.0, -3.0), 1e-12);
    }

    @Test
    void calculatePower_withPositiveBaseLargeExponent_returnsExpectedResult() {
        assertEquals(Math.pow(2.0, 100.0), powerCalculator.calculatePower(2.0, 100.0), 1e-12);
    }

    @Test
    void calculatePower_withPositiveBaseSmallNegativeExponent_returnsExpectedResult() {
        assertEquals(Math.pow(2.0, -0.1), powerCalculator.calculatePower(2.0, -0.1), 1e-12);
    }

    @Test
    void calculatePower_withNegativeBaseSmallPositiveExponent_returnsExpectedResult() {
        assertEquals(Math.pow(-2.0, 0.1), powerCalculator.calculatePower(-2.0, 0.1), 1e-12);
    }

    @Test
    void calculatePower_withZeroBaseNegativeExponent_returnsExpectedResult() {
        assertEquals(Double.POSITIVE_INFINITY, powerCalculator.calculatePower(0.0, -1.0), 1e-12);
    }

    @Test
    void calculatePower_withZeroExponentPositiveBase_returnsExpectedResult() {
        assertEquals(1.0, powerCalculator.calculatePower(5.0, 0.0), 1e-12);
    }

    @Test
    void calculatePower_withPositiveBaseFractionalNegativeExponent_returnsExpectedResult() {
        assertEquals(Math.pow(2.0, -0.5), powerCalculator.calculatePower(2.0, -0.5), 1e-12);
    }

    @Test
    void calculatePower_withNegativeBasePositiveFractionalExponent_returnsNaN() {
        double base = -4.0;
        double exp = 0.5;
        double expected = Double.NaN; // Since negative base raised to a fractional exponent is undefined in real numbers
        double result = powerCalculator.calculatePower(base, exp);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculatePower_withNegativeBasePositiveFractionExponent_returnsNaN() {
        double x = -3.0;
        double y = 0.3333; // Cube root of -3, which is not a real number
        double expected = Double.NaN;
        double result = powerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, -10.0, 1e-12",
            "2.0, -0.0001, 1e-12",
            "1e-10, -1.0, 1e-12"
    })
    void calculatePower_withVariousExponents_returnsExpectedResult(double x, double y, double delta) {
        double expected = Math.pow(x, y);
        double result = powerCalculator.calculatePower(x, y);
        assertEquals(expected, result, delta);
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, 10.0, 1e-12",
            "4.0, 0.5, 1e-12",
            "5.0, 0.0, 1e-12",
            "0.0, 5.0, 1e-12",
            "1.1, 1e6, 1e-12",
            "2.0, 1e-15, 1e-12",
            "0.5, 3.0, 1e-12",
            "10.0, 0.0, 1e-12"
    })
     void calculatePower_withVariousExponentsPar_returnsExpectedResult(double base, double exp, double delta) {
        double expected = Math.pow(base, exp);
        double result = powerCalculator.calculatePower(base, exp);
        assertEquals(expected, result, delta);
    }

    @Test
    void calculatePower_withNegativeBaseEvenFractionExponent_returnsNaN() {
        double x = -16.0;
        double y = 0.25; // Fourth root of -16, which is not a real number
        double expected = Double.NaN;
        double result = powerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculatePower_withFractionalBaseNegativeExponent_returnsExpectedResult() {
        double x = 0.5;
        double y = -3.0;
        double expected = Math.pow(x, y);
        double result = powerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculatePower_withPositiveBaseZeroFractionalExponent_returnsExpectedResult() {
        double x = 10.0;
        double y = 0.0;
        double expected = Math.pow(x, y); // Any number to the power of zero is 1
        double result = powerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculatePower_withZeroBaseFractionalExponent_returnsZero() {
        double x = 0.0;
        double y = 0.5;
        double expected = 0.0; // Zero to any positive power is zero
        double result = powerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculatePower_withNegativeExponentFractionalBase_returnsExpectedResult() {
        double x = 0.1;
        double y = -1.0;
        double expected = Math.pow(x, y); // Should return 10
        double result = powerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }
}
