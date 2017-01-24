package RealCalculator;

import LinearAlgebra.RealTransformations;
import LinearAlgebra.RealVector;

public class RealIntersections {
	public static RealVector boundaryLinePoint(int CenterX, int CenterY, RealVector in, RealVector out) {
		if (in!=null&&out!=null) {
		RealVector center = new RealVector(2);
		center.setValue(0, CenterX); center.setValue(1, CenterY);
		RealVector relIn = RealTransformations.dif(in, center);
		RealVector relOut = RealTransformations.dif(out, center);
		
		RealVector topLeft = new RealVector(-250,-250);
		RealVector topRight = new RealVector(250,-250);
		RealVector botLeft = new RealVector(-250, 250);
		RealVector botRight = new RealVector(250,250);
		
		
		
		
		RealVector leftIntersect = intersection(relIn, relOut, topLeft, botLeft);
		RealVector rightIntersect = intersection(relIn, relOut, topRight, botRight);
		RealVector topIntersect = intersection(relIn, relOut, topLeft, topRight);
		RealVector botIntersect = intersection(relIn, relOut, botRight, botLeft);
		
		
		
		int totalIntersections = 0;
		if (leftIntersect!=null) {totalIntersections++;}
		if (rightIntersect!=null) {totalIntersections++;}
		if (topIntersect!=null) {totalIntersections++;}
		if (botIntersect!=null) {totalIntersections++;}
		
		if (totalIntersections==1) {
			if (leftIntersect!=null) {return leftIntersect;}
			if (rightIntersect!=null) {return rightIntersect;}
			if (topIntersect!=null) {return topIntersect;}
			if (botIntersect!=null) {return botIntersect;}
			return null;
			
		} else if (totalIntersections==2) {
			RealVector doubles = new RealVector(4);
			RealVector one = null; RealVector two = null;
			boolean foundOneAlready = false;
			if (leftIntersect!=null) {one = leftIntersect; foundOneAlready = true;}
			if (rightIntersect!=null) {
				if (foundOneAlready) {
					two = rightIntersect;
				} else {
					one = rightIntersect;
					foundOneAlready = true;
				}
			}
			if (topIntersect!=null) {
				if (foundOneAlready) {
					two = topIntersect;
				} else {
					one = topIntersect;
					foundOneAlready = true;
				}
			}
			if (botIntersect!=null) {
				if (foundOneAlready) {
					two = botIntersect;
				} else {
					one = botIntersect;
					foundOneAlready = true;
				}
			}
			if (one!=null&&two!=null) {
				doubles.setValue(0, one.getValue(0));
				doubles.setValue(1, one.getValue(1));
				doubles.setValue(2, two.getValue(0));
				doubles.setValue(3, two.getValue(1));
				return doubles;
			} else {
				return null;
			}
			
			
		} else {
			return null;
		}
			
		}
		return new RealVector(2);
	}
	
	private static RealVector intersection(RealVector a, RealVector b, RealVector c, RealVector d) {
		double x1=a.getValue(0);
		double x2=b.getValue(0);
		double x3=c.getValue(0);
		double x4=d.getValue(0);
		
		double y1=a.getValue(1);
		double y2=b.getValue(1);
		double y3=c.getValue(1);
		double y4=d.getValue(1);
		
		double x = 0.0;
		double y = 0.0;
		if (x2!=x1&&x3!=x4) {
			double m1 = (y2-y1)/(x2-x1);
			double m2 = (y3-y4)/(x4-x3);
			
			 x = ((y3-y1)+(x1*m1)+(x3*m2))/(m1+m2);
			 y = (m1*(x-x1))+y1;
		} else {
			if (x2==x1) {
				x=x2;
				double m2 = (y3-y4)/(x4-x3);
				y = (m2*(x-x3))+y3;
			} else {
				x=x3;
				double m1 = (y2-y1)/(x2-x1);
				 y = (m1*(x-x1))+y1;
				
			}
		}
		
		
		RealVector awesome = new RealVector(2);
		awesome.setValue(0, x);
		awesome.setValue(1, y);
		
		
		
		if (betweenVectors(a,b,awesome)&&betweenVectors(c,d,awesome)) {
			return awesome;
		} else {
			return null;
		}
		
		
		
	}
	
	private static boolean betweenVectors(RealVector a, RealVector b, RealVector intersect) {
		int x1 = (int)a.getValue(0);	int y1 = (int)a.getValue(1);
		int x2 = (int)b.getValue(0);	int y2 = (int)b.getValue(1);
		int x3 = (int)intersect.getValue(0);	int y3 = (int)intersect.getValue(1);
		
		
		if (x1>x2) {
			if (x3>x1||x3<x2) {
				return false;
			}
		} else {
			if (x3>x2||x3<x1) {
				return false;
			}
		}
		if (y1>y2) {
			if (y3>y1||y3<y2) {
				return false;
			}
		} else {
			if (y3>y2||y3<y1) {
				return false;
			}
		}
		return true;
	}
	
	
	
}
