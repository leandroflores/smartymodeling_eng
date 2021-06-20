package model.structural.base;

import model.structural.base.association.Association;
import funct.FunctDate;
import funct.FunctString;
import funct.evaluation.base.EvaluationProject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import model.comparator.structural.base.ComparatorDiagram;
import model.comparator.structural.base.ComparatorElement;
import model.comparator.structural.base.ComparatorStereotype;
import model.comparator.structural.base.association.ComparatorLink;
import model.comparator.structural.diagram.classes.base.ComparatorTypeUML;
import model.structural.base.association.Link;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import model.structural.base.interfaces.Exportable;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Traceability;
import model.structural.diagram.classes.base.TypeUML;
import model.structural.base.variability.Variability;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.usecase.base.ActorUML;

/**
 * <p>Class of Model <b>Project</b>.</p>
 * <p>Class responsible for representing <b>Project</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.interfaces.Exportable
 */
public class Project implements Exportable {
    private String  id;
    private String  name;
    private String  path;
    private String  version;
    private Profile profile;
    private HashMap diagrams;
    public  HashMap types;
    public  HashMap variabilities;
    private HashMap requirements;
    public  HashMap traceabilities;
    public  HashMap metrics;
    public  HashMap measures;
    public  HashMap products;
    public  HashMap stereotypes;
    public  HashMap links;
    public  HashMap objects;
    
    /**
     * Default constructor method of Class.
     */
    public Project() {
        id      = new FunctString().md5(new FunctDate().getFormattedDate(new Date()));
        name    = "Project0";
        path    = "New_Project.smty";
        version = "1.0";
        init();
        loadDefaultTypes();
        loadSMartyStereotypes();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param path Path Project.
     * @param element W3C Element.
     */
    public Project(String path, org.w3c.dom.Element element) {
        this.id      = element.getAttribute("id");
        this.name    = element.getAttribute("name");
        this.path    = path;
        this.version = element.getAttribute("version");
        init();
    }
    
    /**
     * Method responsible for initializing the HashMaps.
     */
    private void init() {
        diagrams       = new LinkedHashMap();
        types          = new LinkedHashMap();
        variabilities  = new LinkedHashMap();
        requirements   = new LinkedHashMap();
        traceabilities = new LinkedHashMap();
        metrics        = new LinkedHashMap();
        measures       = new LinkedHashMap();
        products       = new LinkedHashMap();
        stereotypes    = new LinkedHashMap();
        links          = new LinkedHashMap();
        objects        = new LinkedHashMap();
        profile        = getDefaultProfile();
    }
    
    /**
     * Method responsible for returning Default Profile.
     * @return Default Profile.
     */
    private Profile getDefaultProfile() {
        loadStereotypes();
        Profile defaultProfile = new Profile();
                defaultProfile.setMandatory((Stereotype) stereotypes.get("STEREOTYPE#1"));
                defaultProfile.setOptional((Stereotype) stereotypes.get("STEREOTYPE#2"));
                defaultProfile.setVariationPoint((Stereotype) stereotypes.get("STEREOTYPE#3"));
                defaultProfile.setInclusive((Stereotype) stereotypes.get("STEREOTYPE#4"));
                defaultProfile.setExclusive((Stereotype) stereotypes.get("STEREOTYPE#5"));
                defaultProfile.setRequires((Stereotype) stereotypes.get("STEREOTYPE#6"));
                defaultProfile.setMutex((Stereotype) stereotypes.get("STEREOTYPE#7"));
        return  defaultProfile;
    }
    
    /**
     * Method responsible for loading Stereotypes.
     */
    private void loadStereotypes() {
        if (stereotypes.isEmpty())
            loadSMartyStereotypes();
    }
    
    /**
     * Method responsible for returning the Next Id.
     * @param  map Map.
     * @param  label Label Identifier.
     * @return Next Id.
     */
    private String nextId(HashMap map, String label) {
        Integer index  = map.size() + 1;
        String  nextId = label + index;
        while (map.get(nextId) != null)
                nextId = label + ++index;
        return  nextId;
    }
    
    /**
     * Method responsible for returning the Next Id.
     * @param  label Object Label.
     * @return Next Id.
     */
    public String nextId(String label) {
        return nextId(objects, label);
    }
    
    /**
     * Method responsible for returning the Project Id.
     * @return Project Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Project Id.
     * @param id Project Id.
     */
    public void setId(String id) {
        this.id = (this.id == null || this.id.trim().equals("")) ? id : this.id;
    }

    /**
     * Method responsible for returning the Project Name.
     * @return Project Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for defining the Project Name.
     * @param name Project Name.
     */
    public void setName(String name) {
        String string = new FunctString().getString(name);
        this.name     = string.isEmpty() ? this.name : string;
    }

    /**
     * Method responsible for returning the Project Path.
     * @return Project Path.
     */
    public String getPath() {
        return path;
    }

    /**
     * Method responsible for defining the Project Path.
     * @param path Project Path.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Method responsible for returning the Project Version.
     * @return Project Version.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Method responsible for defining the Project Version.
     * @param version Project Version.
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * Method responsible for returning the Profile Project.
     * @return Profile Project.
     */
    public Profile getProfile() {
        return profile;
    }
    
    /**
     * Method responsible for setting the Profile Project.
     * @param profile Profile Project.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    public List<Element> getElementsList() {
        List   list = new ArrayList<>();
        for (Object object : objects.values()) {
            if (object instanceof Element)
               list.add((Element) object);
        }
        return list;
    }
    
    /**
     * Method responsible for returning a Element by Id.
     * @param  id Element Id.
     * @return Element found.
     */
    public Element getElement(String id) {
        return (Element) objects.get(id);
    }
    
    /**
     * Method responsible for returning the Default Elements.
     * @return Default Elements.
     */
    public List<Element> getDefaultElements() {
        List   list = new ArrayList<>();
        for (Object object : objects.values()) {
            if (object instanceof Element && ((Element) object).isDefault())
               list.add((Element) object);
        }
               list.sort(new ComparatorElement());
        return list;
    }
    
    /**
     * Method responsible for returning the Associations List.
     * @return Associations List.
     */
    public List<Association> getAssociationsList() {
        List   list = new ArrayList<>();
        for (Object object : objects.values()) {
            if (object instanceof Association)
               list.add((Association) object);
        }
        return list;
    }
    
    /**
     * Method responsible for returning the Diagrams HashMap.
     * @return Diagrams HashMap.
     */
    public HashMap getDiagrams() {
        return diagrams;
    }
    
    /**
     * Method responsible for returning the Feature Diagrams List.
     * @return Feature Diagrams List.
     */
    public List<Diagram> getFeatureDiagramsList() {
        List   list = getDiagrams("Feature");
               list.sort(new ComparatorDiagram());
        return list;
    }
    
    /**
     * Method responsible for returning the UML Diagrams List.
     * @return UML Diagrams List.
     */
    public List<Diagram> getUMLDiagramsList() {
        List   list = new ArrayList<>(diagrams.values());
               list.sort(new ComparatorDiagram());
               list.removeAll(getFeatureDiagramsList());
        return list;
    }
    
    /**
     * Method responsible for returning the Diagrams with Variability.
     * @return Diagrams with Variability List.
     */
    public List<Diagram> getVariabilityDiagramsList() {
        List   list = new ArrayList<>();
        for (Diagram diagram : getUMLDiagramsList()) {
            if (!diagram.getVariabilities().isEmpty())
               list.add(diagram);
        }
        return list;
    }
    
    /**
     * Method responsible for returning the Diagrams List.
     * @return Diagrams List.
     */
    public List<Diagram> getDiagramsList() {
        List   list = new ArrayList<>(diagrams.values());
               list.sort(new ComparatorDiagram());
        return list;
    }
    
    /**
     * Method responsible for updating the Stereotypes.
     */
    public void updateStereotypes() {
        for (Diagram diagram : getDiagramsList())
            diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for returning the Next Diagram Id.
     * @return Next Diagram Id.
     */
    public String nextDiagramId() {
        return nextId(diagrams, "DIAGRAM#");
    }
    
    /**
     * Method responsible for adding a Diagram.
     * @param diagram Diagram.
     */
    public void addDiagram(Diagram diagram) {
        diagram.setId(nextDiagramId());
        if (diagrams.get(diagram.getId()) == null)
            diagrams.put(diagram.getId(), diagram);
    }
    
    /**
     * Method responsible for removing the Elements of a Diagram.
     * @param diagram Diagram.
     */
    private void removeElements(Diagram diagram) {
        List<Element> list = diagram.getElementsList();
        for (int i =  list.size() - 1; i >= 0; i--)
            diagram.removeElement(list.get(i));
    }
    
    /**
     * Method responsible for removing a Diagram.
     * @param diagram Diagram.
     */
    public void removeDiagram(Diagram diagram) {
        removeElements(diagram);
        removeInstances(diagram);
        diagrams.remove(diagram.getId());
    }
    
    /**
     * Method responsible for exporting the Feature Diagrams.
     * @return Feature Diagrams.
     */
    private String exportFeatureDiagrams() {
        String export  = "";
        for (Diagram diagram : getFeatureDiagramsList())
               export += diagram.export();
        return export;
    }
    
    /**
     * Method responsible for exporting the UML Diagrams.
     * @return UML Diagrams.
     */
    private String exportUMLDiagrams() {
        String export  = "";
        for (Diagram diagram : getUMLDiagramsList())
               export += diagram.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Entity by Name.
     * @param  name Entity Name.
     * @return Entity by Name.
     */
    public Entity getEntityByName(String name) {
        for (Object diagram : getDiagrams("class")) {
            Entity entity = getEntityByName((Diagram) diagram, name);
            if (entity != null)
                return entity;
        }
        return null;
    }
    
    /**
     * Method responsible for returning the Entity by Diagram and Name.
     * @param  diagram Class Diagram.
     * @param  name Entity Name.
     * @return Entity by Diagram and Name.
     */
    public Entity getEntityByName(Diagram diagram, String name) {
        return diagram.filterEntityByName(name);
    }
    
    /**
     * Method responsible for returning the Types HashMap.
     * @return Types HashMap.
     */
    public HashMap getTypes() {
        return types;
    }
    
    /**
     * Method responsible for returning the Types List.
     * @return Types List.
     */
    public List<TypeUML> getTypesList() {
        List   list = new ArrayList<>(types.values());
               list.sort(new ComparatorTypeUML());
        return list;
    }
    
    /**
     * Method responsible for returning the Elements by Type.
     * @param  type Element Type.
     * @return Elements List.
     */
    public List getElements(String type) {
        List list = new ArrayList<>();
        for (Element element : getElementsList()) {
            if (element.getType().equalsIgnoreCase(type))
                list.add(element);
        }
        return list;
    }
    
    /**
     * Method responsible for returning the Associations by Type.
     * @param  type Association Type.
     * @return Associations List.
     */
    public List getAssociations(String type) {
        List list = new ArrayList<>();
        for (Association association : getAssociationsList()) {
            if (association.getType().equalsIgnoreCase(type))
                list.add(association);
        }
        return list;
    }
    
    /**
     * Method responsible for returning the Diagrams by Type.
     * @param  type Diagram Type.
     * @return Diagrams List.
     */
    public List getDiagrams(String type) {
        List list = new ArrayList<>();
        for (Diagram diagram : getDiagramsList()) {
            if (diagram.getType().equalsIgnoreCase(type))
                list.add(diagram);
        }
        return list;
    }
    
    /**
     * Method responsible for returning the Element by Type and Name.
     * @param  type Element Type.
     * @param  name Element Name.
     * @return Element found.
     */
    public Element getByName(String type, String name) {
        for (Object element : getElements(type)) {
            if (((Element) element).getName().equalsIgnoreCase(name))
                return (Element) element;
        }
        return null;
    }
    
    /**
     * Method responsible for returning the Next Type Id.
     * @return Next Type Id.
     */
    public String nextTypeId() {
        return nextId(types, "TYPE#");
    }
    
    /**
     * Method responsible for adding a UML Type.
     * @param type UML Type.
     */
    public void addType(TypeUML type) {
        type.setId(nextTypeId());
        types.put(type.getId(), type);
    }
    
    /**
     * Method responsible for adding a Default Type.
     * @param type UML Default Type.
     */
    public void addDefaultType(TypeUML type) {
        if (type.getId() != null)
            types.put(type.getId(), type);
    }
    
    /**
     * Method responsible for returning the Type by Entity.
     * @param  entity Entity.
     * @return Entity Type.
     */
    public TypeUML getEntityType(Entity entity) {
        return (TypeUML) types.get(entity.getId());
    }
    
    /**
     * Method responsible for adding a Entity Type.
     * @param entity Entity.
     */
    public void addEntityType(Entity entity) {
        TypeUML type = new TypeUML(entity);
                types.put(type.getId(), type);
                entity.setTypeUML(type);
    }
    
    /**
     * Method responsible for removing a UML Type.
     * @param type UML Type.
     */
    public void removeType(TypeUML type) {
        types.remove(type.getId());
    }
    
    /**
     * Method responsible for removing a Entity Type.
     * @param entity Entity 
     */
    public void removeEntityType(Entity entity) {
        TypeUML oldType = (TypeUML) types.get(entity.getId());
        TypeUML newType = getObjectType();
        if (oldType != null) {
            for (Object diagram : getDiagrams("class"))
                ((ClassDiagram) diagram).changeTypeUML(oldType, newType);
            removeType(oldType);
        }
    }
    
    /**
     * Method responsible for loading the Default Types.
     */
    private void loadDefaultTypes() {
        loadPrimitiveTypes();
        loadJavaLangTypes();
        loadJavaUtilTypes();
    }
    
    /**
     * Method responsible for loading the Primitive Types.
     */
    private void loadPrimitiveTypes() {
        addDefaultType(new TypeUML("TYPE#1",  "", "boolean", "false", true));
        addDefaultType(new TypeUML("TYPE#2",  "", "byte",    "'0'",   true));
        addDefaultType(new TypeUML("TYPE#3",  "", "char",    "' '",   true));
        addDefaultType(new TypeUML("TYPE#4",  "", "double",  "0.0d",  true));
        addDefaultType(new TypeUML("TYPE#5",  "", "float",   "0.0f",  true));
        addDefaultType(new TypeUML("TYPE#6",  "", "int",     "0",     true));
        addDefaultType(new TypeUML("TYPE#7",  "", "long",    "0.0l",  true));
        addDefaultType(new TypeUML("TYPE#8",  "", "short",   "0",     true));
        addDefaultType(new TypeUML("TYPE#9",  "", "void",    "",      true));
    }
    
    /**
     * Method responsible for loading the Java Lang Types.
     */
    private void loadJavaLangTypes() {
        addDefaultType(new TypeUML("TYPE#10", "java.lang", "Boolean",       false));
        addDefaultType(new TypeUML("TYPE#11", "java.lang", "Byte",          false));
        addDefaultType(new TypeUML("TYPE#12", "java.lang", "Character",     false));
        addDefaultType(new TypeUML("TYPE#13", "java.lang", "Double",        false));
        addDefaultType(new TypeUML("TYPE#14", "java.lang", "Enum",          false));
        addDefaultType(new TypeUML("TYPE#15", "java.lang", "Exception",     false));
        addDefaultType(new TypeUML("TYPE#16", "java.lang", "Float",         false));
        addDefaultType(new TypeUML("TYPE#17", "java.lang", "Integer",       false));
        addDefaultType(new TypeUML("TYPE#18", "java.lang", "Long",          false));
        addDefaultType(new TypeUML("TYPE#19", "java.lang", "Math",          false));
        addDefaultType(new TypeUML("TYPE#20", "java.lang", "Number",        false));
        addDefaultType(new TypeUML("TYPE#21", "java.lang", "Object",        false));
        addDefaultType(new TypeUML("TYPE#22", "java.lang", "Package",       false));
        addDefaultType(new TypeUML("TYPE#23", "java.lang", "Short",         false));
        addDefaultType(new TypeUML("TYPE#24", "java.lang", "String",        false));
        addDefaultType(new TypeUML("TYPE#25", "java.lang", "StringBuffer",  false));
        addDefaultType(new TypeUML("TYPE#26", "java.lang", "StringBuilder", false));
        addDefaultType(new TypeUML("TYPE#27", "java.lang", "Thread",        false));
        addDefaultType(new TypeUML("TYPE#28", "java.lang", "Void",          false));
    }
    
    /**
     * Method responsible for loading the Java Util Types.
     */
    private void loadJavaUtilTypes() {
        addDefaultType(new TypeUML("TYPE#29", "java.util", "Arrays",        false));
        addDefaultType(new TypeUML("TYPE#30", "java.util", "Collections",   false));
        addDefaultType(new TypeUML("TYPE#31", "java.util", "Date",          false));
        addDefaultType(new TypeUML("TYPE#32", "java.util", "EnumMap",       false));
        addDefaultType(new TypeUML("TYPE#33", "java.util", "EnumSet",       false));
        addDefaultType(new TypeUML("TYPE#34", "java.util", "EventListener", false));
        addDefaultType(new TypeUML("TYPE#35", "java.util", "HashMap",       false));
        addDefaultType(new TypeUML("TYPE#36", "java.util", "HashSet",       false));
        addDefaultType(new TypeUML("TYPE#37", "java.util", "HashTable",     false));
        addDefaultType(new TypeUML("TYPE#38", "java.util", "LinkedHashMap", false));
        addDefaultType(new TypeUML("TYPE#39", "java.util", "LinkedHashSet", false));
        addDefaultType(new TypeUML("TYPE#40", "java.util", "LinkedList",    false));
        addDefaultType(new TypeUML("TYPE#41", "java.util", "List",          false));
        addDefaultType(new TypeUML("TYPE#42", "java.util", "Map",           false));
        addDefaultType(new TypeUML("TYPE#43", "java.util", "Queue",         false));
        addDefaultType(new TypeUML("TYPE#44", "java.util", "Random",        false));
        addDefaultType(new TypeUML("TYPE#45", "java.util", "Scanner",       false));
        addDefaultType(new TypeUML("TYPE#46", "java.util", "Set",           false));
        addDefaultType(new TypeUML("TYPE#47", "java.util", "Stack",         false));
        addDefaultType(new TypeUML("TYPE#48", "java.util", "Timer",         false));
        addDefaultType(new TypeUML("TYPE#49", "java.util", "TreeMap",       false));
        addDefaultType(new TypeUML("TYPE#50", "java.util", "TreeSet",       false));
        addDefaultType(new TypeUML("TYPE#51", "java.util", "Vector",        false));
    }
    
    /**
     * Method responsible for returning the Type by Name.
     * @param  name Type Name.
     * @return Type found.
     */
    public TypeUML getTypeByName(String name) {
        for (TypeUML type : getTypesList()) {
            if (type.getName().equals(name))
                return type;
        }
        return getObjectType();
    }
    
    /**
     * Method responsible for returning the Type by Signature.
     * @param  signature Type Signature.
     * @return Type found.
     */
    public TypeUML getTypeBySignature(String signature) {
        for (TypeUML type : getTypesList()) {
            if (type.getSignature().equals(signature) && !type.isPrimitive())
                return type;
        }
        return getObjectType();
    }
    
    /**
     * Method responsible for returning the Object Type.
     * @return Object Type.
     */
    public TypeUML getObjectType() {
        if (types.isEmpty())
            loadPrimitiveTypes();
        return (TypeUML) types.get("TYPE#21");
    }
    
    /**
     * Method responsible for returning the Void Type.
     * @return Void Type.
     */
    public TypeUML getVoidType() {
        if (types.isEmpty())
            loadPrimitiveTypes();
        return (TypeUML) types.get("TYPE#9");
    }
    
    /**
     * Method responsible for exporting the Types.
     * @return Types.
     */
    private String exportTypes() {
        String export  = "  <types>\n";
        for (TypeUML type : getTypesList())
               export += type.export();
        return export +  "  </types>\n";
    }
    
    /**
     * Method responsible for returning the Variabilities HashMap.
     * @return Variabilities HashMap.
     */
    public HashMap getVariabilities() {
        return variabilities;
    }
    
    /**
     * Method responsible for returning the Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> getVariabilitiesList() {
        return new ArrayList<>(variabilities.values());
    }
    
    /**
     * Method responsible for returning the Next Variability Id.
     * @return Next Variability Id.
     */
    public String nextVariabilityId() {
        return nextId(variabilities, "VARIABILITY#");
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param variability Variability.
     */
    public void addVariability(Variability variability) {
        variability.setId(nextVariabilityId());
        variabilities.put(variability.getId(), variability);
        addVariabilityStereotype(variability);
    }
    
    /**
     * Method responsible for returning the Variant Stereotype.
     * @param  variability Variability.
     * @return Variant Stereotype.
     */
    public Stereotype getVariantStereotype(Variability variability) {
        return  variability.getConstraint().equals("Inclusive") ? 
                profile.getInclusive() : 
                profile.getExclusive();
    }
    
    /**
     * Method responsible for adding the Variability Stereotype.
     * @param variability Variability.
     */
    public void addVariabilityStereotype(Variability variability) {
        addLink(new Link(variability.getVariationPoint(), profile.getVariationPoint()));
        for (Element variant : variability.getVariants())
            addLink(new Link(variant, getVariantStereotype(variability)));
    }
    
    /**
     * Method responsible for removing a Variability.
     * @param variability Variability.
     */
    public void removeVariability(Variability variability) {
        variabilities.remove(variability.getId());
    }
    
    /**
     * Method responsible for returning the Next Requirement Id.
     * @return Next Requirement Id.
     */
    public String nextRequirementId() {
        return nextId(requirements, "REQUIREMENT#");
    }
    
    /**
     * Method responsible for adding a Requirement.
     * @param requirement Requirement.
     */
    public void addRequirement(Requirement requirement) {
        requirement.setId(nextRequirementId());
        requirements.put(requirement.getId(), requirement);
    }
    
    /**
     * Method responsible for returning a Requirement by Id.
     * @param  id Requirement Id.
     * @return Requirement found.
     */
    public Requirement getRequirement(String id) {
        return (Requirement) requirements.get(id);
    }
    
    /**
     * Method responsible for removing a Requirement.
     * @param requirement Requirement.
     */
    public void removeRequirement(Requirement requirement) {
        requirements.remove(requirement.getId());
    }
    
    /**
     * Method responsible for removing a Element of Requirements.
     * @param element Element.
     */
    public void removeRequirement(Element element) {
        for (Requirement requirement : getRequirementsList())
            requirement.removeElement(element);
    }
    
    /**
     * Method responsible for returning Requirements List.
     * @return Requirements List.
     */
    public List<Requirement> getRequirementsList() {
        return new ArrayList<>(requirements.values());
    }
    
    /**
     * Method responsible for exporting the Requirements.
     * @return Requirements.
     */
    private String exportRequirements() {
        String export  = "";
        for (Requirement requirement : getRequirementsList())
               export += requirement.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Next Traceability Id.
     * @return Next Traceability Id.
     */
    public String nextTraceabilityId() {
        return nextId(traceabilities, "TRACEABILITY#");
    }
    
    /**
     * Method responsible for adding a Traceability.
     * @param traceability Traceability.
     */
    public void addTraceability(Traceability traceability) {
        traceability.setId(nextTraceabilityId());
        traceabilities.put(traceability.getId(), traceability);
    }
    
    /**
     * Method responsible for returning a Traceability by Id.
     * @param  id Traceability Id.
     * @return Traceability found.
     */
    public Traceability getTraceability(String id) {
        return (Traceability) traceabilities.get(id);
    }
    
    /**
     * Method responsible for returning the Traceabilities by Element.
     * @param  element Element.
     * @return Traceabilities found.
     */
    public List<Traceability> getTraceabilities(Element element) {
        List   filter = new ArrayList<>();
        for (Traceability traceability : getTraceabilitiesList()) {
            if (traceability.contains(element))
               filter.add(traceability);
        }
        return filter;
    }
    
    /**
     * Method responsible for removing a Traceability.
     * @param traceability Traceability.
     */
    public void removeTraceability(Traceability traceability) {
        traceabilities.remove(traceability.getId());
    }
    
    /**
     * Method responsible for removing a Traceability by Element.
     * @param element Element.
     */
    public void removeTraceability(Element element) {
        for (Traceability traceability : getTraceabilitiesList()) {
            if (traceability.contains(element))
                traceability.removeElement(element);
        }
    }
    
    /**
     * Method responsible for returning Traceabilities List.
     * @return Traceabilities List.
     */
    public List<Traceability> getTraceabilitiesList() {
        return new ArrayList<>(traceabilities.values());
    }
    
    /**
     * Method responsible for exporting the Traceabilities.
     * @return Traceabilities.
     */
    private String exportTraceabilities() {
        String export  = "";
        for (Traceability traceability : getTraceabilitiesList())
               export += traceability.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Next Metric Id.
     * @return Next Metric Id.
     */
    public String nextMetricId() {
        return nextId(metrics, "METRIC#");
    }
    
    /**
     * Method responsible for adding a Metric.
     * @param metric Metric.
     */
    public void addMetric(Metric metric) {
        metric.setId(nextMetricId());
        metrics.put(metric.getId(), metric);
    }
    
    /**
     * Method responsible for returning a Metric by Id.
     * @param  id Metric Id.
     * @return Metric found.
     */
    public Metric getMetric(String id) {
        return (Metric) metrics.get(id);
    }
    
    /**
     * Method responsible for removing a Metric.
     * @param metric Metric.
     */
    public void removeMetric(Metric metric) {
        removeMeasures(metric);
        metrics.remove(metric.getId());
    }
    
    /**
     * Method responsible for returning Metrics List.
     * @return Metrics List.
     */
    public List<Metric> getMetricsList() {
        return new ArrayList<>(metrics.values());
    }
    
    /**
     * Method responsible for exporting the Metrics.
     * @return Metrics.
     */
    private String exportMetrics() {
        String export  = "";
        for (Metric metric : getMetricsList())
               export += metric.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Next Measure Id.
     * @return Next Measure Id.
     */
    public String nextMeasureId() {
        return nextId(measures, "MEASURE#");
    }
    
    /**
     * Method responsible for adding a Measure.
     * @param measure Measure.
     */
    public void addMeasure(Measure measure) {
        measure.setId(nextMeasureId());
        measures.put(measure.getId(), measure);
    }
    
    /**
     * Method responsible for returning a Measure by Id.
     * @param  id Measure Id.
     * @return Measure found.
     */
    public Measure getMeasure(String id) {
        return (Measure) measures.get(id);
    }
    
    /**
     * Method responsible for returning the Measures by Metric.
     * @param  metric Metric.
     * @return Measures by Metric.
     */
    public List<Measure> getMeasuresByMetric(Metric metric) {
        List   filter = new ArrayList<>();
        for (Measure measure : getMeasuresList()) {
           if (measure.getMetric().equals(metric))
               filter.add(measure);
        }
        return filter;
    }
    
    /**
     * Method responsible for removing the Measures by Metric.
     * @param metric Metric.
     */
    public void removeMeasures(Metric metric) {
        for (Measure measure : getMeasuresByMetric(metric))
            removeMeasure(measure);
    }
    
    /**
     * Method responsible for removing a Measure.
     * @param measure Measure.
     */
    public void removeMeasure(Measure measure) {
        measures.remove(measure.getId());
    }
    
    /**
     * Method responsible for returning Measures List.
     * @return Measures List.
     */
    public List<Measure> getMeasuresList() {
        return new ArrayList<>(measures.values());
    }
    
    /**
     * Method responsible for exporting the Measures.
     * @return Measures.
     */
    private String exportMeasures() {
        String export  = "";
        for (Measure measure : getMeasuresList())
               export += measure.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Next Product Id.
     * @return Next Product Id.
     */
    public String nextProductId() {
        return nextId(products, "PRODUCT#");
    }
    
    /**
     * Method responsible for adding a Product.
     * @param product Product.
     */
    public void addProduct(Product product) {
        product.setId(nextProductId());
        products.put(product.getId(), product);
    }
    
    /**
     * Method responsible for returning a Product by Id.
     * @param  id Product Id.
     * @return Product found.
     */
    public Product getProduct(String id) {
        return (Product) products.get(id);
    }
    
    /**
     * Method responsible for removing a Element of a Product.
     * @param element Element.
     */
    public void removeProduct(Element element) {
        for (Product product : getProductsList()) 
            remove(product, element);
    }
    
    /**
     * Method responsible for returning the Instances List.
     * @return Instances List.
     */
    public List<Instance> getInstancesList() {
        List   list = new ArrayList<>();
        for (Product product : getProductsList())
               list.addAll(product.getInstancesList());
        return list;
    }
    
    /**
     * Method responsible for returning the Instances by Diagram Type.
     * @param  type Diagram Type.
     * @return Instances List by Diagram Type.
     */
    public List<Instance> getInstances(String type) {
        List   list = new ArrayList<>();
        for (Product product : getProductsList())
               list.addAll(product.getInstances(type));
        return list;
    }
    
    /**
     * Method responsible for removing the Instances by Diagram.
     * @param diagram Diagram.
     */
    public void removeInstances(Diagram diagram) {
        for (Product product : getProductsList())
            product.remove(diagram);
    }
    
    /**
     * Method responsible for removing a Element of a Product.
     * @param product Product.
     * @param element Element.
     */
    private void remove(Product product, Element element) {
        if (product.contains(element)) {
            product.remove(element);
            if (product.isEmpty())
                removeProduct(product);
        }
    }
    
    /**
     * Method responsible for removing a Association of a Product.
     * @param association Association.
     */
    public void removeProduct(Association association) {
        for (Product product : getProductsList()) 
            product.remove(association);
    }
    
    /**
     * Method responsible for removing the Instances of a Product.
     * @param product Product.
     */
    private void removeInstances(Product product) {
        List<Instance> list = product.getInstancesList();
        for (int  i =  list.size() - 1; i >= 0; i--)
            product.removeInstance(list.get(i));
    }
    
    /**
     * Method responsible for removing a Product.
     * @param product Product.
     */
    public void removeProduct(Product product) {
        removeInstances(product);
        products.remove(product.getId());
    }
    
    /**
     * Method responsible for returning Products List.
     * @return Products List.
     */
    public List<Product> getProductsList() {
        return new ArrayList<>(products.values());
    }
    
    /**
     * Method responsible for exporting the Products.
     * @return Products.
     */
    private String exportProducts() {
        String export  = "  <products>\n";
        for (Product product : getProductsList())
               export += product.export();
        return export +  "  </products>\n";
    }
    
    /**
     * Method responsible for returning the Artifacts List.
     * @return Artifacts List.
     */
    public List<Artifact> getArtifactsList() {
        List   list = new ArrayList<>();
        for (Instance instance : getInstancesList())
               list.addAll(instance.getArtifactsList());
        return list;
    }
    
    /**
     * Method responsible for returning the Stereotypes HashMap.
     * @return Stereotypes HashMap.
     */
    public HashMap getStereotypes() {
        return stereotypes;
    }
    
    /**
     * Method responsible for returning the Stereotipos List.
     * @return Stereotipos List.
     */
    public List<Stereotype> getStereotypesList() {
        ArrayList list = new ArrayList<>(stereotypes.values());
                  list.sort(new ComparatorStereotype());
        return    list;
    }
    
    /**
     * Method responsible for returning the Stereotypes List by Primitive Flag.
     * @param  primitive Primitive Flag.
     * @return Stereotypes List.
     */
    public List<Stereotype> getStereotypesList(boolean primitive) {
        List   filter = new ArrayList<>();
        for (Stereotype stereotype : getStereotypesList()) {
            if (stereotype.isPrimitive() == primitive)
               filter.add((Stereotype) stereotype);
        }
        return filter;
    }
    
    /**
     * Method responsible for returning the Next Stereotype Id.
     * @return Next Stereotype Id.
     */
    public String nextStereotypeId() {
        return nextId(stereotypes, "STEREOTYPE#");
    }
    
    /**
     * Method responsible for adding a Stereotype.
     * @param stereotype Stereotype.
     */
    public void addStereotype(Stereotype stereotype) {
        stereotype.setId(nextStereotypeId());
        stereotypes.put(stereotype.getId(), stereotype);
    }
    
    /**
     * Method responsible for adding a Default Stereotype.
     * @param stereotype Stereotype.
     */
    public void addDefaultStereotype(Stereotype stereotype) {
        if (stereotype.getId() != null)
            stereotypes.put(stereotype.getId(), stereotype);
    }
    
    /**
     * Method responsible for adding the Element Stereotype.
     * @param element Element.
     */
    public void addElementStereotype(Element element) {
        if (element.isDefault() && element.allowStereotype()) {
            Stereotype stereotype = element.isMandatory() ? profile.getMandatory() : profile.getOptional();
            addLink(new Link(element, stereotype));
        }
    }
    
    /**
     * Method responsible for removing a Stereotype.
     * @param stereotype Stereotype.
     */
    public void removeStereotype(Stereotype stereotype) {
        removeLinks(stereotype);
        stereotypes.remove(stereotype.getId());
    }
    
    /**
     * Method responsible for loading the SMarty Stereotypes.
     */
    private void loadSMartyStereotypes() {
        addDefaultStereotype(new Stereotype("STEREOTYPE#1", "mandatory", true));
        addDefaultStereotype(new Stereotype("STEREOTYPE#2", "optional",  true));
        addDefaultStereotype(new Stereotype("STEREOTYPE#3", "variationPoint",  true));
        addDefaultStereotype(new Stereotype("STEREOTYPE#4", "alternative_OR",  true));
        addDefaultStereotype(new Stereotype("STEREOTYPE#5", "alternative_XOR", true));
        addDefaultStereotype(new Stereotype("STEREOTYPE#6", "requires", true));
        addDefaultStereotype(new Stereotype("STEREOTYPE#7", "mutex", true));
        addDefaultStereotype(new Stereotype("STEREOTYPE#8", "stereotype", false));
    }
    
    /**
     * Method responsible for returning the Default Stereotype.
     * @return Default Stereotype.
     */
    public Stereotype getDefaultStereotype() {
        if (stereotypes.isEmpty())
            loadSMartyStereotypes();
        return (Stereotype) stereotypes.get("STEREOTYPE#1");
    }
    
    /**
     * Method responsible for returning a Stereotype by Id.
     * @param  id Stereotype Id.
     * @return Stereotype found.
     */
    public Stereotype getStereotype(String id) {
        return (Stereotype) stereotypes.get(id);
    }
    
    /**
     * Method responsible for returning the Stereotype by Name.
     * @param  name Stereotype Name.
     * @return Stereotype found.
     */
    public Stereotype getStereotypeByName(String name) {
        for (Stereotype stereotype : getStereotypesList()) {
            if (stereotype.getName().equals(name))
                return stereotype;
        }
//        return getDefaultStereotype();
        return null;
    }
    
    /**
     * Method responsible for exporting the Stereotypes.
     * @return Stereotypes.
     */
    private String exportStereotypes() {
        String export  = "  <stereotypes>\n";
        for (Stereotype stereotype : getStereotypesList())
               export += stereotype.export();
        return export +  "  </stereotypes>\n";
    }
    
    /**
     * Method responsible for returning the Links HashMap.
     * @return Links HashMap.
     */
    public HashMap getLinks() {
        return links;
    }
    
    /**
     * Method responsible for returning the Links List.
     * @return Links List.
     */
    public List<Link> getLinksList() {
        List   list = new ArrayList<>(links.values());
               list.sort(new ComparatorLink());
        return list;
    }
    
    /**
     * Method responsible for adding a Link.
     * @param link Link.
     */
    public void addLink(Link link) {
        if (link.getElement().allowStereotype()) {
            if (!links.containsKey(link.getId()))
                 links.put(link.getId(), link);
        }
    }
    
    /**
     * Method responsible for returning the Link by Element and Stereotype.
     * @param  element Element.
     * @param  stereotype Stereotype.
     * @return Link by Element and Stereotype.
     */
    public Link getLink(Element element, Stereotype stereotype) {
        return (Link) links.get("LINK#" + element.getId() + "-" + stereotype.getId());
    }
    
    /**
     * Method responsible for returning the Stereotypes String by Element.
     * @param  element Element.
     * @return Stereotypes String.
     */
    public String getStereotypesString(Element element) {
        String string  = "";
        for (Link link : getLinksByElement(element))
               string += link.getStereotype().getName() + " ";
        return string;
    }
    
    /**
     * Method responsible for returning the Links by Element.
     * @param  element Element.
     * @return Links by Element.
     */
    public List<Link> getLinksByElement(Element element) {
        List   filter = new ArrayList<>();
        for (Link link : getLinksList()) {
            if (link.getElement().getId().equals(element.getId()))
               filter.add(link);
        }
        return filter;
    }
    
    /**
     * Method responsible for returning the Links by Stereotype.
     * @param  stereotype Stereotype.
     * @return Links by Stereotype.
     */
    public List<Link> getLinksByStereotype(Stereotype stereotype) {
        List   filter = new ArrayList<>();
        for (Link link : getLinksList()) {
            if (link.getStereotype().equals(stereotype))
               filter.add(link);
        }
        return filter;
    }
    
    /**
     * Method responsible for removing a Link.
     * @param link Link.
     */
    public void removeLink(Link link) {
        links.remove(link.getId());
    }
    
    /**
     * Method responsible for removing the Links by Element.
     * @param element Element.
     */
    public void removeLinks(Element element) {
        for (Link link : getLinksByElement(element))
            removeLink(link);
    }
    
    /**
     * Method responsible for removing the Links by Stereotype.
     * @param stereotype Stereotype.
     */
    public void removeLinks(Stereotype stereotype) {
        for (Link link : getLinksByStereotype(stereotype))
            removeLink(link);
    }
    
    /**
     * Method responsible for reseting the Actor UML on Sequence Diagram.
     * @param actor Actor UML.
     */
    public void reset(ActorUML actor) {
        for (Object diagram : getDiagrams("sequence"))
            ((SequenceDiagram) diagram).resetLifeline(actor);
    }
    
    /**
     * Method responsible for reseting the Class UML on Sequence Diagram.
     * @param class_ Class UML.
     */
    public void reset(ClassUML class_) {
        for (Object diagram : getDiagrams("sequence"))
            ((SequenceDiagram) diagram).resetInstance(class_);
    }
    
    /**
     * Method responsible for reseting the Method UML on Sequence Diagram.
     * @param method Method UML.
     */
    public void reset(MethodUML method) {
        for (Object diagram : getDiagrams("sequence"))
            ((SequenceDiagram) diagram).resetMessage(method);
    }
    
    /**
     * Method responsible for changing the Message Names on Sequence Diagram.
     * @param method Method UML.
     */
    public void changeNames(MethodUML method) {
        for (Object diagram : getDiagrams("sequence"))
            ((SequenceDiagram) diagram).changeNames(method);
    }
    
    /**
     * Method responsible for exporting the Links.
     * @return Links.
     */
    private String exportLinks() {
        String export  = "  <links>\n";
        for (Link link : getLinksList())
               export += link.export();
        return export  + "  </links>\n";
    }
    
    /**
     * Method responsible for returning the Project Icon.
     * @return Project Icon.
     */
    public String getIcon() {
        return "icons/project.png";
    }
    
    /**
     * Method responsible for returning the Project Evaluation.
     * @return Project Evaluation.
     */
    public EvaluationProject getEvaluation() {
        return new EvaluationProject(this);
    }
    
    @Override
    public String export() {
        String export  = "<project id=\"" + id + "\" name=\"" + name + "\" version=\"" + version + "\">\n";
               export += exportTypes();
               export += exportStereotypes();
               export += profile.export();
               export += exportFeatureDiagrams();
               export += exportUMLDiagrams();
               export += exportRequirements();
               export += exportTraceabilities();
               export += exportLinks();
               export += exportProducts();
               export += exportMetrics();
               export += exportMeasures();
               export += "</project>";
        return getString(export);
    }
    
    /**
     * Method responsible for returning the Base 64 String.
     * @param  export String Export.
     * @return Base 64 decoded String.
     */
    private String getString(String export) {
        try {
            byte[] original = Base64.getEncoder().encode(export.getBytes());
            byte[] decoded  = Base64.getDecoder().decode(new String(original).getBytes("UTF-8"));
            return new String(decoded);
        } catch (UnsupportedEncodingException exception) {
            return export;
        }
    }
    
    @Override
    public int hashCode() {
        int    hash = 3;
               hash = 67 * hash + Objects.hashCode(id);
               hash = 67 * hash + Objects.hashCode(version);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Project == false)
            return false;
        return Objects.equals(id, ((Project) object).getId());
    }

    @Override
    public String toString() {
        String project  = "Id             = " + id             + "\n";
               project += "Name           = " + name           + "\n";
               project += "Path           = " + path           + "\n";
               project += "Version        = " + version        + "\n";
               project += "Diagrams       = " + diagrams       + "\n";
               project += "Types          = " + types          + "\n";
               project += "Variabilities  = " + variabilities  + "\n";
               project += "Requirements   = " + requirements   + "\n";
               project += "Traceabilities = " + traceabilities + "\n";
               project += "Metrics        = " + metrics        + "\n";
               project += "Measures       = " + measures       + "\n";
               project += "Stereotypes    = " + stereotypes    + "\n";
               project += "Products       = " + products       + "\n";
               project += "Objects        = " + objects        + "\n";
               project += "Links          = " + links          + "\n";
        return project;
    }
}