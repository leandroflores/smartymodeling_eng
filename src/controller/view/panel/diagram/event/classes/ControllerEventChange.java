package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.ParameterUML;
import model.structural.diagram.classes.base.TypeUML;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventChange</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Changing Events</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
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
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.getId(cell);
            this.change(cell, id);
        this.panel.updateDiagram();
        this.panel.getViewMenu().getPanelModeling().updateInstancePanels();
        this.panel.getViewMenu().getPanelProject().updateUI();
    }
    
    /**
     * Method responsible for changing the Selected Element.
     * @param object Selected Object.
     * @param id Element Id.
     */
    private void change(Object object, String id) {
        if (this.panel.getDiagram().getAssociation(id)  != null)
            this.changeNameAssociation(object, this.panel.getDiagram().getAssociation(id));
        else if (this.panel.getDiagram().getElement(id) instanceof AttributeUML)
            this.changeAttribute(object, (AttributeUML) this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) instanceof MethodUML)
            this.changeMethod(object,    (MethodUML)    this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) != null)
            this.changeElement(object,   (Element)      this.panel.getDiagram().getElement(id));
        else if (id != null)
            this.changeAssociation(object, id);
    }
    
    /**
     * Method responsible for changing the Attribute UML.
     * @param object Graph Object.
     * @param attribute Attribute UML.
     */
    private void changeAttribute(Object object, AttributeUML attribute) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (this.checkAttribute(signature)) {
            attribute.setVisibility(this.getVisibility(signature));
            attribute.setName(this.getName(signature, ":"));
            attribute.setTypeUML(this.getType(signature, true));
            this.panel.updateDiagram();
            this.panel.getViewMenu().update();
            this.panel.getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for checking the Attribute Signature.
     * @param  signature Attribute Signature.
     * @return Attribute Signature checked.
     */
    private boolean checkAttribute(String signature) {
        return     this.checkVisibility(signature) 
               &&  signature.contains(":")
               && !this.getName(signature, ":").equals("");
    }
    
    /**
     * Method responsible for changing the Method UML.
     * @param object Graph Object.
     * @param method Method UML.
     */
    private void changeMethod(Object object, MethodUML method) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (this.checkMethod(signature)) {
            method.setVisibility(this.getVisibility(signature));
            method.setName(this.getName(signature, "("));
            method.setParameters(this.getParameters(signature));
            method.setReturn(this.getType(signature, false));
            this.panel.updateDiagram();
            this.panel.getViewMenu().getProject().changeNames(method);
            this.panel.getViewMenu().update();
            this.panel.getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for checking the Method Signature.
     * @param  signature Method Signature.
     * @return Signature checked.
     */
    private boolean checkMethod(String signature) {
        return     this.checkVisibility(signature) 
               &&  signature.contains("(")
               &&  signature.contains(")")
               &&  signature.indexOf("(") < signature.indexOf(")")
               && !this.getName(signature, "(").equals("")
               &&  signature.contains(":");
    }
    
    /**
     * Method responsible for changing the Element.
     * @param object Graph Object.
     * @param element Element.
     */
    private void changeElement(Object object, Element element) {
        mxCell cell = (mxCell) object;
        if (cell.getValue().toString().equals("") == false) {
            element.setName(cell.getValue().toString());
            this.panel.updateDiagram();
            this.panel.getViewMenu().update();
            this.panel.getViewMenu().setSave(false);
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
        Association association = this.getAssociation(id);
        if (association != null) {
            if (id.endsWith("(source)"))
                this.changeSourceAssociation((AssociationUML) association, (mxCell) object);
            else if (id.endsWith("(target)"))
                this.changeTargetAssociation((AssociationUML) association, (mxCell) object);
        }
    }
    
    /**
     * Method responsible for returning the Association UML by Id.
     * @param  id Cell Id.
     * @return Association UML.
     */
    private Association getAssociation(String id) {
        String newId = id.contains("(") ? id.substring(0, id.indexOf("(")) : "";
        return this.panel.getDiagram().getAssociation(newId);
    }
    
    /**
     * Method responsible for changing the Source Association.
     * @param association Association UML.
     * @param cell Graph Cell.
     */
    private void changeSourceAssociation(AssociationUML association, mxCell cell) {
        String value       = cell.getValue().toString().trim();
        String cardinality = this.getCardinality(value);
        String signature   = this.getSignature(value, "");
               association.setSourceMin(this.getMin(cardinality, association.getSourceMin()));
               association.setSourceMax(this.getMax(cardinality, association.getSourceMax()));
               association.setSourceMax(this.getMax(cardinality, association.getSourceMax()));
               association.setSourceVisibility(this.getVisibility(signature, association.getSourceVisibility()));
               association.setSourceName(this.getAssociationName(signature, association.getSourceName()));
        this.panel.getViewMenu().getPanelProject().updatePanelEdit();
    }
    
    /**
     * Method responsible for changing the Target Association.
     * @param association Association UML.
     * @param cell Graph Cell.
     */
    private void changeTargetAssociation(AssociationUML association, mxCell cell) {
        String value       = cell.getValue().toString().trim();
        String cardinality = this.getCardinality(value);
        String signature   = this.getSignature(value, "");
               association.setTargetMin(this.getMin(cardinality, association.getTargetMin()));
               association.setTargetMax(this.getMax(cardinality, association.getTargetMax()));
               association.setTargetVisibility(this.getVisibility(signature, association.getTargetVisibility()));
               association.setTargetName(this.getAssociationName(signature, association.getTargetName()));
        this.panel.getViewMenu().getPanelProject().getPanelEdit().updateUI();
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
        if (this.checkSignature(value))
            return value.substring(value.indexOf("(") + 1, value.indexOf(")")).trim();
        return backup;
    }
    
    /**
     * Method responsible for checking the Value Signature.
     * @param  value Cell Value.
     * @return Signature checked.
     */
    private boolean checkSignature(String value) {
        return value.contains("(")
            && value.contains(")")
            && value.indexOf("(") < value.indexOf(")");
    }
    
    private boolean checkAssociation(String value) {
        return this.checkVisibility(value);
    }
    
    /**
     * Method responsible for checking the Signature Visibility.
     * @param  signature Signature Visibility.
     * @return Signature Visibility checked.
     */
    private boolean checkVisibility(String signature) {
        return signature.startsWith("+")
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
            return this.panel.getDiagram().getProject().getTypeByName(signature.substring(signature.trim().lastIndexOf(":") + 1).trim());
        return object ? this.panel.getDiagram().getObjectType() : this.panel.getDiagram().getVoidType();
    }
    
    /**
     * Method responsible for returning the Parameters Signature by Signature.
     * @param  signature Signature.
     * @return Parameters Signature.
     */
    private String getParametersSignature(String signature) {
        return signature.substring(signature.indexOf("(") + 1, signature.indexOf(")")).trim();
    }
    
    /**
     * Method responsible for returning the Parameters List by Signature.
     * @param  signature Method Signature.
     * @return Parameters List.
     */
    private String[] getParametersList(String signature) {
        return this.getParametersSignature(signature).split("\\,");
    }
    
    /**
     * Method responsible for returning the Parameters List by Method Signature.
     * @param  signature Method Signature.
     * @return Parameters List.
     */
    private List<ParameterUML> getParameters(String signature) {
        List<ParameterUML> parameters = new ArrayList<>();
        for (String string : this.getParametersList(signature)) {
            ParameterUML parameter = this.getParameter(string.trim());
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
                     parameter.setName(this.getParameterName(signature));
                     parameter.setType(this.getParameterType(signature));
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
            return this.panel.getDiagram().getProject().getTypeByName(signature.substring(signature.lastIndexOf(":") + 1).trim());
        return this.panel.getDiagram().getObjectType();
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (this.panel.getIdentifiers().get(cell) != null)
            return this.panel.getIdentifiers().get(cell);
        return this.getCellId((mxCell) cell);
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
            return this.getValue(value.substring(value.lastIndexOf(".") + 1));
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
        if (this.checkAssociation(value))
            return this.getVisibility(value);
        return backup;
    }
    
    /**
     * Method responsible for returning the Association Name by String Value.
     * @param  value String Value.
     * @param  backup Backup Value.
     * @return Association Name parsed. 
     */
    private String getAssociationName(String value, String backup) {
        if (this.checkAssociation(value))
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
}