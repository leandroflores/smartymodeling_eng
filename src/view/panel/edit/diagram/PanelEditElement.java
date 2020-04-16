package view.panel.edit.diagram;

import funct.FunctString;
import model.structural.base.Diagram;
import model.structural.base.Element;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditElement</b>.</p> 
 * <p>Class responsible for defining a <b>Element Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-16
 * @see    model.structural.base.Element
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditElement extends view.panel.edit.base.PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param element Element.
     */
    public PanelEditElement(ViewMenu view, Diagram diagram, Element element) {
        super(view, diagram, element);
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addComponents(this.getTitle());
    }
    
    /**
     * Method responsible for returning the Element Tab Title.
     * @return Element Tab Title.
     */
    private String getTitle() {
        return new FunctString().initUpperCase(this.getElement().getType());
    }
}