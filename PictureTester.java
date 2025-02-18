/**
 * This class contains class (static) methods that will help you test the
 * Picture class methods. Uncomment the methods and the code in the main to
 * test.
 *
 * @author Barbara Ericson
 */
public class PictureTester {

    /**
     * Method to test the PictureExplorer
     */
    public static void testExplorer() {
        Picture creed = new Picture("creedOut.jpg");
        Picture bigCreed = creed.scale(4, 4);
        bigCreed.write("bigCreedOut.jpg");
        bigCreed.explore();   
    }

    /**
     * Method to test zeroBlue
     */
    public static void testZeroBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    /**
     * Method to test keepOnlyBlue
     */
    public static void testKeepOnlyBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyBlue();
        beach.explore();
    }

    /**
     * Method to test keepOnlyRed
     */
    public static void testKeepOnlyRed() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyRed();
        beach.explore();
    }

    /**
     * Method to test keepOnlyGreen
     */
    public static void testKeepOnlyGreen() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.keepOnlyGreen();
        beach.explore();
    }

    /**
     * Method to test mirrorVertical
     */
    public static void testMirrorVertical() {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.right2Left();
        caterpillar.explore();
    }

    /**
     * Method to test mirrorDiagonal
     */
    public static void testMirrorDiagonal() {
        Picture caterpillar = new Picture("beach.jpg");
        caterpillar.explore();
        caterpillar.mirrorDiagonal();
        caterpillar.explore();
    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorTemple() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorArms() {
        Picture temple = new Picture("snowman.jpg");
        temple.explore();
        temple.mirrorArms();
        temple.explore();
    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorGull() {
        Picture gull = new Picture("seagull.jpg");
        gull.explore();
        gull.mirrorGull();
        gull.explore();
    }


    /**
     * Method to test edgeDetection
     */
    public static void testEdgeDetection() {
        Picture swan = new Picture("swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }

    /**
     * Method to test scaling a picture
     */
    public static void testScale() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        Picture temple2 = temple.scale(0.5, 0.5);
        temple2.explore();
    }

    /**
     * Method to test negating a picture
     */
    public static void testNegate() {
        Picture mark = new Picture("blue-mark.jpg");
        mark.explore();
        mark.negate();
        mark.explore();
    }

    /**
     * Method to test grayscale
     */
    public static void testGrayscale() {
        Picture gorge = new Picture("gorge.jpg");
        gorge.explore();
        gorge.grayscale();
        gorge.explore();
    }

    /**
     * Method to test black and white
     */
    public static void testBlackAndWhite() {
        Picture gorge = new Picture("gorge.jpg");
        gorge.explore();
        gorge.blackAndWhite();
        gorge.explore();
    }

    

    /**
     * Method to test fixing underwater
     */
    public static void testFixUnderwater() {
        Picture water = new Picture("water.jpg");
        water.explore();
        water.fixUnderwater();
        water.explore();
    }

    /**
     * Method to test encoding and decoding
     */
    public static void testEncodeAndDecode() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        Picture message = new Picture("msg.jpg");
        message.explore();
        beach.encode(message);
        beach.explore();
        Picture message2 = beach.decode();
        message2.explore();
    }

    /**
     * Method to test chromakey
     */
    public static void testChromakey() {
        Picture mark = new Picture("blue-mark.jpg");
        Picture moon = new Picture("moon-surface.jpg");
        mark.explore();
        moon.explore();
        mark.chromakey(moon, 0, 20, 60);
        mark.explore();
    }

    /**
     * Method to test cartoon
     */
    public static void testCartoon() {
        Picture mark = new Picture("blue-mark.jpg");
        mark.explore();
        mark.cartoon();
        mark.explore();
    }


    /**
     * Method to test median filter
     */
    public static void testMedian(){
        Picture[] statues = new Picture[9];
        for(int i = 0; i < statues.length; i++){
            statues[i] = new Picture("T" + (i+1) + ".jpg");
            statues[i].explore();
        }
        Picture newPic = new Picture(statues[0].getWidth(), statues[0].getHeight());
        newPic.medianPictures(statues);
        newPic.explore();
    }

    /**
     * Main method for testing. Every class can have a main method in Java
     */
    public static void main(String[] args) {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run

        //testExplorer();
        //testScale();
        //testZeroBlue();
        //testKeepOnlyBlue();
        //testKeepOnlyRed();
        //testKeepOnlyGreen();
        //testNegate();
        //testGrayscale();
        //testBlackAndWhite();
        //testFixUnderwater();
        //testMirrorVertical();
        //testMirrorTemple();
        //testMirrorArms();
        //testMirrorGull();
        //testMirrorDiagonal();
        //testCollage();
        //testCopy();
        //testEdgeDetection();
        //testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
        //testCartoon();
        testMedian();
    }
}
