package file.exportation.code;

import java.io.IOException;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of File <b>ExportInstance</b>.</p>
 * <p>Class responsible for <b>Exporting Instance</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-19
 * @see    file.exportation.code.ExportCode
 * @see    model.structural.base.product.Instance
 */
public class ExportInstance extends ExportCode {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param path Path to Export.
     * @param instance Instance to Export.
     */
    public ExportInstance(String path, Instance instance) {
        super(path);
        this.path     = path + "\\" + instance.getName();
        this.folder   = path + "\\" + instance.getName();
        this.instance = instance;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param path Path to Export.
     * @param name Folder Name to Export.
     * @param instance Instance to Export.
     */
    public ExportInstance(String path, String name, Instance instance) {
        super(path);
        this.path     = path + "\\" + name;
        this.folder   = path + "\\" + name;
        this.instance = instance;
    }
    
    @Override
    public void export() throws IOException {
        deleteFolder();
        createFolder();
        exportInstance();
    }
    
    /**
     * Method responsible for exporting the Instance.
     * @throws IOException Exception to Export Instance.
     */
    private void exportInstance() throws IOException {
        exportPackages();
        exportEntities();
    }
    
    /**
     * Method responsible for exporting the Instance Packages.
     * @throws IOException Exception to Export the Instance Packages.
     */
    private void exportPackages() throws IOException {
        for (Artifact artifact : instance.getArtifactsList()) {
            if (artifact.isPackage() && ((PackageUML) artifact.getElement()).getParent() == null)
                export(path, (PackageUML) artifact.getElement());
        }
    }
    
    /**
     * Method responsible for exporting the Instance Entities.
     * @throws IOException Exception to Export the Instance Entities.
     */
    private void exportEntities() throws IOException {
        for (Artifact artifact : instance.getArtifactsList()) {
            if (artifact.isEntity() && ((Entity) artifact.getElement()).getPackageUML() == null)
                export(path, (Entity) artifact.getElement());
        }
    }
}