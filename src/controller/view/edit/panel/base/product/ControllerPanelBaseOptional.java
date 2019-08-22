package controller.view.edit.panel.base.product;


import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import model.structural.base.Element;
import view.edit.panel.base.product.PanelBaseOptional;

/**
 * <p>Class of Controller <b>ControllerPanelBaseOptional</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseOptional</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/08/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.product.PanelBaseOptional
 */
public class ControllerPanelBaseOptional extends ControllerPanel {
    private final PanelBaseOptional panelBaseOptional;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Optional.
     */
    public ControllerPanelBaseOptional(PanelBaseOptional panel) {
        super(panel);
        this.panelBaseOptional = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseOptional.getBackButton().equals(event.getSource()))
            this.panelBaseOptional.getViewNewProduct().dispose();
        else if (this.panelBaseOptional.getNextButton().equals(event.getSource()))
            this.next();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for going to Next Panel.
     */
    public void next() {
        this.updateProduct();
        this.panelBaseOptional.getViewNewProduct().addVariationPointsTabbedPane();
    }
    
    /**
     * Method responsible for updating the Diagram.
     */
    private void updateProduct() {
        List<Element> elements = this.panelBaseOptional.getViewNewProduct().getDiagram().filterOptionalElements();
        for (Element  element : elements) {
            if (this.panelBaseOptional.getCheckBox(element).isSelected())
                this.panelBaseOptional.getViewNewProduct().increment(element);
        }
    }
}