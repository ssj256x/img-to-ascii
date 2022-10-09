# Image to Ascii
A simple CLI app to convert any image to its corresponding ASCII Art.

```sh
Usage: img2ascii [-hiV] [-oar] [-c=<densityCharset>] -p=<path>
                 [-rh=<resizeHeight>] [-rw=<resizeWidth>]
  -c, --characters=<densityCharset>
                      The set of characters used to generate the ASCII art.
                        Sort characters according to density
  -h, --help          Show this help message and exit.
  -i, --invert        Invert characters in ASCII art
      -oar, --override-aspect-ratio
                      To override default aspect ratio while resizing image
  -p, --path=<path>   Path of image file
      -rh, --resize-height=<resizeHeight>
                      To resize image with given height
      -rw, --resize-width=<resizeWidth>
                      To resize image with given width
  -V, --version       Print version information and exit.
```