package view.panel.edit.base.classes;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.PackageUML;
import view.edit.panel.base.PanelBaseElement;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditPackageUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Package UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/08/2019
 * @see    model.structural.diagram.classes.base.PackageUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditPackageUML extends PanelEditElement {
    private final ClassDiagram diagram;
    private final PackageUML packageUML;
    private PanelBaseElement panelBaseElement;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param packageUML Package UML.
     */
    public PanelEditPackageUML(ViewMenu viewMenu, ClassDiagram diagram, PackageUML packageUML) {
        super(viewMenu, packageUML);
        this.diagram    = diagram;
        this.packageUML = packageUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBasePackageUML();
        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Package UML.
     */
    private void addPanelBasePackageUML() {
        this.panelBaseElement = new PanelBaseElement(this.viewMenu, this.diagram, this.packageUML);
        this.createScrollPane("scrollPanelBasePackageUML", this.panelBaseElement);
        this.getScrollPanelBasePackageUML().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Package", this.getScrollPanelBasePackageUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Package UML.
     * @return Package UML.
     */
    public PackageUML getPackageUML() {
        return this.packageUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Package UML.
     * @return Scroll Panel Base Package UML.
     */
    public JScrollPane getScrollPanelBasePackageUML() {
        return this.scrollPanes.get("scrollPanelBasePackageUML");
    }
}