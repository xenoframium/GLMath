package xenoframium.glmath.linearalgebra;

public class Triangle {
	public Vector3 a;
	public Vector3 b;
	public Vector3 c;

	public Triangle(Vector3 vert1, Vector3 vert2, Vector3 vert3) {
		a = vert1;
		b = vert2;
		c = vert3;
	}

	public Triangle transform(Matrix4 transformationMatrix) {
		Vector4 newA = transformationMatrix.multiply(new Vector4(a.x, a.y, a.z, 1));
		Vector4 newB = transformationMatrix.multiply(new Vector4(b.x, b.y, b.z, 1));
		Vector4 newC = transformationMatrix.multiply(new Vector4(c.x, c.y, c.z, 1));
		return new Triangle(new Vector3(newA.x, newA.y, newA.z), new Vector3(newB.x, newB.y, newB.z),
				new Vector3(newC.x, newC.y, newC.z));
	}
}
