package file.exportation.code.classes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.structural.diagram.classes.Entity;

/**
 * <p>Class of File <b>ExportEntity</b>.</p>
 * <p>Class responsible for <b>Exporting Entity Code</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-19
 * @see    model.structural.diagram.classes.Entity
 */
public class ExportEntity {
    private final String path;
    private final Entity entity;
    private FileWriter   fileWriter;
    private PrintWriter  printWriter;
    
    /**
     * Default constructor method of Class.
     * @param path_ Path to Export.
     * @param entity_ Entity to Export.
     */
    public ExportEntity(String path_, Entity entity_) {
        path   = path_;
        entity = entity_;
    }
    
    /**
     * Method responsible for exporting the Source Code.
     * @throws java.io.IOException Exception to export.
     */
    public void export() throws IOException {
        createFile();
        exportEntity();
        closeFile();
    }
    
    /**
     * Method responsible for creating the Export File.
     * @throws IOException Exception to create File.
     */
    private void createFile() throws IOException {
        fileWriter  = new FileWriter(path + "\\" + entity.getName() + ".java");
        printWriter = new PrintWriter(fileWriter);
    }
    
    /**
     * Method responsible for exporting the Entity.
     */
    private void exportEntity() {
        printWriter.println(entity.exportCode());
    }
    
    /**
     * Method responsible for closing the Export File.
     * @throws IOException Exception to close the File.
     */
    private void closeFile() throws IOException {
        printWriter.close();
        fileWriter.close();
    }
}