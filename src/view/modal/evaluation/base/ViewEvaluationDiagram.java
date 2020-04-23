package view.modal.evaluation.base;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.evaluation.ViewEvaluation;
import view.panel.evaluation.base.PanelEvaluationDiagram;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Diagram View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    controller.view.modal.evaluation.ControllerViewEvaluationDiagram
 * @see    view.modal.evaluation.ViewEvaluation
 * @see    view.panel.evaluation.PanelEvaluationProduct
 */
public final class ViewEvaluationDiagram extends ViewEvaluation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewEvaluationDiagram(ViewMenu view, Project project) {
        super(view);
//        this.controller = new ControllerViewEvaluationDi(this);
        this.title      = "Evaluation Diagram";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 520);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addTabbedPane();
            this.addPanelEvaluationDiagram();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Tabbed Pane.
     */
    private void addTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(500, 400));
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Evaluation Diagram.
     */
    public void addPanelEvaluationDiagram() {
        this.addPanel("panelEvaluationDiagram", new PanelEvaluationDiagram(this));
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelEvaluationDiagram",  this.getPanelEvaluationDiagram());
        this.getScrollPane("scrollPanelEvaluationDiagram").setViewportView(this.getPanelEvaluationDiagram());
        this.tabbedPane.add("Evaluation", this.getScrollPane("scrollPanelEvaluationDiagram"));
    }
    
    /**
     * Method responsible for returning the Panel Evaluation Diagram.
     * @return Panel Evaluation Diagram.
     */
    public PanelEvaluationDiagram getPanelEvaluationDiagram() {
        return (PanelEvaluationDiagram) this.getPanel("panelEvaluationDiagram");
    }
}