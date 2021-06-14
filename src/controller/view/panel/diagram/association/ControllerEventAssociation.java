package controller.view.panel.diagram.association;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Dependency;
import model.structural.base.association.Generalization;
import model.structural.base.variability.Mutex;
import model.structural.base.variability.Requires;
import view.main.structural.ViewMenu;
import view.modal.message.ViewError;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociation</b>.</p>
 * <p>Class responsible for controlling the <b>Association Events</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    com.mxgraph.util.mxEventSource
 * @see    model.structural.base.Diagram
 * @see    view.panel.diagram.PanelDiagram
 */
public abstract class ControllerEventAssociation extends mxEventSource implements mxEventSource.mxIEventListener {
    protected final PanelDiagram panel;
    protected final Diagram diagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventAssociation(PanelDiagram panel) {
        this.panel   = panel;
        this.diagram = panel.getDiagram();
    }

    @Override
    public void invoke(Object object, mxEventObject event) {
        Object element = event.getProperty("cell");
        if (element != null) {
            mxCell association = (mxCell) element;
            addAssociation(association);
            getPanel().updateGraph();
        }
    }
    
    /**
     * Method responsible for adding a Association to Diagram.
     * @param association Association.
     */
    public abstract void addAssociation(mxCell association);
    
    /**
     * Method responsible for creating a Association.
     * @param association Association.
     */
    public abstract void createAssociation(mxCell association);
    
    /**
     * Method responsible for checking the Association Elements.
     * @param  source Source Element.
     * @param  target Target Element.
     * @return Association checked.
     */
    protected boolean check(Element source, Element target) {
        return (source != null && target != null);
    }
    
    /**
     * Method responsible for checking if the Elements are distincts.
     * @param  source Source Element.
     * @param  target Target Element.
     * @return Elements are distincts.
     */
    protected boolean distinct(Element source, Element target) {
        return source.equals(target) == false;
    }
    
    /**
     * Method responsible for checking if the Elements Class are equals.
     * @param  source Source Element.
     * @param  target Target Element.
     * @return Element Class are equals.
     */
    protected boolean equalClass(Element source, Element target) {
        return source.getClass().equals(target.getClass());
    }
    
    /**
     * Method responsible for returning Source Element.
     * @param  association Association.
     * @return Source Element.
     */
    protected Element getSource(mxCell association) {
        return getElement(getPanel().getIdentifiers().get(association.getSource()));
    }
    
    /**
     * Method responsible for returning Target Element.
     * @param  association Association.
     * @return Target Element.
     */
    protected Element getTarget(mxCell association) {
        return getElement(getPanel().getIdentifiers().get(association.getTarget()));
    }
    
    /**
     * Method responsible for adding the Requires.
     * @param association Association.
     */
    protected void addRequires(mxCell association) {
        if (distinct(getSource(association), getTarget(association)))
            getDiagram().addRequires(new Requires(getSource(association), 
                                                  getTarget(association)));
    }
    
    /**
     * Method responsible for adding the Mutex.
     * @param association Association.
     */
    protected void addMutex(mxCell association) {
        if (distinct(getSource(association), getTarget(association)))
            getDiagram().addMutex(new Mutex(getSource(association), 
                                            getTarget(association)));
    }
    
    /**
     * Method responsible for adding the Dependency.
     * @param association Association.
     */
    protected void addDependency(mxCell association) {
        if (distinct(getSource(association), getTarget(association)))
            getDiagram().addDependency(new Dependency(getSource(association), 
                                                      getTarget(association)));
    }
    
    /**
     * Method responsible for checking if the Source is Unique.
     * @param  generalization New Generalization.
     * @return Source is Unique.
     */
    protected boolean checkSource(Generalization generalization) {
        Element super_ = getDiagram().getSuper(generalization.getSource());
        if (super_ != null) {
            String message = generalization.getSource().getName() + " is already has a generalization with " + super_.getName() + "!";
            new ViewError(getViewMenu(), message).setVisible(true);
            return false;
        }
        return true;
    }
   
    /**
     * Method responsible for checking Recursive Generalization.
     * @param  generalization New Generalization.
     * @return Generalization is Not Recursive.
     */
    protected boolean checkRecursive(Generalization generalization) {
        List<Element> supers = getDiagram().getSupers(generalization.getTarget());
        if (supers.contains(generalization.getSource())) {
            new ViewError(getViewMenu(), "Recursive generalization is not allowed!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for adding a Generalization.
     * @param association Association.
     */
    protected void addGeneralization(mxCell association) {
        Generalization generalization = createGeneralization(association);
        if (generalization != null && checkSource(generalization) && checkRecursive(generalization))
            getDiagram().addGeneralization(generalization);
    }
    
    /**
     * Method responsible for creating a Generalization.
     * @param  association Association.
     * @return Generalization.
     */
    private Generalization createGeneralization(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        if (equalClass(source, target) && distinct(source, target))
            return new Generalization(source, target);
        return null;
    }
    
    /**
     * Method responsible for returning the Element by Id.
     * @param  id Element Id.
     * @return Element found.
     */
    protected Element getElement(String id) {
        return getDiagram().getElement(id);
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return getPanel().getViewMenu();
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanel() {
        return panel;
    }
}