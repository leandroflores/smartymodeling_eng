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
        if (this.getPanel().getNewStereotypeButton().equals(event.getSource()))
            this.newStereotype();
        else if (this.getPanel().getAddStereotypeButton().equals(event.getSource()))
            this.addStereotype();
        else if (this.getPanel().getRemoveStereotypeButton().equals(event.getSource()))
            this.removeStereotype();
        this.update();
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
        this.updateSize(this.getPanel().getElement());
        this.getPanel().getViewMenu().getPanelModeling().updateModelingPanels();
        super.refresh();
    }
    
    /**
     * Method responsible for adding a new Stereotype.
     */
    private void newStereotype() {
        String name = this.getPanel().getStereotypeTextField().getText().trim();
        if ((this.check(name)) && (this.getPanel().getProject().getStereotypeByName(name) == null)) {
            Stereotype stereotype = new Stereotype(name);
            this.getPanel().getProject().addStereotype(stereotype);
            this.getPanel().getProject().addLink(new Link(this.getPanel().getElement(), stereotype));
            this.getPanel().updateList();
            this.getPanel().updateComboBox();
            this.getPanel().getStereotypeTextField().setText("");
            this.getPanel().getStereotypeTextField().requestFocus();
        }
    }
    
    /**
     * Method responsible for adding a Stereotype.
     */
    private void addStereotype() {
        Stereotype stereotype = (Stereotype) this.getPanel().getStereotypeComboBox().getSelectedItem();
        this.getPanel().getProject().addLink(new Link(this.getPanel().getElement(), stereotype));
        this.getPanel().updateList();
    }
    
    /**
     * Method responsible for removing a Stereotype.
     */
    private void removeStereotype() {
        if (this.getPanel().getStereotypesList().getSelectedValue() != null) {
            Stereotype stereotype = (Stereotype) this.getPanel().getStereotypesList().getSelectedValue();
            this.getPanel().getProject().removeLink(this.getPanel().getProject().getLink(this.getPanel().getElement(), stereotype));
            this.getPanel().updateList();
        }
    }
    
    @Override
    public PanelStereotype getPanel() {
        return (PanelStereotype) this.panel;
    }
}