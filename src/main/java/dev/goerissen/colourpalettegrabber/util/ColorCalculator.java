package dev.goerissen.colourpalettegrabber.util;

/**
 * Utility class for color calculations.
 *
 * @version 1.0.0
 * @since 2025-04-25
 * @author robingorissen
 */
public class ColorCalculator {

    private ColorCalculator() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates the distance between two RGB colors.<br/>
     * Use the Euclidean distance formula to determine the distance between two colors in RGB space.
     * @param rgb1 The integer representation of the first color in RGB format.
     * @param rgb2 The integer representation of the second color in RGB format.
     * @return The Euclidean distance between the two colors in the RGB space.
     */
    public static double calculateColorDistance(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xFF;
        int g1 = (rgb1 >> 8) & 0xFF;
        int b1 = rgb1 & 0xFF;

        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = rgb2 & 0xFF;

        // Euclidean distance calculation
        return Math.sqrt(Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
    }
}
