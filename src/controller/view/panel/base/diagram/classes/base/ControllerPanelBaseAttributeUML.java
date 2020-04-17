package controller.view.panel.base.diagram.classes.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.TypeUML;
import view.panel.base.diagram.classes.base.PanelBaseAttributeUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAttributeUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseAttributeUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-06
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    view.panel.base.diagram.classes.base.PanelBaseAttributeUML
 */
public class ControllerPanelBaseAttributeUML extends ControllerPanelBaseElement {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Attribute UML.
     */
    public ControllerPanelBaseAttributeUML(PanelBaseAttributeUML panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        this.getAttribute().setName(this.getString(this.getPanel().getNameTextField()));
        this.getAttribute().setTypeUML((TypeUML) this.getPanel().getTypeComboBox().getSelectedItem());
        this.getAttribute().setStatic(this.getPanel().getStaticCheckBox().isSelected());
        this.getAttribute().setFinal(this.getPanel().getFinalCheckBox().isSelected());
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Attribute UML.
     * @return Attribute UML.
     */
    private AttributeUML getAttribute() {
        return this.getPanel().getElement();
    }
    
    @Override
    public PanelBaseAttributeUML getPanel() {
        return (PanelBaseAttributeUML) this.panel;
    }
}