package view.panel.requirement.base;

import controller.view.panel.requirement.base.ControllerPanelRequirementTraceability;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.ControllerProject;
import model.controller.structural.base.requirement.ControllerRequirement;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import view.modal.requirement.base.ViewRequirementTraceability;
import view.panel.requirement.PanelRequirement;

/**
 * <p>Class of View <b>PanelRequirementMatrix</b>.</p> 
 * <p>Class responsible for defining the <b>Requirement Traceability Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    controller.view.panel.requirement.base.ControllerPanelRequirementTraceability
 * @see    view.panel.requirement.PanelRequirement
 */
public final class PanelRequirementTraceability extends PanelRequirement {
    
    /**
     * Default constructor method of Class.
     * @param view View Requirement Traceability.
     */
    public PanelRequirementTraceability(ViewRequirementTraceability view) {
        super(view);
        this.controller = new ControllerPanelRequirementTraceability(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Requirement: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("requirementComboBox", new ControllerRequirement(this.getProject()).getRequirements(), 400), this.createConstraints(4, 1, 1, 1));
        
        this.add(this.createLabel("Type: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createComboBox("typeComboBox", new ControllerProject(this.getProject()).getDiagramTypes(), 400), this.createConstraints(4, 1, 1, 2));
        
        this.add(this.createLabel("Diagram: "), this.createConstraints(1, 1, 0, 3));
        this.add(this.createComboBox("diagramComboBox", new ControllerProject(this.getProject()).getDiagrams(this.getType()), 400), this.createConstraints(4, 1, 1, 3));
        
        this.add(this.createLabel("Element: "), this.createConstraints(1, 1, 0, 4));
        this.add(this.createComboBox("elementComboBox", this.getElements(), 400), this.createConstraints(4, 1, 1, 4));
        
        this.add(this.createButtonsPanel(), this.createConstraints(5, 1, 0, 5));
        
        this.createList("elementsList");
        this.add(this.getScrollPane("elementsList"), this.createConstraints(5, 10, 0, 6));
        this.getScrollPane("elementsList").setPreferredSize(new Dimension(400, 280));
        this.updateElementsList();
    }
    
    /**
     * Method responsible for creating the Buttons Panel.
     * @return Buttons Panel.
     */
    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(1, 2));
               panel.add(this.createButton("addElementButton", "", "Add Element", "add.png"));
               panel.add(this.createButton("delElementButton", "", "Del Element", "not.png"));
        return panel;
    }
    
    /**
     * Method responsible for returning the Selected Requirement.
     * @return Selected Requirement.
     */
    private Requirement getSelectedRequirement() {
        return this.getRequirementComboBox().getSelectedItem() != null ?
               (Requirement) this.getRequirementComboBox().getSelectedItem() : null;
    }
    
    /**
     * Method responsible for returning the Type.
     * @return Type.
     */
    private String getType() {
        return this.getTypeComboBox().getSelectedItem().toString();
    }
    
    /**
     * Method responsible for returning the Selected Diagram.
     * @return Selected Diagram.
     */
    private Diagram getSelectedDiagram() {
        return this.getDiagramComboBox().getSelectedItem() instanceof Diagram ?
               (Diagram) this.getDiagramComboBox().getSelectedItem() : null;
    }
    
    /**
     * Method responsible for updating the Diagram Combo Box.
     */
    public void updateDiagramComboBox() {
        this.getDiagramComboBox().removeAllItems();
        for (Object diagram : new ControllerProject(this.getProject()).getDiagrams(this.getType()))
            this.getDiagramComboBox().addItem(diagram);
        
    }
    
    /**
     * Method responsible for returning the Elements Array.
     * @return Elements Array.
     */
    private Element[] getElements() {
        Diagram diagram = this.getSelectedDiagram();
        if (diagram != null)
            return  new ControllerDiagram(diagram).getDefaultElements();
        return new ControllerProject(this.getProject()).getDefaultElements();
    }
    
    /**
     * Method responsible for updating the Element Combo Box.
     */
    public void updateElementComboBox() {
        this.getElementComboBox().removeAllItems();
        Diagram diagram = this.getSelectedDiagram();
        if (diagram != null) {
            for (Element element : diagram.getDefaultElements())
                this.getElementComboBox().addItem(element);
        }
    }
    
    /**
     * Method responsible for updating the Elements List.
     */
    public void updateElementsList() {
        this.getElementsList().removeAll();
        DefaultListModel model  = new DefaultListModel();
        Requirement requirement = this.getSelectedRequirement();
        if (requirement != null) {
            for (Element element :  requirement.getElements(this.getType()))
                model.addElement(element);
        }
        this.getElementsList().setModel(model);
    }
    
    /**
     * Method responsible for adding a Element.
     */
    public void addElement() {
        Requirement requirement = this.getSelectedRequirement();
        Element     element     = this.getElement();
        if (requirement != null && element != null) {
            requirement.addElement(element.getDiagramType(), element);
            this.updateElementsList();
        }
    }
    
    /**
     * Method responsible for deleting a Element.
     */
    public void delElement() {
        Requirement requirement = this.getSelectedRequirement();
        Element     element     = (Element) this.getElementsList().getSelectedValue();
        if (requirement != null && element != null) {
            requirement.removeElement(element);
            this.updateElementsList();
        }
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Variant Element.
     */
    public Element getElement() {
        return (Element) this.getElementComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for returning the Requirement Combo Box.
     * @return Requirement Combo Box.
     */
    public JComboBox getRequirementComboBox() {
        return this.getComboBox("requirementComboBox");
    }
    
    /**
     * Method responsible for returning the Type Combo Box.
     * @return Type Combo Box.
     */
    public JComboBox getTypeComboBox() {
        return this.getComboBox("typeComboBox");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return this.getComboBox("diagramComboBox");
    }
    
    /**
     * Method responsible for returning the Element Combo Box.
     * @return Element Combo Box.
     */
    public JComboBox getElementComboBox() {
        return this.getComboBox("elementComboBox");
    }
    
    /**
     * Method responsible for returning the Add Element Button.
     * @return Add Element Button.
     */
    public JButton getAddElementButton() {
        return this.getButton("addElementButton");
    }
    
    /**
     * Method responsible for returning the Del Element Button.
     * @return Del Element Button.
     */
    public JButton getDelElementButton() {
        return this.getButton("delElementButton");
    }
    
    /**
     * Method responsible for return the Elements List.
     * @return Elements List.
     */
    public JList getElementsList() {
        return this.getList("elementsList");
    }
}