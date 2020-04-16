package view.panel.edit.diagram.classes.base.association;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.diagram.classes.base.association.PanelBaseAssociationUML;
import view.panel.base.diagram.classes.base.association.PanelBaseSource;
import view.panel.base.diagram.classes.base.association.PanelBaseTarget;
import view.panel.edit.base.PanelEditAssociation;
import view.structural.ViewMenu;

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
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
            this.addPanelBaseAssociationUML();
            this.addPanelBaseSource();
            this.addPanelBaseTarget();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Association UML.
     */
    protected void addPanelBaseAssociationUML() {
        this.addPanel("panelBaseAssociationUML", new PanelBaseAssociationUML(this, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseAssociation",  this.getPanelBaseAssociationUML());
        this.getScrollPanelBaseAssociation().setViewportView(this.getPanelBaseAssociationUML());
        this.tabbedPane.add("Association", this.getScrollPanelBaseAssociation());
    }
    
    /**
     * Method responsible for adding the Panel Base Source.
     */
    protected void addPanelBaseSource() {
        this.addPanel("panelBaseSource", new PanelBaseSource(this.viewMenu, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseSource",  this.getPanelBaseSource());
        this.getScrollPanelBaseSource().setViewportView(this.getPanelBaseSource());
        this.tabbedPane.add("Source", this.getScrollPanelBaseSource());
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    protected void addPanelBaseTarget() {
        this.addPanel("panelBaseTarget", new PanelBaseTarget(this.viewMenu, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseTarget",  this.getPanelBaseTarget());
        this.getScrollPanelBaseTarget().setViewportView(this.getPanelBaseTarget());
        this.tabbedPane.add("Target", this.getScrollPanelBaseTarget());
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public AssociationUML getAssociation() {
        return (AssociationUML) this.association;
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
     * Method responsible for returning the Scroll Panel Base Source.
     * @return Scroll Panel Base Source.
     */
    public JScrollPane getScrollPanelBaseSource() {
        return this.getScrollPane("scrollPanelBaseSource");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return (PanelBaseTarget) this.getPanel("panelBaseTarget");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Target.
     * @return Scroll Panel Base Target.
     */
    public JScrollPane getScrollPanelBaseTarget() {
        return this.getScrollPane("scrollPanelBaseTarget");
    }
}