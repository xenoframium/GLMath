package xenoframium.glmath.linearalgebra;

//z = r + at
public class Line3 {
	public Vec3 r0;
	public Vec3 a;
	
	public Line3(Vec3 r, Vec3 a) {
		this.r0 = r;
		this.a = a;
	}
	
	public Line3(float x0, float a, float y0, float b, float z0, float c) {
		r0 = new Vec3(x0, y0, z0);
		this.a = new Vec3(a, b, c);
	}
	
	public Line3 transform(Mat4 transformationMatrix) {
		Vec4 newR = transformationMatrix.mult(new Vec4(r0.x, r0.y, r0.z, 1));
		Vec4 newA = transformationMatrix.mult(new Vec4(a.x, a.y, a.z, 0));
		return new Line3(new Vec3(newR.x, newR.y, newR.z), new Vec3(newA.x, newA.y, newA.z));
	}
	
	public Vec3 getPointAtT(float t) {
		return r0.add(a.mult(t));
	}
}
