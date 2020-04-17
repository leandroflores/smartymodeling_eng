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
        if (this.ready) {
            if (this.getPanel().getConstructorCheckBox().equals(event.getSource()))
                this.actionConstructor();
            else if (this.getPanel().getAbstractCheckBox().equals(event.getSource()))
                this.actionAbstract();
            else if (this.getPanel().getStaticCheckBox().equals(event.getSource()))
                this.actionStatic();
            else if (this.getPanel().getFinalCheckBox().equals(event.getSource()))
                this.actionFinal();
            this.update();
        }
    }
    
    /**
     * Method responsible for updating the Constructor.
     */
    private void actionConstructor() {
        this.getPanel().getNameTextField().setEnabled(!this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getNameTextField().setText(this.getMethod().getName().trim());
        this.getPanel().getReturnComboBox().setEnabled(!this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getAbstractCheckBox().setEnabled(!this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getAbstractCheckBox().setSelected(this.getPanel().getConstructorCheckBox().isSelected() ? false : this.getPanel().getAbstractCheckBox().isSelected());
        this.getPanel().getStaticCheckBox().setEnabled(!this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getStaticCheckBox().setSelected(this.getPanel().getConstructorCheckBox().isSelected()   ? false : this.getPanel().getStaticCheckBox().isSelected());
        this.getPanel().getFinalCheckBox().setEnabled(!this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getFinalCheckBox().setSelected(this.getPanel().getConstructorCheckBox().isSelected()    ? false : this.getPanel().getFinalCheckBox().isSelected());
    }

    /**
     * Method responsible for updating the Abstract.
     */
    private void actionAbstract() {
        this.getPanel().getReturnComboBox().setEnabled(this.getPanel().getAbstractCheckBox().isSelected());
        this.getPanel().getConstructorCheckBox().setEnabled(!this.getPanel().getAbstractCheckBox().isSelected());
        this.getPanel().getConstructorCheckBox().setSelected(this.getPanel().getAbstractCheckBox().isSelected() ? false : this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getStaticCheckBox().setEnabled(!this.getPanel().getAbstractCheckBox().isSelected());
        this.getPanel().getStaticCheckBox().setSelected(this.getPanel().getAbstractCheckBox().isSelected()      ? false : this.getPanel().getStaticCheckBox().isSelected());
        this.getPanel().getFinalCheckBox().setEnabled(!this.getPanel().getAbstractCheckBox().isSelected());
        this.getPanel().getFinalCheckBox().setSelected(this.getPanel().getAbstractCheckBox().isSelected()       ? false : this.getPanel().getFinalCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Static.
     */
    private void actionStatic() {
        this.getPanel().getReturnComboBox().setEnabled(this.getPanel().getStaticCheckBox().isSelected());
        this.getPanel().getConstructorCheckBox().setEnabled(!this.getPanel().getStaticCheckBox().isSelected());
        this.getPanel().getConstructorCheckBox().setSelected(this.getPanel().getStaticCheckBox().isSelected() ? false : this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getAbstractCheckBox().setEnabled(!this.getPanel().getStaticCheckBox().isSelected());
        this.getPanel().getAbstractCheckBox().setSelected(this.getPanel().getStaticCheckBox().isSelected()    ? false : this.getPanel().getAbstractCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Final.
     */
    private void actionFinal() {
        this.getPanel().getReturnComboBox().setEnabled(this.getPanel().getFinalCheckBox().isSelected());
        this.getPanel().getConstructorCheckBox().setEnabled(!this.getPanel().getFinalCheckBox().isSelected());
        this.getPanel().getConstructorCheckBox().setSelected(this.getPanel().getFinalCheckBox().isSelected() ? false : this.getPanel().getConstructorCheckBox().isSelected());
        this.getPanel().getAbstractCheckBox().setEnabled(!this.getPanel().getFinalCheckBox().isSelected());
        this.getPanel().getAbstractCheckBox().setSelected(this.getPanel().getFinalCheckBox().isSelected()    ? false : this.getPanel().getAbstractCheckBox().isSelected());
    }
    
    @Override
    protected void refresh() {
        this.getProject().changeNames(this.getMethod());
        super.refresh();
    }
    
    @Override
    protected void update() {
        this.getMethod().setVisibility(this.getValue(this.getPanel().getVisibilityComboBox()));
        this.getMethod().setName(this.getString(this.getPanel().getNameTextField()));
        this.getMethod().setConstructor(this.getPanel().getConstructorCheckBox().isSelected());
        this.getMethod().setReturn((TypeUML) this.getPanel().getReturnComboBox().getSelectedItem());
        this.getMethod().setAbstract(this.getPanel().getAbstractCheckBox().isSelected());
        this.getMethod().setStatic(this.getPanel().getStaticCheckBox().isSelected());
        this.getMethod().setFinal(this.getPanel().getFinalCheckBox().isSelected());
        this.refresh();
    }
    
    /**
     * Method responsible for returning the Method UML.
     * @return Method UML.
     */
    private MethodUML getMethod() {
        return this.getPanel().getElement();
    }
    
    @Override
    public PanelBaseMethodUML getPanel() {
        return (PanelBaseMethodUML) this.panel;
    }
}