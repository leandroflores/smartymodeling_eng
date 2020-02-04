package file.exportation.code;

import file.exportation.code.classes.ExportEntity;
import java.io.File;
import java.io.IOException;
import model.structural.base.Element;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Export <b>ExportCode</b>.</p>
 * <p>Class responsible for <b>Exporting Source Code</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  19/01/2020
 * @see    file.exportation.code.classes.ExportEntity
 * @see    java.io.File
 */
public abstract class ExportCode {
    protected String path;
    protected String folder;
    protected File   file;
    
    /**
     * Default constructor method of Class.
     * @param path Path to Export.
     */
    public ExportCode(String path) {
        this.path = path;
    }
    
    /**
     * Method responsible for exporting the Code.
     * @throws IOException Exception to Export the Code.
     */
    public abstract void export() throws IOException;
    
    /**
     * Method responsible for deleting the Folder to Export.
     * @throws IOException Exception to Delete the Folder.
     */
    protected void deleteFolder() throws IOException {
        this.file = new File(this.path);
        this.deleteFolder(this.file);
    }
    
    /**
     * Method responsible for deleting a Folder.
     * @param file File Folder;
     */
    private void deleteFolder(File file) {
        if (file.listFiles() != null) {
            for (File current : file.listFiles()) {
                this.deleteFolder(current);
                file.delete();
            }
        }
        file.delete();
    }
    
    /**
     * Method responsible for creating the Folder to Export.
     * @throws IOException Exception to Create the Folder.
     */
    protected void createFolder() throws IOException {
        this.file = new File(this.path);
        this.file.mkdirs();
    }
    
    /**
     * Method responsible for creating the Folder to Export.
     * @param packageUML Package UML.
     * @throws IOException Exception to create the Folder.
     */
    protected void createPackage(PackageUML packageUML) throws IOException {
        this.file = new File(this.path + "\\" + packageUML.getFolderPath());
        this.file.mkdir();
    }
    
    /**
     * Method responsible for exporting the Element.
     * @param path Path to Export.
     * @param element Element to Export.
     * @throws IOException Exception to Export Element.
     */
    protected void export(String path, Element element) throws IOException {
        if (element instanceof PackageUML)
            this.export(path, ((PackageUML) element));
        else if (element instanceof Entity)
            this.export(path, ((Entity) element));
    }
    
    /**
     * Method responsible for exporting the Package.
     * @param path Path to Export.
     * @param packageUML Package to Export.
     * @throws IOException Exception to Export Package.
     */
    protected void export(String path, PackageUML packageUML) throws IOException {
        this.createPackage(packageUML);
        
        for (PackageUML subPackage : packageUML.getPackagesList())
            this.export(this.folder + "\\" + packageUML.getFolderPath(), subPackage);
        
        for (Entity entity : packageUML.getEntitiesList())
            this.export(this.folder + "\\" + packageUML.getFolderPath(), entity);
    }
    
    /**
     * Method responsible for exporting the Entity.
     * @param path Path to Export.
     * @param entity Entity to Export.
     * @throws IOException Exception to Export Entity.
     */
    protected void export(String path, Entity entity) throws IOException {
        System.out.println("Export Method: " + path);
        new ExportEntity(path, entity).export();
    }
}