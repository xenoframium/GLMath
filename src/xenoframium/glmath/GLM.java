package xenoframium.glmath;

import xenoframium.glmath.linearalgebra.Line3f;
import xenoframium.glmath.linearalgebra.Matrix4;
import xenoframium.glmath.linearalgebra.Plane;
import xenoframium.glmath.linearalgebra.Triangle;
import xenoframium.glmath.linearalgebra.Vector3;

public final class GLM {
	public static float epsilon = 1e-5f;
	
	public static Vector3 findLinePlaneIntersection(Line3f line, Plane plane) {
		float a = plane.n.x;
		float b = plane.n.y;
		float c = plane.n.z;
		float d = plane.r0.x * plane.n.x + plane.r0.y * plane.n.y + plane.r0.z * plane.n.z;
		float rhs = d - (a * line.r0.x + b * line.r0.y + c * line.r0.z);
		float lhs = a * line.a.x + b * line.a.y + c * line.a.z;
		return line.getPointAtT(rhs / lhs);
	}

	public static Line3f lineFromPoints(Vector3 point1, Vector3 point2) {
		return new Line3f(point1, point1.subtract(point2));
	}

	public static Plane planeFromTriangle(Triangle triangle) {
		Vector3 normal = triangle.b.subtract(triangle.a).cross(triangle.c.subtract(triangle.b));
		return new Plane(normal, triangle.a);
	}

	public static boolean isPointInTriangle(Triangle triangle, Vector3 point) {
		Vector3 v0 = triangle.b.subtract(triangle.a);
		Vector3 v1 = triangle.c.subtract(triangle.a);
		Vector3 v2 = point.subtract(triangle.a);

		if (v2.dot(v0.cross(v1)) > epsilon) {
			return false;
		}

		float v0mag = v0.magnitudeSquared();
		float v1mag = v1.magnitudeSquared();
		
		float d01 = v0.dot(v1);
		float d20 = v2.dot(v0);
		float d21 = v2.dot(v1);

		float inverseDenominator = 1 / (v0mag * v1mag - d01 * d01);

		float u = inverseDenominator * (v1mag * d20 - d01 * d21);
		float v = inverseDenominator * (v0mag * d21 - d01 * d20);

		return u >= 0 && v >= 0 && u + v <= 1;
	}
	
	public static boolean doesLineIntersectTriangle(Line3f line, Triangle triangle) {
		return isPointInTriangle(triangle, findLinePlaneIntersection(line, planeFromTriangle(triangle)));
	}
	
	public static Matrix4 perspective(float fov, float aspect, float near, float far) {
		Matrix4 perspectiveMatrix = new Matrix4();

		float angle = (float) Math.toRadians(fov);
		float f = (float) (1 / Math.tan(angle * 0.5));
		perspectiveMatrix.m[0][0] = f / aspect;
		perspectiveMatrix.m[1][1] = f;
		perspectiveMatrix.m[2][2] = (far + near) / (near - far);
		perspectiveMatrix.m[2][3] = -1;
		perspectiveMatrix.m[3][2] = (2 * far * near) / (near - far);
		perspectiveMatrix.m[3][3] = 0;

		return perspectiveMatrix;
	}
	
	public static Matrix4 ortho(float width, float height, float near, float far) {
		Matrix4 orthographicMatrix = new Matrix4();

		float right = width / 2;
		float top = height / 2;
		
		orthographicMatrix.m[0][0] = 1 / right;
		orthographicMatrix.m[1][1] = 1 / top;
		orthographicMatrix.m[2][2] = -2 / (far - near);
		orthographicMatrix.m[3][2] = (near + far) / (near - far);

		return orthographicMatrix;
	}

	public static Matrix4 cameraLookAt(Vector3 cameraPos, Vector3 viewTarget, Vector3 up) {
		Vector3 viewDirection = new Vector3(viewTarget).subtract(cameraPos).normalize();
		Vector3 upDirection = new Vector3(up).normalize();
		Vector3 rightDirection = new Vector3(viewDirection).cross(upDirection).normalize();
		upDirection = new Vector3(rightDirection).cross(viewDirection);

		Matrix4 viewMatrix = new Matrix4();

		viewMatrix.m[0][0] = rightDirection.x;
		viewMatrix.m[0][1] = upDirection.x;
		viewMatrix.m[0][2] = -viewDirection.x;

		viewMatrix.m[1][0] = rightDirection.y;
		viewMatrix.m[1][1] = upDirection.y;
		viewMatrix.m[1][2] = -viewDirection.y;

		viewMatrix.m[2][0] = rightDirection.z;
		viewMatrix.m[2][1] = upDirection.z;
		viewMatrix.m[2][2] = -viewDirection.z;

		viewMatrix.m[3][0] = -rightDirection.dot(cameraPos);
		viewMatrix.m[3][1] = -upDirection.dot(cameraPos);
		viewMatrix.m[3][2] = viewDirection.dot(cameraPos);

		return viewMatrix;
	}
}
