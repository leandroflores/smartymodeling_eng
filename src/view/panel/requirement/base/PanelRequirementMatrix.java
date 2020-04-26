package view.panel.requirement.base;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import model.controller.structural.base.ControllerProject;
import model.controller.structural.base.requirement.ControllerRequirement;
import model.structural.base.Diagram;
import model.structural.base.requirement.Requirement;
import view.modal.requirement.base.ViewRequirementMatrix;
import view.panel.requirement.PanelRequirement;
import view.panel.requirement.base.matrix.PanelMatrix;

/**
 * <p>Class of View <b>PanelRequirementMatrix</b>.</p> 
 * <p>Class responsible for defining the <b>Requirement Matrix Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    view.panel.requirement.PanelRequirement
 */
public final class PanelRequirementMatrix extends PanelRequirement {
    private Requirement requirement;
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Requirement.
     */
    public PanelRequirementMatrix(ViewRequirementMatrix view) {
        super(view);
//        this.controller = new ControllerPanelBaseArtifacts(this);
        this.setDefaultProperties();
//        this.addHeader();
        this.addComponents();
//        this.addFooter();
//        this.setValues();
        
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.addFilters();
        this.addMatrix();
//        this.tabbedPane = new JTabbedPane();
//            this.addPanels();
//        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Filters.
     */
    private void addFilters() {
        this.add(this.createLabel("Requirement: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("requirementComboBox", new ControllerRequirement(this.getProject()).getRequirements(), 175), this.createConstraints(4, 1, 1, 1));
        
        this.add(this.createLabel("Diagram: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createComboBox("diagramComboBox", new ControllerProject(this.getProject()).getProjectTargets(), 175), this.createConstraints(4, 1, 1, 2));
    }
    
    private void addMatrix() {
        this.addPanel("panelMatrix", new PanelMatrix(this));
        this.createScrollPane("scrollPanelMatrix",  this.getPanelMatrix());
        this.getScrollPane("scrollPanelMatrix").setMinimumSize(new Dimension(350, 300));
        this.add(this.getScrollPane("scrollPanelMatrix"), this.createConstraints(5, 15, 0, 3));
//        this.tabbedPane.add("Measure", this.getScrollPane("scrollPanelBaseMeasure"));
//        System.out.println("Requirements: " + this.getProject().getRequirementsList());
    }
    
    /**
     * Method responsible for returning the Requirement Combo Box.
     * @return Requirement Combo Box.
     */
    public JComboBox getRequirementComboBox() {
        return this.getComboBox("requirementComboBox");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return this.getComboBox("diagramComboBox");
    }
    
    /**
     * Method responsible for returning the Panel Matrix.
     * @return Panel Matrix.
     */
    public PanelMatrix getPanelMatrix() {
        return (PanelMatrix) this.getPanel("panelMatrix");
    }
}