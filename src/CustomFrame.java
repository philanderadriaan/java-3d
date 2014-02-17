import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;

import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.PickInfo;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickZoomBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class CustomFrame extends JFrame
{
  /**
   * Graphic configuration.
   */
  GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
  /**
   * Canvas.
   */
  Canvas3D canvas3D = new Canvas3D(config);
  /**
   * Universe.
   */
  SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
  /**
   * Top level branch group.
   */
  BranchGroup scene = new BranchGroup();

  /**
   * Picking behavior.
   */
  Behavior b;

  /**
   * Camera transform group.
   */
  TransformGroup camtg = new TransformGroup();

  /**
   * Creates a frame for canvas. Also shows instructions.
   */
  public CustomFrame()
  {
    super();
    createAndShowGUI();
    JOptionPane
        .showMessageDialog(null,
                           "Select menu item to add object.\nSelect menu item again to remove object.\nLeft click on object to rotate.\nRight click on object to move.\nScroll click on object to zoom.");
  }

  /**
   * Shows all GUI elements.
   */
  private void createAndShowGUI()
  {
    camtg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    camtg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    KeyNavigatorBehavior key = new KeyNavigatorBehavior(camtg);
    key.setEnable(true);
    scene.addChild(key);

    scene.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
    scene.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
    scene.setCapability(BranchGroup.ALLOW_DETACH);
    scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

    // Fix for background flickering on some platforms
    System.setProperty("sun.awt.noerasebackground", "true");

    simpleU.getViewingPlatform().setNominalViewingTransform();

    Light light = new DirectionalLight(new Color3f(1f, 1f, 1f), new Vector3f(-1f, -1f, -1f));
    light.setInfluencingBounds(new BoundingSphere());
    scene.addChild(light);
    light = new DirectionalLight(new Color3f(.2f, .2f, .2f), new Vector3f(1f, 1f, -1f));
    light.setInfluencingBounds(new BoundingSphere());
    scene.addChild(light);

    Behavior b =
        new PickRotateBehavior(scene, canvas3D, new BoundingSphere(), PickInfo.PICK_GEOMETRY);
    b.setSchedulingBounds(new BoundingSphere());
    scene.addChild(b);
    b =
        new PickTranslateBehavior(scene, canvas3D, new BoundingSphere(),
                                  PickInfo.PICK_GEOMETRY);
    b.setSchedulingBounds(new BoundingSphere());
    scene.addChild(b);
    b = new PickZoomBehavior(scene, canvas3D, new BoundingSphere(), PickInfo.PICK_GEOMETRY);
    b.setSchedulingBounds(new BoundingSphere());
    scene.addChild(b);

    b = new PickZoomBehavior(scene, canvas3D, new BoundingSphere(), PickInfo.PICK_GEOMETRY);
    b.setSchedulingBounds(new BoundingSphere());
    scene.addChild(b);

    addExtent();

    scene.compile();
    simpleU.addBranchGraph(scene);

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    add(canvas3D);
    pack();
    if (Toolkit.getDefaultToolkit().isFrameStateSupported(JFrame.MAXIMIZED_BOTH))
      setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    setVisible(true);

    setJMenuBar(new CustomMenuBar(this));

  }

  /**
   * Adds the boundry.
   */
  private void addExtent()
  {
    BranchGroup bg = new BranchGroup();
    Transform3D t3D = new Transform3D();
    final TransformGroup super_transform_group = new TransformGroup(t3D);

    t3D.setTranslation(new Vector3f(0f, 0.4f, 0.4f));
    TransformGroup tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(1.6f, 0.01f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0f, 0.4f, -0.4f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(1.6f, 0.01f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0f, -0.4f, 0.4f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(1.6f, 0.01f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0f, -0.4f, -0.4f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(1.6f, 0.01f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0.8f, 0f, 0.4f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.8f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0.8f, 0f, -0.4f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.8f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(-0.8f, 0f, 0.4f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.8f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(-0.8f, 0f, -0.4f));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.8f, 0.01f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0.8f, 0.4f, 0));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.01f, 0.8f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(0.8f, -0.4f, 0));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.01f, 0.8f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(-0.8f, 0.4f, 0));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.01f, 0.8f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    t3D = new Transform3D();
    t3D.setTranslation(new Vector3f(-0.8f, -0.4f, 0));
    tg = new TransformGroup(t3D);
    tg.addChild(new Cuboid(0.01f, 0.01f, 0.8f, 1.0f, 1.0f, 1.0f));
    super_transform_group.addChild(tg);

    bg.addChild(super_transform_group);
    bg.setCapability(BranchGroup.ALLOW_DETACH);
    bg.compile();

    scene.addChild(bg);
  }

  /**
   * Adds a shape to the scene.
   * 
   * @param bg the branchgroup of the shape.
   */
  public void addShape(BranchGroup bg)
  {
    scene.addChild(bg);
  }

  /**
   * Removes the shape from the scene.
   * 
   * @param bg the branchgroup of the shape.
   */
  public void removeShape(BranchGroup bg)
  {
    scene.removeChild(bg);
  }
}
