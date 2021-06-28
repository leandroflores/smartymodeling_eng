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
 * @see    model.structural.base.Stereotype
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
        setDefaultProperties();
        addComponents();
        update();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("New: "), createConstraints(1, 1, 0, 0));
        add(createTextField("stereotype", "", 10), createConstraints(2, 1, 1, 0));
        add(createButton("newStereotype", "New Stereotype"), createConstraints(2, 1, 3, 0));
      
        add(createComboBox("stereotype", getValues(), 100), createConstraints(3, 1, 0, 1));
        add(createButton("addStereotype", "", "Add Stereotype", "add.png"), createConstraints(1, 1, 3, 1));
        add(createButton("delStereotype", "", "Del Stereotype", "remove.png"), createConstraints(1, 1, 4, 1));
        
        createList("stereotypes");
        add(getScrollPane("stereotypes"), createConstraints(5, 10, 0, 2));
        getScrollPane("stereotypes").setMinimumSize(new Dimension(150, 150));
    }
    
    /**
     * Method responsible for returning the Stereotypes.
     * @return Stereotypes.
     */
    public Stereotype[] getValues() {
        return new ControllerProject(project).getStereotypes();
    }
    
    /**
     * Method responsible for updating the Panel.
     */
    private void update() {
        updateComboBox();
        updateList();
    }
    
    /**
     * Method responsible for updating the Stereotype Combo Box.
     */
    public void updateComboBox() {
        getStereotypeComboBox().removeAllItems();
        for (Stereotype stereotype : project.getStereotypesList(false))
            getStereotypeComboBox().addItem(stereotype);
        getStereotypeComboBox().setPreferredSize(new Dimension(285, 30));
    }
    
    /**
     * Method responsible for updating the Stereotype List.
     */
    public void updateList() {
        getStereotypesList().removeAll();
        DefaultListModel model = new DefaultListModel();
        for (Link link : project.getLinksByElement(element))
            model.addElement(link.getStereotype());
        getStereotypesList().setModel(model);
    }
    
    /**
     * Method responsible for returning the Stereotype Text Field.
     * @return Stereotype Text Field.
     */
    public JTextField getStereotypeTextField() {
        return getTextField("stereotype");
    }
    
    /**
     * Method responsible for returning the New Stereotype Button.
     * @return New Stereotype Button.
     */
    public JButton getNewStereotypeButton() {
        return getButton("newStereotype");
    }
    
    /**
     * Method responsible for returning the Stereotype Combo Box.
     * @return Stereotype Combo Box.
     */
    public JComboBox getStereotypeComboBox() {
        return getComboBox("stereotype");
    }
    
    /**
     * Method responsible for returning the Stereotypes List.
     * @return Stereotypes List.
     */
    public JList getStereotypesList() {
        return getList("stereotypes");
    }
    
    /**
     * Method responsible for returning the Add Stereotype Button.
     * @return Add Stereotype Button.
     */
    public JButton getAddStereotypeButton() {
        return getButton("addStereotype");
    }
    
    /**
     * Method responsible for returning the Remove Stereotype Button.
     * @return Remove Stereotype Button.
     */
    public JButton getRemoveStereotypeButton() {
        return getButton("delStereotype");
    }
    
    /**
     * Method responsible for returning the Element.
     * @return Element.
     */
    public Element getElement() {
        return element;
    }
}