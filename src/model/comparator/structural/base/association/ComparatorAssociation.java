package model.comparator.structural.base.association;

import java.util.Comparator;
import model.structural.base.association.Association;

/**
 * <p>Class of Controller <b>ComparatorAssociation</b>.</p>
 * <p>Class responsible for defining the <b>Association Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    java.util.Comparator
 * @see    model.structural.base.association.Association
 */
public class ComparatorAssociation implements Comparator<Association> {

    @Override
    public int compare(Association associationA, Association associationB) {
        return associationA.getType().equals(associationB.getType()) ?
                associationA.getId().compareTo(associationB.getId()) :
                associationA.getType().compareTo(associationB.getType());
    }
}
