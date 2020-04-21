package controller.view.new_.base.variability;

import controller.view.new_.ControllerViewNew;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.message.ViewError;
import view.new_.base.variability.ViewNewVariability;

/**
 * <p>Class of Controller <b>ControllerViewNewVariability</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
 * @see    controller.view.new_.ControllerViewNew
 * @see    javax.swing.event.ChangeListener
 * @see    model.structural.base.variability.Variability
 * @see    view.new_.base.variability.ViewNewVariability
 */
public class ControllerViewNewVariability extends ControllerViewNew implements ChangeListener {

    /**
     * Default constructor method of Class.
     * @param viewNew View New Variability.
     */
    public ControllerViewNewVariability(ViewNewVariability viewNew) {
        super(viewNew);
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        if (event.getSource() instanceof JTabbedPane) {
            if (((JTabbedPane) event.getSource()).getSelectedIndex() == 0) {
                this.getView().getPanelBaseVariability().updateUI();
            }else {
                this.getView().getPanelBaseVariants().updateVariantsList();
                this.getView().getPanelBaseVariants().updateValues();
            }
        }
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseVariability().getNameTextField(), "Name is required!")
            && this.checkVariants();
    }
    
    /**
     * Method responsible for checking the Variants.
     * @return Variants are checkeds.
     */
    public boolean checkVariants() {
        if (this.getView().getVariability().getVariants().isEmpty()) {
            new ViewError(this.getView(), "Add some Variant!").setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    public void new_() {
        this.getView().getDiagram().addVariability(this.getView().getVariability());
        this.getView().getViewMenu().setTabIndex(2);
        this.getView().getDiagram().updateElementsStereotype();
    }
    
    @Override
    public ViewNewVariability getView() {
        return (ViewNewVariability) this.viewModal;
    }
}