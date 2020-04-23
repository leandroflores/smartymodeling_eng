package view.panel.base.stereotype;

import controller.view.panel.base.stereotype.ControllerPanelStereotype;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Link;
import view.main.structural.ViewMenu;
import view.panel.base.PanelBase;

/**
 * <p>Class of View <b>PanelStereotype</b>.</p> 
 * <p>Class responsible for defining a <b>Stereotype Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-29
 * @see    controller.view.panel.base.stereotype.ControllerPanelStereotype
 * @see    view.panel.base.PanelBase
 */
public final class PanelStereotype extends PanelBase {
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param element Element.
     */
    public PanelStereotype(ViewMenu view, Element element) {
        super(view);
        this.element    = element;
        this.controller = new ControllerPanelStereotype(this);
        this.setDefaultProperties();
        this.addComponents();
        this.update();
    }
    
    @Override
    protected void setDefaultProperties() {
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
        this.add(this.getScrollPane("stereotypesList"), this.createConstraints(5, 10, 0, 2));
        this.getScrollPane("stereotypesList").setMinimumSize(new Dimension(150, 150));
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
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return this.element;
    }
}