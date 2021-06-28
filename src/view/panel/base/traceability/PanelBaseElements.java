package view.panel.base.traceability;

import controller.view.panel.base.traceability.ControllerPanelBaseElements;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.traceability.Traceability;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseElements</b>.</p>
 * <p>Class responsible for defining a <b>Elements Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBaseElements
 * @see    model.structural.base.traceability.Traceability
 * @see    view.panel.base.traceability.PanelBase
 */
public final class PanelBaseElements extends PanelBase {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param traceability Traceability.
     */
    public PanelBaseElements(ViewMenu viewMenu, Traceability traceability) {
        super(viewMenu, traceability);
        controller = new ControllerPanelBaseElements(this);
        setDefaultProperties();
        setDiagram();
        addComponents();
        setValues();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    /**
     * Method responsible for setting the Diagram.
     */
    private void setDiagram() {
        if (!project.getDiagramsList().isEmpty())
             diagram = project.getDiagramsList().get(0);
    }
    
    
    @Override
    protected void addComponents() {
        add(createLabel("Diagram: "), createConstraints(1, 1, 0, 0));
        add(createComboBox("diagram", new ControllerProject(project).getDiagrams(), 150), createConstraints(4, 1, 1, 0));
        
        add(createLabel("Element: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("element", new ControllerDiagram(diagram).getDefaultElements(), 250), createConstraints(2, 1, 1, 1));
        add(createButton("addElement", "", "add.png"), createConstraints(1, 1, 3, 1));
        add(createButton("delElement", "", "not.png"), createConstraints(1, 1, 4, 1));
        
        createList("elements");
        add(getScrollPane("elements"), createConstraints(5, 10, 0, 2));
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        updateValues();
        updateElementsList();
    }
    
    /**
     * Method responsible for adding a Element.
     */
    public void addElement() {
        Element element = getElement();
        if (!traceability.getElements().contains(element)) {
            traceability.addElement(element);
            updateValues();
            updateElementsList();
        }
    }
    
    /**
     * Method responsible for deleting a Element.
     */
    public void delElement() {
        traceability.removeElement((Element) getElementsList().getSelectedValue());
        updateValues();
        updateElementsList();
    }
    
    /**
     * Method responsible for updating the Traceability Values.
     */
    public void updateValues() {
        diagram = (Diagram) getDiagramComboBox().getSelectedItem();
        getElementComboBox().removeAllItems();
        for (Element element : diagram.getDefaultElements())
            getElementComboBox().addItem(element);
        getElementComboBox().updateUI();
    }
    
    /**
     * Method responsible for updating the Elements List.
     */
    public void updateElementsList() {
        getElementsList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i <  traceability.getElements().size(); i++)
            model.addElement(traceability.getElements().get(i));
        getElementsList().setModel(model);
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return (Element) getElementComboBox().getSelectedItem();
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
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}