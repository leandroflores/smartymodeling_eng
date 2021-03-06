package view.panel.edit.base;

import model.structural.base.Stereotype;
import view.panel.base.PanelBaseStereotype;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditStereotype</b>.</p> 
 * <p>Class responsible for defining a <b>Stereotype Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-17
 * @see    model.structural.base.Stereotype
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditStereotype extends PanelEdit {
    private final Stereotype stereotype;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param stereotype Stereotype.
     */
    public PanelEditStereotype(ViewMenu view, Stereotype stereotype) {
        super(view);
        this.stereotype = stereotype;
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseStereotype();
    }
    
    /**
     * Method responsible for adding the Panel Base Stereotype.
     */
    private void addPanelBaseStereotype() {
        addPanel("base_stereotype", new PanelBaseStereotype(viewMenu, stereotype));
        createScrollPane("base_stereotype", getPanelBaseStereotype());
        getScrollPane("base_stereotype").setViewportView(getPanelBaseStereotype());
        tabbedPane.add("Stereotype", getScrollPane("base_stereotype"));
    }
    
    /**
     * Method responsible for returning the Panel Base Stereotype.
     * @return Panel Base Stereotype.
     */
    public PanelBaseStereotype getPanelBaseStereotype() {
        return (PanelBaseStereotype) getPanel("base_stereotype");
    }
    
    /**
     * Method responsible for returning the Stereotype.
     * @return Stereotype.
     */
    public Stereotype getStereotype() {
        return stereotype;
    }
}