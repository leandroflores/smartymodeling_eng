package view.panel.new_.base.evaluation.measure;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.controller.structural.base.ControllerProject;
import model.controller.structural.base.evaluation.ControllerMetric;
import model.structural.base.evaluation.Measure;
import view.modal.new_.base.evaluation.ViewNewMeasure;
import view.panel.new_.PanelNew;
import view.panel.new_.base.evaluation.PanelNewMeasure;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Evaluation Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.panel.base.evaluation.measure.ControllerPanelBase
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final PanelNew panelNew;
    protected final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Measure.
     * @param measure Measure.
     */
    public PanelBase(PanelNewMeasure panel, Measure measure) {
        super(panel.getViewMenu());
        this.panelNew = panel;
        this.measure  = measure;
    }
    
    /**
     * Method responsible for returning the Targets Array.
     * @return Targets Array.
     */
    protected Object[] getTargets() {
        return new ControllerProject(this.project).getDiagramTargets(this.measure.getMetric().getTarget());
    }
    
    /**
     * Method responsible for returning the Metrics Array.
     * @return Metrics Array.
     */
    protected Object[] getMetrics() {
        return new ControllerMetric(this.project).getMetrics();
    }
    
    /**
     * Method responsible for returning the Panel Footer.
     * @return Panel Footer.
     */
    protected JPanel getFooter() {
        JPanel footer = new JPanel();
               footer.setLayout(new GridLayout(1, 2));
               footer.add(this.createButton("returnButton", " Return ", "Return", "back.png", new Dimension(175, 30)));
               footer.add(this.createButton("nextButton",   "  Next  ", " Next ", "next.png", new Dimension(175, 30)));
        return footer;
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    public abstract void addFooter();
    
    /**
     * Method responsible for returning the Return Button.
     * @return Return Button.
     */
    public JButton getReturnButton() {
        return this.getButton("returnButton");
    }
    
    /**
     * Method responsible for returning the Next Button.
     * @return Next Button.
     */
    public JButton getNextButton() {
        return this.getButton("nextButton");
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
    
    /**
     * Method responsible for returning the View New Measure.
     * @return View New Measure.
     */
    public ViewNewMeasure getViewNew() {
        return (ViewNewMeasure) this.getPanelNew().getView();
    }
    
    /**
     * Method responsible for returning the Panel New Measure.
     * @return Panel New Measure.
     */
    public PanelNewMeasure getPanelNew() {
        return (PanelNewMeasure) this.panelNew;
    }
}