package model.comparator.structural.base;

import java.util.Comparator;
import model.structural.base.Stereotype;

/**
 * <p>Class of Controller <b>ComparatorStereotype</b>.</p>
 * <p>Class responsible for defining the <b>Stereotype Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    java.util.Comparator
 * @see    model.structural.base.Stereotype
 */
public class ComparatorStereotype implements Comparator<Stereotype> {

    @Override
    public int compare(Stereotype stereotypeA, Stereotype stereotypeB) {
        return stereotypeA.getName().compareTo(stereotypeB.getName());
    }
}
