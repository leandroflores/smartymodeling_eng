package view.edit.panel.stereotype;

import controller.view.edit.panel.stereotype.ControllerPanelStereotype;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import model.structural.base.association.Link;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelProject</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Stereotypes</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    controller.view.edit.panel.stereotype.ControllerPanelStereotype
 * @see    view.panel.Panel
 */
public final class PanelStereotype extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private final Element  element;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param element Element.
     */
    public PanelStereotype(ViewMenu viewMenu, Element element) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.element    = element;
        this.controller = new ControllerPanelStereotype(this);
        this.setSettings();
        this.addComponents();
        this.update();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("New: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("stereotypeTextField", "", 10), this.createConstraints(2, 1, 1, 0));
        this.add(this.createButton("newStereotypeButton", "New Stereotype"), this.createConstraints(2, 1, 3, 0));
      
        this.add(this.createComboBox("stereotypeComboBox", this.getValues(), 100), this.createConstraints(3, 1, 0, 1));
        this.add(this.createButton("addStereotypeButton",    "", "Add Stereotype",    "add.png"),    this.createConstraints(1, 1, 3, 1));
        this.add(this.createButton("removeStereotypeButton", "", "Remove Stereotype", "remove.png"), this.createConstraints(1, 1, 4, 1));
        
        this.createList("stereotypesList");
        this.add(this.getStereotypesScrollPane(), this.createConstraints(5, 10, 0, 2));
        this.getStereotypesScrollPane().setMinimumSize(new Dimension(150, 150));
    }
    
    /**
     * Method responsible for returning the Stereotypes.
     * @return Stereotypes.
     */
    public Stereotype[] getValues() {
        return new ControllerProject(this.project).getStereotypes();
    }
    
    /**
     * Method responsible for updating the Panel.
     */
    private void update() {
        this.updateComboBox();
        this.updateList();
    }
    
    /**
     * Method responsible for updating the Stereotype Combo Box.
     */
    public void updateComboBox() {
        this.getStereotypeComboBox().removeAllItems();
        for (Stereotype stereotype : this.project.getStereotypesList(false))
            this.getStereotypeComboBox().addItem(stereotype);
        this.getStereotypeComboBox().setPreferredSize(new Dimension(285, 30));
    }
    
    /**
     * Method responsible for updating the Stereotype List.
     */
    public void updateList() {
        this.getStereotypesList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Link link : this.project.getLinksByElement(this.element))
            model.addElement(link.getStereotype());
        this.getStereotypesList().setModel(model);
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
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }
    
    /**
     * Method responsible for returning the Stereotype Text Field.
     * @return Stereotype Text Field.
     */
    public JTextField getStereotypeTextField() {
        return this.getTextField("stereotypeTextField");
    }
    
    /**
     * Method responsible for returning the New Stereotype Button.
     * @return New Stereotype Button.
     */
    public JButton getNewStereotypeButton() {
        return this.getButton("newStereotypeButton");
    }
    
    /**
     * Method responsible for returning the Stereotype Combo Box.
     * @return Stereotype Combo Box.
     */
    public JComboBox getStereotypeComboBox() {
        return this.getComboBox("stereotypeComboBox");
    }
    
    /**
     * Method responsible for returning the Stereotypes List.
     * @return Stereotypes List.
     */
    public JList getStereotypesList() {
        return this.getList("stereotypesList");
    }
    
    /**
     * Method responsible for returning the Stereotypes Scroll Pane.
     * @return Stereotypes Scroll Pane.
     */
    public JScrollPane getStereotypesScrollPane() {
        return this.getScrollPane("stereotypesList");
    }
    
    /**
     * Method responsible for returning the Add Stereotype Button.
     * @return Add Stereotype Button.
     */
    public JButton getAddStereotypeButton() {
        return this.getButton("addStereotypeButton");
    }
    
    /**
     * Method responsible for returning the Remove Stereotype Button.
     * @return Remove Stereotype Button.
     */
    public JButton getRemoveStereotypeButton() {
        return this.getButton("removeStereotypeButton");
    }
}