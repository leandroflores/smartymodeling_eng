package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.diagram.feature.base.Feature;
import view.panel.diagram.types.PanelFeatureDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelFeatureDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Feature Diagram Panel</b> of SMartyModeling.</p>
 * @author Henrique
 * @since  11/02/2020
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    view.panel.diagram.types.PanelFeatureDiagram
 */
public class ControllerPanelFeatureDiagram extends ControllerPanelDiagram {

    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Diagram.
     */
    public ControllerPanelFeatureDiagram(PanelFeatureDiagram panel) {
        super(panel);
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        if (this.getPanelDiagram().getOperation().equals("Feature"))
            this.addFeature(event);
    }

    /**
     * Method responsible for adding a New Feature.
     * @param event Mouse Event.
     */
    public void addFeature(MouseEvent event) {
        Feature feature = new Feature();
                feature.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addFeature(feature);
                feature.setDefaultName();
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().getPanelProject().getPanelTree().updateProjectNode();
//        this.panelBaseVariants.getViewMenu().getPanelProject().getPanelTree().getPanelTreeUML().updateNode(this.panelBaseVariants.getVariability());
//        this.getPanelDiagram().getViewMenu().update();
    }
    
    @Override
    protected PanelFeatureDiagram getPanelDiagram() {
        return (PanelFeatureDiagram) this.panel;
    }
}