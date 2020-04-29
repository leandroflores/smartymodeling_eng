package controller.view.panel.base.evaluation;

import controller.view.panel.base.ControllerPanelBase;
import model.structural.base.evaluation.Measure;
import view.panel.base.evaluation.PanelBaseMeasure;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMeasure</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseMeasure</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.base.evaluation.PanelBaseMeasure
 */
public class ControllerPanelBaseMeasure extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Measure.
     */
    public ControllerPanelBaseMeasure(PanelBaseMeasure panel) {
        super(panel);
    }

    @Override
    protected void refresh() {
        this.getPanelTree().updateNode(this.getMeasure());
        super.refresh();
    }
    
    /**
     * Method responsible for checking the Measure.
     * @return Measure checked.
     */
    protected boolean check() {
        return this.check(this.getPanel().getNameTextField().getText());
    }
    
    @Override
    protected void update() {
        if (this.check()) {
            this.getMeasure().setName(this.getString(this.getPanel().getNameTextField()));
            this.refresh();
        }
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    private Measure getMeasure() {
        return this.getPanel().getMeasure();
    }
    
    @Override
    public PanelBaseMeasure getPanel() {
        return (PanelBaseMeasure) this.panel;
    }
}