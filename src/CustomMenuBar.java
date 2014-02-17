import javax.swing.JMenuBar;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class CustomMenuBar extends JMenuBar
{
  /**
   * Menu for all items.
   * 
   * @param the_frame living frame.
   */
  public CustomMenuBar(CustomFrame the_frame)
  {
    super();
    add(new BasicMenu(the_frame));
    add(new AdvancedMenu(the_frame));
  }
}
