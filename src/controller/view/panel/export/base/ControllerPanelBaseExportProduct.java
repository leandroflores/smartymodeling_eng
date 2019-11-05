package controller.view.panel.export.base;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.export.base.PanelBaseExportProduct;

/**
 * <p>Class of Controller <b>ControllerPanelBaseExportProduct</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseExportProduct</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/11/2019
 * @see    controller.view.ControllerPanel
 * @see    view.panel.export.base.PanelBaseExportProduct
 */
public class ControllerPanelBaseExportProduct extends ControllerPanel {
    private final PanelBaseExportProduct panelBaseExportProduct;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Export Product.
     */
    public ControllerPanelBaseExportProduct(PanelBaseExportProduct panel) {
        super(panel);
        this.panelBaseExportProduct = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseExportProduct.getSearchDirectoryButton().equals(event.getSource())) {
            System.out.println("Test");
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for checking the Values for Exporting.
     * @return Values checked.
     */
    public boolean check() {
        return this.check(this.panelBaseExportProduct.getDirectoryTextField().getText());
    }
}