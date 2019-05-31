package controller.view.edit.panel.stereotype;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Stereotype;
import model.structural.base.association.Link;
import view.edit.panel.stereotype.PanelStereotype;

/**
 * <p>Class of Controller <b>ControllerPanelStereotype</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelStereotype</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  31/05/2019
 * @see    controller.view.ControllerPanel
 * @see    view.panel.diagram.PanelDiagram
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
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for adding a new Stereotype.
     */
    private void newStereotype() {
        String name = this.panelStereotype.getStereotypeTextField().getText().trim();
        if ((this.check(name)) && (this.panelStereotype.getProject().getStereotypeByName(name) == null)) {
            this.panelStereotype.getProject().addStereotype(new Stereotype(name));
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