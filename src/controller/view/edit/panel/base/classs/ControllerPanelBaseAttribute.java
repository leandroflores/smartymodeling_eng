package controller.view.edit.panel.base.classs;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classs.base.TypeUML;
import view.edit.panel.base.classs.PanelBaseAttribute;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAttribute</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseAttribute</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/06/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.classs.PanelBaseAttribute
 */
public class ControllerPanelBaseAttribute extends ControllerPanel {
    private final PanelBaseAttribute panelBaseAttribute;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Attribute.
     */
    public ControllerPanelBaseAttribute(PanelBaseAttribute panel) {
        super(panel);
        this.panelBaseAttribute = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseAttribute.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for setting the Attribute Values.
     */
    private void update() {
        this.panelBaseAttribute.getAttribute().setVisibility(this.panelBaseAttribute.getVisibilityComboBox().getSelectedItem().toString());
        this.panelBaseAttribute.getAttribute().setName(this.panelBaseAttribute.getNameTextField().getText().trim());
        this.panelBaseAttribute.getAttribute().setTypeUML((TypeUML) this.panelBaseAttribute.getTypeComboBox().getSelectedItem());
        this.panelBaseAttribute.getAttribute().setStatic(this.panelBaseAttribute.getStaticCheckBox().isSelected());
        this.panelBaseAttribute.getAttribute().setFinal(this.panelBaseAttribute.getFinalCheckBox().isSelected());
        
        this.panelBaseAttribute.setValues();
    }
}