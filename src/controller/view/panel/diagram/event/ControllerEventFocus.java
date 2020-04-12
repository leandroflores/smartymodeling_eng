package controller.view.panel.diagram.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventFocus</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Focus Modeling Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/11/2019
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
        Object object = this.panel.getComponent().getCellAt(event.getX(), event.getY());
        String id     = this.panel.getIdentifiers().get(object);
        if (this.panel.getDiagram().getElement(id) != null)
            this.updatePanelEdit(this.panel.getDiagram().getElement(id)); 
        else if (this.panel.getDiagram().getAssociation(id) != null)
            this.updatePanelEdit(this.panel.getDiagram().getAssociation(id));
        this.panel.updateUI();
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param stereotype Stereotype.
     */
    private void updatePanelEdit(Stereotype stereotype) {
        this.panel.getViewMenu().getPanelProject().initPanelEditStereotype(stereotype);
        this.panel.getViewMenu().getPanelProject().updatePanelEdit();
        this.panel.getGraph().setSelectionCell(this.panel.getObjects().get(stereotype.getId()));
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param element Element selected.
     */
    private void updatePanelEdit(Element element) {
        this.panel.getViewMenu().getPanelProject().initPanelEditElement(this.panel.getDiagram(), element);
        this.panel.getViewMenu().getPanelProject().updatePanelEdit();
        this.panel.getGraph().setSelectionCell(this.panel.getObjects().get(element.getId()));
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param association Association selected.
     */
    private void updatePanelEdit(Association association) {
        this.panel.getViewMenu().getPanelProject().initPanelEditAssociation(this.panel.getDiagram(), association);
        this.panel.getViewMenu().getPanelProject().updatePanelEdit();
        this.panel.getGraph().setSelectionCell(this.panel.getObjects().get(association.getId()));
    }
}