package model.comparator.structural.diagram.classes.base;

import java.util.Comparator;
import model.structural.diagram.classes.base.TypeUML;

/**
 * <p>Class of Controller <b>ComparatorTypeUML</b>.</p>
 * <p>Class responsible for defining the <b>Type UML Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    java.util.Comparator
 * @see    model.structural.diagram.classes.base.TypeUML
 */
public class ComparatorTypeUML implements Comparator<TypeUML> {

    @Override
    public int compare(TypeUML typeA, TypeUML typeB) {
        return typeA.getName().compareTo(typeB.getName());
    }
}
