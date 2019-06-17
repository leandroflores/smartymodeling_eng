package view.panel.edit.base.classs;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.MethodUML;
import view.edit.panel.base.classs.PanelBaseMethodUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditMethodUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Method UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/06/2019
 * @see    model.structural.diagram.classs.base.MethodUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditMethodUML extends PanelEditElement {
    private final ClassDiagram diagram;
    private final MethodUML methodUML;
    private PanelBaseMethodUML panelBaseMethodUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param methodUML Method UML.
     */
    public PanelEditMethodUML(ViewMenu viewMenu, ClassDiagram diagram, MethodUML methodUML) {
        super(viewMenu, methodUML);
        this.diagram   = diagram;
        this.methodUML = methodUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseMethodUML();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Method UML.
     */
    private void addPanelBaseMethodUML() {
        this.panelBaseMethodUML = new PanelBaseMethodUML(this.viewMenu, this.diagram, this.methodUML);
        this.createScrollPane("scrollPanelBaseMethodUML", this.panelBaseMethodUML);
        this.getScrollPanelBaseMethodUML().setViewportView(this.panelBaseMethodUML);
        this.tabbedPane.add("Method", this.getScrollPanelBaseMethodUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Method UML.
     * @return Method UML.
     */
    public MethodUML getMethodUML() {
        return this.methodUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Method UML.
     * @return Scroll Panel Base Method UML.
     */
    public JScrollPane getScrollPanelBaseMethodUML() {
        return this.scrollPanes.get("scrollPanelBaseMethodUML");
    }

    /**
     * Method responsible for returning the Panel Base Method UML.
     * @return Panel Base Method UML.
     */
    public PanelBaseMethodUML getPanelBaseMethodUML() {
        return this.panelBaseMethodUML;
    }
}