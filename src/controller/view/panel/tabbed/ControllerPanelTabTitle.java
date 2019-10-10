package controller.view.panel.tabbed;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import model.structural.base.product.test.Product_Final;
import view.panel.tabbed.PanelTabTitle;

/**
 * <p>Class of Controller <b>ControllerPanelTabTitle</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Panel Tab Title</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  31/05/2019
 * @see    controller.view.ControllerPanel
 * @see    view.panel.tabbed.PanelTabTitle
 */
public class ControllerPanelTabTitle extends ControllerPanel {
    private final PanelTabTitle panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Tab Title.
     */
    public ControllerPanelTabTitle(PanelTabTitle panel) {
        super(panel);
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panel.getCloseButton().equals(event.getSource()))
            this.closeTab();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    private Diagram getDiagram() {
        return this.panel.getPanelModeling().getPanelDiagram().getDiagram();
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    private Instance getInstance() {
        return this.panel.getPanelModeling().getPanelInstance().getInstance();
    }
    
    /**
     * Method responsible for returning the Product_Final.
     * @return Product_Final.
     */
    private Product_Final getProduct() {
        return this.panel.getPanelModeling().getPanelProduct().getProduct();
    }
    
    /**
     * Method responsible for closing the Tab.
     */
    private void closeTab() {
        if (this.panel.getDiagram() != null)
            this.panel.getPanelModeling().removeDiagram(this.panel.getDiagram());
        else if (this.panel.getInstance() != null)
            this.panel.getPanelModeling().removeInstance(this.panel.getInstance());
        else if (this.panel.getProduct() != null)
            this.panel.getPanelModeling().removeProduct(this.panel.getProduct());
    }
}