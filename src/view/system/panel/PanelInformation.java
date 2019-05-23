package view.system.panel;

import view.Panel;

/**
 * <p>Class of Panel <b>PanelInformation</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Information Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    view.Panel
 */
public final class PanelInformation extends Panel {
    
    @Override
    protected void addComponents() {
        this.add(this.createCenterLabel("SMartyModeling - Version 2.0", 800));
        this.add(this.createCenterLabel("Software Product Line Modeling Environment", 800));
        this.add(this.createCenterLabel("Developed by GReater (Research Group on Systematic Software Reuse and Continuous Experimentation)", 800));
        this.add(this.createCenterLabel("Department of Informatics - State University of Maringa", 800));
    }
}