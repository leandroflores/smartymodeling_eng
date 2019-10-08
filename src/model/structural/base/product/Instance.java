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
    private HashMap artefacts;
    private HashMap associations;
    
    /**
     * Default constructor method of Class.
     */
    public Instance() {
        this.product      = null;
        this.diagram      = null;
        this.artefacts    = new HashMap<>();
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
     * Method responsible for setting the Instance Id.
     * @param id Instance Id.
     */
    public void setId(String id) {
        this.id = id;
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
     * Method responsible for returning the Instance Artefacts.
     * @return Instance Artefacts.
     */
    public HashMap<String, Artefact> getArtefacts() {
        return this.artefacts;
    }
    
    /**
     * Method responsible for returning the Artefacts List.
     * @return Artefacts List.
     */
    public List<Artefact> getArtefactsList() {
        return new ArrayList<>(this.artefacts.values());
    }
    
    /**
     * Method responsible for returning if Instance is Empty.
     * @return Instance is Empty.
     */
    public boolean isEmpty() {
        return this.getArtefactsList().isEmpty();
    }
    
    /**
     * Method responsible for returning the Next Artefact Id.
     * @return Next Artefact Id.
     */
    public String nextArtefactId() {
        Integer index  = 1;
        String  nextId = "ARTEFACT#" + index;
        while (this.artefacts.get(nextId) != null) {
            index += 1;
            nextId = "ARTEFACT#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Artefact.
     * @param artefact Artefact.
     */
    public void addArtefact(Artefact artefact) {
        artefact.setId(this.nextArtefactId());
        this.artefacts.put(artefact.getId(), artefact);
    }
    
    /**
     * Method responsible for returning a Artefact by Id.
     * @param  id Artefact Id.
     * @return Artefact found.
     */
    public Artefact getArtefact(String id) {
        return (Artefact) this.artefacts.get(id);
    }
    
    /**
     * Method responsible for returning the Artefact by Element.
     * @param  element Element.
     * @return Artefact found.
     */
    public Artefact getArtefact(Element element) {
        for (Artefact  artefact : this.getArtefactsList()) {
            if (artefact.getElement().equals(element))
                return artefact;
        }
        return null;
    }
    
    /**
     * Method responsible for returning if the Instance contains a Element.
     * @param  element Element.
     * @return Instance contains Element.
     */
    public boolean contains(Element element) {
        for (Artefact artefact : this.getArtefactsList()) {
            if (artefact.getElement().equals(element))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for removing a Artefact.
     * @param  artefact Artefact.
     */
    public void removeArtefact(Artefact artefact) {
        this.removeAssociations(artefact);
        this.artefacts.remove(artefact.getId());
    }
    
    /**
     * Method responsible for removing a Artefact by Element.
     * @param element Element.
     */
    public void remove(Element element) {
        for (Artefact artefact : this.getArtefactsList()) {
            if (artefact.getElement().equals(element))
                this.removeArtefact(artefact);
        }
    }
    
    /**
     * Method responsible for setting the Instance Artefacts.
     * @param artefacts Instance Artefacts.
     */
    public void setArtefacts(HashMap<String, Artefact> artefacts) {
        this.artefacts = (HashMap) artefacts.clone();
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
     * Method responsible for removing the Association by Artefact.
     * @param artefact Artefact.
     */
    public void removeAssociations(Artefact artefact) {
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
     * Method responsible for setting the Artefact Associations.
     * @param associations Artefact Associations.
     */
    public void setAssociations(HashMap<String, Association> associations) {
        this.associations = (HashMap) associations.clone();
    }
    
    /**
     * Method responsible for updating the Artefact Artifacts and Associations.
     */
    public void update() {
//        this.updateArtifacts();
        this.removeAssociations();
        this.updateAssociations();
    }
    
    /**
     * Method responsible for updating the Artifacts.
     */
    private void updateArtifacts() {
//        for (Artefact artifact : this.getElementsList()) {
//            if (this.getIdentifiers().get(artifact.getId()) <= 0)
//                this.artifacts.removeArtefact(artifact.getId());
//        }
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
     * Method responsible for updating the Associations.
     */
    private void updateAssociations() {
        for (Association association : this.getAssociationsList()) {
            if ((this.artefacts.get(association.getSource().getId()) == null)
             || (this.artefacts.get(association.getTarget().getId()) == null))
                this.associations.remove(association.getId());
        }
    }
        
    /**
     * Method responsible for exporting the Artifacts.
     * @return Artifacts.
     */
    private String exportArtifacts() {
        String export  = "";
        for (Artefact artifact : this.getArtefactsList())
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