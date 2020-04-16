package controller.view.edit.panel.base.product.instance;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import model.structural.base.Element;
import view.edit.panel.base.product.instance.PanelBaseOptional;

/**
 * <p>Class of Controller <b>ControllerPanelBaseOptional</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseOptOptional</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/10/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.product.instance.PanelBaseOptional
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
            this.panelBaseOptional.getViewNewInstance().removePanelBaseOptional();
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
        this.panelBaseOptional.getViewNewInstance().addPanelBaseVarPoints();
    }
    
    /**
     * Method responsible for updating the Instance Artefacts.
     */
    private void updateProduct() {
        List<Element> elements = this.panelBaseOptional.getViewNewInstance().getInstance().getDiagram().filterOptionalElements();
        for (Element  element : elements) {
            if (this.panelBaseOptional.getCheckBox(element).isSelected())
                this.panelBaseOptional.getViewNewInstance().add(element);
        }
    }
}