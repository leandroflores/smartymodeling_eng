package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.association.Association;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.association.Connection;

/**
 * <p>Class of Model <b>FeatureDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Feature Diagram</b> in SMartyModeling.</p>
 * @author Henrique
 * @since  13/02/2020
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Diagram
 * @see    model.structural.diagram.feature.base.Feature
 * @see    model.structural.diagram.feature.base.association.Connection
 */
public final class FeatureDiagram extends Diagram {
    private HashMap<String, Feature> features;
    private HashMap<String, Connection> connections;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public FeatureDiagram(Project project) {
        super(project);
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public FeatureDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }

    @Override
    public void init() {
        this.type        = "Feature";
        this.features    = new HashMap<>();
        this.connections = new HashMap<>();
    }

    /**
     * Method responsible for adding a Feature.
     * @param feature Feature.
     */
    public void addFeature(Feature feature) {
        feature.setId(this.nextId(feature));
        if (this.features.get(feature.getId()) == null) {
            this.features.put(feature.getId(), feature);
            this.addElement(feature);
        }
    }
    
    /**
     * Method responsible for removing a Feature.
     * @param feature Feature.
     */
    public void removeFeature(Feature feature) {
        this.removeAssociations(feature);
        this.removeElement(feature);
        this.features.remove(feature.getId());
    }
    
    /**
     * Method responsible for returning if a Feature is Root.
     * @param  feature Feature.
     * @return Feature is Root.
     */
    public boolean isRoot(Feature feature) {
        return this.getTargetAssociations("connection", feature).isEmpty();
    }
    
    /**
     * Method responsible for returning the Roots List.
     * @return Roots List.
     */
    public List<Feature> getRootsList() {
        List   roots = new ArrayList<>();
        for (Feature feature : this.getFeaturesList()) {
            if (this.isRoot(feature))
               roots.add(feature);
        }
        return roots;
    }
    
    /**
     * Method responsible for returning Features List.
     * @return Features List.
     */
    public List<Feature> getFeaturesList() {
        return new ArrayList<>(this.features.values());
    }
    
    /**
     * Method responsible for adding a Connection.
     * @param connection Connection.
     */
    public void addConnection(Connection connection) {
        connection.setId(this.nextId(connection));
        if (this.connections.get(connection.getId()) == null) {
            this.connections.put(connection.getId(), connection);
            this.addAssociation(connection);
        }
    }
    
    /**
     * Method responsible for removing a Connection.
     * @param connection Connection.
     */
    public void removeConnection(Connection connection) {
        super.removeAssociation(connection);
        this.connections.remove(connection.getId());
    }
    
    /**
     * Method responsible for returning the Connections List.
     * @return Connections List.
     */
    public List<Connection> getConnectionsList() {
        return new ArrayList(this.connections.values());
    }
    
    /**
     * Method responsible for removing Connections by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.createMap(this.connections.values().toArray()));
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof Connection)
            this.removeConnection((Connection) association);
        super.removeAssociation(association);
    }
    
    /**
     * Method responsible for returning the Categories Array.
     * @return Categories Array.
     */
    public String[] getCategories() {
        return new String[]{"mandatory", "optional", "inclusive", "exclusive"};
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
                           diagram.setElements(new HashMap<>(this.elements));
                           diagram.setAssociations(new HashMap<>(this.associations));
                           diagram.setVariabilities(new HashMap<>(this.variabilities));
            return         diagram;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error");
            return null;
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}