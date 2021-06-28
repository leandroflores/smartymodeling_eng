package view.panel.edit.diagram.sequence.base;

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
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseLifelineUML();
        addPanelDependency();
    }
    
    /**
     * Method responsible for adding the Panel Base Lifeline UML.
     */
    protected void addPanelBaseLifelineUML() {
        addPanel("base_lifeline", new PanelBaseLifelineUML(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_lifeline", getPanelBaseLifelineUML());
        getScrollPane("base_lifeline").setViewportView(getPanelBaseLifelineUML());
        tabbedPane.add("Lifeline", getScrollPane("base_lifeline"));
    }
    
    /**
     * Method responsible for returning the Panel Base Lifeline UML.
     * @return Panel Base Lifeline UML.
     */
    public PanelBaseLifelineUML getPanelBaseLifelineUML() {
        return (PanelBaseLifelineUML) getPanel("base_lifeline");
    }
    
    @Override
    public LifelineUML getElement() {
        return (LifelineUML) element;
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
}