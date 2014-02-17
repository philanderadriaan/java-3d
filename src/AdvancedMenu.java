import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class AdvancedMenu extends JMenu
{
  /**
   * Menu for composite shapes.
   * 
   * @param the_frame live frame.
   */
  public AdvancedMenu(final CustomFrame the_frame)
  {
    super("Advanced");
    add(new JMenuItem(new StopSignAction(the_frame)));
    add(new JMenuItem(new TreeAction(the_frame)));
  }
}
