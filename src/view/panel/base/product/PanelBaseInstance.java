package view.panel.base.product;

import controller.view.panel.base.product.ControllerPanelBaseInstance;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.product.Instance;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseInstance</b>.</p> 
 * <p>Class responsible for defining a <b>Instance Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.base.product.ControllerPanelBaseInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseInstance extends PanelBase {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     */
    public PanelBaseInstance(ViewMenu view, Instance instance) {
        super(view);
        this.instance   = instance;
        this.controller = new ControllerPanelBaseInstance(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(3, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Product: "));
        add(createTextFieldNoEditable("product", instance.getProduct().getName(), 15));
        
        add(createLabel("Diagram: "));
        add(createTextFieldNoEditable("diagram", instance.getDiagram().getName(), 15));
        
        add(createLabel("Name*: "));
        add(createTextField("name", instance.getName(), 15));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return instance;
    }
}