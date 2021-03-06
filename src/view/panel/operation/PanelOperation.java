package view.panel.operation;

import funct.FunctView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import view.panel.Panel;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of View <b>PanelOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Diagram Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-04-09
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.Panel
 * @see    view.panel.diagram.PanelDiagram
 */
public abstract class PanelOperation extends Panel {
    protected final PanelDiagram panel;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public PanelOperation(PanelDiagram panel) {
        this.panel = panel;
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    @Override
    protected void addComponents() {
        addClickButton();
        addDiagramButtons();
        addVariabilityButton();
        addEditButton();
        addDeleteButton();
        addAssociationComboBox();
    }
    
    /**
     * Method responsible for adding the Click Button.
     */
    protected void addClickButton() {
        add(createButton("click", "", "Select", "click.png"));
    }
    
    /**
     * Method responsible for adding the Variability Button.
     */
    protected void addVariabilityButton() {
        add(createButton("variability", "", "", "variability.png"));
    }
    
    /**
     * Method responsible for adding the Edit Button.
     */
    protected void addEditButton() {
        add(createButton("edit", "", "Edit", "edit.png"));
    }
    
    /**
     * Method responsible for adding the Delete Button.
     */
    protected void addDeleteButton() {
        add(createButton("delete", "", "Delete", "delete.png"));
    }
    
    /**
     * Method responsible for adding the Diagram Buttons.
     */
    protected abstract void addDiagramButtons();
    
    /**
     * Method responsible for adding the Association Combo Box.
     */
    protected void addAssociationComboBox() {
        add(createComboBox("association", getAssociationsIcons(), 50));
    }
    
    /**
     * Method responsible for returning the Association Icons.
     * @return Association Icons.
     */
    protected abstract Object[] getAssociationsIcons();
    
    /**
     * Method responsible for reseting the Panel Background.
     */
    public abstract void resetBackground();
    
    /**
     * Method responsible for returning Image by URL.
     * @param  url URL Image.
     * @return Association Image.
     */
    protected ImageIcon getAssociationImage(String url) {
        return new FunctView().createImage("icons/associations/" + url + ".png");
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    public abstract PanelDiagram getPanelDiagram();
    
    /**
     * Method responsible for returning the Click Button.
     * @return Click Button.
     */
    public JButton getClickButton() {
        return getButton("click");
    }
    
    /**
     * Method responsible for returning the Variability Button.
     * @return Variability Button.
     */
    public JButton getVariabilityButton() {
        return getButton("variability");
    }
    
    /**
     * Method responsible for returning the Edit Button.
     * @return Edit Button.
     */
    public JButton getEditButton() {
        return getButton("edit");
    }
    
    /**
     * Method responsible for returning Delete Button.
     * @return Delete Button.
     */
    public JButton getDeleteButton() {
        return getButton("delete");
    }
    
    /**
     * Method responsible for returning Association ComboBox.
     * @return Association ComboBox.
     */
    public JComboBox getAssociationComboBox() {
        return getComboBox("association");
    }
}