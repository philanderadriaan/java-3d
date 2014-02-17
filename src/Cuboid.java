import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

/**
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class Cuboid extends Shape3D
{
  /**
   * Creates a cuboid.
   * 
   * @param width of cuboid.
   * @param height of cuboid.
   * @param depth of cuboid.
   * @param red color of cuboid.
   * @param green color of cuboid
.   * @param blue color of cuboid.
   */
  public Cuboid(float width, float height, float depth, float red, float green, float blue)
  {
    setGeometry(createGeometry(width, height, depth));
    Appearance meshApp = new Appearance();
    Material meshMat = new Material();
    meshMat.setDiffuseColor(red, green, blue);
    meshApp.setMaterial(meshMat);
    meshApp.setColoringAttributes(new ColoringAttributes(0f, 0f, 0f,
                                                         ColoringAttributes.SHADE_GOURAUD));
    setAppearance(meshApp);
  }

  /**
   * Creates geometry of cuboid.
   * 
   * @param width of cuboid.
   * @param height of cuboid.
   * @param depth of cuboid.
   * @return geometry of cuboid.
   */
  private Geometry createGeometry(float width, float height, float depth)
  {
    float half_width = width / 2;
    float half_height = height / 2;
    float half_depth = depth / 2;

    IndexedQuadArray geometry = new IndexedQuadArray(8, GeometryArray.COORDINATES, 24);

    Point3f[] vertices = new Point3f[8];
    vertices[0] = new Point3f(half_width, half_height, half_depth);
    vertices[1] = new Point3f(half_width, half_height, -half_depth);
    vertices[2] = new Point3f(half_width, -half_height, half_depth);
    vertices[3] = new Point3f(half_width, -half_height, -half_depth);
    vertices[4] = new Point3f(-half_width, half_height, half_depth);
    vertices[5] = new Point3f(-half_width, half_height, -half_depth);
    vertices[6] = new Point3f(-half_width, -half_height, half_depth);
    vertices[7] = new Point3f(-half_width, -half_height, -half_depth);

    int[] quadIndices = new int[24];
    quadIndices[0] = 0;
    quadIndices[1] = 4;
    quadIndices[2] = 6;
    quadIndices[3] = 2;
    quadIndices[4] = 0;
    quadIndices[5] = 2;
    quadIndices[6] = 3;
    quadIndices[7] = 1;
    quadIndices[8] = 0;
    quadIndices[9] = 1;
    quadIndices[10] = 5;
    quadIndices[11] = 4;
    quadIndices[12] = 7;
    quadIndices[13] = 6;
    quadIndices[14] = 4;
    quadIndices[15] = 5;
    quadIndices[16] = 7;
    quadIndices[17] = 5;
    quadIndices[18] = 1;
    quadIndices[19] = 3;
    quadIndices[20] = 7;
    quadIndices[21] = 3;
    quadIndices[22] = 2;
    quadIndices[23] = 6;

    geometry.setCoordinates(0, vertices);
    geometry.setCoordinateIndices(0, quadIndices);

    // Utility code to automatically generate normals.
    GeometryInfo gInfo = new GeometryInfo(geometry);
    new NormalGenerator().generateNormals(gInfo);
    return gInfo.getIndexedGeometryArray();
  }
}
