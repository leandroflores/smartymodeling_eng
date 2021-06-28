package view.panel.base.requirement.traceability;

import controller.view.panel.base.requirement.traceability.ControllerPanelBaseRequirementDiagram;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.controller.structural.base.ControllerDiagram;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseRequirementDiagram</b>.</p>
 * <p>Class responsible for defining a <b>Diagram Requirement Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.panel.base.requirement.traceability.ControllerPanelBaseRequirementDiagram
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseRequirementDiagram extends PanelBase {
    protected Requirement requirement;
    protected String type;
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     * @param type Diagram Type.
     */
    public PanelBaseRequirementDiagram(ViewMenu view, Requirement requirement, String type) {
        super(view);
        this.requirement = requirement;
        this.type        = type;
        this.controller  = new ControllerPanelBaseRequirementDiagram(this);
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
        add(createLabel("Diagram: "), createConstraints(1, 1, 0, 0));
        add(createComboBox("diagram", getDiagrams(), 100), createConstraints(4, 1, 1, 0));
        
        add(createLabel("Element: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("element", getElements(), 175), createConstraints(4, 1, 1, 1));
        
        add(createButtonsPanel(), createConstraints(5, 1, 0, 2));
        
        createList("elements");
        add(getElementsScrollPane(), createConstraints(5, 10, 0, 3));
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
     * Method responsible for returning the Diagrams Array.
     * @return Diagrams Array.
     */
    public Object[] getDiagrams() {
        List   diagrams = getProject().getDiagrams(type);
               diagram  = diagrams.isEmpty() ? null : (Diagram) diagrams.get(0);
        return diagrams.toArray();
    }
    
    /**
     * Method responsible for returning the Elements Array.
     * @return Elements Array.
     */
    private Element[] getElements() {
        if (diagram != null)
            return new ControllerDiagram(diagram).getDefaultElements();
        return new Element[0];
    }
    
    /**
     * Method responsible for updating the Element Combo Box.
     */
    public void updateElementComboBox() {
        getElementComboBox().removeAllItems();
        diagram = (Diagram) getDiagramComboBox().getSelectedItem();
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
        for (Element element : requirement.getElements(type))
            model.addElement(element);
        getElementsList().setModel(model);
    }
    
    /**
     * Method responsible for adding a Element.
     */
    public void addElement() {
        Element element = getElement();
        if (element != null) {
            requirement.addElement(type, element);
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
     * Method responsible for returning if Exists Diagram.
     * @return Exists Diagram.
     */
    public boolean existsDiagram() {
        return diagram != null;
    }
    
    /**
     * Method responsible for setting the Requirement.
     * @param requirement Requirement.
     */
    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
        updateElementsList();
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