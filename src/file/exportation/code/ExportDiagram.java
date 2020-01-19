package file.exportation.code;

import java.io.IOException;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Export <b>ExportDiagram</b>.</p>
 * <p>Class responsible for <b>Exporting Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  19/01/2020
 * @see    file.exportation.code.ExportCode
 * @see    model.structural.base.Diagram
 */
public class ExportDiagram extends ExportCode {
    private final ClassDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param path Path to Export.
     * @param diagram Diagram to Export.
     */
    public ExportDiagram(String path, ClassDiagram diagram) {
        super(path);
        this.path    = path + "\\" + diagram.getName();
        this.diagram = diagram;
    }
    
    @Override
    public void export() throws IOException {
        this.createFolder();
        this.exportDiagram();
    }
    
    /**
     * Method responsible for exporting the Diagram.
     * @throws IOException Exception to Export the Diagram.
     */
    private void exportDiagram() throws IOException {
        this.exportPackages();
        this.exportEntities();
    }
    
    /**
     * Method responsible for exporting the Diagram Packages.
     * @throws IOException Exception to Export the Packages.
     */
    private void exportPackages() throws IOException {
        for (PackageUML packageUML : this.diagram.getPackagesList()) {
            if (packageUML.getParent() == null)
                this.export(this.path, packageUML);
        }
    }
    
    /**
     * Method responsible for exporting the Diagram Entities.
     * @throws IOException Exception to Export the Entities.
     */
    private void exportEntities() throws IOException {
        for (Entity entity : this.diagram.getEntitiesList()) {
            if (entity.getPackageUML() == null)
                this.export(this.path, entity);
        }
    }
}