package com.imgtoascii.commands;

import picocli.CommandLine;
import com.imgtoascii.services.ImgToAsciiArtService;
import com.imgtoascii.util.DensityChars;
import com.imgtoascii.util.Util;

import java.awt.image.BufferedImage;

@CommandLine.Command(name = "img2ascii", version = "1.0", mixinStandardHelpOptions = true)
public class ImgToAsciiCommand implements Runnable {

    @CommandLine.Option(names = {"-p", "--path"}, description = "Path of image file", required = true)
    String path;

    @CommandLine.Option(names = {"-i", "--invert"}, description = "Invert characters in ASCII art")
    boolean invert = false;

    @CommandLine.Option(names = {"-rw", "--resize-width"}, description = "To resize image with given width")
    int resizeWidth = 0;

    @CommandLine.Option(names = {"-rh", "--resize-height"}, description = "To resize image with given height")
    int resizeHeight = 0;

    @CommandLine.Option(names = {"-oar", "--override-aspect-ratio"},
            description = "To override default aspect ratio while resizing image")
    boolean overrideAspectRatio;

    @CommandLine.Option(names = {"-c", "--characters"},
            description = "The set of characters used to generate the ASCII art. Sort characters according to density")
    String densityCharset = DensityChars.REDUCED_1;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ImgToAsciiCommand()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        ImgToAsciiArtService imgToAsciiArtService = new ImgToAsciiArtService(densityCharset.toCharArray(), invert);
        BufferedImage image;

        try {
            image = Util.read(path).orElseThrow(InstantiationException::new);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        }

        if (resizeHeight > 0 && resizeWidth > 0)
            image = Util.resize(image, resizeWidth, resizeHeight, overrideAspectRatio);

        System.out.println(imgToAsciiArtService.convertToAscii(image));
    }
}
