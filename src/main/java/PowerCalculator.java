/**
 * A simple calculator for computing power functions (x^y) with special handling for edge cases.
 */
public class PowerCalculator {

    /**
     * Constructs a new PowerCalculator.
     * <p>
     * This constructor initializes a new instance of the PowerCalculator class,
     * which provides methods for computing power functions (x^y) with special
     * handling for edge cases such as 0^0 and negative exponents.
     */
    public PowerCalculator(){

    }

    /**
     * Calculates x raised to the power of y (x^y).
     * Handles special cases such as zero base and fractional exponents.
     * Uses helper methods for different exponent types.
     *
     * @param x The base value.
     * @param y The exponent value.
     * @return The result of x raised to the power of y.
     */
    public static double calculatePower(double x, double y) {
        if (x == 0.0) {
            if (y == 0.0) {
                return 1.0;
            } else if (y < 0.0) {
                return Double.POSITIVE_INFINITY;
            } else {
                return 0.0;
            }
        }

        if (y % 1 == 0) {
            // Integer exponent
            return calculatePowerIntegerExponent(x, (int) y);
        } else {
            // Fractional or decimal exponent
            if (x < 0.0 && y % 1 != 0) {
                return Double.NaN; // Return NaN for complex result
            }
            return exp(y * log(abs(x)));
        }
    }

    /**
     * Calculates x raised to the power of an integer exponent (x^y) where y is an integer.
     * Uses iterative multiplication for positive exponents and division for negative exponents.
     *
     * @param x The base value.
     * @param y The integer exponent value.
     * @return The result of x raised to the power of y.
     */
    public static double calculatePowerIntegerExponent(double x, int y) {
        double result = 1.0;
        int absExponent = abs(y);

        for (int i = 0; i < absExponent; i++) {
            result *= x;
        }

        return y < 0 ? 1.0 / result : result;
    }

    /**
     * Computes the natural logarithm (ln) of a given positive double value using the Taylor series expansion.
     * Handles precision and edge cases for logarithm calculation.
     *
     * @param x The value for which logarithm is to be calculated.
     * @return The natural logarithm of x.
     * @throws IllegalArgumentException If x is non-positive.
     */
    public static double log(double x) {
        if (x <= 0.0) {
            throw new IllegalArgumentException("Logarithm undefined for non-positive values");
        }

        double result = 0.0;
        double term;
        int n = 1;
        double precision = 1e-15;

        // Using Taylor series for ln(1 + z) where z = (x-1)/(x+1)
        if (x > 0 && x < 1) {
            x = 1 / x;
            term = (x - 1) / (x + 1);
            double z = term;
            while (abs(term) > precision) {
                result += 2 * term / n;
                term *= z * z;
                n += 2;
            }
            return -result;
        } else {
            term = (x - 1) / (x + 1);
            double z = term;
            while (abs(term) > precision) {
                result += 2 * term / n;
                term *= z * z;
                n += 2;
            }
            return result;
        }
    }

    /**
     * Computes the exponential function (e^x) using Taylor series expansion.
     * Calculates the sum of the series until the terms become smaller than a specified precision.
     *
     * @param x The exponent value.
     * @return The result of e raised to the power of x.
     */
    public static double exp(double x) {
        double result = 1.0;
        double term = 1.0;
        int n = 1;
        double precision = 1e-15;

        while (abs(term) > precision) {
            term *= x / n;
            result += term;
            n++;
        }

        return result;
    }

    /**
     * Computes the absolute value of an integer.
     *
     * @param value The integer value.
     * @return The absolute value of the integer.
     */
    public static int abs(int value) {
        return (value < 0) ? -value : value;
    }

    /**
     * Computes the absolute value of a double.
     *
     * @param value The double value.
     * @return The absolute value of the double.
     */
    public static double abs(double value) {
        return (value < 0.0) ? -value : value;
    }
}
