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
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import view.panel.diagram.PanelDiagram;
import view.panel.diagram.types.PanelActivityDiagram;
import view.panel.diagram.types.PanelClassDiagram;
import view.panel.diagram.types.PanelComponentDiagram;
import view.panel.diagram.types.PanelFeatureDiagram;
import view.panel.diagram.types.PanelSequenceDiagram;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of File <b>ExportDiagram</b>.</p>
 * <p>Class responsible for <b>Exporting</b> the <b>Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    model.structural.base.Diagram
 */
public class ExportDiagram {
    private final String  path;
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param path_ Path to Export.
     * @param diagram_ Diagram.
     */
    public ExportDiagram(String path_, Diagram diagram_) {
        path     = path_;
        diagram = diagram_;
    }
    
    /**
     * Method responsible for Export the Diagram.
     * @throws java.io.IOException Exception to Create Image.
     */
    public void export() throws IOException {
        String newPath = path + "\\" + diagram.getId() + ".png";
        BufferedImage pngImage = new ExportImage(getImage()).getPNGImage();
        ImageIO.write(pngImage, "PNG", new File(newPath));
    }
    
    /**
     * Method responsible for returning the Diagram Image.
     * @return Diagram Image.
     */
    private BufferedImage getImage() {
        PanelDiagram panel = getPanelDiagram();
                     panel.updateGraph();
        return mxCellRenderer.createBufferedImage(panel.getGraph(),  null, 1, Color.WHITE, true, null);
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    private PanelDiagram getPanelDiagram() {
        if (diagram.getType().equals("Feature"))
            return new PanelFeatureDiagram(null, (FeatureDiagram)  diagram);
        else if (diagram.getType().equals("Activity"))
            return new PanelActivityDiagram(null, (ActivityDiagram)  diagram);
        else if (diagram.getType().equals("Class"))
            return new PanelClassDiagram(null, (ClassDiagram) diagram);
        else if (diagram.getType().equals("Component"))
            return new PanelComponentDiagram(null, (ComponentDiagram) diagram);
        else if (diagram.getType().equals("Sequence"))
            return new PanelSequenceDiagram(null,  (SequenceDiagram)  diagram);
        return new PanelUseCaseDiagram(null, (UseCaseDiagram) diagram);
    }
}