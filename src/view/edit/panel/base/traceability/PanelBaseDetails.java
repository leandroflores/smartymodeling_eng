package view.edit.panel.base.traceability;

import controller.view.edit.panel.base.traceability.ControllerPanelBaseDetails;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseDetails</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Details Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  15/01/2020
 * @see    controller.view.edit.panel.base.traceability.ControllerPanelBaseDetails
 * @see    model.structural.base.traceability.Traceability
 * @see    view.Panel
 */
public final class PanelBaseDetails extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param traceability Traceability.
     */
    public PanelBaseDetails(ViewMenu viewMenu, Traceability traceability) {
        this.viewMenu     = viewMenu;
        this.project      = this.viewMenu.getProject();
        this.traceability = traceability;
        this.controller   = new ControllerPanelBaseDetails(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
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
        this.add(this.createLabel("Name: "), this.getConstraints(1, 1, 0, 0));
        this.add(this.createTextFieldNoEditable("nameTextField", this.traceability.getName(), 150), this.getConstraints(4, 1, 1, 0));
        
        this.createList("elementsList");
        this.add(this.getElementsScrollPane(), this.getConstraints(5, 10, 0, 1));
    }
    
    /**
     * Method responsible for setting the Details Values.
     */
    public void setValues() {
        this.updateElementsList();
    }
    
    /**
     * Method responsible for updating the Elements List.
     */
    public void updateElementsList() {
        this.getElementsList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Element element : this.traceability.getElements())
            model.addElement(element.getName());
        this.getElementsList().setModel(model);
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
     * Method responsible for return the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
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