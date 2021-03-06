package view.panel.edit.base;

import model.structural.base.Diagram;
import model.structural.base.association.Association;
import view.panel.base.diagram.PanelBaseAssociation;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditAssociation</b>.</p> 
 * <p>Class responsible for defining a <b>Association Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    model.structural.base.association.Association
 * @see    view.panel.edit.PanelEdit
 */
public abstract class PanelEditAssociation extends PanelEdit {
    protected final Diagram diagram;
    protected final Association association;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param association Association.
     */
    public PanelEditAssociation(ViewMenu view, Diagram diagram, Association association) {
        super(view);
        this.diagram     = diagram;
        this.association = association;
    }
    
    /**
     * Method responsible for adding the Components.
     * @param title Tab Element Title.
     */
    protected void addPanels(String title) {
        addPanelBaseAssociation(title);
    }
    
    /**
     * Method responsible for adding the Panel Base Association.
     * @param title Tab Title.
     */
    protected void addPanelBaseAssociation(String title) {
        addPanel("base_association", new PanelBaseAssociation(viewMenu, getDiagram(), getAssociation()));
        createScrollPane("base_association",  getPanelBaseAssociation());
        getScrollPane("base_association").setViewportView(getPanelBaseAssociation());
        tabbedPane.add(title, getScrollPane("base_association"));
    }
    
    /**
     * Method responsible for returning the Panel Base Association.
     * @return Panel Base Association.
     */
    public PanelBaseAssociation getPanelBaseAssociation() {
        return (PanelBaseAssociation) getPanel("base_association");
    }
    
    /**
     * Method responsible for returning the Association.
     * @return Association.
     */
    public Association getAssociation() {
        return association;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}