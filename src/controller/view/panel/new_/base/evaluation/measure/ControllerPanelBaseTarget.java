package controller.view.panel.new_.base.evaluation.measure;

import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.Project;
import view.panel.new_.base.evaluation.measure.PanelBaseTarget;

/**
 * <p>Class of Controller <b>ControllerPanelBaseTarget</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseTarget</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-23
 * @see    controller.view.panel.new_.base.evaluation.measure.ControllerPanelBase
 * @see    view.panel.new_.base.evaluation.measure.PanelBaseTarget
 */
public class ControllerPanelBaseTarget extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Target.
     */
    public ControllerPanelBaseTarget(PanelBaseTarget panel) {
        super(panel);
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    @Override
    protected void return_() {
        this.getPanel().getPanelNew().removePanelBaseTarget();
    }
    
    @Override
    protected boolean check() {
        return true;
    }
    
    @Override
    public void next() {
        this.getPanel().getPanelNew().addPanelBaseEvaluation();
    }
    
    @Override
    public void update() {
        Object target = this.getPanel().getTargetComboBox().getSelectedItem();
        if (target instanceof String)
            this.update(this.getPanel().getProject());
        else if (target instanceof Diagram)
            this.update((Diagram) target);
    }
    
    /**
     * Method responsible for updating the Values.
     * @param project Project.
     */
    private void update(Project project) {
        this.getMeasure().setTarget("Project");
        this.getPanel().getElementsTextField().setText(Integer.toString(project.getElementsList().size()));
        this.getPanel().getAssociationsTextField().setText(Integer.toString(project.getAssociationsList().size()));
        this.getPanel().getVariabilitiesTextField().setText(Integer.toString(project.getVariabilitiesList().size()));
    }
    
    /**
     * Method responsible for updating the Values.
     * @param diagram Diagram.
     */
    private void update(Diagram diagram) {
        this.getMeasure().setTarget(diagram.getName());
        this.getPanel().getElementsTextField().setText(Integer.toString(diagram.getElements().size()));
        this.getPanel().getAssociationsTextField().setText(Integer.toString(diagram.getAssociations().size()));
        this.getPanel().getVariabilitiesTextField().setText(Integer.toString(diagram.getVariabilitiesList().size()));
    }
    
    @Override
    public PanelBaseTarget getPanel() {
        return (PanelBaseTarget) this.panel;
    }
}