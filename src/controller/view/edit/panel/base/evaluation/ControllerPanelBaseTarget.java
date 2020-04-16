package controller.view.edit.panel.base.evaluation;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.Project;
import view.panel.base.evaluation.PanelBaseTarget;

/**
 * <p>Class of Controller <b>ControllerPanelBaseTarget</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseTarget</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/10/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.base.evaluation.PanelBaseTarget
 */
public class ControllerPanelBaseTarget extends ControllerPanel {
    private final PanelBaseTarget panelBaseTarget;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Target.
     */
    public ControllerPanelBaseTarget(PanelBaseTarget panel) {
        super(panel);
        this.panelBaseTarget = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.updateValues();
        if (this.panelBaseTarget.getBackButton().equals(event.getSource()))
            this.panelBaseTarget.getViewNewMeasure().removePanelBaseTarget();
        else if (this.panelBaseTarget.getNextButton().equals(event.getSource()))
            this.next();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.updateValues();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.updateValues();
    }
    
    /**
     * Method responsible for updating the Values.
     */
    public void updateValues() {
        Object target = this.panelBaseTarget.getTargetComboBox().getSelectedItem();
        if (target instanceof String)
            this.update(this.panelBaseTarget.getProject());
        else if (target instanceof Diagram)
            this.update((Diagram) target);
    }
    
    /**
     * Method responsible for going to Next Panel.
     */
    public void next() {
        this.panelBaseTarget.getViewNewMeasure().addPanelBaseEvaluation();
    }
    
    /**
     * Method responsible for updating the Values.
     * @param project Project.
     */
    private void update(Project project) {
        this.panelBaseTarget.getElementsTextField().setText(Integer.toString(project.getElementsList().size()));
        this.panelBaseTarget.getAssociationsTextField().setText(Integer.toString(project.getAssociationsList().size()));
        this.panelBaseTarget.getVariabilitiesTextField().setText(Integer.toString(project.getVariabilitiesList().size()));
    }
    
    /**
     * Method responsible for updating the Values.
     * @param diagram Diagram.
     */
    private void update(Diagram diagram) {
        this.panelBaseTarget.getElementsTextField().setText(Integer.toString(diagram.getElements().size()));
        this.panelBaseTarget.getAssociationsTextField().setText(Integer.toString(diagram.getAssociations().size()));
        this.panelBaseTarget.getVariabilitiesTextField().setText(Integer.toString(diagram.getVariabilitiesList().size()));
    }
}