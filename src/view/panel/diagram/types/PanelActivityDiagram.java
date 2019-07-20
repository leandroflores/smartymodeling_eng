package view.panel.diagram.types;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationActivity;
import controller.view.panel.diagram.types.ControllerPanelActivityDiagram;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.structural.diagram.ActivityDiagram;
import view.panel.diagram.PanelDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelActivityDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Activity Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/07/2019
 * @see    controller.view.panel.diagram.types.ControllerPanelActivityDiagram
 * @see    model.structural.diagram.ActivityDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelActivityDiagram extends PanelDiagram {
    private final ActivityDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Activity Diagram.
     */
    public PanelActivityDiagram(ViewMenu view, ActivityDiagram diagram) {
        super(view, diagram);
        this.diagram    = diagram;
        this.controller = new ControllerPanelActivityDiagram(this);
        this.initComponents();
        this.addComponents();
        this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public void addComponents() {
        this.addOperationsPanel();
        this.addModelingPanel();
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationActivity(this));
    }
    
    @Override
    public void addOperationsPanel() {
        JPanel panel = new JPanel();
               panel.setLayout(new FlowLayout(FlowLayout.LEFT));
               panel.add(this.createButton("clickButton",       "", "Select",          "click.png"));
               panel.add(this.createButton("initialButton",     "", "New Initial",     "diagram/activity/initial.png"));
               panel.add(this.createButton("activityButton",    "", "New Activity",    "diagram/activity/activity.png"));
               panel.add(this.createButton("decisionButton",    "", "New Decision",    "diagram/activity/decision.png"));
               panel.add(this.createButton("finalButton",       "", "New Final",       "diagram/activity/final.png"));
               panel.add(this.createButton("variabilityButton", "", "New Variability", "variability.png"));
               panel.add(this.createButton("editButton",        "", "Edit",            "edit.png"));
               panel.add(this.createButton("deleteButton",      "", "Delete",          "delete.png"));
               panel.add(this.createComboBox("associationComboBox", this.getAssociationItems(), 50));
       this.add(panel, BorderLayout.PAGE_START);
       this.add(new JSeparator(), BorderLayout.PAGE_END);
       this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public Object[] getAssociationItems() {
        Object[] items  = {
            this.getAssociationImage("classs/association"),
            this.getAssociationImage("dependency"),
            this.getAssociationImage("requires"),
            this.getAssociationImage("mutex")};
        return   items;
    }
    
    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getInitialButton().setBackground(this.getDefaultColor());
        this.getActivityButton().setBackground(this.getDefaultColor());
        this.getDecisionButton().setBackground(this.getDefaultColor());
        this.getFinalButton().setBackground(this.getDefaultColor());
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
                this.setFlowStyle();
                break;
            case 1:
            case 2:
            case 3:
                this.setDependencyStyle();
                break;
            default:
                this.setFlowStyle();    
                break;
        }
    }
    
    /**
     * Method responsible for setting the Flow Style.
     */
    private void setFlowStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Initial Button.
     * @return Initial Button.
     */
    public JButton getInitialButton() {
        return this.buttons.get("initialButton");
    }
    
    /**
     * Method responsible for returning the Activity Button.
     * @return Activity Button.
     */
    public JButton getActivityButton() {
        return this.buttons.get("activityButton");
    }
    
    /**
     * Method responsible for returning the Decision Button.
     * @return Decision Button.
     */
    public JButton getDecisionButton() {
        return this.buttons.get("decisionButton");
    }
    
    /**
     * Method responsible for returning the Final Button.
     * @return Final Button.
     */
    public JButton getFinalButton() {
        return this.buttons.get("finalButton");
    }
}