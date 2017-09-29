package xenoframium.glmath.linearalgebra;

public class Triangle {
	public Vec3 a;
	public Vec3 b;
	public Vec3 c;

	public Triangle(Vec3 vert1, Vec3 vert2, Vec3 vert3) {
		a = vert1;
		b = vert2;
		c = vert3;
	}

	public Triangle transform(Mat4 transformationMatrix) {
		Vec4 newA = transformationMatrix.mult(new Vec4(a.x, a.y, a.z, 1));
		Vec4 newB = transformationMatrix.mult(new Vec4(b.x, b.y, b.z, 1));
		Vec4 newC = transformationMatrix.mult(new Vec4(c.x, c.y, c.z, 1));
		return new Triangle(new Vec3(newA.x, newA.y, newA.z), new Vec3(newB.x, newB.y, newB.z),
				new Vec3(newC.x, newC.y, newC.z));
	}
}
