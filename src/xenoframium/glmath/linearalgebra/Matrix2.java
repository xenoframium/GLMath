package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class Matrix2 {

	private static final float[][] identity = { { 1, 0 }, { 0, 1 } };

	public float[][] m;

	public Matrix2() {
		this.m = GLMUtil.deepCopy(identity);
	}

	public Matrix2(Matrix2 mat) {
		this.m = GLMUtil.deepCopy(mat.m);
	}

	public Matrix2(float[][] m) {
		this.m = m;
	}

	public static Matrix2 getIdentity() {
		return new Matrix2(GLMUtil.deepCopy(identity));
	}
	
	public Matrix2 multiply(Matrix2 mat) {
		m = MatrixMath.multiply(m, mat.m);
		return this;
	}

	public Matrix2 multiply(float scalar) {
		m = MatrixMath.multiply(scalar, m);
		return this;
	}

	public Matrix2 add(Matrix2 mat) {
		m = MatrixMath.add(m, mat.m);
		return this;
	}

	public Matrix2 subtract(Matrix2 mat) {
		m = MatrixMath.subtract(m, mat.m);
		return this;
	}

	public float determinant() {
		return MatrixMath.determinant(m);
	}

	public Matrix2 transpose() {
		m = MatrixMath.transpose(m);
		return this;
	}

	public Matrix2 inverse() {
		m = MatrixMath.inverse(m);
		return this;
	}

	public Vector2 multiply(Vector2 vec) {
		return new Vector2(vec.x * m[0][0] + vec.y * m[1][0], vec.x * m[0][1] + vec.y * m[1][1]);
	}
	
	public ImmutableMatrix2 toImmutableMatrix2() {
		return new ImmutableMatrix2(m);
	}
	
	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(4);
		buffer.put(m[0]);
		buffer.put(m[1]);
		buffer.flip();
		return buffer;
	}
}
