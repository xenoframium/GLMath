package xenoframium.glmath.linearalgebra;

//n.(z - r) = 0
public class Plane {
	public Vector3 n;
	public Vector3 r0;
	
	public Plane(Vector3 n, Vector3 r) {
		this.n = n;
		this.r0 = r;
	}
	
	public Plane(float a, float b, float c, float d) {
		n = new Vector3(a, b, c);
		r0 = new Vector3(0, 0, d / c);
	}
	
	public Plane transform(Matrix4 transformationMatrix) {
		Vector4 newN = transformationMatrix.multiply(new Vector4(n.x, n.y, n.z, 0));
		Vector4 newR = transformationMatrix.multiply(new Vector4(r0.x, r0.y, r0.z, 1));
		return new Plane(new Vector3(newN.x, newN.y, newN.z), new Vector3(newR.x, newR.y, newR.z));
	}
}
