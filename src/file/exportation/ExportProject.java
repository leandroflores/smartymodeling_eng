package file.exportation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.structural.base.Project;

/**
 * <p>Class of Export <b>ExportProject</b>.</p>
 * <p>Class responsible for <b>Exporting Project</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    model.structural.Project
 */
public class ExportProject {
    private final Project project;
    private final String  path;
    private FileWriter  fileWriter;
    private PrintWriter printWriter;
    
    /**
     * Default constructor method of Class.
     * @param project SMartyModeling Project.
     * @param path Project Path.
     */
    public ExportProject(Project project, String path) {
        this.project = project;
        this.path    = path;
    }
    
    /**
     * Method responsible for exporting the Project.
     * @throws IOException
     */
    public void export() throws IOException {
        this.createFile();
        this.exportProject();
        this.closeFile();
    }
    
    /**
     * Method responsible for creating Exportation File.
     * @throws IOException 
     */
    private void createFile() throws IOException {
        this.fileWriter  = new FileWriter(this.path);
        this.printWriter = new PrintWriter(this.fileWriter);
    }
    
    /**
     * Method responsible for exporting the Project.
     */
    private void exportProject() {
        this.printWriter.print(this.project.export());
    }
    
    /**
     * Method responsible for closing Exportation File.
     * @throws IOException
     */
    private void closeFile() throws IOException {
        this.printWriter.close();
        this.fileWriter.close();
    }
}