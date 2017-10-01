package xenoframium.glmath.linearalgebra;

//n.(z - r) = 0
public class Plane {
	public Vec3 n;
	public Vec3 r0;
	
	public Plane(Vec3 n, Vec3 r) {
		this.n = n;
		this.r0 = r;
	}
	
	public Plane(float a, float b, float c, float d) {
		n = new Vec3(a, b, c);
		r0 = new Vec3(0, 0, d / c);
	}
	
	public Plane transform(Mat4 transformationMatrix) {
		Vec4 newN = transformationMatrix.mult(new Vec4(n.x, n.y, n.z, 0));
		Vec4 newR = transformationMatrix.mult(new Vec4(r0.x, r0.y, r0.z, 1));
		return new Plane(new Vec3(newN.x, newN.y, newN.z), new Vec3(newR.x, newR.y, newR.z));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Plane plane = (Plane) o;

		if (n != null ? !n.equals(plane.n) : plane.n != null) return false;
		return r0 != null ? r0.equals(plane.r0) : plane.r0 == null;
	}

	@Override
	public int hashCode() {
		int result = n != null ? n.hashCode() : 0;
		result = 31 * result + (r0 != null ? r0.hashCode() : 0);
		return result;
	}
}
