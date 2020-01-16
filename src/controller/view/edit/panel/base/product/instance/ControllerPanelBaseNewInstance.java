package controller.view.edit.panel.base.product.instance;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.product.Product;
import view.edit.panel.base.product.instance.PanelBaseNewInstance;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerPanelBaseNewInstance</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseNewInstance</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.ControllerPanel
 * @see    model.structural.base.product.Instance
 * @see    view.edit.panel.base.product.instance.PanelBaseNewInstance
 */
public class ControllerPanelBaseNewInstance extends ControllerPanel {
    private final PanelBaseNewInstance panelBaseNewInstance;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base New Instance.
     */
    public ControllerPanelBaseNewInstance(PanelBaseNewInstance panel) {
        super(panel);
        this.panelBaseNewInstance = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
        if (this.panelBaseNewInstance.getBackButton().equals(event.getSource()))
            this.panelBaseNewInstance.getViewNewInstance().dispose();
        else if (this.panelBaseNewInstance.getNextButton().equals(event.getSource()))
            this.next();
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
     * Method responsible for checking the Product.
     * @return Product checked.
     */
    private boolean checkProduct() {
        if (this.panelBaseNewInstance.getProductComboBox().getSelectedItem() == null) {
            new ViewError(this.panelBaseNewInstance.getViewNewInstance(), "Select a Product!").setVisible(true);
            this.panelBaseNewInstance.getProductComboBox().requestFocus();
            return false;
        }
        return true;
    }

    /**
     * Method responsible for checking the Diagram.
     * @return Diagram checked.
     */
    private boolean checkDiagram() {
        if (this.panelBaseNewInstance.getDiagramComboBox().getSelectedItem() == null) {
            new ViewError(this.panelBaseNewInstance.getViewNewInstance(), "Select a Diagram!").setVisible(true);
            this.panelBaseNewInstance.getDiagramComboBox().requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Parameters.
     * @return Parameters checked.
     */
    private boolean check() {
        return this.checkProduct()
            && this.checkDiagram();
    }
    
    /**
     * Method responsible for going to Next Panel.
     */
    public void next() {
        if (this.check()) {
            this.update();
            this.panelBaseNewInstance.getViewNewInstance().addPanelBaseOptional();
        }
    }
    
    /**
     * Method responsible for setting the Instance Values.
     */
    private void update() {
        this.panelBaseNewInstance.getInstance().setProduct((Product) this.panelBaseNewInstance.getProductComboBox().getSelectedItem());
        this.panelBaseNewInstance.getInstance().setDiagram((Diagram) this.panelBaseNewInstance.getDiagramComboBox().getSelectedItem());
        this.panelBaseNewInstance.getInstance().setName(this.panelBaseNewInstance.getNameTextField().getText().trim());
        this.panelBaseNewInstance.getViewNewInstance().getViewMenu().setSave(false);
    }
}