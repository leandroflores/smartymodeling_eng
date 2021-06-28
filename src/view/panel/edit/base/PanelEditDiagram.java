package view.panel.edit.base;

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
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseDiagram();
    }
    
    /**
     * Method responsible for adding the Panel Base Diagram.
     */
    protected void addPanelBaseDiagram() {
        addPanel("base_diagram", new PanelBaseDiagram(viewMenu, diagram));
        createScrollPane("base_diagram", getPanelBaseDiagram());
        getScrollPane("base_diagram").setViewportView(getPanelBaseDiagram());
        tabbedPane.add("Diagram", getScrollPane("base_diagram"));
    }
    
    /**
     * Method responsible for returning the Panel Base Diagram.
     * @return Panel Base Diagram.
     */
    public PanelBaseDiagram getPanelBaseDiagram() {
        return (PanelBaseDiagram) getPanel("base_diagram");
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}