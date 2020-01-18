package view.panel.edit.base;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import view.edit.panel.base.PanelBaseDiagram;
import view.edit.panel.base.PanelBaseStereotype;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEdit</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Stereotype</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/01/2020
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditStereotype extends PanelEdit {
    private final Project project;
    private final Stereotype stereotype;
    private PanelBaseStereotype panelBaseStereotype;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param stereotype Stereotype.
     */
    public PanelEditStereotype(ViewMenu viewMenu, Stereotype stereotype) {
        super(viewMenu);
        this.project    = this.viewMenu.getProject();
        this.stereotype = stereotype;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseStereotype();
        
        this.add(this.tabbedPane);

        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Stereotype.
     */
    private void addPanelBaseStereotype() {
        this.panelBaseStereotype = new PanelBaseStereotype(this.viewMenu, this.stereotype);
        this.createScrollPane("scrollPanelBaseStereotype",  this.panelBaseStereotype);
        this.getScrollPanelBaseStereotype().setViewportView(this.panelBaseStereotype);
        this.tabbedPane.add("Stereotype", this.getScrollPanelBaseStereotype());
    }
    
    /**
     * Method responsible for returning the Stereotype.
     * @return Stereotype.
     */
    public Stereotype getStereotype() {
        return this.stereotype;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Stereotype.
     * @return Scroll Panel Base Stereotype.
     */
    public JScrollPane getScrollPanelBaseStereotype() {
        return this.scrollPanes.get("scrollPanelBaseStereotype");
    }
}