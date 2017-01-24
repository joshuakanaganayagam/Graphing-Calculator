package ComplexTriangles;

import ComplexCalculator.ComplexArithmetic;
import ComplexCalculator.ComplexNumber;
import ComplexCalculator.ComplexTrigonometry;
import LinearAlgebra.ComplexMatrix;
import LinearAlgebra.ComplexTransformations;
import LinearAlgebra.ComplexVector;
import LinearAlgebra.RealMatrix;
import LinearAlgebra.RealTransformations;
import LinearAlgebra.RealVector;

public class TriangleAngles {
/**The goal of this class is to produce the angles of a given triangle.
 * Initially a real triangle will be presented followed by a Complex equivalent.
 */
	public static ComplexNumber calculateComplexAngle(ComplexTriangle triangle, boolean isTrial1, int n) {
		ComplexVector A = triangle.getA();
		ComplexVector B = triangle.getB();
		ComplexVector C = triangle.getC();
		ComplexVector a;
		ComplexVector b;
		ComplexVector c;
		
		ComplexVector e;
		ComplexVector f;
		ComplexVector g;
		a=A;
		if (isTrial1) {
			b=B;
			c=C;
		} else {
			c=B;
			b=C;
			if (n==2) {
				n=3;
			} else if (n==3) {
				n=2;				
			}
		}
		if (n==1) {
			e=c;
			f=a;
			g=b;
		} else if (n==2) {
			e=a;
			f=b;
			g=c;
			
		} else {
			e=b;
			f=c;
			g=a;
		}
		
		
		return calculatePreciseAngle(e,f,g);
	}
	
	
	
	
	




	public static double calculateAngle(RealTriangle triangle, boolean isTrial1, int n) {
		RealVector A = triangle.getA();
		RealVector B = triangle.getB();
		RealVector C = triangle.getC();
		RealVector a;
		RealVector b;
		RealVector c;
		RealVector e;
		RealVector f;
		RealVector g;
		a = A;
		if (isTrial1) {
			b=B;
			c=C;
		} else {
			c=B;
			b=C;
			if (n==2) {
				n = 3;
			} else if (n==3) {
				n = 2;
			}			
		}
		if (n==1) {
			e=c;
			f=a;
			g=b;
		} else if (n==2) {
			e=a;
			f=b;
			g=c;
			
		} else {
			e=b;
			f=c;
			g=a;
		}
		
		
		
		
		
		return calculatePreciseRealAngle(e,f,g);
		
	}
	
	private static ComplexNumber calculatePreciseAngle(ComplexVector a, ComplexVector b, ComplexVector c) {
		ComplexVector b2 = ComplexTransformations.dif(b, a);
		ComplexVector c2 = ComplexTransformations.dif(c, a);
		ComplexMatrix m = ctMatrix(b2.getValue(0), b2.getValue(1));
		ComplexVector b3 = ComplexTransformations.product(m, b2);
		ComplexVector c3 = ComplexTransformations.product(m, c2);
		ComplexVector c4 = ComplexTransformations.dif(c3, b3);
		RealMatrix g = new RealMatrix(2);
		g.setValue(0, 0, -1.0);
		g.setValue(1, 1, 1.0);
		ComplexMatrix gg = g.toComplex();
		ComplexVector c5 = ComplexTransformations.product(gg, c4);
		
		
		
		
		
		return ComplexTrigonometry.theta(c5);
	}
	
	
	
	private static double calculatePreciseRealAngle(RealVector a, RealVector b, RealVector c) {
		RealVector b2 = RealTransformations.dif(b, a);
		RealVector c2 = RealTransformations.dif(c, a);
		RealMatrix m = tMatrix(b2.getValue(0), b2.getValue(1));
		
		
		
		
		
		
		RealVector b3 = RealTransformations.product(m, b2);
		RealVector c3 = RealTransformations.product(m, c2);
		RealVector c4 = RealTransformations.dif(c3, b3);
		RealMatrix g = new RealMatrix(2);
		g.setValue(0, 0, -1.0);
		g.setValue(1, 1, 1.0);
		RealVector c5 = RealTransformations.product(g, c4);
		double ratio = c5.getValue(0)/Math.hypot(c5.getValue(1), c5.getValue(0));
		
		
		
		return Math.acos(ratio);
	}
	
	
	
	private static ComplexMatrix ctMatrix(ComplexNumber x, ComplexNumber y) {
		ComplexNumber mag = ComplexArithmetic.complexHypotenuse(x, y);
		ComplexNumber Xx = ComplexArithmetic.quotientComplexes(x, mag);
		ComplexNumber Xy = ComplexArithmetic.scaleComplex(ComplexArithmetic.quotientComplexes(y, mag), -1.0);
		ComplexNumber Yx = ComplexArithmetic.quotientComplexes(y, mag);
		ComplexNumber Yy = ComplexArithmetic.quotientComplexes(x, mag);
		
		ComplexMatrix m = new ComplexMatrix(2);
		m.setValue(0, 0, Xx);
		m.setValue(0, 1, Xy);
		m.setValue(1, 0, Yx);
		m.setValue(1, 1, Yy);
		return m;
		
	}
	
	
	private static RealMatrix tMatrix(double x, double y) {
		double mag = Math.hypot(x, y);
		
		double Xx = (x/mag);
		double Xy = -(y/mag);
		double Yx = (y/mag);
		double Yy = (x/mag);
		
		RealMatrix m = new RealMatrix(2);
		m.setValue(0, 0, Xx);
		m.setValue(0, 1, Xy);
		m.setValue(1, 0, Yx);
		m.setValue(1, 1, Yy);
		return m;
	}
	
	
	
	
	
	
	
	
	
	
	
}
