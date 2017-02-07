package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.quaternion.Quaternion;
import xenoframium.glmath.util.GLMUtil;

public class Matrix4 {

	private static final float[][] identity = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

	public float[][] m;

	public Matrix4() {
		this.m = GLMUtil.deepCopy(identity);
	}

	public Matrix4(Matrix4 mat) {
		this.m = GLMUtil.deepCopy(mat.m);
	}

	public Matrix4(float[][] m) {
		this.m = m;
	}

	public static Matrix4 getIdentity() {
		return new Matrix4(GLMUtil.deepCopy(identity));
	}

	public Matrix4 multiply(Matrix4 mat) {
		m = MatrixMath.multiply(m, mat.m);
		return this;
	}

	public Matrix4 multiply(float scalar) {
		m = MatrixMath.multiply(scalar, m);
		return this;
	}

	public Matrix4 add(Matrix4 mat) {
		m = MatrixMath.add(m, mat.m);
		return this;
	}

	public Matrix4 subtract(Matrix4 mat) {
		m = MatrixMath.subtract(m, mat.m);
		return this;
	}

	public float determinant() {
		return MatrixMath.determinant(m);
	}

	public Matrix4 transpose() {
		m = MatrixMath.transpose(m);
		return this;
	}

	public Matrix4 inverse() {
		m = MatrixMath.inverse(m);
		return this;
	}

	public Matrix4 translate(Vector3 vec) {
		m = new Matrix4(new float[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { vec.x, vec.y, vec.z, 1 } })
				.multiply(this).m;
		return this;
	}

	public Matrix4 scale(Vector3 vec) {
		m = new Matrix4(new float[][] { { vec.x, 0, 0, 0 }, { 0, vec.y, 0, 0 }, { 0, 0, vec.z, 0 }, { 0, 0, 0, 1 } })
				.multiply(this).m;
		return this;
	}

	public Matrix4 rotate(Quaternion quat) {
		m = quat.toRotationMatrix().multiply(this).m;
		return this;
	}

	public Matrix4 rotate(Vector3 axis, float angle) {
		rotate(new Quaternion(axis, angle));
		return this;
	}

	public Vector4 multiply(Vector4 vec) {
		float[] result = MatrixMath.multiply(m, vec.toArray());

		return new Vector4(result[0], result[1], result[2], result[3]);
	}

	public ImmutableMatrix4 toImmutableMatrix4() {
		return new ImmutableMatrix4(m);
	}

	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(16);
		buffer.put(m[0]);
		buffer.put(m[1]);
		buffer.put(m[2]);
		buffer.put(m[3]);
		buffer.flip();
		return buffer;
	}
}
