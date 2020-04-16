package controller.view.edit.panel.base;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.PanelBaseElement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseElement</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseElement</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/06/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.PanelBaseElement
 */
public class ControllerPanelBaseElement extends ControllerPanel {
    private final PanelBaseElement panelBaseElement;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Element.
     */
    public ControllerPanelBaseElement(PanelBaseElement panel) {
        super(panel);
        this.panelBaseElement = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
//        if (this.panelBaseElement.getNameTextField().equals(event.getSource()))
//            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseElement.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for checking the Diagram.
     * @return Diagram checked.
     */
    private boolean check() {
        return this.check(this.panelBaseElement.getNameTextField().getText());
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    private void update() {
        this.panelBaseElement.getElement().setName(this.panelBaseElement.getNameTextField().getText().trim());
        this.panelBaseElement.getElement().setMandatory(this.panelBaseElement.getMandatoryCheckBox().isSelected());
        this.panelBaseElement.getDiagram().updateStereotype(this.panelBaseElement.getElement());
        this.panelBaseElement.getViewMenu().getPanelProject().getPanelTree().updateNode(this.panelBaseElement.getElement());
        this.panelBaseElement.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseElement.getDiagram());
        this.panelBaseElement.getViewMenu().getPanelModeling().setSelected(this.panelBaseElement.getDiagram(), this.panelBaseElement.getElement().getId());
        this.panelBaseElement.getViewMenu().getPanelModeling().updateInstancePanels();
        this.panelBaseElement.getViewMenu().setSave(false);
    }
}