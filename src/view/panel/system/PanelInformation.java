package view.panel.system;

import view.panel.Panel;

/**
 * <p>Class of Panel <b>PanelInformation</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Information Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    view.panel.Panel
 */
public final class PanelInformation extends Panel {
    
    /**
     * Default constructor method of Class.
     */
    public PanelInformation() {
        addComponents();
    }
    
    @Override
    protected void addComponents() {
        add(createCenterLabel("SMartyModeling - Version 2.0", 800));
        add(createCenterLabel("Software Product Line Modeling Environment", 800));
        add(createCenterLabel("Developed by GReater (Research Group on Systematic Software Reuse and Continuous Experimentation)", 800));
        add(createCenterLabel("Department of Informatics - State University of Maringa", 800));
    }
}