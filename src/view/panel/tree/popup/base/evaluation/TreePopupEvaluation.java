package view.panel.tree.popup.base.evaluation;

import controller.view.panel.tree.popup.item.base.evaluation.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.base.evaluation.ControllerMenuItemEdit;
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
    protected void setControllers() {
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    @Override
    protected void addMenuItems() {
        this.add(getEditMenuItem());
        this.add(getDeleteMenuItem());
    }
}