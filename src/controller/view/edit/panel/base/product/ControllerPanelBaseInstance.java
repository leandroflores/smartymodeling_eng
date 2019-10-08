package controller.view.edit.panel.base.product;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.product.Product;
import view.edit.panel.base.product.PanelBaseInstance;

/**
 * <p>Class of Controller <b>ControllerPanelBaseInstance</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseInstance</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.ControllerPanel
 * @see    model.structural.base.product.Instance
 * @see    view.edit.panel.base.product.PanelBaseInstance
 */
public class ControllerPanelBaseInstance extends ControllerPanel {
    private final PanelBaseInstance panelBaseInstance;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Instance.
     */
    public ControllerPanelBaseInstance(PanelBaseInstance panel) {
        super(panel);
        this.panelBaseInstance = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }

    /**
     * Method responsible for checking the Instance.
     * @return Instance checked.
     */
    private boolean check() {
        return this.check(this.panelBaseInstance.getNameTextField().getText());
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    private void update() {
        this.panelBaseInstance.getInstance().setProduct((Product) this.panelBaseInstance.getProductComboBox().getSelectedItem());
        this.panelBaseInstance.getInstance().setDiagram((Diagram) this.panelBaseInstance.getDiagramComboBox().getSelectedItem());
        this.panelBaseInstance.getInstance().setName(this.panelBaseInstance.getNameTextField().getText().trim());
        this.panelBaseInstance.getViewMenu().setSave(false);
    }
}