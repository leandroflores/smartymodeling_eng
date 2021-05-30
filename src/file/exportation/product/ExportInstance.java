package file.exportation.product;

import com.mxgraph.util.mxCellRenderer;
import file.exportation.pdf.ExportImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.structural.base.product.Instance;
import view.panel.instance.PanelInstance;
import view.panel.instance.types.PanelActivityInstance;
import view.panel.instance.types.PanelClassInstance;
import view.panel.instance.types.PanelComponentInstance;
import view.panel.instance.types.PanelUseCaseInstance;

/**
 * <p>Class of File <b>ExportInstance</b>.</p>
 * <p>Class responsible for <b>Exporting</b> the <b>Instance</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-04
 * @see    model.structural.base.product.Instance
 */
public class ExportInstance {
    private final String   path;
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param path_ Path to Export.
     * @param instance_ Product Instance.
     */
    public ExportInstance(String path_, Instance instance_) {
        path     = path_;
        instance = instance_;
    }
    
    /**
     * Method responsible for Export the Instance.
     * @throws java.io.IOException Exception to Create Image.
     */
    public void export() throws IOException {
        String newPath = path + "\\" + instance.getId() + ".png";
        BufferedImage pngImage = new ExportImage(getImage()).getPNGImage();
        ImageIO.write(pngImage, "PNG", new File(newPath));

    }
    
    /**
     * Method responsible for returning the Instance Image.
     * @return Instance Image.
     */
    private BufferedImage getImage() {
        PanelInstance panel = getPanelInstance();
                      panel.updateGraph();
        return mxCellRenderer.createBufferedImage(panel.getGraph(),  null, 1, Color.WHITE, true, null);
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    private PanelInstance getPanelInstance() {
        if (instance.getDiagram().getType().equals("Activity"))
            return new PanelActivityInstance(null, instance);
        else if (instance.getDiagram().getType().equals("Class"))
            return new PanelClassInstance(null, instance);
        else if (instance.getDiagram().getType().equals("Component"))
            return new PanelComponentInstance(null, instance);
        return new PanelUseCaseInstance(null, instance);
    }
}