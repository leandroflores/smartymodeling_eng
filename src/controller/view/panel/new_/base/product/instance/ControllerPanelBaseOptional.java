package controller.view.panel.new_.base.product.instance;

import java.util.List;
import model.structural.base.Element;
import model.structural.base.product.Instance;
import view.panel.new_.base.product.instance.PanelBaseOptional;

/**
 * <p>Class of Controller <b>ControllerPanelBaseOptional</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseOptional</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-08
 * @see    controller.view.panel.new_.base.product.instance.ControllerPanelBase
 * @see    view.panel.new_.base.product.instance.PanelBaseOptional
 */
public class ControllerPanelBaseOptional extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Optional.
     */
    public ControllerPanelBaseOptional(PanelBaseOptional panel) {
        super(panel);
    }
    
    @Override
    protected void return_() {
        this.getPanel().getPanelNew().removePanelBaseOptional();
    }
    
    @Override
    protected boolean check() {
        return true;
    }
    
    @Override
    public void next() {
        this.update();
        this.getPanel().getPanelNew().addPanelBaseVarPoints();
    }
    
    @Override
    protected void update() {
        List<Element> elements = this.getInstance().getDiagram().filterOptionalElements();
        for (Element  element : elements) {
            if (this.getPanel().getCheckBox(element).isSelected())
                this.getPanel().getPanelNew().add(element);
        }
    }
    
    @Override
    protected Instance getInstance() {
        return this.getPanel().getInstance();
    }
    
    @Override
    public PanelBaseOptional getPanel() {
        return (PanelBaseOptional) this.panel;
    }
}