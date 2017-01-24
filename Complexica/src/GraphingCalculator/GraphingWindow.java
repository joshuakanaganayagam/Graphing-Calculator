package GraphingCalculator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import ComplexCalculator.ComplexNumber;

public class GraphingWindow {
	/**These complex numbers represent values that the window will eventually graph on its screen.
	 * For now only three complex numbers will be graphed but this can be changed later.
	 * 
	 */
	
	//Checks if the window is visible.
	boolean visible;
	
	
	ComplexNumber Center;
	
	
	//Represents the on-screen coordinates of the graphing window.
	int CenterX;
	int CenterY;
	
	//A preinstantiated number used for drawing gridlines.
	ComplexNumber temp;
	
	//scroll rate constant.
	private static double scrollRate = 30.0;
	
	//This is the initial value for the windowDiameter.
	private static double defaultDiameter = 10.0;
	
	//The windowDiameter represents the real and imaginary components of the Complex rectangular range that is revealed.
	double windowDiameter;
	//These are the real and imaginary components of the graphing window's extreme values.
	double windowLeft, windowRight, windowUp, windowDown;
	
	//A variable for scale.
	double unit;
	
	//A special colour for non special gridlines
	Color lightlightGray;
	
	
	public GraphingWindow(ComplexNumber c, int x, int y) {
		Center = new ComplexNumber();
		temp = new ComplexNumber();
		lightlightGray = new Color(240,240,240);
		
		Center.set(c);
		windowDiameter = defaultDiameter;
		CenterX = x;
		CenterY = y;
		setWindowConstraints();
		
		
	}
	
	public void setWindowConstraints() {
		
		windowLeft = Center.getReal()-(windowDiameter/2);
		windowRight = windowLeft + windowDiameter;
		windowUp = Center.getImaginary()-(windowDiameter/2);
		windowDown = windowUp + windowDiameter;
		
		
		
		
	}
	
	
	
	
	
	public void scroll(int notches) {
		double scale = Math.exp((double)notches/scrollRate);
		if (windowDiameter*scale>1.0E-13&&windowDiameter*scale<1.0E250) {
			windowDiameter*=scale;
		}
		
		
		
		setWindowConstraints();
	}
	
	
	
	public static void drawCursor(Graphics g, int x, int y, int type) {
		if (type==0) {
			g.setColor(Color.BLACK);
			g.drawRect(x-2, y-2, 4, 4);;
			
		}
		
		
		if (type==1) {
			g.setColor(Color.RED);
			g.drawLine(x-10, y-10, x+10, y+10);
			g.drawLine(x+10, y-10, x-10, y+10);
			
		}
		
		if (type==2) {
		g.setColor(Color.BLUE);
		} if (type==3) {
			g.setColor(new Color(0,170,10));
			
		} if (type==4) {
			g.setColor(Color.ORANGE);
		} if (type==5) {
			g.setColor(Color.CYAN);
		} if (type==6) {
			g.setColor(Color.MAGENTA);
		} if (type==7) {
			g.setColor(new Color(200,240,0));
		} if (type==8) {
			g.setColor(new Color(170,0,210));
		} if (type==9) {
			g.setColor(new Color(200,120,20));
		} if (type==102) {
			g.setColor(new Color(20,0,200));
		}  if (type==103) {
			g.setColor(new Color(0,200,20));
		}
		
		
		if (type>1&&type<100) {
			
			g.drawLine(x, y-20, x, y+20);
			g.drawLine(x+20, y, x-20, y);
			g.drawOval(x-10, y-10, 20, 20);
			
		}
		if (type>100) {
			g.drawLine(x, y-20, x, y+20);
			g.drawLine(x+17, y+10, x-17, y-10);
			g.drawLine(x-17, y+10, x+17, y-10);
			g.drawOval(x-10, y-10, 20, 20);
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	public void drawComplexNumber(Graphics g, ComplexNumber m, int type) {
		if (visible) {
			int x = complexNumberToCoordinate(m,1);
			int y = complexNumberToCoordinate(m,2);
			if (x!=-1&&y!=-1) {
				
				
				drawCursor(g, x, y, type);
				g.setColor(Color.BLACK);
			}
		
		}
	
		
	}
	
	public boolean containsPoint(int x, int y) {
			int relX = x-CenterX;
			int relY = y-CenterY;
			if (relX>-250&&relX<250&&relY>-250&&relY<250) {
				if (!visible) {
					return false;
				}
				return true;
			} else {
				return false;
			}
			
			
		}
	
	
	
	
	public ComplexNumber coordinatesToComplexNumber(int x, int y) {
		int relX = x-CenterX;
		int relY = y-CenterY;
		if (containsPoint(x,y)) {
			double real = (double)(relX)*(windowDiameter/500.0)+Center.getReal();
			double imag = -(double)(relY)*(windowDiameter/500.0)+Center.getImaginary();
			return new ComplexNumber(real, imag);
			
		} else {
			return null;
		}
		
	}
	
	
	//1 is for reals, 2 is for imaginaries.
	private int complexNumberToCoordinate(ComplexNumber value, int type) {
		if (type==1) {
			double realValue = value.getReal();
			if (realValue>windowLeft&&realValue<windowRight) {
				return ((int)((realValue - Center.getReal())*(500/windowDiameter))+CenterX);
				
			}  else {
				return -1;
			}
						
		} else if (type==2) {
			double imagValue = value.getImaginary();
			if (imagValue>windowUp&&imagValue<windowDown) {
				return (-(int)((imagValue - Center.getImaginary())*(500/windowDiameter))+CenterY);
				
			}  else {
				return -1;
			}
		} else {
			return -1;
		}
		
	}
	
	
	public void setComplexCenter(ComplexNumber c) {
		Center.set(c);
	}
	
	
	
	
	
	
	
	
	
	
	
	//Code for drawing axis.
	public void drawGridLines(Graphics g) {
		if (visible) {
			g.setColor(new Color(245,245,245));
			g.fillRect(CenterX-265, CenterY-315, 530, 580);
			
			g.setColor(Color.WHITE);
			g.fillRect(CenterX-250, CenterY-250, 500, 500);
			
			setWindowConstraints();
			g.setColor(lightlightGray);
			double magnitude = Math.floor(Math.log10(windowDiameter/2.0));
			unit = Math.pow(10.0, magnitude);
			double horizontalGLine = unit*Math.ceil(windowUp/unit);
			while (horizontalGLine<windowDown) {
				drawHorizontalGridLine(g, horizontalGLine, false);
				horizontalGLine += unit;
			}
		
			double verticalGLine = unit*Math.ceil(windowLeft/unit);
			while (verticalGLine<windowRight) {
				drawVerticalGridLine(g, verticalGLine, false);
				verticalGLine += unit;
			}
			g.setColor(Color.BLACK);
			drawVerticalGridLine(g, 0.0, true);
			drawHorizontalGridLine(g, 0.0, true);
			
			
			
			g.setColor(Color.BLACK);
			g.drawRect(CenterX-250, CenterY-250, 500, 500);
			g.drawRect(CenterX-265, CenterY-315, 530, 580);
		
		}
	}
	
	public void drawGUI(Graphics g, int select) {
		if (visible) {
			g.setColor(Color.WHITE);
			g.fillOval(CenterX-247, CenterY-300, 40, 40);
			drawCursor(g, CenterX-227, CenterY-280, select+2);
			g.fillRect(CenterX+150, CenterY-305, 100, 30);
			g.setColor(Color.BLACK);
			g.drawOval(CenterX-247, CenterY-300, 40, 40);
			g.drawRect(CenterX+150, CenterY-305, 100, 30);
		}
	}
	
	
	
	public void drawHorizontalGridLine(Graphics g, double imag, boolean axis) {
		temp.setComplexNumber(0.0, imag);
		int y = complexNumberToCoordinate(temp, 2);
		if (roundedDigit(temp.getImaginary())%5==0&&!axis) {
	    	g.setColor(Color.LIGHT_GRAY);
	    }
		g.drawLine(CenterX-250, y, CenterX+250, y);
		if (roundedDigit(temp.getImaginary())%5==0&&!axis) {
	    	g.setColor(lightlightGray);
	    }
		if (!axis) {
			g.setColor(Color.LIGHT_GRAY);
		}
		
		g.drawString(roundedDouble(temp.getImaginary(), false), CenterX-240, y);
		if (!axis) {
			g.setColor(lightlightGray);
		}
	}
	public void drawVerticalGridLine(Graphics g, double real, boolean axis) {	
		temp.setComplexNumber(real, 0.0);
		int x = complexNumberToCoordinate(temp, 1);
		if (roundedDigit(temp.getReal())%5==0&&!axis) {
	    	g.setColor(Color.LIGHT_GRAY);
	    }
		
		g.drawLine(x, CenterY-250, x, CenterY+250);
		
		if (roundedDigit(temp.getReal())%5==0&&!axis) {
	    	g.setColor(lightlightGray);
	    }
		
		if (!axis) {
			g.setColor(Color.LIGHT_GRAY);
		}
		Graphics2D g2d = (Graphics2D)g;
	    
	    // clockwise 90 degrees
	    AffineTransform at = new AffineTransform();
	    // thanks to M.C. Henle for the bug fix!
	    at.setToRotation(-Math.PI/2.0, x, CenterY+250);
	    g2d.setTransform(at);
	    
	    g2d.drawString(roundedDouble(temp.getReal(), true), x, CenterY+250);
	    at.setToRotation(0.0, windowDiameter/2.0, windowDiameter/2.0);
	    g2d.setTransform(at);
	    if (!axis) {
			g.setColor(lightlightGray);
		}
		//g.drawString(roundedDouble(temp.getReal(), true), x, CenterY+250);
		
	}
	
	
	private String roundedDouble(double a, boolean real) {
		long sf = roundedDigit(a);
		long m = (long)Math.round(Math.log10(unit));
		if (sf%2!=0) {
			return "";
		}
		if (real) {
		return + sf + ".0" +"E" + m ;
		} else {
			return "("+ sf + ".0" +"E" + m +")i";
		}
		
	}
	
	private long roundedDigit(double a) {
		return (long)Math.round(a/unit);
		
		
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setCenterX(int x) {
		CenterX = x;
	}
	
	
	
	
	
	
	
	
	
}
