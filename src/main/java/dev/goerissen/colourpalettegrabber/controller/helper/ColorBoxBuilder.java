package dev.goerissen.colourpalettegrabber.controller.helper;

import dev.goerissen.colourpalettegrabber.util.ColorTextFX;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Collection;

/**
 * Utility class to create a color box with buttons for e.g. RGB, CMYK, and HEX values.
 *
 * @version 1.0.0
 * @since 2025-04-26
 * @author robingorissen
 */
public class ColorBoxBuilder {
    private ColorBoxBuilder() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates a color box with color buttons for RGB, CMYK, and HEX values.
     * @param color The color to be displayed in the box.
     * @param width The width of the box.
     * @param height The height of the box.
     * @param colorFormats The collection of color formats to be displayed.
     * @return A StackPane containing the color box and buttons.
     */
    public static StackPane createColorBox(Color color, double width, double height, Collection<String> colorFormats) {
        StackPane pane = new StackPane();

        // Color
        Rectangle rect = new Rectangle(width, height+25*colorFormats.size(), color);
        pane.getChildren().add(rect);

        VBox textBox = new VBox(10);
        textBox.setAlignment(Pos.CENTER);

        for (String format : colorFormats) {
            Text text = ColorTextFX.getColorText(color, format);
            textBox.getChildren().add(text);
        }
        pane.getChildren().add(textBox);
        return pane;
    }

}
