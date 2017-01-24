package ComplexTriangles;



import LinearAlgebra.RealVector;

public class RealTriangle {
	RealVector A;
	RealVector B;
	RealVector C;
	
	public RealTriangle(double Ax, double Ay, double Bx, double By, double Cx, double Cy) {
		A = new RealVector(Ax, Ay);
		B = new RealVector(Bx, By);
		C = new RealVector(Cx, Cy);
	}
	
	public RealTriangle() {
		A = new RealVector(2);
		B = new RealVector(2);
		C = new RealVector(2);
	}
	
	public void setPoint(int Point, double x, double y) {
		RealVector d = new RealVector(x,y);
		if (Point==1) {
			A.setVector(d);;
		} else if (Point==2) {
			B.setVector(d);
		} else if (Point==3) {
			C.setVector(d);
		}
	}
	
	
	
	
	
	
	public RealVector getA() {
		return A;
	}

	public RealVector getB() {
		return B;
	}

	public RealVector getC() {
		return C;
	}

	public String toString() {
		String tot = "A = " + A + "\nB = " + B + "\nC = " + C;
		return tot+"\n";
	}
}
