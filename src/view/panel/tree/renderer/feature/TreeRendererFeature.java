package view.panel.tree.renderer.feature;

import javax.swing.JTree;
import model.structural.base.Element;
import model.structural.diagram.feature.base.Variability;
import view.panel.tree.renderer.TreeRenderer;

/**
 * <p>Class of View <b>TreeRendererFeature</b>.</p>
 * <p>Class responsible for defining the <b>Feature Tree Renderer</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-14
 * @see    view.panel.tree.renderer.TreeRenderer
 */
public class TreeRendererFeature extends TreeRenderer {
    
    /**
     * Default constructor method of Class.
     * @param tree Tree.
     */
    public TreeRendererFeature(JTree tree) {
        super(tree);
    }
    
    @Override
    public void setElementIcon(Element element) {
        super.setElementIcon(element);
        if (element instanceof Variability) {
            this.setText(element.getTitle());
            this.setToolTipText(element.getTitle());
            this.setIcon(this.getImage(element.getIcon()));
        }
    }
}