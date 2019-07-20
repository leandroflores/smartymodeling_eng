package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventEdit</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Editing Events</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventEdit extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventEdit(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        if (this.panel.getGraph().getSelectionCell() != null) {
            Object cell = this.panel.getGraph().getSelectionCell();
            String id   = this.getId(cell);
            this.edit(cell, id);
        }
    }
    
    /**
     * Method responsible for editing the Selected Element.
     * @param element Selected Element.
     * @param id Element Id.
     */
    private void edit(Object element, String id) {
        if      (this.panel.getDiagram().getElement(id) instanceof AttributeUML)
            this.edit(element, (AttributeUML) this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) instanceof MethodUML)
            this.edit(element,    (MethodUML)    this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) != null)
            this.edit(element, (Element)    this.panel.getDiagram().getElement(id));
    }
    
    /**
     * Method responsible for editing the Attribute UML.
     * @param object Graph Object.
     * @param attribute Attribute UML.
     */
    private void edit(Object object, AttributeUML attribute) {
        mxCell cell = (mxCell) object;
               cell.setValue(attribute.getSignature());
    }
    
    /**
     * Method responsible for editing the Method UML.
     * @param object Graph Object.
     * @param method Method UML.
     */
    private void edit(Object object, MethodUML method) {
        mxCell cell = (mxCell) object;
               cell.setValue(method.getTitle());
    }
    
    /**
     * Method responsible for editing the Element.
     * @param object Graph Object.
     * @param element Element.
     */
    private void edit(Object object, Element element) {
        String       id    = element.getId() + "(name)";
        mxGraphModel model = (mxGraphModel) this.panel.getGraph().getModel();
        mxCell       cell  = (mxCell) model.getCell(id);
                     cell.setValue(element.getName());
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (this.panel.getIdentifiers().get(cell) == null) {
            if (cell instanceof mxCell)
                return this.getId(((mxCell) cell).getParent());
        }
        return this.panel.getIdentifiers().get(cell);
    }
}