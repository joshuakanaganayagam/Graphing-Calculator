package ComplexCalculator;


public class ComplexNumber {
	private double realPart;
	private double imaginaryPart;

	
	public ComplexNumber() {
		realPart = 0;
		imaginaryPart = 0;
		//creates new instance.
	}
	
	public void set(ComplexNumber c) {
		realPart = c.realPart;
		imaginaryPart = c.imaginaryPart;
	}
	
	public ComplexNumber(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
		//creates new instance.
	}
	
	public void setComplexNumber(double realPart, double imaginaryPart) {
		this.realPart = realPart;
		this.imaginaryPart = imaginaryPart;
		//resets existing instance.
	}
	
	public ComplexNumber complexConjugate() {
		return new ComplexNumber(realPart, -imaginaryPart);
	}
	
	public double magnitude() {
		double a =Math.hypot(realPart, imaginaryPart);
		return a;
		
	}
	
	
	
	
	
	
	public double getReal() {
		return realPart;
	}
	
	
	public double getImaginary() {
		return imaginaryPart;	
	}
	
	public String toString() {
	
		return getReal() + " + i" + getImaginary();
		
		
	}
	

}
