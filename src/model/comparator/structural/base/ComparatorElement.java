package model.comparator.structural.base;

import java.util.Comparator;
import model.structural.base.Element;

/**
 * <p>Class of Controller <b>ComparatorElement</b>.</p>
 * <p>Class responsible for defining the <b>Element Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    java.util.Comparator
 * @see    model.structural.base.Element
 */
public class ComparatorElement implements Comparator<Element> {

    @Override
    public int compare(Element elementA, Element elementB) {
        return elementA.getType().equals(elementB.getType())  ?
               elementA.getName().compareTo(elementB.getName()) :
               elementA.getType().compareTo(elementB.getType());
    }
}
