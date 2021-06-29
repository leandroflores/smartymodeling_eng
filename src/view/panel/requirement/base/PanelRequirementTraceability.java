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
        controller = new ControllerPanelRequirementTraceability(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Requirement: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("requirement", new ControllerRequirement(getProject()).getRequirements(), 400), createConstraints(4, 1, 1, 1));
        
        add(createLabel("Type: "), createConstraints(1, 1, 0, 2));
        add(createComboBox("type", new ControllerProject(getProject()).getDiagramTypes(), 400), createConstraints(4, 1, 1, 2));
        
        add(createLabel("Diagram: "), createConstraints(1, 1, 0, 3));
        add(createComboBox("diagram", new ControllerProject(getProject()).getDiagrams(getType()), 400), createConstraints(4, 1, 1, 3));
        
        add(createLabel("Element: "), createConstraints(1, 1, 0, 4));
        add(createComboBox("element", getElements(), 400), createConstraints(4, 1, 1, 4));
        
        add(createButtonsPanel(), createConstraints(5, 1, 0, 5));
        
        createList("elements");
        add(getScrollPane("elements"), createConstraints(5, 10, 0, 6));
        getScrollPane("elements").setPreferredSize(new Dimension(400, 280));
        updateElementsList();
    }
    
    /**
     * Method responsible for creating the Buttons Panel.
     * @return Buttons Panel.
     */
    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(1, 2));
               panel.add(createButton("add_element", "", "Add Element", "add.png"));
               panel.add(createButton("del_element", "", "Del Element", "not.png"));
        return panel;
    }
    
    /**
     * Method responsible for returning the Selected Requirement.
     * @return Selected Requirement.
     */
    private Requirement getSelectedRequirement() {
        return getRequirementComboBox().getSelectedItem() != null ?
               (Requirement) getRequirementComboBox().getSelectedItem() : null;
    }
    
    /**
     * Method responsible for returning the Type.
     * @return Type.
     */
    private String getType() {
        return getTypeComboBox().getSelectedItem().toString();
    }
    
    /**
     * Method responsible for returning the Selected Diagram.
     * @return Selected Diagram.
     */
    private Diagram getSelectedDiagram() {
        return getDiagramComboBox().getSelectedItem() instanceof Diagram ?
               (Diagram) getDiagramComboBox().getSelectedItem() : null;
    }
    
    /**
     * Method responsible for updating the Diagram Combo Box.
     */
    public void updateDiagramComboBox() {
        getDiagramComboBox().removeAllItems();
        for (Object diagram : new ControllerProject(getProject()).getDiagrams(getType()))
            getDiagramComboBox().addItem(diagram);
        
    }
    
    /**
     * Method responsible for returning the Elements Array.
     * @return Elements Array.
     */
    private Element[] getElements() {
        Diagram diagram = getSelectedDiagram();
        if (diagram != null)
            return  new ControllerDiagram(diagram).getDefaultElements();
        return new ControllerProject(getProject()).getDefaultElements();
    }
    
    /**
     * Method responsible for updating the Element Combo Box.
     */
    public void updateElementComboBox() {
        getElementComboBox().removeAllItems();
        Diagram diagram = getSelectedDiagram();
        if (diagram != null) {
            for (Element element : diagram.getDefaultElements())
                getElementComboBox().addItem(element);
        }
    }
    
    /**
     * Method responsible for updating the Elements List.
     */
    public void updateElementsList() {
        getElementsList().removeAll();
        DefaultListModel model  = new DefaultListModel();
        Requirement requirement = getSelectedRequirement();
        if (requirement != null) {
            for (Element element :  requirement.getElements(getType()))
                model.addElement(element);
        }
        getElementsList().setModel(model);
    }
    
    /**
     * Method responsible for adding a Element.
     */
    public void addElement() {
        Requirement requirement = getSelectedRequirement();
        Element     element     = getElement();
        if (requirement != null && element != null) {
            requirement.addElement(element.getDiagramType(), element);
            updateElementsList();
        }
    }
    
    /**
     * Method responsible for deleting a Element.
     */
    public void delElement() {
        Requirement requirement = getSelectedRequirement();
        Element     element     = (Element) getElementsList().getSelectedValue();
        if (requirement != null && element != null) {
            requirement.removeElement(element);
            updateElementsList();
        }
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Variant Element.
     */
    public Element getElement() {
        return (Element) getElementComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for returning the Requirement Combo Box.
     * @return Requirement Combo Box.
     */
    public JComboBox getRequirementComboBox() {
        return getComboBox("requirement");
    }
    
    /**
     * Method responsible for returning the Type Combo Box.
     * @return Type Combo Box.
     */
    public JComboBox getTypeComboBox() {
        return getComboBox("type");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return getComboBox("diagram");
    }
    
    /**
     * Method responsible for returning the Element Combo Box.
     * @return Element Combo Box.
     */
    public JComboBox getElementComboBox() {
        return getComboBox("element");
    }
    
    /**
     * Method responsible for returning the Add Element Button.
     * @return Add Element Button.
     */
    public JButton getAddElementButton() {
        return getButton("add_element");
    }
    
    /**
     * Method responsible for returning the Del Element Button.
     * @return Del Element Button.
     */
    public JButton getDelElementButton() {
        return getButton("del_element");
    }
    
    /**
     * Method responsible for return the Elements List.
     * @return Elements List.
     */
    public JList getElementsList() {
        return getList("elements");
    }
}