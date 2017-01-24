package GraphingCalculator;


import java.awt.Graphics;
import java.util.ArrayList;

import ComplexCalculator.*;
import LinearAlgebra.ComplexVector;
import LinearAlgebra.RealVector;


public class GraphingWindowController {

	GraphingWindow domain;
	GraphingWindow range;
	GamingWindow gameDomain;
	GamingWindow gameRange;
	
	boolean domainVisible;
	boolean rangeVisible;
	boolean gameDomainVisible;
	boolean gameRangeVisible;
	
	int leftView;
	int rightView;
	
	
	
	
	ComplexNumber domainCenter;
	ComplexNumber rangeCenter;
	ComplexVector gameDomainCenter;
	ComplexVector gameRangeCenter;
	
	
	

	
	boolean visibleCursor;
	boolean gameVisibleCursor;
	boolean gameRangeVisibleCursor;
	boolean scrollVisibleCursor;
	boolean scrollRangeVisibleCursor;
	int draggingStatus;
	int rotatingStatus;
	//0 not dragging, 1 for domain, 2 for range.
	
	
	ComplexNumber domainHover;
	ComplexNumber rangeHover;
	RealVector gameDomainHover;
	RealVector gameRangeHover;
	Double scrollDomainHover;
	Double scrollRangeHover;
	
	
	ArrayList<ComplexNumber> domainClick;
	ArrayList<ComplexNumber> rangeClick;
	ArrayList<String> domainNames;
	ArrayList<String> rangeNames;
	ArrayList<ComplexVector> gameDomainClick;
	ArrayList<ComplexVector> gameRangeClick;
	ArrayList<String> gameDomainNames;
	ArrayList<String> gameRangeNames;
	
	
	
	
	int domainSelect;
	int rangeSelect;
	int gameDomainSelect;
	int gameRangeSelect;
	
	
	
	
	
	
	
	
	
	
	
	public GraphingWindowController() {
		leftView = 1;
		rightView = 2;
		
		domainSelect = 0;
		rangeSelect = 0;
		gameDomainSelect = 0;
		gameRangeSelect = 0;
		
		domainVisible = true;
		rangeVisible = true;
		gameDomainVisible = false;
		gameRangeVisible = false;
		
		
		domainCenter = new ComplexNumber();
		gameDomainCenter = new ComplexVector(2);
		domainClick = new ArrayList<ComplexNumber>();
		gameDomainClick = new ArrayList<ComplexVector>();
		domainNames = new ArrayList<String>();
		gameDomainNames = new ArrayList<String>();
		
		
		
		for (int i=0; i<GraphingFunctions.totalGameDomainSize; i++) {
			gameDomainClick.add(new ComplexVector(2));
			gameDomainNames.add(GraphingFunctions.gameDomainNames[i]);
		}
		
		
		
		for (int i=0; i<GraphingFunctions.totalDomainSize; i++) {
			if ((i/2)<GraphingFunctions.totalGameDomainSize) {
				if (i%2==0) {
					domainClick.add(gameDomainClick.get(i/2).getValue(0));
				} else {
					domainClick.add(gameDomainClick.get(i/2).getValue(1));
				}
			}  else {
				domainClick.add(new ComplexNumber());
			}
			
			
			
			domainNames.add(GraphingFunctions.domainNames[i]);
		}
		
		
		
		domainHover = new ComplexNumber();
		gameDomainHover = new RealVector(3);
		gameRangeHover = new RealVector(3);
		scrollDomainHover = null;
		scrollRangeHover = null;
		
		visibleCursor = false;
		gameVisibleCursor = false;
		gameRangeVisibleCursor = false;
		scrollVisibleCursor = false;
		scrollRangeVisibleCursor = false;
		rangeCenter = new ComplexNumber();
		gameRangeCenter = new ComplexVector(2);
		rangeClick = new ArrayList<ComplexNumber>();
		rangeNames = new ArrayList<String>();
		gameRangeClick = new ArrayList<ComplexVector>();
		gameRangeNames = new ArrayList<String>();
		
		for (int i=0; i<GraphingFunctions.totalGameRangeSize; i++) {
			gameRangeClick.add(new ComplexVector(2));
			gameRangeNames.add(GraphingFunctions.gameRangeNames[i]);
		}
		
		for (int i=0; i<GraphingFunctions.totalRangeSize; i++) {
			if ((i/2)<GraphingFunctions.totalGameRangeSize) {
				if (i%2==0) {
					rangeClick.add(gameRangeClick.get(i/2).getValue(0));
				} else {
					rangeClick.add(gameRangeClick.get(i/2).getValue(1));
				}
			}  else {
				rangeClick.add(new ComplexNumber());
			}
			rangeNames.add(GraphingFunctions.rangeNames[i]);
		}
		
		
		calculateOutput();
		calculateGameOutput();
		
		domain = new GraphingWindow(domainCenter, 300, 350);
		range = new GraphingWindow(rangeCenter, 850, 350);
		gameDomain = new GamingWindow(gameDomainCenter, 300, 350);
		gameRange = new GamingWindow(gameRangeCenter, 300, 350);
		
		
		setVisibility();
		
		
	}
	
	
	private void calculateGameOutput() {
		
		
	}
	
	private void setVisibility() {
		boolean currentGameDomainVisibility = gameDomain.visible;
		
		domain.setVisible(domainVisible);
		range.setVisible(rangeVisible);
		gameDomain.setVisible(gameDomainVisible);
		gameRange.setVisible(gameRangeVisible);
		if (currentGameDomainVisibility!=gameDomainVisible) {
			if (gameDomain.visible) {
			domainClick.add(gameDomainCenter.getValue(0));
			domainClick.add(gameDomainCenter.getValue(1));
			domainNames.add("Domain X-Coord");
			domainNames.add("Domain Y-Coord");
			} else {
				if (domainClick.get(domainClick.size()-2)==gameDomainCenter.getValue(0)) {
					domainClick.remove(domainClick.size()-1);
					domainClick.remove(domainClick.size()-1);
					domainNames.remove(domainNames.size()-1);
					domainNames.remove(domainNames.size()-1);
				} else {
					domainClick.remove(domainClick.size()-3);
					domainClick.remove(domainClick.size()-3);
					domainNames.remove(domainNames.size()-3);
					domainNames.remove(domainNames.size()-3);
				}
			}
			
			if (domainSelect>=domainClick.size()) {
				domainSelect=0;
			}
			
		}
		
		
		
		if (leftView==1) {
			domain.setCenterX(300);
		} else if (leftView==2) {
			range.setCenterX(300);
		} else if (leftView==3) {
			gameDomain.setCenterX(300);
		} else if (leftView==4) {
			gameRange.setCenterX(300);
		}
		if (rightView==1) {
			domain.setCenterX(850);
		} else if (rightView==2) {
			range.setCenterX(850);
		} else if (rightView==3) {
			gameDomain.setCenterX(850);
		} else if (rightView==4) {
			gameRange.setCenterX(850);
		}
		
	}
	
	
	private void calculateOutput() {
		ArrayList<ComplexNumber> hoverData = new ArrayList<ComplexNumber>();
		for (ComplexNumber a: domainClick) {
			hoverData.add(a);
		}
		hoverData.set(domainSelect, domainHover);
		
		rangeHover = GraphingFunctions.graph(hoverData, rangeSelect);
		for (int i=0; i<rangeClick.size(); i++) {
			rangeClick.get(i).set(GraphingFunctions.graph(domainClick, i)) ;
		}
		
		
		
	}
	
	
	
	public void drawPoints(Graphics g) {
		calculateOutput();
		calculateGameOutput();
		if (visibleCursor) {
			domain.drawComplexNumber(g, domainHover, 1);
			range.drawComplexNumber(g, rangeHover, 1);
		}
		if (gameVisibleCursor) {
			ComplexVector gdHover = gameDomain.threeVecToComplexVector(gameDomainHover, gameDomainCenter.getValue(1).getImaginary());
			gameDomain.drawComplexVector(g, gdHover, 1);
		}
		if (gameRangeVisibleCursor) {
			ComplexVector gdHover = gameRange.threeVecToComplexVector(gameRangeHover, gameRangeCenter.getValue(1).getImaginary());
			gameRange.drawComplexVector(g, gdHover, 1);
		}
		if (scrollVisibleCursor) {
			if (scrollDomainHover!=null) {
				gameDomain.drawCursorScroll(scrollDomainHover.doubleValue());
			}
		}
		if (scrollRangeVisibleCursor) {
			if (scrollRangeHover!=null) {
				gameRange.drawCursorScroll(scrollRangeHover.doubleValue());
			}
		}
		domain.drawComplexNumber(g, domainCenter, 0);
		range.drawComplexNumber(g, rangeCenter, 0);
		gameDomain.drawComplexVector(g, gameDomainCenter, 0);
		gameRange.drawComplexVector(g, gameRangeCenter, 0);
		
		for (int i=domainClick.size()-1; i>-1; i--) {
			if (domainClick.get(i)==gameDomainCenter.getValue(0)) {
				domain.drawComplexNumber(g, domainClick.get(i), 102);
			} else if (domainClick.get(i)==gameDomainCenter.getValue(1)) {
				domain.drawComplexNumber(g, domainClick.get(i), 103);
			} else {
				domain.drawComplexNumber(g, domainClick.get(i), 2+i);
			}
			
		}
		
		for (int i=rangeClick.size()-1; i>-1; i--) {
			range.drawComplexNumber(g, rangeClick.get(i), 2+i);
		}
		
		for (int i=gameDomainClick.size()-1; i>-1; i--) {
			gameDomain.drawComplexVector(g, gameDomainClick.get(i), 2+i);
		}
		
		for (int i=gameRangeClick.size()-1; i>-1; i--) {
			gameRange.drawComplexVector(g, gameRangeClick.get(i), 2+i);
		}
		
		if (domainVisible) {
			g.drawString(domainNames.get(domainSelect), domain.CenterX-200, domain.CenterY-280);
			g.drawString("x = " + domainClick.get(domainSelect).toString(), domain.CenterX-200, domain.CenterY-260);
		}
		
		if (rangeVisible) {
			g.drawString(rangeNames.get(rangeSelect), range.CenterX-200, range.CenterY-280);
			g.drawString("y = " + rangeClick.get(rangeSelect).toString(), range.CenterX-200, range.CenterY-260);	
		}
		
		for (int i=0; i<GraphingFunctions.totalCurveSize; i++) {
			gameDomain.plotFunction(domainClick, i);
			gameRange.plotFunction(domainClick, i);
		}
		
		if (domainClick.get(domainSelect)==gameDomainCenter.getValue(0)) {
			domain.drawGUI(g, 100);
		} else if (domainClick.get(domainSelect)==gameDomainCenter.getValue(1)) {
			domain.drawGUI(g, 101);
		} else {
			domain.drawGUI(g, domainSelect);
		}
		
		gameDomain.drawEntireBuffer(g);
		gameRange.drawEntireBuffer(g);
		
		range.drawGUI(g, rangeSelect);
		gameDomain.drawGUI(g, gameDomainSelect);
		gameRange.drawGUI(g, gameRangeSelect);
		
		
		
		
		
		
		
		
		
	}
	

	
	
	public void setDomainClick(int x, int y) {
		if (domain.containsPoint(x, y)) {
			ComplexNumber temp = domain.coordinatesToComplexNumber(x, y);
			if (temp!=null) {
				domainClick.get(domainSelect).set(temp);
			}
		}
		if (gameDomain.containsPoint(x, y)&&!gameDomain.containsImagScroll(x, y)) {
			ComplexVector gdHover = gameDomain.threeVecToComplexVector(gameDomainHover, gameDomainCenter.getValue(1).getImaginary());
			
			if (gameDomain.vectorVisible(gdHover)) {
				gameDomainClick.get(gameDomainSelect).setVector(gdHover);
			}
			
		}
		if (gameDomain.containsImagScroll(x, y)) {
			Double number = gameDomain.imagScreenToImagDouble(y);
			if (number!=null) {
				ComplexVector current = gameDomainClick.get(gameDomainSelect);
				current.getValue(1).setComplexNumber(current.getValue(1).getReal(), 
						number);
			}
		}
		
		
	}
	
	
	
	public void startDrag(int x, int y) {
		if (domain.containsPoint(x, y)) {
			draggingStatus = 1;
		} else if (range.containsPoint(x, y)) {
			draggingStatus = 2;
		} else if (gameDomain.containsImagScroll(x, y)) {
			draggingStatus = 5;
		} else if (gameRange.containsImagScroll(x, y)) {
			draggingStatus = 6;
		} else if (gameDomain.containsPoint(x, y)) { 
			draggingStatus = 3;
		} else if (gameRange.containsPoint(x, y)) { 
			draggingStatus = 4;
		} else {
			draggingStatus = 0;
		}
	}
	
	
	
	
	
	
	
	
	public void startRotate(int x, int y) {
		if (gameDomain.containsPoint(x, y)) {
			rotatingStatus = 3;
		} else if (gameRange.containsPoint(x, y)) {
			rotatingStatus = 4;
		} else {
			rotatingStatus = 0;
		}
	}
	
	
	public void drag(int x, int y, int dx, int dy) {
		if (draggingStatus!=0) {
			if (domain.containsPoint(x, y)) {
				if (draggingStatus!=1) {
					draggingStatus = 0;
				}
			} else if (range.containsPoint(x, y)) {
				if (draggingStatus!=2) {
					draggingStatus = 0;
				}
			} else if (gameDomain.containsImagScroll(x, y)) {
				if (draggingStatus!=5) {
					draggingStatus = 0;
				}
			} else if (gameRange.containsImagScroll(x, y)) {
				if (draggingStatus!=6) {
					draggingStatus = 0;
				}
			}else if (gameDomain.containsPoint(x, y)) {
				if (draggingStatus!=3) {
					draggingStatus = 0;
				}
			} else if (gameRange.containsPoint(x, y)) {
				if (draggingStatus!=4) {
					draggingStatus = 0;
				}
			} else {
				draggingStatus = 0;
			}
			if (draggingStatus==1) {
				ComplexNumber temp = domain.coordinatesToComplexNumber(domain.CenterX-dx, domain.CenterY-dy);
				if (temp!=null) {
					domainCenter.set(temp);
					domain.setComplexCenter(domainCenter);
					domain.setWindowConstraints();
				}
			}
			if (draggingStatus==2) {
				ComplexNumber temp = range.coordinatesToComplexNumber(range.CenterX-dx, range.CenterY-dy);
				if (temp!=null) {
					rangeCenter.set(temp);
					range.setComplexCenter(rangeCenter);
					range.setWindowConstraints();
				}
			}
			if (draggingStatus==3) {
				RealVector screen = new RealVector(3);
				screen.setValue(0, gameDomain.CenterX-dx); screen.setValue(1, gameDomain.CenterY-dy);
				screen.setValue(2, 375.0/GamingWindow.fov);
				RealVector temp = gameDomain.threeDFromScreenCoordinates(screen);
				if (temp!=null) {
					ComplexVector c = gameDomain.threeVecToComplexVector(temp, gameDomainCenter.getValue(1).getImaginary());
					
					gameDomainCenter.setVector(c);;
					
					gameDomain.setWindowConstraints();
				}
			}
			if (draggingStatus==4) {
				RealVector screen = new RealVector(3);
				screen.setValue(0, gameRange.CenterX-dx); screen.setValue(1, gameRange.CenterY-dy);
				screen.setValue(2, 375.0/GamingWindow.fov);
				RealVector temp = gameRange.threeDFromScreenCoordinates(screen);
				if (temp!=null) {
					ComplexVector c = gameRange.threeVecToComplexVector(temp, gameRangeCenter.getValue(1).getImaginary());
					
					gameRangeCenter.setVector(c);;
					
					gameRange.setWindowConstraints();
				}
			}
			if (draggingStatus==5) {
				Double temp = gameDomain.imagScreenToImagDouble(gameDomain.CenterY-dy);
				if (temp!=null) {
					
					ComplexVector c = new ComplexVector(gameDomainCenter.getValue(0),
							new ComplexNumber(gameDomainCenter.getValue(1).getReal(), temp));
					gameDomainCenter.setVector(c);;
					
					gameDomain.setWindowConstraints();
				}
				
			}
			if (draggingStatus==6) {
				Double temp = gameRange.imagScreenToImagDouble(gameRange.CenterY-dy);
				if (temp!=null) {
					
					ComplexVector c = new ComplexVector(gameRangeCenter.getValue(0),
							new ComplexNumber(gameRangeCenter.getValue(1).getReal(), temp));
					gameRangeCenter.setVector(c);;
					
					gameRange.setWindowConstraints();
				}
			}
			calculateOutput();
			
			
		}
	}
	
	
	public void rotate(int x, int y, int dx, int dy) {
		if (rotatingStatus!=0) {
			if (gameDomain.containsPoint(x, y)) {
				rotatingStatus = 3;
				gameDomain.incrementCameraAngles(dx, dy);
			} else if (gameRange.containsPoint(x,y)) {
				rotatingStatus = 4;
				gameRange.incrementCameraAngles(dx, dy);
			} else {
				rotatingStatus=0;
			}
			
		}
	}
	
	
	public void scroll(int notches, int x, int y) {
		if (domain.containsPoint(x, y)) {
			domain.scroll(notches);
		} else if (range.containsPoint(x, y)) {
			range.scroll(notches);
		} else if (gameDomain.containsPoint(x, y)) {
			gameDomain.scroll(notches, x, y);
		} else if (gameRange.containsPoint(x, y)) {
			gameRange.scroll(notches, x, y);
		}
		
	}
	
	
	
	
	
	
	
	public void setDomainHover(int x, int y) {
		ComplexNumber temp = domain.coordinatesToComplexNumber(x, y);
		if (temp!=null) {
			
			domainHover.set(temp);
			visibleCursor = true;
		} else {
			visibleCursor = false;
		}
		
		RealVector screenVector = new RealVector(3);
		screenVector.setValue(0, x);
		screenVector.setValue(1, y);
		screenVector.setValue(2, 375.0/GamingWindow.fov);
		RealVector gtemp = gameDomain.threeDFromScreenCoordinates(screenVector);
		
		if (gtemp!=null&&!gameDomain.containsImagScroll(x, y)) {
			
			gameDomainHover.setVector(gtemp);
			gameVisibleCursor = true;
			scrollVisibleCursor = false;
		} else if (gameDomain.containsImagScroll(x, y)) { 
			gameVisibleCursor = false;
			scrollVisibleCursor = true;
			scrollDomainHover = gameDomain.imagScreenToImagDouble(y);
		} else {
			gameVisibleCursor = false;
			scrollVisibleCursor = false;
		}
		RealVector grtemp = gameRange.threeDFromScreenCoordinates(screenVector);
		if (grtemp!=null&&!gameRange.containsImagScroll(x, y)) {
			
			gameRangeHover.setVector(grtemp);
			gameRangeVisibleCursor = true;
			scrollRangeVisibleCursor = false;
		} else if (gameRange.containsImagScroll(x, y)) {
			gameRangeVisibleCursor = false;
			scrollRangeVisibleCursor = true;
			scrollRangeHover = gameRange.imagScreenToImagDouble(y);
		} else {
			gameRangeVisibleCursor = false;
			scrollRangeVisibleCursor = false;
		}
		

		
	}
	
	public void drawGridLines(Graphics g) {
		domain.drawGridLines(g);
		range.drawGridLines(g);
		gameDomain.clearBuffer();
		gameDomain.drawGridLines(g);
		gameRange.clearBuffer();
		gameRange.drawGridLines(g);
	}
	
	
	public void switchSelector(int x, int y) {
		if (domain.containsPoint(x, y)) {
			domainSelect+=1;
			if (domainSelect==domainClick.size()) {
				domainSelect=0;
			}
			domainCenter.set(domainClick.get(domainSelect));
			domain.setComplexCenter(domainCenter);
			domain.setWindowConstraints();
			
		}
		
		
		if (range.containsPoint(x, y)) {
			rangeSelect+=1;
			if (rangeSelect==rangeClick.size()) {
				rangeSelect=0;
			}
			rangeCenter.set(rangeClick.get(rangeSelect));
			range.setComplexCenter(rangeCenter);
			range.setWindowConstraints();
		}
		
		if (gameDomain.containsImagScroll(x, y)) {
			gameDomainSelect+=1;
			if (gameDomainSelect==gameDomainClick.size()) {
				gameDomainSelect=0;
			}
			gameDomainCenter.setVector(gameDomainClick.get(gameDomainSelect));
			
			gameDomain.setWindowConstraints();
			
		}
		
		if (gameRange.containsImagScroll(x, y)) {
			gameRangeSelect+=1;
			if (gameRangeSelect==gameRangeClick.size()) {
				gameRangeSelect=0;
			}
			gameRangeCenter.setVector(gameRangeClick.get(gameRangeSelect));
			
			gameRange.setWindowConstraints();
			
		}
		
		
	}
	
	public void hide(int x) {
		
		if (x==1) {
			if (domainVisible) {
				domainVisible = false;
				if (leftView==1) {
					leftView = 0;
				}
				if (rightView==1) {
					rightView = 0;
				}
			} else {
				if (leftView==0) {
					domainVisible = true;
					leftView = 1;
				} else if (rightView==0) {
					domainVisible = true;
					rightView = 1;
				}
			}
		}
		if (x==2) {
			if (rangeVisible) {
				rangeVisible = false;
				if (leftView==2) {
					leftView = 0;
				}
				if (rightView==2) {
					rightView = 0;
				}
			} else {
				if (leftView==0) {
					rangeVisible = true;
					leftView = 2;
				} else if (rightView==0) {
					rangeVisible = true;
					rightView = 2;
				}
			}
		}
		if (x==3) {
			if (gameDomainVisible) {
				gameDomainVisible = false;
				if (leftView==3) {
					leftView = 0;
				}
				if (rightView==3) {
					rightView = 0;
				}
			} else {
				if (leftView==0) {
					gameDomainVisible = true;
					leftView = 3;
				} else if (rightView==0) {
					gameDomainVisible = true;
					rightView = 3;
				}
			}
		}
		if (x==4) {
			if (gameRangeVisible) {
				gameRangeVisible = false;
				if (leftView==4) {
					leftView = 0;
				}
				if (rightView==4) {
					rightView = 0;
				}
			} else {
				if (leftView==0) {
					gameRangeVisible = true;
					leftView = 4;
				} else if (rightView==0) {
					gameRangeVisible = true;
					rightView = 4;
				}
			}
		}
		setVisibility();
	}
	
	
	public void realXMode() {
		if (gameDomainVisible){
			gameDomain.sqapQM();
		} else if (gameRangeVisible) {
			gameRange.sqapQM();
		}
		
	}
	
	
	
	

	
}
