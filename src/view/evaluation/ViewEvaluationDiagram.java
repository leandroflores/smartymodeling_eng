package view.evaluation;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.panel.evaluation.PanelEvaluationDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEvaluationDiagram</b>.</p>
 * <p>Class responsible for defining the <b>View Evaluation Diagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    controller.view.evaluation.
 * @see    view.evaluation.ViewEvaluation
 * @see    view.panel.evaluation.PanelEvaluationProduct
 */
public final class ViewEvaluationDiagram extends ViewEvaluation { 
    private PanelEvaluationDiagram panelEvaluationDiagram;
    
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
        this.panelEvaluationDiagram = new PanelEvaluationDiagram(this);
        this.tabbedPane.removeAll();
        this.createScrollPane("scrollPanelEvaluationDiagram",  this.panelEvaluationDiagram);
        this.getScrollPanelEvaluationDiagram().setViewportView(this.panelEvaluationDiagram);
        this.tabbedPane.add("Evaluation", this.getScrollPanelEvaluationDiagram());
    }
    
    /**
     * Method responsible for returning the Panel Evaluation Diagram.
     * @return Panel Evaluation Diagram.
     */
    public PanelEvaluationDiagram getPanelEvaluationDiagram() {
        return this.panelEvaluationDiagram;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Evaluation Diagram.
     * @return Scroll Panel Evaluation Diagram.
     */
    public JScrollPane getScrollPanelEvaluationDiagram() {
        return this.scrollPanes.get("scrollPanelEvaluationDiagram");
    }
}