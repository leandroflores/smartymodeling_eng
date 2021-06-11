package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.ParameterUML;
import model.structural.diagram.classes.base.TypeUML;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventChange</b>.</p>
 * <p>Class responsible for defining the <b>Change Events</b> in <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventChange extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventChange(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = getPanel().getGraph().getSelectionCell();
        String id   = getId(cell);
            change(cell, id);
        getPanel().updateGraph();
        getPanel().getViewMenu().getPanelModeling().updateInstancePanels();
        getPanel().getViewMenu().getPanelProject().updateUI();
    }
    
    /**
     * Method responsible for changing the Selected Element.
     * @param object Selected Object.
     * @param id Element Id.
     */
    private void change(Object object, String id) {
        if (getDiagram().getAssociation(id) != null)
            changeNameAssociation(object, getDiagram().getAssociation(id));
        else if (getDiagram().getElement(id) instanceof AttributeUML)
            changeAttribute(object, (AttributeUML) getDiagram().getElement(id));
        else if (getDiagram().getElement(id) instanceof MethodUML)
            changeMethod(object, (MethodUML) getDiagram().getElement(id));
        else if (getDiagram().getElement(id) != null)
            changeElement(object, (Element)getDiagram().getElement(id));
        else if (id != null)
            changeAssociation(object, id);
    }
    
    /**
     * Method responsible for changing the Attribute UML.
     * @param object Graph Object.
     * @param attribute Attribute UML.
     */
    private void changeAttribute(Object object, AttributeUML attribute) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (checkAttribute(signature)) {
            attribute.setVisibility(getVisibility(signature));
            attribute.setName(getName(signature, ":"));
            attribute.setTypeUML(getType(signature, true));
            getPanel().updateGraph();
            getPanel().getViewMenu().update();
            getPanel().getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for checking the Attribute Signature.
     * @param  signature Attribute Signature.
     * @return Attribute Signature checked.
     */
    private boolean checkAttribute(String signature) {
        return     checkVisibility(signature) 
               &&  signature.contains(":")
               && !getName(signature, ":").equals("");
    }
    
    /**
     * Method responsible for changing the Method UML.
     * @param object Graph Object.
     * @param method Method UML.
     */
    private void changeMethod(Object object, MethodUML method) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (checkMethod(signature)) {
            method.setVisibility(getVisibility(signature));
            method.setName(getName(signature, "("));
            method.setParameters(getParameters(signature));
            method.setReturn(getType(signature, false));
            getPanel().updateGraph();
            getPanel().getViewMenu().getProject().changeNames(method);
            getPanel().getViewMenu().update();
            getPanel().getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for checking the Method Signature.
     * @param  signature Method Signature.
     * @return Signature checked.
     */
    private boolean checkMethod(String signature) {
        return     checkVisibility(signature) 
               &&  signature.contains("(")
               &&  signature.contains(")")
               &&  signature.indexOf("(") < signature.indexOf(")")
               && !getName(signature, "(").equals("")
               &&  signature.contains(":");
    }
    
    /**
     * Method responsible for changing the Element.
     * @param object Graph Object.
     * @param element Element.
     */
    private void changeElement(Object object, Element element) {
        mxCell cell = (mxCell) object;
        if (!cell.getValue().toString().equals("")) {
            element.setName(cell.getValue().toString());
            getPanel().updateGraph();
            getPanel().getViewMenu().update();
            getPanel().getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for changing the Association.
     * @param object Graph Object.
     * @param association Association.
     */
    private void changeNameAssociation(Object object, Association association) {
        mxCell cell = (mxCell) object;
        if (association instanceof AssociationUML)
            ((AssociationUML) association).setName(cell.getValue().toString().trim());
    }
    
    /**
     * Method responsible for changing the Cardinality.
     * @param object Graph Object.
     * @param id Association Id.
     */
    private void changeAssociation(Object object, String id) {
        Association association = getAssociation(id);
        if (association != null) {
            if (id.endsWith("(source)"))
                changeSourceAssociation((AssociationUML) association, (mxCell) object);
            else if (id.endsWith("(target)"))
                changeTargetAssociation((AssociationUML) association, (mxCell) object);
        }
    }
    
    /**
     * Method responsible for returning the Association UML by Id.
     * @param  id Cell Id.
     * @return Association UML.
     */
    private Association getAssociation(String id) {
        String newId = id.contains("(") ? id.substring(0, id.indexOf("(")) : "";
        return getDiagram().getAssociation(newId);
    }
    
    /**
     * Method responsible for changing the Source Association.
     * @param association Association UML.
     * @param cell Graph Cell.
     */
    private void changeSourceAssociation(AssociationUML association, mxCell cell) {
        String value       = cell.getValue().toString().trim();
        String cardinality = getCardinality(value);
        String signature   = getSignature(value, "");
               association.setSourceMin(getMin(cardinality, association.getSourceMin()));
               association.setSourceMax(getMax(cardinality, association.getSourceMax()));
               association.setSourceMax(getMax(cardinality, association.getSourceMax()));
               association.setSourceVisibility(getVisibility(signature, association.getSourceVisibility()));
               association.setSourceName(getAssociationName(signature, association.getSourceName()));
        getPanel().getViewMenu().getPanelProject().updatePanelEdit();
    }
    
    /**
     * Method responsible for changing the Target Association.
     * @param association Association UML.
     * @param cell Graph Cell.
     */
    private void changeTargetAssociation(AssociationUML association, mxCell cell) {
        String value       = cell.getValue().toString().trim();
        String cardinality = getCardinality(value);
        String signature   = getSignature(value, "");
               association.setTargetMin(getMin(cardinality, association.getTargetMin()));
               association.setTargetMax(getMax(cardinality, association.getTargetMax()));
               association.setTargetVisibility(getVisibility(signature, association.getTargetVisibility()));
               association.setTargetName(getAssociationName(signature, association.getTargetName()));
        getPanel().getViewMenu().getPanelProject().getPanelEdit().updateUI();
    }
    
    /**
     * Method responsible for returning the Cardinality.
     * @param  value Cell Value.
     * @return Cardinality.
     */
    private String getCardinality(String value) {
        if (value.contains("("))
            return value.substring(0, value.indexOf("(") - 1).trim();
        return value.trim();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @param  value Cell Value.
     * @param  backup Backup Value.
     * @return Signature.
     */
    private String getSignature(String value, String backup) {
        if (checkSignature(value))
            return value.substring(value.indexOf("(") + 1, 
                                   value.indexOf(")")).trim();
        return backup;
    }
    
    /**
     * Method responsible for checking the Value Signature.
     * @param  value Cell Value.
     * @return Signature checked.
     */
    private boolean checkSignature(String value) {
        return  value.contains("(")
             && value.contains(")")
             && value.indexOf("(") < value.indexOf(")");
    }
    
    private boolean checkAssociation(String value) {
        return checkVisibility(value);
    }
    
    /**
     * Method responsible for checking the Signature Visibility.
     * @param  signature Signature Visibility.
     * @return Signature Visibility checked.
     */
    private boolean checkVisibility(String signature) {
        return  signature.startsWith("+")
             || signature.startsWith("-")
             || signature.startsWith("#")
             || signature.startsWith("~");
    }
    
    /**
     * Method responsible for returning the Visibility by Signature.
     * @param  signature Signature.
     * @return Visibility.
     */
    private String getVisibility(String signature) {
        if (signature.startsWith("+"))
            return "public";
        else if (signature.startsWith("#"))
            return "protected";
        else if (signature.startsWith("-"))
            return "private";
        return "default";
    }
    
    /**
     * Method responsible for returning the Name by Signature.
     * @param  signature Signature.
     * @param  symbol Symbol.
     * @return Name.
     */
    private String getName(String signature, String symbol) {
        if (signature.contains(symbol))
            return signature.substring(1, signature.trim().indexOf(symbol)).trim();
        return "";
    }
    
    /**
     * Method responsible for returning the Type UML by Signature.
     * @param  signature Signature.
     * @param  object Object Flag.
     * @return Type UML.
     */
    private TypeUML getType(String signature, boolean object) {
        if (signature.contains(":"))
            return getDiagram().getProject().getTypeByName(signature.substring(signature.trim().lastIndexOf(":") + 1).trim());
        return object ? getDiagram().getObjectType() : getDiagram().getVoidType();
    }
    
    /**
     * Method responsible for returning the Parameters Signature by Signature.
     * @param  signature Signature.
     * @return Parameters Signature.
     */
    private String getParametersSignature(String signature) {
        return signature.substring(signature.indexOf("(") + 1, 
                                   signature.indexOf(")")).trim();
    }
    
    /**
     * Method responsible for returning the Parameters List by Signature.
     * @param  signature Method Signature.
     * @return Parameters List.
     */
    private String[] getParametersList(String signature) {
        return getParametersSignature(signature).split("\\,");
    }
    
    /**
     * Method responsible for returning the Parameters List by Method Signature.
     * @param  signature Method Signature.
     * @return Parameters List.
     */
    private List<ParameterUML> getParameters(String signature) {
        List<ParameterUML> parameters = new ArrayList<>();
        for (String string : getParametersList(signature)) {
            ParameterUML parameter = getParameter(string.trim());
            if (!parameter.getName().equals("") && parameter.getType() != null)
                parameters.add(parameter);
        }
        return  parameters;
    }
    
    /**
     * Method responsible for returning the Parameter by Signature.
     * @param  signature Signature.
     * @return Parameter.
     */
    private ParameterUML getParameter(String signature) {
        ParameterUML parameter = new ParameterUML();
                     parameter.setName(getParameterName(signature));
                     parameter.setType(getParameterType(signature));
        return       parameter;
    }
    
    /**
     * Method responsible for returning the Parameter Name by Signature.
     * @param  signature Signature.
     * @return Parameter Name.
     */
    private String getParameterName(String signature) {
        if (signature.contains(":"))
            return signature.substring(0, signature.indexOf(":")).trim();
        return "";
    }
    
    /**
     * Method responsible for returning the Parameter Type by Signature.
     * @param  signature Signature.
     * @return Parameter Type.
     */
    private TypeUML getParameterType(String signature) {
        if (signature.contains(":"))
            return getDiagram().getProject().getTypeByName(signature.substring(signature.lastIndexOf(":") + 1).trim());
        return getDiagram().getObjectType();
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (getPanel().getIdentifiers().get(cell) != null)
            return getPanel().getIdentifiers().get(cell);
        return getCellId((mxCell) cell);
    }
    
    /**
     * Method responsible for returning the Cell Id by Cell.
     * @param  cell Cell.
     * @return Cell Id.
     */
    private String getCellId(mxCell cell) {
        if (cell != null) {
            if (cell.getId().endsWith("(name)"))
                return cell.getId().substring(0, cell.getId().indexOf("("));
            return cell.getId();
        }
        return "";
    }
    
    /**
     * Method responsible for returning the Min by String Value.
     * @param  value String Value.
     * @param  backup Backup Min Value.
     * @return Min parsed.
     */
    private Integer getMin(String value, Integer backup) {
        if (value.equals("*"))
            return 0;
        if (value.matches("\\d+..\\d+"))
            return Integer.parseInt(value.substring(0, value.indexOf(".")));
        if (value.matches("\\d+"))
            return Integer.parseInt(value);
        return backup;
    }
    
    /**
     * Method responsible for returning the Max by String Value.
     * @param  value String Value.
     * @param  backup Backup Min Value.
     * @return Max parsed.
     */
    private Integer getMax(String value, Integer backup) {
        if (value.equals("*"))
            return Integer.MAX_VALUE;
        if (value.matches("\\d+..\\d+"))
            return getValue(value.substring(value.lastIndexOf(".") + 1));
        if (value.matches("\\d+"))
            return Integer.parseInt(value);
        return backup;
    }
    
    /**
     * Method responsible for returning the Visibility by String Value.
     * @param  value String Value.
     * @param  backup Backup Value.
     * @return Visibility parsed.
     */
    private String getVisibility(String value, String backup) {
        if (checkAssociation(value))
            return getVisibility(value);
        return backup;
    }
    
    /**
     * Method responsible for returning the Association Name by String Value.
     * @param  value String Value.
     * @param  backup Backup Value.
     * @return Association Name parsed. 
     */
    private String getAssociationName(String value, String backup) {
        if (checkAssociation(value))
            return value.substring(1).trim();
        return backup;
    }
    
    /**
     * Method responsible for returning the Integer by Value.
     * @param  value String Value.
     * @return Integer parsed.
     */
    private Integer getValue(String value) {
        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException exception) {
            return Integer.MAX_VALUE;
        }
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return getPanel().getDiagram();
    }
    
    /**
     * Method responsible for returning the Panel Class Diagram.
     * @return Panel Class Diagram.
     */
    public PanelClassDiagram getPanel() {
        return panel;
    }
}