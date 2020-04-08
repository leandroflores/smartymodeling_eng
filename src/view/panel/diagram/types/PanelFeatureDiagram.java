package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationFeature;
import controller.view.panel.diagram.event.ControllerEventChange;
import controller.view.panel.diagram.event.ControllerEventEdit;
import controller.view.panel.diagram.event.ControllerEventMove;
import controller.view.panel.diagram.event.ControllerEventFocus;
import controller.view.panel.diagram.event.ControllerEventPoints;
import controller.view.panel.diagram.event.ControllerEventResize;
import controller.view.panel.diagram.event.ControllerEventSelect;
import controller.view.panel.diagram.types.ControllerPanelFeatureDiagram;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.structural.base.Element;
import model.structural.diagram.FeatureDiagram;
import view.panel.diagram.PanelDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelUseCaseDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Feature Diagram Panel</b> of SMartyModeling.</p>
 * @author Henrique
 * @since  11/02/2020
 * @see    controller.view.panel.diagram.association.types.ControllerEventAssociationFeature
 * @see    controller.view.panel.diagram.types.ControllerPanelFeatureDiagram
 * @see    model.structural.diagram.FeatureDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelFeatureDiagram extends PanelDiagram {
    private final FeatureDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Feature Diagram.
     */
    public PanelFeatureDiagram(ViewMenu view, FeatureDiagram diagram) {
        super(view, diagram);
        this.diagram    = diagram;
        this.controller = new ControllerPanelFeatureDiagram(this);
        this.initComponents();
        this.addComponents();
        this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addOperationsPanel();
        this.addModelingPanel();
        this.addControllers();
    }
    
    @Override
    public void addOperationsPanel() {
        JPanel  panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.LEFT));
                panel.add(this.createButton("clickButton",       "", "Select",      "click.png"));
                panel.add(this.createButton("featureButton",     "", "New Feature", "diagram/activity/activity.png"));
                panel.add(this.createButton("variabilityButton", "", "",            "variability.png"));
                panel.add(this.createButton("editButton",        "", "Edit",        "edit.png"));
                panel.add(this.createButton("deleteButton",      "", "Delete",      "delete.png"));
                panel.add(this.createComboBox("associationComboBox", this.getAssociationItems(), 50));
       this.add(panel);
       this.getVariabilityButton().setVisible(false);
       this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public Object[] getAssociationItems() {
        Object[] items  = {
                 this.getAssociationImage("component/mandatory"),
                 this.getAssociationImage("component/optional"),
                 this.getAssociationImage("component/inclusive"),
                 this.getAssociationImage("component/exclusive")};
        return   items;
    }
    
    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getFeatureButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setRealizationStyle();
                break;
            case 1:
            case 2:
            case 4:
            case 5:
                this.setExtendStyle();
                break;
            case 3:
                this.setGeneralizationStyle();
                break;
            default:
                this.setDependencyStyle();
                break;
        }
    }

    @Override
    public void addElements() {
        for (int i = 0; i < this.diagram.getElementsList().size(); i++) {
            Element element = this.diagram.getElementsList().get(i);
            this.graph.getStylesheet().putCellStyle(element.getStyleLabel(), element.getStyle());
            String  title   = element.getName();
            mxCell  cell    = (mxCell) this.graph.insertVertex(this.parent, null, title, element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
            this.identifiers.put(cell, element.getId());
            this.objects.put(element.getId(), cell);
        }
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationFeature(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventSelect(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    /**
     * Method responsible for setting Realization Style.
     */
    private void setRealizationStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    /**
     * Method responsible for setting Extend Style.
     */
    private void setExtendStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_OPEN);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return this.diagram;
    }

    /**
     * Method responsible for returning the Feature Button.
     * @return Feature Button.
     */
    public JButton getFeatureButton() {
        return this.buttons.get("featureButton");
    }
}