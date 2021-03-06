package controller.view.panel.base.diagram.sequence.base.association;

import controller.view.panel.base.ControllerPanelBaseAssociation;
import java.awt.event.ActionEvent;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.base.diagram.sequence.base.association.PanelBaseMessageUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMessageUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseMessageUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-04
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    model.structural.diagram.sequence.base.association.MessageUML
 * @see    view.panel.base.diagram.sequence.base.association.PanelBaseMessageUML
 */
public class ControllerPanelBaseMessageUML extends ControllerPanelBaseAssociation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Message UML.
     */
    public ControllerPanelBaseMessageUML(PanelBaseMessageUML panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
    
    @Override
    protected void update() {
        getMessage().setName(getString(getPanel().getNameTextField()));
        getMessage().setMethod((MethodUML) getPanel().getMethodComboBox().getSelectedItem());
        refresh();
    }
    
    /**
     * Method responsible for returning the Message UML.
     * @return Message UML.
     */
    private MessageUML getMessage() {
        return getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseMessageUML getPanel() {
        return (PanelBaseMessageUML) panel;
    }
}