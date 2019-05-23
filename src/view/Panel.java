package view;

import controller.Controller;
import funct.FunctView;
import funct.FunctString;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;

/**
 * <p>Class of View <b>Panel</b>.</p> 
 * <p>Class responsible for defining a Model for the <b>Panels</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/05/2019
 * @see    controller.Controller
 * @see    javax.swing.JPanel
 */
public abstract class Panel extends JPanel {
    protected Controller controller;
    
    protected HashMap<String, JButton> buttons;
    protected HashMap<String, JComboBox> comboBoxes;
    protected HashMap<String, JCheckBox> checkBoxes;
    protected HashMap<String, JTextField> textFields;
    protected HashMap<String, JFileChooser> fileChoosers;
    
    /**
     * Default constructor method of Classe.
     */
    public Panel() {
        super();
        this.init();
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for starting the Lists.
     */
    private void init() {
        this.buttons      = new HashMap<>();
        this.textFields   = new HashMap<>();
        this.comboBoxes   = new HashMap<>();
        this.checkBoxes   = new HashMap<>();
        this.fileChoosers = new HashMap<>();
    }
    
    /**
     * Method responsible for adding the Components.
     */
    protected abstract void addComponents();
    
    /**
     * Method responsible for returning the Default Color.
     * @return Default Color.
     */
    protected Color getDefaultColor() {
        return this.getBackground();
    }
    
    /**
     * Method responsible for returning the Focus Color.
     * @return Focus Color.
     */
    protected Color getFocusColor() {
        return ColorUIResource.LIGHT_GRAY;
    }
    
    /**
     * Method responsible for defining the settings for the Panel.
     */
    private void setSettings() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    /**
     * Method responsible for adding Lines to Panel.
     * @param number Number of Lines.
     */
    protected void addLines(int number) {
        for (int i = 0; i < number; ++i)
            this.add(new JLabel(new FunctString().getSpaces(1000)));
    }
    
    /**
     * Method responsible for returning a new JLabel.
     * @param  title JLabel Title.
     * @return New JLabel.
     */
    protected JLabel createLabel(String title) {
        JLabel label = new JLabel(title);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
        return label;
    }
    
    /**
     * Method responsible for returning a new JLabel.
     * @param  title JLabel Title.
     * @param  size JLabel Size.
     * @return New JLabel.
     */
    protected JLabel createLabel(String title, int size) {
        JLabel label = new JLabel(title, SwingConstants.RIGHT);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               label.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
        return label;
    }
    
    /**
     * Method responsible for returning a new Center JLabel.
     * @param  title JLabel Title.
     * @param  size JLabel Size.
     * @return New Center JLabel.
     */
    protected JLabel createCenterLabel(String title, int size) {
        JLabel label = new JLabel(title, SwingConstants.RIGHT);
               label.setAlignmentX(Component.CENTER_ALIGNMENT);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               label.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
        return label;
    }
    
    /**
     * Method responsible for returning a new Image JLabel.
     * @param  url Image URL.
     * @return New Image JLabel.
     */
    protected JLabel createLabelImage(String url) {
        return new JLabel(new FunctView().createImage(url));
    }
    
    /**
     * Method responsible for returning a new JTextField.
     * @param  id JTextField Id.
     * @param  message JTextField Message.
     * @param  size JTextField Size.
     * @return New JTextField.
     */
    protected JTextField createTextField(String id, String message, int size) {
        JTextField textField = new JTextField(size);
                   textField.setText(message);
                   textField.addActionListener(this.controller);
                   textField.addKeyListener(this.controller);
                   textField.setPreferredSize(new Dimension(ViewStyle.WIDTH, ViewStyle.HEIGHT));
                   textField.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                   this.textFields.put(id, textField);
        return     textField;
    }
    
    /**
     * Method responsible for returning a new No Editable JTextField.
     * @param  id JTextField Id.
     * @param  message JTextField Message.
     * @param  size JTextField Size.
     * @return New No Editable JTextField.
     */
    protected JTextField createTextFieldNoEditable(String id, String message, int size) {
        JTextField textField = this.createTextField(id, message, size);
                   textField.setEditable(false);
        return     textField;
    }
    
    /**
     * Method responsible for returning a new JComboBox.
     * @param  id JComboBox Id.
     * @param  values JComboBox Values.
     * @param  size JComboBox Size.
     * @return New JComboBox.
     */
    protected JComboBox createComboBox(String id, Object[] values, int size) {
        JComboBox comboBox = new JComboBox(values);
                  comboBox.addActionListener(this.controller);
                  comboBox.addKeyListener(this.controller);
                  comboBox.setAlignmentX(CENTER_ALIGNMENT);
                  comboBox.setAlignmentY(CENTER_ALIGNMENT);
                  comboBox.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
                  comboBox.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
                  this.comboBoxes.put(id, comboBox);
        return    comboBox;
    }
    
    /**
     * Method responsible for returning a new JCheckBox.
     * @param  id JCheckBox Id.
     * @param  title JCheckBox Title.
     * @return New JCheckBox.
     */
    public JCheckBox createCheckBox(String id, String title) {
        JCheckBox checkBox = new JCheckBox(title);
                  checkBox.addActionListener(this.controller);
                  checkBox.addKeyListener(this.controller);
                  checkBox.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                  this.checkBoxes.put(id, checkBox);
        return    checkBox;
    }
    
    /**
     * Method responsible for returning a new JButton.
     * @param  id JButton Id.
     * @param  title JButton Title.
     * @param  url JButton Image URL.
     * @return New JButton.
     */
    protected JButton createButton(String id, String title, String url) {
        JButton button = new JButton(new FunctView().createImage("icons/" + url));
                button.setText(title);
                button.setToolTipText(title);
                button.addActionListener(this.controller);
                button.addKeyListener(this.controller);
                button.setPreferredSize(new Dimension(75, 30));
                button.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                this.buttons.put(id, button);
        return  button;
    }
    
    /**
     * Method responsible for returning a new JButton.
     * @param  id JButton Id.
     * @param  title JButton Title.
     * @param  focusTitle JButton Focus Title.
     * @param  url JButton Image URL.
     * @return New JButton.
     */
    protected JButton createButton(String id, String title, String focusTitle, String url) {
        JButton button = this.createButton(id, title, url);
                button.setToolTipText(focusTitle);
        return  button;
    }
    
    /**
     * Metodo responsavel por retornar um New JFileChooser.
     * @param  id JFileChooser Id.
     * @return New JFileChooser.
     */
    public JFileChooser createFileChooserArquivo(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new FileNameExtensionFilter("SMARTY", "smty", "smty"));
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
}