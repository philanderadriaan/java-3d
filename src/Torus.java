import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

/**
 * 
 * @author Matthew Alden
 * @author Phil Adriaan
 * @version 1
 */
public class Torus extends Shape3D
{
  /**
   * Creates torus.
   * 
   * @param majorRadius Radius of bigger circle.
   * @param minorRadius Radius of smaller circle.
   * @param majorSamples Sample of bigger circle.
   * @param minorSamples Sample of smaller circle.
   * @param r red percentage.
   * @param g green percentage.
   * @param b blue percentage.
   */
  public Torus(float majorRadius, float minorRadius, int majorSamples, int minorSamples,
               float r, float g, float b)
  {
    setGeometry(createGeometry(majorRadius, minorRadius, majorSamples, minorSamples));

    Appearance meshApp = new Appearance();
    Material meshMat = new Material();
    meshMat.setDiffuseColor(r, g, b);
    meshApp.setMaterial(meshMat);
    meshApp.setColoringAttributes(new ColoringAttributes(0f, 0f, 0f,
                                                         ColoringAttributes.SHADE_GOURAUD));
    setAppearance(meshApp);
  }

  /**
   * 
   * @param majorRadius Radius of bigger circle.
   * @param minorRadius Radius of smaller circle.
   * @param majorSamples Sample of bigger circle.
   * @param minorSamples Sample of smaller circle.
   * @return Geometry of torus.
   */
  private Geometry createGeometry(float majorRadius, float minorRadius, int majorSamples,
                                  int minorSamples)
  {
    IndexedQuadArray geometry =
        new IndexedQuadArray(majorSamples * minorSamples, GeometryArray.COORDINATES,
                             4 * majorSamples * minorSamples);

    Point3f[] vertices = new Point3f[majorSamples * minorSamples];
    for (int i = 0; i < minorSamples; i++)
      vertices[i] =
          new Point3f((float) Math.cos(i * 2 * Math.PI / minorSamples) * minorRadius +
                      majorRadius, (float) Math.sin(i * 2 * Math.PI / minorSamples) *
                                   minorRadius, 0);

    for (int i = 1; i < majorSamples; i++)
    {
      Transform3D t3d = new Transform3D();
      t3d.rotY(i * 2 * Math.PI / majorSamples);
      for (int j = 0; j < minorSamples; j++)
      {
        vertices[i * minorSamples + j] = new Point3f();
        t3d.transform(vertices[j], vertices[i * minorSamples + j]);
      }
    }

    int[] quadIndices = new int[4 * majorSamples * minorSamples];
    for (int i = 0; i < majorSamples; i++)
      for (int j = 0; j < minorSamples; j++)
      {
        quadIndices[4 * (i * minorSamples + j)] = i * minorSamples + j;
        quadIndices[4 * (i * minorSamples + j) + 1] =
            (i + 1) % majorSamples * minorSamples + j;
        quadIndices[4 * (i * minorSamples + j) + 2] =
            (i + 1) % majorSamples * minorSamples + (j + 1) % minorSamples;
        quadIndices[4 * (i * minorSamples + j) + 3] =
            i * minorSamples + (j + 1) % minorSamples;
      }

    geometry.setCoordinates(0, vertices);
    geometry.setCoordinateIndices(0, quadIndices);

    // Utility code to automatically generate normals.
    GeometryInfo gInfo = new GeometryInfo(geometry);
    new NormalGenerator().generateNormals(gInfo);
    return gInfo.getIndexedGeometryArray();
  }
}
