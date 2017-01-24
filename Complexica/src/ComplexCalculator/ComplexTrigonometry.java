package ComplexCalculator;

import LinearAlgebra.ComplexTransformations;
import LinearAlgebra.ComplexVector;

public class ComplexTrigonometry {

	public static ComplexNumber ComplexSine(ComplexNumber angle) {
		double real = angle.getReal();
		double imag = angle.getImaginary();
		ComplexNumber sineOfAngle = new ComplexNumber();
		double exp = Math.exp(imag);
		double expInv = 1/exp;
		double expSum = exp+expInv;
		double expDif = exp-expInv;
		double sinReal = Math.sin(real);
		double cosReal = Math.cos(real);
		double realOpposite = (sinReal*expSum/2);
		double imagOpposite = (cosReal*expDif/2);
		sineOfAngle.setComplexNumber(realOpposite, imagOpposite);
		
		
		return sineOfAngle;
	}
	
	
	public static ComplexNumber ComplexCosine(ComplexNumber angle) {
		double real = angle.getReal();
		double imag = angle.getImaginary();
		ComplexNumber cosineOfAngle = new ComplexNumber();
		double exp = Math.exp(imag);
		double expInv = 1/exp;
		double expSum = exp+expInv;
		double expDif = expInv-exp;
		double sinReal = Math.sin(real);
		double cosReal = Math.cos(real);
		double realAdjacent = (cosReal*expSum/2);
		double imagAdjacent = (sinReal*expDif/2);
		cosineOfAngle.setComplexNumber(realAdjacent, imagAdjacent);
		
		
		return cosineOfAngle;
	}
	
	public static ComplexNumber ComplexTangent(ComplexNumber angle) {
		return ComplexArithmetic.quotientComplexes(ComplexSine(angle), ComplexCosine(angle));
	}
	
	
	
	
	
	
	public static ComplexNumber arcSine(ComplexNumber sine) {
		ComplexNumber radical = ComplexArithmetic.subtractComplexes(CommonComplex.one(), ComplexArithmetic.squareComplex(sine));
		ComplexNumber discriminant = ComplexArithmetic.sqrtComplex(radical);
		ComplexNumber antibase = ComplexArithmetic.subtractComplexes(ComplexArithmetic.productComplexes(CommonComplex.i(),sine), discriminant);
		ComplexNumber complexExponent = ComplexExponential.complexLn(antibase);
		ComplexNumber qTwoThree = ComplexArithmetic.productComplexes(complexExponent, CommonComplex.minusI());
		
		return ComplexArithmetic.subtractComplexes(CommonComplex.piOver(1), qTwoThree);
	}
	
	public static ComplexNumber arcCos(ComplexNumber cosine) {
		
		return ComplexArithmetic.subtractComplexes(CommonComplex.piOver(2), arcSine(cosine));	
	}
	
	
	public static ComplexNumber arcTan(ComplexNumber tangent) {
		ComplexNumber numerator = ComplexArithmetic.subtractComplexes(CommonComplex.i(), tangent);
		ComplexNumber denominator = ComplexArithmetic.addComplexes(CommonComplex.i(), tangent);
		ComplexNumber quotient = ComplexArithmetic.quotientComplexes(numerator, denominator);
		ComplexNumber discriminant = ComplexArithmetic.sqrtComplex(quotient);
		ComplexNumber exponent = ComplexExponential.complexLn(discriminant);
		ComplexNumber arcTangent = ComplexArithmetic.productComplexes(CommonComplex.minusI(), exponent);
		return arcTangent;
		
	}
	
	public static ComplexNumber theta(ComplexVector unNormalised) {
		ComplexNumber mag = unNormalised.getHypotenuse();
		ComplexVector unitVector = ComplexTransformations.antiScale(unNormalised, mag);
		ComplexNumber ThetaOne = ComplexTrigonometry.arcCos(unitVector.getValue(0));
		ComplexNumber ThetaTwo = ComplexArithmetic.neg(ThetaOne);
		ComplexNumber y1 = ComplexTrigonometry.ComplexSine(ThetaOne);
		ComplexNumber y2 = ComplexTrigonometry.ComplexSine(ThetaTwo);
		ComplexNumber Diff1 = ComplexArithmetic.subtractComplexes(unitVector.getValue(1), y1);
		ComplexNumber Diff2 = ComplexArithmetic.subtractComplexes(unitVector.getValue(1), y2);
		double dist1 = Diff1.magnitude();
		double dist2 = Diff2.magnitude();
		if (dist1<dist2) {
			return ThetaOne;
		} else {
			return ThetaTwo;
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
