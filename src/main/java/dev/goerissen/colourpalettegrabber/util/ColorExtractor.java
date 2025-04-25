package dev.goerissen.colourpalettegrabber.util;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Utility class to extract colors from images.
 * It provides methods to extract all colors, dominant colors, and create color boxes with buttons.
 * @version 1.0.1
 * @since 2025-04-24
 * @author robingorissen
 */
public class ColorExtractor {

    private ColorExtractor() {
        // Private constructor to prevent instantiation
    }

    /**
     * Extract all colors from the image and return a map with the color as key and the count as value.
     * @param image The image to extract colors from.
     * @return A map with the color as key and the count as value.
     */
    public static Map<Integer, Integer> extractAllColors(BufferedImage image) {
        Map<Integer, Integer> colorMap = new HashMap<>();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                colorMap.put(rgb, colorMap.getOrDefault(rgb, 0) + 1);
            }
        }

        return colorMap;
    }

    /**
     * Extract the dominant colors ordered by domination from the image and return a limited list of colors.
     * @param image The image to extract colors from.
     * @param colorCount The limit of colors to extract.
     * @return A list of colors.
     */
    public static List<Color> extractDominantColors(BufferedImage image, int colorCount) {
        Map<Integer, Integer> colorMap = new HashMap<>();

        for (int y = 0; y < image.getHeight(); y += 1) {
            for (int x = 0; x < image.getWidth(); x += 1) {
                int rgb = image.getRGB(x, y);
                colorMap.put(rgb, colorMap.getOrDefault(rgb, 0) + 1);
            }
        }

        return colorMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(colorCount)
                .map(entry -> {
                    int rgb = entry.getKey();
                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;
                    return Color.rgb(r, g, b);
                }).toList();
    }

    /**
     * Creates a color box with color buttons for RGB, CMYK, and HEX values.
     * @param color The color to be displayed in the box.
     * @param width The width of the box.
     * @param height The height of the box.
     * @return A StackPane containing the color box and buttons.
     */
    public static StackPane createColorBoxWithColorButtons(Color color, double width, double height) {
        StackPane pane = new StackPane();

        // Color
        Rectangle rect = new Rectangle(width, height, color);
        pane.getChildren().add(rect);

        VBox textBox = new VBox(10);
        textBox.setAlignment(Pos.CENTER);

        // RGB([0-255], [0-255], [0-255]) (RGB)
        Text rgb = ColorTextFX.getRGB(color);
        textBox.getChildren().add(rgb);

        // (CMYK)
        Text cmyk = ColorTextFX.getCMYK(color);
        textBox.getChildren().add(cmyk);

        // # RRGGBB (Hexadezimal)
        Text hex = ColorTextFX.getHex(color);
        textBox.getChildren().add(hex);

        pane.getChildren().add(textBox);
        return pane;
    }

    /**
     * Extracts distinct colors from a color map, ensuring that the selected colors are not too similar to each other.
     * @param colorMap A map of colors with their frequency.
     * @param colorCount The number of distinct colors to extract.
     * @param minDistance The minimum distance between selected colors.
     * @return A list of distinct colors.
     */
    public static List<Color> extractDistinctColors(Map<Integer, Integer> colorMap, int colorCount, double minDistance) {
        List<Color> selectedColors = new ArrayList<>();

        // Sort colors by frequency (most frequent first)
        return colorMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .filter(rgb -> !isTooSimilar(rgb, selectedColors, minDistance)) // Filter out colors that are too similar to already selected colors
                .limit(colorCount)
                .map(rgb -> {
                    // RGB to JavaFX Color
                    Color color = Color.rgb((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
                    selectedColors.add(color); // Save
                    return color;
                }).toList();
                //.collect(Collectors.toList()); // CC since Java > Java 16
    }

    /**
     * Converts a JavaFX Color to an int RGB value.
     * @param color The JavaFX Color to convert.
     * @return The int RGB value.
     */
    private static int colorToRgb(Color color) {
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
    private static boolean isTooSimilar(int rgb, List<Color> selectedColors, double minDistance) {
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
