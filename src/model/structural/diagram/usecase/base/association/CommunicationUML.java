package model.structural.diagram.usecase.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.association.Association;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;

/**
 * <p>Class of Model <b>CommunicationUML</b>.</p>
 * <p>Class responsible for representing <b>Use Case Realization UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-22
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.usecase.base.ActorUML
 * @see    model.structural.diagram.usecase.base.UseCaseUML
 */
public class CommunicationUML extends Association {
    
    /**
     * Default constructor method of Class.
     * @param actor Actor UML.
     * @param useCase Use Case UML.
     */
    public CommunicationUML(ActorUML actor, UseCaseUML useCase) {
        super();
        source = actor;
        target = useCase;
        type   = "communication";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public CommunicationUML(org.w3c.dom.Element element) {
        super(element);
        type = "communication";
    }

    @Override
    public ActorUML getSource() {
        return (ActorUML) source;
    }

    /**
     * Method responsible for setting Source.
     * @param actor Actor UML.
     */
    public void setSource(ActorUML actor) {
        source = actor;
    }

    @Override
    public UseCaseUML getTarget() {
        return (UseCaseUML) target;
    }

    /**
     * Method responsible for setting Target.
     * @param useCase Use Case UML.
     */
    public void setTarget(UseCaseUML useCase) {
        target = useCase;
    }
    
    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleCommunication";
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
    protected String exportHeader() {
        String export  = "    <"       + type;
               export += " id=\""      + id             + "\"";
               export += " actor=\""   + source.getId() + "\"";
               export += " useCase=\"" + target.getId() + "\">\n";
        return export;
    }
}