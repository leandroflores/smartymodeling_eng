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
        controller = new ControllerPanelRequirementMatrix(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        addFilters();
        addMatrix();
    }
    
    /**
     * Method responsible for adding the Filters.
     */
    private void addFilters() {
        add(createLabel("Requirement: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("requirement", new ControllerRequirement(getProject()).getRequirementsTarget(), 400), createConstraints(4, 1, 1, 1));
        
        
        add(createLabel("Diagram: "), createConstraints(1, 1, 0, 2));
        add(createComboBox("diagram", new ControllerProject(getProject()).getProjectTargets(), 400), createConstraints(4, 1, 1, 2));
    }
    
    /**
     * Method responsible for adding the Matrix.
     */
    private void addMatrix() {
        addPanel("matrix", new PanelMatrix(this, getSelectedDiagram(), getRequirements()));
        createScrollPane("matrix",  getPanelMatrix());
        getScrollPane("matrix").setMinimumSize(new Dimension(540, 360));
        getScrollPane("matrix").setPreferredSize(new Dimension(540, 360));
        add(getScrollPane("matrix"), createConstraints(5, 15, 0, 3));
    }
    
    /**
     * Method responsible for updating the Matrix.
     */
    public void updateMatrix() {
        getScrollPane("matrix").setViewportView(new PanelMatrix(this, getSelectedDiagram(), getRequirements()));
        getScrollPane("matrix").setMinimumSize(new Dimension(540, 360));
        getScrollPane("matrix").setPreferredSize(new Dimension(540, 360));
    }
    
    /**
     * Method responsible for returning the Requirements List.
     * @return Requirements List.
     */
    private List<Requirement> getRequirements() {
        return getRequirementComboBox().getSelectedItem() instanceof Requirement ?
               getList((Requirement) getRequirementComboBox().getSelectedItem()) :
               getProject().getRequirementsList();
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
        return getDiagramComboBox().getSelectedItem() instanceof Diagram ?
               (Diagram) getDiagramComboBox().getSelectedItem() : null;
    }
    
    /**
     * Method responsible for returning the Requirement Combo Box.
     * @return Requirement Combo Box.
     */
    public JComboBox getRequirementComboBox() {
        return getComboBox("requirement");
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return getComboBox("diagram");
    }
    
    /**
     * Method responsible for returning the Panel Matrix.
     * @return Panel Matrix.
     */
    public PanelMatrix getPanelMatrix() {
        return (PanelMatrix) getPanel("matrix");
    }
}