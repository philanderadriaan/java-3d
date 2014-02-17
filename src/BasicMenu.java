import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.vecmath.Color3f;

import com.sun.j3d.utils.geometry.Sphere;

/**
 * 
 * @author Phil
 * @version 1
 */
public class BasicMenu extends JMenu
{
  /**
   * Menu for basic shapes.
   * 
   * @param the_frame live frame.
   */
  public BasicMenu(CustomFrame the_frame)
  {
    super("Basic");

    add(new JMenuItem(new ShapeAction(the_frame, new BrokenGlass(0.5f, .1f, 0.5f, 1.0f, 1.0f,
                                                              1.0f, 100), "Broken Glass")));
    add(new JMenuItem(new ShapeAction(the_frame, new Sphere(0.25f, setColor()), "Sphere")));
    add(new JMenuItem(new ShapeAction(the_frame, new Torus(.25f, .0625f, 60, 30, 1, 0, 1),
                                      "Donut")));
    add(new JMenuItem(new ShapeAction(the_frame, new Cuboid(0.5f, 0.4f, 0.3f, 1, 0, 1),
                                      "Cuboid")));
    add(new JMenuItem(new ShapeAction(the_frame, new Octagon(0.25f, 1, 0, 1), "Octagon")));
  }

  /**
   * Set color to purple.
   * 
   * @return appearance of purple mesh.
   */
  private Appearance setColor()
  {
    Appearance meshApp = new Appearance();
    Material meshMat = new Material();
    meshMat.setDiffuseColor(1f, 0f, 1f);
    meshApp.setMaterial(meshMat);
    meshApp.setColoringAttributes(new ColoringAttributes(0f, 0f, 0f, ColoringAttributes.SHADE_GOURAUD));
    return meshApp;
  }
}
