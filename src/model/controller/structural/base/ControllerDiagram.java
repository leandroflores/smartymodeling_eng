package model.controller.structural.base;

import java.util.Arrays;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Controller <b>ControllerDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
 * @see    model.structural.base.Diagram
 */
public class ControllerDiagram {
    public static final String[] TYPES = {"Activity", "Class", "Components", "Sequence", "UseCases"};
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Diagram.
     */
    public ControllerDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for returning Elements Array.
     * @return Elements Array.
     */
    public String[] getElements() {
        List<Element> elements = this.diagram.getElementsList();
        String[]      array    = new String[elements.size()];
        for (int i = 0; i < elements.size(); i++)
                    array[i] = elements.get(i).getAbstract();
        Arrays.sort(array);
        return      array;
    }
    
    /**
     * Method responsible for returning the Default Elements Array.
     * @return Default Elements Array.
     */
    public Element[] getDefaultElements() {
        List      list  = this.diagram.getDefaultElementsList();
        Element[] array = new Element[list.size()];
        for (int i = 0; i < list.size(); i++)
                  array[i] = (Element) list.get(i);
        return    array;
    }
    
    /**
     * Method responsible for returning the Default Elements Array.
     * @return Default Elements Array.
     */
    public Element[] getAllElements() {
        List<Element> list  = this.diagram.getDefaultElementsList();
        Element[]     array = new Element[list.size()];
        for (int i = 0; i < list.size(); i++)
                    array[i] = list.get(i);
        Arrays.sort(array);
        return      array;
    }
}