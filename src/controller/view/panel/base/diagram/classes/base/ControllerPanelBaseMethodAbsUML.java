package controller.view.panel.base.diagram.classes.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.TypeUML;
import view.panel.base.diagram.classes.base.PanelBaseMethodAbsUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMethodAbsUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseMethodAbsUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-27
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    view.panel.base.diagram.classes.base.PanelBaseMethodAbsUML
 */
public class ControllerPanelBaseMethodAbsUML extends ControllerPanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Abstract Method.
     */
    public ControllerPanelBaseMethodAbsUML(PanelBaseMethodAbsUML panel) {
        super(panel);
    }

    @Override
    protected void refresh() {
        getProject().changeNames(getMethod());
        super.refresh();
    }
    
    @Override
    protected void update() {
        getMethod().setVisibility(getValue(getPanel().getVisibilityComboBox()));
        getMethod().setName(getString(getPanel().getNameTextField()));
        getMethod().setReturn((TypeUML) getPanel().getReturnComboBox().getSelectedItem());
        refresh();
    }
    
    /**
     * Method responsible for returning the Method UML.
     * @return Method UML.
     */
    private MethodUML getMethod() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseMethodAbsUML getPanel() {
        return (PanelBaseMethodAbsUML) panel;
    }
}