package controller.view.panel.base.diagram.classes.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import java.awt.event.ActionEvent;
import model.structural.diagram.classes.base.ClassUML;
import view.panel.base.diagram.classes.base.PanelBaseClassUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseClassUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseClassUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    view.panel.base.diagram.classes.base.PanelBaseClassUML
 */
public class ControllerPanelBaseClassUML extends ControllerPanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Class.
     */
    public ControllerPanelBaseClassUML(PanelBaseClassUML panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.ready) {
            if (this.getPanel().getAbstractCheckBox().equals(event.getSource()))
                this.actionAbstract();
            else if (this.getPanel().getFinalCheckBox().equals(event.getSource()))
                this.actionFinal();
        }
    }

    /**
     * Method responsible for updating the Abstract.
     */
    private void actionAbstract() {
        this.getPanel().getFinalCheckBox().setEnabled(!this.getPanel().getAbstractCheckBox().isSelected());
        this.getPanel().getFinalCheckBox().setSelected(this.getPanel().getAbstractCheckBox().isSelected() ? false : this.getPanel().getFinalCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Final.
     */
    private void actionFinal() {
        this.getPanel().getAbstractCheckBox().setEnabled(!this.getPanel().getFinalCheckBox().isSelected());
        this.getPanel().getAbstractCheckBox().setSelected(this.getPanel().getFinalCheckBox().isSelected() ? false : this.getPanel().getAbstractCheckBox().isSelected());
    }
    
    @Override
    protected void update() {
        this.getClassUML().setName(this.getString(this.getPanel().getNameTextField()));
        this.getClassUML().setAbstract(this.getPanel().getAbstractCheckBox().isSelected());
        this.getClassUML().setFinal(this.getPanel().getFinalCheckBox().isSelected());
        this.getClassUML().setMandatory(this.getPanel().getMandatoryCheckBox().isSelected());
        this.getDiagram().updateStereotype(this.getClassUML());
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Class UML.
     * @return Class UML.
     */
    private ClassUML getClassUML() {
        return this.getPanel().getElement();
    }
    
    @Override
    public PanelBaseClassUML getPanel() {
        return (PanelBaseClassUML) this.panel;
    }
}