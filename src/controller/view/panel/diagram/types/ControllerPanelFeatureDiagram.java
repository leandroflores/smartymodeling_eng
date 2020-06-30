package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.feature.base.association.Combination;
import view.modal.message.ViewError;
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
        else if (this.getPanelDiagram().getOperation().equals("Inclusive")
              || this.getPanelDiagram().getOperation().equals("Exclusive"))
            this.addVariability(event);
                
    }

    /**
     * Method responsible for adding a New Feature.
     * @param event Mouse Event.
     */
    public void addFeature(MouseEvent event) {
        Feature feature = new Feature(this.getPanelDiagram().getDiagram());
                feature.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addFeature(feature);
                feature.setDefaultName();
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param event Mouse Event.
     */
    public void addVariability(MouseEvent event) {
        if (this.checkVariationPoint()) {
            String      category    = this.getPanelDiagram().getOperation();
            Feature     feature     = (Feature) this.getPanelDiagram().getSelectedElement();
            Variability variability = new Variability(this.getPanelDiagram().getDiagram(), category, feature);
                        variability.setPosition(event.getX(), event.getY());
            Combination combination = new Combination(variability, feature, true);
            this.getPanelDiagram().getDiagram().addVariability(variability);
            this.getPanelDiagram().getDiagram().addCombination(combination);
            this.getPanelDiagram().updateGraph();
            this.getPanelDiagram().getViewMenu().update();
        }
    }
    
    /**
     * Method responsible for checking if a Feature is Selected.
     * @return Feature is Selected.
     */
    private boolean checkVariationPoint() {
        Element element  = this.getPanelDiagram().getSelectedElement();
        if (element == null || !(element instanceof Feature)) {
            new ViewError(this.getPanelDiagram().getViewMenu(), "Select a Feature as a Variation Point!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    protected PanelFeatureDiagram getPanelDiagram() {
        return (PanelFeatureDiagram) this.panel;
    }
}