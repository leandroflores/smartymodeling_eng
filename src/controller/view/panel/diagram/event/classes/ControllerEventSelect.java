package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventSelect</b>.</p>
 * <p>Class responsible for defining the <b>Select Events</b> in <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventSelect extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventSelect(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        if (getPanel().getGraph().getSelectionCell() != null) {
            if (getPanel().getGraph().getSelectionCell() instanceof mxCell) {
                mxCell cell = (mxCell) getPanel().getGraph().getSelectionCell();
                String id   = getPanel().getIdentifiers().get(cell);
                select(cell, id);
            }
        }
    }
    
    /**
     * Method responsible for selecting the Element.
     * @param cell Graph Cell.
     * @param id Element Id.
     */
    private void select(mxCell cell, String id) {
        if (getDiagram().getProject().getStereotype(id) != null)
            updateEditPanel(getDiagram().getProject().getStereotype(id));
        else if (cell.getId().endsWith("(newAttribute)"))
            newAttribute(cell, (Entity) getDiagram().getElement(id));
        else if (cell.getId().endsWith("(newMethod)"))
            newMethod(cell, (Entity) getDiagram().getElement(id));
        else if (getDiagram().getAssociation(id) != null)
            updatePoints(getDiagram().getAssociation(id), cell);
    }
    
    /**
     * Method responsible for updating the Edit Panel Stereotype.
     * @param stereotype Edit Panel Stereotype.
     */
    private void updateEditPanel(Stereotype stereotype) {
        getPanel().getViewMenu().getPanelProject().initPanelEditStereotype(stereotype);
    }
    
    /**
     * Method responsible for adding a new Attribute to a Entity.
     * @param cell Graph Cell.
     * @param entity Entity.
     */
    private void newAttribute(mxCell cell, Entity entity) {
        AttributeUML attribute = new AttributeUML(entity.getDiagram());
                     getDiagram().addAttribute(attribute);
                     attribute.setEntity(entity);
                     attribute.setTypeUML(getDiagram().getObjectType());
                     entity.addAttribute(attribute);
                     attribute.setDefaultName();
                     entity.updateSize();
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for adding a new Method to a Entity.
     * @param cell Graph Cell.
     * @param entity Entity.
     */
    private void newMethod(mxCell cell, Entity entity) {
        MethodUML method = new MethodUML(entity.getDiagram());
                  method.setId(getDiagram().nextMethodId());
                  method.setEntity(entity);
                  method.setReturn(getDiagram().getVoidType());
                  entity.addMethod(method);
                  getDiagram().addMethod(method);
                  method.setDefaultName();
                  entity.updateSize();
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for updating the Association Points.
     * @param association 
     */
    private void updatePoints(Association association, mxCell edge) {
        mxGeometry geometry = ((mxGraphModel) (getPanel().getGraph().getModel())).getGeometry(edge);
                   association.setPoints(geometry.getPoints());
        getPanel().getViewMenu().setSave(false);
    }
     
    /**
     * Method responsible for updating the Edit Panel.
     * @param cell Graph Cell.
     * @param id Object Id.
     */
    private void updateEditPanel(mxCell cell) {
        String id = getPanel().getIdentifiers().get(cell);
        if (getDiagram().getElement(id) != null)
            getPanel().getViewMenu().getPanelProject().initPanelEditElement(getDiagram(), getDiagram().getElement(id));
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