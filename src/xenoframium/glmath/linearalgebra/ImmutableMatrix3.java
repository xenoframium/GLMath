package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class ImmutableMatrix3 {

	private static final float[][] identity = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };

	private final float[][] m;

	public ImmutableMatrix3() {
		this.m = GLMUtil.deepCopy(identity);
	}

	public ImmutableMatrix3(ImmutableMatrix3 mat) {
		this.m = GLMUtil.deepCopy(mat.m);
	}

	public ImmutableMatrix3(float[][] m) {
		this.m = GLMUtil.deepCopy(m);
	}

	public static ImmutableMatrix3 getIdentity() {
		return new ImmutableMatrix3(GLMUtil.deepCopy(identity));
	}
	
	public float get(int c, int r) {
		return m[c][r];
	}
	
	public ImmutableMatrix3 multiply(ImmutableMatrix3 mat) {
		return new ImmutableMatrix3(MatrixMath.multiply(m, mat.m));
	}

	public ImmutableMatrix3 multiply(float scalar) {
		return new ImmutableMatrix3(MatrixMath.multiply(scalar, m));
	}

	public ImmutableMatrix3 add(ImmutableMatrix3 mat) {
		return new ImmutableMatrix3(MatrixMath.add(m, mat.m));
	}

	public ImmutableMatrix3 subtract(ImmutableMatrix3 mat) {
		return new ImmutableMatrix3(MatrixMath.subtract(m, mat.m));
	}

	public float determinant() {
		return MatrixMath.determinant(m);
	}

	public ImmutableMatrix3 transpose() {
		return new ImmutableMatrix3(MatrixMath.transpose(m));
	}

	public ImmutableMatrix3 inverse() {
		return new ImmutableMatrix3(MatrixMath.inverse(m));
	}

	public Vector3 multiply(Vector3 vec) {
		float[] result = MatrixMath.multiply(m, vec.toArray());
		
		return new Vector3(result[0], result[1], result[2]);
	}
	
	public Matrix2 toMatrix3() {
		return new Matrix2(m);
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
