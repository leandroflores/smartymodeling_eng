package file.exportation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.structural.base.Project;

/**
 * <p>Class of File <b>ExportProject</b>.</p>
 * <p>Class responsible for <b>Exporting Project</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    model.structural.Project
 */
public class ExportProject {
    private final Project project;
    private final String  path;
    private FileWriter  fileWriter;
    private PrintWriter printWriter;
    
    /**
     * Default constructor method of Class.
     * @param project_ SMartyModeling Project.
     * @param path_ Project Path.
     */
    public ExportProject(Project project_, String path_) {
        project = project_;
        path    = path_;
    }
    
    /**
     * Method responsible for exporting the Project.
     * @throws IOException
     */
    public void export() throws IOException {
        createFile();
        exportProject();
        closeFile();
    }
    
    /**
     * Method responsible for creating Exportation File.
     * @throws IOException 
     */
    private void createFile() throws IOException {
        fileWriter  = new FileWriter(path);
        printWriter = new PrintWriter(fileWriter);
    }
    
    /**
     * Method responsible for exporting the Project.
     */
    private void exportProject() {
        printWriter.print(project.export());
    }
    
    /**
     * Method responsible for closing Exportation File.
     * @throws IOException
     */
    private void closeFile() throws IOException {
        printWriter.close();
        fileWriter.close();
    }
}