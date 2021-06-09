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
        if (ready && getPanel().getAbstractCheckBox().equals(event.getSource()))
            actionAbstract();
        else if (ready && getPanel().getFinalCheckBox().equals(event.getSource()))
            actionFinal();
    }

    /**
     * Method responsible for updating the Abstract.
     */
    private void actionAbstract() {
        getPanel().getFinalCheckBox().setEnabled(!getPanel().getAbstractCheckBox().isSelected());
        getPanel().getFinalCheckBox().setSelected(getPanel().getAbstractCheckBox().isSelected() ? false : getPanel().getFinalCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Final.
     */
    private void actionFinal() {
        getPanel().getAbstractCheckBox().setEnabled(!getPanel().getFinalCheckBox().isSelected());
        getPanel().getAbstractCheckBox().setSelected(getPanel().getFinalCheckBox().isSelected() ? false : getPanel().getAbstractCheckBox().isSelected());
    }
    
    @Override
    protected void update() {
        getClassUML().setName(getString(getPanel().getNameTextField()));
        getClassUML().setAbstract(getPanel().getAbstractCheckBox().isSelected());
        getClassUML().setFinal(getPanel().getFinalCheckBox().isSelected());
        getClassUML().setMandatory(getPanel().getMandatoryCheckBox().isSelected());
        getDiagram().updateStereotype(getClassUML());
        refresh();
    }
    
    /**
     * Method responsible for returning the Class UML.
     * @return Class UML.
     */
    private ClassUML getClassUML() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseClassUML getPanel() {
        return (PanelBaseClassUML) panel;
    }
}