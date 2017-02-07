package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class ImmutableVector4 {
	
	public final float x;
	public final float y;
	public final float z;
	public final float w;
	
	public ImmutableVector4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public ImmutableVector4(ImmutableVector4 vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		this.w = vec.w;
	}
	
	public ImmutableVector4(Vector3 vec, float w) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		this.w = w;
	}
	
	public float dot(ImmutableVector4 vec) {
		return x * vec.x + y * vec.y + z * vec.z + w * vec.w;
	}
	
	public ImmutableVector4 add(ImmutableVector4 vec) {
		return new ImmutableVector4(x + vec.x, y + vec.y, z + vec.z, w + vec.w);
	}
	
	public ImmutableVector4 subtract(ImmutableVector4 vec) {
		return new ImmutableVector4(x - vec.x, y - vec.y, z - vec.z, w - vec.w);
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public float magnitudeSquared() {
		return x * x + y * y  + z * z + w * w;
	}
	
	public ImmutableVector4 multiply(float scalar) {
		return new ImmutableVector4(x * scalar, y * scalar, z * scalar, w * scalar);
	}
	
	public ImmutableVector4 divide(float scalar) {
		float inverseDenom = 1 / scalar;
		return new ImmutableVector4(x * inverseDenom, y * inverseDenom, z * inverseDenom, w * inverseDenom);
	}
	
	public ImmutableVector4 normalize() {
		return divide(magnitude());
	}
	
	public float[] toArray() {
		return new float[]{x, y, z, w};
	}
	
	public Vector4 toFunctionalVector4() {
		return new Vector4(x, y, z, w);
	}
	
	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(4);
		buffer.put(x);
		buffer.put(y);
		buffer.put(z);
		buffer.put(w);
		buffer.flip();
		return buffer;
	}
}
