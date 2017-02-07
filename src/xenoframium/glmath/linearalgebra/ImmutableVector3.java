package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class ImmutableVector3 {
	
	public final float x;
	public final float y;
	public final float z;
	
	public ImmutableVector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public ImmutableVector3(ImmutableVector3 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public float dot(ImmutableVector3 vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}
	
	public ImmutableVector3 cross(ImmutableVector3 vec) {
		return new ImmutableVector3(y * vec.z - z * vec.y, z * vec.x - x * vec.z, x * vec.y - y * vec.x);
	}
	
	public ImmutableVector3 add(ImmutableVector3 vec) {
		return new ImmutableVector3(x + vec.x, y + vec.y, z + vec.z);
	}
	
	public ImmutableVector3 subtract(ImmutableVector3 vec) {
		return new ImmutableVector3(x - vec.x, y - vec.y, z - vec.z);
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public float magnitudeSquared() {
		return x * x + y * y  + z * z;
	}
	
	public ImmutableVector3 multiply(float scalar) {
		return new ImmutableVector3(x * scalar, y * scalar, z * scalar);
	}
	
	public ImmutableVector3 divide(float scalar) {
		float inverseDenom = 1 / scalar;
		return new ImmutableVector3(x * inverseDenom, y * inverseDenom, z * inverseDenom);
	}
	
	public ImmutableVector3 normalize() {
		return divide(magnitude());
	}
	
	public float[] toArray() {
		return new float[]{x, y, z};
	}
	
	public Vector3 toVector3() {
		return new Vector3(x, y, z);
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
