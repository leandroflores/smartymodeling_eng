package model.structural.diagram.usecase;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;

/**
 * <p>Class of Model <b>RealizationUML</b>.</p>
 * <p>Class responsible for representing <b>Use Case Realization UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.usecase.ActorUML
 * @see    model.structural.diagram.usecase.UseCaseUML
 */
public class RealizationUML extends Association {
    
    /**
     * Default constructor method of Class.
     * @param actor Actor UML.
     * @param useCase Use Case UML.
     */
    public RealizationUML(ActorUML actor, UseCaseUML useCase) {
        this.source = actor;
        this.target = useCase;
        this.type   = "realization";
    }

    @Override
    public ActorUML getSource() {
        return (ActorUML) this.source;
    }

    /**
     * Method responsible for setting Source.
     * @param actor Actor UML.
     */
    public void setSource(ActorUML actor) {
        this.source = actor;
    }

    @Override
    public UseCaseUML getTarget() {
        return (UseCaseUML) this.target;
    }

    /**
     * Method responsible for setting Target.
     * @param target Use Case UML.
     */
    public void setTarget(UseCaseUML target) {
        this.target = target;
    }
    
    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleRealization";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    @Override
    public String export() {
        String export  = "    <" + this.type;
               export += " actor=\""   + this.source.getId() + "\"";
               export += " useCase=\"" + this.target.getId() + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public int hashCode() {
        int    hash = 3;
               hash = 19 * hash + Objects.hashCode(this.source);
               hash = 19 * hash + Objects.hashCode(this.target);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof RealizationUML == false)
            return false;
        return this.source.equals(((RealizationUML) object).getSource())
            && this.target.equals(((RealizationUML) object).getTarget());
    }
}