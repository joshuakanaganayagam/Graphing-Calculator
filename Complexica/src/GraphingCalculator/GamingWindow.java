package GraphingCalculator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import ComplexCalculator.ComplexNumber;
import LinearAlgebra.ComplexVector;
import LinearAlgebra.RealTransformations;
import LinearAlgebra.RealVector;
import LinearAlgebra.ThreeDRotations;
import RealCalculator.RealIntersections;

public class GamingWindow {
	boolean visible;
	ComplexVector center;
	int CenterX;
	int CenterY;
	
	RealVector camera;
	RealVector vectorCenter;
	
	
	double angleY;
	double angleT;
	double cameraRadius;
	
	Color bufferColor;
	ArrayList<RealVector> Buffer;
	ArrayList<RealVector> ScrollBuffer;
	RealVector circle;
	
	
	private static double scrollRate = 30.0;
	private static double defaultDiameter = 10.0;
	private static int divisions = 100;
	
	
	double windowDiameter;
	double windowLeft, windowRight, windowUp, windowDown, windowForward, windowBack, windowHigh, windowLow;
	
	public static double fov = 1/Math.sqrt(3.0);
	private static double nfov = -1/Math.sqrt(3.0);
	
	double unit;
	
	Color lightlightGray;
	
	
	
	
	
	public GamingWindow(ComplexVector c, int x, int y) {
		center = c;
		camera = new RealVector(3);
		vectorCenter = new RealVector(3);
		lightlightGray = new Color(220,220,220);
		cameraRadius=1.5;
		setCameraAngles(0,Math.PI);
		
		
		circle = new RealVector(3);
		circle.setValue(0, Math.PI);
		circle.setValue(1, Math.E);
		circle.setValue(2, Math.PI);
		
		
		windowDiameter = defaultDiameter;
		CenterX = x;
		CenterY = y;
		qm = false;
		setWindowConstraints();
		Buffer = new ArrayList<RealVector>();
		sortedBuffer = new ArrayList<RealVector>();
		ScrollBuffer = new ArrayList<RealVector>();
		bufferColor = Color.BLACK;
	}
	
	public void setWindowConstraints() {
		
		windowLeft = center.getValue(0).getReal()-(windowDiameter/2);
		windowRight = windowLeft + windowDiameter;
		
		if (getQm()) {
			windowForward = center.getValue(1).getReal()+(windowDiameter/2);
			windowBack = windowForward - windowDiameter;
			
			windowUp = center.getValue(0).getImaginary()+(windowDiameter/2);
			windowDown = windowUp - windowDiameter;
		} else {
		windowUp = center.getValue(1).getReal()+(windowDiameter/2);
		windowDown = windowUp - windowDiameter;
		
		windowForward = center.getValue(0).getImaginary()+(windowDiameter/2);
		windowBack = windowForward - windowDiameter;
		}
		windowHigh = center.getValue(1).getImaginary()+(windowDiameter/2);
		windowLow = windowHigh - windowDiameter;
		cameraRadius = 1.5;
		
		
		if (getQm()) {
			vectorCenter.setValue(0, center.getValue(0).getReal());
			vectorCenter.setValue(2, center.getValue(1).getReal());
			vectorCenter.setValue(1, center.getValue(0).getImaginary());
		} else {
			vectorCenter.setValue(0, center.getValue(0).getReal());
			vectorCenter.setValue(1, center.getValue(1).getReal());
			vectorCenter.setValue(2, center.getValue(0).getImaginary());
		}
		positionCamera();
		
	}
	
	private boolean onScrollBar(ComplexVector a) {
		double imag = a.getValue(1).getImaginary();
		return (imag>windowLow&&imag<windowHigh);
	}
	
	
	public void scroll(int notches, int x, int y) {
		if (containsImagScroll(x,y)) {
			double scale = Math.exp((double)notches/scrollRate);
			if (windowDiameter*scale>1.0E-13&&windowDiameter*scale<1.0E250) {
				windowDiameter*=scale;
			}
		} else {
			RealVector screen = new RealVector(3);
			screen.setValue(0, CenterX); screen.setValue(1, CenterY);
			screen.setValue(2, (-notches*3) + (375.0/GamingWindow.fov));
			
			RealVector temp = threeDFromScreenCoordinates(screen);
			if (temp!=null) {
				ComplexVector c = threeVecToComplexVector(temp, center.getValue(1).getImaginary());
				
				
				center.setVector(c);;
				
				
			}
		}
		
		
		
		
		setWindowConstraints();
	}
	
	public void drawCursor(Graphics g, RealVector b, int type) {
		
		RealVector leftSmudge = new RealVector(3); leftSmudge.setValue(0, 0.08*windowDiameter);
		RealVector upSmudge = new RealVector(3); upSmudge.setValue(1, 0.08*windowDiameter);
		RealVector fordSmudge = new RealVector(3); fordSmudge.setValue(2, 0.08*windowDiameter);
		
		
		RealVector screenLeft = screenCoordinatesFrom3d(RealTransformations.dif(b, leftSmudge), false);
		RealVector screenRight = screenCoordinatesFrom3d(RealTransformations.sum(b, leftSmudge), false);
		RealVector screenUp = screenCoordinatesFrom3d(RealTransformations.dif(b, upSmudge), false);
		RealVector screenDown = screenCoordinatesFrom3d(RealTransformations.sum(b, upSmudge), false);
		RealVector screenFord = screenCoordinatesFrom3d(RealTransformations.dif(b, fordSmudge), false);
		RealVector screenBack = screenCoordinatesFrom3d(RealTransformations.sum(b, fordSmudge), false);
		/*
		RealVector screenLeft = (RealTransformations.dif(b, leftSmudge));
		RealVector screenRight = (RealTransformations.sum(b, leftSmudge));
		RealVector screenUp = (RealTransformations.dif(b, upSmudge));
		RealVector screenDown = (RealTransformations.sum(b, upSmudge));
		RealVector screenFord = (RealTransformations.dif(b, fordSmudge));
		RealVector screenBack = (RealTransformations.sum(b, fordSmudge));
		
		*/
		
		
		
		
		
		RealVector screen = screenCoordinatesFrom3d(b, false);
		
		int x = (int)screen.getValue(0);
		int y = (int)screen.getValue(1);
		
		if (type==0) {
			g.drawOval(x-2, y-2, 4, 4);
			
			
		}
		
		if (type==1) {
			//g.drawOval(x-2, y-2, 4, 4);;
			double l = 0.08*windowDiameter;
			
			RealVector pleftSmudge = new RealVector(3); 
			RealVector pupSmudge = new RealVector(3);
			RealVector pfordSmudge = new RealVector(3);
			RealVector prightSmudge = new RealVector(3); 
			
			
			pupSmudge.setValue(1, l);
			pfordSmudge.setValue(2, l*0.9428);
			pfordSmudge.setValue(1, -l/3);
			pleftSmudge.setValue(1, -l/3);
			prightSmudge.setValue(1, -l/3);
			
			pleftSmudge.setValue(0, l*0.8165);
			prightSmudge.setValue(0, -l*0.8165);
			
			pleftSmudge.setValue(2, l*(-0.4714));
			prightSmudge.setValue(2, l*(-0.4714));
			
			/*
			
			
			screenRight = (RealTransformations.sum(b, prightSmudge));
			screenLeft = (RealTransformations.sum(b, pleftSmudge));
			screenUp = (RealTransformations.sum(b, pupSmudge));
			screenFord = (RealTransformations.sum(b, pfordSmudge));
			g.drawLine((int)screenLeft.getValue(0), (int)screenLeft.getValue(1), (int)screen.getValue(0), (int)screen.getValue(1));
			g.drawLine((int)screenUp.getValue(0), (int)screenUp.getValue(1), (int)screen.getValue(0), (int)screen.getValue(1));
			g.drawLine((int)screenFord.getValue(0), (int)screenFord.getValue(1), (int)screen.getValue(0), (int)screen.getValue(1));
			g.drawLine((int)screenRight.getValue(0), (int)screenRight.getValue(1), (int)screen.getValue(0), (int)screen.getValue(1));
			*/
			
			screenRight = screenCoordinatesFrom3d(RealTransformations.sum(b, prightSmudge), false);
			screenLeft = screenCoordinatesFrom3d(RealTransformations.sum(b, pleftSmudge), false);
			screenUp = screenCoordinatesFrom3d(RealTransformations.sum(b, pupSmudge), false);
			screenFord = screenCoordinatesFrom3d(RealTransformations.sum(b, pfordSmudge), false);
			addBuffer(screenRight, screen);
			addBuffer(screenLeft, screen);
			addBuffer(screenUp, screen);
			addBuffer(screenFord, screen);
			
			//addBuffer(screen, circle);
			
		}

		
		if (type>1) {
			if (screenLeft!=null&&screenRight!=null&&screenUp!=null&&screenDown!=null&&screenFord!=null&&screenBack!=null) {
				/*g.drawLine((int)screenLeft.getValue(0), (int)screenLeft.getValue(1), (int)screenRight.getValue(0), (int)screenRight.getValue(1));
				g.drawLine((int)screenUp.getValue(0), (int)screenUp.getValue(1), (int)screenDown.getValue(0), (int)screenDown.getValue(1));
				g.drawLine((int)screenFord.getValue(0), (int)screenFord.getValue(1), (int)screenBack.getValue(0), (int)screenBack.getValue(1));
				*/
				addBuffer(screenLeft, screenRight);
				addBuffer(screenUp, screenDown);
				addBuffer(screenFord, screenBack);
				addBuffer(screenFord, screenBack);
				addBuffer(screen, circle);
				
				//g.drawOval(x-10, y-10, 20, 20);
			}
			
			
		}
		
		
		
	}
	
	
	private void setBufferColor(int type) {
		if (type==0) {
			bufferColor = Color.BLACK;
		} else if (type==1) {
			bufferColor = Color.RED;
		} else if (type==2) {
			bufferColor = Color.BLUE;
		} else if (type==3) {
			//g.setColor(new Color(0,170,10));
			bufferColor = (new Color(0,170,10));
		} else if (type==4) {
			bufferColor = Color.ORANGE;
		} else if (type==5) {
			bufferColor = Color.CYAN;
		} else if (type==6) {
			bufferColor = Color.MAGENTA;
		} else if (type==7) {
			//g.setColor(new Color(200,240,0));
			bufferColor = (new Color(200,240,0));
		} else if (type==8) {
			//g.setColor(new Color(170,0,210));
			bufferColor = (new Color(170,0,210));
		} else if (type==9) {
			//g.setColor(new Color(200,120,20));
			bufferColor = (new Color(200,120,20));
		} 
	}
	
	
	
	public boolean vectorVisible(ComplexVector vector) {
		if (vector.getSize()!=2) {
			return false;
		} else {
			RealVector a = complexNumberToCoordinate(vector);
			
			if (a!=null) {
				return true;
			} else {
				return false;
			}
		}
		
	}
	
	
	
	public void drawGUI(Graphics g, int select) {
		if (visible) {
			g.setColor(Color.WHITE);
			g.fillOval(CenterX-247, CenterY-300, 40, 40);
			GraphingWindow.drawCursor(g, CenterX-227, CenterY-280, select+2);
			g.drawLine(CenterX-213, CenterY-266, CenterX-241, CenterY-294);
			g.drawLine(CenterX-213, CenterY-294, CenterX-241, CenterY-266);
			g.fillRect(CenterX+150, CenterY-305, 100, 30);
			g.setColor(new Color(245,245,245));
			g.fillRect(CenterX+195, CenterY-205, 40, 410);;
			g.setColor(Color.BLACK);
			g.drawOval(CenterX-247, CenterY-300, 40, 40);
			g.drawRect(CenterX+150, CenterY-305, 100, 30);
			g.drawRect(CenterX+200, CenterY-200, 30, 400);
			g.drawRect(CenterX+195, CenterY-205, 40, 410);
			
			g.setColor(Color.WHITE);
			g.fillRect(CenterX+201, CenterY-199, 28, 398);
			drawImagScroll(g);
			g.setColor(Color.WHITE);
			
		}
	}
	
	
	
	
	
	
	
	public void drawComplexVector(Graphics g, ComplexVector m, int type) {
		if (visible) {
			
			RealVector a = complexNumberToCoordinate(m);
			setBufferColor(type);
			if (a!=null) {
				
				
				drawCursor(g, a, type);
				
				
				
			}
			if (onScrollBar(m)) {
				
				addScrollBuffer(m.getValue(1).getImaginary());
			}
			setBufferColor(0);
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
	
	public boolean containsImagScroll(int x, int y) {
		int relX = x-CenterX;
		int relY = y-CenterY;
		if (relX>200&&relX<230&&relY>-200&&relY<200) {
			if (!visible) {
				return false;
			}
			return true;
		} else {
			return false;
		}
		
		
	}
	
	private void drawImagScroll(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		int centralY = imagDoubleToImagScreen(center.getValue(1).getImaginary());
		g.drawLine(CenterX+215, CenterY-200, CenterX+215, CenterY+200);
		g.drawOval(CenterX+213, centralY-2, 4, 4);
		drawImagScrollGridLines(g);
		drawEntireScrollBuffer(g);
		clearScrollBuffer();
	}
	
	
	private int imagDoubleToImagScreen(double imagValue) {
		double relValue = imagValue - center.getValue(1).getImaginary();
		double scaledValue = relValue*400.0 / windowDiameter;
		int answer =  (CenterY-(int)scaledValue);
		if (answer>CenterY+200||answer<CenterY-200) {
			answer = -1;
		}
		return answer;
	}
	
	public Double imagScreenToImagDouble(int screenValue) {
		if (screenValue>CenterY+200||screenValue<CenterY-200) {
			return null;
		}
		double scaledValue = (double)(CenterY - screenValue);
		double relValue = (scaledValue * windowDiameter) / 400.0;
		double imagValue = relValue + center.getValue(1).getImaginary();
		
		return new Double(imagValue);
	}
	
	private void drawImagScrollGridLines(Graphics g) {
		g.setColor(lightlightGray);
		

		double magnitude = Math.floor(Math.log(windowDiameter/2.0)/Math.log(2));
		unit = Math.pow(2, magnitude);
		double imagGLine = unit*Math.ceil(windowLow/unit);
		while (imagGLine<windowHigh) {
			drawImagGridLine(g, imagGLine);
			imagGLine += unit;
		}
		g.setColor(Color.BLACK);
		drawImagGridLine(g, 0.0);
		
	}
	
	private void drawImagGridLine(Graphics g, double imagValue) {
		int yLoc = imagDoubleToImagScreen(imagValue);
		if (yLoc!=-1) {
			g.drawLine(200+CenterX, yLoc, 230+CenterX, yLoc);
		}
	}
	
	private void drawImagPoint(Graphics g, double imagValue) {
		int yLoc = imagDoubleToImagScreen(imagValue);
		if (yLoc!=-1) {
			g.drawLine(205+CenterX, yLoc, 225+CenterX, yLoc);
			g.drawRect(209+CenterX, yLoc-4, 12, 8);
		}
	}
	
	private void drawImagCursorLine(Graphics g, double imagValue) {
		int yLoc = imagDoubleToImagScreen(imagValue);
		if (yLoc!=-1) {
			g.drawLine(205+CenterX, yLoc-5, 225+CenterX, yLoc+5);
			g.drawLine(205+CenterX, 5+yLoc, 225+CenterX, yLoc-5);
		}
	}
	
	public void drawCursorScroll(double imagValue) {
		setBufferColor(1);
		if (imagValue>windowLow&&imagValue<windowHigh) {
			
			addScrollBuffer(imagValue);
		}
		setBufferColor(0);
	}
	
	
	
	public ComplexVector threeVecToComplexVector(RealVector a, double imag) {
		if (getQm()) {
			return new ComplexVector(new ComplexNumber(a.getValue(0),a.getValue(1)),
					new ComplexNumber(a.getValue(2), imag));
		} else {
			return new ComplexVector(new ComplexNumber(a.getValue(0),a.getValue(2)),
					new ComplexNumber(a.getValue(1), imag));
		}
		
	}
	
	
	private RealVector complexNumberToCoordinate(ComplexVector a) {
		RealVector v3d = new RealVector(3);
		if (getQm()) {
			v3d.setValue(0, a.getValue(0).getReal());
			v3d.setValue(2, a.getValue(1).getReal());
			v3d.setValue(1, a.getValue(0).getImaginary());
		} else {
			v3d.setValue(0, a.getValue(0).getReal());
			v3d.setValue(1, a.getValue(1).getReal());
			v3d.setValue(2, a.getValue(0).getImaginary());
		}
		if (v3d.getValue(0)<windowLeft||v3d.getValue(0)>windowRight) {
			return null;
		}
		if (v3d.getValue(1)<windowDown||v3d.getValue(1)>windowUp) {
			return null;
		}
		if (v3d.getValue(2)<windowBack||v3d.getValue(2)>windowForward) {
			return null;
		}
		if (a.getValue(1).getImaginary()<(windowLow+(0.49*windowDiameter))
				||a.getValue(1).getImaginary()>(windowHigh-(0.49*windowDiameter))) {
			return null;
		}
		
		
		return v3d;
		
	}
	
	private RealVector screenCoordinatesFrom3d(RealVector point, boolean safe) {
		if (point==null) {
			return null;
		}
		
		positionCamera();
		RealVector centeredPoint = RealTransformations.dif(point, vectorCenter);
		RealVector scaledPoint = RealTransformations.scale(centeredPoint, 1/windowDiameter);
		RealVector relPoint = RealTransformations.dif(scaledPoint, camera);
		RealVector relCenter = RealTransformations.dif(new RealVector(3), camera);
		RealVector location = ThreeDRotations.screenLocation(relPoint, relCenter);
		if (safe) {
			if (location.getValue(0)>fov||location.getValue(0)<nfov||location.getValue(1)>fov||location.getValue(1)<nfov) {
				return null;
			}
		}
		RealVector screenCenter = new RealVector(3);
		screenCenter.setValue(0, CenterX);
		screenCenter.setValue(1, CenterY);
		screenCenter.setValue(2, 0);
		return RealTransformations.sum(screenCenter, RealTransformations.scale(location, (250.0/fov)));
		
	}
	
	public RealVector threeDFromScreenCoordinates(RealVector screen) {
		if (!containsPoint((int)screen.getValue(0), (int)screen.getValue(1))) {
			return null;
		}
		
		
		
		RealVector screenCenter = new RealVector(3);
		screenCenter.setValue(0, CenterX);
		screenCenter.setValue(1, CenterY);
		RealVector point4 = RealTransformations.dif(screen, screenCenter);
		RealVector location = RealTransformations.scale(point4, (fov/250.0));
		RealVector relCenter = RealTransformations.dif(new RealVector(3), camera);
		RealVector relPoint = ThreeDRotations.locateScreenPoint(location, relCenter);
		RealVector scaledPoint = RealTransformations.sum(relPoint, camera);
		
		RealVector centeredPoint = RealTransformations.scale(scaledPoint, windowDiameter);
		RealVector pointA = RealTransformations.sum(centeredPoint, vectorCenter);
		
		return pointA;
	}
	
	
	
	
	public void drawGridLines(Graphics g) {
		if (visible) {
			g.setColor(new Color(245,245,245));
			g.fillRect(CenterX-265, CenterY-315, 530, 580);
			
			g.setColor(Color.WHITE);
			g.fillRect(CenterX-250, CenterY-250, 500, 500);
			
			setWindowConstraints();
			
			
			double magnitude = Math.floor(Math.log(windowDiameter/2.0)/Math.log(2));
			unit = Math.pow(2, magnitude);
			
			bufferColor = (lightlightGray);
			
			
			double verticalGLine = unit*Math.ceil(windowLeft/unit) ;
			double horizontalGLine = unit*Math.ceil(windowDown/unit);
			while (horizontalGLine<windowUp) {
				while (verticalGLine<windowRight) {
				    	drawVerticalGridLine(g, horizontalGLine, verticalGLine, 3);
				verticalGLine += unit;
			}
				verticalGLine = unit*Math.ceil(windowLeft/unit);
				horizontalGLine += unit;
			}
			
			
			 verticalGLine = unit*Math.ceil(windowBack/unit) ;
			 horizontalGLine = unit*Math.ceil(windowDown/unit);
			while (horizontalGLine<windowUp) {
				while (verticalGLine<windowForward) {
				    	drawVerticalGridLine(g, horizontalGLine, verticalGLine, 1);
				verticalGLine += unit;
			}
				verticalGLine = unit*Math.ceil(windowBack/unit);
				horizontalGLine += unit;
			}
			
			
			 verticalGLine = unit*Math.ceil(windowLeft/unit) ;
			 horizontalGLine = unit*Math.ceil(windowBack/unit);
			while (horizontalGLine<windowForward) {
				while (verticalGLine<windowRight) {
				    	drawVerticalGridLine(g, horizontalGLine, verticalGLine, 2);
				verticalGLine += unit;
			}
				verticalGLine = unit*Math.ceil(windowLeft/unit);
				horizontalGLine += unit;
			}
			
			
			
		
			
			
			//g.setColor(Color.GRAY);
			//drawVerticalGridLine(g, 0.0, true);
			//drawHorizontalGridLine(g, 0.0, true);
			
			bufferColor = Color.GRAY;
			
			if (windowLeft<0&&windowRight>0&&windowBack<0&&windowForward>0) {
				drawVerticalGridLine(g, 0, 0, 2);
			}
			if (windowDown<0&&windowUp>0&&windowBack<0&&windowForward>0) {
				drawVerticalGridLine(g, 0, 0, 1);
			}
			if (windowDown<0&&windowUp>0&&windowLeft<0&&windowRight>0) {
				drawVerticalGridLine(g, 0, 0, 3);
			}
			
			
			
			
			
			g.setColor(Color.BLACK);
			g.drawRect(CenterX-250, CenterY-250, 500, 500);
			g.drawRect(CenterX-265, CenterY-315, 530, 580);
			bufferColor = Color.BLACK;
			drawSquareOutLine(g);
		}
	}
	
	
	public void drawVerticalGridLine(Graphics g, double horizontal, double vertical, int axis) {
		RealVector back = new RealVector(3);
		back.setValue(0, vertical);
		back.setValue(1, horizontal);
		
		RealVector ford = new RealVector(3);
		ford.setValue(0, vertical);
		ford.setValue(1, horizontal);
		
		if (axis==1) {
			back.setValue(2, vertical);
			ford.setValue(2, vertical);
			ford.setValue(axis-1, windowLeft);
			back.setValue(axis-1, windowRight);
		} else if (axis==2) {
			back.setValue(2, horizontal);
			ford.setValue(2, horizontal);
			ford.setValue(axis-1, windowUp);
			back.setValue(axis-1, windowDown);
		} else if (axis==3) {
			ford.setValue(axis-1, windowForward);
			back.setValue(axis-1, windowBack);
		}
		
		
		
		
		
		
		
		
		draw3dLine(g,back,ford);

		
		
		
		
		/*temp.setComplexNumber(0.0, imag);
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
		}*/
	}
	
	
	
	
	
	
	
	
	public void drawSquareOutLine(Graphics g) {
		g.setColor(Color.GRAY);
		RealVector corner[] = new RealVector[8];
		for (int i=0; i<8; i++) {
			corner[i] = new RealVector(3);
		}
		
		int j=0;
		for (int f=0; f<2; f++) {
			for (int u=0; u<2; u++) {
				for (int l=0; l<2; l++) {
					if (f==0) {
						corner[j].setValue(2, windowForward);
					} else {
						corner[j].setValue(2, windowBack);
					}
					if (u==0) {
						corner[j].setValue(1, windowUp);
					} else {
						corner[j].setValue(1, windowDown);
					}
					if (l==0) {
						corner[j].setValue(0, windowLeft);
					} else {
						corner[j].setValue(0, windowRight);
					}
					
					
					
					
					j++;
				}
			}
		}
		
		draw3dLine(g,corner[0],corner[1]);
		draw3dLine(g,corner[2],corner[3]);
		draw3dLine(g,corner[4],corner[5]);
		draw3dLine(g,corner[6],corner[7]);
		
		draw3dLine(g,corner[0],corner[2]);
		draw3dLine(g,corner[1],corner[3]);
		draw3dLine(g,corner[4],corner[6]);
		draw3dLine(g,corner[5],corner[7]);
		
		draw3dLine(g,corner[0],corner[4]);
		draw3dLine(g,corner[1],corner[5]);
		draw3dLine(g,corner[2],corner[6]);
		draw3dLine(g,corner[3],corner[7]);
	}
	
	
	private void draw3dLine(Graphics g, RealVector a, RealVector b) {
		for (int i=0; i<18; i++) {
			RealVector first = RealTransformations.vectorParts(a, b, i, 20);
			RealVector second = RealTransformations.vectorParts(a, b, i+3, 20);
			RealVector c = screenCoordinatesFrom3d(first, false);
			RealVector d = screenCoordinatesFrom3d(second, false);
			if (c!=null&&d!=null) {
				//draw2dLine(g,c,d);
				addBuffer(c,d);
			}
		}
		
		
	}
	
	private void draw2dLine(Graphics g, RealVector a3, RealVector b3) {
		RealVector a = new RealVector(2);
		RealVector b = new RealVector(2);
		a.setValue(0, a3.getValue(0)); a.setValue(1, a3.getValue(1));
		b.setValue(0, b3.getValue(0)); b.setValue(1, b3.getValue(1));
		
		
		int x1 = (int)a.getValue(0);
		int y1 = (int)a.getValue(1);
		int x2 = (int)b.getValue(0);
		int y2 = (int)b.getValue(1);
		
		if (containsPoint(x1,y1)&&containsPoint(x2,y2)) {
			g.drawLine(x1, y1, x2, y2);
		} else {
			RealVector initialIntersection = RealIntersections.boundaryLinePoint(CenterX, CenterY, a, b);
			
			if (initialIntersection!=null) {
				
				if (initialIntersection.getSize()==2) {
					RealVector intersection = RealTransformations.sum(initialIntersection, new RealVector(CenterX, CenterY));
					
					int nx1 = (int)intersection.getValue(0);
					int ny1 = (int)intersection.getValue(1);
					if (containsPoint(x1,y1)) {
						g.drawLine(nx1, ny1, x1, y1);
						
					}
					if (containsPoint(x2,y2)) {
						g.drawLine(nx1, ny1, x2, y2);
					}
					
					
					
				} else if (initialIntersection.getSize()==4) {
					RealVector quadCenter = new RealVector(4);
					quadCenter.setValue(0, CenterX); quadCenter.setValue(2, CenterX);
					quadCenter.setValue(1, CenterY); quadCenter.setValue(3, CenterY);
					RealVector intersection = RealTransformations.sum(initialIntersection, quadCenter);
					
					int nx1 = (int)intersection.getValue(0);
					int ny1 = (int)intersection.getValue(1);
					int nx2 = (int)intersection.getValue(2);
					int ny2 = (int)intersection.getValue(3);
					g.drawLine(nx1, ny1, nx2, ny2);
				}

			}
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setCenterX(int x) {
		CenterX = x;
	}
	
	public void incrementCameraAngles(int x, int y) {
		double amount = 0.005;
		
		angleT+=(amount*x);
		angleY+=(amount*y);
		positionCamera();
	}
	
	
	private void setCameraAngles(double y, double t) {
		angleY = y;
		angleT = t;
		positionCamera();
	}
	
	private void positionCamera() {
		if (angleY>Math.PI/2) {
			angleY = Math.PI/2;
		}
		if (angleY<-Math.PI/2) {
			angleY = -Math.PI/2;
		}
		
		
		
		camera.setValue(1, cameraRadius*Math.sin(angleY));
		camera.setValue(2, cameraRadius*Math.cos(angleY)*Math.cos(angleT));
		camera.setValue(0, cameraRadius*Math.sin(angleT)*Math.cos(angleY));
	}
	
	private boolean qm;
	public void sqapQM() {
		qm=!qm;
		setWindowConstraints();
	}
	
	/*
	private int roundedDigit(double a) {
		return (int)Math.round(a/unit);
		
		
	}*/
	
	ArrayList<RealVector> sortedBuffer;
	
	private void sortBuffer() {
		while (sortedBuffer.size()>0) {
			sortedBuffer.remove(0);
		}
		for (int i=0; i<Buffer.size(); i++) {
			insertLine(Buffer.get(i));
		}
		
		
	}
	
	private void insertLine(RealVector line7) {
		int maxIndex = sortedBuffer.size()-1;
		int minIndex = 0;
		while (minIndex<=maxIndex) {
			int midIndex = (maxIndex+minIndex)/2;
			if (line7.getValue(2)>sortedBuffer.get(midIndex).getValue(2)) {
				minIndex=midIndex+1;
			} else if (line7.getValue(2)<sortedBuffer.get(midIndex).getValue(2)) {
				maxIndex=midIndex-1;
			} else {
				maxIndex = midIndex-1;
				minIndex = midIndex;
			}
			}
		sortedBuffer.add(minIndex, line7);
		
	}
	
	
	
	public void clearBuffer() {
		
		while (Buffer.size()>0) {
			Buffer.remove(0);
		}
	}
	
	public void drawEntireBuffer(Graphics g) {
		if (visible) {
			sortBuffer();
			for (int i=sortedBuffer.size()-1; i>-1; i--) {
				drawLineFromBuffer(g, sortedBuffer.get(i));
			}
			clearBuffer();
		}
	}
	
	private void addBuffer(RealVector a, RealVector b) {
		RealVector line = new RealVector(7);
		line.setValue(0, a.getValue(0)); line.setValue(1, a.getValue(1)); line.setValue(2, a.getValue(2));
		line.setValue(3, b.getValue(0)); line.setValue(4, b.getValue(1)); line.setValue(5, b.getValue(2));
		line.setValue(6, (double)getBufferColorNumber());
		Buffer.add(line);
		
	}
	
	
	private void drawLineFromBuffer(Graphics g, RealVector line) {
		RealVector a = new RealVector(line.getValue(0), line.getValue(1), line.getValue(2));
		RealVector b = new RealVector(line.getValue(3), line.getValue(4), line.getValue(5));
		g.setColor(getColorfromBuffer((int)line.getValue(6)));
		
		if (b.getValue(0)==circle.getValue(0)&&b.getValue(1)==circle.getValue(1)) {
			drawCircle(g,a);
		} else {
			draw2dLine(g,a,b);	
		}
		
		
	}
	
	private void drawCircle(Graphics g, RealVector location) {
		int x = (int)location.getValue(0);
		int y = (int)location.getValue(1);
		if (containsPoint(x,y)) {
	 		g.drawOval(x-10, y-10, 20, 20);
		}

		
		
	}
	
	
	private int getBufferColorNumber() {
		//This method declares the types of buffer int colors.
		int blue = bufferColor.getBlue();
		int red = bufferColor.getRed();
		int green = bufferColor.getGreen();
		int totRed = 256*256*red;
		int totGreen = 256*green;
		return blue+totRed+totGreen;
		
	}
	
	private Color getColorfromBuffer(int num) {
		int red = num/(256*256);
		int green = (num%(256*256))/(256);
		int blue = (num%256);
		Color x = new Color(red,green,blue);
		return x;
	}
	
	
	
	private void addScrollBuffer(double a) {
		RealVector line = new RealVector(2);
		line.setValue(0, a);
		line.setValue(1, (double)getBufferColorNumber());
		ScrollBuffer.add(line);
		
	}
	public void clearScrollBuffer() {
		
		while (ScrollBuffer.size()>0) {
			ScrollBuffer.remove(0);
		}
	}
	
	public void drawEntireScrollBuffer(Graphics g) {
		if (visible) {
			
			for (int i=ScrollBuffer.size()-1; i>-1; i--) {
				drawLineFromScrollBuffer(g, ScrollBuffer.get(i));
			}
			clearScrollBuffer();
		}
	}
	
	private void drawLineFromScrollBuffer(Graphics g, RealVector line) {
		double a = line.getValue(0);
		g.setColor(getColorfromBuffer((int)line.getValue(1)));
		
		if (line.getValue(1)==16711680) {
			drawImagCursorLine(g, a);
		} else if (line.getValue(1)!=0) {
			drawImagPoint(g, a);
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	public boolean getQm() {
		return qm;
	}

	public void plotFunction(ArrayList<ComplexNumber> input, int fnNumber) {
		setBufferColor(fnNumber+2);
		double segment = windowDiameter/divisions;
		RealVector[] points = new RealVector[(divisions+1)];
		for (int i=0; i<=divisions; i++) {
			double rex;
			if (qm) {
				rex=windowBack+(segment * (double)i);
			}else {
				rex=windowDown+(segment * (double)i);
			}
			ComplexNumber x = new ComplexNumber(rex, center.getValue(1).getImaginary());
			ComplexNumber y = GraphingFunctions.curve(input, x, fnNumber);
			ComplexVector p = new ComplexVector(y,x);
			RealVector point = complexNumberToCoordinate(p);
			points[i]=point;
			
			
		}
		
		for (int i=0; i<divisions; i++) {
			
			RealVector c = screenCoordinatesFrom3d(points[i], false);
			RealVector d = screenCoordinatesFrom3d(points[i+1], false);
			if (c!=null&&d!=null) {
				//draw2dLine(g,c,d);
				addBuffer(c,d);
			}
		}
		
	}

	
	
	

}
