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
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseAssociationUML();
        addPanelBaseSource();
        addPanelBaseTarget();
    }
    
    /**
     * Method responsible for adding the Panel Base Association UML.
     */
    protected void addPanelBaseAssociationUML() {
        addPanel("base_association", new PanelBaseAssociationUML(this, getDiagram(), getAssociation()));
        createScrollPane("base_association", getPanelBaseAssociationUML());
        getScrollPane("base_association").setViewportView(getPanelBaseAssociationUML());
        tabbedPane.add("Association", getScrollPane("base_association"));
    }
    
    /**
     * Method responsible for adding the Panel Base Source.
     */
    protected void addPanelBaseSource() {
        addPanel("source", new PanelBaseSource(viewMenu, getDiagram(), getAssociation()));
        createScrollPane("source",  getPanelBaseSource());
        getScrollPane("source").setViewportView(getPanelBaseSource());
        tabbedPane.add("Source", getScrollPane("source"));
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    protected void addPanelBaseTarget() {
        addPanel("target", new PanelBaseTarget(viewMenu, getDiagram(), getAssociation()));
        createScrollPane("target",  getPanelBaseTarget());
        getScrollPane("target").setViewportView(getPanelBaseTarget());
        tabbedPane.add("Target", getScrollPane("target"));
    }
    
    /**
     * Method responsible for returning the Panel Base Association UML.
     * @return Panel Base Association UML.
     */
    public PanelBaseAssociationUML getPanelBaseAssociationUML() {
        return (PanelBaseAssociationUML) getPanel("base_association");
    }
    
    /**
     * Method responsible for returning the Panel Base Source.
     * @return Panel Base Source.
     */
    public PanelBaseSource getPanelBaseSource() {
        return (PanelBaseSource) getPanel("source");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return (PanelBaseTarget) getPanel("target");
    }
    
    @Override
    public AssociationUML getAssociation() {
        return (AssociationUML) association;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
}