package RealCalculator;

public class RealArithmetic {
	public static double square(double root) {
		return Math.pow(root, 2);
		
	}
	
	public static boolean sign(double a) {
		
		if (a<0) {
			return false;
		}
		return true;
		
	}
	
	public static double signProductor(double a) {
		if (sign(a)) {
			return 1.0;
		} else {
			return -1.0;
		}
		
		
	}
	
	
	
}
