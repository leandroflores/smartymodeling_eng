package view.panel.edit.base.classs;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.ClassUML;
import view.edit.panel.base.classs.PanelBaseClassUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditClassUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Class UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/06/2019
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditClassUML extends PanelEditElement {
    private final ClassDiagram diagram;
    private final ClassUML classUML;
    private PanelBaseClassUML panelBaseClassUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param classUML Class UML.
     */
    public PanelEditClassUML(ViewMenu viewMenu, ClassDiagram diagram, ClassUML classUML) {
        super(viewMenu, classUML);
        this.diagram  = diagram;
        this.classUML = classUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseClassUML();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Class UML.
     */
    private void addPanelBaseClassUML() {
        this.panelBaseClassUML = new PanelBaseClassUML(this.viewMenu, this.diagram, this.classUML);
        this.createScrollPane("scrollPanelBaseClassUML", this.panelBaseClassUML);
        this.getScrollPanelBaseClassUML().setViewportView(this.panelBaseClassUML);
        this.tabbedPane.add("Class", this.getScrollPanelBaseClassUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Class UML.
     * @return Class UML.
     */
    public ClassUML getClassUML() {
        return this.classUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Class UML.
     * @return Scroll Panel Base Class UML.
     */
    public JScrollPane getScrollPanelBaseClassUML() {
        return this.scrollPanes.get("scrollPanelBaseClassUML");
    }

    /**
     * Method responsible for returning the Panel Base Class UML.
     * @return Panel Base Class UML.
     */
    public PanelBaseClassUML getPanelBaseClassUML() {
        return this.panelBaseClassUML;
    }
}