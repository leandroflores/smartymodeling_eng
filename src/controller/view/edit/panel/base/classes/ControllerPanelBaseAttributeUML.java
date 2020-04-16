package controller.view.edit.panel.base.classes;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classes.base.TypeUML;
import view.edit.panel.base.classes.PanelBaseAttributeUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAttributeUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseAttributeUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/06/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.classes.PanelBaseAttributeUML
 */
public class ControllerPanelBaseAttributeUML extends ControllerPanel {
    private final PanelBaseAttributeUML panelBaseAttributeUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Attribute UML.
     */
    public ControllerPanelBaseAttributeUML(PanelBaseAttributeUML panel) {
        super(panel);
        this.panelBaseAttributeUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
//        if (this.panelBaseAttributeUML.getNameTextField().equals(event.getSource()))
//            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseAttributeUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for setting the Attribute Values.
     */
    private void update() {
        this.panelBaseAttributeUML.getAttributeUML().setVisibility(this.panelBaseAttributeUML.getVisibilityComboBox().getSelectedItem().toString());
        this.panelBaseAttributeUML.getAttributeUML().setName(this.panelBaseAttributeUML.getNameTextField().getText().trim());
        this.panelBaseAttributeUML.getAttributeUML().setTypeUML((TypeUML) this.panelBaseAttributeUML.getTypeComboBox().getSelectedItem());
        this.panelBaseAttributeUML.getAttributeUML().setStatic(this.panelBaseAttributeUML.getStaticCheckBox().isSelected());
        this.panelBaseAttributeUML.getAttributeUML().setFinal(this.panelBaseAttributeUML.getFinalCheckBox().isSelected());
        this.panelBaseAttributeUML.getViewMenu().getPanelProject().getPanelTree().getPanelTreeDiagram().updateNode(this.panelBaseAttributeUML.getAttributeUML());
        this.panelBaseAttributeUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseAttributeUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseAttributeUML.getDiagram());
        this.panelBaseAttributeUML.getViewMenu().setSave(false);
    }
}