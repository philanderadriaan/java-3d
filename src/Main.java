import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public final class Main
{
  /**
   * To prevent initialization.
   */
  private Main()
  {

  }

  /**
   * Starts the UI.
   * 
   * @param the_args command line argument.
   */
  public static void main(final String[] the_args)
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (final UnsupportedLookAndFeelException exception)
    {
      exception.printStackTrace();
    }
    catch (final ClassNotFoundException exception)
    {
      exception.printStackTrace();
    }
    catch (final InstantiationException exception)
    {
      exception.printStackTrace();
    }
    catch (final IllegalAccessException exception)
    {
      exception.printStackTrace();
    }
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        new CustomFrame();
      }
    });
  }
}
