package controller.view.panel.diagram.event.classes;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Events</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventMove(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.panel.getIdentifiers().get(cell);
        if (cell != null)
            this.move(id, event);
    }
    
    /**
     * Method responsible for moving the Selected Element.
     * @param object Graph Object.
     * @param id Element Id.
     * @param event Event.
     */
    private void move(String id, mxEventObject event) {
        if (this.panel.getDiagram().getElement(id) != null) 
            this.move(this.panel.getDiagram().getElement(id), event);
        else if (id != null)
            this.moveCardinality(id, event);
    }
    
    /**
     * Method responsible for moving the Element.
     * @param element Element.
     * @param event Event.
     */
    private void move(Element element, mxEventObject event) {
        if (element instanceof PackageUML)
            this.move((PackageUML) element, event);
        else if (element instanceof Entity)
            this.move((Entity) element, event);
    }
    
    /**
     * Method responsible for moving the Package UML.
     * @param packageUML Package UML.
     * @param event Object Event.
     */
    private void move(PackageUML packageUML, mxEventObject event) {
        Integer dx = this.getX(event);
        Integer dy = this.getY(event);
                packageUML.updateGlobalX(dx);
                packageUML.updateGlobalY(dy);
                packageUML.updateGlobal(dx, dy);
    }
    
    /**
     * Method responsible for moving the Entity.
     * @param entity Entity.
     * @param event Object Event.
     */
    private void move(Entity entity, mxEventObject event) {
        Integer dx = this.getX(event);
        Integer dy = this.getY(event);
                entity.updateGlobalX(dx);
                entity.updateGlobalY(dy);
                this.panel.getDiagram().dx(entity, dx);
                this.panel.getDiagram().dy(entity, dy);
    }
    
    /**
     * Method responsible for changing the Cardinality.
     * @param id Association Id.
     * @param event Event.
     */
    private void moveCardinality(String id, mxEventObject event) {
        if (id.endsWith("(source)"))
            this.moveSourceCardinality(id, event);
        else if (id.endsWith("(target)"))
            this.moveTargetCardinality(id, event);
    }
    
    /**
     * Method responsible for changing the Source Cardinality.
     * @param id Association Id.
     * @param event Event.
     */
    private void moveSourceCardinality(String id, mxEventObject event) {
        AssociationUML associationUML = (AssociationUML) this.panel.getDiagram().getAssociation(id.substring(0, id.indexOf("(")));
                       associationUML.dxSource(this.getX(event));
                       associationUML.dySource(this.getY(event));
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for changing the Target Cardinality.
     * @param id Association Id.
     * @param event Event.
     */
    private void moveTargetCardinality(String id, mxEventObject event) {
        AssociationUML associationUML = (AssociationUML) this.panel.getDiagram().getAssociation(id.substring(0, id.indexOf("(")));
                       associationUML.dxTarget(this.getX(event));
                       associationUML.dyTarget(this.getY(event));
        this.panel.getViewMenu().setSave(false);               
    }
    
    /**
     * Method responsible for returning the X Value.
     * @param  event Object Event.
     * @return X Value.
     */
    private Integer getX(mxEventObject event) {
        return ((Double) event.getProperty("dx")).intValue();
    }
    
    /**
     * Method responsible for returning the Y Value.
     * @param  event Object Event.
     * @return Y Value.
     */
    private Integer getY(mxEventObject event) {
        return ((Double) event.getProperty("dy")).intValue();
    }
}