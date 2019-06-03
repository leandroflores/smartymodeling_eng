package controller.view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
import model.structural.diagram.classs.base.AttributeUML;
import model.structural.diagram.classs.base.ClassUML;
import model.structural.diagram.classs.base.InterfaceUML;
import model.structural.diagram.classs.base.MethodUML;
import model.structural.diagram.classs.base.PackageUML;
import view.delete.ViewDeleteElement;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelClassDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerPanelClassDiagram extends ControllerPanelDiagram {
    private final PanelClassDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerPanelClassDiagram(PanelClassDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelDiagram.getPackageButton().equals(event.getSource()))
            this.setAddPackage();
        else if (this.panelDiagram.getClassButton().equals(event.getSource()))
            this.setAddClass();
        else if (this.panelDiagram.getInterfaceButton().equals(event.getSource()))
            this.setAddInterface();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
    }
    
    @Override
    public void mousePressed(MouseEvent evento) {
        switch (this.panelDiagram.getOperation()) {
            case "Package":
                this.addPackage(evento);
                break;
            case "Class":
                this.addClass(evento);
                break;
            case "Interface":
                this.addInterface(evento);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for setting Add Package Operation.
     */
    public void setAddPackage() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getPackageButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Package");
    }
    
    /**
     * Method responsible for setting Add Class Operation.
     */
    public void setAddClass() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getClassButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Class");
    }
    
    /**
     * Method responsible for setting Add Interface Operation.
     */
    public void setAddInterface() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getInterfaceButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Interface");
    }

    /**
     * Method responsible for adding a new Package UML.
     * @param event Mouse Event.
     */
    public void addPackage(MouseEvent event) {
//        System.out.println(this.painelDiagrama.getGrafo().getSelectionCell());
//        this.getPacote(evento);
        PackageUML packageUML = new PackageUML();
                   packageUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addPackage(packageUML);
                   packageUML.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Class UML.
     * @param event Mouse Event.
     */
    public void addClass(MouseEvent event) {
        ClassUML classUML = new ClassUML();
                 classUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addClass(classUML);
                 classUML.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Interface UML.
     * @param event Mouse Event.
     */
    public void addInterface(MouseEvent event) {
        InterfaceUML interfaceUML = new InterfaceUML();
                     interfaceUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addInterface(interfaceUML);
                     interfaceUML.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    @Override
    public void edit() {
        if (this.panelDiagram.getGraph() != null) {
            mxCell cell = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
            String id   = this.panelDiagram.getIdentifiers().get(cell);
            if (id != null)
                this.edit(cell, id);
//            else if (celula.getId().endsWith("(nome)"))
//                this.painelDiagrama.getComponente().startEditingAtCell(celula);
        }
//        this.painelDiagrama.atualizarDiagrama();
//        this.painelDiagrama.getViewMenu().update();
    }
    
    /**
     * Method responsible for editing the Element by Id.
     * @param cell Graph Cell.
     * @param id Element Id.
     */
    private void edit(mxCell cell, String id) {
        if      (this.panelDiagram.getDiagram().getElement(id) instanceof AttributeUML)
            this.panelDiagram.getComponent().startEditingAtCell(cell);
        else if (this.panelDiagram.getDiagram().getElement(id) instanceof MethodUML)
            this.panelDiagram.getComponent().startEditingAtCell(cell);
        else if (this.panelDiagram.getDiagram().getElement(id) != null)
            this.editElement(this.panelDiagram.getDiagram().getElement(id));
    }
    
    /**
     * Method responsible for editing the Element.
     * @param element Element.
     */
    private void editElement(Element element) {
        String       id    = element.getId() + "(name)";
        mxGraphModel model = (mxGraphModel) this.panelDiagram.getGraph().getModel();
        mxCell       cell  = (mxCell) model.getCell(id);
        this.panelDiagram.getComponent().startEditingAtCell(cell);
    }
    
    @Override
    public void delete() {
        if (this.panelDiagram.getGraph() != null) {
            mxCell cell = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
            String id   = this.panelDiagram.getIdentifiers().get(cell);
            if (id != null)
                this.delete(id);
        }
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
        this.panelDiagram.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for deleting the Element by Id.
     * @param id Element Id.
     */
    private void delete(String id) {
        if (this.panelDiagram.getDiagram().getElement(id)      instanceof AttributeUML)
            this.panelDiagram.getDiagram().removeAttribute((AttributeUML) this.panelDiagram.getDiagram().getElement(id));
        else if (this.panelDiagram.getDiagram().getElement(id) instanceof MethodUML)
            this.panelDiagram.getDiagram().removeMethod((MethodUML) this.panelDiagram.getDiagram().getElement(id));
        else if (this.panelDiagram.getDiagram().getElement(id)     != null)
            new ViewDeleteElement(this.panelDiagram.getViewMenu().getPanelModeling(), this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
        else if (this.panelDiagram.getDiagram().getAssociation(id) != null)
            this.panelDiagram.getDiagram().removeAssociation(this.panelDiagram.getDiagram().getAssociation(id));
    }
    
//    private PacoteUML getPacote(MouseEvent evento) {
//        if (this.painelDiagrama.getGrafo().getSelectionCell() != null) {
//            Object celula = this.painelDiagrama.getGrafo().getSelectionCell();
//            System.out.println(this.painelDiagrama.getObjetos());
//            System.out.println(celula);
//            System.out.println(this.painelDiagrama.getObjetos().get(celula));
//        }
//        return null;
//    }
}