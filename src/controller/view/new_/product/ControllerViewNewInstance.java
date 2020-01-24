package controller.view.new_.product;

import controller.view.new_.ControllerViewNew;
import java.awt.event.ActionEvent;
import java.util.Map;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import view.message.ViewError;
import view.new_.product.ViewNewInstance;

/**
 * <p>Class of Controller <b>ControllerViewNewInstance</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewInstance</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  11/10/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    model.structural.base.product.Instance
 * @see    view.new_.product.ViewNewInstance
 */
public class ControllerViewNewInstance extends ControllerViewNew {
    private final ViewNewInstance viewNewInstance;

    /**
     * Default constructor method of Class.
     * @param viewNew View New Instance.
     */
    public ControllerViewNewInstance(ViewNewInstance viewNew) {
        super(viewNew);
        this.viewNewInstance = viewNew;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }

    /**
     * Method responsible for checking the Instance Product.
     * @return Product is selected.
     */
    public boolean checkProduct() {
        return this.check(this.viewNewInstance.getPanelBaseNewInstance().getProductComboBox(), "Select a Project!");
    }
    
    /**
     * Method responsible for checking the Instance Diagram.
     * @return Diagram is selected.
     */
    public boolean checkDiagram() {
        return this.check(this.viewNewInstance.getPanelBaseNewInstance().getDiagramComboBox(), "Select a Diagram!");
    }
    
    /**
     * Method responsible for checking the Instance Name.
     * @return Name is checked.
     */
    public boolean checkName() {
        return this.check(this.viewNewInstance.getPanelBaseArtifacts().getNameTextField(), "Name is required!");
    }
    
    /**
     * Method responsible for checking if the Instance is not Empty.
     * @return Instance is not Empty.
     */
    public boolean checkInstance() {
        if (this.viewNewInstance.getInstance().isEmpty()) {
            new ViewError(this.viewNew, "Instance is Empty!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean check() {
        return this.checkProduct()
            && this.checkDiagram()
            && this.checkName()
            && this.checkInstance();
    }

    /**
     * Method responsible for adding the Instance Artifacts.
     */
    public void addInstanceArtifacts() {
        Instance instance = this.viewNewInstance.getInstance();
        for (Map.Entry<String, Integer> artifact : this.viewNewInstance.getElements().entrySet()) {
            if (artifact.getValue() > 0)
                instance.addArtifact(new Artifact(instance.getDiagram().getElement(artifact.getKey())));
        }
    }
    
    /**
     * Method responsible for returning a New Instance.
     * @return New Instance.
     */
    public Instance newInstance() {
        Instance instance = this.viewNewInstance.getInstance();
                 this.addInstanceArtifacts();
                 instance.update();
        return   instance;
    }
    
    @Override
    public void insert() {
        Instance instance = this.newInstance();
                 instance.getProduct().addInstance(instance);
        this.viewNewInstance.getViewMenu().showInstance(instance);
        this.close();
    }
}