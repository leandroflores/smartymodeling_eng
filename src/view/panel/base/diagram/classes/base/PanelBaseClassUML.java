package view.panel.base.diagram.classes.base;

import controller.view.panel.base.diagram.classes.base.ControllerPanelBaseClassUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.ClassUML;
import view.panel.base.PanelBaseElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseClassUML</b>.</p>
 * <p>Class responsible for defining a <b>Class UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    controller.view.panel.base.diagram.classes.base.ControllerPanelBaseClassUML
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseClassUML extends PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param class_ Class UML.
     */
    public PanelBaseClassUML(ViewMenu view, ClassDiagram diagram, ClassUML class_) {
        super(view, diagram, class_);
        controller = new ControllerPanelBaseClassUML(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(5, 2, 2, 5));
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", getElement().getName(), 25));
        
        add(createLabel("Abstract: "));
        add(createCheckBox("abstract",  "", getElement().isAbstract()));
        
        add(createLabel("Final: "));
        add(createCheckBox("final", "", getElement().isFinal()));
        
        add(createLabel("Mandatory: "));
        add(createCheckBox("mandatory", "", getElement().isMandatory()));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Abstract Check Box.
     * @return Abstract Check Box.
     */
    public JCheckBox getAbstractCheckBox() {
        return getCheckBox("abstract");
    }
    
    /**
     * Method responsible for returning the Final Check Box.
     * @return Final Check Box.
     */
    public JCheckBox getFinalCheckBox() {
        return getCheckBox("final");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return getCheckBox("mandatory");
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
    
    @Override
    public ClassUML getElement() {
        return (ClassUML) element;
    }
}