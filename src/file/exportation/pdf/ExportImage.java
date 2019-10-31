package file.exportation.pdf;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>Class of Export <b>ExportImage</b>.</p>
 * <p>Class responsible for <b>Exporting</b> the <b>Image</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  31/10/2019
 */
public class ExportImage {
    private final String path;
    private final BufferedImage image;
    
    /**
     * Default constructor method of Class.
     * @param path Image Path.
     * @param image Buffered Image.
     */
    public ExportImage(String path, BufferedImage image) {
        this.path  = path;
        this.image = image;
    }
    
    /**
     * Method responsible for returning the PNG Image.
     * @return PNG Image.
     * @throws IOException Exception.
     */
    public BufferedImage getPNGImage() throws IOException {
        return this.getPngImage(this.image, Color.WHITE);
    }
    
    /**
     * 
     * @param image
     * @param color
     * @return
     * @throws IOException 
     */
    private BufferedImage getPngImage(BufferedImage image, Color color) throws IOException {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB); 
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                int alpha = (color.getRGB() == pixel) ? 0 : 255;
                newImage.setRGB(x, y, (alpha & 0x0ff) << 24 
                                    | ((pixel >> 16) &0xff) << 16 
                                    | ((pixel >> 8) &0xff)  << 8 
                                    | ((pixel) &0xff));
            }
        }
        return newImage;
    }
}