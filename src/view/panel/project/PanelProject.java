package view.panel.project;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.AttributeUML;
import view.Panel;
import view.panel.edit.PanelEdit;
import view.panel.edit.base.PanelEditDiagram;
import view.panel.edit.base.PanelEditProject;
import view.panel.edit.base.classs.PanelEditAttributeUML;
import view.panel.tree.PanelTree;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelProject</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Project</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  11/06/2019
 * @see    view.Panel
 */
public final class PanelProject extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private JSplitPane splitPane;
    private PanelTree  panelTree;
    private PanelEdit  panelEdit;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelProject(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.initComponents();
        this.addComponents();
    }

    /**
     * Method responsible for initializing the Components.
     */
    private void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(240, 250));
        this.setMinimumSize(new Dimension(240, 220));
    }
    
    @Override
    protected void addComponents() {
        this.initVerticalSplitPane();
        this.initPanelTree();
        this.initPanelEdit();
        
        this.splitPane.setTopComponent(this.getScrollPanelTree());
        this.splitPane.setBottomComponent(this.getScrollPanelEdit());
        
        this.add(this.splitPane, this.getConstraints());
    }
    
    /**
     * Method responsible for initializing the Vertical Split Pane.
     */
    private void initVerticalSplitPane() {
        this.splitPane = new JSplitPane();
        this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    }
    
    /**
     * Method responsible for initializing the Panel Tree.
     */
    public void initPanelTree() {
        this.panelTree = new PanelTree(this.viewMenu);
        this.createScrollPane("scrollPanelTree");
        this.getScrollPanelTree().setViewportView(this.panelTree);
        this.getScrollPanelTree().setMinimumSize(new Dimension(200, 250));
        this.getScrollPanelTree().setPreferredSize(new Dimension(200, 300));
    }
    
    /**
     * Method responsible for initializing the Panel Edit.
     */
    public void initPanelEdit() {
        this.panelEdit = new PanelEdit(this.viewMenu);
        this.createScrollPane("scrollPanelEdit");
        this.getScrollPanelEdit().setViewportView(this.panelEdit);
        this.getScrollPanelEdit().setMinimumSize(new Dimension(200, 200));
        this.getScrollPanelEdit().setPreferredSize(new Dimension(200, 200));
    }
    
    /**
     * Method responsible for initializing the Panel Edit Project.
     */
    public void initPanelEditProject() {
        this.panelEdit = new PanelEditProject(this.viewMenu);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Diagram.
     * @param diagram Diagram.
     */
    public void initPanelEditDiagram(Diagram diagram) {
        this.panelEdit = new PanelEditDiagram(this.viewMenu, diagram);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Class Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(ClassDiagram diagram, Element element) {
        if (element instanceof AttributeUML)
            this.panelEdit = new PanelEditAttributeUML(this.viewMenu, diagram, (AttributeUML) element);
//            System.out.println("Attribute: " + (AttributeUML) element);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     */
    public void updatePanelEdit() {
        this.panelEdit.updateUI();
        this.getScrollPanelEdit().setViewportView(this.panelEdit);
        this.getScrollPanelEdit().updateUI();
    }
    
    /**
     * Method responsible for returning the Constraints.
     * @return Constraints.
     */
    private GridBagConstraints getConstraints() {
        GridBagConstraints constraints         = new GridBagConstraints();
                           constraints.fill    = GridBagConstraints.BOTH;
                           constraints.gridx   = 0;
                           constraints.weightx = 1;
                           constraints.weighty = 1;
                    return constraints;
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    public PanelTree getPanelTree() {
        return this.panelTree;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree.
     * @return Scroll Panel Tree.
     */
    public JScrollPane getScrollPanelTree() {
        return this.scrollPanes.get("scrollPanelTree");
    }
    
    /**
     * Method responsible for returning the Panel Edit.
     * @return Panel Edit.
     */
    public PanelEdit getPanelEdit() {
        return this.panelEdit;
    }
    
    /**
     * Method responsible for returning Scroll Panel Edit.
     * @return Scroll Panel Edit
     */
    public JScrollPane getScrollPanelEdit() {
        return this.scrollPanes.get("scrollPanelEdit");
    }
}