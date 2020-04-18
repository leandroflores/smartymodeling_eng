package view.interfaces;

/**
 * <p>Interface <b>View</b>.</p>
 * <p>Interface responsible for defining <b>View Operations</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-12
 */
public interface InterfaceView {
    
    /**
     * Method responsible for initializing the View Components.
     */
    public void initComponents();
     
    /**
     * Method responsible for adding the View Header.
     */
    public void addHeader();
    
    /**
     * Method responsible for adding the View Components.
     */
    public void addComponents();
    
    /**
     * Method responsible for adding the View Footer.
     */
    public void addFooter();
}