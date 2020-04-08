package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
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
    private final PanelFeatureDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Diagram.
     */
    public ControllerPanelFeatureDiagram(PanelFeatureDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelDiagram.getFeatureButton().equals(event.getSource()))
            this.setAddFeature();
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        if (this.panelDiagram.getOperation().equals("Feature"))
            this.addFeature(event);
    }
    
    /**
     * Method responsible for defining Add Feature Operation.
     */
    public void setAddFeature() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getFeatureButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Feature");
    }

    /**
     * Method responsible for adding a new Feature.
     * @param event Mouse Event.
     */
    public void addFeature(MouseEvent event) {
        Feature feature = new Feature();
                feature.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addFeature(feature);
                feature.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
}