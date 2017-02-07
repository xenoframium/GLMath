package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.quaternion.Quaternion;
import xenoframium.glmath.util.GLMUtil;

public class ImmutableMatrix4 {

	private static final float[][] identity = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 1 } };

	private final float[][] m;

	public ImmutableMatrix4() {
		this.m = GLMUtil.deepCopy(identity);
	}

	public ImmutableMatrix4(ImmutableMatrix4 mat) {
		this.m = GLMUtil.deepCopy(mat.m);
	}

	public ImmutableMatrix4(float[][] m) {
		this.m = GLMUtil.deepCopy(m);
	}

	public static ImmutableMatrix4 getIdentity() {
		return new ImmutableMatrix4(GLMUtil.deepCopy(identity));
	}

	public float get(int c, int r) {
		return m[c][r];
	}

	public ImmutableMatrix4 multiply(ImmutableMatrix4 mat) {
		return new ImmutableMatrix4(MatrixMath.multiply(m, mat.m));
	}

	public ImmutableMatrix4 multiply(float scalar) {
		return new ImmutableMatrix4(MatrixMath.multiply(scalar, m));
	}

	public ImmutableMatrix4 add(ImmutableMatrix4 mat) {
		return new ImmutableMatrix4(MatrixMath.add(m, mat.m));
	}

	public ImmutableMatrix4 subtract(ImmutableMatrix4 mat) {
		return new ImmutableMatrix4(MatrixMath.subtract(m, mat.m));
	}

	public float determinant() {
		return MatrixMath.determinant(m);
	}

	public ImmutableMatrix4 transpose() {
		return new ImmutableMatrix4(MatrixMath.transpose(m));
	}

	public ImmutableMatrix4 inverse() {
		return new ImmutableMatrix4(MatrixMath.inverse(m));
	}

	public ImmutableMatrix4 translate(Vector3 vec) {
		return multiply(new ImmutableMatrix4(
				new float[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 }, { vec.x, vec.y, vec.z, 1 } }));
	}

	public ImmutableMatrix4 scale(Vector3 vec) {
		return multiply(new ImmutableMatrix4(
				new float[][] { { vec.x, 0, 0, 0 }, { 0, vec.y, 0, 0 }, { 0, 0, vec.z, 0 }, { 0, 0, 0, 1 } }));
	}

	public ImmutableMatrix4 rotate(Quaternion quat) {
		return multiply(quat.toRotationMatrix().toImmutableMatrix4());
	}

	public ImmutableMatrix4 rotate(Vector3 axis, float angle) {
		return multiply(new Quaternion(axis, angle).toRotationMatrix().toImmutableMatrix4());
	}

	public Vector3 multiply(Vector3 vec) {
		float[] result = MatrixMath.multiply(m, vec.toArray());

		return new Vector3(result[0], result[1], result[2]);
	}

	public Matrix4 toMatrix4() {
		return new Matrix4(m);
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
