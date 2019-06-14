package view.panel.edit.base.classs;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.Entity;
import view.edit.panel.base.PanelBaseElement;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditEntity</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Entity</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/06/2019
 * @see    model.structural.diagram.classs.Entity
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditEntity extends PanelEditElement {
    private final ClassDiagram diagram;
    private final Entity entity;
    private PanelBaseElement panelBaseElement;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param entity Entity.
     */
    public PanelEditEntity(ViewMenu viewMenu, ClassDiagram diagram, Entity entity) {
        super(viewMenu, entity);
        this.diagram = diagram;
        this.entity  = entity;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseElement();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     */
    private void addPanelBaseElement() {
        this.panelBaseElement = new PanelBaseElement(this.viewMenu, this.diagram, this.entity);
        this.createScrollPane("scrollPanelBaseElement", this.panelBaseElement);
        this.getScrollPanelBaseElement().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Element", this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Element.
     * @return Scroll Panel Base Element.
     */
    public JScrollPane getScrollPanelBaseElement() {
        return this.scrollPanes.get("scrollPanelBaseElement");
    }

    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return this.panelBaseElement;
    }
}