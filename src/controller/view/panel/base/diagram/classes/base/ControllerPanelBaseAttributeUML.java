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
 * @see    model.structural.diagram.classes.base.AttributeUML
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
        getAttribute().setName(getString(getPanel().getNameTextField()));
        getAttribute().setTypeUML((TypeUML) getPanel().getTypeComboBox().getSelectedItem());
        getAttribute().setStatic(getPanel().getStaticCheckBox().isSelected());
        getAttribute().setFinal(getPanel().getFinalCheckBox().isSelected());
        refresh();
    }
    
    /**
     * Method responsible for returning the Attribute UML.
     * @return Attribute UML.
     */
    private AttributeUML getAttribute() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseAttributeUML getPanel() {
        return (PanelBaseAttributeUML) panel;
    }
}