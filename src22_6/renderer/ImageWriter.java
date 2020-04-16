package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Class allow to create jpg-file and to edit its name, path
 * width, height and number of pixels. It also allow to decide
 * the color of each pixel on the picture.
 */
public class ImageWriter {

    private int _imageWidth, _imageHeight;
    private int _Nx, _Ny;
    public final String PROJECT_PATH = System.getProperty("user.dir");
    private BufferedImage _image;
    private String _imageName;

    // ***************** Constructors ********************** //
    public ImageWriter(String imageName, int width, int height, int Nx, int Ny) {
        _imageName = imageName;
        _imageWidth = width;
        _imageHeight = height;
        _Nx = Nx;
        _Ny = Ny;

        _image = new BufferedImage(_imageWidth, _imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Copy constructor
     */
    public ImageWriter(ImageWriter imageWriter) {
        this(imageWriter._imageName,
                imageWriter._imageWidth, imageWriter._imageHeight,
                imageWriter._Nx, imageWriter._Ny);
    }

    // ***************** Getters/Setters ********************** //

    public int getWidth() {
        return _imageWidth;
    }

    public int getHeight() {
        return _imageHeight;
    }

    public int getNy() {
        return _Ny;
    }

    public int getNx() {
        return _Nx;
    }

    public void setNy(int _Ny) {
        this._Ny = _Ny;
    }

    public void setNx(int _Nx) {
        this._Nx = _Nx;
    }


    // ************** Pixel-painting functions *************** //

    /**
     * Paint a specific position in the image by rgb color
     *
     * @param xIndex Row index of the image
     * @param yIndex Column index of the image
     * @param r      Red component of the color
     * @param g      Green component of the color
     * @param b      Blue component of the color
     */
    public void writePixel(int xIndex, int yIndex, int r, int g, int b) {
        int rgb = new Color(r, g, b).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);
    }

    /**
     * Paint a specific position in the image by rgb array
     *
     * @param xIndex   Row index of the image
     * @param yIndex   Column index of the image
     * @param rgbArray
     */
    public void writePixel(int xIndex, int yIndex, int[] rgbArray) {
        int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);
    }

    /**
     * Paint a specific position in the image by color
     *
     * @param xIndex Row index of the image
     * @param yIndex Column index of the image
     * @param color  This color will be set in the given index
     */
    public void writePixel(int xIndex, int yIndex, Color color) {
        _image.setRGB(xIndex, yIndex, color.getRGB());
    }

    /**
     * Paint by Color the entire pixel in the image
     *
     * @param row    Row number of the pixel in the viewplane
     * @param column Column number of the pixel in the viewplane
     */
    public void writeEntirePixel(int row, int column, Color color) {

        int pixelWidth = _imageWidth / _Nx;
        int pixelHeight = _imageHeight / _Ny;
        int startX = row * pixelWidth;
        int startY = column * pixelHeight;

        for (int i = startX; i < startX + pixelWidth; i++)
            for (int j = startY; j < startY + pixelHeight; j++) {
                _image.setRGB(j, i, color.getRGB());
            }
    }

    // **************** Other Operations ******************** //

    /**
     * Export the image to a jpg-file
     */
    public void writeToimage() {
        File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".png");

        try {
            ImageIO.write(_image, "png", ouFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print a grid on the image
     *
     * @param interval Number of jumps between 2 grid lines
     */
    public void printGrid(int interval) {
        for (int i = 0; i < _imageWidth; i += interval) {
            for (int j = 0; j < _imageHeight; j++) {
                this.writePixel(i, j, new java.awt.Color(255, 255, 255));
                this.writePixel(j, i, new java.awt.Color(255, 255, 255));
            }
        }
    }

}
