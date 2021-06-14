package file.exportation.code;

import java.io.IOException;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of File <b>ExportDiagram</b>.</p>
 * <p>Class responsible for <b>Exporting Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-19
 * @see    file.exportation.code.ExportCode
 * @see    model.structural.diagram.ClassDiagram
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
        this.folder  = path + "\\" + diagram.getName();
        this.diagram = diagram;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param path Path to Export.
     * @param name Folder Name to Export.
     * @param diagram Diagram to Export.
     */
    public ExportDiagram(String path, String name, ClassDiagram diagram) {
        super(path);
        this.path    = path + "\\" + name;
        this.folder  = path + "\\" + name;
        this.diagram = diagram;
    }
    
    @Override
    public void export() throws IOException {
        deleteFolder();
        createFolder();
        exportDiagram();
    }
    
    /**
     * Method responsible for exporting the Diagram.
     * @throws IOException Exception to Export the Diagram.
     */
    private void exportDiagram() throws IOException {
        exportPackages();
        exportEntities();
    }
    
    /**
     * Method responsible for exporting the Diagram Packages.
     * @throws IOException Exception to Export the Packages.
     */
    private void exportPackages() throws IOException {
        for (PackageUML packageUML : diagram.getPackagesList()) {
            if (packageUML.getParent() == null)
                export(path, packageUML);
        }
    }
    
    /**
     * Method responsible for exporting the Diagram Entities.
     * @throws IOException Exception to Export the Entities.
     */
    private void exportEntities() throws IOException {
        for (Entity entity : diagram.getEntitiesList()) {
            if (entity.getPackageUML() == null)
                export(path, entity);
        }
    }
}