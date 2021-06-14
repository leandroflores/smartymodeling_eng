package model.comparator.structural.base;

import java.util.Comparator;
import model.structural.base.Diagram;

/**
 * <p>Class of Controller <b>ComparatorDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    java.util.Comparator
 * @see    model.structural.base.Diagram
 */
public class ComparatorDiagram implements Comparator<Diagram> {

    @Override
    public int compare(Diagram diagramA, Diagram diagramB) {
        return diagramA.getType().equals(diagramB.getType())  ?
               diagramA.getName().compareTo(diagramB.getId()) :
               diagramA.getType().compareTo(diagramB.getType());
    }
}
