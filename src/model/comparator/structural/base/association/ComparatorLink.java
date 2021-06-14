package model.comparator.structural.base.association;

import java.util.Comparator;
import model.structural.base.association.Link;

/**
 * <p>Class of Controller <b>ComparatorLink</b>.</p>
 * <p>Class responsible for defining the <b>Link Comparator</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-19
 * @see    java.util.Comparator
 * @see    model.structural.base.association.Link
 */
public class ComparatorLink implements Comparator<Link> {

    @Override
    public int compare(Link linkA, Link linkB) {
        return linkA.getSignature().compareTo(linkB.getSignature());
    }
}
