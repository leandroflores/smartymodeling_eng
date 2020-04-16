package view.panel.edit.base.sequence;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import view.edit.panel.base.sequence.PanelBaseInstanceUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditInstanceUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Instance UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/10/2019
 * @see    model.structural.diagram.sequence.base.InstanceUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditInstanceUML extends PanelEditElement {
    private final SequenceDiagram diagram;
    private final InstanceUML instanceUML;
    private PanelBaseInstanceUML panelBaseInstanceUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Sequence Diagram.
     * @param instanceUML Instance UML.
     */
    public PanelEditInstanceUML(ViewMenu viewMenu, SequenceDiagram diagram, InstanceUML instanceUML) {
        super(viewMenu, instanceUML);
        this.diagram     = diagram;
        this.instanceUML = instanceUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseInstanceUML();
        this.addPanelDependency();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Instance UML.
     */
    private void addPanelBaseInstanceUML() {
        this.panelBaseInstanceUML = new PanelBaseInstanceUML(this.viewMenu, this.diagram, this.instanceUML);
        this.createScrollPane("scrollPanelBaseInstanceUML", this.panelBaseInstanceUML);
        this.getScrollPanelBaseInstanceUML().setViewportView(this.panelBaseInstanceUML);
        this.tabbedPane.add("Instance", this.getScrollPanelBaseInstanceUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Instance UML.
     * @return Instance UML.
     */
    public InstanceUML getInstanceUML() {
        return this.instanceUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Instance UML.
     * @return Scroll Panel Base Instance UML.
     */
    public JScrollPane getScrollPanelBaseInstanceUML() {
        return this.getScrollPane("scrollPanelBaseInstanceUML");
    }

    /**
     * Method responsible for returning the Panel Base Instance UML.
     * @return Panel Base Instance UML.
     */
    public PanelBaseInstanceUML getPanelBaseInstanceUML() {
        return this.panelBaseInstanceUML;
    }
}