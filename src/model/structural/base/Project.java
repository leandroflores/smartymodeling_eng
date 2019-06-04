package model.structural.base;

import model.structural.base.association.Association;
import funct.FunctDate;
import funct.FunctString;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import model.structural.base.association.Link;
import model.structural.base.interfaces.Exportable;
import model.structural.diagram.classs.base.TypeUML;
import model.structural.base.variability.Variability;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.Entity;

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
        this.diagrams      = new HashMap();
        this.types         = new HashMap();
        this.variabilities = new HashMap();
        this.stereotypes   = new HashMap();
        this.links         = new HashMap();
        this.objects       = new HashMap();
        this.profile       = this.getDefaultProfile();
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
        Integer index  = 1;
        String  nextId = label + index;
        while (this.objects.get(nextId) != null) {
            index += 1;
            nextId = label + index;
        }
        return nextId;
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
        for (int i = diagram.getElementsList().size() - 1; i >= 0; i--)
            diagram.removeElement(diagram.getElementsList().get(i));
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
        if (this.types.get(entity.getId()) != null) {
            List<Diagram> list = this.getDiagramsList();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof ClassDiagram)
                    System.out.println("Class Diagram: " + (ClassDiagram) list.get(i));
            }
            System.out.println("Remove Type: " + this.types.get(entity.getId()));
        }
    }
    
    /**
     * Method responsible for adding Primitive Types.
     */
    private void loadPrimitiveTypes() {
        this.addDefaultType(new TypeUML("TYPE#1",  "", "boolean", true));
        this.addDefaultType(new TypeUML("TYPE#2",  "", "byte", true));
        this.addDefaultType(new TypeUML("TYPE#3",  "", "char", true));
        this.addDefaultType(new TypeUML("TYPE#4",  "", "double", true));
        this.addDefaultType(new TypeUML("TYPE#5",  "", "float", true));
        this.addDefaultType(new TypeUML("TYPE#6",  "", "int", true));
        this.addDefaultType(new TypeUML("TYPE#7",  "", "long", true));
        this.addDefaultType(new TypeUML("TYPE#8",  "", "short", true));
        this.addDefaultType(new TypeUML("TYPE#9",  "", "void", true));
        this.addDefaultType(new TypeUML("TYPE#10", "java.lang", "Boolean", false));
        this.addDefaultType(new TypeUML("TYPE#11", "java.lang", "Byte", false));
        this.addDefaultType(new TypeUML("TYPE#12", "java.lang", "Character", false));
        this.addDefaultType(new TypeUML("TYPE#13", "java.lang", "Double", false));
        this.addDefaultType(new TypeUML("TYPE#14", "java.lang", "Enum", false));
        this.addDefaultType(new TypeUML("TYPE#15", "java.lang", "Exception", false));
        this.addDefaultType(new TypeUML("TYPE#16", "java.lang", "Float", false));
        this.addDefaultType(new TypeUML("TYPE#17", "java.lang", "Integer", false));
        this.addDefaultType(new TypeUML("TYPE#18", "java.lang", "Long", false));
        this.addDefaultType(new TypeUML("TYPE#19", "java.lang", "Math", false));
        this.addDefaultType(new TypeUML("TYPE#20", "java.lang", "Number", false));
        this.addDefaultType(new TypeUML("TYPE#21", "java.lang", "Object", false));
        this.addDefaultType(new TypeUML("TYPE#22", "java.lang", "Package", false));
        this.addDefaultType(new TypeUML("TYPE#23", "java.lang", "Short", false));
        this.addDefaultType(new TypeUML("TYPE#24", "java.lang", "String", false));
        this.addDefaultType(new TypeUML("TYPE#25", "java.lang", "StringBuffer", false));
        this.addDefaultType(new TypeUML("TYPE#26", "java.lang", "StringBuilder", false));
        this.addDefaultType(new TypeUML("TYPE#27", "java.lang", "Thread", false));
        this.addDefaultType(new TypeUML("TYPE#28", "java.lang", "Void", false));
        this.addDefaultType(new TypeUML("TYPE#29", "java.util", "Arrays", false));
        this.addDefaultType(new TypeUML("TYPE#30", "java.util", "Collections", false));
        this.addDefaultType(new TypeUML("TYPE#31", "java.util", "Date", false));
        this.addDefaultType(new TypeUML("TYPE#32", "java.util", "EnumMap", false));
        this.addDefaultType(new TypeUML("TYPE#33", "java.util", "EnumSet", false));
        this.addDefaultType(new TypeUML("TYPE#34", "java.util", "EventListener", false));
        this.addDefaultType(new TypeUML("TYPE#35", "java.util", "HashMap", false));
        this.addDefaultType(new TypeUML("TYPE#36", "java.util", "HashSet", false));
        this.addDefaultType(new TypeUML("TYPE#37", "java.util", "Hashtable", false));
        this.addDefaultType(new TypeUML("TYPE#38", "java.util", "LinkedHashMap", false));
        this.addDefaultType(new TypeUML("TYPE#39", "java.util", "LinkedHashSet", false));
        this.addDefaultType(new TypeUML("TYPE#40", "java.util", "LinkedList", false));
        this.addDefaultType(new TypeUML("TYPE#41", "java.util", "List", false));
        this.addDefaultType(new TypeUML("TYPE#42", "java.util", "Map", false));
        this.addDefaultType(new TypeUML("TYPE#43", "java.util", "Queue", false));
        this.addDefaultType(new TypeUML("TYPE#44", "java.util", "Random", false));
        this.addDefaultType(new TypeUML("TYPE#45", "java.util", "Scanner", false));
        this.addDefaultType(new TypeUML("TYPE#46", "java.util", "Set", false));
        this.addDefaultType(new TypeUML("TYPE#47", "java.util", "Stack", false));
        this.addDefaultType(new TypeUML("TYPE#48", "java.util", "Timer", false));
        this.addDefaultType(new TypeUML("TYPE#49", "java.util", "TreeMap", false));
        this.addDefaultType(new TypeUML("TYPE#50", "java.util", "TreeSet", false));
        this.addDefaultType(new TypeUML("TYPE#51", "java.util", "Vector", false));
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
        String export  = "";
        for (TypeUML type : this.getTypesList())
               export += type.export();
        return export;
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
    }
    
    /**
     * Method responsible for removing a Variability.
     * @param variability Variability.
     */
    public void removeVariability(Variability variability) {
        this.variabilities.remove(variability.getId());
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
                  list.sort(new Comparator<Stereotype>() {
                      @Override
                      public int compare(Stereotype stereotypeA, Stereotype stereotypeB) {
                          return stereotypeA.getName().compareTo(stereotypeB.getName());                          
                      }
                  });
        return    list;
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
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#1", "mandatory"));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#2", "optional"));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#3", "variationPoint"));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#4", "alternative_OR"));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#5", "alternative_XOR"));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#6", "requires"));
        this.addDefaultStereotype(new Stereotype("STEREOTYPE#7", "mutex"));
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
        String export  = "";
        for (Stereotype stereotype : this.getStereotypesList())
               export += stereotype.export();
        return export;
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
        return new ArrayList<>(this.links.values());
    }
    
    /**
     * Method responsible for adding a Link.
     * @param link Link.
     */
    public void addLink(Link link) {
        if (this.links.containsKey(link.getId()) == false)
            this.links.put(link.getId(), link);
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
    public List<Link> filterLinksByElement(Element element) {
        List<Link> list   = this.getLinksList();
        List<Link> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getElement().equals(element))
                   filter.add(list.get(i));
        }
        return     filter;
    }
    
    /**
     * Method responsible for returning the Links by Stereotype.
     * @param  stereotype Stereotype.
     * @return Links by Stereotype.
     */
    public List<Link> filterLinksByStereotype(Stereotype stereotype) {
        List<Link> list   = this.getLinksList();
        List<Link> filter = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStereotype().equals(stereotype))
                   filter.add(list.get(i));
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
        List<Link> filter = this.filterLinksByElement(element);
        for (int i = 0; i < filter.size(); i++)
            this.removeLink(filter.get(i));
    }
    
    /**
     * Method responsible for removing the Links by Stereotype.
     * @param stereotype Stereotype.
     */
    public void removeLinks(Stereotype stereotype) {
        List<Link> filter = this.filterLinksByStereotype(stereotype);
        for (int i = 0; i < filter.size(); i++)
            this.removeLink(filter.get(i));
    }
    
    /**
     * Method responsible for exporting the Links.
     * @return Links.
     */
    private String exportLinks() {
        String export  = "";
        for (Link link : this.getLinksList())
               export += link.export();
        return export;
    }
    
    @Override
    public String export() {
        String export  = "<project id=\"" + this.id + "\" name=\"" + this.name + "\" version=\"" + this.version + "\">\n";
               export += "  <types>\n"       + this.exportTypes()       + "  </types>\n";
               export += "  <stereotypes>\n" + this.exportStereotypes() + "  </stereotypes>\n";
               export += this.profile.export();
               export += this.exportDiagrams();
               export += "  <links>\n"       + this.exportLinks()       + "  </links>\n";
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
        String project  = "Id            = " + this.id            + "\n";
               project += "Name          = " + this.name          + "\n";
               project += "Path          = " + this.path          + "\n";
               project += "Version       = " + this.version       + "\n";
               project += "Diagrams      = " + this.diagrams      + "\n";
               project += "Stereotypes   = " + this.stereotypes   + "\n";
               project += "Variabilities = " + this.variabilities + "\n";
               project += "Objects       = " + this.objects       + "\n";
               project += "Types         = " + this.types         + "\n";
        return project;
    }
}