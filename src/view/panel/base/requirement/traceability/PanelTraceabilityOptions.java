package view.panel.base.requirement.traceability;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Traceability;
import view.panel.Panel;

/**
 * <p>Class of View <b>PanelTraceabilityOptions</b>.</p>
 * <p>Class responsible for defining a <b>Traceability Options Panel</b> of SMartyModeling.</p>
 * @author Renan
 * @since  2021-06-28
 * @see    model.structural.base.traceability.Traceability
 * @see    view.panel.Panel
 */
public final class PanelTraceabilityOptions extends Panel {
    protected final Project project;
    protected final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param traceability Traceability.
     */
    public PanelTraceabilityOptions(Project project, Traceability traceability) {
        this.project      = project;
        this.traceability = traceability;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(325, 200));
        addComponents();
    }
    
    @Override
    protected void addComponents() {
        for (Requirement requirement : project.getRequirementsList())
            add(createCheckBox(requirement.getId(), requirement.getName(), traceability.contains(requirement)));
        getCheckBox(traceability.getOwner().getId()).setVisible(false);
        getCheckBox(traceability.getOwner().getId()).setSelected(false);
    }
    
    /**
     * Method responsible for returning the Destination List.
     * @return Destination List.
     */
    public List<Requirement> getDestination() {
        List destination = new ArrayList<>();
        for (Requirement requirement : project.getRequirementsList()) {
            if (getCheckBox(requirement.getId()).isSelected())
                destination.add(requirement);
        }
        return destination;
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return traceability;
    }
}