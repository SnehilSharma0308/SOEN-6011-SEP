package com.sep.f7;

/**
 * A simple calculator for computing power functions (x^y)
 * with special handling for edge cases.
 */
public class PowerCalculator {

    /**
     * The precision used for calculations in the Taylor series expansion.
     * This value determines the accuracy of the logarithm
     * and exponential computations.
     */
    private static final double PRECISION = 1e-15;

    /**
     * Constructs a new com.sep.f7.PowerCalculator.
     * <p>
     * This constructor initializes a new instance of the
     * com.sep.f7.PowerCalculator class which provides methods
     * for computing power functions (x^y) with special handling
     * for edge cases such as 0^0 and negative exponents.
     */
    public PowerCalculator() {
        //contructor for instantiang objects
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
    public double calculatePower(final double x, final double y) {
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
     * Calculates x raised to the power of an integer exponent
     * (x^y) where y is an integer.
     * Uses iterative multiplication for positive exponents and
     * division for negative exponents.
     *
     * @param x The base value.
     * @param y The integer exponent value.
     * @return The result of x raised to the power of y.
     */
    public double calculatePowerIntegerExponent(final double x, final int y) {
        double result = 1.0;
        int absExponent = abs(y);

        for (int i = 0; i < absExponent; i++) {
            result *= x;
        }

        return y < 0 ? 1.0 / result : result;
    }

    /**
     * Computes the natural logarithm (ln) of a given positive
     * double value using the Taylor series expansion.
     * Handles precision and edge cases for logarithm calculation.
     *
     * @param exp The value for which logarithm is to be calculated.
     * @return The natural logarithm of x.
     * @throws IllegalArgumentException If x is non-positive.
     */
    public double log(final double exp) {
        if (exp <= 0.0) {
            throw new IllegalArgumentException(
                    "Logarithm undefined for non-positive values"
            );
        }

        double result = 0.0;
        double term;
        int n = 1;
        double x = exp;
        // Using Taylor series for ln(1 + z) where z = (x-1)/(x+1)
        if (x > 0 && x < 1) {
            x = 1 / x;
            term = (x - 1) / (x + 1);
            double z = term;
            while (abs(term) > PRECISION) {
                result += 2 * term / n;
                term *= z * z;
                n += 2;
            }
            return -result;
        } else {
            term = (x - 1) / (x + 1);
            double z = term;
            while (abs(term) > PRECISION) {
                result += 2 * term / n;
                term *= z * z;
                n += 2;
            }
            return result;
        }
    }

    /**
     * Computes the exponential function (e^x) using Taylor series expansion.
     * Calculates the sum of the series until the terms become smaller
     * than a specified precision.
     *
     * @param x The exponent value.
     * @return The result of e raised to the power of x.
     */
    public double exp(final double x) {
        double result = 1.0;
        double term = 1.0;
        int n = 1;

        while (abs(term) > PRECISION) {
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
    public int abs(final int value) {
        return (value < 0) ? -value : value;
    }

    /**
     * Computes the absolute value of a double.
     *
     * @param value The double value.
     * @return The absolute value of the double.
     */
    public double abs(final double value) {
        return (value < 0.0) ? -value : value;
    }
}
