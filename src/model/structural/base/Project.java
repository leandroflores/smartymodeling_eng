package model.structural.base;

import model.structural.base.association.Association;
import funct.FunctDate;
import funct.FunctString;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import model.structural.base.association.Link;
import model.structural.base.evaluation.Metric;
import model.structural.base.interfaces.Exportable;
import model.structural.base.product.Product;
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
 * @since  20/05/2019
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
    public  HashMap traceabilities;
    public  HashMap metrics;
    public  HashMap products;
    public  HashMap stereotypes;
    public  HashMap links;
    public  HashMap objects;
    
    /**
     * Default constructor method of Class.
     */
    public Project() {
        this.id      = new FunctString().md5(new FunctDate().getFormattedDate(new Date()));
        this.name    = "Project0";
        this.path    = "New_Project.smty";
        this.version = "2.0";
        this.init();
        this.loadPrimitiveTypes();
        this.loadSMartyStereotypes();
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
        this.init();
    }
    
    /**
     * Method responsible for initializing HashMaps.
     */
    private void init() {
        this.diagrams       = new LinkedHashMap();
        this.types          = new LinkedHashMap();
        this.variabilities  = new LinkedHashMap();
        this.traceabilities = new LinkedHashMap();
        this.metrics        = new LinkedHashMap();
        this.products       = new LinkedHashMap();
        this.stereotypes    = new LinkedHashMap();
        this.links          = new LinkedHashMap();
        this.objects        = new LinkedHashMap();
        this.profile        = this.getDefaultProfile();
    }
    
    /**
     * Method responsible for returning Default Profile.
     * @return Default Profile.
     */
    private Profile getDefaultProfile() {
        this.loadStereotypes();
        Profile defaultProfile = new Profile();
                defaultProfile.setMandatory((Stereotype) this.stereotypes.get("STEREOTYPE#1"));
                defaultProfile.setOptional((Stereotype) this.stereotypes.get("STEREOTYPE#2"));
                defaultProfile.setVariationPoint((Stereotype) this.stereotypes.get("STEREOTYPE#3"));
                defaultProfile.setInclusive((Stereotype) this.stereotypes.get("STEREOTYPE#4"));
                defaultProfile.setExclusive((Stereotype) this.stereotypes.get("STEREOTYPE#5"));
        return  defaultProfile;
    }
    
    /**
     * Method responsible for loading Stereotypes.
     */
    private void loadStereotypes() {
        if (this.stereotypes.isEmpty())
            this.loadSMartyStereotypes();
    }
    
    /**
     * Method responsible for returning the Next Id.
     * @param  label Object Label.
     * @return Next Id.
     */
    public String nextId(String label) {
        Integer index   = 1;
        String  nextId  = label + index;
        while (this.objects.get(nextId) != null) {
                index  += 1;
                nextId  = label + index;
        }
        return  nextId;
    }
    
    /**
     * Method responsible for returning the Project Id.
     * @return Project Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Project Id.
     * @param id Project Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Project Name.
     * @return Project Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining the Project Name.
     * @param name Project Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method responsible for returning the Project Path.
     * @return Project Path.
     */
    public String getPath() {
        return this.path;
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
        return this.version;
    }

    /**
     * Method responsible for defining the Project Version.
     * @param version Project Version..
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * Metodo responsavel por retornar o Profile do Project.
     * @return Profile do Project.
     */
    public Profile getProfile() {
        return this.profile;
    }
    
    /**
     * Metodo responsavel por definir o Profile do Project.
     * @param profile Profile do Project.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    public List<Element> getElements() {
        List<Element> elements = new ArrayList<>();
        for (Object object : this.objects.values()) {
            if (object instanceof Element)
                elements.add((Element) object);
        }
        return elements;
    }
    
    /**
     * Method responsible for returning the Associations List.
     * @return Associations List.
     */
    public List<Association> getAssociations() {
        List<Association> associations = new ArrayList<>();
        for (Object object : this.objects.values()) {
            if (object instanceof Association)
                associations.add((Association) object);
        }
        return associations;
    }
    
    /**
     * Method responsible for returning the Diagrams HashMap.
     * @return Diagrams HashMap.
     */
    public HashMap getDiagrams() {
        return this.diagrams;
    }
    
    /**
     * Method responsible for returning the Diagrams List.
     * @return Diagrams List.
     */
    public List<Diagram> getDiagramsList() {
        ArrayList list = new ArrayList<>(this.diagrams.values());
                  list.sort(new Comparator<Diagram>() {
                      @Override
                      public int compare(Diagram diagramA, Diagram diagramB) {
                          if (diagramA.getType().equals(diagramB.getType()))
                              return diagramA.getName().compareTo(diagramB.getName());
                          return diagramA.getType().compareTo(diagramB.getType());
                      }
                  });
        return    list;
    }
    
    /**
     * Method responsible for updating the Stereotypes.
     */
    public void updateStereotypes() {
        for (Diagram diagram : this.getDiagramsList())
            diagram.updateElementsStereotype();
    }
    
    /**
     * Method responsible for returning the Next Diagram Id.
     * @return Next Diagram Id.
     */
    public String nextDiagramId() {
        Integer index  = 1;
        String  nextId = "DIAGRAM#" + index;
        while (this.diagrams.get(nextId) != null) {
            index += 1;
            nextId = "DIAGRAM#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Diagram.
     * @param diagram Diagram.
     */
    public void addDiagram(Diagram diagram) {
        diagram.setId(this.nextDiagramId());
        if (this.diagrams.get(diagram.getId()) == null)
            this.diagrams.put(diagram.getId(), diagram);
    }
    
    /**
     * Method responsible for removing the Elements from a Diagram.
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
        this.removeElements(diagram);
        this.diagrams.remove(diagram.getId());
    }
    
    /**
     * Method responsible for exporting the Diagrams.
     * @return Diagrams.
     */
    private String exportDiagrams() {
        String export  = "";
        for (Diagram diagram : this.getDiagramsList())
               export += diagram.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Types HashMap.
     * @return Types HashMap.
     */
    public HashMap getTypes() {
        return this.types;
    }
    
    /**
     * Method responsible for returning the Types List.
     * @return Types List.
     */
    public List<TypeUML> getTypesList() {
        ArrayList list = new ArrayList<>(this.types.values());
                  list.sort(new Comparator<TypeUML>() {
                      @Override
                      public int compare(TypeUML typeA, TypeUML typeB) {
                          return typeA.getName().compareTo(typeB.getName());
                      }
                  });
        return    list;
    }
    
    /**
     * Method responsible for returning the Elements by Type.
     * @param  type Element Type.
     * @return Elements List.
     */
    public List getElements(String type) {
        List list = new ArrayList<>();
        for (Element element : this.getElements()) {
            if (element.getType().equalsIgnoreCase(type))
                list.add(element);
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
        for (Diagram diagram : this.getDiagramsList()) {
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
        for (Object element : this.getElements(type)) {
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
        Integer index  = 1;
        String  nextId = "TYPE#" + index;
        while (this.types.get(nextId) != null) {
            index += 1;
            nextId = "TYPE#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a UML Type.
     * @param type UML Type.
     */
    public void addType(TypeUML type) {
        type.setId(this.nextTypeId());
        this.types.put(type.getId(), type);
    }
    
    /**
     * Method responsible for adding a Default Type.
     * @param type UML Default Type.
     */
    public void addDefaultType(TypeUML type) {
        if (type.getId() != null)
            this.types.put(type.getId(), type);
    }
    
    /**
     * Method responsible for returning the Type by Entity.
     * @param  entity Entity.
     * @return Entity Type.
     */
    public TypeUML getEntityType(Entity entity) {
        return (TypeUML) this.types.get(entity.getId());
    }
    
    /**
     * Method responsible for adding a Entity Type.
     * @param entity Entity.
     */
    public void addEntityType(Entity entity) {
        TypeUML type = new TypeUML(entity);
        this.types.put(type.getId(), type);
                entity.setTypeUML(type);
    }
    
    /**
     * Method responsible for removing a UML Type.
     * @param type UML Type.
     */
    public void removeType(TypeUML type) {
        this.types.remove(type.getId());
    }
    
    /**
     * Method responsible for removing a Entity Type.
     * @param entity Entity 
     */
    public void removeEntityType(Entity entity) {
        TypeUML oldType = (TypeUML) this.types.get(entity.getId());
        TypeUML newType = this.getObjectType();
        if (oldType != null) {
            for (Object diagram : this.getDiagrams("class"))
                ((ClassDiagram) diagram).changeTypeUML(oldType, newType);
            this.removeType(oldType);
        }
    }
    
    /**
     * Method responsible for adding Primitive Types.
     */
    private void loadPrimitiveTypes() {
        this.addDefaultType(new TypeUML("TYPE#1",  "", "boolean", true));
        this.addDefaultType(new TypeUML("TYPE#2",  "", "byte",    true));
        this.addDefaultType(new TypeUML("TYPE#3",  "", "char",    true));
        this.addDefaultType(new TypeUML("TYPE#4",  "", "double",  true));
        this.addDefaultType(new TypeUML("TYPE#5",  "", "float",   true));
        this.addDefaultType(new TypeUML("TYPE#6",  "", "int",     true));
        this.addDefaultType(new TypeUML("TYPE#7",  "", "long",    true));
        this.addDefaultType(new TypeUML("TYPE#8",  "", "short",   true));
        this.addDefaultType(new TypeUML("TYPE#9",  "", "void",    true));
        this.addDefaultType(new TypeUML("TYPE#10", "java.lang", "Boolean",       false));
        this.addDefaultType(new TypeUML("TYPE#11", "java.lang", "Byte",          false));
        this.addDefaultType(new TypeUML("TYPE#12", "java.lang", "Character",     false));
        this.addDefaultType(new TypeUML("TYPE#13", "java.lang", "Double",        false));
        this.addDefaultType(new TypeUML("TYPE#14", "java.lang", "Enum",          false));
        this.addDefaultType(new TypeUML("TYPE#15", "java.lang", "Exception",     false));
        this.addDefaultType(new TypeUML("TYPE#16", "java.lang", "Float",         false));
        this.addDefaultType(new TypeUML("TYPE#17", "java.lang", "Integer",       false));
        this.addDefaultType(new TypeUML("TYPE#18", "java.lang", "Long",          false));
        this.addDefaultType(new TypeUML("TYPE#19", "java.lang", "Math",          false));
        this.addDefaultType(new TypeUML("TYPE#20", "java.lang", "Number",        false));
        this.addDefaultType(new TypeUML("TYPE#21", "java.lang", "Object",        false));
        this.addDefaultType(new TypeUML("TYPE#22", "java.lang", "Package",       false));
        this.addDefaultType(new TypeUML("TYPE#23", "java.lang", "Short",         false));
        this.addDefaultType(new TypeUML("TYPE#24", "java.lang", "String",        false));
        this.addDefaultType(new TypeUML("TYPE#25", "java.lang", "StringBuffer",  false));
        this.addDefaultType(new TypeUML("TYPE#26", "java.lang", "StringBuilder", false));
        this.addDefaultType(new TypeUML("TYPE#27", "java.lang", "Thread",        false));
        this.addDefaultType(new TypeUML("TYPE#28", "java.lang", "Void",          false));
        this.addDefaultType(new TypeUML("TYPE#29", "java.util", "Arrays",        false));
        this.addDefaultType(new TypeUML("TYPE#30", "java.util", "Collections",   false));
        this.addDefaultType(new TypeUML("TYPE#31", "java.util", "Date",          false));
        this.addDefaultType(new TypeUML("TYPE#32", "java.util", "EnumMap",       false));
        this.addDefaultType(new TypeUML("TYPE#33", "java.util", "EnumSet",       false));
        this.addDefaultType(new TypeUML("TYPE#34", "java.util", "EventListener", false));
        this.addDefaultType(new TypeUML("TYPE#35", "java.util", "HashMap",       false));
        this.addDefaultType(new TypeUML("TYPE#36", "java.util", "HashSet",       false));
        this.addDefaultType(new TypeUML("TYPE#37", "java.util", "Hashtable",     false));
        this.addDefaultType(new TypeUML("TYPE#38", "java.util", "LinkedHashMap", false));
        this.addDefaultType(new TypeUML("TYPE#39", "java.util", "LinkedHashSet", false));
        this.addDefaultType(new TypeUML("TYPE#40", "java.util", "LinkedList",    false));
        this.addDefaultType(new TypeUML("TYPE#41", "java.util", "List",    false));
        this.addDefaultType(new TypeUML("TYPE#42", "java.util", "Map",     false));
        this.addDefaultType(new TypeUML("TYPE#43", "java.util", "Queue",   false));
        this.addDefaultType(new TypeUML("TYPE#44", "java.util", "Random",  false));
        this.addDefaultType(new TypeUML("TYPE#45", "java.util", "Scanner", false));
        this.addDefaultType(new TypeUML("TYPE#46", "java.util", "Set",     false));
        this.addDefaultType(new TypeUML("TYPE#47", "java.util", "Stack",   false));
        this.addDefaultType(new TypeUML("TYPE#48", "java.util", "Timer",   false));
        this.addDefaultType(new TypeUML("TYPE#49", "java.util", "TreeMap", false));
        this.addDefaultType(new TypeUML("TYPE#50", "java.util", "TreeSet", false));
        this.addDefaultType(new TypeUML("TYPE#51", "java.util", "Vector",  false));
    }
    
    /**
     * Method responsible for returning the Type by Name.
     * @param  name Type Name.
     * @return Type found.
     */
    public TypeUML getTypeByName(String name) {
        for (TypeUML tipo : this.getTypesList()) {
            if (tipo.getName().equals(name))
                return tipo;
        }
        return this.getObjectType();
    }
    
    /**
     * Method responsible for returning the Type by Signature.
     * @param  signature Type Signature.
     * @return Type found.
     */
    public TypeUML getTypeBySignature(String signature) {
        for (TypeUML type : this.getTypesList()) {
            if ((type.getSignature().equals(signature)) && (type.isPrimitive() == false))
                return type;
        }
        return this.getObjectType();
    }
    
    /**
     * Method responsible for returning the Object Type.
     * @return Object Type.
     */
    public TypeUML getObjectType() {
        if (this.types.isEmpty())
            this.loadPrimitiveTypes();
        return (TypeUML) this.types.get("TYPE#21");
    }
    
    /**
     * Method responsible for returning the Void Type.
     * @return Void Type.
     */
    public TypeUML getVoidType() {
        if (this.types.isEmpty())
            this.loadPrimitiveTypes();
        return (TypeUML) this.types.get("TYPE#9");
    }
    
    /**
     * Method responsible for exporting the Types.
     * @return Types.
     */
    private String exportTypes() {
        String export  = "  <types>\n";
        for (TypeUML type : this.getTypesList())
               export += type.export();
        return export  + "  </types>\n";
    }
    
    /**
     * Method responsible for returning the Variabilities HashMap.
     * @return Variabilities HashMap.
     */
    public HashMap getVariabilities() {
        return this.variabilities;
    }
    
    /**
     * Method responsible for returning the Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> getVariabilitiesList() {
        return new ArrayList<>(this.variabilities.values());
    }
    
    /**
     * Method responsible for returning the Next Variability Id.
     * @return Next Variability Id.
     */
    public String nextVariabilityId() {
        Integer index  = 1;
        String  nextId = "VARIABILITY#" + index;
        while (this.variabilities.get(nextId) != null) {
            index += 1;
            nextId = "VARIABILITY#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Variability.
     * @param variability Variability.
     */
    public void addVariability(Variability variability) {
        variability.setId(this.nextVariabilityId());
        this.variabilities.put(variability.getId(), variability);
        this.addVariabilityStereotype(variability);
    }
    
    /**
     * Method responsible for returning the Variant Stereotype.
     * @param  variability Variability.
     * @return Variant Stereotype.
     */
    public Stereotype getVariantStereotype(Variability variability) {
        if (variability.getConstraint().equals("Inclusive"))
            return this.profile.getInclusive();
        return this.profile.getExclusive();
    }
    
    /**
     * Method responsible for adding the Variability Stereotype.
     * @param variability Variability.
     */
    public void addVariabilityStereotype(Variability variability) {
        this.addLink(new Link(variability.getVariationPoint(), this.profile.getVariationPoint()));
        for (Element variant : variability.getVariants())
            this.addLink(new Link(variant, this.getVariantStereotype(variability)));
    }
    
    /**
     * Method responsible for removing a Variability.
     * @param variability Variability.
     */
    public void removeVariability(Variability variability) {
        this.variabilities.remove(variability.getId());
    }
    
    /**
     * Method responsible for returning the Next Traceability Id.
     * @return Next Traceability Id.
     */
    public String nextTraceabilityId() {
        Integer index  = 1;
        String  nextId = "TRACEABILITY#" + index;
        while (this.traceabilities.get(nextId) != null) {
            index += 1;
            nextId = "TRACEABILITY#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Traceability.
     * @param traceability Traceability.
     */
    public void addTraceability(Traceability traceability) {
        traceability.setId(this.nextTraceabilityId());
        this.traceabilities.put(traceability.getId(), traceability);
    }
    
    /**
     * Method responsible for returning a Traceability by Id.
     * @param  id Traceability Id.
     * @return Traceability found.
     */
    public Traceability getTraceability(String id) {
        return (Traceability) this.traceabilities.get(id);
    }
    
    /**
     * Method responsible for removing a Traceability.
     * @param traceability Traceability.
     */
    public void removeTraceability(Traceability traceability) {
        this.traceabilities.remove(traceability.getId());
    }
    
    /**
     * Method responsible for removing a Traceability by Element.
     * @param element Element.
     */
    public void removeTraceability(Element element) {
        for (Traceability traceability : this.getTraceabilitiesList()) {
            if (traceability.contains(element))
                traceability.removeElement(element);
        }
    }
    
    /**
     * Method responsible for returning Traceabilities List.
     * @return Traceabilities List.
     */
    public List<Traceability> getTraceabilitiesList() {
        return new ArrayList<>(this.traceabilities.values());
    }
    
    /**
     * Method responsible for exporting the Traceabilities.
     * @return Traceabilities.
     */
    private String exportTraceabilities() {
        String export  = "";
        for (Traceability traceability : this.getTraceabilitiesList())
               export += traceability.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Next Metric Id.
     * @return Next Metric Id.
     */
    public String nextMetricId() {
        Integer index  = 1;
        String  nextId = "METRIC#" + index;
        while (this.metrics.get(nextId) != null) {
            index += 1;
            nextId = "METRIC#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Metric.
     * @param metric Metric.
     */
    public void addMetric(Metric metric) {
        metric.setId(this.nextMetricId());
        this.metrics.put(metric.getId(), metric);
    }
    
    /**
     * Method responsible for returning a Metric by Id.
     * @param  id Metric Id.
     * @return Metric found.
     */
    public Metric getMetric(String id) {
        return (Metric) this.metrics.get(id);
    }
    
    /**
     * Method responsible for removing a Metric.
     * @param metric Metric.
     */
    public void removeMetric(Metric metric) {
        this.metrics.remove(metric.getId());
    }
    
    /**
     * Method responsible for returning Metrics List.
     * @return Metrics List.
     */
    public List<Metric> getMetricsList() {
        return new ArrayList<>(this.metrics.values());
    }
    
    /**
     * Method responsible for exporting the Metrics.
     * @return Metrics.
     */
    private String exportMetrics() {
        String export  = "";
        for (Metric metric : this.getMetricsList())
               export += metric.export();
        return export;
    }
    
    /**
     * Method responsible for returning the Next Product Id.
     * @return Next Product Id.
     */
    public String nextProductId() {
        Integer index  = 1;
        String  nextId = "PRODUCT#" + index;
        while (this.products.get(nextId) != null) {
            index += 1;
            nextId = "PRODUCT#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Product.
     * @param product Product.
     */
    public void addProduct(Product product) {
        product.setId(this.nextProductId());
        this.products.put(product.getId(), product);
    }
    
    /**
     * Method responsible for returning a Product by Id.
     * @param  id Product Id.
     * @return Product found.
     */
    public Product getProduct(String id) {
        return (Product) this.products.get(id);
    }
    
    /**
     * Method responsible for removing a Element of a Product.
     * @param element Element.
     */
    public void removeProduct(Element element) {
        for (Product product : this.getProductsList()) 
            this.remove(product, element);
    }
    
    /**
     * Method responsible for removing a Element from a Product.
     * @param product Product.
     * @param element Element.
     */
    private void remove(Product product, Element element) {
        if (product.contains(element)) {
            product.remove(element);
            if (product.isEmpty())
                this.removeProduct(product);
        }
    }
    
    /**
     * Method responsible for removing a Association from a Product.
     * @param association Association.
     */
    public void removeProduct(Association association) {
        for (Product product : this.getProductsList()) 
            product.remove(association);
    }
    
    /**
     * Method responsible for removing a Product.
     * @param product Product.
     */
    public void removeProduct(Product product) {
        this.products.remove(product.getId());
    }
    
    /**
     * Method responsible for returning Products List.
     * @return Products List.
     */
    public List<Product> getProductsList() {
        return new ArrayList<>(this.products.values());
    }
    
    /**
     * Method responsible for exporting the Products.
     * @return Products.
     */
    private String exportProducts() {
        String export  = "  <products>\n";
        for (Product product : this.getProductsList())
               export += product.export();
        return export  + "  </products>\n";
    }
    
    /**
     * Method responsible for returning the Stereotypes HashMap.
     * @return Stereotypes HashMap.
     */
    public HashMap getStereotypes() {
        return this.stereotypes;
    }
    
    /**
     * Method responsible for returning the Stereotipos List.
     * @return Stereotipos List.
     */
    public List<Stereotype> getStereotypesList() {
        ArrayList list = new ArrayList<>(this.stereotypes.values());
                  list.sort(this.getStereotypeComparator());
        return    list;
    }
    
    /**
     * Method responsible for returning the Stereotypes List by Primitive Flag.
     * @param  primitive Primitive Flag.
     * @return Stereotypes List.
     */
    public List<Stereotype> getStereotypesList(boolean primitive) {
        List<Stereotype> filter = new ArrayList<>();
        for (Object stereotype : this.stereotypes.values()) {
            if (((Stereotype) stereotype).isPrimitive() == primitive)
                filter.add((Stereotype) stereotype);
        }
                filter.sort(this.getStereotypeComparator());
        return  filter;
    }
    
    /**
     * Method responsible for returning the Stereotype Comparator.
     * @return Stereotype Comparator.
     */
    private Comparator getStereotypeComparator() {
        return new Comparator<Stereotype>() {
                @Override
                public int compare(Stereotype stereotypeA, Stereotype stereotypeB) {
                    return stereotypeA.getName().compareTo(stereotypeB.getName());                          
                }};
    }
    
    /**
     * Method responsible for returning the Next Stereotype Id.
     * @return Next Stereotype Id.
     */
    public String nextStereotypeId() {
        Integer index  = 1;
        String  nextId = "STEREOTYPE#" + index;
        while (this.stereotypes.get(nextId) != null) {
            index += 1;
            nextId = "STEREOTYPE#" + index;
        }
        return nextId;
    }
    
    /**
     * Method responsible for adding a Stereotype.
     * @param stereotype Stereotype.
     */
    public void addStereotype(Stereotype stereotype) {
        stereotype.setId(this.nextStereotypeId());
        this.stereotypes.put(stereotype.getId(), stereotype);
    }
    
    /**
     * Method responsible for adding a Default Stereotype.
     * @param stereotype Stereotype.
     */
    public void addDefaultStereotype(Stereotype stereotype) {
        if (stereotype.getId() != null)
            this.stereotypes.put(stereotype.getId(), stereotype);
    }
    
    /**
     * Method responsible for adding the Element Stereotype.
     * @param element Element.
     */
    public void addElementStereotype(Element element) {
        if (element.isDefault() && element.allowStereotype()) {
            Stereotype stereotype = element.isMandatory() ? this.profile.getMandatory() : this.profile.getOptional();
            this.addLink(new Link(element, stereotype));
        }
    }
    
    /**
     * Method responsible for removing a Stereotype.
     * @param stereotype Stereotype.
     */
    public void removeStereotype(Stereotype stereotype) {
        this.removeLinks(stereotype);
        this.stereotypes.remove(stereotype.getId());
    }
    
    /**
     * Method responsible for loading the SMarty Stereotypes.
     */
    private void loadSMartyStereotypes() {
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#1", "mandatory",       true));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#2", "optional",        true));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#3", "variationPoint",  true));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#4", "alternative_OR",  true));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#5", "alternative_XOR", true));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#6", "requires",        true));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#7", "mutex",           true));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#8", "stereotype",      false));
    }
    
    /**
     * Method responsible for returning the Default Stereotype.
     * @return Default Stereotype.
     */
    public Stereotype getDefaultStereotype() {
        if (this.stereotypes.isEmpty())
            this.loadSMartyStereotypes();
        return (Stereotype) this.stereotypes.get("STEREOTYPE#1");
    }
    
    /**
     * Method responsible for returning the Stereotype by Name.
     * @param  name Stereotype Name.
     * @return Stereotype found.
     */
    public Stereotype getStereotypeByName(String name) {
        for (Stereotype stereotype : this.getStereotypesList()) {
            if (stereotype.getName().equals(name))
                return stereotype;
        }
//        return this.getDefaultStereotype();
        return null;
    }
    
    /**
     * Method responsible for exporting the Stereotypes.
     * @return Stereotypes.
     */
    private String exportStereotypes() {
        String export  = "  <stereotypes>\n";
        for (Stereotype stereotype : this.getStereotypesList())
               export += stereotype.export();
        return export  + "  </stereotypes>\n";
    }
    
    /**
     * Method responsible for returning the Links HashMap.
     * @return Links HashMap.
     */
    public HashMap getLinks() {
        return this.links;
    }
    
    /**
     * Method responsible for returning the Links List.
     * @return Links List.
     */
    public List<Link> getLinksList() {
        ArrayList list = new ArrayList<>(this.links.values());
                  list.sort(this.getLinkComparator());
        return    list;
    }
    
    /**
     * Method responsible for returning the Link Comparator.
     * @return Link Comparator.
     */
    private Comparator getLinkComparator() {
        return new Comparator<Link>() {
                @Override
                public int compare(Link linkA, Link linkB) {
                    return linkA.getSignature().compareTo(linkB.getSignature());
                }};
    }
    
    /**
     * Method responsible for adding a Link.
     * @param link Link.
     */
    public void addLink(Link link) {
        if (link.getElement().allowStereotype()) {
            if (this.links.containsKey(link.getId()) == false)
                this.links.put(link.getId(), link);
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
     * Method responsible for returning the Links by Element.
     * @param  element Element.
     * @return Links by Element.
     */
    public List<Link> getLinksByElement(Element element) {
        List<Link> filter = new ArrayList<>();
        for (Link link : this.getLinksList()) {
            if (link.getElement().equals(element))
                   filter.add(link);
        }
        return     filter;
    }
    
    /**
     * Method responsible for returning the Links by Stereotype.
     * @param  stereotype Stereotype.
     * @return Links by Stereotype.
     */
    public List<Link> getLinksByStereotype(Stereotype stereotype) {
        List<Link> filter = new ArrayList<>();
        for (Link link : this.getLinksList()) {
            if (link.getStereotype().equals(stereotype))
                   filter.add(link);
        }
        return     filter;
    }
    
    /**
     * Method responsible for removing a Link.
     * @param link Link.
     */
    public void removeLink(Link link) {
        this.links.remove(link.getId());
    }
    
    /**
     * Method responsible for removing the Links by Element.
     * @param element Element.
     */
    public void removeLinks(Element element) {
        List<Link> filter = this.getLinksByElement(element);
        for (int i = 0; i < filter.size(); i++)
            this.removeLink(filter.get(i));
    }
    
    /**
     * Method responsible for removing the Links by Stereotype.
     * @param stereotype Stereotype.
     */
    public void removeLinks(Stereotype stereotype) {
        List<Link> filter = this.getLinksByStereotype(stereotype);
        for (int i = 0; i < filter.size(); i++)
            this.removeLink(filter.get(i));
    }
    
    /**
     * Method responsible for reseting the Actor UML on Sequence Diagram.
     * @param actor Actor UML.
     */
    public void reset(ActorUML actor) {
        for (Object diagram : this.getDiagrams("sequence"))
            ((SequenceDiagram) diagram).resetLifeline(actor);
    }
    
    /**
     * Method responsible for reseting the Class UML on Sequence Diagram.
     * @param class_ Class UML.
     */
    public void reset(ClassUML class_) {
        for (Object diagram : this.getDiagrams("sequence"))
            ((SequenceDiagram) diagram).resetInstance(class_);
    }
    
    /**
     * Method responsible for reseting the Method UML on Sequence Diagram.
     * @param method Method UML.
     */
    public void reset(MethodUML method) {
        for (Object diagram : this.getDiagrams("sequence"))
            ((SequenceDiagram) diagram).resetMessage(method);
    }
    
    /**
     * Method responsible for exporting the Links.
     * @return Links.
     */
    private String exportLinks() {
        String export  = "  <links>\n";
        for (Link link : this.getLinksList())
               export += link.export();
        return export  + "  </links>\n";
    }
    
    @Override
    public String export() {
        String export  = "<project id=\"" + this.id + "\" name=\"" + this.name + "\" version=\"" + this.version + "\">\n";
               export += this.exportTypes();
               export += this.exportStereotypes();
               export += this.profile.export();
               export += this.exportDiagrams();
               export += this.exportTraceabilities();
               export += this.exportMetrics();
               export += this.exportLinks();
               export += this.exportProducts();
               export += "</project>";
        return export;
    }
    
    @Override
    public int hashCode() {
        int    hash = 3;
               hash = 67 * hash + Objects.hashCode(this.id);
               hash = 67 * hash + Objects.hashCode(this.version);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Project == false)
            return false;
        return Objects.equals(this.id, ((Project) object).getId());
    }

    @Override
    public String toString() {
        String project  = "Id             = " + this.id             + "\n";
               project += "Name           = " + this.name           + "\n";
               project += "Path           = " + this.path           + "\n";
               project += "Version        = " + this.version        + "\n";
               project += "Diagrams       = " + this.diagrams       + "\n";
               project += "Traceabilities = " + this.traceabilities + "\n";
               project += "Metrics        = " + this.metrics        + "\n";
               project += "Stereotypes    = " + this.stereotypes    + "\n";
               project += "Variabilities  = " + this.variabilities  + "\n";
               project += "Objects        = " + this.objects        + "\n";
               project += "Types          = " + this.types          + "\n";
               project += "Links          = " + this.links          + "\n";
               project += "Products       = " + this.products       + "\n";
        return project;
    }
}