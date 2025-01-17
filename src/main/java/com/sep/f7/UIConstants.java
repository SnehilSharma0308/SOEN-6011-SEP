package com.sep.f7;

/**
 * UIConstants class stores all the constant values
 * used in the PowerCalculatorUI class.
 * This promotes code reusability and cleaner code structure.
 */
public final class UIConstants {

    /**
     * Private constructor to prevent instantiation.
     */
    private UIConstants() {
        // Prevent instantiation
    }

    /**
     * Speaker image location.
     */
    public static final String SPEAKER_IMAGE = "/com/sep/f7/speaker1.png";

    /**
     * Regular style sheet location.
     */
    public static final String STYLE_SHEET = "/com/sep/f7/style.css";

    /**
     * High contrast sheet location.
     */
    public static final String HIGH_CONTRAST = "/com/sep/f7/high-contrast.css";

    /**
     * Space between result box.
     */
    public static final double RESULT_SPACE = 10;

    /**
     * Size of the asterisk for GUI.
     */
    public static final double ASTERISK_SIZE = 12;

    /** Font size for the title label. */
    public static final int TITLE_FONT_SIZE = 20;

    /** Font size for the equation label. */
    public static final int EQUATION_FONT_SIZE = 24;

    /** Font size for the labels. */
    public static final int LABEL_FONT_SIZE = 16;

    /** Font size for the result label. */
    public static final int RESULT_FONT_SIZE = 16;

    /** Font size for the error label. */
    public static final int ERROR_FONT_SIZE = 16;

    /** Padding for the grid layout. */
    public static final int GRID_PADDING = 20;

    /** Vertical gap for the grid layout. */
    public static final int GRID_VGAP = 15;

    /** Horizontal gap for the grid layout. */
    public static final int GRID_HGAP = 10;

    /** Spacing between buttons. */
    public static final int BUTTON_SPACING = 20;

    /** Padding for the calculator layout. */
    public static final int CALCULATOR_PADDING = 20;

    /** Minimum width for the result label. */
    public static final int RESULT_MIN_WIDTH = 120;

    /** Minimum height for the result label. */
    public static final int RESULT_MIN_HEIGHT = 40;

    /** Width of the main scene. */
    public static final int SCENE_WIDTH = 500;

    /** Height of the main scene. */
    public static final int SCENE_HEIGHT = 600;

    /**
     * CSS class name for marking text fields with invalid input.
     */
    public static final String TEXT_FIELD_INVALID = "text-field-invalid";

    /** Default font used in the UI. */
    public static final String DEFAULT_FONT = "Arial";
}
