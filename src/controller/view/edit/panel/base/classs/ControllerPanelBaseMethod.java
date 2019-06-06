package controller.view.edit.panel.base.classs;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classs.base.TypeUML;
import view.edit.panel.base.classs.PanelBaseMethod;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMethod</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseMethod</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/06/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.classs.PanelBaseMethod
 */
public class ControllerPanelBaseMethod extends ControllerPanel {
    private final PanelBaseMethod panelBaseMethod;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Method.
     */
    public ControllerPanelBaseMethod(PanelBaseMethod panel) {
        super(panel);
        this.panelBaseMethod = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseMethod.getConstructorCheckBox().equals(event.getSource()))
            this.actionConstructor();
        else if (this.panelBaseMethod.getAbstractCheckBox().equals(event.getSource()))
            this.actionAbstract();
        else if (this.panelBaseMethod.getStaticCheckBox().equals(event.getSource()))
            this.actionStatic();
        else if (this.panelBaseMethod.getFinalCheckBox().equals(event.getSource()))
            this.actionFinal();
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseMethod.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for updating the Constructor.
     */
    private void actionConstructor() {
        this.panelBaseMethod.getNameTextField().setEnabled(!this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getNameTextField().setText(this.panelBaseMethod.getConstructorCheckBox().isSelected() ? this.panelBaseMethod.getMethod().getEntity().getName(): this.panelBaseMethod.getMethod().getName());
        this.panelBaseMethod.getReturnComboBox().setEnabled(!this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getAbstractCheckBox().setEnabled(!this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getAbstractCheckBox().setSelected(this.panelBaseMethod.getConstructorCheckBox().isSelected() ? false : this.panelBaseMethod.getAbstractCheckBox().isSelected());
        this.panelBaseMethod.getStaticCheckBox().setEnabled(!this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getStaticCheckBox().setSelected(this.panelBaseMethod.getConstructorCheckBox().isSelected()   ? false : this.panelBaseMethod.getStaticCheckBox().isSelected());
        this.panelBaseMethod.getFinalCheckBox().setEnabled(!this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getFinalCheckBox().setSelected(this.panelBaseMethod.getConstructorCheckBox().isSelected()    ? false : this.panelBaseMethod.getFinalCheckBox().isSelected());
    }

    /**
     * Method responsible for updating the Abstract.
     */
    private void actionAbstract() {
        this.panelBaseMethod.getReturnComboBox().setEnabled(this.panelBaseMethod.getAbstractCheckBox().isSelected());
        this.panelBaseMethod.getConstructorCheckBox().setEnabled(!this.panelBaseMethod.getAbstractCheckBox().isSelected());
        this.panelBaseMethod.getConstructorCheckBox().setSelected(this.panelBaseMethod.getAbstractCheckBox().isSelected() ? false : this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getStaticCheckBox().setEnabled(!this.panelBaseMethod.getAbstractCheckBox().isSelected());
        this.panelBaseMethod.getStaticCheckBox().setSelected(this.panelBaseMethod.getAbstractCheckBox().isSelected()      ? false : this.panelBaseMethod.getStaticCheckBox().isSelected());
        this.panelBaseMethod.getFinalCheckBox().setEnabled(!this.panelBaseMethod.getAbstractCheckBox().isSelected());
        this.panelBaseMethod.getFinalCheckBox().setSelected(this.panelBaseMethod.getAbstractCheckBox().isSelected()       ? false : this.panelBaseMethod.getFinalCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Static.
     */
    private void actionStatic() {
        this.panelBaseMethod.getReturnComboBox().setEnabled(this.panelBaseMethod.getStaticCheckBox().isSelected());
        this.panelBaseMethod.getConstructorCheckBox().setEnabled(!this.panelBaseMethod.getStaticCheckBox().isSelected());
        this.panelBaseMethod.getConstructorCheckBox().setSelected(this.panelBaseMethod.getStaticCheckBox().isSelected() ? false : this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getAbstractCheckBox().setEnabled(!this.panelBaseMethod.getStaticCheckBox().isSelected());
        this.panelBaseMethod.getAbstractCheckBox().setSelected(this.panelBaseMethod.getStaticCheckBox().isSelected()    ? false : this.panelBaseMethod.getAbstractCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Final.
     */
    private void actionFinal() {
        this.panelBaseMethod.getReturnComboBox().setEnabled(this.panelBaseMethod.getFinalCheckBox().isSelected());
        this.panelBaseMethod.getConstructorCheckBox().setEnabled(!this.panelBaseMethod.getFinalCheckBox().isSelected());
        this.panelBaseMethod.getConstructorCheckBox().setSelected(this.panelBaseMethod.getFinalCheckBox().isSelected() ? false : this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getAbstractCheckBox().setEnabled(!this.panelBaseMethod.getFinalCheckBox().isSelected());
        this.panelBaseMethod.getAbstractCheckBox().setSelected(this.panelBaseMethod.getFinalCheckBox().isSelected()    ? false : this.panelBaseMethod.getAbstractCheckBox().isSelected());
    }
    
    /**
     * Method responsible for setting the Method Values.
     */
    private void update() {
        this.panelBaseMethod.getMethod().setVisibility(this.panelBaseMethod.getVisibilityComboBox().getSelectedItem().toString());
        this.panelBaseMethod.getMethod().setName(this.panelBaseMethod.getNameTextField().getText().trim());
        this.panelBaseMethod.getMethod().setConstructor(this.panelBaseMethod.getConstructorCheckBox().isSelected());
        this.panelBaseMethod.getMethod().setReturn((TypeUML) this.panelBaseMethod.getReturnComboBox().getSelectedItem());
        this.panelBaseMethod.getMethod().setAbstract(this.panelBaseMethod.getAbstractCheckBox().isSelected());
        this.panelBaseMethod.getMethod().setStatic(this.panelBaseMethod.getStaticCheckBox().isSelected());
        this.panelBaseMethod.getMethod().setFinal(this.panelBaseMethod.getFinalCheckBox().isSelected());
        
        this.panelBaseMethod.setValues();
    }
}