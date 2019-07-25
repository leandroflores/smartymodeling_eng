package view.edit.panel.base.traceability;

import controller.view.edit.panel.base.traceability.ControllerPanelBaseElements;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import model.controller.structural.base.ControllerDiagram;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseElements</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Elements Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/07/2019
 * @see    controller.view.edit.panel.base.traceability.ControllerPanelBaseElements
 * @see    model.structural.base.traceability.Traceability
 * @see    view.Panel
 */
public final class PanelBaseElements extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private       Diagram  diagram;
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param traceability Traceability.
     */
    public PanelBaseElements(ViewMenu viewMenu, Traceability traceability) {
        this.viewMenu     = viewMenu;
        this.project      = this.viewMenu.getProject();
        this.setDiagram();
        this.traceability = traceability;
        this.controller   = new ControllerPanelBaseElements(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for setting the Diagram.
     */
    private void setDiagram() {
        if (!this.project.getDiagramsList().isEmpty())
            this.diagram = this.project.getDiagramsList().get(0);
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(300, 300));
        this.setSize(new Dimension(300, 300));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Diagram: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createComboBox("diagramComboBox", new ControllerProject(this.project).getDiagrams(), 150), this.getConstraints(4, 1, 1, 0));
        
        this.add(this.createLabel("Element: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("elementComboBox", new ControllerDiagram(this.diagram).getDefaultElements(), 250), this.getConstraints(2, 1, 1, 1));
        this.add(this.createButton("addElementButton", "", "add.png"), this.getConstraints(1, 1, 3, 1));
        this.add(this.createButton("delElementButton", "", "not.png"), this.getConstraints(1, 1, 4, 1));
        
        this.createList("elementsList");
        this.add(this.getElementsScrollPane(), this.getConstraints(5, 10, 0, 2));
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    public void setValues() {
        this.updateValues();
        this.updateElementsList();
    }
    
    /**
     * Method responsible for adding a Element.
     */
    public void addElement() {
        Element element = this.getElement();
        if (!this.traceability.getElements().contains(element)) {
            this.traceability.addElement(element);
            this.updateValues();
            this.updateElementsList();
        }
    }
    
    /**
     * Method responsible for deleting a Element.
     */
    public void delElement() {
        this.traceability.removeElement((Element) this.getElementsList().getSelectedValue());
        this.updateValues();
        this.updateElementsList();
    }
    
    /**
     * Method responsible for updating the Traceability Values.
     */
    public void updateValues() {
        this.diagram = (Diagram) this.getDiagramComboBox().getSelectedItem();
        this.getElementComboBox().removeAllItems();
        for (Element element : this.diagram.getDefaultElementsList())
            this.getElementComboBox().addItem(element);
        this.getElementComboBox().updateUI();
    }
    
    /**
     * Method responsible for updating the Elements List.
     */
    public void updateElementsList() {
        this.getElementsList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i <  this.traceability.getElements().size(); i++)
            model.addElement(this.traceability.getElements().get(i));
        this.getElementsList().setModel(model);
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return (Element) this.getElementComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for return the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return this.comboBoxes.get("diagramComboBox");
    }
    
    /**
     * Method responsible for returning the Element Combo Box.
     * @return Element Combo Box.
     */
    public JComboBox getElementComboBox() {
        return this.comboBoxes.get("elementComboBox");
    }
    
    /**
     * Method responsible for returning the Add Element Button.
     * @return Add Element Button.
     */
    public JButton getAddElementButton() {
        return this.buttons.get("addElementButton");
    }
    
    /**
     * Method responsible for returning the Del Element Button.
     * @return Del Element Button.
     */
    public JButton getDelElementButton() {
        return this.buttons.get("delElementButton");
    }
    
    /**
     * Method responsible for return the Elements List.
     * @return Elements List.
     */
    public JList getElementsList() {
        return this.lists.get("elementsList");
    }
    
    /**
     * Method responsible for return the Elements Scroll Pane.
     * @return Elements Scroll Pane.
     */
    public JScrollPane getElementsScrollPane() {
        return this.scrollPanes.get("elementsList");
    }
}