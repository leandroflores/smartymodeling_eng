package controller.view.modal.new_.base.variability;

import controller.view.modal.new_.ControllerViewNew;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.modal.message.ViewError;
import view.modal.new_.base.variability.ViewNewVariability;

/**
 * <p>Class of Controller <b>ControllerViewNewVariability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    javax.swing.event.ChangeListener
 * @see    model.structural.base.variability.Variability
 * @see    view.modal.new_.base.variability.ViewNewVariability
 */
public class ControllerViewNewVariability extends ControllerViewNew implements ChangeListener {

    /**
     * Default constructor method of Class.
     * @param view View New Variability.
     */
    public ControllerViewNewVariability(ViewNewVariability view) {
        super(view);
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        if (event.getSource() instanceof JTabbedPane) {
            if (((JTabbedPane) event.getSource()).getSelectedIndex() == 0) {
                getView().getPanelBaseVariability().updateUI();
            }else {
                getView().getPanelBaseVariants().updateVariantsList();
                getView().getPanelBaseVariants().updateValues();
            }
        }
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseVariability().getNameTextField(), "Name is required!")
            && checkVariants();
    }
    
    /**
     * Method responsible for checking the Variants.
     * @return Variants are checkeds.
     */
    public boolean checkVariants() {
        if (getView().getVariability().getVariants().isEmpty()) {
            new ViewError(getView(), "Add some Variant!").setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    public void new_() {
        getView().getDiagram().addVariability(getView().getVariability());
        getView().getViewMenu().setTabIndex(2);
        getView().getDiagram().updateElementsStereotype();
    }
    
    @Override
    public ViewNewVariability getView() {
        return (ViewNewVariability) super.getView();
    }
}