package view.panel.edit.diagram.classes.base.association;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.diagram.classes.base.association.PanelBaseAssociationUML;
import view.panel.base.diagram.classes.base.association.PanelBaseSource;
import view.panel.base.diagram.classes.base.association.PanelBaseTarget;
import view.panel.edit.base.PanelEditAssociation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditAssociationUML</b>.</p> 
 * <p>Class responsible for defining a <b>Association UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-15
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.panel.edit.base.PanelEditAssociation
 */
public final class PanelEditAssociationUML extends PanelEditAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param association Association UML.
     */
    public PanelEditAssociationUML(ViewMenu view, ClassDiagram diagram, AssociationUML association) {
        super(view, diagram, association);
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseAssociationUML();
        this.addPanelBaseSource();
        this.addPanelBaseTarget();
    }
    
    /**
     * Method responsible for adding the Panel Base Association UML.
     */
    protected void addPanelBaseAssociationUML() {
        this.addPanel("panelBaseAssociationUML", new PanelBaseAssociationUML(this, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseAssociationUML",  this.getPanelBaseAssociationUML());
        this.getScrollPane("scrollPanelBaseAssociationUML").setViewportView(this.getPanelBaseAssociationUML());
        this.tabbedPane.add("Association", this.getScrollPane("scrollPanelBaseAssociationUML"));
    }
    
    /**
     * Method responsible for adding the Panel Base Source.
     */
    protected void addPanelBaseSource() {
        this.addPanel("panelBaseSource", new PanelBaseSource(this.viewMenu, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseSource",  this.getPanelBaseSource());
        this.getScrollPane("scrollPanelBaseSource").setViewportView(this.getPanelBaseSource());
        this.tabbedPane.add("Source", this.getScrollPane("scrollPanelBaseSource"));
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    protected void addPanelBaseTarget() {
        this.addPanel("panelBaseTarget", new PanelBaseTarget(this.viewMenu, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseTarget",  this.getPanelBaseTarget());
        this.getScrollPane("scrollPanelBaseTarget").setViewportView(this.getPanelBaseTarget());
        this.tabbedPane.add("Target", this.getScrollPane("scrollPanelBaseTarget"));
    }
    
    /**
     * Method responsible for returning the Panel Base Association UML.
     * @return Panel Base Association UML.
     */
    public PanelBaseAssociationUML getPanelBaseAssociationUML() {
        return (PanelBaseAssociationUML) this.getPanel("panelBaseAssociationUML");
    }
    
    /**
     * Method responsible for returning the Panel Base Source.
     * @return Panel Base Source.
     */
    public PanelBaseSource getPanelBaseSource() {
        return (PanelBaseSource) this.getPanel("panelBaseSource");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return (PanelBaseTarget) this.getPanel("panelBaseTarget");
    }
    
    @Override
    public AssociationUML getAssociation() {
        return (AssociationUML) this.association;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
}