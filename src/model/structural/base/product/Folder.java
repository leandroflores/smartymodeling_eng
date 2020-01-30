package model.structural.base.product;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.diagram.classes.base.PackageUML;

/**
 * <p>Class of Model <b>Folder</b>.</p>
 * <p>Class responsible for representing the <b>Folder</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    model.structural.base.interfaces.Exportable
 * @see    model.structural.base.interfaces.Modelable
 */
public class Folder extends Artifact {
    private PackageUML packageUML;
    private Folder  parent;
    private HashMap folders;
    private HashMap artifacts;
    
    /**
     * Default constructor method of Class.
     */
    public Folder() {
        super();
    }
    
    
    
    
    @Override
    public String getStyleLabel() {
        return this.element.getStyleLabel();
    }

    @Override
    public Map getStyle() {
        Map    style = new HashMap(this.element.getStyle());
               style.put(mxConstants.STYLE_EDITABLE, "0");
        return style;
    }
    
    @Override
    public String export() {
        String export  = "        "; 
               export += "<artifact"; 
               export += " id=\""        + this.id              + "\"";
               export += " element=\""   + this.element.getId() + "\"";
               export += " x=\""         + this.getX()          + "\"";
               export += " y=\""         + this.getY()          + "\"";
               export += " height=\""    + this.getHeight()     + "\"";
               export += " width=\""     + this.getWidth()      + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Folder == false)
            return false;
        return Objects.equals(this.id, ((Folder) object).getId());
    }

    @Override
    public int hashCode() {
        int    hash = 5;
               hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.element.toString();
    }
}