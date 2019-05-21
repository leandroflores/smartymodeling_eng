package model.structural;

import funct.FunctString;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import model.structural.diagram.classs.TypeUML;
import model.structural.variability.Mutex;
import model.structural.variability.Requires;
import model.structural.variability.Variability;

/**
 * <p>Class of Model <b>Diagram</b>.</p>
 * <p>Class responsible for representing <b>Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.Exportable
 */
public abstract class Diagram implements Exportable {
    protected Project project;
    protected String  id;
    protected String  name;
    protected String  type;
    protected HashMap elements;
    protected HashMap associations;
    protected HashMap variabilities;
    
    /**
     * Default constructor method of Class.
     */
    public Diagram() {
        this.elements      = new HashMap<>();
        this.associations  = new HashMap<>();
        this.variabilities = new HashMap<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     */
    public Diagram(Project project) {
        this();
        this.project = project;
        this.id      = "";
        this.name    = "Diagram";
        this.type    = "";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public Diagram(Project project, org.w3c.dom.Element element) {
        this();
        this.project = project;
        this.id      = element.getAttribute("id");
        this.name    = element.getAttribute("name");
        this.type    = element.getAttribute("type");
    }
    
    /**
     * Method responsible for initializing HashMaps.
     */
    public abstract void init();
    
    /**
     * Method responsible for returning Diagram Icon Path.
     * @return Diagram Icon Path.
     */
    public abstract String getIcon();
    
    /**
     * Method responsible for returning Diagram Clone.
     * @return Diagram Clone.
     */
    public abstract Diagram getClone();

    /**
     * Method responsible for returning Diagram Project.
     * @return Diagram Project.
     */
    public Project getProject() {
        return this.project;
    }

    /**
     * Method responsible for defining Diagram Project.
     * @param project Diagram Project.
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning Diagram Id.
     * @return Diagram Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining Diagram Id.
     * @param id Diagram Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning Diagram Name.
     * @return Diagram Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining Diagram Name.
     * @param name Diagram Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning Diagram Type.
     * @return Diagram Type.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Method responsible for defining Diagram Type.
     * @param type Diagram Type.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     *  Method responsible for returing Next Id.
     * @param  label Object Label.
     * @return Next Id.
     */
    public String nextId(String label) {
        return this.project.nextId(label);
    }
    
    /**
     * Method responsible for returning Elements HashMap.
     * @return Elements HashMap.
     */
    public HashMap<String, Element> getElements() {
        return this.elements;
    }
    
    /**
     * Method responsible for returning Elements List.
     * @return Elements List.
     */
    public List<Element> getElementsList() {
        ArrayList<Element> list = new ArrayList<>(this.elements.values());
                           list.sort(this.getElementComparator());
        return             list;
    }
    
    /**
     * Method responsible for returning Elements by Mandatory Flag.
     * @param  mandatory Mandatory Flag.
     * @return Elements by Mandatory Flag.
     */
    public List<Element> filterElements(boolean mandatory) {
        List<Element> list   = this.getElementsList();
        List<Element> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isMandatory() == mandatory)
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning Optional Elements.
     * @return Optional Elements.
     */
    public List<Element> filterOptionalElements() {
        List<Element> list   = this.getElementsList();
        List<Element> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (this.filterVariants(list.get(i), "").isEmpty())
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning Element Comparator.
     * @return Element Comparator.
     */
    private Comparator<Element> getElementComparator() {
        return new Comparator<Element>() {
            @Override
            public int compare(Element element1, Element element2) {
                if (element1.getType().compareTo(element2.getType()) == 0)
                     return element1.getName().compareTo(element2.getName());
                return element1.getType().compareTo(element2.getType());
            }
        };
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     */
    public void addElement(Element element) {
        this.elements.put(element.getId(), element);
        this.project.objects.put(element.getId(), element);
    }
    
    /**
     * Method responsible for updating Element Name.
     * @param element Element.
     * @param name Element Name.
     */
    public void updateElement(Element element, String name) {
        String nameChecked = name.replaceAll(this.getProject().getPerfil().getIdentificadorObrigatorio()     + "\n", "")
                                 .replaceAll(this.getProject().getPerfil().getIdentificadorOpcional()        + "\n", "")
                                 .replaceAll(this.getProject().getPerfil().getIdentificadorPontoDeVariacao() + "\n", "")
                                 .replaceAll(this.getProject().getPerfil().getIdentificadorInclusivo()       + "\n", "")
                                 .replaceAll(this.getProject().getPerfil().getIdentificadorExclusivo()       + "\n", "")
                                 .replaceAll(this.getProject().getPerfil().getIdentificadorMutex()           + "\n", "")
                                 .replaceAll(this.getProject().getPerfil().getIdentificadorRequires()        + "\n", "")
                                 .replaceAll(">", "").replaceAll("<", "");
        element.setName(nameChecked);
    }
    
    /**
     * Method responsible for returning a Element by Id.
     * @param  id Element Id.
     * @return Element found.
     */
    public Element getElement(String id) {
        return (Element) this.elements.get(id);
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    public void removeElement(Element element) {
        this.removeAssociation(element);
        this.removeVariability(element);
        this.project.objects.remove(element.getId());
        this.elements.remove(element.getId());
    }
    
    /**
     * Method responsible for defining Elements List.
     * @param elements Elements List.
     */
    public void setElements(HashMap<String, Element> elements) {
        this.elements = elements;
    }
    
    /**
     * Method responsible for returning a Associations List By Class.
     * @param  classs Association Class.
     * @return Associations List.
     */
    private List filterAssociations(Class classs) {
        List    list = new ArrayList();
        for (Association association : this.getAssociationsList()) {
            if (association.getClass().equals(classs))
                list.add(association);
        }
        return  list;
    }
    
    /**
     * Method responsible for returning a Associations List By Element and Class.
     * @param  element Element.
     * @param  classs Association Class.
     * @return Associations List.
     */
    public List<Association> filterAssociations(Element element, Class classs) {
        List<Association> list   = this.getAssociationsList();
        List<Association> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getClass().equals(classs)
            &&  list.get(i).contains(element))
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning Associations HashMap.
     * @return Associations HashMap.
     */
    public HashMap<String, Association> getAssociations() {
        return this.associations;
    }
    
    /**
     * Method responsible for returning Associations List.
     * @return Associations List.
     */
    public List<Association> getAssociationsList() {
        ArrayList<Association> list = new ArrayList<>(this.associations.values());
                               list.sort(this.getAssociationComparator());
        return                 list;
    }
    
    /**
     * Method responsible for returning Association Comparator.
     * @return Association Comparator.
     */
    private Comparator<Association> getAssociationComparator() {
        return new Comparator<Association>() {
            @Override
            public int compare(Association association1, Association association2) {
                if (association1.getType().compareTo(association2.getType()) == 0)
                     return association1.getId().compareTo(association2.getId());
                return association1.getType().compareTo(association2.getType());
            }
        };
    }
    
    /**
     * Method responsible for adding a Requires.
     * @param requires Requires.
     */
    public void addRequires(Requires requires) {
        if (this.associations.containsKey(requires.getId()) == false)
            this.addAssociation(requires);
    }
    
    /**
     * Method responsible for removing a Requires.
     * @param requires Requires.
     */
    public void removeRequires(Requires requires) {
        this.removeAssociation(requires);
    }
    
    /**
     * Method responsible for returning Requires List.
     * @return Requires List.
     */
    public List<Requires> getRequiresList() {
        return (List<Requires>) this.filterAssociations(Requires.class);
    }
    
    /**
     * Method responsible for returning Requires List by Element.
     * @param  element Element.
     * @return Requires List.
     */
    public List<Association> getRequiresList(Element element) {
        return this.filterAssociations(element, Requires.class);
    }
    
    /**
     * Method responsible for adding a Mutex.
     * @param mutex Mutex.
     */
    public void addMutex(Mutex mutex) {
        if (this.associations.containsKey(mutex.getId()) == false)
            this.addAssociation(mutex);
    }
    
    /**
     * Method responsible for removing a Mutex.
     * @param mutex Mutex.
     */
    public void removeMutex(Mutex mutex) {
        this.removeAssociation(mutex);
    }
    
    /**
     * Method responsible for returning Mutex List.
     * @return Mutex List.
     */
    public List<Mutex> getMutexList() {
        return (List<Mutex>) this.filterAssociations(Requires.class);
    }
    
    /**
     * Method responsible for returning Mutex List by Element.
     * @param  element Element.
     * @return Mutex List.
     */
    public List<Association> getMutexList(Element element) {
        return this.filterAssociations(element, Mutex.class);
    }
    
    /**
     * Method responsible for adding a Inheritance.
     * @param inheritance Inheritance.
     */
    public void addInheritance(Inheritance inheritance) {
        if (this.associations.containsKey(inheritance.getId()) == false)
            this.addAssociation(inheritance);
    }
    
    /**
     * Method responsible for removing a Inheritance.
     * @param inheritance Inheritance.
     */
    public void removeInheritance(Inheritance inheritance) {
        this.removeAssociation(inheritance);
    }
    
    /**
     * Method responsible for returning Inheritance List.
     * @return Inheritance List.
     */
    public List<Inheritance> getInheritanceList() {
        return (List<Inheritance>) this.filterAssociations(Inheritance.class);
    }
    
    /**
     * Method responsible for adding a Association.
     * @param association Association.
     */
    public void addAssociation(Association association) {
        this.associations.put(association.getId(), association);
        this.project.objects.put(association.getId(), association);
    }
    
    /**
     * Method responsible for returning a Association by Id.
     * @param  id Association Id.
     * @return Association found.
     */
    public Association getAssociation(String id) {
        return (Association) this.associations.get(id);
    }
    
    /**
     * Method responsible for removing a Association.
     * @param association Association.
     */
    public void removeAssociation(Association association) {
        this.project.objects.remove(association.getId());
        this.associations.remove(association.getId());
    }
    
    /**
     * Method responsible for removing a Association by Element.
     * @param element Element.
     */
    private void removeAssociation(Element element) {
        for (Association association : this.getAssociationsList()) {
            if (association.contains(element))
                this.removeAssociation(association);
        }
    }
    
    /**
     * Method responsible for defining Associations List.
     * @param associations Associations List.
     */
    public void setAssociations(HashMap<String, Association> associations) {
        this.associations = associations;
    }
    
    /**
     * Method responsible for returning Variabilities HashMap.
     * @return Variabilities HashMap.
     */
    public HashMap<String, Variability> getVariabilities() {
        return this.variabilities;
    }
    
    /**
     * Method responsible for returning Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> getVariabilitiesList() {
        ArrayList<Variability> list = new ArrayList<>(this.variabilities.values());
                               list.sort(this.getVariabilityComparator());
        return                 list;
    }
    
    /**
     * Method responsible for returning Variability Comparator.
     * @return Variability Comparator.
     */
    private Comparator<Variability> getVariabilityComparator() {
        return new Comparator<Variability>() {
            @Override
            public int compare(Variability variability1, Variability variability2) {
                return variability1.getName().compareTo(variability2.getName());
            }
        };
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param variability Variability.
     */
    public void addVariability(Variability variability) {
        variability.setId(this.project.nextVariabilityId());
        if (this.variabilities.get(variability.getId()) == null) {
            this.variabilities.put(variability.getId(), variability);
            this.project.variabilities.put(variability.getId(), variability);
        }
    }
    
    /**
     * Method responsible for returning a Variability by Id.
     * @param  id Variability Id.
     * @return Variability found.
     */
    public Variability getVariability(String id) {
        return (Variability) this.variabilities.get(id);
    }
    
    /**
     * Method responsible for removing a Variability.
     * @param variability Variability.
     */
    public void removeVariability(Variability variability) {
        this.project.variabilities.remove(variability.getId());
        this.variabilities.remove(variability.getId());
    }
    
    /**
     * Method responsible for removing a Variability by Element.
     * @param element Element.
     */
    private void removeVariability(Element element) {
        this.removeVariationPoint(element);
        this.removeVariants(element);
    }
    
    /**
     * Method responsible for removing Variabilities by Variation Point.
     * @param element Variation Point.
     */
    private void removeVariationPoint(Element element) {
        for (Variability variabilidade : this.getVariationPoints(element))
            this.removeVariability(variabilidade);
    }
    
    /**
     * Method responsible for removing Variants by Element.
     * @param element Element.
     */
    private void removeVariants(Element element) {
        for (Variability variabilidade : this.filterVariants(element, "")) {
            variabilidade.removeVariant(element);
            if (variabilidade.emptyVariants())
                this.removeVariability(variabilidade);
        }
    }
    
    /**
     * Method responsible for defining Variabilities HashMap.
     * @param variabilities Variabilities HashMap.
     */
    public void setVariabilities(HashMap<String, Variability> variabilities) {
        this.variabilities = variabilities;
    }
    
    /**
     * Method responsible for returning Object Type.
     * @return Object Type.
     */
    public TypeUML getObjectType() {
        return this.project.getObjectType();
    }
    
    /**
     * Method responsible for returning Void Type.
     * @return Void Type.
     */
    public TypeUML getVoidType() {
        return this.project.getVoidType();
    }
    
    /**
     * Method responsible for returning Stereotypes by Element.
     * @param  element Element.
     * @return Stereotypes found.
     */
    public String getStereotypesByElement(Element element) {
        String stereotype  = this.getOptionalStereotype(element)
                           + this.getVariationPointStereotype(element);
        String inclusive   = this.getInclusiveStereotype(element);
        String exclusive   = this.getExclusiveStereotype(element);
        String toReturn    = "";
        if (!inclusive.equals("") || !exclusive.equals(""))
               toReturn   += stereotype.replaceAll(this.getProject().getPerfil().getIdentificadorObrigatorio() + "\n", "");
        else
               toReturn   += stereotype;
        return toReturn   + inclusive + exclusive;
    }
    
    /**
     * Method responsible for returning Stereotypes List by Element.
     * @param  element Element.
     * @return Stereotypes List
     */
    public List<String> getStereotypesList(Element element) {
        return new FunctString().stringToList(this.getStereotypesByElement(element));
    }
    
    /**
     * Method responsible for returning Optional Stereotype by Element.
     * @param  element Element.
     * @return Optional Stereotype.
     */
    public String getOptionalStereotype(Element element) {
        if (element.isMandatory())
            return this.getProject().getPerfil().getIdentificadorObrigatorio() + "\n";
        return this.getProject().getPerfil().getIdentificadorOpcional() + "\n";
    }
    
    /**
     * Method responsible for returning Variation Point Steretype by Element.
     * @param  element Element.
     * @return Variation Point Steretype.
     */
    public String getVariationPointStereotype(Element element) {
        return this.getVariationPoints(element).isEmpty() ? "" : this.getProject().getPerfil().getIdentificadorPontoDeVariacao() + "\n";
    }
    
    /**
     * Method responsible for returning Variation Points by Element.
     * @param  element Element.
     * @return Variation Points.
     */
    public List<Variability> getVariationPoints(Element element) {
        List<Variability> list   = this.getVariabilitiesList();
        List<Variability> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getVariationPoint().equals(element))
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning Variants List by Element and Constraint.
     * @param  element Element.
     * @param  constraint Variability Constraint.
     * @return Variants List.
     */
    public List<Variability> filterVariants(Element element, String constraint) {
        List<Variability> list   = this.getVariabilitiesList();
        List<Variability> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getConstraint().toLowerCase().contains(constraint)
            &&  list.get(i).isVariant(element))
                filter.add(list.get(i));
        }
        return filter;
    }
    
    /**
     * Method responsible for returning Inclusive Stereotype by Element.
     * @param  element Element.
     * @return Inclusive Stereotype.
     */
    public String getInclusiveStereotype(Element element) {
        return this.filterVariants(element, "inclusive").isEmpty() ? "" : this.getProject().getPerfil().getIdentificadorInclusivo() + "\n";
    }
    
    /**
     * Method responsible for returning Exclusive Stereotype by Element.
     * @param  element Element.
     * @return Exclusive Stereotype.
     */
    public String getExclusiveStereotype(Element element) {
        return this.filterVariants(element, "exclusive").isEmpty() ? "" : this.getProject().getPerfil().getIdentificadorExclusivo() + "\n";
    }
    
    /**
     * Method responsible for returning Elements to export.
     * @return Elements to export.
     */
    private String exportElements() {
        String export  = "";
        for (Element element : this.getElementsList())
               export += element.export();
        return export;
    }
    
    /**
     * Method responsible for returning Associations to export.
     * @return Associations to export.
     */
    private String exportAssociations() {
        String export  = "";
        for (Association association : this.getAssociationsList())
               export += association.export();
        return export;
    }
    
    /**
     * Method responsible for returning Variabilities to export.
     * @return Variabilities to export.
     */
    private String exportVariabilities() {
        String export  = "";
        for (Variability variability : this.getVariabilitiesList())
               export += variability.export();
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <diagram id=\"" + this.id + "\" name=\"" + this.name + "\" type=\"" + this.type + "\">\n";
               export += this.exportElements();
               export += this.exportAssociations();
               export += this.exportVariabilities();
               export += "  </diagram>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name + "(" + this.type + ")";
    }
}