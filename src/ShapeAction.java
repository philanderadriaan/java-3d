import java.awt.event.ActionEvent;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.AbstractAction;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class ShapeAction extends AbstractAction
{
  /**
   * Current frame.
   */
  CustomFrame my_frame;

  /**
   * Branchgroup.
   */
  BranchGroup bg = new BranchGroup();

  /**
   * Is the shape selected?
   */
  boolean selected = false;

  /**
   * Action to create basic shape.
   * 
   * @param the_frame live frame.
   * @param the_shape shape.
   * @param the_name name of the shape.
   */
  public ShapeAction(CustomFrame the_frame, Node the_shape, String the_name)
  {
    super(the_name);
    my_frame = the_frame;
    Transform3D t3D = new Transform3D();
    TransformGroup tg = new TransformGroup(t3D);
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tg.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
    tg.addChild(the_shape);
    bg.addChild(tg);
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
