package controller.view.panel.base.diagram.sequence.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.usecase.base.ActorUML;
import view.panel.base.diagram.sequence.base.PanelBaseLifelineUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseLifelineUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseLifelineUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-03
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    view.panel.base.diagram.sequence.base.PanelBaseLifelineUML
 */
public class ControllerPanelBaseLifelineUML extends ControllerPanelBaseElement {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Lifeline UML.
     */
    public ControllerPanelBaseLifelineUML(PanelBaseLifelineUML panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getInstance().setName(getString(getPanel().getNameTextField()));
        getInstance().setActor((ActorUML) getPanel().getActorComboBox().getSelectedItem());
        getInstance().setMandatory(getPanel().getMandatoryCheckBox().isSelected());
        getDiagram().updateStereotype(getInstance());
        refresh();
    }
    
    /**
     * Method responsible for returning the Lifeline UML.
     * @return Lifeline UML.
     */
    private LifelineUML getInstance() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseLifelineUML getPanel() {
        return (PanelBaseLifelineUML) panel;
    }
}