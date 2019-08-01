package model.structural.diagram.classes.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Element;
import model.structural.diagram.classes.Entity;

/**
 * <p>Class of Model <b>PackageUML</b>.</p>
 * <p>Class responsible for representing <b>Package UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.base.Element
 */
public class PackageUML extends Element {
    private PackageUML parent;
    private HashMap    packages;
    private HashMap    entities;
    
    /**
     * Default constructor method of Class.
     */
    public PackageUML() {
        this.parent    = null;
        this.packages  = new HashMap<>();
        this.entities  = new HashMap<>();
        this.name      = "package";
        this.type      = "package";
        this.mandatory = true;
        this.size      = new Point(250, 100);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public PackageUML(org.w3c.dom.Element element) {
        super(element, true);
        this.type     = "package";
        this.packages = new HashMap<>();
        this.entities = new HashMap<>();
    }

    @Override
    public void setDefaultName() {
        super.setDefaultName();
        this.name = this.name.toLowerCase();
    }
    
    @Override
    public void dx(Integer distance) {
        super.dx(distance);
        this.dxPackages(distance);
        this.dxEntities(distance);
    }
    
    /**
     * Method responsible for moving X.
     * @param distance Distance.
     */
    private void dxPackages(Integer distance) {
        for (PackageUML packageUML : this.getPackagesList()) {
            System.out.println(packageUML);
            System.out.println("");
            packageUML.dx(distance);
        }
    }
    
    /**
     * Method responsible for moving X.
     * @param distance Distance.
     */
    private void dxEntities(Integer distance) {
        for (Entity entity : this.getEntitiesList())
            entity.dx(distance);
    }
    
    @Override
    public void dy(Integer distance) {
        super.dy(distance);
        this.dyPackages(distance);
        this.dyEntities(distance);
    }
    
    /**
     * Method responsible for moving Y.
     * @param distance Distance.
     */
    private void dyPackages(Integer distance) {
        for (PackageUML packageUML : this.getPackagesList())
            packageUML.dy(distance);
    }
    
    /**
     * Method responsible for moving Y.
     * @param distance Distance.
     */
    private void dyEntities(Integer distance) {
        for (Entity entity : this.getEntitiesList())
            entity.dy(distance);
    }
    
    /**
     * Method responsible for returning the Parent Package.
     * @return Parent Package.
     */
    public PackageUML getParent() {
        return this.parent;
    }

    /**
     * Method responsible for setting the Parent Package.
     * @param parent Parent Package.
     */
    public void setParent(PackageUML parent) {
        this.parent = parent;
    }
    
    /**
     * Method responsible for returning the Packages HashMap.
     * @return Packages HashMap.
     */
    public HashMap getPackages() {
        return this.packages;
    }
    
    /**
     * Method responsible for returning the Packages List.
     * @return Packages List.
     */
    public List<PackageUML> getPackagesList() {
        return new ArrayList<>(this.packages.values());
    }
    
    /**
     * Method responsible for adding a Package UML.
     * @param package_ Package UML.
     */
    public void addPackage(PackageUML package_) {
        this.packages.put(package_.getId(), package_);
    }
    
    /**
     * Method responsible for removing a Package UML.
     * @param package_ Package UML.
     */
    public void removePackage(PackageUML package_) {
        this.packages.remove(package_.getId());
    }
    
    /**
     * Method responsible for setting the Package HashMap.
     * @param packages Package HashMap.
     */
    public void setPackages(HashMap packages) {
        this.packages = packages;
    }
    
    /**
     * Method responsible for returning the Entity HashMap.
     * @return Entity HashMap.
     */
    public HashMap getEntities() {
        return this.entities;
    }
    
    /**
     * Method responsible for returning the Entities List.
     * @return Entities List.
     */
    public List<Entity> getEntitiesList() {
        return new ArrayList<>(this.entities.values());
    }
    
    /**
     * Method responsible for adding a Entity.
     * @param entity Entity.
     */
    public void addEntity(Entity entity) {
        this.entities.put(entity.getId(), entity);
    }
    
    /**
     * Method responsible for removing a Entity.
     * @param entity Entity.
     */
    public void removeEntity(Entity entity) {
        this.entities.remove(entity.getId());
    }
    
    /**
     * Method responsible for setting the Entity HashMap.
     * @param entities Entity HashMap.
     */
    public void setEntities(HashMap entities) {
        this.entities = entities;
    }
    
    @Override
    public String getIcon() {
        return "src/images/icons/diagram/classes/package.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "stylePackageUML" + this.id;
    }
    
    /**
     * Method responsible for returning the Fill Color Code.
     * @return Fill Color Code.
     */
    private String getFillColor() {
        if (this.parent == null)
            return "#EEEEEE";
        return "#9999FF";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FOLDABLE, "0");
               style.put(mxConstants.STYLE_FILLCOLOR,   this.getFillColor());
               style.put(mxConstants.STYLE_STROKECOLOR, this.getFillColor());
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    /**
     * Method responsible for returning the Package Style.
     * @return Package Style.
     */
    public Map getPackageStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
        return style;
    }
    
    /**
     * Method responsible for returning the Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               style.put(mxConstants.STYLE_SHAPE,     mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        return style;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}