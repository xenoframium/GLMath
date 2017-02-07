package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class ImmutableVector2 {
	
	public final float x;
	public final float y;
	
	public ImmutableVector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public ImmutableVector2(ImmutableVector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}

	public float dot(ImmutableVector2 vec) {
		return x * vec.x + y * vec.y;
	}
	
	public ImmutableVector2 add(ImmutableVector2 vec) {
		return new ImmutableVector2(x + vec.x, y + vec.y);
	}
	
	public ImmutableVector2 subtract(ImmutableVector2 vec) {
		return new ImmutableVector2(x - vec.x, y - vec.y);
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float magnitudeSquared() {
		return x * x + y * y;
	}
	
	public ImmutableVector2 multiply(float scalar) {
		return new ImmutableVector2(x * scalar, y * scalar);
	}
	
	public ImmutableVector2 divide(float scalar) {
		float inverseDenom = 1 / scalar;
		return new ImmutableVector2(x * inverseDenom, y * inverseDenom);
	}
	
	public ImmutableVector2 normalize() {
		return divide(magnitude());
	}
	
	public float[] toArray() {
		return new float[]{x, y};
	}
	
	public Vector2 toVector2() {
		return new Vector2(x, y);
	}
	
	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(2);
		buffer.put(x);
		buffer.put(y);
		buffer.flip();
		return buffer;
	}
}
