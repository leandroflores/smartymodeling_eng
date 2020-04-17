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
        this.update();
    }
    
    @Override
    protected void update() {
        this.getMessage().setName(this.getString(this.getPanel().getNameTextField()));
        this.getMessage().setMethod((MethodUML) this.getPanel().getMethodComboBox().getSelectedItem());
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Message UML.
     * @return Message UML.
     */
    private MessageUML getMessage() {
        return this.getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseMessageUML getPanel() {
        return (PanelBaseMessageUML) this.panel;
    }
}