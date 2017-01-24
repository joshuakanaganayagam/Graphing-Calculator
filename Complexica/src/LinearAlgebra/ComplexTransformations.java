package LinearAlgebra;

import ComplexCalculator.ComplexArithmetic;
import ComplexCalculator.ComplexNumber;

public class ComplexTransformations {

	public static ComplexVector sum(ComplexVector a, ComplexVector b) {
		if (a.getSize()==b.getSize()) {
			ComplexVector c = new ComplexVector(a.getSize());
			for (int i=0; i<a.getSize(); i++) {
				c.setValue(i, ComplexArithmetic.addComplexes(a.getValue(i), b.getValue(i)));
				
			}
			return c;
		} else {
			return null;
		}
	}
	

	public static ComplexVector dif(ComplexVector a, ComplexVector b) {
		if (a.getSize()==b.getSize()) {
			ComplexVector c = new ComplexVector(a.getSize());
			for (int i=0; i<a.getSize(); i++) {
				c.setValue(i, ComplexArithmetic.subtractComplexes(a.getValue(i), b.getValue(i)));
				
			}
			return c;
		} else {
			return null;
		}
	}
	
	public static ComplexVector product(ComplexMatrix a, ComplexVector b) {
		if (a.getSize()==b.getSize()) {
			ComplexVector d = new ComplexVector(a.getSize());
			for (int r=0; r<a.getSize(); r++) {
				ComplexNumber tot = new ComplexNumber();
				for (int c=0; c<a.getSize(); c++) {
					tot.set(ComplexArithmetic.addComplexes(tot, ComplexArithmetic.productComplexes(a.getValue(c, r), b.getValue(c))));
					
				}
				d.setValue(r, tot);
			
			}
			return d;
			
		} else {
			return null;
		}
	}
	
	public static ComplexVector scale(ComplexVector a, ComplexNumber b) {
		ComplexVector c = new ComplexVector(a.getSize());
		for (int r=0; r<a.getSize(); r++) {
			c.setValue(r, ComplexArithmetic.productComplexes(a.getValue(r), b));
		}
		return c;
	}
	
	public static ComplexVector antiScale(ComplexVector a, ComplexNumber b) {
		ComplexVector c = new ComplexVector(a.getSize());
		for (int r=0; r<a.getSize(); r++) {
			c.setValue(r, ComplexArithmetic.quotientComplexes(a.getValue(r), b));
		}
		return c;
	}
	
	
	
	
	
	
}
