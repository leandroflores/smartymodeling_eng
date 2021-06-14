package controller.view.panel.diagram.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventFocus</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Focus Event</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    java.awt.event.MouseAdapter
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventFocus extends MouseAdapter {
    private final PanelDiagram panel;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventFocus(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        Object object = getPanel().getComponent().getCellAt(event.getX(), event.getY());
        String id     = getPanel().getIdentifiers().get(object);
        if (getPanel().getDiagram().getElement(id) != null)
            updatePanelEdit(getPanel().getDiagram().getElement(id)); 
        else if (getPanel().getDiagram().getAssociation(id) != null)
            updatePanelEdit(getPanel().getDiagram().getAssociation(id));
        getPanel().updateUI();
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param stereotype Stereotype.
     */
    private void updatePanelEdit(Stereotype stereotype) {
        getPanel().getViewMenu().getPanelProject().initPanelEditStereotype(stereotype);
        getPanel().getViewMenu().getPanelProject().updatePanelEdit();
        getPanel().getGraph().setSelectionCell(getPanel().getObjects().get(stereotype.getId()));
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param element Element selected.
     */
    private void updatePanelEdit(Element element) {
        getPanel().getViewMenu().getPanelProject().initPanelEditElement(getPanel().getDiagram(), element);
        getPanel().getViewMenu().getPanelProject().updatePanelEdit();
        getPanel().getGraph().setSelectionCell(getPanel().getObjects().get(element.getId()));
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param association Association selected.
     */
    private void updatePanelEdit(Association association) {
        getPanel().getViewMenu().getPanelProject().initPanelEditAssociation(getPanel().getDiagram(), association);
        getPanel().getViewMenu().getPanelProject().updatePanelEdit();
        getPanel().getGraph().setSelectionCell(getPanel().getObjects().get(association.getId()));
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanel() {
        return panel;
    }
}