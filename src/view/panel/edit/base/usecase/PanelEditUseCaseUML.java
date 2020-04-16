package view.panel.edit.base.usecase;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.edit.panel.base.PanelBaseElement;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditUseCaseUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Use Case UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  19/07/2019
 * @see    model.structural.diagram.usecase.base.UseCaseUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditUseCaseUML extends PanelEditElement {
    private final UseCaseDiagram diagram;
    private final UseCaseUML useCaseUML;
    private PanelBaseElement panelBaseElement;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Use Case Diagram.
     * @param useCaseUML Use Case UML.
     */
    public PanelEditUseCaseUML(ViewMenu viewMenu, UseCaseDiagram diagram, UseCaseUML useCaseUML) {
        super(viewMenu, useCaseUML);
        this.diagram    = diagram;
        this.useCaseUML = useCaseUML;
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
        this.panelBaseElement = new PanelBaseElement(this.viewMenu, this.diagram, this.useCaseUML);
        this.createScrollPane("scrollPanelBaseElement",   this.panelBaseElement);
        this.getScrollPanelBaseElement().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Use Case", this.getScrollPanelBaseElement());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public UseCaseDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Use Case UML.
     * @return Use Case UML.
     */
    public UseCaseUML getUseCaseUML() {
        return this.useCaseUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Element.
     * @return Scroll Panel Base Element.
     */
    public JScrollPane getScrollPanelBaseElement() {
        return this.getScrollPane("scrollPanelBaseElement");
    }

    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return this.panelBaseElement;
    }
}