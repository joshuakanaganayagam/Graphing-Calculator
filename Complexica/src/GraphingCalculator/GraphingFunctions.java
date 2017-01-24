package GraphingCalculator;

import java.util.ArrayList;

import ComplexCalculator.*;
import LinearAlgebra.ComplexVector;






public class GraphingFunctions {
	
	public static final int totalDomainSize = 3;
	public static final int totalRangeSize = 5;
	public static final String domainNames[] = {"A, y-coord", "A, x-coord", "Angle Theta"};
	public static final String rangeNames[] = {"A norm, x-coord", "A norm, y-coord", 
			"Theta Vector, x-coord", "Theta Vector, y-coord", "A angle"};
	
	public static final int totalGameDomainSize = 1;
	public static final int totalGameRangeSize = 2;
	
	public static final String gameDomainNames[] = {"A"};
	public static final String gameRangeNames[] = {"A norm", "Theta Vector"};
	
	public static final int totalCurveSize = 4;
	
	
	
	public static ComplexNumber graph(ArrayList<ComplexNumber> input, int outIndex) {
		ComplexNumber output = new ComplexNumber();
		ComplexVector Apoint = new ComplexVector(input.get(1),input.get(0));
		ComplexNumber Amag = Apoint.getHypotenuse();
		
		
		
		if (outIndex==0) {
			//output.set(TriangleAngles.calculateComplexAngle(triangle, true, 1));
			output.set(ComplexArithmetic.quotientComplexes(input.get(0), Amag));
		}
		if (outIndex==1) {
			//output.set(TriangleAngles.calculateComplexAngle(triangle, true, 2));
			output.set(ComplexArithmetic.quotientComplexes(input.get(1), Amag));
		}
		if (outIndex==2) {
			//output.set(TriangleAngles.calculateComplexAngle(triangle, true, 3));
			output.set(ComplexTrigonometry.ComplexCosine(input.get(2)));
			}
		if (outIndex==3) {
			output.set(ComplexTrigonometry.ComplexSine(input.get(2)));
			}
		if (outIndex==4) {
			
			output.set(ComplexTrigonometry.theta(Apoint));
		}
		return output;
		
		
	}
	
	public static ComplexNumber curve(ArrayList<ComplexNumber> input, ComplexNumber x, int outIndex) {
		ComplexNumber output = new ComplexNumber();
		
		if (outIndex==0) {
			//output.set(ComplexArithmetic.addComplexes(ComplexArithmetic.productComplexes(input.get(0), x), input.get(1)));
			output.set(ComplexArithmetic.sqrtComplex(ComplexArithmetic.subtractComplexes(CommonComplex.one(),
					ComplexArithmetic.squareComplex(x))));
		}
		if (outIndex==1) {
			//output.set(ComplexArithmetic.addComplexes(ComplexArithmetic.productComplexes(input.get(0), x), input.get(1)));
			output.set(ComplexArithmetic.neg(ComplexArithmetic.sqrtComplex(ComplexArithmetic.subtractComplexes(CommonComplex.one(),
					ComplexArithmetic.squareComplex(x)))));
		}
		
		if (outIndex==2) {
			output.set(ComplexArithmetic.productComplexes(ComplexArithmetic.quotientComplexes(input.get(0), input.get(1)), x));
		}
		if (outIndex==3) {
			output.set(ComplexArithmetic.productComplexes(ComplexArithmetic.quotientComplexes(ComplexTrigonometry.ComplexCosine(input.get(2)), ComplexTrigonometry.ComplexSine(input.get(2))), x));
		}
		
		
		
		return output;
	}
	
	
	
	
}