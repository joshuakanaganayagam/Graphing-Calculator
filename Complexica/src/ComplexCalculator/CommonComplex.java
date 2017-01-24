package ComplexCalculator;

public class CommonComplex {
	public static ComplexNumber one() {
		return new ComplexNumber(1,0);
		
	}
	public static ComplexNumber	minusOne() {
		return new ComplexNumber(-1,0);
		
	}
	public static ComplexNumber i() {
		return new ComplexNumber(0,1);
		
	}
	public static ComplexNumber minusI() {
		return new ComplexNumber(0,-1);
		
	}
	
	public static ComplexNumber piOver(int n) {
		return new ComplexNumber(Math.PI/n,0);
	}
	
	public static ComplexNumber complexReal(double real) {
		return new ComplexNumber(real,0.0);
	}
	public static ComplexNumber complexImag(double imag) {
		return new ComplexNumber(0.0, imag);
	}
	
	
}
