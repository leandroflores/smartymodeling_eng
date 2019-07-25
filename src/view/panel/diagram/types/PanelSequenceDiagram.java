package view.panel.diagram.types;

import com.mxgraph.util.mxConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.structural.diagram.SequenceDiagram;
import view.panel.diagram.PanelDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelSequenceDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    controller.view.panel.diagram.types.
 * @see    model.structural.diagram.SequenceDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelSequenceDiagram extends PanelDiagram {
    private final SequenceDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     */
    public PanelSequenceDiagram(ViewMenu view, SequenceDiagram diagram) {
        super(view, diagram);
        this.diagram    = diagram;
//        this.controller = new ControllerPanelActivityDiagram(this);
        this.initComponents();
        this.addComponents();
        this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public void addComponents() {
        this.addOperationsPanel();
        this.addModelingPanel();
        this.addControllers();
    }
    
    @Override
    public void addOperationsPanel() {
        JPanel  panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.LEFT));
                panel.add(this.createButton("clickButton",         "", "Select",             "click.png"));
                panel.add(this.createButton("lifelineActorButton", "", "New Actor Lifeline", "diagram/sequence/lifeline-actor.png"));
                panel.add(this.createButton("lifelineClassButton", "", "New Class Lifeline", "diagram/sequence/lifeline-class.png"));
                panel.add(this.createButton("variabilityButton",   "", "New Variability",    "variability.png"));
                panel.add(this.createButton("editButton",          "", "Edit",               "edit.png"));
                panel.add(this.createButton("deleteButton",        "", "Delete",             "delete.png"));
                panel.add(this.createComboBox("associationComboBox", this.getAssociationItems(), 50));
       this.add(panel, BorderLayout.PAGE_START);
       this.add(new JSeparator(), BorderLayout.PAGE_END);
       this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public Object[] getAssociationItems() {
        Object[] items  = {
            this.getAssociationImage("sequence/message-a"),
            this.getAssociationImage("sequence/message-s"),
            this.getAssociationImage("dependency"),
            this.getAssociationImage("requires"),
            this.getAssociationImage("mutex")};
        return   items;
    }
    
    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getLifelineActorButton().setBackground(this.getDefaultColor());
        this.getLifelineClassButton().setBackground(this.getDefaultColor());
    }
    
//    @Override
//    public void addElements() {
//        
//        for (int i = 0; i < this.diagram.getElementsList().size(); i++) {
//            Elemento elemento = this.diagram.getListaElementos().get(i);
//            this.grafo.getStylesheet().putCellStyle(elemento.getRotuloEstilo(), elemento.getEstilo());
//            String   titulo   = this.diagram.getEstereotipos(elemento) + elemento.getNome();
//            mxCell   vertice  = (mxCell) this.grafo.insertVertex(this.parent, null, titulo, elemento.getPosicao().x, elemento.getPosicao().y, elemento.getTamanho().x, elemento.getTamanho().y, elemento.getRotuloEstilo());
//            this.objetos.put(vertice, elemento.getId());
//            this.entidades.put(elemento.getId(), vertice);
//        }
//    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setMessageStyle();
                break;
            case 1:
            case 2:
            case 3:
                this.setDependencyStyle();
                break;
            default:
                this.setMessageStyle();    
                break;
        }
    }
    
    /**
     * Method responsible for setting the Message Style.
     */
    private void setMessageStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    @Override
     public void addControllers() {
//        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationActivity(this));
//        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
//        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
//        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
//        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
     }
    
    @Override
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Lifeline Actor Button.
     * @return Lifeline Actor Button.
     */
    public JButton getLifelineActorButton() {
        return this.buttons.get("lifelineActorButton");
    }
    
    /**
     * Method responsible for returning the Lifeline Class Button.
     * @return Lifeline Class Button.
     */
    public JButton getLifelineClassButton() {
        return this.buttons.get("lifelineClassButton");
    }
}