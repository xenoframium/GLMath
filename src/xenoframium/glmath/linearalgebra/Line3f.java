package xenoframium.glmath.linearalgebra;

//z = r + at
public class Line3f {
	public Vector3 r0;
	public Vector3 a;
	
	public Line3f(Vector3 r, Vector3 a) {
		this.r0 = r;
		this.a = a;
	}
	
	public Line3f(float x0, float a, float y0, float b, float z0, float c) {
		r0 = new Vector3(x0, y0, z0);
		this.a = new Vector3(a, b, c);
	}
	
	public Line3f transform(Matrix4 transformationMatrix) {
		Vector4 newR = transformationMatrix.multiply(new Vector4(r0.x, r0.y, r0.z, 1));
		Vector4 newA = transformationMatrix.multiply(new Vector4(a.x, a.y, a.z, 0));
		return new Line3f(new Vector3(newR.x, newR.y, newR.z), new Vector3(newA.x, newA.y, newA.z));
	}
	
	public Vector3 getPointAtT(float t) {
		return r0.add(a.multiply(t));
	}
}
