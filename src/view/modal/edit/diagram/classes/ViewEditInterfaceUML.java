package view.modal.edit.diagram.classes;

import controller.view.modal.edit.diagram.classes.base.ControllerViewEditInterfaceUML;
import java.awt.Dimension;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.InterfaceUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.PanelBaseElement;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.edit.diagram.classes.base.PanelEditInterfaceUML;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditInterfaceUML</b>.</p>
 * <p>Class responsible for defining the <b>Interface UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.diagram.classes.base.ControllerViewEditInterfaceUML
 * @see    model.structural.diagram.classes.base.InterfaceUML
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditInterfaceUML extends ViewEdit {
    private final ClassDiagram diagram;
    private final InterfaceUML interface_;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Class Diagram.
     * @param interface_ Interface UML.
     */
    public ViewEditInterfaceUML(PanelModeling panel, ClassDiagram diagram, InterfaceUML interface_) {
        super(panel);
        this.diagram    = diagram;
        this.interface_ = interface_;
        this.controller = new ControllerViewEditInterfaceUML(this);
        this.title      = "Edit Interface Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditInterfaceUML();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Interface UML.
     */
    private void addPanelEditInterfaceUML() {
        this.addPanel("panelEditInterface", new PanelEditInterfaceUML(this.view, this.diagram, this.interface_));
        this.getPanel("panelEditInterface").setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanel("panelEditInterface"));
    }
    
    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseInterfaceUML() {
        return ((PanelEditInterfaceUML) this.getPanel("panelEditInterface")).getPanelBaseElement();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return ((PanelEditInterfaceUML) this.getPanel("panelEditInterface")).getPanelStereotype();
    }
}