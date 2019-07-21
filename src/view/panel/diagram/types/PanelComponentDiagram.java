package view.panel.diagram.types;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationComponent;
import controller.view.panel.diagram.event.ControllerEventChange;
import controller.view.panel.diagram.event.ControllerEventEdit;
import controller.view.panel.diagram.event.ControllerEventMove;
import controller.view.panel.diagram.event.ControllerEventResize;
import controller.view.panel.diagram.types.ControllerPanelComponentDiagram;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.structural.diagram.ComponentDiagram;
import view.panel.diagram.PanelDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelComponentDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Component Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.panel.diagram.types.ControllerPanelComponentDiagram
 * @see    model.structural.diagram.ComponentDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelComponentDiagram extends PanelDiagram {
    private final ComponentDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Component Diagram.
     */
    public PanelComponentDiagram(ViewMenu view, ComponentDiagram diagram) {
        super(view, diagram);
        this.diagram    = diagram;
        this.controller = new ControllerPanelComponentDiagram(this);
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
                panel.add(this.createButton("clickButton",       "", "Select",          "click.png"));
                panel.add(this.createButton("componentButton",   "", "New Component",   "diagram/component/component.png"));
                panel.add(this.createButton("interfaceButton",   "", "New Interface",   "diagram/component/interface.png"));
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
            this.getAssociationImage("component/provide"),
            this.getAssociationImage("component/require"),
            this.getAssociationImage("dependency"),
            this.getAssociationImage("requires"),
            this.getAssociationImage("mutex")};
        return   items;
    }
    
    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getComponentButton().setBackground(this.getDefaultColor());
        this.getInterfaceButton().setBackground(this.getDefaultColor());
    }
    
//    @Override
//    public void addElements() {
//        for (int i = 0; i < this.diagram.getListaElementos().size(); i++) {
//            Elemento elemento = this.diagram.getListaElementos().get(i);
//            this.grafo.getStylesheet().putCellStyle(elemento.getRotuloEstilo(), elemento.getEstilo());
//            this.grafo.getStylesheet().putCellStyle("estiloImagemComponente", this.getEstiloImagemComponente());
//            String   titulo   = this.diagram.getEstereotipos(elemento) + elemento.getNome();
//            mxCell   vertice  = (mxCell) this.grafo.insertVertex(this.parent, null, titulo, elemento.getPosicao().x, elemento.getPosicao().y, elemento.getTamanho().x, elemento.getTamanho().y, elemento.getRotuloEstilo());
//            if (elemento instanceof ComponenteUML) {
//                this.grafo.insertVertex(vertice, null, "", 10, 10, 20, 20, "estiloImagemComponente");
//            }
//            this.objetos.put(vertice, elemento.getId());
//            this.entidades.put(elemento.getId(), vertice);
//        }
//    }
    
//    /**
//     * Metodo responsavel por retornar o Estilo da Imagem do Componente UML.
//     * @return Estilo da Imagem do Componente UML.
//     */
//    private Map getEstiloImagemComponente() {
//        Map    estilo = new HashMap<>();
//               estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
//               estilo.put(mxConstants.STYLE_IMAGE, "/imagens/diagramas/componentes/componente.png");
//               estilo.put(mxConstants.STYLE_EDITABLE, 0);
//               estilo.put(mxConstants.STYLE_RESIZABLE, 0);
//               estilo.put(mxConstants.STYLE_MOVABLE, 0);
//        return estilo;
//    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setProvideStyle();
                break;
            case 1:
                this.setRequireStyle();
                break;
            case 2:
            case 3:
            case 4:
                this.setDependencyStyle();
                break;
            default:
                this.setProvideStyle();    
                break;
        }
    }
    
    /**
     * Method responsible for setting the Provide Style.
     */
    private void setProvideStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    /**
     * Method responsible for setting the Require Style.
     */
    private void setRequireStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_OPEN);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationComponent(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
     }
    
    @Override
    public ComponentDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Component Button.
     * @return Component Button.
     */
    public JButton getComponentButton() {
        return this.buttons.get("componentButton");
    }
    
    /**
     * Method responsible for returning the Interface Button.
     * @return Interface Button.
     */
    public JButton getInterfaceButton() {
        return this.buttons.get("interfaceButton");
    }
}