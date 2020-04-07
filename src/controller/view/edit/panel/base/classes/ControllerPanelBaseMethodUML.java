package controller.view.edit.panel.base.classes;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classes.base.TypeUML;
import view.edit.panel.base.classes.PanelBaseMethodUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMethodUML</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseMethodUML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/06/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.classes.PanelBaseMethodUML
 */
public class ControllerPanelBaseMethodUML extends ControllerPanel {
    private final PanelBaseMethodUML panelBaseMethodUML;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Method.
     */
    public ControllerPanelBaseMethodUML(PanelBaseMethodUML panel) {
        super(panel);
        this.panelBaseMethodUML = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseMethodUML.getConstructorCheckBox().equals(event.getSource()))
            this.actionConstructor();
        else if (this.panelBaseMethodUML.getAbstractCheckBox().equals(event.getSource()))
            this.actionAbstract();
        else if (this.panelBaseMethodUML.getStaticCheckBox().equals(event.getSource()))
            this.actionStatic();
        else if (this.panelBaseMethodUML.getFinalCheckBox().equals(event.getSource()))
            this.actionFinal();
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseMethodUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseMethodUML.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for updating the Constructor.
     */
    private void actionConstructor() {
        this.panelBaseMethodUML.getNameTextField().setEnabled(!this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getNameTextField().setText(this.panelBaseMethodUML.getMethodUML().getName().trim());
        this.panelBaseMethodUML.getReturnComboBox().setEnabled(!this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getAbstractCheckBox().setEnabled(!this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getAbstractCheckBox().setSelected(this.panelBaseMethodUML.getConstructorCheckBox().isSelected() ? false : this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
        this.panelBaseMethodUML.getStaticCheckBox().setEnabled(!this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getStaticCheckBox().setSelected(this.panelBaseMethodUML.getConstructorCheckBox().isSelected()   ? false : this.panelBaseMethodUML.getStaticCheckBox().isSelected());
        this.panelBaseMethodUML.getFinalCheckBox().setEnabled(!this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getFinalCheckBox().setSelected(this.panelBaseMethodUML.getConstructorCheckBox().isSelected()    ? false : this.panelBaseMethodUML.getFinalCheckBox().isSelected());
    }

    /**
     * Method responsible for updating the Abstract.
     */
    private void actionAbstract() {
        this.panelBaseMethodUML.getReturnComboBox().setEnabled(this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
        this.panelBaseMethodUML.getConstructorCheckBox().setEnabled(!this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
        this.panelBaseMethodUML.getConstructorCheckBox().setSelected(this.panelBaseMethodUML.getAbstractCheckBox().isSelected() ? false : this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getStaticCheckBox().setEnabled(!this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
        this.panelBaseMethodUML.getStaticCheckBox().setSelected(this.panelBaseMethodUML.getAbstractCheckBox().isSelected()      ? false : this.panelBaseMethodUML.getStaticCheckBox().isSelected());
        this.panelBaseMethodUML.getFinalCheckBox().setEnabled(!this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
        this.panelBaseMethodUML.getFinalCheckBox().setSelected(this.panelBaseMethodUML.getAbstractCheckBox().isSelected()       ? false : this.panelBaseMethodUML.getFinalCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Static.
     */
    private void actionStatic() {
        this.panelBaseMethodUML.getReturnComboBox().setEnabled(this.panelBaseMethodUML.getStaticCheckBox().isSelected());
        this.panelBaseMethodUML.getConstructorCheckBox().setEnabled(!this.panelBaseMethodUML.getStaticCheckBox().isSelected());
        this.panelBaseMethodUML.getConstructorCheckBox().setSelected(this.panelBaseMethodUML.getStaticCheckBox().isSelected() ? false : this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getAbstractCheckBox().setEnabled(!this.panelBaseMethodUML.getStaticCheckBox().isSelected());
        this.panelBaseMethodUML.getAbstractCheckBox().setSelected(this.panelBaseMethodUML.getStaticCheckBox().isSelected()    ? false : this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
    }
    
    /**
     * Method responsible for updating the Final.
     */
    private void actionFinal() {
        this.panelBaseMethodUML.getReturnComboBox().setEnabled(this.panelBaseMethodUML.getFinalCheckBox().isSelected());
        this.panelBaseMethodUML.getConstructorCheckBox().setEnabled(!this.panelBaseMethodUML.getFinalCheckBox().isSelected());
        this.panelBaseMethodUML.getConstructorCheckBox().setSelected(this.panelBaseMethodUML.getFinalCheckBox().isSelected() ? false : this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getAbstractCheckBox().setEnabled(!this.panelBaseMethodUML.getFinalCheckBox().isSelected());
        this.panelBaseMethodUML.getAbstractCheckBox().setSelected(this.panelBaseMethodUML.getFinalCheckBox().isSelected()    ? false : this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
    }
    
    /**
     * Method responsible for setting the Method Values.
     */
    private void update() {
        this.panelBaseMethodUML.getMethodUML().setVisibility(this.panelBaseMethodUML.getVisibilityComboBox().getSelectedItem().toString());
        this.panelBaseMethodUML.getMethodUML().setName(this.panelBaseMethodUML.getNameTextField().getText().trim());
        this.panelBaseMethodUML.getMethodUML().setConstructor(this.panelBaseMethodUML.getConstructorCheckBox().isSelected());
        this.panelBaseMethodUML.getMethodUML().setReturn((TypeUML) this.panelBaseMethodUML.getReturnComboBox().getSelectedItem());
        this.panelBaseMethodUML.getMethodUML().setAbstract(this.panelBaseMethodUML.getAbstractCheckBox().isSelected());
        this.panelBaseMethodUML.getMethodUML().setStatic(this.panelBaseMethodUML.getStaticCheckBox().isSelected());
        this.panelBaseMethodUML.getMethodUML().setFinal(this.panelBaseMethodUML.getFinalCheckBox().isSelected());
        this.panelBaseMethodUML.getViewMenu().getPanelProject().getPanelTree().getPanelTreeUML().updateNode(this.panelBaseMethodUML.getMethodUML());
        this.panelBaseMethodUML.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseMethodUML.getViewMenu().getProject().changeNames(this.panelBaseMethodUML.getMethodUML());
        this.panelBaseMethodUML.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseMethodUML.getDiagram());
        this.panelBaseMethodUML.getViewMenu().setSave(false);
    }
}