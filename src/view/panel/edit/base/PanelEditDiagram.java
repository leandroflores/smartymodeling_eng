package view.panel.edit.base;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import view.panel.base.PanelBaseDiagram;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditDiagram</b>.</p> 
 * <p>Class responsible for defining a <b>Diagram Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-12
 * @see    model.structural.base.Diagram
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditDiagram extends PanelEdit {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     */
    public PanelEditDiagram(ViewMenu view, Diagram diagram) {
        super(view);
        this.diagram = diagram;
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
            this.addPanelBaseDiagram();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Diagram.
     */
    protected void addPanelBaseDiagram() {
        this.addPanel("panelBaseDiagram", new PanelBaseDiagram(this.viewMenu, this.diagram));
        this.createScrollPane("scrollPanelBaseDiagram",  this.getPanelBaseDiagram());
        this.getScrollPanelBaseDiagram().setViewportView(this.getPanelBaseDiagram());
        this.tabbedPane.add("Diagram", this.getScrollPanelBaseDiagram());
    }
    
    /**
     * Method responsible for returning the Panel Base Diagram.
     * @return Panel Base Diagram.
     */
    public PanelBaseDiagram getPanelBaseDiagram() {
        return (PanelBaseDiagram) this.getPanel("panelBaseDiagram");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Diagram.
     * @return Scroll Panel Base Diagram.
     */
    public JScrollPane getScrollPanelBaseDiagram() {
        return this.getScrollPane("scrollPanelBaseDiagram");
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
}