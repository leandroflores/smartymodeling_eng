package controller.view.edit.panel.base.classes.association;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.classes.association.PanelBaseAssociationUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAssociationUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseAssociationUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  16/11/2019
 * @see    controller.view.ControllerPanel
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.edit.panel.base.classes.association.PanelBaseAssociationUML
 */
public class ControllerPanelBaseAssociationUML extends ControllerPanel {
    private final PanelBaseAssociationUML panelBaseAssociationUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Association UML.
     */
    public ControllerPanelBaseAssociationUML(PanelBaseAssociationUML panel) {
        super(panel);
        this.panelBaseAssociationUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    /**
     * Method responsible for setting the Association UML Values.
     */
    private void update() {
        this.panelBaseAssociationUML.getAssociationUML().setName(this.panelBaseAssociationUML.getNameTextField().getText().trim());
        this.panelBaseAssociationUML.getAssociationUML().setDirection(this.panelBaseAssociationUML.getDirectedCheckBox().isSelected());
        this.panelBaseAssociationUML.updatePanelBaseTarget();
        this.panelBaseAssociationUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseAssociationUML.getViewMenu().getPanelModeling().updateModelingPanel();
        this.panelBaseAssociationUML.getViewMenu().setSave(false);
    }
}