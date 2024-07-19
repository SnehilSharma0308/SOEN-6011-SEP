import org.junit.Test;
import static org.junit.Assert.*;

public class PowerCalculatorTest {

    @Test
    public void testCalculatePowerPositiveExponent() {
        double x = 2.0;
        double y = 10.0;
        double expected = Math.pow(x, y);
        double result = PowerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    public void testCalculatePowerNegativeExponent() {
        double x = 2.0;
        double y = -10.0;
        double expected = Math.pow(x, y);
        double result = PowerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    public void testCalculatePowerFractionalExponent() {
        double x = 4.0;
        double y = 0.5;
        double expected = Math.pow(x, y);
        double result = PowerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    public void testCalculatePowerZeroExponent() {
        double x = 5.0;
        double y = 0.0;
        double expected = Math.pow(x, y);
        double result = PowerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    public void testCalculatePowerZeroBase() {
        double x = 0.0;
        double y = 5.0;
        double expected = Math.pow(x, y);
        double result = PowerCalculator.calculatePower(x, y);
        assertEquals(expected, result, 1e-12);
    }

    @Test
    public void testCalculatePowerLargeValues() {
        double x = 99.0;
        double y = 38.0;
        double result = PowerCalculator.calculatePower(x, y);
        assertTrue(result > 0);
    }

    @Test
    public void testCalculatePowerSmallValues() {
        double x = 0.2;
        double y = 1.4;
        double result = PowerCalculator.calculatePower(x, y);
        assertTrue(result > 0);
    }

    @Test
    public void testCalculatePowerPositiveIntegerExponent() {
        assertEquals(Math.pow(2.0, 10.0), PowerCalculator.calculatePower(2.0, 10.0), 1e-12);
        assertEquals(Math.pow(4.0, 3.0), PowerCalculator.calculatePower(4.0, 3.0), 1e-12);
        assertEquals(Math.pow(99.0, 0.0), PowerCalculator.calculatePower(99.0, 0.0), 1e-12);
        assertEquals(Math.pow(0.0, 0.0), PowerCalculator.calculatePower(0.0, 0.0), 1e-12);
        assertEquals(Math.pow(0.0, 5.0), PowerCalculator.calculatePower(0.0, 5.0), 1e-12);
        assertEquals(Math.pow(5.0, 0.0), PowerCalculator.calculatePower(5.0, 0.0), 1e-12);
    }

    @Test
    public void testCalculatePowerNegativeIntegerExponent() {
        assertEquals(0.0009765625, PowerCalculator.calculatePower(2.0, -10.0), 1e-12);
        assertEquals(0.015625, PowerCalculator.calculatePower(4.0, -3.0), 1e-12);
        assertEquals(1.0, PowerCalculator.calculatePower(99.0, -0.0), 1e-12);
        assertEquals(1.0, PowerCalculator.calculatePower(0.0, -0.0), 1e-12);
        assertEquals(Double.POSITIVE_INFINITY, PowerCalculator.calculatePower(0.0, -5.0), 1e-12);
        assertEquals(1.0, PowerCalculator.calculatePower(5.0, -0.0), 1e-12);
    }

    @Test
    public void testCalculatePowerFractionalExponent2() {
        assertEquals(Math.pow(4.0, 0.5), PowerCalculator.calculatePower(4.0, 0.5), 1e-12);
        assertEquals(Math.pow(16.0, -0.5), PowerCalculator.calculatePower(16.0, -0.5), 1e-12);
    }

    @Test
    public void testCalculatePowerVerySmallFractionExponent3() {
        assertEquals(Math.pow(2.0, 0.1), PowerCalculator.calculatePower(2.0, 0.1), 1e-12);
        assertEquals(Math.pow(5.0, -0.3), PowerCalculator.calculatePower(5.0, -0.3), 1e-12);
    }

    @Test
    public void testCalculatePowerFractionalExponent4() {
        assertEquals(Math.pow(2.0, 0.1), PowerCalculator.calculatePower(2.0, 0.1), 1e-12);
        assertEquals(Math.pow(5.0, 0.75), PowerCalculator.calculatePower(5.0, 0.75), 1e-12);
        assertEquals(Math.pow(16.0, -0.5), PowerCalculator.calculatePower(16.0, -0.5), 1e-12);
    }

    @Test
    public void testCalculatePowerVerySmallFractionExponent() {
        assertEquals(Math.pow(2.0, 0.0001), PowerCalculator.calculatePower(2.0, 0.0001), 1e-12);
        assertEquals(Math.pow(5.0, -0.0003), PowerCalculator.calculatePower(5.0, -0.0003), 1e-12);
    }

    @Test
    public void testCalculatePowerVerySmallXValues() {
        assertEquals(1.0, PowerCalculator.calculatePower(1e-10, 0.0), 1e-12);
        assertEquals(Math.pow(1e-6, 3.0), PowerCalculator.calculatePower(1e-6, 3.0), 1e-12);
    }

    @Test
    public void testCalculatePowerNegativeSmallFractions() {
        assertEquals(Math.pow(-2.5, 2.5), PowerCalculator.calculatePower(-2.5, 2.5), 1e-12);
        assertEquals(Math.pow(-2.0, 0.5), PowerCalculator.calculatePower(-2.0, 0.5), 1e-12);
        assertEquals(Math.pow(-0.9, -0.9), PowerCalculator.calculatePower(-0.9, -0.9), 1e-12);
    }

    @Test
    public void testCalculatePowerPositiveSmallFractions() {
        assertEquals(Math.pow(0.9, -5.0), PowerCalculator.calculatePower(0.9, -5.0), 1e-12);
        assertEquals(Math.pow(10.0, -0.5), PowerCalculator.calculatePower(10.0, -0.5), 1e-12);
        assertEquals(Math.pow(1.8, 0.5), PowerCalculator.calculatePower(1.8, 0.5), 1e-12);
    }

    @Test
    public void testCalculatePowerNegativeLargeFractions() {
        assertEquals(Math.pow(-2.0, -2.0), PowerCalculator.calculatePower(-2.0, -2.0), 1e-12);
        assertEquals(Math.pow(-1.5, -0.5), PowerCalculator.calculatePower(-1.5, -0.5), 1e-12);
        assertEquals(Math.pow(-2.5, 0.5), PowerCalculator.calculatePower(-2.5, 0.5), 1e-12);
    }

    @Test
    public void testCalculatePowerSpecialCases() {
        assertEquals(1.0, PowerCalculator.calculatePower(0.0, 0.0), 1e-12);
        assertEquals(Double.POSITIVE_INFINITY, PowerCalculator.calculatePower(0.0, -5.0), 1e-12);
        assertEquals(0.0, PowerCalculator.calculatePower(0.001, 1000.0), 1e-12);
        assertEquals(0.0, PowerCalculator.calculatePower(0.00001, 5.0), 1e-12);
    }

}
