package controller.view.panel.base.stereotype;

import controller.view.panel.base.ControllerPanelBase;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Link;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.base.stereotype.PanelStereotype;

/**
 * <p>Class of Controller <b>ControllerPanelStereotype</b>.</p>
 * <p>Class responsible for controlling the <b>PanelStereotype</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.Stereotype
 * @see    view.panel.base.stereotype.PanelStereotype
 */
public class ControllerPanelStereotype extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Stereotype.
     */
    public ControllerPanelStereotype(PanelStereotype panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getPanel().getNewStereotypeButton().equals(event.getSource()))
            newStereotype();
        else if (getPanel().getAddStereotypeButton().equals(event.getSource()))
            addStereotype();
        else if (getPanel().getRemoveStereotypeButton().equals(event.getSource()))
            removeStereotype();
        update();
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
    
    @Override
    protected void update() {
        updateSize(getPanel().getElement());
        getPanel().getViewMenu().getPanelModeling().updateModelingPanels();
        refresh();
    }
    
    /**
     * Method responsible for adding a new Stereotype.
     */
    private void newStereotype() {
        String name = getPanel().getStereotypeTextField().getText().trim();
        if ((check(name)) && (getPanel().getProject().getStereotypeByName(name) == null)) {
            Stereotype stereotype = new Stereotype(name);
            getPanel().getProject().addStereotype(stereotype);
            getPanel().getProject().addLink(new Link(getPanel().getElement(), stereotype));
            getPanel().updateList();
            getPanel().updateComboBox();
            getPanel().getStereotypeTextField().setText("");
            getPanel().getStereotypeTextField().requestFocus();
        }
    }
    
    /**
     * Method responsible for adding a Stereotype.
     */
    private void addStereotype() {
        Stereotype stereotype = (Stereotype) getPanel().getStereotypeComboBox().getSelectedItem();
        getPanel().getProject().addLink(new Link(getPanel().getElement(), stereotype));
        getPanel().updateList();
    }
    
    /**
     * Method responsible for removing a Stereotype.
     */
    private void removeStereotype() {
        if (getPanel().getStereotypesList().getSelectedValue() != null) {
            Stereotype stereotype = (Stereotype) getPanel().getStereotypesList().getSelectedValue();
            getPanel().getProject().removeLink(getPanel().getProject().getLink(getPanel().getElement(), stereotype));
            getPanel().updateList();
        }
    }
    
    @Override
    public PanelStereotype getPanel() {
        return (PanelStereotype) this.panel;
    }
}