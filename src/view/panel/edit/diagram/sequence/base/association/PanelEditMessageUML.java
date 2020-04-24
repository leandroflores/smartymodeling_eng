package view.panel.edit.diagram.sequence.base.association;

import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.base.diagram.sequence.base.association.PanelBaseMessageUML;
import view.panel.edit.base.PanelEditAssociation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditMessageUML</b>.</p> 
 * <p>Class responsible for defining a <b>Message UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-04
 * @see    model.structural.diagram.sequence.base.association.MessageUML
 * @see    view.panel.edit.base.PanelEditAssociation
 */
public final class PanelEditMessageUML extends PanelEditAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     * @param message Message UML.
     */
    public PanelEditMessageUML(ViewMenu view, SequenceDiagram diagram, MessageUML message) {
        super(view, diagram, message);
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseMessageUML();
    }
    
    /**
     * Method responsible for adding the Panel Base Message UML.
     */
    protected void addPanelBaseMessageUML() {
        this.addPanel("panelBaseMessageUML", new PanelBaseMessageUML(this.viewMenu, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseMessageUML",  this.getPanelBaseMessageUML());
        this.getScrollPane("scrollPanelBaseMessageUML").setViewportView(this.getPanelBaseMessageUML());
        this.tabbedPane.add("Message", this.getScrollPane("scrollPanelBaseMessageUML"));
    }
    
    /**
     * Method responsible for returning the Panel Base Message UML.
     * @return Panel Base Message UML.
     */
    public PanelBaseMessageUML getPanelBaseMessageUML() {
        return (PanelBaseMessageUML) this.getPanel("panelBaseMessageUML");
    }
    
    @Override
    public MessageUML getAssociation() {
        return (MessageUML) this.association;
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.diagram;
    }
}