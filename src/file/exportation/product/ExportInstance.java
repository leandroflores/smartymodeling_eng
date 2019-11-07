package file.exportation.product;

import com.mxgraph.util.mxCellRenderer;
import file.exportation.pdf.ExportImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.structural.base.product.Instance;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.UseCaseDiagram;
import view.panel.instance.PanelInstance;
import view.panel.instance.types.PanelActivityInstance;
import view.panel.instance.types.PanelClassInstance;
import view.panel.instance.types.PanelComponentInstance;
import view.panel.instance.types.PanelUseCaseInstance;

/**
 * <p>Class of Export <b>ExportInstance</b>.</p>
 * <p>Class responsible for <b>Exporting</b> the <b>Instance</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  04/11/2019
 * @see    model.structural.base.product.Instance
 */
public class ExportInstance {
    private final String   path;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param path Path to Export.
     * @param instance Product Instance.
     */
    public ExportInstance(String path, Instance instance) {
        this.path     = path;
        this.instance = instance;
    }
    
    /**
     * Method responsible for Export the Instance.
     * @throws java.io.IOException Exception to Create Image.
     */
    public void export() throws IOException {
        String newPath = this.path + "\\" + this.instance.getId() + ".png";
        BufferedImage pngImage = new ExportImage(this.getImage()).getPNGImage();
        ImageIO.write(pngImage, "PNG", new File(newPath));

    }
    
    /**
     * Method responsible for returning the Instance Image.
     * @return Instance Image.
     */
    private BufferedImage getImage() {
        PanelInstance panel = this.getPanelInstance();
                      panel.updateInstance();
        return mxCellRenderer.createBufferedImage(panel.getGraph(),  null, 5, Color.WHITE, true, null);
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    private PanelInstance getPanelInstance() {
        if (this.instance.getDiagram().getType().equals("Activity"))
            return new PanelActivityInstance(null,  this.instance, (ActivityDiagram)  this.instance.getDiagram());
        if (this.instance.getDiagram().getType().equals("Class"))
            return new PanelClassInstance(null,     this.instance,   (ClassDiagram)   this.instance.getDiagram());
        if (this.instance.getDiagram().getType().equals("Component"))
            return new PanelComponentInstance(null, this.instance, (ComponentDiagram) this.instance.getDiagram());
        return new PanelUseCaseInstance(null, this.instance, (UseCaseDiagram) this.instance.getDiagram());
    }
}