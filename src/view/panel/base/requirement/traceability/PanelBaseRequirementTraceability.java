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
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
        setMinimumSize(new Dimension(150, 150));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Requirement: "), createConstraints(1, 1, 0, 0));
        add(createComboBox("requirement", getContext(), 100), createConstraints(4, 1, 1, 0));
        
        add(createLabel("Context: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("context", getContext(), 100), createConstraints(4, 1, 1, 1));
        
        add(createLabel("Element: "), createConstraints(1, 1, 0, 2));
        add(createComboBox("element", getElements(), 175), createConstraints(4, 1, 1, 2));
        
        add(createButtonsPanel(), createConstraints(5, 1, 0, 3));
        
        createList("elementsList");
        add(getElementsScrollPane(), createConstraints(5, 10, 0, 4));
        updateElementsList();
    }
    
    /**
     * Method responsible for creating the Buttons Panel.
     * @return Buttons Panel.
     */
    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(1, 2));
               panel.add(createButton("addElement", "", "Add Element", "add.png"));
               panel.add(createButton("delElement", "", "Del Element", "not.png"));
        return panel;
    }
    
     /**
     * Method responsible for returning the Requirement Array.
     * @return Requirement Array.
     */
    public Object[] getRequirement() {
        return new ControllerProject(project).getRequirements();
    }
    
    /**
     * Method responsible for returning the Context Array.
     * @return Context Array.
     */
    public Object[] getContext() {
        return new ControllerProject(project).getGeneralContext();
    }
    
    /**
     * Method responsible for returning the Selected Diagram.
     * @return Selected Diagram.
     */
    private Diagram getSelectedDiagram() {
        if (getContextComboBox().getSelectedItem() instanceof Diagram)
            return (Diagram) getContextComboBox().getSelectedItem();
        return null;
    }
    
    /**
     * Method responsible for returning the Elements Array.
     * @return Elements Array.
     */
    private Element[] getElements() {
        Diagram diagram = getSelectedDiagram();
        if (diagram != null)
            return  new ControllerDiagram(diagram).getDefaultElements();
        return new ControllerProject(project).getDefaultElements();
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
        DefaultListModel model = new DefaultListModel();
        for (Element element :  requirement.getAllElements())
            model.addElement(element);
        getElementsList().setModel(model);
    }
    
    /**
     * Method responsible for adding a Element.
     */
    public void addElement() {
        Element element = getElement();
        if (element != null) {
            requirement.addElement(element.getDiagramType(), element);
            updateElementsList();
            getViewMenu().updatePanelTree();
        }
    }
    
    /**
     * Method responsible for deleting a Element.
     */
    public void delElement() {
        Element element = (Element) getElementsList().getSelectedValue();
        if (element != null) {
            requirement.removeElement(element);
            updateElementsList();
            getViewMenu().updatePanelTree();
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
     * Method responsible for returning the Context Combo Box.
     * @return Context Combo Box.
     */
    public JComboBox getContextComboBox() {
        return getComboBox("context");
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
        return getButton("addElement");
    }
    
    /**
     * Method responsible for returning the Del Element Button.
     * @return Del Element Button.
     */
    public JButton getDelElementButton() {
        return getButton("delElement");
    }
    
    /**
     * Method responsible for return the Elements List.
     * @return Elements List.
     */
    public JList getElementsList() {
        return getList("elements");
    }
    
    /**
     * Method responsible for return the Elements Scroll Pane.
     * @return Elements Scroll Pane.
     */
    public JScrollPane getElementsScrollPane() {
        return getScrollPane("elements");
    }
}