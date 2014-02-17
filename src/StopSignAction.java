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
public class StopSignAction extends AbstractAction
{
  /**
   * Live frame.
   */
  CustomFrame my_frame;

  /**
   * Branch group of stop sign.
   */
  BranchGroup bg = new BranchGroup();

  /**
   * Is stop sign selected?
   */
  boolean selected = false;

  /**
   * Creates stop sign.
   * 
   * @param the_frame live frame.
   */
  public StopSignAction(CustomFrame the_frame)
  {
    super("Stop Sign");
    my_frame = the_frame;
    Transform3D t3D = new Transform3D();
    TransformGroup tg2 = new TransformGroup(t3D);

    t3D.setTranslation(new Vector3f(0f, 0.25f, 0.02f));
    TransformGroup tg = new TransformGroup(t3D);
    tg.addChild(new Octagon(0.1f, 1, 0, 0));
    tg2.addChild(tg);

    t3D = new Transform3D();
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.02f, 0.5f, 0.02f, 1, 1, 1));
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
