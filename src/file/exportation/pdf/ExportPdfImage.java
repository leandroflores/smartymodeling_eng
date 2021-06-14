package file.exportation.pdf;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>Class of File <b>ExportPdfImage</b>.</p>
 * <p>Class responsible for <b>Exporting Pdf Image</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-05-20
 */
public class ExportPdfImage {
    private final Image image;
    private final String path;
    
    /**
     * Default constructor method of Class.
     * @param image PDF Image.
     * @param path Path to export.
     * @throws com.itextpdf.text.BadElementException
     * @throws java.io.IOException
     */
    public ExportPdfImage(BufferedImage image, String path) throws BadElementException, IOException {
        this.image = Image.getInstance(image, null);
        this.path  = path.replace(".png", ".pdf");
    }
    
    /**
     * Method responsible for export the PDF Image.
     * @throws FileNotFoundException
     * @throws DocumentException 
     */
    public void export() throws FileNotFoundException, DocumentException, IOException {
        OutputStream stream   = new FileOutputStream(path);
        Document document = new Document(PageSize.A4, 0, 0, 0, 0);
                 document.setPageSize(PageSize.A4.rotate());
            PdfWriter.getInstance(document, stream);
                 document.open();
            image.setBorder(Image.NO_BORDER);
            image.setBorderColor(BaseColor.BLACK);
//            image.setBorder(Image.);
            image.scaleToFit(PageSize.A4.getHeight(), PageSize.A4.getWidth());
                 document.newPage();
                 document.add(image);
                 document.close();
        stream.close();
    }
}