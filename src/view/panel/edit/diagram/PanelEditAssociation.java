package view.panel.edit.diagram;

import model.structural.base.Diagram;
import model.structural.base.association.Association;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditAssociation</b>.</p> 
 * <p>Class responsible for defining a <b>Association Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    model.structural.base.association.Association
 * @see    view.panel.edit.base.PanelEditAssociation
 */
public final class PanelEditAssociation extends view.panel.edit.base.PanelEditAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param association Association.
     */
    public PanelEditAssociation(ViewMenu view, Diagram diagram, Association association) {
        super(view, diagram, association);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanels("Association");
    }
}