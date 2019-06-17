package view.panel.edit.base.classs;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.InterfaceUML;
import view.edit.panel.base.PanelBaseElement;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditInterfaceUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Interface UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/06/2019
 * @see    model.structural.diagram.classs.base.InterfaceUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditInterfaceUML extends PanelEditElement {
    private final ClassDiagram diagram;
    private final InterfaceUML interfaceUML;
    private PanelBaseElement panelBaseElement;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param interfaceUML Interface UML.
     */
    public PanelEditInterfaceUML(ViewMenu viewMenu, ClassDiagram diagram, InterfaceUML interfaceUML) {
        super(viewMenu, interfaceUML);
        this.diagram      = diagram;
        this.interfaceUML = interfaceUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseInterfaceUML();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Interface UML.
     */
    private void addPanelBaseInterfaceUML() {
        this.panelBaseElement = new PanelBaseElement(this.viewMenu, this.diagram, this.interfaceUML);
        this.createScrollPane("scrollPanelBaseInterfaceUML", this.panelBaseElement);
        this.getScrollPanelBaseInterfaceUML().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Interface", this.getScrollPanelBaseInterfaceUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Interface UML.
     * @return Interface UML.
     */
    public InterfaceUML getInterfaceUML() {
        return this.interfaceUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Interface UML.
     * @return Scroll Panel Base Interface UML.
     */
    public JScrollPane getScrollPanelBaseInterfaceUML() {
        return this.scrollPanes.get("scrollPanelBaseInterfaceUML");
    }
}