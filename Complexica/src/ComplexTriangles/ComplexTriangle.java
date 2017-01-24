package ComplexTriangles;

import ComplexCalculator.CommonComplex;
import ComplexCalculator.ComplexNumber;
import LinearAlgebra.ComplexVector;

public class ComplexTriangle {
	
	ComplexVector A;
	ComplexVector B;
	ComplexVector C;
	
	public ComplexTriangle(ComplexNumber Ax, ComplexNumber Ay, ComplexNumber Bx, ComplexNumber By, ComplexNumber Cx, ComplexNumber Cy) {
		A = new ComplexVector(Ax, Ay);
		B = new ComplexVector(Bx, By);
		C = new ComplexVector(Cx, Cy);
	}
	
	public ComplexTriangle() {
		A = new ComplexVector(2);
		B = new ComplexVector(2);
		C = new ComplexVector(2);
	}
	
	public void setPoint(int Point, ComplexNumber x, ComplexNumber y) {
		ComplexVector d = new ComplexVector(x,y);
		if (Point==1) {
			A.setVector(d);;
		} else if (Point==2) {
			B.setVector(d);
		} else if (Point==3) {
			C.setVector(d);
		}
	}
	
	public void setRealPoint(int Point, double x, double y) {
		setPoint(Point, CommonComplex.complexReal(x), CommonComplex.complexReal(y));
	}
	
	
	public ComplexVector getA() {
		return A;
	}

	public ComplexVector getB() {
		return B;
	}

	public ComplexVector getC() {
		return C;
	}

	
	public String toString() {
		String tot = "A = " + A + "\nB = " + B + "\nC = " + C;
		return tot+"\n";
	}
	
	
	
}
