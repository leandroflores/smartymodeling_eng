package controller.view.edit.panel.stereotype;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Link;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.edit.panel.stereotype.PanelStereotype;

/**
 * <p>Class of Controller <b>ControllerPanelStereotype</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelStereotype</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  31/05/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.stereotype.PanelStereotype
 */
public class ControllerPanelStereotype extends ControllerPanel {
    private final PanelStereotype panelStereotype;

    /**
     * Default constructor method of Class.
     * @param panel Panel Stereotype.
     */
    public ControllerPanelStereotype(PanelStereotype panel) {
        super(panel);
        this.panelStereotype = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelStereotype.getNewStereotypeButton().equals(event.getSource()))
            this.newStereotype();
        else if (this.panelStereotype.getAddStereotypeButton().equals(event.getSource()))
            this.addStereotype();
        else if (this.panelStereotype.getRemoveStereotypeButton().equals(event.getSource()))
            this.removeStereotype();
        this.updateSize(this.panelStereotype.getElement());
        this.panelStereotype.getViewMenu().getPanelModeling().updateModelingPanel();
        this.panelStereotype.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for updating the Element Size.
     * @param element Element.
     */
    private void updateSize(Element element) {
        if (element instanceof AttributeUML)
            ((AttributeUML) element).getEntity().updateSize();
        else if (element instanceof MethodUML)
            ((MethodUML) element).getEntity().updateSize();
        else if (element instanceof Entity)
            ((Entity) element).updateSize();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for adding a new Stereotype.
     */
    private void newStereotype() {
        String name = this.panelStereotype.getStereotypeTextField().getText().trim();
        if ((this.check(name)) && (this.panelStereotype.getProject().getStereotypeByName(name) == null)) {
            Stereotype stereotype = new Stereotype(name);
            this.panelStereotype.getProject().addStereotype(stereotype);
            this.panelStereotype.getProject().addLink(new Link(this.panelStereotype.getElement(), stereotype));
            this.panelStereotype.updateList();
            this.panelStereotype.updateComboBox();
            this.panelStereotype.getStereotypeTextField().setText("");
            this.panelStereotype.getStereotypeTextField().requestFocus();
        }
    }
    
    /**
     * Method responsible for adding a Stereotype.
     */
    private void addStereotype() {
        Stereotype stereotype = (Stereotype) this.panelStereotype.getStereotypeComboBox().getSelectedItem();
        this.panelStereotype.getProject().addLink(new Link(this.panelStereotype.getElement(), stereotype));
        this.panelStereotype.updateList();
    }
    
    /**
     * Method responsible for removing a Stereotype.
     */
    private void removeStereotype() {
        if (this.panelStereotype.getStereotypesList().getSelectedValue() != null) {
            Stereotype stereotype = (Stereotype) this.panelStereotype.getStereotypesList().getSelectedValue();
            this.panelStereotype.getProject().removeLink(this.panelStereotype.getProject().getLink(this.panelStereotype.getElement(), stereotype));
            this.panelStereotype.updateList();
        }
    }
}