package view.panel.tree.popup.base.evaluation;

import controller.view.panel.tree.popup.item.base.evaluation.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.evaluation.ControllerMenuItemEdit;
import controller.view.panel.tree.popup.item.base.evaluation.ControllerMenuItemNew;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import view.panel.tree.base.evaluation.PanelTreeEvaluation;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of View <b>TreePopupEvaluation</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    view.panel.tree.base.evaluation.PanelTreeEvaluation
 * @see    view.panel.tree.popup.TreePopup
 */
public final class TreePopupEvaluation extends TreePopup {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree Evalution.
     */
    public TreePopupEvaluation(PanelTreeEvaluation panel) {
        super(panel);
        this.addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        this.createMenuItem("metric",  "Metric",  "menu/evaluation/metric",  KeyEvent.VK_M, InputEvent.ALT_MASK);
        this.createMenuItem("measure", "Measure", "menu/evaluation/measure", KeyEvent.VK_S, InputEvent.ALT_MASK);
        
        this.getNewMenu().add(this.getMetricMenuItem());
        this.getNewMenu().add(this.getMeasureMenuItem());
    }
    
    @Override
    protected void setControllers() {
        this.getMetricMenuItem().addActionListener(new ControllerMenuItemNew(this));
        this.getMeasureMenuItem().addActionListener(new ControllerMenuItemNew(this));
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(this.getNewMenu());
        this.addSeparator();
        this.add(getEditMenuItem());
        this.addSeparator();
        this.add(getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the Metric Menu Item.
     * @return Metric Menu Item.
     */
    public JMenuItem getMetricMenuItem() {
        return this.getItems().get("metric");
    }
    
    /**
     * Method responsible for returning the Measure Menu Item.
     * @return Measure Menu Item.
     */
    public JMenuItem getMeasureMenuItem() {
        return this.getItems().get("measure");
    }
}