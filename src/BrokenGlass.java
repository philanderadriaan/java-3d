import java.util.Random;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
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
public class BrokenGlass extends Shape3D
{
  /**
   * Random generator.
   */
  Random random = new Random();

  /**
   * Creates an area with broken glass.
   * 
   * @param width of area.
   * @param height of area.
   * @param depth of area.
   * @param red color of glass.
   * @param green color of glass.
   * @param blue color of glass.
   * @param quantity of glass.
   */
  public BrokenGlass(float width, float height, float depth, float red, float green,
                     float blue, int quantity)
  {
    setGeometry(createGeometry(width, height, depth, quantity));

    Appearance meshApp = new Appearance();
    Material meshMat = new Material();
    meshMat.setDiffuseColor(red, green, blue);
    meshApp.setMaterial(meshMat);
    meshApp.setColoringAttributes(new ColoringAttributes(0f, 0f, 0f,
                                                         ColoringAttributes.SHADE_GOURAUD));
    setAppearance(meshApp);
  }

  /**
   * 
   * @param width of area.
   * @param height of area.
   * @param depth of area.
   * @param quantity of glass.
   * @return Geometry of all broken glass in an area.
   */
  private Geometry createGeometry(float width, float height, float depth, int quantity)
  {
    int facets = quantity * 3;
    IndexedTriangleArray geometry =
        new IndexedTriangleArray(1000, GeometryArray.COORDINATES, facets);

    Point3f[] vertices = new Point3f[1000];

    for (int i = 0; i < 1000; i++)
    {
      float x = i / 100;
      float y = i % 100 / 10;
      float z = i % 10;
      vertices[i] =
          new Point3f(x / 10 * width - width / 2, y / 10 * height - height / 2, z / 10 *
                                                                                depth - depth /
                                                                                2);
    }

    int[] triangleIndices = new int[facets];
    for (int i = 0; i < quantity; i++)
    {
      int f = i * 3;
      triangleIndices[f] = random.nextInt(1000);
      triangleIndices[f + 1] = random.nextInt(1000);
      triangleIndices[f + 2] = random.nextInt(1000);
    }

    geometry.setCoordinates(0, vertices);
    geometry.setCoordinateIndices(0, triangleIndices);

    // Utility code to automatically generate normals.
    GeometryInfo gInfo = new GeometryInfo(geometry);
    new NormalGenerator().generateNormals(gInfo);
    return gInfo.getIndexedGeometryArray();
  }
}
