package xenoframium.glmath.linearalgebra;

import java.nio.FloatBuffer;

import xenoframium.glmath.util.GLMUtil;

public class ImmutableMatrix2 {

	private static final float[][] identity = { { 1, 0 }, { 0, 1 } };

	private final float[][] m;

	public ImmutableMatrix2() {
		this.m = GLMUtil.deepCopy(identity);
	}

	public ImmutableMatrix2(ImmutableMatrix2 mat) {
		this.m = GLMUtil.deepCopy(mat.m);
	}

	public ImmutableMatrix2(float[][] m) {
		this.m = GLMUtil.deepCopy(m);
	}

	public static ImmutableMatrix2 getIdentity() {
		return new ImmutableMatrix2(GLMUtil.deepCopy(identity));
	}

	public float get(int c, int r) {
		return m[c][r];
	}
	
	public ImmutableMatrix2 multiply(ImmutableMatrix2 mat) {
		return new ImmutableMatrix2(MatrixMath.multiply(m, mat.m));
	}

	public ImmutableMatrix2 multiply(float scalar) {
		return new ImmutableMatrix2(MatrixMath.multiply(scalar, m));
	}

	public ImmutableMatrix2 add(ImmutableMatrix2 mat) {
		return new ImmutableMatrix2(MatrixMath.add(m, mat.m));
	}

	public ImmutableMatrix2 subtract(ImmutableMatrix2 mat) {
		return new ImmutableMatrix2(MatrixMath.subtract(m, mat.m));
	}

	public float determinant() {
		return MatrixMath.determinant(m);
	}

	public ImmutableMatrix2 transpose() {
		return new ImmutableMatrix2(MatrixMath.transpose(m));
	}

	public ImmutableMatrix2 inverse() {
		return new ImmutableMatrix2(MatrixMath.inverse(m));
	}

	public Vector2 multiply(Vector2 vec) {
		return new Vector2(vec.x * m[0][0] + vec.y * m[1][0], vec.x * m[0][1] + vec.y * m[1][1]);
	}
	
	public Matrix2 toMatrix2() {
		return new Matrix2(m);
	}
	
	public FloatBuffer asBuffer() {
		FloatBuffer buffer = GLMUtil.createDirectFloatBuffer(4);
		buffer.put(m[0]);
		buffer.put(m[1]);
		buffer.flip();
		return buffer;
	}
}
