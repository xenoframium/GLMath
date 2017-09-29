package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class Vec2 {
	
	public float x;
	public float y;

	private FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(2);

	public Vec2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2(Vec2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public float dot(Vec2 vec) {
		return x * vec.x + y * vec.y;
	}
	
	public Vec2 add(Vec2... vecs) {
		float tx = x, ty = y;
		for (Vec2 vec : vecs) {
			tx += vec.x;
			ty += vec.y;
		}
		x = tx;
		y = ty;
		return this;
	}
	
	public Vec2 subt(Vec2... vecs) {
		float tx = x, ty = y;
		for (Vec2 vec : vecs) {
			tx -= vec.x;
			ty -= vec.y;
		}
		x = tx;
		y = ty;
		return this;
	}
	
	public float mag() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float magSq() {
		return x * x + y * y;
	}
	
	public Vec2 mult(float scalar) {
		x *= scalar;
		y *= scalar;
		return this;
	}
	
	public Vec2 div(float scalar) {
		float inverseDenom = 1 / scalar;
		x *= inverseDenom;
		y *= inverseDenom;
		return this;
	}
	
	public Vec2 normalize() {
		return div(mag());
	}
	
	public float[] asArr() {
		return new float[]{x, y};
	}
}
