package xenoframium.glmath.quaternion;

import xenoframium.glmath.linearalgebra.Mat4;
import xenoframium.glmath.linearalgebra.Vec3;

public class Quat {

	public float w = 1;
	public float x = 0;
	public float y = 0;
	public float z = 0;

	public Quat() {
	}

	public Quat(Quat quat) {
		w = quat.w;
		x = quat.x;
		y = quat.y;
		z = quat.z;
	}
	
	public Quat(float w, float x, float y, float z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Quat(Vec3 vec, float radians) {
		float m = (float) Math.sin(radians * 0.5);
		this.w = (float) Math.cos(radians * 0.5);
		this.x = m * vec.x;
		this.y = m * vec.y;
		this.z = m * vec.z;
	}

	public float magSq() {
		return w * w + x * x + y * y + z * z;
	}
	
	public float mag() {
		return (float) Math.sqrt(w * w + x * x + y * y + z * z);
	}
	
	public Quat mult(float scalar) {
		return new Quat(w * scalar, x * scalar, y * scalar, z * scalar);
	}

	public Quat normalize() {
		return mult(1 / this.mag());
	}

	public Quat conj() {
		return new Quat(w, -x, -y, -z);
	}

	public Mat4 toRotMat() {
		Mat4 res = new Mat4();
		Quat t = this.normalize();

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