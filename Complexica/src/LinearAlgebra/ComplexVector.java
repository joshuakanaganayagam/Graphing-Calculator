package LinearAlgebra;

import ComplexCalculator.ComplexArithmetic;
import ComplexCalculator.ComplexNumber;

public class ComplexVector {

	private int size;
	private ComplexNumber[] values;
	
	
	//Standard vector setup
	public ComplexVector(int size) {
		this.size = size;
		values = new ComplexNumber[size];
		for (int i=0; i<size; i++) {
			values[i] = new ComplexNumber();
		}
	}
	
	//Overloaded 2D vector setup
	public ComplexVector(ComplexNumber x, ComplexNumber y) {
		size = 2;
		values = new ComplexNumber[2];
		values[0] = new ComplexNumber();
		values[1] = new ComplexNumber();
		setValue(0,x);
		setValue(1,y);
	}
	
	
	
	public void setValue(int index, ComplexNumber value) {
		if (index>-1&&index<size) {
			values[index].set(value);
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public ComplexNumber getValue(int index) {
		if (index>-1&&index<size) {
			return values[index];
		} else {
			return null;
		}
	}
	
	public void setVector(ComplexVector vector) {
		
		setValue(0,vector.getValue(0));
		setValue(1,vector.getValue(1));
	}
	
	public ComplexNumber getHypotenuse() {
		if (size==2) {
			return ComplexArithmetic.complexHypotenuse(getValue(0), getValue(1));
		} else {
			return null;
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
