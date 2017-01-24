package ComplexCalculator;

public class ComplexExponential {
	
	
	//Euler's Formula:
	public static ComplexNumber realCis(double argument) {
		ComplexNumber c = new ComplexNumber();
		c.setComplexNumber(Math.cos(argument), Math.sin(argument));
		return c;
	}
	
	
	public static ComplexNumber exponentialFunction(ComplexNumber base) {
		ComplexNumber c = new ComplexNumber();
		c.setComplexNumber(Math.cos(base.getImaginary()), Math.sin(base.getImaginary()));
		ComplexArithmetic.selfScaleComplex(c, Math.exp(base.getReal()));
		return c;
		
	}
	
	public static ComplexNumber complexCis(ComplexNumber argument) {
		ComplexNumber c = new ComplexNumber();
		c = ComplexArithmetic.productComplexes(argument, new ComplexNumber(0,1));
		return exponentialFunction(c);	
	}
	
	public static double argument(ComplexNumber value) {
		if (value.getImaginary()!=0) {
			double arctan = ((value.magnitude()+value.getReal())/value.getImaginary());
			double immediateAngle = Math.atan(arctan)*2;
			return (Math.PI-immediateAngle);
		} else {
			if (value.getReal()>0) {
				return 0.0;
			} else {
				return Math.PI;
			}
		}
	}
	
	
	
	public static ComplexNumber complexLn(ComplexNumber power) {
		double real = Math.log(power.magnitude());
		double imaginary = argument(power);
		ComplexNumber exponent = new ComplexNumber(real, imaginary);
		return (exponent);
	}
	
	
	
	
	
	
}
