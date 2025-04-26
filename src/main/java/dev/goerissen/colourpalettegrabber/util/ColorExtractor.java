package dev.goerissen.colourpalettegrabber.util;

import javafx.scene.paint.Color;

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
     * Extract all colors from the image and return a map with the color as key and the count as value.<br/>
     * This method iterates through each pixel of the image and counts the occurrences of each color.
     * @param image The image to extract colors from.
     * @throws IllegalArgumentException if the image is null.
     * @return A map with the color as key and the count as value.
     */
    public static Map<Integer, Integer> extractAllColors(BufferedImage image) throws IllegalArgumentException{

        if(image == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }

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
                .filter(rgb -> !ColorUtils.isTooSimilar(rgb, selectedColors, minDistance)) // Filter out colors that are too similar to already selected colors
                .limit(colorCount)
                .map(rgb -> {
                    // RGB to JavaFX Color
                    Color color = Color.rgb((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
                    selectedColors.add(color); // Save
                    return color;
                }).toList();
                //.collect(Collectors.toList()); // CC since Java > Java 16
    }


}
