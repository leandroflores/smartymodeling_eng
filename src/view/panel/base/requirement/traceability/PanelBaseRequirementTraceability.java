package view.panel.base.requirement.traceability;

import controller.view.panel.base.requirement.traceability.ControllerPanelBaseRequirementTraceability;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseRequirementTraceability</b>.</p>
 * <p>Class responsible for defining a <b>Traceability Requirement Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    controller.view.panel.base.requirement.traceability.ControllerPanelBaseRequirementTraceability
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseRequirementTraceability extends PanelBase {
    protected Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     */
    public PanelBaseRequirementTraceability(ViewMenu view, Requirement requirement) {
        super(view);
        this.requirement = requirement;
        this.controller  = new ControllerPanelBaseRequirementTraceability(this);
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
        this.add(this.createComboBox("requirementComboBox", this.getContext(), 100), this.createConstraints(4, 1, 1, 0));
        
        this.add(this.createLabel("Context: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("contextComboBox", this.getContext(), 100), this.createConstraints(4, 1, 1, 1));
        
        this.add(this.createLabel("Element: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createComboBox("elementComboBox", this.getElements(), 175), this.createConstraints(4, 1, 1, 2));
        
        this.add(this.createButtonsPanel(), this.createConstraints(5, 1, 0, 3));
        
        this.createList("elementsList");
        this.add(this.getElementsScrollPane(), this.createConstraints(5, 10, 0, 4));
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
     * Method responsible for returning the Requirement Array.
     * @return Requirement Array.
     */
    public Object[] getRequirement() {
        return new ControllerProject(this.project).getRequirements();
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
            return  new ControllerDiagram(diagram).getDefaultElements();
        return new ControllerProject(this.project).getDefaultElements();
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
        DefaultListModel model = new DefaultListModel();
        for (Element element :  this.requirement.getAllElements())
            model.addElement(element);
        this.getElementsList().setModel(model);
    }
    
    /**
     * Method responsible for adding a Element.
     */
    public void addElement() {
        Element element = this.getElement();
        if (element != null) {
            this.requirement.addElement(element.getDiagramType(), element);
            this.updateElementsList();
            this.getViewMenu().updatePanelTree();
        }
    }
    
    /**
     * Method responsible for deleting a Element.
     */
    public void delElement() {
        Element element = (Element) this.getElementsList().getSelectedValue();
        if (element != null) {
            this.requirement.removeElement(element);
            this.updateElementsList();
            this.getViewMenu().updatePanelTree();
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
    
    /**
     * Method responsible for return the Elements Scroll Pane.
     * @return Elements Scroll Pane.
     */
    public JScrollPane getElementsScrollPane() {
        return this.getScrollPane("elementsList");
    }
}