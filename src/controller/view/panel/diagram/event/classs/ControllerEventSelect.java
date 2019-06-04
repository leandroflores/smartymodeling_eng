package controller.view.panel.diagram.event.classs;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.diagram.classs.Entity;
import model.structural.diagram.classs.base.AttributeUML;
import model.structural.diagram.classs.base.MethodUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventSelect</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Selecting Events</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventSelect extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventSelect(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject evento) {
        if (this.panel.getGraph().getSelectionCell() != null) {
            if (this.panel.getGraph().getSelectionCell() instanceof mxCell) {
                mxCell cell = (mxCell) this.panel.getGraph().getSelectionCell();
                String id   = this.panel.getIdentifiers().get(cell);
                if (cell.getId().endsWith("(addAttribute)"))
                    this.addAttribute(cell, (Entity) this.panel.getDiagram().getElement(id));
                else if (cell.getId().endsWith("(addMethod)"))
                    this.addMethod(cell, (Entity) this.panel.getDiagram().getElement(id));
            }
        }
    }
    
    /**
     * Method responsible for adding a Attribute to a Entity.
     * @param cell Graph Cell.
     * @param entity Entity.
     */
    private void addAttribute(mxCell cell, Entity entity) {
//        System.out.println("Add Attribute: " + this.panel.getDiagram().nextAttributeId());
        AttributeUML attribute = new AttributeUML();
                     this.panel.getDiagram().addAttribute(attribute);
                     attribute.setEntity(entity);
                     attribute.setTypeUML(this.panel.getDiagram().getObjectType());
                     entity.addAttribute(attribute);
                     attribute.setDefaultName();
        this.panel.updateDiagram();
        this.panel.getViewMenu().update();
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for adding a Method to a Entity.
     * @param cell Graph Cell.
     * @param entity Entity.
     */
    private void addMethod(mxCell cell, Entity entity) {
        MethodUML method = new MethodUML();
                  method.setId(this.panel.getDiagram().nextMethodId());
                  method.setEntity(entity);
                  method.setReturn(this.panel.getDiagram().getVoidType());
                  entity.addMethod(method);
                  this.panel.getDiagram().addMethod(method);
                  method.setDefaultName();
        this.panel.updateDiagram();
        this.panel.getViewMenu().update();
        this.panel.getViewMenu().setSave(false);
    }
}