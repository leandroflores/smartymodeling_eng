package view.panel;

import controller.Controller;
import funct.FunctView;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import view.style.ViewStyle;

/**
 * <p>Class of View <b>Panel</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for the <b>Panels</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-18
 * @see    controller.Controller
 * @see    javax.swing.JPanel
 */
public abstract class Panel extends JPanel {
    protected Controller controller;
    private HashMap buttons;
    private HashMap checkBoxes;
    private HashMap comboBoxes;
    private HashMap fileChoosers;
    private HashMap lists;
    private HashMap panels;
    private HashMap scrollPanes;
    private HashMap tables;
    private HashMap tableColums;
    private HashMap tableModels;
    private HashMap textAreas;
    private HashMap textFields;
    
    /**
     * Default constructor method of Classe.
     */
    public Panel() {
        super();
        this.init();
        this.setSettings();
    }
    
    /**
     * Method responsible for initializing the Component Maps.
     */
    private void init() {
        this.buttons      = new HashMap<>();
        this.checkBoxes   = new HashMap<>();
        this.comboBoxes   = new HashMap<>();
        this.fileChoosers = new HashMap<>();
        this.lists        = new HashMap<>();
        this.panels       = new HashMap<>();
        this.scrollPanes  = new HashMap<>();
        this.tables       = new HashMap<>();
        this.tableColums  = new HashMap<>();
        this.tableModels  = new HashMap<>();
        this.textAreas    = new HashMap<>();
        this.textFields   = new HashMap<>();
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
     * Method responsible for returning a New Center JLabel.
     * @param  title Label Title.
     * @param  size Label Size.
     * @return New Center JLabel.
     */
    protected JLabel createCenterLabel(String title, int size) {
        JLabel label = this.createLabel(title, size);
               label.setAlignmentX(Component.CENTER_ALIGNMENT);
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
     * Method responsible for returning a new JButton.
     * @param  id JButton Id.
     * @param  title JButton Title.
     * @return New JButton.
     */
    protected JButton createButton(String id, String title) {
        JButton button = new JButton();
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
     * Method responsible for returning a New Button.
     * @param  id Button Id.
     * @param  title Button Title.
     * @param  path Button Image Path.
     * @return New Button.
     */
    protected JButton createButton(String id, String title, String path) {
        JButton button = this.createButton(id, title);
                button.setIcon(new FunctView().createImage("icons/" + path));
        return  button;
    }
    
    /**
     * Method responsible for returning a New Button.
     * @param  id Button Id.
     * @param  title Button Title.
     * @param  focus Button Focus Title.
     * @param  path Button Image Path.
     * @return New Button.
     */
    protected JButton createButton(String id, String title, String focus, String path) {
        JButton button = this.createButton(id, title, path);
                button.setToolTipText(focus);
        return  button;
    }
    
    /**
     * Method responsible for returning the Button by Id.
     * @param  id Button Id.
     * @return Button found.
     */
    protected JButton getButton(String id) {
        return (JButton) this.buttons.get(id);
    }
    
    /**
     * Method responsible for returning the Buttons Map.
     * @return Buttons Map.
     */
    protected HashMap getButtons() {
        return new HashMap(this.buttons);
    }
    
    /**
     * Method responsible for returning a New Check Box.
     * @param  id Check Box Id.
     * @param  title Check Box Title.
     * @param  selected Selected Flag.
     * @return New Check Box.
     */
    protected JCheckBox createCheckBox(String id, String title, boolean selected) {
        JCheckBox checkBox = new JCheckBox(title);
                  checkBox.setSelected(selected);
                  checkBox.addActionListener(this.controller);
                  checkBox.addKeyListener(this.controller);
                  checkBox.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                  this.checkBoxes.put(id, checkBox);
        return    checkBox;
    }
    
    /**
     * Method responsible for returning the Check Box by Id.
     * @param  id Check Box Id.
     * @return Check Box found.
     */
    protected JCheckBox getCheckBox(String id) {
        return (JCheckBox) this.checkBoxes.get(id);
    }
    
    /**
     * Method responsible for returning a New Combo Box.
     * @param  id Combo Box Id.
     * @param  values Combo Box Values.
     * @param  size Combo Box Size.
     * @return New Combo Box.
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
     * Method responsible for returning a New Combo Box.
     * @param  id Combo Box Id.
     * @param  values Combo Box Values.
     * @param  size Combo Box Size.
     * @param  object Selected Object.
     * @return New Combo Box.
     */
    protected JComboBox createComboBox(String id, Object[] values, int size, Object object) {
        JComboBox comboBox = this.createComboBox(id, values, size);
                  comboBox.setSelectedItem(object);
        return    comboBox;
    }
    
    /**
     * Method responsible for returning the Combo Box by Id.
     * @param  id Combo Box Id.
     * @return Combo Box found.
     */
    protected JComboBox getComboBox(String id) {
        return (JComboBox) this.comboBoxes.get(id);
    }
    
    /**
     * Method responsible for returning a New File Chooser.
     * @param  id File Chooser Id.
     * @return New File Chooser.
     */
    protected JFileChooser createFileChooser(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new FileNameExtensionFilter("SMARTY", "smty", "smty"));
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning a New Directory Chooser.
     * @param  id Directory Chooser Id.
     * @return New Directory Chooser.
     */
    protected JFileChooser createDirectoryChooser(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning the File Chooser by Id.
     * @param  id File Chooser Id.
     * @return File Chooser found.
     */
    protected JFileChooser getFileChooser(String id) {
        return (JFileChooser) this.fileChoosers.get(id);
    }
    
    /**
     * Method responsible for returning a New List.
     * @param  id List Id.
     * @return New List.
     */
    protected JList createList(String id) {
        JList  list = new JList();
               list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               list.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
               list.addKeyListener(this.controller);
               this.createScrollPane(id, list);
               this.lists.put(id, list);
        return list;
    }
    
    /**
     * Method responsible for returning the List by Id.
     * @param  id List Id.
     * @return List found.
     */
    protected JList getList(String id) {
        return (JList) this.lists.get(id);
    }
    
    /**
     * Method responsible for adding a Panel.
     * @param id Panel Id.
     * @param panel Panel.
     */
    protected void addPanel(String id, Panel panel) {
        this.panels.put(id, panel);
    }
    
    /**
     * Method responsible for returning a Panel by Id.
     * @param  id Panel Id.
     * @return Panel found.
     */
    protected Panel getPanel(String id) {
        return (Panel) this.panels.get(id);
    }
    
    /**
     * Method responsible for removing a Panel.
     * @param id Panel Id.
     */
    protected void removePanel(String id) {
        this.panels.remove(id);
    }
    
    /**
     * Method responsible for returning a New Scroll Pane.
     * @param  id Scroll Pane Id.
     * @return New Scroll Pane.
     */
    protected JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a New Scroll Pane of a Panel.
     * @param  id Scroll Pane Id.
     * @param  panel Panel.
     * @return New Scroll Pane of a Panel.
     */
    protected JScrollPane createScrollPane(String id, JPanel panel) {
        JScrollPane scrollPane = this.createScrollPane(id);
                    scrollPane.setPreferredSize(new Dimension(390, 120));
                    scrollPane.setViewportView(panel);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a New Scroll Pane of a List.
     * @param  id Scroll Pane Id.
     * @param  list List.
     * @return New Scroll Pane of a JList.
     */
    private JScrollPane createScrollPane(String id, JList list) {
        JScrollPane scrollPane = this.createScrollPane(id);
                    scrollPane.setViewportView(list);
                    scrollPane.setPreferredSize(new Dimension(390, 120));
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a New Scroll Pane of a Text Area.
     * @param  id Scroll Pane Id.
     * @param  textArea Text Area.
     * @return New Scroll Pane of a Text Area.
     */
    private JScrollPane createScrollPane(String id, JTextArea textArea) {
        JScrollPane scrollPane = this.createScrollPane(id);
                    scrollPane.setViewportView(textArea);
                    scrollPane.setPreferredSize(new Dimension(200, 100));
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a new Scroll Pane of a Table.
     * @param  id Scroll Pane Id.
     * @param  table Table.
     * @return New Scroll Pane of a Table.
     */
    private JScrollPane createScrollPane(String id, JTable table) {
        JScrollPane scrollPane = this.createScrollPane(id);
                    scrollPane.setViewportView(table);
                    scrollPane.setPreferredSize(new Dimension(380, 150));
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning the Scroll Pane by Id.
     * @param  id Scroll Pane Id.
     * @return Scroll Pane found.
     */
    protected JScrollPane getScrollPane(String id) {
        return (JScrollPane) this.scrollPanes.get(id);
    }
    
    /**
     * Method responsible for returning a New Table.
     * @param  id Table Id.
     * @return New Table.
     */
    protected JTable createTable(String id) {
        JTable table = new JTable(this.createTableModel());
               table.addKeyListener(this.controller);
               this.createScrollPane(id, table);
               this.tableModels.put(id, table.getModel());
               this.tableColums.put(id, table.getColumnModel());
               this.tables.put(id, table);
        return table;
    }
    
    /**
     * Method responsible for returning a New Table Model.
     * @return New Table Model.
     */
    private DefaultTableModel createTableModel() {
        return 
            new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int col) {   
                    return true;
                }};
    }
    
    /**
     * Method responsible for adding the Columns on Table.
     * @param id Table Id.
     * @param values Column Values.
     */
    protected void addColumns(String id, String[] values) {
        for (String value : values)
            ((DefaultTableModel) this.tableModels.get(id)).addColumn(value);
    }
    
    /**
     * Method responsible for setting the Columns Size on Table.
     * @param id Table Id. 
     * @param size Size Array.
     */
    protected void setColumnsSize(String id, int[] size) {
        for (int i = 0; i < size.length; i++)
            this.getTable(id).getColumnModel().getColumn(i).setPreferredWidth(size[i]);
    }
    
    /**
     * Method responsible for cleaning the Table.
     * @param id Table Id.
     */
    protected void clearTable(String id) {
        while (this.getTableModel(id).getRowCount() > 0)
            this.getTableModel(id).removeRow(0);
        this.getTable(id).removeAll();
    }
    
    /**
     * Method responsible for adding the Line Values on Table.
     * @param id Table Id. 
     * @param values Line Values.
     */
    protected void addRows(String id, Object[][] values) {
        this.clearTable(id);
        for (Object[] value : values) {
            this.getTableModel(id).addRow(value);
            this.getTable(id).setEditingRow(JTable.AUTO_RESIZE_NEXT_COLUMN);
            this.getTable(id).setEditingRow(0);
        }
    }
    
    /**
     * Method responsible for returning the Table by Id.
     * @param  id Table Id.
     * @return Table found.
     */
    protected JTable getTable(String id) {
        return (JTable) this.tables.get(id);
    }
    
    /**
     * Method responsible for returning the Table Model by Id.
     * @param  id Table Model Id.
     * @return Table Model found.
     */
    protected DefaultTableModel getTableModel(String id) {
        return (DefaultTableModel) this.tableModels.get(id);
    }
    
    /**
     * Method responsible for returning the Table Column by Id.
     * @param  id Table Column Id.
     * @return Table Column found.
     */
    protected TableColumnModel getTableColumn(String id) {
        return (TableColumnModel) this.tableColums.get(id);
    }
    
    /**
     * Method responsible for returning a New Text Area.
     * @param  id Text Area Id.
     * @return New Text Area.
     */
    protected JTextArea createTextArea(String id) {
        JTextArea textArea = new JTextArea(5, 10);
                  textArea.addKeyListener(this.controller);
                  textArea.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                  this.textAreas.put(id, textArea);
                  this.createScrollPane(id, textArea);
        return    textArea;
    }
    
    /**
     * Method responsible for returning a New Text Area.
     * @param  id Text Area Id.
     * @param  text Text Area Text.
     * @return New Text Area.
     */
    protected JTextArea createTextArea(String id, String text) {
        JTextArea textArea = this.createTextArea(id);
                  textArea.setText(text);
        return    textArea;
    }
    
    /**
     * Method responsible for returning the Text Area by Id.
     * @param  id Text Area Id.
     * @return Text Area found.
     */
    protected JTextArea getTextArea(String id) {
        return (JTextArea) this.textAreas.get(id);
    }
    
    /**
     * Method responsible for returning a New Text Field.
     * @param  id Text Field Id.
     * @param  value Text Field Value.
     * @param  size Text Field Size.
     * @return New Text Field.
     */
    protected JTextField createTextField(String id, String value, int size) {
        JTextField textField = new JTextField(size);
                   textField.setText(value);
                   textField.addActionListener(this.controller);
                   textField.addKeyListener(this.controller);
                   textField.setPreferredSize(new Dimension(ViewStyle.WIDTH, ViewStyle.HEIGHT));
                   textField.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                   this.textFields.put(id, textField);
        return     textField;
    }
    
    /**
     * Method responsible for returning a New No Editable Text Field.
     * @param  id Text Field Id.
     * @param  value Text Field Value.
     * @param  size Text Field Size.
     * @return New No Editable JTextField.
     */
    protected JTextField createTextFieldNoEditable(String id, String value, int size) {
        JTextField textField = this.createTextField(id, value, size);
                   textField.setEditable(false);
        return     textField;
    }
    
    /**
     * Method responsible for returning the Text Field by Id.
     * @param  id Text Field Id.
     * @return Text Field found.
     */
    protected JTextField getTextField(String id) {
        return (JTextField) this.textFields.get(id);
    }
    
    /**
     * Method responsible for returning the New Constraints.
     * @param  width Width Constraints.
     * @param  height Height Constraints.
     * @param  x X Position Grid.
     * @param  y Y Position Grid.
     * @return New Constraints.
     */
    protected GridBagConstraints createConstraints(int width, int height, int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
                           constraints.gridheight = height;
                           constraints.gridwidth  = width;
                           constraints.gridx      = x;
                           constraints.gridy      = y;
                           constraints.fill       = GridBagConstraints.HORIZONTAL;
        return constraints;
    }
    
    /**
     * Method responsible for returning the New Start Constraint.
     * @return New Start Constraint.
     */
    protected GridBagConstraints createStartConstraint() {
        return this.setStartConstraint(new GridBagConstraints());
    }
    
    /**
     * Method responsible for setting the Start Constraint.
     * @param  constraint Grid Bag Constraint.
     * @return Start Constraint.
     */
    protected GridBagConstraints setStartConstraint(GridBagConstraints constraint) {
               constraint.anchor  = GridBagConstraints.PAGE_START;
               constraint.fill    = GridBagConstraints.HORIZONTAL;
               constraint.gridx   = 0;
               constraint.gridy   = 0;
               constraint.weightx = 1.0;
               constraint.weighty = 0.25;
        return constraint;
    }
    
    /**
     * Method responsible for returning the New Body Constraint.
     * @return New Body Constraint.
     */
    protected GridBagConstraints createBodyConstraint() {
        return this.setBodyConstraint(new GridBagConstraints());
    }
    
    /**
     * Method responsible for setting the Body Constraint.
     * @param  constraint Grid Bag Constraint.
     * @return Body Constraint.
     */
    protected GridBagConstraints setBodyConstraint(GridBagConstraints constraint) {
               constraint.anchor     = GridBagConstraints.FIRST_LINE_START;
               constraint.fill       = GridBagConstraints.BOTH;
               constraint.gridx      = 0;
               constraint.gridy      = 1;
               constraint.weightx    = 1.0;
               constraint.weighty    = 11.75;
               constraint.gridheight = GridBagConstraints.RELATIVE;
               constraint.gridwidth  = GridBagConstraints.RELATIVE;
        return constraint;       
    }
}