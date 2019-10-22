package view.new_.evaluation;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.edit.panel.base.evaluation.PanelBaseEvaluation;
import view.edit.panel.base.evaluation.PanelBaseMeasure;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewMeasure</b>.</p>
 * <p>Class responsible for defining the <b>New Measure View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/09/2019
 * @see    controller.view.new_.evaluation.
 * @see    model.structural.base.evaluation.Measure
 * @see    view.new_.ViewNew
 */
public final class ViewNewMeasure extends ViewNew { 
    private final Project project;
    private final Measure measure;
    private PanelBaseMeasure    panelBaseMeasure;
    private PanelBaseEvaluation panelBaseEvaluation;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewNewMeasure(ViewMenu view, Project project) {
        super(view);
        this.project    = project;
        this.measure    = new Measure();
//        this.controller = new ControllerViewNewMetric(this);
        this.title      = "New Measure";
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
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));
        
        this.addPanelBaseMeasure();
        this.addPanelBaseEvaluation();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Measure.
     */
    private void addPanelBaseMeasure() {
        this.panelBaseMeasure = new PanelBaseMeasure(this.getViewMenu(), this.measure);
        this.createScrollPane("scrollPanelBaseMeasure", this.panelBaseMeasure);
        this.getScrollPanelBaseMeasure().setViewportView(this.panelBaseMeasure);
        this.tabbedPane.add("Measure", this.getScrollPanelBaseMeasure());
    }
    
    /**
     * Method responsible for adding the Panel Base Evaluation.
     */
    private void addPanelBaseEvaluation() {
        this.panelBaseEvaluation  = new PanelBaseEvaluation(this.getViewMenu(), this.measure);
        this.createScrollPane("scrollPanelEvaluation",  this.panelBaseEvaluation);
        this.getScrollPanelEvaluation().setViewportView(this.panelBaseEvaluation);
        this.tabbedPane.add("Evaluation", this.getScrollPanelEvaluation());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return this.panelBaseMeasure;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Measure.
     * @return Scroll Panel Base Measure.
     */
    public JScrollPane getScrollPanelBaseMeasure() {
        return this.scrollPanes.get("scrollPanelBaseMeasure");
    }
    
    /**
     * Method responsible for returning the Panel Base Evaluation.
     * @return Panel Base Evaluation.
     */
    public PanelBaseEvaluation getPanelBaseEvaluation() {
        return this.panelBaseEvaluation;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Evaluation.
     * @return Scroll Panel Evaluation.
     */
    public JScrollPane getScrollPanelEvaluation() {
        return this.scrollPanes.get("scrollPanelEvaluation");
    }
}