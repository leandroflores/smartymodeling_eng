package view.panel.edit.base;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Stereotype;
import view.panel.base.PanelBaseStereotype;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

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
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
            this.addPanelBaseStereotype();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Stereotype.
     */
    private void addPanelBaseStereotype() {
        this.addPanel("panelBaseStereotype", new PanelBaseStereotype(this.viewMenu, this.stereotype));
        this.createScrollPane("scrollPanelBaseStereotype",  this.getPanelBaseStereotype());
        this.getScrollPanelBaseStereotype().setViewportView(this.getPanelBaseStereotype());
        this.tabbedPane.add("Stereotype", this.getScrollPanelBaseStereotype());
    }
    
    /**
     * Method responsible for returning the Panel Base Stereotype.
     * @return Panel Base Stereotype.
     */
    public PanelBaseStereotype getPanelBaseStereotype() {
        return (PanelBaseStereotype) this.getPanel("panelBaseStereotype");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Stereotype.
     * @return Scroll Panel Base Stereotype.
     */
    public JScrollPane getScrollPanelBaseStereotype() {
        return this.getScrollPane("scrollPanelBaseStereotype");
    }
    
    /**
     * Method responsible for returning the Stereotype.
     * @return Stereotype.
     */
    public Stereotype getStereotype() {
        return this.stereotype;
    }
}