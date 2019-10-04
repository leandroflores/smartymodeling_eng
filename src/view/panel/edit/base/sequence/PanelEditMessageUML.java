package view.panel.edit.base.sequence;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.edit.panel.base.sequence.PanelBaseMessageUML;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditMessageUML</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Message UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/10/2019
 * @see    model.structural.diagram.sequence.base.association.MessageUML
 * @see    view.panel.edit.base.PanelEdit
 */
public final class PanelEditMessageUML extends PanelEdit {
    private final SequenceDiagram diagram;
    private final MessageUML messageUML;
    private PanelBaseMessageUML panelBaseMessageUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Sequence Diagram.
     * @param messageUML Message UML.
     */
    public PanelEditMessageUML(ViewMenu viewMenu, SequenceDiagram diagram, MessageUML messageUML) {
        super(viewMenu);
        this.diagram    = diagram;
        this.messageUML = messageUML;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseMessageUML();
//        this.addPanelStereotype();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Message UML.
     */
    private void addPanelBaseMessageUML() {
        this.panelBaseMessageUML = new PanelBaseMessageUML(this.viewMenu, this.diagram, this.messageUML);
        this.createScrollPane("scrollPanelBaseMessageUML", this.panelBaseMessageUML);
        this.getScrollPanelBaseMessageUML().setViewportView(this.panelBaseMessageUML);
        this.tabbedPane.add("Message", this.getScrollPanelBaseMessageUML());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Message UML.
     * @return Message UML.
     */
    public MessageUML getMessageUML() {
        return this.messageUML;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Message UML.
     * @return Scroll Panel Base Message UML.
     */
    public JScrollPane getScrollPanelBaseMessageUML() {
        return this.scrollPanes.get("scrollPanelBaseMessageUML");
    }

    /**
     * Method responsible for returning the Panel Base Message UML.
     * @return Panel Base Message UML.
     */
    public PanelBaseMessageUML getPanelBaseMessageUML() {
        return this.panelBaseMessageUML;
    }
}