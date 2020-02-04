package file.exportation.code;

import java.io.IOException;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Export <b>ExportInstance</b>.</p>
 * <p>Class responsible for <b>Exporting Instance</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  19/01/2020
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
        System.out.println("Path: " + this.path);
        this.instance = instance;
    }
    
    @Override
    public void export() throws IOException {
        this.deleteFolder();
        this.createFolder();
        this.exportInstance();
    }
    
    /**
     * Method responsible for exporting the Instance.
     * @throws IOException Exception to Export Instance.
     */
    private void exportInstance() throws IOException {
        this.exportPackages();
        this.exportEntities();
    }
    
    /**
     * Method responsible for exporting the Instance Packages.
     * @throws IOException Exception to Export the Instance Packages.
     */
    private void exportPackages() throws IOException {
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.isPackage() && ((PackageUML) artifact.getElement()).getParent() == null)
                this.export(this.path,   (PackageUML) artifact.getElement());
        }
    }
    
    /**
     * Method responsible for exporting the Instance Entities.
     * @throws IOException Exception to Export the Instance Entities.
     */
    private void exportEntities() throws IOException {
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.isEntity() && ((Entity) artifact.getElement()).getPackageUML() == null)
                this.export(this.path,  (Entity) artifact.getElement());
        }
    }
}