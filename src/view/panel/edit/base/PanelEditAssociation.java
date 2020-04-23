package view.panel.edit.base;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
    protected void addComponents(String title) {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
            this.addPanelBaseAssociation(title);
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Association.
     * @param title Tab Title.
     */
    protected void addPanelBaseAssociation(String title) {
        this.addPanel("panelBaseAssociation", new PanelBaseAssociation(this.viewMenu, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseAssociation",  this.getPanelBaseAssociation());
        this.getScrollPanelBaseAssociation().setViewportView(this.getPanelBaseAssociation());
        this.tabbedPane.add(title, this.getScrollPanelBaseAssociation());
    }
    
    /**
     * Method responsible for returning the Panel Base Association.
     * @return Panel Base Association.
     */
    public PanelBaseAssociation getPanelBaseAssociation() {
        return (PanelBaseAssociation) this.getPanel("panelBaseAssociation");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Association.
     * @return Scroll Panel Base Association.
     */
    public JScrollPane getScrollPanelBaseAssociation() {
        return this.getScrollPane("scrollPanelBaseAssociation");
    }
    
    /**
     * Method responsible for returning the Association.
     * @return Association.
     */
    public Association getAssociation() {
        return this.association;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
}