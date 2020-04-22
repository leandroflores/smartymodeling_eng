package view.panel.base.requirement.traceability;

import controller.view.panel.base.requirement.traceability.ControllerPanelBaseAddElement;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import view.panel.base.PanelBase;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAddElement</b>.</p>
 * <p>Class responsible for defining a <b>Add Element Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.requirement.traceability.ControllerPanelBaseAddElement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseAddElement extends PanelBase {
    protected Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     */
    public PanelBaseAddElement(ViewMenu view, Requirement requirement) {
        super(view);
        this.requirement = requirement;
        this.controller  = new ControllerPanelBaseAddElement(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
        this.setMinimumSize(new Dimension(150, 150));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Requirement: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextFieldNoEditable("requirementTextField", this.requirement.toString(), 15), this.createConstraints(4, 1, 1, 0));
        
        this.add(this.createLabel("Context: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("contextComboBox", this.getContext(),  275), this.createConstraints(4, 1, 1, 1));
        
        this.add(this.createLabel("Element: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createComboBox("elementComboBox", this.getElements(), 275), this.createConstraints(4, 1, 1, 2));
    }
    
    /**
     * Method responsible for returning the Context Array.
     * @return Context Array.
     */
    public Object[] getContext() {
        return new ControllerProject(this.project).getGeneralContext();
    }
    
    /**
     * Method responsible for returning the Selected Diagram.
     * @return Selected Diagram.
     */
    private Diagram getSelectedDiagram() {
        if (this.getContextComboBox().getSelectedItem() instanceof Diagram)
            return (Diagram) this.getContextComboBox().getSelectedItem();
        return null;
    }
    
    /**
     * Method responsible for returning the Elements Array.
     * @return Elements Array.
     */
    private Element[] getElements() {
        Diagram diagram = this.getSelectedDiagram();
        if (diagram != null)
            return new ControllerDiagram(diagram).getDefaultElements();
        return new ControllerProject(this.project).getDefaultElements();
    }
    
    /**
     * Method responsible for updating the Element Combo Box.
     */
    public void updateElementComboBox() {
        this.getElementComboBox().removeAllItems();
        for (Element element : this.getElements())
            this.getElementComboBox().addItem(element);
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Variant Element.
     */
    public Element getElement() {
        return (Element) this.getElementComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for returning the Context Combo Box.
     * @return Context Combo Box.
     */
    public JComboBox getContextComboBox() {
        return this.getComboBox("contextComboBox");
    }
    
    /**
     * Method responsible for returning the Element Combo Box.
     * @return Element Combo Box.
     */
    public JComboBox getElementComboBox() {
        return this.getComboBox("elementComboBox");
    }
}