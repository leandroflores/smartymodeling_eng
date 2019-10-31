package file.exportation.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Class of Export <b>ExportPdfImage</b>.</p>
 * <p>Class responsible for <b>Exporting Pdf Image</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  30/10/2019
 */
public class ExportPdfImage {
    private final Image image;
    private final String path;
    private final Rectangle standard;
    
    /**
     * Default constructor method of Class.
     * @param image Image PDF.
     * @param path Path to export.
     */
    public ExportPdfImage(Image image, String path) {
        this.image    = image;
        this.path     = path.replace(".png", ".pdf");
        this.standard = PageSize.A4;
    }
    
    /**
     * Method responsible for returning the Scale Portrait.
     * @return Scale Portrait.
     */
    private float getScalePortrait() {
        return Math.min(this.standard.getWidth()  / this.image.getWidth(),
                        this.standard.getHeight() / this.image.getHeight());
    }
    
    /**
     * Method responsible for returning the Scale Landscape.
     * @return Scale Landscape.
     */
    private float getScaleLandscape() {
        return Math.min(this.standard.getHeight() / this.image.getWidth(),
                        this.standard.getWidth()  / this.image.getHeight());
    }
    
    /**
     * Method responsible for returning if Landscape is better than Portrait Orientation.
     * @return Landscape is better than Protrait.
     */
    private boolean isLandscape() {
        return this.getScaleLandscape() > this.getScalePortrait();
    }
    
    /**
     * Method responsible for returning the Size.
     * @return Size.
     */
    private Float[] getSize() {
        return this.isLandscape() ? this.getLandscapeSize() : this.getPortraitSize();
    }
    
    /**
     * Method responsible for returning the Landscape Size.
     * @return Landscape Size.
     */
    private Float[] getLandscapeSize() {
        Float   scale   = this.getScaleLandscape();
        Float[] size    = new Float[2];
                size[0] = this.image.getWidth()  * scale;
                size[1] = this.image.getHeight() * scale;
                this.standard.rotate();
        return  size;
    }
    
    /**
     * Method responsible for returning the Portrait Size.
     * @return Portrait Size.
     */
    private Float[] getPortraitSize() {
        Float   scale   = this.getScalePortrait();
        Float[] size    = new Float[2];
                size[0] = this.image.getWidth()  * scale;
                size[1] = this.image.getHeight() * scale;
        return  size;
    }
    
    /**
     * Method responsible for export the PDF Image.
     * @throws FileNotFoundException
     * @throws DocumentException 
     */
    public void export() throws FileNotFoundException, DocumentException, IOException {
        this.standard.rotate();
        OutputStream stream   = new FileOutputStream(this.path);
        Document     document = new Document(this.standard, 10, 10, 10, 10);
        Float[]      size     = this.getSize();
        
        PdfWriter.getInstance(document, stream);
        
        document.open();
            this.image.scaleAbsolute(size[0], size[1]);
            this.image.setAbsolutePosition((this.standard.getWidth()  - size[1]) / 2,
                                           (this.standard.getHeight() - size[0]) / 2);
            this.image.setBorder(Image.NO_BORDER);
            this.image.setBorder(0);
        document.newPage();
        document.add(this.image);
        document.close();
        stream.close();
    }
}