package com.sep.f7;

/**
 * com.sep.f7.Launcher class serves as the entry point for the application.
 * It launches the com.sep.f7.PowerCalculatorUI JavaFX application.
 */
public final class Launcher {

    /**
     * Private constructor to prevent instantiation.
     */
    private Launcher() {
        // Prevent instantiation
    }

    /**
     * The main method serves as the entry point of the application.
     * It launches the com.sep.f7.PowerCalculatorUI class.
     *
     * @param args command line arguments (not used)
     */
    public static void main(final String[] args) {
        PowerCalculatorUI.main(args);
    }
}
