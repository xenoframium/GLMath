package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class Vector3 {
	
	public float x;
	public float y;
	public float z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector3 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public float dot(Vector3 vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}
	
	public Vector3 cross(Vector3 vec) {
		float nx = y * vec.z - z * vec.y;
		float ny = z * vec.x - x * vec.z;
		float nz = x * vec.y - y * vec.x;
		x = nx;
		y = ny;
		z = nz;
		return this;
	}
	
	public Vector3 add(Vector3 vec) {
		x += vec.x;
		y += vec.y;
		z += vec.z;
		return this;
	}
	
	public Vector3 subtract(Vector3 vec) {
		x -= vec.x;
		y -= vec.y;
		z -= vec.z;
		return this;
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float magnitudeSquared() {
		return x * x + y * y  + z * z;
	}
	
	public Vector3 multiply(float scalar) {
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}
	
	public Vector3 divide(float scalar) {
		float inverseDenom = 1 / scalar;
		x *= inverseDenom;
		y *= inverseDenom;
		z *= inverseDenom;
		return this;
	}
	
	public Vector3 normalize() {
		return divide(magnitude());
	}
	
	public float[] toArray() {
		return new float[]{x, y, z};
	}
	
	public ImmutableVector3 toImmutableVector3() {
		return new ImmutableVector3(x, y, z);
	}

	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(3);
		buffer.put(x);
		buffer.put(y);
		buffer.put(z);
		buffer.flip();
		return buffer;
	}
}
