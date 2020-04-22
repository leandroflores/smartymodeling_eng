package controller.view.new_.base.requirement.traceability;

import controller.view.new_.ControllerViewNew;
import model.structural.base.Element;
import view.new_.base.requirement.traceability.ViewNewAddElement;

/**
 * <p>Class of Controller <b>ControllerViewNewAddElement</b>.</p>
 * <p>Class responsible for controlling the <b>ViewNewAddElement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.new_.ControllerViewNew
 * @see    model.structural.base.requirement.Requirement
 * @see    view.new_.base.requirement.traceability.ViewNewAddElement
 */
public class ControllerViewNewAddElement extends ControllerViewNew {

    /**
     * Default constructor method of Class.
     * @param viewNew View New Requirement Add Element.
     */
    public ControllerViewNewAddElement(ViewNewAddElement viewNew) {
        super(viewNew);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelBase().getElementComboBox(), "Select a Element!");
    }

    @Override
    public void new_() {
        Element element = this.getView().getPanelBase().getElement();
        this.getView().getRequirement().addElement(element.getDiagramType(), element);
        this.getPanelTree().updateNode(this.getView().getRequirement());
        this.getView().getViewMenu().setTabIndex(0);
    }
    
    @Override
    public ViewNewAddElement getView() {
        return (ViewNewAddElement) this.viewModal;
    }
}