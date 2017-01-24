package LinearAlgebra;

public class RealTransformations {
	
	public static RealVector sum(RealVector a, RealVector b) {
		if (a.getSize()==b.getSize()) {
			RealVector c = new RealVector(a.getSize());
			for (int i=0; i<a.getSize(); i++) {
				c.setValue(i, a.getValue(i)+b.getValue(i));
				
			}
			return c;
		} else {
			return null;
		}
	}
	
	public static RealVector dif(RealVector a, RealVector b) {
		if (a.getSize()==b.getSize()) {
			RealVector c = new RealVector(a.getSize());
			for (int i=0; i<a.getSize(); i++) {
				c.setValue(i, a.getValue(i)-b.getValue(i));
				
			}
			return c;
		} else {
			return null;
		}
	}
	
	public static RealVector scale(RealVector a, double s) {
		RealVector b = new RealVector(a.getSize());
		for (int i = 0; i<a.getSize(); i++) {
			b.setValue(i, a.getValue(i)*s);
		}
		return b;
	}
	
	
	
	public static RealVector product(RealMatrix a, RealVector b) {
		if (a.getSize()==b.getSize()) {
			RealVector d = new RealVector(a.getSize());
			for (int r=0; r<a.getSize(); r++) {
				double tot = 0;
				for (int c=0; c<a.getSize(); c++) {
					tot+=(a.getValue(c, r)*b.getValue(c));
				}
				d.setValue(r, tot);
			
			}
			return d;
			
		} else {
			return null;
		}
	}
	
	public static RealVector vectorParts(RealVector source, RealVector destination, int count, int parts) {
		if (source.getSize()!=destination.getSize()) {
			return null;
		} else {
			double fraction = ((double)count)/((double)parts);
			RealVector fullDelta = dif(destination, source);
			RealVector partialDelta = scale(fullDelta, fraction);
			return sum(partialDelta, source);
		}
	}
	
	
	
	
	
	
	public static RealMatrix inverseMatrix3(RealMatrix abc) {
		if (abc.getSize()==3) {
			RealMatrix xyz = new RealMatrix(3);
			
			RealVector x = invSupport(abc);
	
			RealMatrix yInput = new RealMatrix(3);
			
			yInput.setMatrix(abc);
			yInput.setValue(0, 0, abc.getValue(1, 0)); 
			yInput.setValue(0, 1, abc.getValue(1, 1)); 
			yInput.setValue(0, 2, abc.getValue(1, 2));
			yInput.setValue(1, 0, abc.getValue(0, 0)); 
			yInput.setValue(1, 1, abc.getValue(0, 1)); 
			yInput.setValue(1, 2, abc.getValue(0, 2));
			RealVector y = invSupport(yInput);
			
			RealMatrix zInput = new RealMatrix(3);
			zInput.setMatrix(abc);
			zInput.setValue(0, 0, abc.getValue(2, 0)); 
			zInput.setValue(0, 1, abc.getValue(2, 1)); 
			zInput.setValue(0, 2, abc.getValue(2, 2));
			zInput.setValue(2, 0, abc.getValue(0, 0)); 
			zInput.setValue(2, 1, abc.getValue(0, 1)); 
			zInput.setValue(2, 2, abc.getValue(0, 2));
			RealVector z = invSupport(zInput);
			
			xyz.setValue(0, 0, x.getValue(0));
			xyz.setValue(1, 0, x.getValue(1));
			xyz.setValue(2, 0, x.getValue(2));
			
			xyz.setValue(0, 1, y.getValue(0));
			xyz.setValue(1, 1, y.getValue(1));
			xyz.setValue(2, 1, y.getValue(2));
			
			xyz.setValue(0, 2, z.getValue(0));
			xyz.setValue(1, 2, z.getValue(1));
			xyz.setValue(2, 2, z.getValue(2));
			
			return xyz;
			
		} else {
			return null;
		}
	}
	private static RealVector invSupport(RealMatrix input) {
		/**Here is a simple expanation of how the equations shall work
		 * input has the following nature:
		 * C1V1+C2V3+C3V3=1
		 * C4V1+C5V3+C6V3=0
		 * C7V1+C8V3+C9V3=0
		 */
		RealVector V123 = new RealVector(3);
		double Aa = input.getValue(0, 0);
		double Ab = input.getValue(0, 1);
		double Ac = input.getValue(0, 2);
		
		double Ba = input.getValue(1, 0);
		double Bb = input.getValue(1, 1);
		double Bc = input.getValue(1, 2);
		
		double Ca = input.getValue(2, 0);
		double Cb = input.getValue(2, 1);
		double Cc = input.getValue(2, 2);
		
		double denominator = (Ab*Bc*Ca)-(Ab*Ba*Cc)+(Aa*Bb*Cc)-(Aa*Bc*Cb)+(Ac*Cb*Ba)-(Ac*Ca*Bb);
		V123.setValue(0, (((Bb*Cc)-(Bc*Cb))/denominator));
		V123.setValue(1, (((Bc*Ca)-(Ba*Cc))/denominator));
		V123.setValue(2, (((Cb*Ba)-(Ca*Bb))/denominator));
		return V123;
		
		
		
		
	}
	
	
	
	
	
}
