package ComplexCalculator;

import RealCalculator.RealArithmetic;

public class ComplexArithmetic {
	
	

	
	
	public static ComplexNumber addComplexes(ComplexNumber a, ComplexNumber b) {
		return new ComplexNumber(a.getReal()+b.getReal(), a.getImaginary()+b.getImaginary());
		
	}
	
	public static ComplexNumber subtractComplexes(ComplexNumber a, ComplexNumber b) {
		return new ComplexNumber(a.getReal()-b.getReal(), a.getImaginary()-b.getImaginary());
	}
	

	
	
	
	public static ComplexNumber productComplexes(ComplexNumber a, ComplexNumber b) {
		ComplexNumber c = new ComplexNumber();
		c.setComplexNumber((a.getReal()*b.getReal()-a.getImaginary()*b.getImaginary()),
				(a.getReal()*b.getImaginary()+a.getImaginary()*b.getReal()));
		return c;
		
	}
	
	public static ComplexNumber quotientComplexes(ComplexNumber a, ComplexNumber b) {
		ComplexNumber c = new ComplexNumber();
		double denominator = RealArithmetic.square(b.getReal()) + RealArithmetic.square(b.getImaginary());
		c.setComplexNumber(((a.getReal()*b.getReal()+a.getImaginary()*b.getImaginary())/(denominator)),
				((a.getImaginary()*b.getReal()-a.getReal()*b.getImaginary())/(denominator)));
		
		return c;
		
	}
	
	
	
	public static ComplexNumber squareComplex(ComplexNumber a) {
		return productComplexes(a,a);
		
	}
	
	

	
	public static ComplexNumber sqrtComplex(ComplexNumber a) {
		ComplexNumber b = new ComplexNumber();
		double radical = (a.getReal()+a.magnitude());
		double real = Math.sqrt((radical/2))*RealArithmetic.signProductor(a.getImaginary());
		double imaginary = (Math.sqrt(RealArithmetic.square(a.getImaginary()))/Math.sqrt((radical*2)));
		if (radical==0) {
			imaginary = Math.sqrt(a.magnitude());
		}
		b.setComplexNumber(real, imaginary);
		return b;
	}
	
	
	public static ComplexNumber complexHypotenuse(ComplexNumber a, ComplexNumber b) {
		return sqrtComplex(addComplexes(squareComplex(a),squareComplex(b)));
	}
	
	
	
	
	
	
	
	
	public static ComplexNumber scaleComplex(ComplexNumber a, double b) {
		ComplexNumber c = new ComplexNumber();
		c.setComplexNumber(a.getReal()*b, a.getImaginary()*b);
		return c;
	}
	
	public static ComplexNumber divScaleComplex(ComplexNumber a, double b) {
		ComplexNumber c = new ComplexNumber();
		c.setComplexNumber(a.getReal()/b, a.getImaginary()/b);
		return c;
	}
	
	public static void selfScaleComplex(ComplexNumber a, double b) {
		double real = a.getReal()*b;
		double imag = a.getImaginary()*b;
		a.setComplexNumber(real, imag);
	}
	
	public static void selfDivScaleComplex(ComplexNumber a, double b) {
		double real = a.getReal()/b;
		double imag = a.getImaginary()/b;
		a.setComplexNumber(real, imag);
	}
	
	public static ComplexNumber neg(ComplexNumber a) {
		return scaleComplex(a, -1.0);
	}
	
	
	
	
	
	
	
	
	
}
