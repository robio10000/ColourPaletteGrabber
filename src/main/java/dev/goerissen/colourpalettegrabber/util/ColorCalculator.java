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
     * Use the Euclidean distance formula to determine the distance between two colors in RGB space.<br/>
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

    public static double[] calculateRGB(double r, double g, double b) {
        return new double[]{r*255, g*255, b*255};
    }

    /**
     * Calculates the CMYK values from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @return An array containing the CMYK values in the order of C, M, Y, K.
     */
    public static double[] calculateCMYK(double r, double g, double b) {
        double k = 1 - Math.max(r, Math.max(g, b));
        double c = k<1 ? (1 - r - k) / (1 - k) : 0;
        double m = k<1 ? (1 - g - k) / (1 - k) : 0;
        double y = k<1 ? (1 - b - k) / (1 - k) : 0;

        return new double[]{c * 100, m * 100, y * 100, k * 100};
    }

    /**
     * Calculates the HEX values from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @return An array containing the HEX values in the order of R, G, B.
     */
    public static double[] calculateHEX(double r, double g, double b) {
        double[] hex = new double[3];
        hex[0] = Math.round(r * 255);
        hex[1] = Math.round(g * 255);
        hex[2] = Math.round(b * 255);
        return hex;
    }


    /**
     * Calculates the LAB values from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @return An array containing the LAB values in the order of L, A, B.
     */
    public static double[] calculateLAB(double r, double g, double b) {
        final double Xn = 95.047;
        final double Yn = 100.000;
        final double Zn = 108.883;

        double[] xyz = calculateXYZ(r, g, b);
        double x = xyz[0] / Xn;
        double y = xyz[1] / Yn;
        double z = xyz[2] / Zn;

        double l = (y > 0.008856) ? (116 * Math.cbrt(y)) - 16 : 903.3 * y;
        double a = 500 * (f(x) - f(y));
        double bVal = 200 * (f(y) - f(z));

        return new double[]{l, a, bVal};
    }

    /**
     * Helper function for LAB calculation.
     * @param t The value to be transformed.
     * @return The transformed value.
     */
    private static double f(double t) {
        return (t > 0.008856) ? Math.cbrt(t) : (7.787 * t) + (16.0 / 116.0);
    }

    /**
     * Calculates the HSL values from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @return An array containing the HSL values in the order of H, S, L.
     */
    public static double[] calculateHSL(double r, double g, double b) {
        double min = Math.min(r, Math.min(g, b));
        double max = Math.max(r, Math.max(g, b));
        double delta = max - min;

        double h = 0;
        if (delta != 0) {
            if (max == r) {
                h = ((g - b) / delta) % 6;
            } else if (max == g) {
                h = ((b - r) / delta) + 2;
            } else {
                h = ((r - g) / delta) + 4;
            }
            h *= 60;
            if (h < 0) h += 360;
        }

        double l = (max + min) / 2;
        double s = (delta == 0) ? 0 : delta / (1 - Math.abs(2 * l - 1));

        return new double[]{h, s * 100, l * 100};
    }

    /**
     * Calculates the LUV values from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @return An array containing the LUV values in the order of L, U, V.
     */
    public static double[] calculateLUV(double r, double g, double b) {
        final double Yn = 100.000;

        double[] xyz = calculateXYZ(r, g, b);
        double x = xyz[0];
        double y = xyz[1];
        double z = xyz[2];

        double uPrime = (4 * x) / (x + 15 * y + 3 * z);
        double vPrime = (9 * y) / (x + 15 * y + 3 * z);

        double l = (y / Yn > 0.008856) ? (116 * Math.cbrt(y / Yn)) - 16 : 903.3 * (y / Yn);
        double u = 13 * l * (uPrime - 0.19783000664283);
        double v = 13 * l * (vPrime - 0.46831999493879);

        return new double[]{l, u, v};
    }

    /**
     * Calculates the HWB values from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @return An array containing the HWB values in the order of H, W, B.
     */
    public static double[] calculateHWB(double r, double g, double b) {
        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));

        double h = calculateHue(r, g, b, max, min);
        double w = min;
        double bl = 1 - max;

        return new double[]{h, w * 100, bl * 100};
    }

    /**
     * Calculates the hue value from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @param max Maximum value among R, G, B
     * @param min Minimum value among R, G, B
     * @return The hue value in degrees (0-360).
     */
    private static double calculateHue(double r, double g, double b, double max, double min) {
        double delta = max - min;
        double h = 0;
        if (delta != 0) {
            if (max == r) {
                h = ((g - b) / delta) % 6;
            } else if (max == g) {
                h = ((b - r) / delta) + 2;
            } else {
                h = ((r - g) / delta) + 4;
            }
            h *= 60;
            if (h < 0) h += 360;
        }
        return h;
    }

    /**
     * Calculates the XYZ values from RGB values.
     * @param r Red value (0-1)
     * @param g Green value (0-1)
     * @param b Blue value (0-1)
     * @return An array containing the XYZ values in the order of X, Y, Z.
     */
    public static double[] calculateXYZ(double r, double g, double b) {
        r = (r > 0.04045) ? Math.pow((r + 0.055) / 1.055, 2.4) : r / 12.92;
        g = (g > 0.04045) ? Math.pow((g + 0.055) / 1.055, 2.4) : g / 12.92;
        b = (b > 0.04045) ? Math.pow((b + 0.055) / 1.055, 2.4) : b / 12.92;

        double x = (r * 0.4124564 + g * 0.3575761 + b * 0.1804375) * 100;
        double y = (r * 0.2126729 + g * 0.7151522 + b * 0.0721750) * 100;
        double z = (r * 0.0193339 + g * 0.1191920 + b * 0.9503041) * 100;

        return new double[]{x, y, z};
    }

}
