package LinearAlgebra;

import ComplexCalculator.CommonComplex;

public class RealVector {
	private int size;
	private double[] values;
	
	
	//Standard vector setup
	public RealVector(int size) {
		this.size = size;
		values = new double[size];
	}
	
	//Overloaded 2D vector setup
	public RealVector(double x, double y) {
		size = 2;
		values = new double[2];
		setValue(0,x);
		setValue(1,y);
	}
	
	
	//Overloaded 3D vector setup
		public RealVector(double x, double y, double z) {
			size = 3;
			values = new double[3];
			setValue(0,x);
			setValue(1,y);
			setValue(2,z);
		}
	
	
	
	public void setValue(int index, double value) {
		if (index>-1&&index<size) {
			values[index] = value;
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public double getValue(int index) {
		if (index>-1&&index<size) {
			return values[index];
		} else {
			return 0.0;
		}
	}
	
	
	public ComplexVector toComplex() {
		ComplexVector cVector = new ComplexVector(size);
		for (int i=0; i<size; i++) {
			cVector.setValue(i, CommonComplex.complexReal(values[i]));
		}
		return cVector;
	}
	
	
public void setVector(RealVector vector) {
		if (size==vector.getSize()) {
			for (int i=0; i<size; i++) {
				setValue(i,vector.getValue(i));
			}
		}
		
	}
	
public String toString() {
	String tot = "["+values[0];
	
	for (int i=1;i<size;i++) {
		tot = tot+", "+values[i];
	}
	tot = tot+"]";
	return tot;
			
}

}
