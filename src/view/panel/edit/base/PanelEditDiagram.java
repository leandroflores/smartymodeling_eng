package view.panel.edit.base;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Project;
import view.edit.panel.base.PanelBaseDiagram;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditDiagram</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Diagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  12/06/2019
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditDiagram extends PanelEdit {
    private final Project project;
    private final Diagram diagram;
    private PanelBaseDiagram panelBaseDiagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     */
    public PanelEditDiagram(ViewMenu viewMenu, Diagram diagram) {
        super(viewMenu);
        this.project = this.viewMenu.getProject();
        this.diagram = diagram;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseDiagram();
        
        this.add(this.tabbedPane);

//        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Diagram.
     */
    private void addPanelBaseDiagram() {
        this.panelBaseDiagram = new PanelBaseDiagram(this.viewMenu, this.diagram);
        this.createScrollPane("scrollPanelBaseDiagram",  this.panelBaseDiagram);
        this.getScrollPanelBaseDiagram().setViewportView(this.panelBaseDiagram);
        this.tabbedPane.add("Diagram", this.getScrollPanelBaseDiagram());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Diagram.
     * @return Scroll Panel Base Diagram.
     */
    public JScrollPane getScrollPanelBaseDiagram() {
        return this.getScrollPane("scrollPanelBaseDiagram");
    }
}