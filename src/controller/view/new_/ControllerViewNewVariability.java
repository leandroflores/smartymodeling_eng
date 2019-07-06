package controller.view.new_;

import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.structural.base.variability.Variability;
import view.message.ViewError;
import view.new_.ViewNewVariability;

/**
 * <p>Class of Controller <b>ControllerViewNewVariability</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewVariability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/07/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    view.new_.ViewNewVariability
 */
public class ControllerViewNewVariability extends ControllerViewNew implements ChangeListener {
    private final ViewNewVariability viewNewVariability;

    /**
     * Default constructor method of Class.
     * @param viewNew View New Variability.
     */
    public ControllerViewNewVariability(ViewNewVariability viewNew) {
        super(viewNew);
        this.viewNewVariability = viewNew;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        if (event.getSource() instanceof JTabbedPane) {
            if (((JTabbedPane) event.getSource()).getSelectedIndex() == 0) {
                this.viewNewVariability.getPanelBaseVariability().updateUI();
            }else {
                this.viewNewVariability.getPanelBaseVariants().updateVariantsList();
                this.viewNewVariability.getPanelBaseVariants().updateValues();
            }
        }
    }
    
    /**
     * Method responsible for checking the Variability Name.
     * @return Name is checked.
     */
    public boolean checkName() {
        return this.check(this.viewNewVariability.getPanelBaseVariability().getNameTextField(), "Name is required!");
    }
    
    /**
     * Method responsible for checking the Variation Point.
     * @return Variation Point is selected.
     */
    public boolean checkVariationPoint() {
        if (this.viewNewVariability.getVariability().getVariationPoint() == null) {
            new ViewError(this.viewNewVariability, "Select a Variation Point!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Variants.
     * @return Variants are checkeds.
     */
    public boolean checkVariants() {
        if (this.viewNewVariability.getVariability().getVariants().isEmpty()) {
            new ViewError(this.viewNewVariability, "Add some Variant!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean check() {
        return this.checkName()
            && this.checkVariationPoint()
            && this.checkVariants();
    }

    @Override
    public void insert() {
        Variability variability = this.viewNewVariability.getVariability();
        this.viewNewVariability.getDiagram().addVariability(variability);
        this.viewNewVariability.getViewMenu().getPanelModeling().updateDiagram(this.viewNewVariability.getDiagram());
        this.viewNewVariability.getViewMenu().update();
        this.viewNewVariability.dispose();
    }
}