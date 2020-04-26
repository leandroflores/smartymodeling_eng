package view.panel.requirement.base.matrix;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.requirement.Requirement;
import view.panel.requirement.PanelRequirement;
import view.panel.requirement.base.PanelRequirementMatrix;

/**
 * <p>Class of View <b>PanelMatrix</b>.</p> 
 * <p>Class responsible for defining the <b>Matrix Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-26
 * @see    view.panel.requirement.PanelRequirement
 */
public final class PanelMatrix extends PanelRequirement {
    private Requirement requirement_;
    private Diagram diagram;
    private Integer columns;
    private Integer index;
    
    /**
     * Default constructor method of Class.
     * @param panel View Requirement.
     */
    public PanelMatrix(PanelRequirementMatrix panel) {
        super(panel.getView());
//        this.controller = new ControllerPanelBaseArtifacts(this);
        this.setDefaultProperties();
        this.addComponents();
        
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.addMatrix();
    }
    
    /**
     * Method responsible for adding the Matrix.
     */
    private void addMatrix() {
        List<Element>  list = this.getElements();
        this.columns = list.size();
        this.addHeaderMatrix(list);
        this.index   = 1;
        int count    = 0;
        for (Requirement requirement : this.getProject().getRequirementsList()) {
            this.add(super.createLabel(requirement.getCode()), this.createConstraints(1, 1, count++, this.index));
            for (Element element : list)
                this.add(this.createElementCheckBox(requirement, element), this.createConstraints(1, 1, count++, this.index));
            this.index++;
        }
    }
    
    @Override
    protected JLabel createLabel(String text) {
        JLabel label = super.createLabel(text);
               label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               label.setMinimumSize(new Dimension(20, 250));
               label.setPreferredSize(new Dimension(20, 250));
               label.setMaximumSize(new Dimension(20, 250));
               label.setVerticalAlignment(JLabel.BOTTOM);
        return label;
    }
    
    private void addHeaderMatrix(List<Element> list) {
        this.add(this.createLabel(""), this.createConstraints(1, 1, 0, 0));
        int count = 1;
        for (Element element : list)
            this.add(this.createLabel(element.getHTMLCode()), this.createConstraints(1, 1, count++, 0));
    }
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    private List<Element> getElements() {
        if (this.diagram != null)
            return this.diagram.getDefaultElements();
        return this.getProject().getDefaultElements();
    }
    
    /**
     * Method responsible for returning a Element Check Box.
     * @param requirement Requirement.
     * @param element Variant.
     * @return New Element Check Box.
     */
    public JCheckBox createElementCheckBox(Requirement requirement, Element element) {
        String    id       = "checkBox" + requirement.getId() + element.getId();
        JCheckBox checkBox = this.createCheckBox(id, "", true);
                  checkBox.setName(requirement.getId() + "|" + element.getId());
                  checkBox.setSelected(requirement.contains(element));
                  checkBox.setEnabled(false);
        return    checkBox;
    }
    
}