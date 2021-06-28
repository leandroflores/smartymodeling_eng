package view.panel.edit.base.evaluation;

import model.structural.base.evaluation.Measure;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;
import view.panel.base.evaluation.PanelBaseMeasure;

/**
 * <p>Class of View <b>PanelEditMeasure</b>.</p> 
 * <p>Class responsible for defining a <b>Measure Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    model.structural.base.evaluation.Measure
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditMeasure extends PanelEdit {
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param measure Measure.
     */
    public PanelEditMeasure(ViewMenu view, Measure measure) {
        super(view);
        this.measure = measure;
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseMeasure();
    }
    
    /**
     * Method responsible for adding the Panel Base Measure.
     */
    protected void addPanelBaseMeasure() {
        addPanel("base_measure", new PanelBaseMeasure(viewMenu, measure));
        createScrollPane("base_measure", getPanelBaseMeasure());
        tabbedPane.add("Measure", getScrollPane("base_measure"));
    }
    
    /**
     * Method responsible for returning the Panel Base Measure.
     * @return Panel Base Measure.
     */
    public PanelBaseMeasure getPanelBaseMeasure() {
        return (PanelBaseMeasure) getPanel("base_measure");
    }
    
    /**
     * Method responsible for returning the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return measure;
    }
}