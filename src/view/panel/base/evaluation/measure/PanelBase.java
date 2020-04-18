package view.panel.base.evaluation.measure;

import java.awt.Dimension;
import javax.swing.JButton;
import model.controller.structural.base.ControllerProject;
import model.controller.structural.base.evaluation.ControllerMetric;
import model.structural.base.evaluation.Measure;
import view.new_.base.ViewNew;
import view.new_.base.evaluation.ViewNewMeasure;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Evaluation Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.panel.base.evaluation.measure.ControllerPanelBase
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final ViewNew view;
    protected final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param view View New Measure.
     * @param measure Measure.
     */
    public PanelBase(ViewNewMeasure view, Measure measure) {
        super(view.getViewMenu());
        this.view    = view;
        this.measure = measure;
    }
    
    /**
     * Method responsible for returning the Targets Array.
     * @return Targets Array.
     */
    protected Object[] getTargets() {
        return new ControllerProject(this.project).getTargets(this.measure.getMetric().getTarget());
    }
    
    /**
     * Method responsible for returning the Metrics Array.
     * @return Metrics Array.
     */
    protected Object[] getMetrics() {
        return new ControllerMetric(this.project).getMetrics();
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    public void addFooter() {
        this.add(this.createButton("returnButton", " Return ", "Return", "back.png"));
        this.add(this.createButton("nextButton",   "  Next  ", " Next ", "next.png"));
        
        this.getReturnButton().setPreferredSize(new Dimension(150, 30));
        this.getNextButton().setPreferredSize(new Dimension(150, 30));
    }
    
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
     * Method responsible for returning the View New Measure.
     * @return View New Measure.
     */
    public ViewNewMeasure getViewNew() {
        return (ViewNewMeasure) this.view;
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
}