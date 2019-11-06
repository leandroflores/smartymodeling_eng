package model.structural.base.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.interfaces.Exportable;
import model.structural.base.variability.Mutex;
import model.structural.base.variability.Requires;

/**
 * <p>Class of Model <b>Instance</b>.</p>
 * <p>Class responsible for representing the <b>Instance</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    model.structural.base.interfaces.Exportable
 */
public class Instance implements Exportable {
    private String  id;
    private String  name;
    private Product product;
    private Diagram diagram;
    private HashMap artifacts;
    private HashMap associations;
    
    /**
     * Default constructor method of Class.
     */
    public Instance() {
        this.product      = null;
        this.diagram      = null;
        this.artifacts    = new HashMap<>();
        this.associations = new HashMap<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Instance(org.w3c.dom.Element element) {
        this();
        this.id   = element.getAttribute("id");
        this.name = element.getAttribute("name");
    }
    
    /**
     * Method responsible for returning the Instance Id.
     * @return Instance Id.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Method responsible for returning the Instance Complete Id.
     * @return Instance Complete Id.
     */
    public String getCompleteId() {
        return this.product.getId() + " - " + this.id;
    }

    /**
     * Method responsible for setting the Instance Id.
     * @param id Instance Id.
     */
    public void setId(String id) {
        this.id = ((this.id == null) || (this.id.trim().equals(""))) ? id : this.id;
    }
   
    /**
     * Method responsible for returning the Instance Name.
     * @return Instance Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for setting the Instance Name.
     * @param name Instance Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method responsible for returning the Instance Product.
     * @return Instance Product.
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Method responsible for setting the Instance Product.
     * @param product Instance Product.
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    /**
     * Method responsible for returning the Instance Diagram.
     * @return Instance Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }

    /**
     * Method responsible for setting the Instance Diagram.
     * @param diagram Instance Diagram.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for returning the Instance Artifacts.
     * @return Instance Artifacts.
     */
    public HashMap<String, Artifact> getArtifacts() {
        return this.artifacts;
    }
    
    /**
     * Method responsible for returning the Artifacts List.
     * @return Artifacts List.
     */
    public List<Artifact> getArtifactsList() {
        return new ArrayList<>(this.artifacts.values());
    }
    
    /**
     * Method responsible for returning if Instance is Empty.
     * @return Instance is Empty.
     */
    public boolean isEmpty() {
        return this.getArtifactsList().isEmpty();
    }
    
    /**
     * Method responsible for returning the Next Artifact Id.
     * @return Next Artifact Id.
     */
    public String nextArtefactId() {
        Integer index  = 1;
        String  nextId = "ARTIFACT#" + index;
        while (this.artifacts.get(nextId) != null) {
            index += 1;
            nextId = "ARTIFACT#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Artifact.
     * @param artifact Artifact.
     */
    public void addArtifact(Artifact artifact) {
        artifact.setId(this.nextArtefactId());
        this.artifacts.put(artifact.getId(), artifact);
    }
    
    /**
     * Method responsible for returning a Artifact by Id.
     * @param  id Artifact Id.
     * @return Artifact found.
     */
    public Artifact getArtifact(String id) {
        return (Artifact) this.artifacts.get(id);
    }
    
    /**
     * Method responsible for returning the Artifact by Element.
     * @param  element Element.
     * @return Artifact found.
     */
    public Artifact getArtifact(Element element) {
        for (Artifact  artifact : this.getArtifactsList()) {
            if (artifact.getElement().equals(element))
                return artifact;
        }
        return null;
    }
    
    /**
     * Method responsible for returning if the Instance contains a Element.
     * @param  element Element.
     * @return Instance contains Element.
     */
    public boolean contains(Element element) {
        for (Artifact artefact : this.getArtifactsList()) {
            if (artefact.getElement().equals(element))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for removing a Artifact.
     * @param  artifact Artifact.
     */
    public void removeArtifact(Artifact artifact) {
        this.removeAssociations(artifact);
        this.artifacts.remove(artifact.getId());
    }
    
    /**
     * Method responsible for reseting the Instance.
     */
    public void reset() {
        this.artifacts    = new HashMap();
        this.associations = new HashMap();
    }
    
    /**
     * Method responsible for removing a Artifact by Element.
     * @param element Element.
     */
    public void remove(Element element) {
        for (Artifact artifact : this.getArtifactsList()) {
            if (artifact.getElement().equals(element))
                this.removeArtifact(artifact);
        }
    }
    
    /**
     * Method responsible for setting the Instance Artifacts.
     * @param artifacts Instance Artifacts.
     */
    public void setArtifacts(HashMap<String, Artifact> artifacts) {
        this.artifacts = (HashMap) artifacts.clone();
    }
    
    /**
     * Method responsible for returning the Instance Associations.
     * @return Instance Associations.
     */
    public HashMap<String, Association> getAssociations() {
        return this.associations;
    }
    
    /**
     * Method responsible for returning the Associations List.
     * @return Associations List.
     */
    public List<Association> getAssociationsList() {
        return new ArrayList<>(this.associations.values());
    }
    
    /**
     * Method responsible for removing the Association by Artifact.
     * @param artefact Artifact.
     */
    public void removeAssociations(Artifact artefact) {
        for (Association association : this.getAssociationsList()) {
            if (association.contains(artefact.getElement()))
                this.associations.remove(association.getId());
        }
    }
    
    /**
     * Method responsible for removing a Association.
     * @param  association Association.
     */
    public void remove(Association association) {
        this.associations.remove(association.getId());
    }
    
    /**
     * Method responsible for setting the Artifact Associations.
     * @param associations Artifact Associations.
     */
    public void setAssociations(HashMap<String, Association> associations) {
        this.associations = (HashMap) associations.clone();
    }
    
    /**
     * Method responsible for updating the Artifact Artifacts and Associations.
     */
    public void update() {
        this.addAssociations();
        this.removeAssociations();
    }
    
    /**
     * Method responsible for adding the Instance Associations.
     */
    public void addAssociations() {
        for (Association association : this.diagram.getAssociationsList()) {
            if ((this.contains(association.getSource()))
             && (this.contains(association.getTarget())))
                    this.associations.put(association.getId(), association);
        }
    }
    
    /**
     * Method responsible for removing the Mutex and Requires Associations.
     */
    private void removeAssociations() {
        for (Association association : this.getAssociationsList()) {
            if (association instanceof Requires
             || association instanceof Mutex)
                this.associations.remove(association.getId());
        }
    }
    
    /**
     * Method responsible for returning the Abstract.
     * @return Abstract.
     */
    public String getAbstract() {
        return this.name + " (" + this.diagram.getType() + ")";
    }
    
    /**
     * Method responsible for returning the Instance Icon.
     * @return Instance Icon.
     */
    public String getIcon() {
        return "src/images/icons/product/instance.png";
    }
        
    /**
     * Method responsible for exporting the Artifacts.
     * @return Artifacts.
     */
    private String exportArtifacts() {
        String export  = "";
        for (Artifact artifact : this.getArtifactsList())
               export += artifact.export();
        return export;
    }
    
    /**
     * Method responsible for exporting the Associations.
     * @return Associations.
     */
    private String exportAssociations() {
        String export  = "";
        for (Association association : this.getAssociationsList())
               export += "        <association id=\"" + association.getId() + "\"/>\n";
        return export;
    }
        
    @Override
    public String export() {
        String export  = "      <instance id=\"" + this.id + "\" name=\"" + this.name + "\"" + " diagram=\"" + this.diagram.getId() + "\">\n";
               export += this.exportArtifacts();
               export += this.exportAssociations();
               export += "      </instance>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}