package file.exportation.diagram;

import com.mxgraph.util.mxCellRenderer;
import file.exportation.pdf.ExportImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.structural.base.Diagram;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import view.panel.diagram.PanelDiagram;
import view.panel.diagram.types.PanelActivityDiagram;
import view.panel.diagram.types.PanelClassDiagram;
import view.panel.diagram.types.PanelComponentDiagram;
import view.panel.diagram.types.PanelSequenceDiagram;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of Export <b>ExportDiagram</b>.</p>
 * <p>Class responsible for <b>Exporting</b> the <b>Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    model.structural.base.Diagram
 */
public class ExportDiagram {
    private final String  path;
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param path Path to Export.
     * @param diagram Diagram.
     */
    public ExportDiagram(String path, Diagram diagram) {
        this.path     = path;
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for Export the Diagram.
     * @throws java.io.IOException Exception to Create Image.
     */
    public void export() throws IOException {
        String newPath = this.path + "\\" + this.diagram.getId() + ".png";
        BufferedImage pngImage = new ExportImage(this.getImage()).getPNGImage();
        ImageIO.write(pngImage, "PNG", new File(newPath));

    }
    
    /**
     * Method responsible for returning the Diagram Image.
     * @return Diagram Image.
     */
    private BufferedImage getImage() {
        PanelDiagram panel = this.getPanelDiagram();
                     panel.updateDiagram();
        return mxCellRenderer.createBufferedImage(panel.getGraph(),  null, 1, Color.WHITE, true, null);
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    private PanelDiagram getPanelDiagram() {
        if (this.diagram.getType().equals("Activity"))
            return new PanelActivityDiagram(null,  (ActivityDiagram)  this.diagram);
        else if (this.diagram.getType().equals("Class"))
            return new PanelClassDiagram(null, (ClassDiagram) this.diagram);
        else if (this.diagram.getType().equals("Component"))
            return new PanelComponentDiagram(null, (ComponentDiagram) this.diagram);
        else if (this.diagram.getType().equals("Sequence"))
            return new PanelSequenceDiagram(null,  (SequenceDiagram)  this.diagram);
        return new PanelUseCaseDiagram(null, (UseCaseDiagram) this.diagram);
    }
}