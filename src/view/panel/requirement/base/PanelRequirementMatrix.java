package view.panel.requirement.base;

import controller.view.panel.requirement.base.ControllerPanelRequirementMatrix;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
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
 * @see    controller.view.panel.requirement.base.ControllerPanelRequirementMatrix
 * @see    view.panel.requirement.PanelRequirement
 */
public final class PanelRequirementMatrix extends PanelRequirement {
    private Requirement requirement;
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Requirement Matrix.
     */
    public PanelRequirementMatrix(ViewRequirementMatrix view) {
        super(view);
        this.controller = new ControllerPanelRequirementMatrix(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        this.addFilters();
        this.addMatrix();
    }
    
    /**
     * Method responsible for adding the Filters.
     */
    private void addFilters() {
        this.add(this.createLabel("Requirement: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("requirementComboBox", new ControllerRequirement(this.getProject()).getRequirementsTarget(), 400), this.createConstraints(4, 1, 1, 1));
        
        
        this.add(this.createLabel("Diagram: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.createComboBox("diagramComboBox", new ControllerProject(this.getProject()).getProjectTargets(), 400), this.createConstraints(4, 1, 1, 2));
    }
    
    /**
     * Method responsible for adding the Matrix.
     */
    private void addMatrix() {
        this.addPanel("panelMatrix", new PanelMatrix(this, this.getSelectedDiagram(), this.getRequirements()));
        this.createScrollPane("scrollPanelMatrix",  this.getPanelMatrix());
        this.getScrollPane("scrollPanelMatrix").setMinimumSize(new Dimension(540, 360));
        this.getScrollPane("scrollPanelMatrix").setPreferredSize(new Dimension(540, 360));
        this.add(this.getScrollPane("scrollPanelMatrix"), this.createConstraints(5, 15, 0, 3));
    }
    
    /**
     * Method responsible for updating the Matrix.
     */
    public void updateMatrix() {
        this.getScrollPane("scrollPanelMatrix").setViewportView(new PanelMatrix(this, this.getSelectedDiagram(), this.getRequirements()));
        this.getScrollPane("scrollPanelMatrix").setMinimumSize(new Dimension(540, 360));
        this.getScrollPane("scrollPanelMatrix").setPreferredSize(new Dimension(540, 360));
    }
    
    /**
     * Method responsible for returning the Requirements List.
     * @return Requirements List.
     */
    private List<Requirement> getRequirements() {
        return this.getRequirementComboBox().getSelectedItem() instanceof Requirement ?
               this.getList((Requirement) this.getRequirementComboBox().getSelectedItem()) :
               this.getProject().getRequirementsList();
    }
    
    /**
     * Method responsible for returning the Requirement List.
     * @param  requirement Selected Requirement.
     * @return Requirement List.
     */
    private List<Requirement> getList(Requirement requirement) {
        List   list = new ArrayList();
               list.add(requirement);
        return list;
    }
    
    /**
     * Method responsible for returning the Selected Diagram.
     * @return Selected Diagram.
     */
    private Diagram getSelectedDiagram() {
        return this.getDiagramComboBox().getSelectedItem() instanceof Diagram ?
               (Diagram) this.getDiagramComboBox().getSelectedItem() : null;
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