package view.panel.edit.diagram.sequence.base;

import javax.swing.JTabbedPane;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.LifelineUML;
import view.panel.base.diagram.sequence.base.PanelBaseLifelineUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditLifelineUML</b>.</p> 
 * <p>Class responsible for defining a <b>Lifeline UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-03
 * @see    model.structural.diagram.sequence.base.LifelineUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditLifelineUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     * @param lifeline Lifeline UML.
     */
    public PanelEditLifelineUML(ViewMenu view, SequenceDiagram diagram, LifelineUML lifeline) {
        super(view, diagram, lifeline);
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        
        this.addPanelBaseLifelineUML();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Lifeline UML.
     */
    protected void addPanelBaseLifelineUML() {
        this.addPanel("panelBaseLifelineUML", new PanelBaseLifelineUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseElement",  this.getPanelBaseLifelineUML());
        this.getScrollPanelBaseElement().setViewportView(this.getPanelBaseLifelineUML());
        this.tabbedPane.add("Lifeline", this.getScrollPanelBaseElement());
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.diagram;
    }
    
    @Override
    public LifelineUML getElement() {
        return (LifelineUML) this.element;
    }

    /**
     * Method responsible for returning the Panel Base Lifeline UML.
     * @return Panel Base Lifeline UML.
     */
    public PanelBaseLifelineUML getPanelBaseLifelineUML() {
        return (PanelBaseLifelineUML) this.getPanel("panelBaseLifelineUML");
    }
}