package LinearAlgebra;

public class ThreeDRotations {

	
	public static RealVector screenLocation(RealVector point, RealVector center) {
		RealMatrix a = subjugate(center,2,0,1);
		RealVector center2 = RealTransformations.product(a, center);
		RealVector point2 = RealTransformations.product(a, point);
		RealMatrix b = subjugate(center2,2,1,0);
		RealVector point3 = RealTransformations.product(b, point2);
		RealVector point4 = new RealVector(3);
		
		
		point4.setValue(0, point3.getValue(0)/point3.getValue(2));
		point4.setValue(1, -point3.getValue(1)/point3.getValue(2));
		point4.setValue(2, point3.getValue(2));
		
		
		return point4;
	}
	
	private static RealMatrix subjugate(RealVector center, int forward, int zero, int pivot) {
		//0 is x, 1 is y, 2 is z
		if (center.getSize()!=3) {
			return null;
		}
		RealMatrix a = new RealMatrix(3);
		//In the original model forward is x, zero is y, pivot is z.
		//Currently the matrix is blank with 3 0s.
		double x = center.getValue(forward);
		double y = center.getValue(zero);
		a.setValue(pivot, pivot, 1);
		double mag = Math.hypot(x, y);
		double Xx = x/mag;
		double Yx = y/mag;
		double Xy = -Yx;
		a.setValue(forward, forward, Xx);
		a.setValue(zero, zero, Xx);
		a.setValue(zero, forward, Yx);
		a.setValue(forward, zero, Xy);
	
		return a;
	}
	
	public static RealVector locateScreenPoint(RealVector twoLocation, RealVector center) {
		RealMatrix a = subjugate(center,2,0,1);
		RealVector center2 = RealTransformations.product(a, center);
		RealMatrix b = subjugate(center2,2,1,0);
		RealVector point3 = new RealVector(3);
		point3.setValue(2, twoLocation.getValue(2));
		point3.setValue(0, twoLocation.getValue(0)*twoLocation.getValue(2));
		point3.setValue(1, -twoLocation.getValue(1)*twoLocation.getValue(2));
		RealMatrix unb = RealTransformations.inverseMatrix3(b);
		
		RealMatrix una = RealTransformations.inverseMatrix3(a);
		RealVector point2 = RealTransformations.product(unb, point3);
		RealVector point1 = RealTransformations.product(una, point2);
		
		return point1;
		
		
	}
	
	
	
	
	
	
	
	
}
