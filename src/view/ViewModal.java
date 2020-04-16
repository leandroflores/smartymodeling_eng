package view;

import view.interfaces.InterfaceView;
import controller.Controller;
import funct.FunctDate;
import funct.FunctView;
import funct.FunctString;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 * <p>Class of View <b>ViewModal</b>.</p>
 * <p>Class responsible for defining a Abstract Model for the <b>Modal Views</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-18
 * @see    controller.Controller
 * @see    javax.swing.JDialog
 * @see    view.InterfaceView
 */
public abstract class ViewModal extends JDialog implements InterfaceView {
    protected String     title;
    protected Controller controller;
    private HashMap buttons;
    private HashMap scrollPanes;
    private HashMap tables;
    private HashMap textFields;
    
    /**
     * Default constructor method of Class.
     * @param view View Parent.
     */
    public ViewModal(View view) {
        super(view, "", true);
        this.init();
        this.setSettings();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param view View Parent.
     */
    public ViewModal(ViewModal view) {
        super(view, "", true);
        this.init();
        this.setSettings();
    }
    
    /**
     * Method responsible for initializing the Maps.
     */
    private void init() {
        this.buttons       = new HashMap<>();
        this.scrollPanes   = new HashMap<>();
        this.tables        = new HashMap<>();
        this.textFields    = new HashMap<>();   
    }
    
    /**
     * Method responsible for defining the settings for the View Modal.
     */
    private void setSettings() {
        this.setSize(220, 120);
        this.setLocation(350, 400);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void addHeader() {
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding Lines to View Modal.
     * @param number Number of Lines.
     */
    protected void addLines(int number) {
        for (int i = 0; i < number; i++)
            this.add(new JLabel(new FunctString().getSpaces(1000)));
    }
    
    /**
     * Method responsible for returning a New Label.
     * @param  title Label Title.
     * @return New Label.
     */
    protected JLabel createLabel(String title) {
        JLabel label = new JLabel(title);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
        return label;
    }
    
    /**
     * Method responsible for returning a New Label.
     * @param  title Label Title.
     * @param  size Label Size.
     * @return New Label.
     */
    protected JLabel createLabel(String title, int size) {
        JLabel label = this.createLabel(title);
               label.setHorizontalAlignment(SwingConstants.RIGHT);
               label.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
        return label;
    }
    
    /**
     * Method responsible for returning a New Image Label.
     * @param  path Image Path.
     * @return New Image Label.
     */
    protected JLabel createLabelImage(String path) {
        return new JLabel(new FunctView().createImage(path));
    }
    
    /**
     * Method responsible for returning a New Button.
     * @param  id Button Id.
     * @param  title Button Title.
     * @param  path Button Image Path.
     * @return New Button.
     */
    protected JButton createButton(String id, String title, String path) {
        JButton button = new JButton(new FunctView().createImage("icons/" + path + ".png"));
                button.setText(title);
                button.setToolTipText(title);
                button.addActionListener(this.controller);
                button.addKeyListener(this.controller);
                button.setFont(new Font(ViewStyle.STYLE, ViewStyle.CENTER, ViewStyle.SIZE));
                this.buttons.put(id, button);
        return  button;
    }
    
    /**
     * Method responsible for returning the Button by Id.
     * @param  id Button Id.
     * @return Button found.
     */
    public JButton getButton(String id) {
        return (JButton) this.buttons.get(id);
    }
    
    /**
     * Method responsible for returning a New Scroll Pane.
     * @param  id Scroll Pane Id.
     * @return New Scroll Pane.
     */
    public JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = new JScrollPane();
//                    scrollPane.setPreferredSize(new Dimension(390, 120));
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a New Scroll Pane of a Panel.
     * @param  id Scroll Pane Id.
     * @param  panel Panel.
     * @return New Scroll Pane of a JPanel.
     */
    public JScrollPane createScrollPane(String id, JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
                    scrollPane.setPreferredSize(new Dimension(390, 120));
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning the Scroll Pane by Id.
     * @param  id Scroll Pane Id.
     * @return Scroll Pane found.
     */
    public JScrollPane getScrollPane(String id) {
        return (JScrollPane) this.scrollPanes.get(id);
    }
    
    /**
     * Method responsible for returning a New Text Field.
     * @param  id Text Field Id.
     * @param  title Text Field Title.
     * @param  size Text Field Size.
     * @return New Text Field.
     */
    protected JTextField createTextField(String id, String title, int size) {
        JTextField textField = new JTextField(size);
                   textField.setText(title);
                   textField.addActionListener(this.controller);
                   textField.addKeyListener(this.controller);
                   textField.setPreferredSize(new Dimension(ViewStyle.WIDTH, ViewStyle.HEIGHT));
                   textField.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                   this.textFields.put(id, textField);
        return     textField;
    }
    
    /**
     * Method responsible for returning a New Date Text Field.
     * @param  id Text Field Id.
     * @param  date Text Field Date.
     * @return New Date Text Field.
     */
    protected JTextField createTextFieldDate(String id, Date date) {
        return this.createTextField(id, new FunctDate().getFormattedUSDate(date), 8);
    }
    
    /**
     * Method responsible for returning a New No Editable Text Field.
     * @param  id Text Field Id.
     * @param  title Text Field Title.
     * @param  size Text Field Size.
     * @return New No Editable Text Field.
     */
    protected JTextField createTextFieldNoEditable(String id, String title, int size) {
        JTextField textField = this.createTextField(id, title, size);
                   textField.setEditable(false);
        return     textField;
    }
    
    /**
     * Method responsible for returning the Text Field by Id.
     * @param  id Text Field Id.
     * @return Text Field found.
     */
    public JTextField getTextField(String id) {
        return (JTextField) this.textFields.get(id);
    }
    
    /**
     * Method responsible for defining a Size to View Modal.
     * @param y Axis Y.
     * @param x Axis X.
     */
    @Override
    public void setSize(int y, int x) {
        super.setSize(y, x);
        this.setLocationRelativeTo(this.getParent());
    }
}