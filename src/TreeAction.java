import java.awt.event.ActionEvent;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.AbstractAction;
import javax.vecmath.Vector3f;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class TreeAction extends AbstractAction
{
  /**
   * Live frame.
   */
  CustomFrame my_frame;

  /**
   * Group for tree.
   */
  BranchGroup bg = new BranchGroup();

  /**
   * Is the tree selected?
   */
  boolean selected = false;

  /**
   * Creates the tree.
   * 
   * @param the_frame live frame.
   */
  public TreeAction(CustomFrame the_frame)
  {
    super("Tree");
    my_frame = the_frame;
    Transform3D t3D = new Transform3D();
    TransformGroup tg2 = new TransformGroup(t3D);

    t3D.setTranslation(new Vector3f(0f, 0.25f, 0f));
    TransformGroup tg = new TransformGroup(t3D);
    tg.addChild(new BrokenGlass(0.75f, 0.5f, 0.75f, 0, 1, 0, 1000));
    tg2.addChild(tg);

    t3D = new Transform3D();
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.1f, 0.75f, 0.1f, .4f, .2f, .1f));
    tg2.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0.25f, 0f, 0f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.4f, 0.01f, 1f, .5f, .25f));
    tg2.addChild(tg);

    t3D = new Transform3D();
    t3D.rotX(Math.PI / 2);
    t3D.setTranslation(new Vector3f(0.25f, -0.23f, 0f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Torus(.025f, .01f, 10, 10, 0.25f, 0.25f, 0.25f));
    tg2.addChild(tg);

    tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tg2.setCapability(TransformGroup.ENABLE_PICK_REPORTING);

    bg.addChild(tg2);
    bg.setCapability(BranchGroup.ALLOW_DETACH);
    bg.compile();
  }

  @Override
  public void actionPerformed(ActionEvent the_event)
  {

    if (selected)
    {
      my_frame.removeShape(bg);
    }
    else
    {
      my_frame.addShape(bg);
    }
    selected = !selected;
  }

}
