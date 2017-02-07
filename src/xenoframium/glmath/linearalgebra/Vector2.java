package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class Vector2 {
	
	public float x;
	public float y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public float dot(Vector2 vec) {
		return x * vec.x + y * vec.y;
	}
	
	public Vector2 add(Vector2 vec) {
		x += vec.x;
		y += vec.y;
		return this;
	}
	
	public Vector2 subtract(Vector2 vec) {
		x -= vec.x;
		y -= vec.y;
		return this;
	}
	
	public float magnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float magnitudeSquared() {
		return x * x + y * y;
	}
	
	public Vector2 multiply(float scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}
	
	public Vector2 divide(float scalar) {
		float inverseDenom = 1 / scalar;
		x *= inverseDenom;
		y *= inverseDenom;
		return this;
	}
	
	public Vector2 normalize() {
		return divide(magnitude());
	}
	
	public float[] toArray() {
		return new float[]{x, y};
	}
	
	public ImmutableVector2 toImmutableVector2() {
		return new ImmutableVector2(x, y);
	}
	
	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(2);
		buffer.put(x);
		buffer.put(y);
		buffer.flip();
		return buffer;
	}
}
