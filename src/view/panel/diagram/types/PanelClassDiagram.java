package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxPoint;
import controller.view.panel.diagram.association.types.ControllerEventAssociationClass;
import controller.view.panel.diagram.event.classes.ControllerEventChange;
import controller.view.panel.diagram.event.classes.ControllerEventEdit;
import controller.view.panel.diagram.event.classes.ControllerEventGroup;
import controller.view.panel.diagram.event.classes.ControllerEventMove;
import controller.view.panel.diagram.event.classes.ControllerEventResize;
import controller.view.panel.diagram.event.classes.ControllerEventSelect;
import controller.view.panel.diagram.types.ControllerPanelClassDiagram;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.diagram.PanelDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelClassDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    controller.view.panel.diagram.types.ControllerPanelClassDiagram
 * @see    model.structural.diagram.ClassDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelClassDiagram extends PanelDiagram {
    private final ClassDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     */
    public PanelClassDiagram(ViewMenu view, ClassDiagram diagram) {
        super(view, diagram);
        this.diagram    = diagram;
        this.controller = new ControllerPanelClassDiagram(this);
        this.initComponents();
        this.addComponents();
        this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addOperationsPanel();
        this.addModelingPanel();
        this.addControllers();
    }
    
    @Override
    public void addOperationsPanel() {
        JPanel  panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.LEFT));
                panel.add(this.createButton("clickButton",       "", "Select",          "click.png"));
                panel.add(this.createButton("packageButton",     "", "New Package",     "diagram/classes/package.png"));
                panel.add(this.createButton("classButton",       "", "New Class",       "diagram/classes/class.png"));
                panel.add(this.createButton("interfaceButton",   "", "New Interface",   "diagram/classes/interface.png"));
                panel.add(this.createButton("variabilityButton", "", "New Variability", "variability.png"));
                panel.add(this.createButton("editButton",        "", "Edit",            "edit.png"));
                panel.add(this.createButton("deleteButton",      "", "Delete",          "delete.png"));
                panel.add(this.createComboBox("associationComboBox", this.getAssociationItems(), 50));
       this.add(panel);
       this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public Object[] getAssociationItems() {
        Object[] items  = {
            this.getAssociationImage("classes/association"),
            this.getAssociationImage("classes/directed-association"),
            this.getAssociationImage("classes/aggregation"),
            this.getAssociationImage("classes/directed-aggregation"),
            this.getAssociationImage("classes/composition"),
            this.getAssociationImage("classes/directed-composition"),
            this.getAssociationImage("generalization"),
            this.getAssociationImage("classes/realization"),
            this.getAssociationImage("dependency"),
            this.getAssociationImage("requires"),
            this.getAssociationImage("mutex")};
        return   items;
    }
    
    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getPackageButton().setBackground(this.getDefaultColor());
        this.getClassButton().setBackground(this.getDefaultColor());
        this.getInterfaceButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public void addElements() {
        this.addPackages();
        this.addClasses();
        this.addInterfaces();
    }
    
    /**
     * Method responsible for adding the Diagram Packages.
     */
    private void addPackages() {
        for (PackageUML packageUML : this.diagram.getPackagesList()) {
            if (packageUML.getParent() == null)
                this.addPackage(this.parent, packageUML);
        }
    }
    
    private void addPackage(Object parent, PackageUML packageUML) {
        this.graph.getStylesheet().putCellStyle(packageUML.getStyleLabel(), packageUML.getStyle());
        mxCell   vertex  = (mxCell) this.graph.insertVertex(parent, packageUML.getId(), "", packageUML.getPosition().x, packageUML.getPosition().y, packageUML.getSize().x, packageUML.getSize().y, packageUML.getStyleLabel());
                 vertex.setConnectable(false);
        this.insert(vertex, packageUML);
            this.addPackages(packageUML, vertex);
            this.addEntities(packageUML, vertex);
        this.identifiers.put(vertex, packageUML.getId());
        this.objects.put(packageUML.getId(), vertex);
    }
    
    /**
     * Method responsible for adding the Package Childs.
     * @param packageUML Package UML.
     * @param parent Parent Vertex.
     */
    private void addPackages(PackageUML packageUML, Object parent) {
        for (PackageUML current : packageUML.getPackagesList()) {
            this.addPackage(parent, current);
        }
    }
    
    /**
     * Method responsible for adding the Package Entities.
     * @param packageUML Package UML.
     * @param parent Parent Vertex.
     */
    private void addEntities(PackageUML packageUML, Object parent) {
        for (Entity entity : packageUML.getEntitiesList()) {
            if (entity instanceof ClassUML)
                this.addClass(parent, (ClassUML) entity);
            else if (entity instanceof InterfaceUML)
                this.addInterface(parent, (InterfaceUML) entity);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Classes.
     */
    private void addClasses() {
        for (ClassUML classUML : this.diagram.getClassList()) {
            if (classUML.getPackageUML() == null)
                this.addClass(this.parent, classUML);
        }
    }
    
    /**
     * Method responsible for adding the Class UML.
     * @param parent Parent Vertex.
     * @param classUML Class UML.
     */
    private void addClass(Object parent, ClassUML classUML) {
        this.graph.getStylesheet().putCellStyle(classUML.getStyleLabel(), classUML.getStyle());
        mxCell vertex = (mxCell) this.graph.insertVertex(parent, classUML.getId(), "", classUML.getPosition().x, classUML.getPosition().y, classUML.getSize().x, classUML.getSize().y, classUML.getStyleLabel());
               vertex.setConnectable(true);
        this.insert(vertex, classUML);
        this.identifiers.put(vertex, classUML.getId());
        this.objects.put(classUML.getId(), vertex);
    }
    
    /**
     * Method responsible for adding the Diagram Interfaces.
     */
    private void addInterfaces() {
        for (InterfaceUML interfaceUML : this.diagram.getInterfacesList()) {
            if (interfaceUML.getPackageUML() == null)
                this.addInterface(this.parent, interfaceUML);
        }
    }
    
    /**
     * Method responsible for adding the Interface UML.
     * @param parent Parent Vertex.
     * @param interfaceUML Interface UML.
     */
    private void addInterface(Object parent, InterfaceUML interfaceUML) {
        this.graph.getStylesheet().putCellStyle(interfaceUML.getStyleLabel(), interfaceUML.getStyle());
        mxCell vertex = (mxCell) this.graph.insertVertex(parent, interfaceUML.getId(), "", interfaceUML.getPosition().x, interfaceUML.getPosition().y, interfaceUML.getSize().x, interfaceUML.getSize().y, interfaceUML.getStyleLabel());
               vertex.setConnectable(true);
        this.insert(vertex, interfaceUML);
        this.identifiers.put(vertex, interfaceUML.getId());
        this.objects.put(interfaceUML.getId(), vertex);
    }
    
    /**
     * Method responsible for inserting a Package Cell.
     * @param vertex Parent Vertex.
     * @param packageUML Package UML.
     */
    private void insert(mxCell vertex, PackageUML packageUML) {
        this.graph.getStylesheet().putCellStyle("packageHeader", packageUML.getPackageStyle());
        this.graph.getStylesheet().putCellStyle("packageName",   packageUML.getNameStyle());
        
        mxCell head = (mxCell) this.graph.insertVertex(vertex, packageUML.getId() + "(name)",                    "",  0,  0, packageUML.getWidth() * 0.3,                          15, "packageHeader");
        mxCell body = (mxCell) this.graph.insertVertex(vertex, packageUML.getId() + "(body)",                    "",  0, 15, packageUML.getWidth(),       packageUML.getHeight() - 15, "packageHeader");
               this.addStereotypeCells(body, packageUML);
               this.addNameCell(body, packageUML);

               head.setConnectable(false);
               body.setConnectable(false);
        
        this.identifiers.put(head, packageUML.getId());
        this.identifiers.put(body, packageUML.getId());
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param packageUML Package UML.
     */
    private void addStereotypeCells(mxCell parent, PackageUML packageUML) {
        List<Stereotype>  stereotypes = this.diagram.getStereotypesList(packageUML);
        for (int i = 0; i < stereotypes.size(); i++) {
            Stereotype stereotype = stereotypes.get(i);
            this.graph.getStylesheet().putCellStyle("stereotypeStyle", stereotype.getStyle()); 
            mxCell     cell       = (mxCell) this.graph.insertVertex(parent, "LINK#" + packageUML.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (i * 21) + 5, packageUML.getWidth() - 10, 20, "stereotypeStyle");
                       cell.setConnectable(false);
                       cell.setTerminal(null, true);
                       cell.setId(stereotype.getId());
            this.identifiers.put(cell,         stereotype.getId());
            this.identifiers.put(cell.getId(), packageUML.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param packageUML Package UML.
     */
    private void addNameCell(mxCell parent, PackageUML packageUML) {
        this.graph.getStylesheet().putCellStyle("nameStyle", packageUML.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, packageUML.getId() + "(name)", packageUML.getName(), 5, packageUML.getNamePosition(), packageUML.getWidth() - 10, 25, "nameStyle");
               cell.setConnectable(false);
               cell.setId(packageUML.getId() + "(name)");
        this.identifiers.put(cell.getId(), packageUML.getId());
    }
    
    /**
     * Method responsible for inserting a Entity Cell.
     * @param vertex Parent Vertex.
     * @param entity Entity.
     */
    private void insert(mxCell vertex, Entity entity) {
        this.addStereotypeCells(vertex, entity);
        this.addInterfaceStereotypeCell(vertex, entity);
        this.addNameCell(vertex, entity);
        this.addLineCell(vertex, entity.getNamePosition() + 27, entity);
        this.addNewAttributeCell(vertex, entity);
        this.addAttributesCells(vertex, entity);
        this.addLineCell(vertex, entity.getMethodsPosition(), entity);
        this.addNewMethodCell(vertex, entity);
        this.addMethodsCells(vertex, entity);
    }

    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addStereotypeCells(mxCell parent, Entity entity) {
        List<Stereotype>  stereotypes = this.diagram.getStereotypesList(entity);
        for (int i = 0; i < stereotypes.size(); i++) {
            Stereotype stereotype = stereotypes.get(i);
            this.graph.getStylesheet().putCellStyle("stereotypeStyle", stereotype.getStyle()); 
            mxCell     cell       = (mxCell) this.graph.insertVertex(parent, "LINK#" + entity.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (i * 21) + 5, entity.getWidth() - 10, 20, "stereotypeStyle");
                       cell.setConnectable(false);
                       cell.setId(stereotype.getId());
            this.identifiers.put(cell,         stereotype.getId());
            this.identifiers.put(cell.getId(), stereotype.getId());
        }
    }
    
    /**
     * Method responsible for adding the Interface Stereotype Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addInterfaceStereotypeCell(mxCell parent, Entity entity) {
        if (entity instanceof InterfaceUML) {
            this.graph.getStylesheet().putCellStyle("stereotypeStyle",  entity.getStereotypeStyle());
            mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(interface)", "<<interface>>", 5, entity.getInterfacePosition(), entity.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
            this.identifiers.put(cell, entity.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNameCell(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("nameStyle", entity.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(name)", entity.getName(), 5, entity.getNamePosition(), entity.getWidth() - 10, 25, "nameStyle");
               cell.setConnectable(false);
               cell.setId(entity.getId() + "(name)");
        this.identifiers.put(cell.getId(), entity.getId());
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addLineCell(mxCell parent, Integer y, Entity entity) {
        this.graph.getStylesheet().putCellStyle("lineStyle", entity.getLineStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, null, "", 0, y, entity.getWidth(), 1, "lineStyle");
               cell.setConnectable(false);
        this.identifiers.put(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Add Attribute Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNewAttributeCell(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("newAttributeStyle", entity.getNewAttributeStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(newAttribute)", "", 5, entity.getAttributesPosition(), 5, 5, "newAttributeStyle");
               cell.setConnectable(false);
        this.identifiers.put(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Attributes Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addAttributesCells(mxCell parent, Entity entity) {
        List<AttributeUML>  attributes = entity.getAttributesList();
        for (int i = 0; i < attributes.size(); i++) {
            AttributeUML attribute = attributes.get(i);
            this.graph.getStylesheet().putCellStyle(attribute.getStyleLabel(), attribute.getStyle());
            mxCell       cell      = (mxCell) this.graph.insertVertex(parent, attribute.getId(), attribute.getCompleteSignature(), 5, entity.getAttributesPosition() + (i * 16) + 6, entity.getWidth() - 10, 15, attribute.getStyleLabel());
                         cell.setConnectable(false);
                         cell.setId(attribute.getId());
            this.identifiers.put(cell,         attribute.getId());
            this.identifiers.put(cell.getId(), attribute.getId());
        }
    }
    
    /**
     * Method responsible for adding the New Method Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNewMethodCell(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("newMethodStyle", entity.getNewMethodStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(newMethod)", "", 5, entity.getMethodsPosition() + 5, 10, 5, "newMethodStyle");
               cell.setConnectable(false);
        this.identifiers.put(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Methods Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addMethodsCells(mxCell parent, Entity entity) {
        List<MethodUML>     methods = entity.getMethodsList();
        for (int i = 0; i < methods.size(); i++) {
            MethodUML method = methods.get(i);
            this.graph.getStylesheet().putCellStyle(method.getStyleLabel(), method.getStyle());
            mxCell    cell   = (mxCell) this.graph.insertVertex(parent, method.getId(), method.getCompleteSignature(), 5, entity.getMethodsPosition() + 10 + (i * 16), entity.getWidth() - 10, 15, method.getStyleLabel());
                      cell.setConnectable(false);
                      cell.setId(method.getId());
            this.identifiers.put(cell,         method.getId());
            this.identifiers.put(cell.getId(), method.getId());
        }
    }
    
    @Override
    public void addAssociations() {
        List<Association>   associations = this.diagram.getAssociationsList();
        for (int i = 0; i < associations.size(); i++) {
            Association association = associations.get(i);
            this.graph.getStylesheet().putCellStyle(association.getStyleLabel(), association.getStyle());
            this.addNormalAssociation(association);
            if (association instanceof AssociationUML)
                this.addAssociationUML((AssociationUML) association); 
        }
    }
    
    /**
     * Method responsible for adding the Normal Association.
     * @param association Association.
     */
    private void addNormalAssociation(Association association) {
        List<mxPoint> points = new ArrayList<>();
                      points.add(new mxPoint(50, 50));
//                      points.add(new mxPoint(0, 0));
        mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, association.getId(), association.getTitle(), this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
        mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
//        System.out.println("Alt. Bou.: " + geometry.getAlternateBounds());
//        System.out.println("Old Geo 1: " + geometry.getPoint());
//        System.out.println("Old Geo 2: " + geometry.getPoints());
                   geometry.setPoints(points);
        ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);
//        System.out.println("New Geo 1: " + geometry.getPoint());
//        System.out.println("New Geo 2: " + geometry.getPoints());
        this.identifiers.put(edge, association.getId());
    }
    
    /**
     * Method responsible for adding the Association UML.
     * @param associationUML Association UML.
     */
    private void addAssociationUML(AssociationUML associationUML) {
        this.graph.getStylesheet().putCellStyle(associationUML.getCardinalityLabel(), associationUML.getCardinalityStyle());
        mxCell source = (mxCell) this.graph.insertVertex(this.parent, associationUML.getId() + "(source)", associationUML.getSourceLabel(), associationUML.getSourceX(), associationUML.getSourceY(), 30, 20, associationUML.getCardinalityLabel());
               source.setConnectable(false);
        mxCell target = (mxCell) this.graph.insertVertex(this.parent, associationUML.getId() + "(target)", associationUML.getTargetLabel(), associationUML.getTargetX(), associationUML.getTargetY(), 30, 20, associationUML.getCardinalityLabel());
               target.setConnectable(false);
        this.identifiers.put(source, associationUML.getId() + "(source)");
        this.identifiers.put(target, associationUML.getId() + "(target)");
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setAssociationStyle(false);
                break;
            case 1:
                this.setAssociationStyle(true);
                break;
            case 2:
                this.setAggregationStyle(false);
                break;
            case 3:
                this.setAggregationStyle(true);
                break;
            case 4:
                this.setCompositionStyle(false);
                break;
            case 5:
                this.setCompositionStyle(true);
                break;
            case 6:
                this.setGeneralizationStyle();
                break;
            case 7:
                this.setRealizationStyle();
                break;
            case 8:
                this.setDependencyStyle();
                break;
            case  9:
            case 10:
                this.setRealizationStyle();
                break;
            default:
                this.setAssociationStyle(false);    
                break;
        }
    }
    
    /**
     * Method responsible for setting the Association Style.
     * @param direction Direction Flag.
     */
    private void setAssociationStyle(boolean direction) {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    /**
     * Method responsible for setting the Aggregation Style.
     * @param direction Direction Flag.
     */
    private void setAggregationStyle(boolean direction) {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTSIZE,   "15");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_DIAMOND);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#A9A9A9");
    }
    
    /**
     * Method responsible for setting the Composition Style.
     * @param direction Direction Flag.
     */
    private void setCompositionStyle(boolean direction) {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTSIZE,   "15");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_DIAMOND);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    /**
     * Method responsible for setting the Realization Style.
     */
    private void setRealizationStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDSIZE,     "10");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_BLOCK);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    @Override
    public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationClass(this));
        this.component.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventSelect(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventGroup(this));
        
        this.component.addMouseListener((MouseListener) this.controller);
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Package Button.
     * @return Package Button.
     */
    public JButton getPackageButton() {
        return this.buttons.get("packageButton");
    }
    
    /**
     * Method responsible for returning the Class Button.
     * @return Class Button.
     */
    public JButton getClassButton() {
        return this.buttons.get("classButton");
    }
    
    /**
     * Method responsible for returning the Interface Button.
     * @return Interface Button.
     */
    public JButton getInterfaceButton() {
        return this.buttons.get("interfaceButton");
    }
}