package dev.goerissen.colourpalettegrabber.util;

import javafx.scene.paint.Color;

import java.util.List;

/**
 * Utility class for color-related operations.
 *
 * @version 1.0.0
 * @since 2025-04-26
 * @author robingorissen
 */
public class ColorUtils {

    private ColorUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a JavaFX Color to an int RGB value.
     * @param color The JavaFX Color to convert.
     * @return The int RGB value.
     */
    public static int colorToRgb(Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return (r << 16) | (g << 8) | b; // Kombiniert die RGB-Komponenten zu einem einzelnen int
    }

    /**
     * Checks if the given RGB value is too similar to any of the selected colors.
     * @param rgb The RGB value to check.
     * @param selectedColors The list of already selected colors.
     * @param minDistance The minimum distance between selected colors.
     * @return True if the color is too similar, false otherwise.
     */
    public static boolean isTooSimilar(int rgb, List<Color> selectedColors, double minDistance) {
        for (Color selectedColor : selectedColors) {
            // Calculate the distance to the current selected color
            int selectedRgb = colorToRgb(selectedColor);
            double distance = ColorCalculator.calculateColorDistance(rgb, selectedRgb);

            // If the distance is smaller than the minimum distance, the color is too similar
            if (distance < minDistance) {
                return true;
            }
        }
        return false;
    }
}
