package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import java.util.List;
import model.structural.base.Element;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.feature.base.association.Combination;
import model.structural.diagram.feature.base.association.Connection;
import view.modal.message.ViewError;
import view.panel.diagram.types.PanelFeatureDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationFeature</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Feature Diagram Association</b> of SMartyModeling.</p>
 * @author Henrique
 * @since  2020-02-11
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.FeatureDiagram
 * @see    view.panel.diagram.types.PanelFeatureDiagram
 */
public class ControllerEventAssociationFeature extends ControllerEventAssociation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Diagram.
     */
    public ControllerEventAssociationFeature(PanelFeatureDiagram panel) {
        super(panel);
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        if (check(source, target) && distinct(source, target)
                && containsRoot(target)  && isValid(target))
            createAssociation(association);
    }
    
    /**
     * Method responsible for checking the Association Types.
     * @param  source Association Source.
     * @param  target Association Target.
     * @return Association Types is checked.
     */
    private boolean checkFeature(Element source, Element target) {
        return source instanceof Feature 
            && target instanceof Feature;
    }
    
    /**
     * Method responsible for returning if Feature is Valid to Connection.
     * @param  feature Feature Target.
     * @return Feature is Valid to Connection.
     */
    private boolean isValid(Element feature) {
        if (!getDiagram().getSourceAssociations("connection", feature).isEmpty()) {
            new ViewError(getViewMenu(), "Feature is already Connection Target!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for returning if Diagram contains Feature Root.
     * @param  feature Target Feature.
     * @return Diagram contains Feature Root.
     */
    private boolean containsRoot(Element feature) {
        List<Feature> roots = getDiagram().getRootsList();
        return  roots.size() >  1
            || (roots.size() == 1 && !roots.get(0).equals(feature));
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (getPanel().getType()) {
            case 0:
            case 1:
                addConnection(association);
                break;
            case 2:
                addCombination(association);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding a Connection.
     * @param association Association.
     */
    private void addConnection(mxCell association) {
        Connection connection = createConnection(association);
        if (connection != null)
            getDiagram().addConnection(connection);
    }

    /**
     * Method responsible for returning a new Connection.
     * @param  association Association.
     * @return Connection.
     */
    private Connection createConnection(mxCell association) {
        Element source   = getSource(association);
        Element target   = getTarget(association);
        String  category = getCategory();
        try {
            return new Connection((Feature) source, (Feature) target, category);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for returning the Connection Category.
     * @return Connection Category.
     */
    private String getCategory() {
        switch (getPanel().getType()) {
            case 1:
                return "optional";
            default:
                return "mandatory";
        }
    }
    
    /**
     * Method responsible for adding a Combination.
     * @param association Association.
     */
    private void addCombination(mxCell association) {
        Combination combination = createCombination(association);
        if (combination != null) {
            Variability variability = combination.getSource();
            if (!variability.getVariationPoint().equals(combination.getTarget())) {
                getDiagram().addCombination(combination);
                variability.addVariant(combination.getTarget());
            }
        }
    }
    
    /**
     * Method responsible for returning a new Combination.
     * @param  association Association.
     * @return Combination.
     */
    private Combination createCombination(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        boolean root   = false;
        try {
            return new Combination((Variability) source, (Feature) target, root);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) diagram;
    }
    
    @Override
    public PanelFeatureDiagram getPanel() {
        return (PanelFeatureDiagram) panel;
    }
}