package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class Vec3 {

	private FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(3);
	public float x;
	public float y;
	public float z;
	
	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3(Vec3 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public float dot(Vec3 vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}
	
	public Vec3 cross(Vec3 vec) {
		float nx = y * vec.z - z * vec.y;
		float ny = z * vec.x - x * vec.z;
		float nz = x * vec.y - y * vec.x;
		x = nx;
		y = ny;
		z = nz;
		return this;
	}
	
	public Vec3 add(Vec3... vecs) {
		float tx = x, ty = y, tz = z;
		for (Vec3 vec : vecs) {
			tx += vec.x;
			ty += vec.y;
			tz += vec.z;
		}
		x = tx;
		y = ty;
		z = tz;
		return this;
	}
	
	public Vec3 subt(Vec3... vecs) {
		float tx = x, ty = y, tz = z;
		for (Vec3 vec : vecs) {
			tx -= vec.x;
			ty -= vec.y;
			tz -= vec.z;
		}
		x = tx;
		y = ty;
		z = tz;
		return this;
	}
	
	public float mag() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float magSq() {
		return x * x + y * y  + z * z;
	}
	
	public Vec3 mult(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}
	
	public Vec3 div(float scalar) {
		float inverseDenom = 1 / scalar;
		x *= inverseDenom;
		y *= inverseDenom;
		z *= inverseDenom;
		return this;
	}
	
	public Vec3 normalize() {
		return div(mag());
	}
	
	public float[] asArr() {
		return new float[]{x, y, z};
	}
}
