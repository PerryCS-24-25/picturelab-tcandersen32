import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture() {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param file the name of the file to create the picture from
     */
    public Picture(java.io.File file) {
        // let the parent class handle this fileName
        super(file);
    }

    /**
     * Constructor that takes the width and height
     * 
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int width, int height) {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(SimplePicture copyPicture) {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image) {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////
    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName()
            + " height " + getHeight()
            + " width " + getWidth();
        return output;

    }

    /**
     * Method to create a new picture by scaling the current picture by the given
     * x and y factors
     *
     * @param xFactor the amount to scale in x
     * @param yFactor the amount to scale in y
     * @return the resulting picture
     */
    public Picture scale(double xFactor, double yFactor) {
        // set up the scale transform
        AffineTransform scaleTransform = new AffineTransform();
        scaleTransform.scale(xFactor, yFactor);

        // create a new picture object that is the right size
        Picture result = new Picture((int) (getWidth() * xFactor),
                (int) (getHeight() * yFactor));

        // get the graphics 2d object to draw on the result
        Graphics graphics = result.getGraphics();
        Graphics2D g2 = (Graphics2D) graphics;

        // draw the current image onto the result image scaled
        g2.drawImage(this.getImage(), scaleTransform, null);

        result.setTitle(getTitle());
        return result;
    }

    /**
     * Method to set the blue to 0
     */
    public void zeroBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
            }
        }
    }

    /**
     * Removes all the red from this image.
     */
    public void zeroRed() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed(0);
            }
        }
    }

    /**
     * Removes all the green from this image.
     */
    public void zeroGreen() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
            }
        }   
    }
    
    /**
     * Keeps only blue, removing all red and green from this image
     */
    public void keepOnlyBlue() {
        zeroRed();
        zeroGreen();
    }

    /**
     * Keeps only red, removing all blue and green from this image
     */
    public void keepOnlyRed() {
        zeroBlue();
        zeroGreen();
    }
    
    /**
     * Keeps only green, removing all red and blue from this image
     */
    public void keepOnlyGreen() {
        zeroRed();
        zeroBlue();
    }

    /**
     * Creates the negative of the image, reversing red, green, and blue components
     */
    public void negate() {
        Pixel[] pixels = getPixels();
        for(Pixel pixel : pixels){
            pixel.setRed(255 - pixel.getRed());
            pixel.setBlue(255 - pixel.getBlue());
            pixel.setGreen(255 - pixel.getGreen());
        }
    }

    /**
     * Creates the grayscale of an image, setting each color value of a pixel to the average color value
     */
    public void grayscale() {
        Pixel[] pixels = getPixels();
        for(Pixel pixel : pixels){
            int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
            pixel.setRed(avg);
            pixel.setBlue(avg);
            pixel.setGreen(avg);
        }
    }

    /**
     * Creates the grayscale of an image, setting each color value of a pixel to the average color value
     */
    public void blackAndWhite() {
        Pixel[] pixels = getPixels();
        for(Pixel pixel : pixels){
            int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
            if(avg > 127){
                pixel.setRed(255);
                pixel.setBlue(255);
                pixel.setGreen(255);
            } else {
                pixel.setRed(0);
                pixel.setBlue(0);
                pixel.setGreen(0);
            }
        }
    }

    /**
     * Method that mirrors the picture around a horizontal mirror in the center of
     * the picture from top to bottom
     */
    public void top2Bottom() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = 0; row < height/2; row++) {
            for (int col = 0; col < width; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture around a vertical mirror in the center of
     * the picture from right to left
     */
    public void right2Left() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }


    /**
     * Method that mirrors the picture around a horizontal mirror in the center of
     * the picture from bottom to top
     */
    public void bottom2Top() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int height = pixels.length;
        int width = pixels[0].length;
        for (int row = 0; row < height/2; row++) {
            for (int col = 0; col < width; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[height - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture around a vertical mirror in the center of
     * the picture from left to right
     */
    public void left2Right() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * Method that mirrors the picture around a diagonal mirror 
     */
    public void mirrorDiagonal() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel pixel1 = null;
        Pixel pixel2 = null;
        int height = pixels.length;
        int width = pixels[0].length;
        if(width < height){
            height = width;
        }
        for (int row = 0; row < height; row++) {
            for (int col = row + 1; col < height; col++) {
                pixel1 = pixels[row][col];
                pixel2 = pixels[col][row];
                pixel1.setColor(pixel2.getColor());
            }
        }
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorTemple() {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {
                leftPixel = pixels[row][col];
                count++;
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        System.out.println(count);
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorArms() {
        int mirrorPoint = 203;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 156; row < 177; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 103; col < 160; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }

        // loop through the rows
        for (int row = 177; row < 196; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 103; col < 160; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorGull() {
        int mirrorPoint = 363;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 228; row < 325; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 239; col < 345; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }

    }


    /**
     * Encodes a message into a picture
     * @param message a Black and White message to encode into a picture
     */
    public void encode(Picture message){
        message.blackAndWhite();
        Pixel[][] pixels = this.getPixels2D();
        Pixel[][] messagePixels = message.getPixels2D();
        for(int row = 0; row < pixels.length; row++){
            for(int col = 0; col < pixels[row].length; col++){
                pixels[row][col].setRed((pixels[row][col].getRed() / 2) * 2);
            }
        }

        for(int row = 0; row < messagePixels.length; row++){
            for(int col = 0; col < messagePixels[0].length; col++){
                if(row < pixels.length && col < pixels[0].length && messagePixels[row][col].getRed() == 0){
                    pixels[row][col].setRed(pixels[row][col].getRed() + 1);
                }
            }
        }
    }

    /**
     * decodes a message hidden in a picture
     * @return the Black and White message hidden in the picture
     */
    public Picture decode(){
        Picture message = new Picture(this);
        Pixel[][] pixels = this.getPixels2D();
        Pixel[][] messagePixels = message.getPixels2D();

        for(int row = 0; row < pixels.length; row++){
            for(int col = 0; col < pixels[row].length; col++){
                if(pixels[row][col].getRed() % 2 == 1){
                    messagePixels[row][col].setRed(0);
                    messagePixels[row][col].setBlue(0);
                    messagePixels[row][col].setGreen(0);
                } else {
                    messagePixels[row][col].setRed(255);
                    messagePixels[row][col].setBlue(255);
                    messagePixels[row][col].setGreen(255);
                }
            }
        }

        return message;
    }

    /**
     * copy from the passed fromPic to the specified startRow and startCol in the
     * current picture
     *
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, int startRow, int startCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
        fromRow < fromPixels.length
        && toRow < toPixels.length;
        fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
            fromCol < fromPixels[0].length
            && toCol < toPixels[0].length;
            fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * 
     * @param fromPic The source image we are copying from
     * @param destRow the start row to copy to
     * @param destCol the start col to copy to
     * @param fromRow The start row of fromPic
     * @param fromCol The start col of fromPic
     * @param w Width of the area we wish to copy.
     * @param h Height of the area we wish to copy.
     */
    public void copy(Picture fromPic,int destRow, int destCol, int fromRow, int fromCol, int w, int h) {
        //TODO: Write and test this method
    }

    /**
     * Method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist) {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0;
            col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor)
                > edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /**
     * Fixes underwater images by expanding color range
     */
    public void fixUnderwater() {
        int minR = 255;
        int maxR = 0;
        int minG = 255;
        int maxG = 0;
        int minB = 255;
        int maxB = 0;

        Pixel[] pixels = getPixels();
        for(Pixel pixel : pixels){
            if(pixel.getRed() < minR)
                minR = pixel.getRed();
            if(pixel.getRed() > maxR)
                maxR = pixel.getRed();

            if(pixel.getGreen() < minG)
                minG = pixel.getGreen();
            if(pixel.getGreen() > maxG)
                maxG = pixel.getGreen();

            if(pixel.getBlue() < minB)
                minB = pixel.getBlue();
            if(pixel.getBlue() > maxB)
                maxB = pixel.getBlue();
        }

        int rangeR = maxR - minR;
        int rangeG = maxG - minG;
        int rangeB = maxB - minB;

        for(Pixel pixel : pixels){
            pixel.setRed((pixel.getRed() - minR) * 255 / rangeR);
            pixel.setBlue((pixel.getBlue() - minB) * 255 / rangeB);
            pixel.setGreen((pixel.getGreen() - minG) * 255 / rangeG);
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) {
        Picture snowman = new Picture("snowman.jpg");
        snowman.explore();
        snowman.mirrorArms();
        snowman.explore();
    }

} // this } is the end of class Picture, put all new methods before this
