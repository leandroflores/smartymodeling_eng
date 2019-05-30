package view.edit.panel.stereotype;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JTextField;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import view.Panel;

/**
 * <p>Class of View <b>PanelProject</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Stereotypes</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    view.Panel
 */
public final class PanelStereotype extends Panel {
    private final Project project;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element Element.
     */
    public PanelStereotype(Project project, Element element) {
        this.project = project;
        this.element = element;
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(400, 200));
    }
    
    @Override
    protected void addComponents() {
        this.addLines(1);
//        List<Link> filter = this.project.filterLinksByElement(this.element);
        List<Stereotype> filter = this.project.getStereotypesList();
        for (int i = 0; i < filter.size(); i++) {
            this.add(this.createLabel(filter.get(i).toString()));
            this.addLines(1);
        }
    }
    
    /**
     * Method responsible for returning Path Text Field.
     * @return Path Text Field.
     */
    public JTextField getPathTextField() {
        return this.textFields.get("pathTextField");
    }
    
    /**
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
}