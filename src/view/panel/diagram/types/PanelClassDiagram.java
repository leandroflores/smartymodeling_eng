package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.event.classs.ControllerEventEdit;
import controller.view.panel.diagram.types.ControllerPanelClassDiagram;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.Entity;
import model.structural.diagram.classs.association.AssociationUML;
import model.structural.diagram.classs.base.AttributeUML;
import model.structural.diagram.classs.base.ClassUML;
import model.structural.diagram.classs.base.InterfaceUML;
import model.structural.diagram.classs.base.MethodUML;
import model.structural.diagram.classs.base.PackageUML;
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
    
    /**
     * Metodo responsavel por adicionar os Componentes na View Painel.
     */
    @Override
    public void addComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addOperationsPanel();
        this.addModelingPanel();
        
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
//        this.componente.addListener(mxEvent.LABEL_CHANGED, new ControllerEventoAtualizar(this));
//        this.componente.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventoSelecionar(this));
//        this.componente.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventoMovimento(this));
//        this.componente.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventoPacote(this));
//        this.componente.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventoAssociacaoClasses(this));
//        this.componente.getGraph().addListener(mxEvent.GROUP_CELLS, new ControllerEventoPacote(this));
    }
    
    @Override
    public void addOperationsPanel() {
        JPanel panel = new JPanel();
               panel.add(this.createButton("clickButton",        "", "Select",          "click.png"));
               panel.add(this.createButton("packageButton",      "", "New Package",     "diagram/classs/package.png"));
               panel.add(this.createButton("classeButton",       "", "New Class",       "diagram/classs/class.png"));
               panel.add(this.createButton("interfaceButton",    "", "New Interface",   "diagram/classs/interface.png"));
               panel.add(this.createButton("variabilityButton",  "", "New Variability", "variability.png"));
               panel.add(this.createButton("editButton",         "", "Edit",            "edit.png"));
               panel.add(this.createButton("deleteButton",       "", "Delete",          "delete.png"));
               panel.add(this.createComboBox("associationComboBox", this.getAssociationItems(), 50));
       this.add(panel);
       this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public Object[] getAssociationItems() {
        Object[] items  = {
            this.getAssociationImage("classs/association"),
            this.getAssociationImage("classs/directed-association"),
            this.getAssociationImage("classs/aggregation"),
            this.getAssociationImage("classs/directed-aggregation"),
            this.getAssociationImage("classs/composition"),
            this.getAssociationImage("classs/directed-composition"),
            this.getAssociationImage("generalization"),
            this.getAssociationImage("classs/realization"),
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
            this.graph.getStylesheet().putCellStyle(packageUML.getStyleLabel(), packageUML.getStyle());
            mxCell   vertex  = (mxCell) this.graph.insertVertex(this.parent, packageUML.getId(), "", packageUML.getPosition().x, packageUML.getPosition().y, packageUML.getSize().x, packageUML.getSize().y, packageUML.getStyleLabel());
                     vertex.setConnectable(false);
//            this.insert(vertex, packageUML);
            this.identifiers.put(vertex, packageUML.getId());
            this.objects.put(packageUML.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Classes.
     */
    private void addClasses() {
        for (ClassUML classUML : this.diagram.getClassList()) {
            this.graph.getStylesheet().putCellStyle(classUML.getStyleLabel(), classUML.getStyle());
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, classUML.getId(), "", classUML.getPosition().x, classUML.getPosition().y, classUML.getSize().x, classUML.getSize().y, classUML.getStyleLabel());
                   vertex.setConnectable(true);
            this.insert(vertex, classUML);
            this.identifiers.put(vertex, classUML.getId());
            this.objects.put(classUML.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Interfaces.
     */
    private void addInterfaces() {
        for (InterfaceUML interfaceUML : this.diagram.getInterfacesList()) {
            this.graph.getStylesheet().putCellStyle(interfaceUML.getStyleLabel(), interfaceUML.getStyle());
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, interfaceUML.getId(), "", interfaceUML.getPosition().x, interfaceUML.getPosition().y, interfaceUML.getSize().x, interfaceUML.getSize().y, interfaceUML.getStyleLabel());
                   vertex.setConnectable(true);
            this.insert(vertex, interfaceUML);
            this.identifiers.put(vertex, interfaceUML.getId());
            this.objects.put(interfaceUML.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for inserting a Package Cell.
     * @param vertex Parent Vertex.
     * @param packageUML Package UML.
     */
    private void insert(mxCell vertex, PackageUML packageUML) {
        this.graph.getStylesheet().putCellStyle("packageHeader", packageUML.getPackageStyle());
        this.graph.getStylesheet().putCellStyle("packageName",   packageUML.getNameStyle());
        
        mxCell head = (mxCell) this.graph.insertVertex(vertex, packageUML.getId() + "(name)",                    "",  0,  0, packageUML.getWidth() * 0.3,                     15,  "packageHeader");
        mxCell body = (mxCell) this.graph.insertVertex(vertex, null,                                             "",  0, 15, packageUML.getWidth(),       packageUML.getHeight(),  "packageHeader");
        mxCell name = (mxCell) this.graph.insertVertex(body,   null,                          packageUML.toString(),  5,  5, packageUML.getWidth()- 10,                       15,  "packageName");
               
               head.setConnectable(false);
               body.setConnectable(false);
               name.setConnectable(false);
        
        this.identifiers.put(head, packageUML.getId());
        this.identifiers.put(body, packageUML.getId());
        this.identifiers.put(name, packageUML.getId());
    }
    
    /**
     * Method responsible for inserting a Entity Cell.
     * @param vertex Parent Vertex.
     * @param entity Entity.
     */
    private void insert(mxCell vertex, Entity entity) {
        this.addStereotypeCells(vertex, entity);
//        this.addCelulaInterface(vertice, entidade);
        this.addNameCell(vertex, entity);
        this.addLineCell(vertex, 60, entity);
        this.addAddAttributeCell(vertex, entity);
        this.addAttributesCells(vertex, entity);
        this.addLineCell(vertex, entity.getAttributesPosition(), entity);
        this.addAddMethodCell(vertex, entity);
        this.addMethodsCells(vertex, entity);
    }

    /**
     * Method responsible for adding the Interface Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addInterfaceCell(mxCell parent, Entity entity) {
        if (entity instanceof InterfaceUML) {
            this.graph.getStylesheet().putCellStyle("stereotypeStyle",  entity.getStereotypeStyle());
            mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(interface)", "<<interface>>", 5, 6, entity.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
            this.identifiers.put(cell, entity.getId());
        }
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addStereotypeCells(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("stereotypeStyle",  entity.getStereotypeStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(stereotypo)", this.diagram.getStereotypesByElement(entity), 5, 6, entity.getWidth() - 10, 20, "stereotypeStyle");
               cell.setConnectable(false);
        this.identifiers.put(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNameCell(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("nameStyle", entity.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(name)", entity.getName(), 5, 28, entity.getWidth() - 10, 25, "nameStyle");
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
    private void addAddAttributeCell(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("addAttributeStyle", entity.getAddAttributeStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(addAttribute)", "", 5, 62, 5, 5, "addAttributeStyle");
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
            mxCell       cell      = (mxCell) this.graph.insertVertex(parent, attribute.getId(), attribute.getSignature(), 5, 70 + (i * 16), entity.getWidth() - 10, 15, attribute.getStyleLabel());
                         cell.setConnectable(false);
                         cell.setId(attribute.getId());
            this.identifiers.put(cell,         attribute.getId());
            this.identifiers.put(cell.getId(), attribute.getId());
        }
    }
    
    /**
     * Method responsible for adding the Add Method Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addAddMethodCell(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("addMethodStyle", entity.getAddMethodStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(addMethod)", "", 5, entity.getAttributesPosition() + 5, 10, 5, "addMethodStyle");
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
            mxCell    cell   = (mxCell) this.graph.insertVertex(parent, method.getId(), method.getCompleteSignature(), 5, entity.getAttributesPosition() + 10 + (i * 16), entity.getWidth() - 10, 15, method.getStyleLabel());
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
            if (association instanceof  AssociationUML) {
                this.addAssociationUML((AssociationUML) association);
            }else {
                Object edge = this.graph.insertEdge(this.parent, null, association.getTitle(), this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
                this.identifiers.put(edge, association.getId());
            }
        }
    }
    
    /**
     * Method responsible for adding the Association UML.
     * @param association Association UML.
     */
    private void addAssociationUML(AssociationUML association) {
        System.out.println(association.export());
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