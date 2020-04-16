package view.panel.edit.base;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.association.Association;
import view.edit.panel.base.PanelBaseAssociation;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditAssociation</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/11/2019
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditAssociation extends PanelEdit {
    private final Project project;
    private final Diagram diagram;
    private final Association association;
    private PanelBaseAssociation panelBaseAssociation;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     * @param association Association.
     */
    public PanelEditAssociation(ViewMenu viewMenu, Diagram diagram, Association association) {
        super(viewMenu);
        this.project     = this.viewMenu.getProject();
        this.diagram     = diagram;
        this.association = association;
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseAssociation();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Association.
     */
    protected void addPanelBaseAssociation() {
        this.panelBaseAssociation  = new PanelBaseAssociation(this.viewMenu, this.diagram, this.association);
        this.createScrollPane("scrollPanelBaseAssociation", this.panelBaseAssociation);
        this.getScrollPanelBaseAssociation().setViewportView(this.panelBaseAssociation);
        this.tabbedPane.add("Association", this.getScrollPanelBaseAssociation());
    }
    
    /**
     * Method responsible for returning the Association.
     * @return Association.
     */
    public Association getAssociation() {
        return this.association;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Association.
     * @return Scroll Panel Base Association.
     */
    public JScrollPane getScrollPanelBaseAssociation() {
        return this.getScrollPane("scrollPanelBaseAssociation");
    }
    
    /**
     * Method responsible for returning the Panel Base Association.
     * @return Panel Base Association.
     */
    public PanelBaseAssociation getPanelBaseAssociation() {
        return this.panelBaseAssociation;
    }
}