package controller.view.panel.base.diagram.sequence.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.sequence.base.InstanceUML;
import view.panel.base.diagram.sequence.base.PanelBaseInstanceUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseInstanceUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseInstanceUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-03
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    model.structural.diagram.sequence.base.InstanceUML
 * @see    view.panel.base.diagram.sequence.base.PanelBaseInstanceUML
 */
public class ControllerPanelBaseInstanceUML extends ControllerPanelBaseElement {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Instance UML.
     */
    public ControllerPanelBaseInstanceUML(PanelBaseInstanceUML panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getInstance().setName(getString(getPanel().getNameTextField()));
        getInstance().setClassUML((ClassUML) getPanel().getClassComboBox().getSelectedItem());
        getInstance().setMandatory(getPanel().getMandatoryCheckBox().isSelected());
        getDiagram().updateStereotype(getInstance());
        refresh();
    }
    
    /**
     * Method responsible for returning the Instance UML.
     * @return Instance UML.
     */
    private InstanceUML getInstance() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseInstanceUML getPanel() {
        return (PanelBaseInstanceUML) panel;
    }
}