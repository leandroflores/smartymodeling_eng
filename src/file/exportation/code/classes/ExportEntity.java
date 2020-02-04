package file.exportation.code.classes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.structural.diagram.classes.Entity;

/**
 * <p>Class of Export <b>ExportEntity</b>.</p>
 * <p>Class responsible for <b>Exporting Entity Code</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  19/01/2020
 * @see    model.structural.diagram.classes.Entity
 */
public class ExportEntity {
    private final String path;
    private final Entity entity;
    private FileWriter   fileWriter;
    private PrintWriter  printWriter;
    
    /**
     * Default constructor method of Class.
     * @param path Path to Export.
     * @param entity Entity to Export.
     */
    public ExportEntity(String path, Entity entity) {
        this.path   = path;
        this.entity = entity;
    }
    
    /**
     * Method responsible for exporting the Source Code.
     * @throws java.io.IOException Exception to export.
     */
    public void export() throws IOException {
        this.createFile();
        this.exportEntity();
        this.closeFile();
    }
    
    /**
     * Method responsible for creating the Export File.
     * @throws IOException Exception to create File.
     */
    private void createFile() throws IOException {
        this.fileWriter  = new FileWriter(this.path + "\\" + this.entity.getName() + ".java");
        this.printWriter = new PrintWriter(this.fileWriter);
    }
    
    /**
     * Method responsible for exporting the Entity.
     */
    private void exportEntity() {
        this.printWriter.println(this.entity.exportCode());
    }
    
    /**
     * Method responsible for closing the Export File.
     * @throws IOException Exception to close the File.
     */
    private void closeFile() throws IOException {
        this.printWriter.close();
        this.fileWriter.close();
    }
}