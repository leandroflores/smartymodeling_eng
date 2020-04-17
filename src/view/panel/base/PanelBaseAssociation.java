package view.panel.base;

import java.awt.Dimension;
import java.awt.GridLayout;
import model.structural.base.Diagram;
import model.structural.base.association.Association;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAssociation</b>.</p> 
 * <p>Class responsible for defining a Abstract Model of <b>Association Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    model.structural.base.association.Association
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBaseAssociation extends PanelBase {
    protected final Diagram diagram;
    protected final Association association;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param association Association.
     */
    public PanelBaseAssociation(ViewMenu view, Diagram diagram, Association association) {
        super(view);
        this.diagram     = diagram;
        this.association = association;
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(3, 2));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Association.
     * @return Association.
     */
    public Association getAssociation() {
        return this.association;
    }
}