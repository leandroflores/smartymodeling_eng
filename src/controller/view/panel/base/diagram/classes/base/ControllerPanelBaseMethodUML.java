package controller.view.panel.base.diagram.classes.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import java.awt.event.ActionEvent;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.TypeUML;
import view.panel.base.diagram.classes.base.PanelBaseMethodUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMethodUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseMethodUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.panel.base.diagram.classes.base.PanelBaseMethodUML
 */
public class ControllerPanelBaseMethodUML extends ControllerPanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Method.
     */
    public ControllerPanelBaseMethodUML(PanelBaseMethodUML panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (ready) {
            if (getPanel().getConstructorCheckBox().equals(event.getSource()))
                actionConstructor();
            else if (getPanel().getAbstractCheckBox().equals(event.getSource()))
                actionAbstract();
            else if (getPanel().getStaticCheckBox().equals(event.getSource()))
                actionStatic();
            else if (getPanel().getFinalCheckBox().equals(event.getSource()))
                actionFinal();
            update();
        }
    }
    
    /**
     * Method responsible for updating the Constructor.
     */
    private void actionConstructor() {
        getPanel().getNameTextField().setEnabled(!getPanel().getConstructorCheckBox().isSelected());
        getPanel().getNameTextField().setText(getMethod().getName().trim());
        getPanel().getReturnComboBox().setEnabled(!getPanel().getConstructorCheckBox().isSelected());
        getPanel().getAbstractCheckBox().setEnabled(!getPanel().getConstructorCheckBox().isSelected());
        getPanel().getAbstractCheckBox().setSelected(getPanel().getConstructorCheckBox().isSelected() ? false : getPanel().getAbstractCheckBox().isSelected());
        getPanel().getStaticCheckBox().setEnabled(!getPanel().getConstructorCheckBox().isSelected());
        getPanel().getStaticCheckBox().setSelected(getPanel().getConstructorCheckBox().isSelected()   ? false : getPanel().getStaticCheckBox().isSelected());
        getPanel().getFinalCheckBox().setEnabled(!getPanel().getConstructorCheckBox().isSelected());
        getPanel().getFinalCheckBox().setSelected(getPanel().getConstructorCheckBox().isSelected()    ? false : getPanel().getFinalCheckBox().isSelected());
    }

    /**
     * Method responsible for updating the Abstract.
     */
    private void actionAbstract() {
        getPanel().getReturnComboBox().setEnabled(getPanel().getAbstractCheckBox().isSelected());
        getPanel().getConstructorCheckBox().setEnabled(!getPanel().getAbstractCheckBox().isSelected());
        getPanel().getConstructorCheckBox().setSelected(getPanel().getAbstractCheckBox().isSelected() ? false : getPanel().getConstructorCheckBox().isSelected());
        getPanel().getStaticCheckBox().setEnabled(!getPanel().getAbstractCheckBox().isSelected());
        getPanel().getStaticCheckBox().setSelected(getPanel().getAbstractCheckBox().isSelected() ? false : getPanel().getStaticCheckBox().isSelected());
        getPanel().getFinalCheckBox().setEnabled(!getPanel().getAbstractCheckBox().isSelected());
        getPanel().getFinalCheckBox().setSelected(getPanel().getAbstractCheckBox().isSelected()  ? false : getPanel().getFinalCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Static.
     */
    private void actionStatic() {
        getPanel().getReturnComboBox().setEnabled(getPanel().getStaticCheckBox().isSelected());
        getPanel().getConstructorCheckBox().setEnabled(!getPanel().getStaticCheckBox().isSelected());
        getPanel().getConstructorCheckBox().setSelected(getPanel().getStaticCheckBox().isSelected() ? false : getPanel().getConstructorCheckBox().isSelected());
        getPanel().getAbstractCheckBox().setEnabled(!getPanel().getStaticCheckBox().isSelected());
        getPanel().getAbstractCheckBox().setSelected(getPanel().getStaticCheckBox().isSelected() ? false : getPanel().getAbstractCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Final.
     */
    private void actionFinal() {
        getPanel().getReturnComboBox().setEnabled(getPanel().getFinalCheckBox().isSelected());
        getPanel().getConstructorCheckBox().setEnabled(!getPanel().getFinalCheckBox().isSelected());
        getPanel().getConstructorCheckBox().setSelected(getPanel().getFinalCheckBox().isSelected() ? false : getPanel().getConstructorCheckBox().isSelected());
        getPanel().getAbstractCheckBox().setEnabled(!getPanel().getFinalCheckBox().isSelected());
        getPanel().getAbstractCheckBox().setSelected(getPanel().getFinalCheckBox().isSelected() ? false : getPanel().getAbstractCheckBox().isSelected());
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
        getMethod().setConstructor(getPanel().getConstructorCheckBox().isSelected());
        getMethod().setReturn((TypeUML) getPanel().getReturnComboBox().getSelectedItem());
        getMethod().setAbstract(getPanel().getAbstractCheckBox().isSelected());
        getMethod().setStatic(getPanel().getStaticCheckBox().isSelected());
        getMethod().setFinal(getPanel().getFinalCheckBox().isSelected());
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
    public PanelBaseMethodUML getPanel() {
        return (PanelBaseMethodUML) panel;
    }
}