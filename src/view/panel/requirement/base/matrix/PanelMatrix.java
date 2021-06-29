package view.panel.requirement.base.matrix;

import controller.view.panel.requirement.base.matrix.ControllerPanelMatrix;
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
 * @see    controller.view.panel.requirement.base.matrix.ControllerPanelMatrix
 * @see    view.panel.requirement.PanelRequirement
 */
public final class PanelMatrix extends PanelRequirement {
    private final Diagram diagram;
    private final List requirements;
    private Integer index;
    
    /**
     * Default constructor method of Class.
     * @param panel View Requirement.
     * @param diagram Diagram.
     * @param requirements Requirements List.
     */
    public PanelMatrix(PanelRequirementMatrix panel, Diagram diagram, List requirements) {
        super(panel.getView());
        this.diagram      = diagram;
        this.requirements = requirements;
        this.controller   = new ControllerPanelMatrix(this);
        setDefaultProperties();
        addComponents();
        
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        addMatrix();
    }
    
    /**
     * Method responsible for adding the Matrix.
     */
    private void addMatrix() {
        addMatrixHeader();
        addMatrixBody();
    }
    
    /**
     * Method responsible for adding the Matrix Header.
     */
    private void addMatrixHeader() {
        int count = 1;
        add(createLabel(""), createConstraints(1, 1, 0, 0));
        for (Element element : getElements())
            add(createLabel(element.getHTMLCode()), createConstraints(1, 1, count++, 0));
    }
    
    /**
     * Method responsible for adding the Matrix Body.
     */
    private void addMatrixBody() {
        index = 1;
        for (Requirement requirement : getRequirements()) {
            int count = 0;
            add(super.createLabel(requirement.getCode()), createConstraints(1, 1, count++, index));
            for (Element element : getElements())
                add(createElementCheckBox(requirement, element), createConstraints(1, 1, count++, index));
            index++;
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
    
    /**
     * Method responsible for returning the Elements List.
     * @return Elements List.
     */
    private List<Element> getElements() {
        if (diagram != null)
            return diagram.getDefaultElements();
        return getProject().getDefaultElements();
    }
    
    /**
     * Method responsible for returning the Requirements List.
     * @return Requirements List.
     */
    private List<Requirement> getRequirements() {
        return requirements;
    }
    
    /**
     * Method responsible for returning a Element Check Box.
     * @param requirement Requirement.
     * @param element Variant.
     * @return New Element Check Box.
     */
    public JCheckBox createElementCheckBox(Requirement requirement, Element element) {
        String    id       = "checkBox" + requirement.getId() + element.getId();
        JCheckBox checkBox = createCheckBox(id, "", true);
                  checkBox.setName(requirement.getId() + "|" + element.getId());
                  checkBox.setSelected(requirement.contains(element));
                  checkBox.setEnabled(true);
        return    checkBox;
    }
    
}