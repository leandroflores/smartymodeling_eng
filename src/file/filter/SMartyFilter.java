package file.filter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * <p>Class of File <b>SMartyFilter</b>.</p>
 * <p>Class responsible for defining the <b>File Filter</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    javax.swing.filechooser.FileFilter
 */
public class SMartyFilter extends FileFilter {
    
    @Override
    public boolean accept(File file) {
        if (file.isDirectory())
            return false;
        return file.getName().toLowerCase().trim().endsWith(".smty");
    }

    @Override
    public String getDescription() {
        return "SMarty File (*.smty)";
    }
}