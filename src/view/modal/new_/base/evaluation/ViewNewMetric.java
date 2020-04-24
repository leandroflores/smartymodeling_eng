package view.modal.new_.base.evaluation;

import controller.view.modal.new_.base.evaluation.ControllerViewNewMetric;
import java.awt.Dimension;
import model.structural.base.evaluation.Metric;
import view.modal.new_.ViewNew;
import view.panel.base.evaluation.PanelBaseMetric;
import view.panel.base.evaluation.PanelBaseOperation;
import view.main.structural.ViewMenu;
import view.panel.edit.base.evaluation.PanelEditMetric;

/**
 * <p>Class of View <b>ViewNewMetric</b>.</p>
 * <p>Class responsible for defining the <b>New Metric View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-20
 * @see    controller.view.modal.new_.base.evaluation.ControllerViewNewMetric
 * @see    model.structural.base.evaluation.Metric
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewMetric extends ViewNew {
    private final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewMetric(ViewMenu view) {
        super(view);
        this.metric     = new Metric();
        this.controller = new ControllerViewNewMetric(this);
        this.title      = "New Metric";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditMetric();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Metric.
     */
    private void addPanelEditMetric() {
        this.addPanel("panelEditMetric", new PanelEditMetric(this.view, this.metric));
        this.getPanel("panelEditMetric").setPreferredSize(new Dimension(500, 325));
        this.add(this.getPanel("panelEditMetric"));
    }
    
    /**
     * Method responsible for returning the Panel Base Metric.
     * @return Panel Base Metric.
     */
    public PanelBaseMetric getPanelBaseMetric() {
        return ((PanelEditMetric) this.getPanel("panelEditMetric")).getPanelBaseMetric();
    }
    
    /**
     * Method responsible for returning the Panel Base Operation.
     * @return Panel Base Operation.
     */
    public PanelBaseOperation getPanelBaseOperation() {
        return ((PanelEditMetric) this.getPanel("panelEditMetric")).getPanelBaseOperation();
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
}