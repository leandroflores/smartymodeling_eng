package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
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
    
    @Override
    public void addElements() {
        this.addComponentsUML();
        this.addInterfacesUML();
    }
    
    /**
     * Method responsible for adding the Diagram Components.
     */
    private void addComponentsUML() {
        this.graph.getStylesheet().putCellStyle("styleImageComponent", this.getImageComponentStyle());
        for (ComponentUML componentUML : this.diagram.getComponents()) {
            this.graph.getStylesheet().putCellStyle(componentUML.getStyleLabel(), componentUML.getStyle());
            String title  = this.diagram.getStereotypes(componentUML, "\n") + componentUML.getName();
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, componentUML.getId(), title, componentUML.getPosition().x, componentUML.getPosition().y, componentUML.getSize().x, componentUML.getSize().y, componentUML.getStyleLabel());
                   vertex.setConnectable(true);
            this.graph.insertVertex(vertex, null, "", 10, 10, 20, 20, "styleImageComponent");
            this.identifiers.put(vertex, componentUML.getId());
            this.objects.put(componentUML.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Interfaces.
     */
    private void addInterfacesUML() {
        for (InterfaceUML interfaceUML : this.diagram.getInterfaces()) {
            this.graph.getStylesheet().putCellStyle(interfaceUML.getStyleLabel(), interfaceUML.getStyle());
            String title  = this.diagram.getStereotypes(interfaceUML, "\n") + interfaceUML.getName();
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, interfaceUML.getId(), title, interfaceUML.getPosition().x, interfaceUML.getPosition().y, interfaceUML.getSize().x, interfaceUML.getSize().y, interfaceUML.getStyleLabel());
                   vertex.setConnectable(true);
            this.identifiers.put(vertex, interfaceUML.getId());
            this.objects.put(interfaceUML.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for returning the Image Component Style.
     * @return Image Component Style.
     */
    private Map getImageComponentStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/component/component.png");
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
        return style;
    }
    
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