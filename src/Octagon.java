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
public class Octagon extends Shape3D
{
  /**
   * Creates an octagon.
   * 
   * @param radius of octagon.
   * @param red color of octagon.
   * @param green color of octagon.
   * @param blue color of octagon.
   */
  public Octagon(float radius, float red, float green, float blue)
  {
    setGeometry(createGeometry(radius));

    Appearance meshApp = new Appearance();
    Material meshMat = new Material();
    meshMat.setDiffuseColor(red, green, blue);
    meshApp.setMaterial(meshMat);
    meshApp.setColoringAttributes(new ColoringAttributes(0f, 0f, 0f,
                                                         ColoringAttributes.SHADE_GOURAUD));
    setAppearance(meshApp);
  }

  /**
   * Geometry of octagon.
   * 
   * @param radius of octagon.
   * @return geometry of finished octagon.
   */
  private Geometry createGeometry(float radius)
  {
    IndexedTriangleArray geometry = new IndexedTriangleArray(9, GeometryArray.COORDINATES, 54);

    double angle = Math.PI / 8;

    Point3f[] vertices = new Point3f[9];
    vertices[0] = new Point3f(0, 0, 0);
    for (int i = 1; i < 9; i++)
    {
      float x = (float) (radius * Math.cos(angle));
      float y = (float) (radius * Math.sin(angle));
      vertices[i] = new Point3f(x, y, 0);
      angle += (Math.PI / 4);
    }

    int[] triangleIndices = new int[54];
    for (int i = 1; i < 9; i++)
    {
      triangleIndices[i * 6] = 0;
      triangleIndices[i * 6 + 1] = i;
      triangleIndices[i * 6 + 2] = (i % 8) + 1;
      triangleIndices[i * 6 + 3] = 0;
      triangleIndices[i * 6 + 4] = (i % 8) + 1;
      triangleIndices[i * 6 + 5] = i;
    }

    geometry.setCoordinates(0, vertices);
    geometry.setCoordinateIndices(0, triangleIndices);

    // Utility code to automatically generate normals.
    GeometryInfo gInfo = new GeometryInfo(geometry);
    new NormalGenerator().generateNormals(gInfo);
    return gInfo.getIndexedGeometryArray();
  }
}
