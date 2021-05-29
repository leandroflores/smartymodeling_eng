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
     * @param view View New Instance.
     */
    public ControllerViewNewInstance(ViewNewInstance view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelBaseInstance().getDiagramComboBox(),  "Select a Diagram!")
            && check(getView().getPanelBaseArtifacts().getProductComboBox(), "Select a Product!")
            && check(getView().getPanelBaseArtifacts().getNameTextField(), "Name is required!")
            && checkInstance();
    }
    
    /**
     * Method responsible for returning if the Instance contains Artifacts.
     * @return Instance contains Artifacts.
     */
    private boolean containsArtifacts() {
        for (Integer count : getView().getElements().values()) {
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
        if (!containsArtifacts()) {
            new ViewError(getView(), "Instance is Empty!").setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Method responsible for adding the New Artifacts.
     */
    public void addNewArtifacts() {
        Instance instance = getView().getInstance();
        for (Map.Entry<String, Integer> artifact : getView().getElements().entrySet()) {
            if (artifact.getValue() > 0)
                instance.addArtifact(new Artifact(instance.getDiagram().getElement(artifact.getKey())));
        }
    }
    
    /**
     * Method responsible for returning a New Instance.
     * @return New Instance.
     */
    public Instance createNewInstance() {
        Instance instance = getView().getInstance();
                 addNewArtifacts();
                 instance.update();
        return   instance;
    }
    
    @Override
    public void new_() {
        Instance instance = createNewInstance();
                 instance.getProduct().addInstance(instance);
        getView().getViewMenu().setTabIndex(4);
        getView().getViewMenu().showInstance(instance);
    }
    
    @Override
    public ViewNewInstance getView() {
        return (ViewNewInstance) super.getView();
    }
}