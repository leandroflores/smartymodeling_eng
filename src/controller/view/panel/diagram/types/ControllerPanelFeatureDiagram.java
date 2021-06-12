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
 * <p>Class responsible for controlling the <b>PanelFeatureDiagram</b> Events of SMartyModeling.</p>
 * @author Henrique
 * @since  2020-02-11
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.diagram.FeatureDiagram
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
        switch (getPanel().getOperation()) {
            case "Feature":
                addFeature(event);
                break;
            case "Inclusive":
            case "Exclusive":
                addVariability(event);
                break;
            default:
                break;
        }
    }

    /**
     * Method responsible for adding a New Feature.
     * @param event Mouse Event.
     */
    public void addFeature(MouseEvent event) {
        Feature feature = new Feature(getPanel().getDiagram());
                feature.setPosition(event.getX(), event.getY());
                getPanel().getDiagram().addFeature(feature);
                feature.setDefaultName();
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param event Mouse Event.
     */
    public void addVariability(MouseEvent event) {
        if (checkVariationPoint()) {
            String      category    = getPanel().getOperation();
            Feature     feature     = (Feature) getPanel().getSelectedElement();
            Variability variability = new Variability(getPanel().getDiagram(), category, feature);
                        variability.setPosition(event.getX(), event.getY());
            Combination combination = new Combination(variability, feature, true);
            getPanel().getDiagram().addVariability(variability);
            getPanel().getDiagram().addCombination(combination);
            getPanel().updateGraph();
            getPanel().getViewMenu().update();
        }
    }
    
    /**
     * Method responsible for checking if a Feature is Selected.
     * @return Feature is Selected.
     */
    private boolean checkVariationPoint() {
        Element element = getPanel().getSelectedElement();
        if (element == null || !(element instanceof Feature)) {
            new ViewError(getPanel().getViewMenu(), "Select a Feature as a Variation Point!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public PanelFeatureDiagram getPanel() {
        return (PanelFeatureDiagram) panel;
    }
}