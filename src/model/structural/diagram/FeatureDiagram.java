package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.association.Association;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.feature.base.association.Combination;
import model.structural.diagram.feature.base.association.Connection;

/**
 * <p>Class of Model <b>FeatureDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Feature Diagram</b> in SMartyModeling.</p>
 * @author Henrique
 * @since  2020-02-13
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Diagram
 * @see    model.structural.diagram.feature.base.Feature
 * @see    model.structural.diagram.feature.base.Variability
 * @see    model.structural.diagram.feature.base.association.Connection
 */
public final class FeatureDiagram extends Diagram {
    private HashMap<String, Feature>     features;
    private HashMap<String, Variability> variabilities_;
    private HashMap<String, Connection>  connections;
    private HashMap<String, Combination> combinations;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public FeatureDiagram(Project project) {
        super(project);
        init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public FeatureDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        init();
    }

    @Override
    public void init() {
        type           = "Feature";
        features       = new HashMap<>();
        variabilities_ = new HashMap<>();
        connections    = new HashMap<>();
        combinations   = new HashMap<>();
    }

    /**
     * Method responsible for adding a Feature.
     * @param feature Feature.
     */
    public void addFeature(Feature feature) {
        feature.setId(nextId(feature));
        if (features.get(feature.getId()) == null) {
            features.put(feature.getId(), feature);
            addElement(feature);
        }
    }
    
    /**
     * Method responsible for removing a Feature.
     * @param feature Feature.
     */
    public void removeFeature(Feature feature) {
        removeVariationPoint(feature);
        removeAssociations(feature);
        removeElement(feature);
        features.remove(feature.getId());
    }
    
    /**
     * Method responsible for returning Features List.
     * @return Features List.
     */
    public List<Feature> getFeaturesList() {
        return new ArrayList<>(features.values());
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param variability Variability.
     */
    public void addVariability(Variability variability) {
        variability.setId(nextId(variability));
        if (variabilities_.get(variability.getId()) == null) {
            variabilities_.put(variability.getId(), variability);
            addElement(variability);
        }
    }
    
    /**
     * Method responsible for removing a Variability.
     * @param variability Variability.
     */
    public void removeVariability(Variability variability) {
        removeAssociations(variability);
        removeElement(variability);
        variabilities_.remove(variability.getId());
    }
    
    /**
     * Method responsible for removing Variabilities by Variation Point.
     * @param feature Variation Point.
     */
    private void removeVariationPoint(Feature feature) {
        for (Variability variability_ : getVariationPoints(feature))
            removeVariability(variability_);
    }
    
    /**
     * Method responsible for returning Variation Points by Feature.
     * @param  feature Feature.
     * @return Variation Points.
     */
    public List<Variability> getVariationPoints(Feature feature) {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : getVariability()) {
            if (variability.getVariationPoint().equals(feature))
                filter.add(variability);
        }
        return filter;
    } 
    
    /**
     * Method responsible for returning Variability List.
     * @return Variability List.
     */
    public List<Variability> getVariability() {
        return new ArrayList<>(variabilities_.values());
    }
    
    /**
     * Method responsible for returning if a Feature is Root.
     * @param  feature Feature.
     * @return Feature is Root.
     */
    public boolean isRoot(Feature feature) {
        return getTargetAssociations("connection", feature).isEmpty();
    }
    
    /**
     * Method responsible for returning the Roots List.
     * @return Roots List.
     */
    public List<Feature> getRootsList() {
        List   roots = new ArrayList<>();
        for (Feature feature : getFeaturesList()) {
            if (isRoot(feature))
               roots.add(feature);
        }
        return roots;
    }
    
    
    
    /**
     * Method responsible for adding a Connection.
     * @param connection Connection.
     */
    public void addConnection(Connection connection) {
        connection.setId(nextId(connection));
        if (connections.get(connection.getId()) == null) {
            connections.put(connection.getId(), connection);
            addAssociation(connection);
        }
    }
    
    /**
     * Method responsible for removing a Connection.
     * @param connection Connection.
     */
    public void removeConnection(Connection connection) {
        super.removeAssociation(connection);
        connections.remove(connection.getId());
    }
    
    /**
     * Method responsible for returning the Connections List.
     * @return Connections List.
     */
    public List<Connection> getConnectionsList() {
        return new ArrayList(connections.values());
    }
    
    /**
     * Method responsible for adding a Combination.
     * @param combination Combination.
     */
    public void addCombination(Combination combination) {
        combination.setId(nextId(combination));
        if (combinations.get(combination.getId()) == null) {
            combinations.put(combination.getId(), combination);
            addAssociation(combination);
        }
    }
    
    /**
     * Method responsible for removing a Combination.
     * @param combination Combination.
     */
    public void removeCombination(Combination combination) {
        super.removeAssociation(combination);
        combinations.remove(combination.getId());
    }
    
    /**
     * Method responsible for returning the Combinations List.
     * @return Combinations List.
     */
    public List<Combination> getCombinationsList() {
        return new ArrayList(combinations.values());
    }
    
    /**
     * Method responsible for removing Connections by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        removeAssociation(element, createMap(connections.values().toArray()));
        removeAssociation(element, createMap(combinations.values().toArray()));
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof Connection)
            removeConnection((Connection) association);
        if (association instanceof Combination)
            removeCombination((Combination) association);
        super.removeAssociation(association);
    }
    
    /**
     * Method responsible for returning the Categories Array.
     * @return Categories Array.
     */
    public String[] getCategories() {
        return new String[]{"mandatory", "optional"};
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "feature.png";
    }
    
    @Override
    public String getInstanceIcon() {
        return "";
    }
    
    @Override
    public FeatureDiagram getClone() {
        try {
            FeatureDiagram diagram = (FeatureDiagram) super.clone();
                           diagram.setElements(new HashMap<>(elements));
                           diagram.setAssociations(new HashMap<>(associations));
                           diagram.setVariabilities(new HashMap<>(variabilities));
            return         diagram;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }
}