package controller.view.modal.new_.base.product;

import controller.view.modal.new_.ControllerViewNew;
import java.util.Map;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import view.modal.message.ViewError;
import view.modal.new_.base.product.ViewNewInstance;

/**
 * <p>Class of Controller <b>ControllerViewNewInstance</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewInstance</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-11
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    model.structural.base.product.Instance
 * @see    view.modal.new_.base.product.ViewNewInstance
 */
public class ControllerViewNewInstance extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param viewNew View New Instance.
     */
    public ControllerViewNewInstance(ViewNewInstance viewNew) {
        super(viewNew);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBaseInstance().getDiagramComboBox(),  "Select a Diagram!")
            && this.check(this.getView().getPanelBaseArtifacts().getProductComboBox(), "Select a Product!")
            && this.check(this.getView().getPanelBaseArtifacts().getNameTextField(), "Name is required!")
            && this.checkInstance();
    }
    
    /**
     * Method responsible for returning if the Instance contains Artifacts.
     * @return Instance contains Artifacts.
     */
    private boolean containsArtifacts() {
        for (Integer count : this.getView().getElements().values()) {
            if (count > 0)
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for checking if the Instance is not Empty.
     * @return Instance is not Empty.
     */
    public boolean checkInstance() {
        if (!this.containsArtifacts()) {
            new ViewError(this.getView(), "Instance is Empty!").setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Method responsible for adding the New Artifacts.
     */
    public void addNewArtifacts() {
        Instance instance = this.getView().getInstance();
        for (Map.Entry<String, Integer> artifact : this.getView().getElements().entrySet()) {
            if (artifact.getValue() > 0)
                instance.addArtifact(new Artifact(instance.getDiagram().getElement(artifact.getKey())));
        }
    }
    
    /**
     * Method responsible for returning a New Instance.
     * @return New Instance.
     */
    public Instance createNewInstance() {
        Instance instance = this.getView().getInstance();
                 this.addNewArtifacts();
                 instance.update();
        return   instance;
    }
    
    @Override
    public void new_() {
        Instance instance = this.createNewInstance();
                 instance.getProduct().addInstance(instance);
        this.getView().getViewMenu().setTabIndex(4);
        this.getView().getViewMenu().showInstance(instance);
    }
    
    @Override
    public ViewNewInstance getView() {
        return (ViewNewInstance) this.viewModal;
    }
}