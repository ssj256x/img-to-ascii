package com.imgtoascii.services;

import java.awt.image.BufferedImage;

import static com.imgtoascii.util.Util.map;

public class ImgToAsciiArtService {

    private final char[] density;
    private final boolean invert;

    public ImgToAsciiArtService(char[] density, boolean invert) {
        this.density = density;
        this.invert = invert;
    }

    /**
     * This functions takes an image and converts it to an ASCII string representation of that image.
     * 1. Take the image as input.
     * 2. Extract the RGB values for each pixel of that image
     * 3. Convert the pixels to its grayscale by taking the average of the R, G and B values.
     * 4. Map the obtained grayscale RGB value to the length of the density string.
     * 5. The value obtained above will correspond to the index in the density character array.
     * 6. Append this character to a {@link StringBuilder}.
     * 7. Return the resulting string once the image has been processed.
     *
     * @param image - The image to be processed
     * @return The ASCII representation of that image
     */
    public String convertToAscii(BufferedImage image) {
        int densityLength = density.length;
        int width = image.getWidth();
        int height = image.getHeight();
        StringBuilder asciiArtString = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int avg = (r + g + b) / 3; // convert to grayscale

                int idx = this.invert ?
                        map(avg, 0, 0xff, 0, densityLength - 1) :
                        map(avg, 0, 0xff, densityLength - 1, 0);

                asciiArtString.append(this.density[idx]).append(" ");
            }
            asciiArtString.append("\n");
        }
        return asciiArtString.toString();
    }
}
