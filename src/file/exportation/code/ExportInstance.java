package file.exportation.code;

import java.io.IOException;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;

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
        this.instance = instance;
    }
    
    @Override
    public void export() throws IOException {
        this.createFolder();
        this.exportInstance();
    }
    
    /**
     * Method responsible for exporting the Instance.
     * @throws IOException Exception to Export Instance.
     */
    private void exportInstance() throws IOException {
        for (Artifact artifact : this.instance.getArtifactsList())
            this.export(this.path, artifact.getElement());
    }
}