package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class Matrix3 {

	private static final float[][] identity = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };

	public float[][] m;

	public Matrix3() {
		this.m = GLMUtil.deepCopy(identity);
	}

	public Matrix3(Matrix3 mat) {
		this.m = GLMUtil.deepCopy(mat.m);
	}

	public Matrix3(float[][] m) {
		this.m = m;
	}

	public static Matrix3 getIdentity() {
		return new Matrix3(GLMUtil.deepCopy(identity));
	}

	public Matrix3 multiply(Matrix3 mat) {
		m = MatrixMath.multiply(m, mat.m);
		return this;
	}

	public Matrix3 multiply(float scalar) {
		m = MatrixMath.multiply(scalar, m);
		return this;
	}

	public Matrix3 add(Matrix3 mat) {
		m = MatrixMath.add(m, mat.m);
		return this;
	}

	public Matrix3 subtract(Matrix3 mat) {
		m = MatrixMath.subtract(m, mat.m);
		return this;
	}

	public float determinant() {
		return MatrixMath.determinant(m);
	}

	public Matrix3 transpose() {
		m = MatrixMath.transpose(m);
		return this;
	}

	public Matrix3 inverse() {
		m = MatrixMath.inverse(m);
		return this;
	}
	
	public Vector3 multiply(Vector3 vec) {
		float[] result = MatrixMath.multiply(m, vec.toArray());
		
		return new Vector3(result[0], result[1], result[2]);
	}
	
	public ImmutableMatrix3 toImmutableMatrix3() {
		return new ImmutableMatrix3(m);
	}
	
	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(9);
		buffer.put(m[0]);
		buffer.put(m[1]);
		buffer.put(m[2]);
		buffer.flip();
		return buffer;
	}
}
