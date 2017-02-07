package xenoframium.glmath.quaternion;

import xenoframium.glmath.linearalgebra.Matrix4;
import xenoframium.glmath.linearalgebra.Vector3;

public class Quaternion {

	public float w = 1;
	public float x = 0;
	public float y = 0;
	public float z = 0;

	public Quaternion() {
	}

	public Quaternion(Quaternion quat) {
		w = quat.w;
		x = quat.x;
		y = quat.y;
		z = quat.z;
	}
	
	public Quaternion(float w, float x, float y, float z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Quaternion(Vector3 vec, float radians) {
		float m = (float) Math.sin(radians * 0.5);
		this.w = (float) Math.cos(radians * 0.5);
		this.x = m * vec.x;
		this.y = m * vec.y;
		this.z = m * vec.z;
	}

	public float magnitudeSquared() {
		return w * w + x * x + y * y + z * z;
	}
	
	public float magnitude() {
		return (float) Math.sqrt(w * w + x * x + y * y + z * z);
	}
	
	public Quaternion multiply(float scalar) {
		return new Quaternion(w * scalar, x * scalar, y * scalar, z * scalar);
	}

	public Quaternion normalize() {
		return multiply(1 / this.magnitude());
	}

	public Quaternion conjugate() {
		return new Quaternion(w, -x, -y, -z);
	}

	public Matrix4 toRotationMatrix() {
		Matrix4 res = new Matrix4();
		Quaternion t = this.normalize();

		float r = t.w, i = t.x, j = t.y, k = t.z;

		t = null;

		res.m[0][0] = 1 - (2 * j*j) - (2 * k*k);
		res.m[0][1] = 2 * (i * j + k * r);
		res.m[0][2] = 2 * (i * k - j * r);
		res.m[0][3] = 0;

		res.m[1][0] = 2 * (i * j - k * r);
		res.m[1][1] = 1 - (2 * i*i) - (2 * k*k);
		res.m[1][2] = 2 * (j * k + i * r);
		res.m[1][3] = 0;

		res.m[2][0] = 2 * (i * k + j * r);
		res.m[2][1] = 2 * (j * k - i * r);
		res.m[2][2] = 1 - (2 * i*i) - (2 * j*j);
		res.m[2][3] = 0;

		res.m[3][0] = 0;
		res.m[3][1] = 0;
		res.m[3][2] = 0;
		res.m[3][3] = 1;
		
		return res;
	}

}