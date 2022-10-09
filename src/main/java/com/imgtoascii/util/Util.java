package com.imgtoascii.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Util {

    private Util() {
    }

    /**
     * Reads an image from a given url.
     *
     * @param url - The URL of the image to fetch
     * @return The fetched image
     */
    public static Optional<BufferedImage> read(String url) {
        File file;
        BufferedImage image;

        try {
            file = new File(url);
            image = ImageIO.read(file);
        } catch (IOException e) {
            return Optional.empty();
        }
        return Optional.of(image);
    }

    /**
     * Write the given image to the give location
     *
     * @param image - The image to be written
     * @param url   - The url to write to
     */
    public static void write(BufferedImage image, String url) {
        File file;
        try {
            file = new File(url);
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Maps a given number in range [a1, b1] to a number in range [a2, b2]
     *
     * @param val -  The value to be mapped
     * @param a1  - Lower end of range 1
     * @param b1  - Upper end of range 1
     * @param a2  - Lower end of range 2
     * @param b2  - Upper end of range 2
     * @return The mapped value
     */
    public static int map(int val, int a1, int b1, int a2, int b2) {
        return a2 + ((val - a1) * (b2 - a2)) / (b1 - a1);
    }

    /**
     * Resizes a given image to a new width and height. It also allows us to maintain or override the aspect ratio of
     * the given image.
     *
     * @param image               - The image to be resized
     * @param newWidth            - The new width
     * @param newHeight           - The new height
     * @param overrideAspectRatio - Maintain aspect ratio or not
     * @return The resized image
     */
    public static BufferedImage resize(BufferedImage image, int newWidth, int newHeight, boolean overrideAspectRatio) {

        if (!overrideAspectRatio) {
            double curWidth = image.getWidth();
            double curHeight = image.getHeight();
            double widthRatio = newWidth / curWidth;
            double heightRatio = newHeight / curHeight;
            double ratio = Math.min(widthRatio, heightRatio);
            newWidth = (int) (curWidth * ratio);
            newHeight = (int) (curHeight * ratio);
        }

        Image resultingImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
