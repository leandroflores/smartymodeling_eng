package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventEdit</b>.</p>
 * <p>Class responsible for defining the <b>Edit Events</b> in <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
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
        if (getPanel().getGraph().getSelectionCell() != null) {
            Object cell = getPanel().getGraph().getSelectionCell();
            String id   = getId(cell);
            edit(cell, id);
        }
    }
    
    /**
     * Method responsible for editing the Selected Element.
     * @param element Selected Element.
     * @param id Element Id.
     */
    private void edit(Object element, String id) {
        if (getDiagram().getElement(id) instanceof AttributeUML)
            edit(element, (AttributeUML) getDiagram().getElement(id));
        else if (getDiagram().getElement(id) instanceof MethodUML)
            edit(element, (MethodUML) getDiagram().getElement(id));
        else if (getDiagram().getElement(id) != null)
            edit(element, (Element) getDiagram().getElement(id));
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
        mxGraphModel model = (mxGraphModel) getPanel().getGraph().getModel();
        mxCell       cell  = (mxCell) model.getCell(id);
                     cell.setValue(element.getName());
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (getPanel().getIdentifiers().get(cell) == null) {
            if (cell instanceof mxCell)
                return getId(((mxCell) cell).getParent());
        }
        return getPanel().getIdentifiers().get(cell);
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return getPanel().getDiagram();
    }
    
    /**
     * Method responsible for returning the Panel Class Diagram.
     * @return Panel Class Diagram.
     */
    public PanelClassDiagram getPanel() {
        return panel;
    }
}