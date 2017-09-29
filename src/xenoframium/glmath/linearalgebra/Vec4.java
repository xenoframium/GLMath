package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class Vec4 {

	private FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(4);
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Vec4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Vec4(Vec4 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		this.w = vec.w;
	}
	
	public Vec4(Vec3 vec, float w) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		this.w = w;
	}
	
	public float dot(Vec4 vec) {
		return x * vec.x + y * vec.y + z * vec.z + w * vec.w;
	}
	
	public Vec4 add(Vec4 vec) {
		return new Vec4(x + vec.x, y + vec.y, z + vec.z, w + vec.w);
	}
	
	public Vec4 subt(Vec4 vec) {
		return new Vec4(x - vec.x, y - vec.y, z - vec.z, w - vec.w);
	}
	
	public float mag() {
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public float magSq() {
		return x * x + y * y  + z * z + w * w;
	}
	
	public Vec4 mult(float scalar) {
		return new Vec4(x * scalar, y * scalar, z * scalar, w * scalar);
	}
	
	public Vec4 div(float scalar) {
		float inverseDenom = 1 / scalar;
		return new Vec4(x * inverseDenom, y * inverseDenom, z * inverseDenom, w * inverseDenom);
	}
	
	public Vec4 normalize() {
		return div(mag());
	}
	
	public float[] asArr() {
		return new float[]{x, y, z, w};
	}
}
