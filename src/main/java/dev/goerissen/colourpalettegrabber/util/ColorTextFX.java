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


    public static final String RGB = "RGB";
    public static final String CMYK = "CMYK";
    public static final String HEX = "HEX";
    public static final String LAB = "LAB";
    public static final String HSL = "HSL";
    public static final String XYZ = "XYZ";
    public static final String LUV = "LUV";
    public static final String HWB = "HWB";
    public static final String NCOL = "Ncol";


    private ColorTextFX() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates a Text object representing the color in the specified format.<br/>
     * @param format The format to convert the color to. Possible values are RGB, CMYK, HEX, LAB, HSL, XYZ, LUV, HWB, NCOL.
     * @param color The color to convert to text.
     * @return A Text object with the color value in the specified format.
     * @throws IllegalArgumentException if the format is unknown.
     */
    public static Text getColorText(Color color, String format) {
        switch (format) {
            case RGB:
                return getRGB(color);
            case CMYK:
                return getCMYK(color);
            case HEX:
                return getHex(color);
            case LAB:
                return getLAB(color);
            case HSL:
                return getHSL(color);
            case XYZ:
                return getXYZ(color);
            case LUV:
                return getLUV(color);
            case HWB:
                return getHWB(color);
            default:
                throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

    /**
     * Creates a Text object representing the RGB value of the given color.<br/>
     * The text is clickable and copies the RGB value to the clipboard when clicked.
     * @param color The color to convert to RGB text.
     * @return A Text object with the RGB value of the color.
     */
    public static Text getRGB(Color color) {
        double[] rgb = ColorCalculator.calculateRGB(color.getRed(), color.getGreen(), color.getBlue());

        Text rgbText = new Text(String.format("RGB(%d, %d, %d)", (int) rgb[0], (int) rgb[1], (int) rgb[2]));
        rgbText.setFill(color.invert());
        rgbText.setCursor(Cursor.HAND);

        rgbText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(rgbText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(rgbText, "RGB value copied to clipboard!");
        });

        return rgbText;
    }

    /**
     * Creates a Text object representing the CMYK value of the given color.<br/>
     * The text is clickable and copies the CMYK value to the clipboard when clicked.
     * @param color The color to convert to CMYK text.
     * @return A Text object with the CMYK value of the color.
     */
    public static Text getCMYK(Color color) {
        // (CMYK)
        double[] cmyk = ColorCalculator.calculateCMYK(color.getRed(), color.getGreen(), color.getBlue());

        Text cmykText = new Text(String.format("CMYK(%.2f%%, %.2f%%, %.2f%%, %.2f%%)", cmyk[0], cmyk[1], cmyk[2], cmyk[3]));
        cmykText.setFill(color.invert());
        cmykText.setCursor(Cursor.HAND);

        cmykText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(cmykText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(cmykText, "CMYK value copied to clipboard!");
        });

        return cmykText;
    }

    /**
     * Creates a Text object representing the HEX value of the given color.<br/>
     * The text is clickable and copies the HEX value to the clipboard when clicked.
     * @param color The color to convert to HEX text.
     * @return A Text object with the HEX value of the color.
     */
    public static Text getHex(Color color) {
        double[] hex = ColorCalculator.calculateHEX(color.getRed(), color.getGreen(), color.getBlue());

        Text hexText = new Text(String.format("#%02X%02X%02X", (int)hex[0], (int)hex[1], (int)hex[2]));
        hexText.setFill(color.invert());
        hexText.setCursor(Cursor.HAND);
        hexText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(hexText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(hexText, "HEX value copied to clipboard!");
        });

        return hexText;
    }

    /**
     * Creates a Text object representing the LAB value of the given color.<br/>
     * The text is clickable and copies the LAB value to the clipboard when clicked.
     * @param color The color to convert to LAB text.
     * @return A Text object with the LAB value of the color.
     */
    public static Text getLAB(Color color) {
        double[] lab = ColorCalculator.calculateLAB(color.getRed(), color.getGreen(), color.getBlue());

        Text labText = new Text(String.format("LAB(%.2f, %.2f, %.2f)", lab[0], lab[1], lab[2]));
        labText.setFill(color.invert());
        labText.setCursor(Cursor.HAND);

        labText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(labText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(labText, "LAB value copied to clipboard!");
        });

        return labText;
    }

    /**
     * Creates a Text object representing the HSL value of the given color.<br/>
     * The text is clickable and copies the HSL value to the clipboard when clicked.
     * @param color The color to convert to HSL text.
     * @return A Text object with the HSL value of the color.
     */
    public static Text getHSL(Color color) {
        double[] hsl = ColorCalculator.calculateHSL(color.getRed(), color.getGreen(), color.getBlue());

        Text hslText = new Text(String.format("HSL(%.2f, %.2f%%, %.2f%%)", hsl[0], hsl[1], hsl[2]));
        hslText.setFill(color.invert());
        hslText.setCursor(Cursor.HAND);

        hslText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(hslText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(hslText, "HSL value copied to clipboard!");
        });

        return hslText;
    }

    /**
     * Creates a Text object representing the XYZ value of the given color.<br/>
     * The text is clickable and copies the XYZ value to the clipboard when clicked.
     * @param color The color to convert to XYZ text.
     * @return A Text object with the XYZ value of the color.
     */
    public static Text getXYZ(Color color) {
        double[] xyz = ColorCalculator.calculateXYZ(color.getRed(), color.getGreen(), color.getBlue());

        Text xyzText = new Text(String.format("XYZ(%.2f, %.2f, %.2f)", xyz[0], xyz[1], xyz[2]));
        xyzText.setFill(color.invert());
        xyzText.setCursor(Cursor.HAND);

        xyzText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(xyzText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(xyzText, "XYZ value copied to clipboard!");
        });

        return xyzText;
    }

    /**
     * Creates a Text object representing the LUV value of the given color.<br/>
     * The text is clickable and copies the LUV value to the clipboard when clicked.
     * @param color The color to convert to LUV text.
     * @return A Text object with the LUV value of the color.
     */
    public static Text getLUV(Color color) {
        double[] luv = ColorCalculator.calculateLUV(color.getRed(), color.getGreen(), color.getBlue());

        Text luvText = new Text(String.format("Luf(%.2f, %.2f, %.2f)", luv[0], luv[1], luv[2]));
        luvText.setFill(color.invert());
        luvText.setCursor(Cursor.HAND);

        luvText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(luvText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(luvText, "LUV value copied to clipboard!");
        });

        return luvText;
    }

    /**
     * Creates a Text object representing the HWB value of the given color.<br/>
     * The text is clickable and copies the HWB value to the clipboard when clicked.
     * @param color The color to convert to HWB text.
     * @return A Text object with the HWB value of the color.
     */
    public static Text getHWB(Color color) {
        double[] hwb = ColorCalculator.calculateHWB(color.getRed(), color.getGreen(), color.getBlue());

        Text hwbText = new Text(String.format("HWB(%.2f, %.2f%%, %.2f%%)", hwb[0], hwb[1], hwb[2]));
        hwbText.setFill(color.invert());
        hwbText.setCursor(Cursor.HAND);

        hwbText.setOnMouseClicked(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(hwbText.getText());
            clipboard.setContent(content);

            PopUp.showPopUpTooltip(hwbText, "HWB value copied to clipboard!");
        });

        return hwbText;
    }
}
