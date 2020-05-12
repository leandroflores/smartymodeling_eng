package view.panel.base.diagram.classes.base;

import controller.view.panel.base.diagram.classes.base.ControllerPanelBaseDescription;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.structural.diagram.classes.Entity;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseDescription</b>.</p> 
 * <p>Class responsible for defining a <b>Description Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-05-11
 * @see    controller.view.panel.base.diagram.classes.base.ControllerPanelBaseDescription
 * @see    model.structural.diagram.classes.Entity
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseDescription extends PanelBase {
    private final Entity entity;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param entity Entity.
     */
    public PanelBaseDescription(ViewMenu view, Entity entity) {
        super(view);
        this.entity     = entity;
        this.controller = new ControllerPanelBaseDescription(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.createTextArea("descriptionTextArea", this.entity.getDescription());
        this.add(this.createLabel("Description: "), this.createConstraints(1, 1, 0, 0));
        this.add(this.getDescriptionScrollPane(), this.createConstraints(4, 5, 1, 0));
    }
    
    /**
     * Method responsible for return the Entity.
     * @return Entity.
     */
    public Entity getEntity() {
        return this.entity;
    }
    
    /**
     * Method responsible for returning the Description Text Area.
     * @return Description Text Area.
     */
    public JTextArea getDescriptionTextArea() {
        return this.getTextArea("descriptionTextArea");
    }
    
    /**
     * Method responsible for return the Description Scroll Pane.
     * @return Description Scroll Pane.
     */
    public JScrollPane getDescriptionScrollPane() {
        return this.getScrollPane("descriptionTextArea");
    }
}