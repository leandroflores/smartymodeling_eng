package view.panel.edit.base.classs;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.AttributeUML;
import view.edit.panel.base.classs.PanelBaseAttributeUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditAttributeUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Attribute UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  12/06/2019
 * @see    model.structural.diagram.classs.base.AttributeUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditAttributeUML extends PanelEditElement {
    private final ClassDiagram diagram;
    private final AttributeUML attributeUML;
    private PanelBaseAttributeUML panelBaseAttributeUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param attributeUML Attribute UML.
     */
    public PanelEditAttributeUML(ViewMenu viewMenu, ClassDiagram diagram, AttributeUML attributeUML) {
        super(viewMenu, attributeUML);
        this.diagram      = diagram;
        this.attributeUML = attributeUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseAttributeUML();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Attribute UML.
     */
    private void addPanelBaseAttributeUML() {
        this.panelBaseAttributeUML = new PanelBaseAttributeUML(this.diagram, this.attributeUML);
        this.createScrollPane("scrollPanelBaseAttributeUML", this.panelBaseAttributeUML);
        this.getScrollPanelBaseAttributeUML().setViewportView(this.panelBaseAttributeUML);
        this.tabbedPane.add("Attribute", this.getScrollPanelBaseAttributeUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Attribute UML.
     * @return Attribute UML.
     */
    public AttributeUML getAttributeUML() {
        return this.attributeUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Attribute UML.
     * @return Scroll Panel Base Attribute UML.
     */
    public JScrollPane getScrollPanelBaseAttributeUML() {
        return this.scrollPanes.get("scrollPanelBaseAttributeUML");
    }

    /**
     * Method responsible for returning the Panel Base Attribute UML.
     * @return Panel Base Attribute UML.
     */
    public PanelBaseAttributeUML getPanelBaseAttributeUML() {
        return this.panelBaseAttributeUML;
    }
}