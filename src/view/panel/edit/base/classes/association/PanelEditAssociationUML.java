package view.panel.edit.base.classes.association;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.edit.panel.base.classes.association.PanelBaseAssociationUML;
import view.edit.panel.base.classes.association.PanelBaseSource;
import view.edit.panel.base.classes.association.PanelBaseTarget;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditAssociationUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Association UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  15/11/2019
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditAssociationUML extends PanelEdit {
    private final Project project;
    private final ClassDiagram diagram;
    private final AssociationUML associationUML;
    private PanelBaseAssociationUML panelBaseAssociationUML;
    private PanelBaseSource panelBaseSource;
    private PanelBaseTarget panelBaseTarget;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param associationUML Association UML.
     */
    public PanelEditAssociationUML(ViewMenu viewMenu, ClassDiagram diagram, AssociationUML associationUML) {
        super(viewMenu);
        this.project        = this.viewMenu.getProject();
        this.diagram        = diagram;
        this.associationUML = associationUML;
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseAssociationUML();
        this.addPanelBaseSource();
        this.addPanelBaseTarget();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Association UML.
     */
    protected void addPanelBaseAssociationUML() {
        this.panelBaseAssociationUML  = new PanelBaseAssociationUML(this, this.diagram, this.associationUML);
        this.createScrollPane("scrollPanelBaseAssociationUML",  this.panelBaseAssociationUML);
        this.getScrollPanelBaseAssociationUML().setViewportView(this.panelBaseAssociationUML);
        this.tabbedPane.add("Association", this.getScrollPanelBaseAssociationUML());
    }
    
    /**
     * Method responsible for adding the Panel Base Source.
     */
    protected void addPanelBaseSource() {
        this.panelBaseSource = new PanelBaseSource(this.viewMenu, this.diagram, this.associationUML);
        this.createScrollPane("scrollPanelBaseSource",  this.panelBaseSource);
        this.getScrollPanelBaseSource().setViewportView(this.panelBaseSource);
        this.tabbedPane.add("Source", this.getScrollPanelBaseSource());
    }
    
    /**
     * Method responsible for adding the Panel Base Target.
     */
    protected void addPanelBaseTarget() {
        this.panelBaseTarget = new PanelBaseTarget(this.viewMenu, this.diagram, this.associationUML);
        this.createScrollPane("scrollPanelBaseTarget",  this.panelBaseTarget);
        this.getScrollPanelBaseTarget().setViewportView(this.panelBaseTarget);
        this.tabbedPane.add("Target", this.getScrollPanelBaseTarget());
    }
    
    /**
     * Method responsible for returning the Association UML.
     * @return Association UML.
     */
    public AssociationUML getAssociationUML() {
        return this.associationUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Association UML.
     * @return Scroll Panel Base Association UML.
     */
    public JScrollPane getScrollPanelBaseAssociationUML() {
        return this.scrollPanes.get("scrollPanelBaseAssociationUML");
    }
    
    /**
     * Method responsible for returning the Panel Base Association UML.
     * @return Panel Base Association UML.
     */
    public PanelBaseAssociationUML getPanelBaseAssociationUML() {
        return this.panelBaseAssociationUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Source.
     * @return Scroll Panel Base Source.
     */
    public JScrollPane getScrollPanelBaseSource() {
        return this.scrollPanes.get("scrollPanelBaseSource");
    }
    
    /**
     * Method responsible for returning the Panel Base Source.
     * @return Panel Base Source.
     */
    public PanelBaseSource getPanelBaseSource() {
        return this.panelBaseSource;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Target.
     * @return Scroll Panel Base Target.
     */
    public JScrollPane getScrollPanelBaseTarget() {
        return this.scrollPanes.get("scrollPanelBaseTarget");
    }
    
    /**
     * Method responsible for returning the Panel Base Target.
     * @return Panel Base Target.
     */
    public PanelBaseTarget getPanelBaseTarget() {
        return this.panelBaseTarget;
    }
}