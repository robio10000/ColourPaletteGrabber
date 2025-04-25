package dev.goerissen.colourpalettegrabber.util;

import javafx.scene.Cursor;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Utility class for color text representation in JavaFX.
 * It provides methods to create text representations of colors in RGB, CMYK, and HEX formats.
 *
 * @version 1.0.0
 * @since 2025-04-25
 * @author robingorissen
 */
public class ColorTextFX {

    private ColorTextFX() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates a Text object representing the RGB value of the given color.<br/>
     * The text is clickable and copies the RGB value to the clipboard when clicked.
     * @param color The color to convert to RGB text.
     * @return A Text object with the RGB value of the color.
     */
    public static Text getRGB(Color color) {
        // R:[0-255] G:[0-255] B:[0-255] (RGB)
        Text rgb = new Text(String.format("RGB(%d, %d, %d)",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255)));
        rgb.setFill(color.invert());
        rgb.setCursor(Cursor.HAND);

        rgb.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(rgb.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(rgb, "RGB value copied to clipboard!");
        });

        return rgb;
    }

    /**
     * Creates a Text object representing the CMYK value of the given color.<br/>
     * The text is clickable and copies the CMYK value to the clipboard when clicked.
     * @param color The color to convert to CMYK text.
     * @return A Text object with the CMYK value of the color.
     */
    public static Text getCMYK(Color color) {
        // (CMYK)
        double k = 1 - Math.max(color.getRed(), Math.max(color.getGreen(), color.getBlue()));
        double c = k<1 ? (1 - color.getRed() - k) / (1 - k) : 0;
        double m = k<1 ? (1 - color.getGreen() - k) / (1 - k) : 0;
        double y = k<1 ? (1 - color.getBlue() - k) / (1 - k) : 0;

        Text cmyk = new Text(String.format("C:%d M:%d Y:%d K:%d",
                (int)(c * 255),
                (int)(m * 255),
                (int)(y * 255),
                (int)(k * 255)));
        cmyk.setFill(color.invert());
        cmyk.setCursor(Cursor.HAND);
        cmyk.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(cmyk.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(cmyk, "CMYK value copied to clipboard!");
        });

        return cmyk;
    }

    /**
     * Creates a Text object representing the HEX value of the given color.<br/>
     * The text is clickable and copies the HEX value to the clipboard when clicked.
     * @param color The color to convert to HEX text.
     * @return A Text object with the HEX value of the color.
     */
    public static Text getHex(Color color) {
        // # RRGGBB (Hexadezimal)
        Text hex = new Text(String.format("#%02X%02X%02X",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255)));
        hex.setFill(color.invert());
        hex.setCursor(Cursor.HAND);
        hex.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(hex.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(hex, "HEX value copied to clipboard!");
        });

        return hex;
    }
}
