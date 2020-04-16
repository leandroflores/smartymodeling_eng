package view.panel.edit.base.sequence;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.LifelineUML;
import view.edit.panel.base.sequence.PanelBaseLifelineUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditLifelineUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Lifeline UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/10/2019
 * @see    model.structural.diagram.sequence.base.LifelineUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditLifelineUML extends PanelEditElement {
    private final SequenceDiagram diagram;
    private final LifelineUML lifelineUML;
    private PanelBaseLifelineUML panelBaseLifelineUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Sequence Diagram.
     * @param lifelineUML Lifeline UML.
     */
    public PanelEditLifelineUML(ViewMenu viewMenu, SequenceDiagram diagram, LifelineUML lifelineUML) {
        super(viewMenu, lifelineUML);
        this.diagram     = diagram;
        this.lifelineUML = lifelineUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseLifelineUML();
//        this.addPanelStereotype();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Lifeline UML.
     */
    private void addPanelBaseLifelineUML() {
        this.panelBaseLifelineUML = new PanelBaseLifelineUML(this.viewMenu, this.diagram, this.lifelineUML);
        this.createScrollPane("scrollPanelBaseLifelineUML", this.panelBaseLifelineUML);
        this.getScrollPanelBaseLifelineUML().setViewportView(this.panelBaseLifelineUML);
        this.tabbedPane.add("Lifeline", this.getScrollPanelBaseLifelineUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Lifeline UML.
     * @return Lifeline UML.
     */
    public LifelineUML getLifelineUML() {
        return this.lifelineUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Lifeline UML.
     * @return Scroll Panel Base Lifeline UML.
     */
    public JScrollPane getScrollPanelBaseLifelineUML() {
        return this.getScrollPane("scrollPanelBaseLifelineUML");
    }

    /**
     * Method responsible for returning the Panel Base Lifeline UML.
     * @return Panel Base Lifeline UML.
     */
    public PanelBaseLifelineUML getPanelBaseLifelineUML() {
        return this.panelBaseLifelineUML;
    }
}