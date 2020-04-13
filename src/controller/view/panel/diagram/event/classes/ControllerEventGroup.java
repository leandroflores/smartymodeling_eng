package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventGroup</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Grouping Panel Modeling</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  10/06/2019
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventGroup extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventGroup(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        mxCell     cell    = (mxCell) this.panel.getGraph().getSelectionCell();
        Element    element = this.getElement(cell);
        PackageUML parent  = this.getParent(this.getId(this.getCellId(cell.getParent())));
        if (this.getId(this.getCellId(cell.getParent())).trim().equals("1"))
            this.updateDefaultParent(element);
        else if (parent != null)
            this.updateParent(parent, element);
    }
    
    /**
     * Method responsible for returning the Element Selected.
     * @param  cell Graph Cell.
     * @return Element Selected.
     */
    private Element getElement(mxCell cell) {
        Element element = this.panel.getDiagram().getElement(this.getCellId(cell));
        if (element != null) {
            PackageUML parent = this.getParent(this.getId(this.getCellId(cell.getParent())));
            Integer    localX = element.getGlobalX() - this.getGlobalX(parent);
            Integer    localY = element.getGlobalY() - this.getGlobalY(parent);
            if (localX < 0)
                localX = 0;
            if (localY < 0)
                localY = 0;
            element.setPosition(localX, localY);
//            element.setPosition((int) cell.getGeometry().getX(), (int) cell.getGeometry().getY());
        }
        return  element;
    }
    
    public int getGlobalX(Element element) {
        if (element == null)
            return 0;
        if (element instanceof PackageUML) {
            if (((PackageUML) element).getParent() == null)
                return element.getX();
            else
                return element.getX() + this.getGlobalX(((PackageUML) element).getParent());
        }else {
            if (((Entity) element).getPackageUML() == null)
                return element.getX();
            else
                return element.getX() + this.getGlobalX(((Entity) element).getPackageUML());
        }
    }
    
    public int getGlobalY(Element element) {
        if (element == null)
            return 0;
        if (element instanceof PackageUML) {
            if (((PackageUML) element).getParent() == null)
                return element.getY();
            else
                return element.getY() + this.getGlobalY(((PackageUML) element).getParent());
        }else {
            if (((Entity) element).getPackageUML() == null)
                return element.getY();
            else
                return element.getY() + this.getGlobalY(((Entity) element).getPackageUML());
        }
    }
    
    /**
     * Method responsible for returning the Parent Object.
     * @param  parentId Parent Id.
     * @return Parent Package.
     */
    private PackageUML getParent(String parentId) {
        Element parent = this.panel.getDiagram().getElement(parentId);
        if (parent instanceof PackageUML)
            return (PackageUML) parent;
        return null;
    }
    
    /**
     * Method responsible for updating the Default Parent.
     * @param element Element.
     */
    private void updateDefaultParent(Element element) {
        if (element instanceof Entity)
            ((Entity) element).resetPackageUML();
        else if (element instanceof PackageUML)
            ((PackageUML) element).resetParent();
        this.panel.getViewMenu().setSave(false);
        this.panel.getViewMenu().update();
        this.panel.updateGraph();
    }
    
    /**
     * Method responsible for updating the Default Parent.
     * @param element Element.
     */
    private void updateParent(PackageUML parent, Element element) {
        if (element instanceof Entity)
            this.updateParent(parent, (Entity) element);
        else if (element instanceof PackageUML)
            this.updateParent(parent, (PackageUML) element);
        this.panel.getViewMenu().setSave(false);
        this.panel.getViewMenu().update();
        this.panel.updateGraph();
    }
    
    /**
     * Method responsible for updating the Parent Entity.
     * @param parent Parent Package.
     * @param entity Entity.
     */
    private void updateParent(PackageUML parent, Entity entity) {
        if (parent != entity.getPackageUML())
            entity.changePackageUML(parent);
    }
    
    /**
     * Method responsible for updating the Parent Package.
     * @param parent Parent Package.
     * @param packageUML Package UML.
     */
    private void updateParent(PackageUML parent, PackageUML packageUML) {
        if (parent != packageUML.getParent())
            packageUML.changePackageUML(parent);
    }
    
    /**
     * Method responsible for returning the Id.
     * @param  id Original String.
     * @return Id.
     */
    private String getId(String id) {
        if (id.contains("("))
            return id.substring(0, id.indexOf("("));
        return id;
    }
    
    /**
     * Method responsible for returning the Cell Id.
     * @param  cell Graph Cell.
     * @return Cell Id.
     */
    private String getCellId(Object cell) {
        if ((cell != null) && (cell instanceof mxCell))
            return ((mxCell) cell).getId();
        return "";
    }
}