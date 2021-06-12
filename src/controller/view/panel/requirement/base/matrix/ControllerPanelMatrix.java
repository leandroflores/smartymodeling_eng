package controller.view.panel.requirement.base.matrix;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.requirement.Requirement;
import view.panel.requirement.base.matrix.PanelMatrix;

/**
 * <p>Class of Controller <b>ControllerPanelMatrix</b>.</p>
 * <p>Class responsible for controlling the <b>PanelMatrix</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-27
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.requirement.base.matrix.PanelMatrix
 */
public class ControllerPanelMatrix extends ControllerPanel {

    /**
     * Default constructor method of Class.
     * @param panel Panel Matrix.
     */
    public ControllerPanelMatrix(PanelMatrix panel) {
        super(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() instanceof JCheckBox)
            updateValue((JCheckBox) event.getSource());
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getSource() instanceof JCheckBox)
            updateValue((JCheckBox) event.getSource());
    }
    
    /**
     * Method responsible for updating the Check Box Value.
     * @param checkBox Check Box Selected.
     */
    private void updateValue(JCheckBox checkBox) {
        Requirement requirement = getRequirement(checkBox.getName());
        Element     element     = getElement(checkBox.getName());
        if (checkBox.isSelected())
            requirement.addElement(element.getDiagramType(), element);
        else
            requirement.removeElement(element);
    }
    
    /**
     * Method responsible for returning the Requirement by Check Box.
     * @param  id Check Box Id.
     * @return Requirement.
     */
    private Requirement getRequirement(String id) {
        return getProject().getRequirement(id.substring(0, id.indexOf("|")));
    }
    
    /**
     * Method responsible for returning the Element by Check Box.
     * @param  id Check Box Id.
     * @return Element.
     */
    private Element getElement(String id) {
        return getProject().getElement(id.substring(id.indexOf("|") + 1));
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return getPanel().getProject();
    }
    
    @Override
    public PanelMatrix getPanel() {
        return (PanelMatrix) panel;
    }
}