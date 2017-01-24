package GraphingCalculator;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.event.MouseInputAdapter;


public class GraphingCalculator extends Applet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	int dragDownXCoord;
	int dragDownYCoord;
	int rotateXCoord;
	int rotateYCoord;
	
	
	Rectangle dragButton;

	
	
	GraphingWindowController controller;
	MouseInputAdapter listener;
	KeyAdapter keyboard;

	boolean stillPainting;
	
	
	
	public void init() {
		setSize(1150,650);
		stillPainting = false;
		dragButton = new Rectangle(50, 570, 150, 30);
		
		controller = new GraphingWindowController();
		
		keyboard = new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()=='1') {
					
					controller.hide(1);
				}
				if (e.getKeyChar()=='2') {
					
					controller.hide(2);
				}
				if (e.getKeyChar()=='3') {
					
					controller.hide(3);
				}
				if (e.getKeyChar()=='4') {
					
					controller.hide(4);
				}
				if (e.getKeyChar()=='q') {
					controller.realXMode();
				}/*
				if (e.getKeyChar()=='a') {
					
					controller.rotate(2);
				}
				if (e.getKeyChar()=='s') {
	
					controller.rotate(3);
				}
				if (e.getKeyChar()=='d') {
	
					controller.rotate(4);
				}*/
				if (!stillPainting) {
					repaint();
				}
				
			}
		};
		
		
		
		listener = new MouseInputAdapter(){
			
			
			
			public void mouseMoved(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
				
				//if (!dragging) {
				controller.setDomainHover(x, y);
				
				
				//}
				if (!stillPainting) {
					repaint();
				}
				
				
				
				
			}
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int b = e.getButton();
				
				if (b==2) {
					dragDownXCoord = x;
					dragDownYCoord = y;
					controller.startDrag(x, y);
				} else if (b==1) {
					controller.setDomainClick(x, y);
					
				} else if (b==3) {
					controller.switchSelector(x, y);
					controller.setDomainHover(x, y);
					rotateXCoord = x;
					rotateYCoord = y;
					controller.startRotate(x, y);
				}
				if (!stillPainting) {
					repaint();
				}
				
				
				
			}
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int b = e.getButton();
				if (b==2) {
					
					controller.drag(x, y, x-dragDownXCoord, y-dragDownYCoord);
					dragDownXCoord = x;
					dragDownYCoord = y;
					
				} else if (b==1) {
				controller.setDomainClick(x, y);
				controller.setDomainHover(x, y);
				
				
				} else if (b==3) {
					controller.rotate(x, y, x-rotateXCoord, y-rotateYCoord);
					rotateXCoord = x;
					rotateYCoord = y;
				}
				
				controller.setDomainHover(x, y);
				if (!stillPainting) {
					repaint();
				}
				
				
			}
			
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if (dragButton.contains(x, y)) {
					
				}
				
			}
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				int x = e.getX();
				int y = e.getY();
				//if (dragging) {
					controller.scroll(notches, x, y);
					controller.setDomainHover(x, y);
				//}
					if (!stillPainting) {
						repaint();
					}
					
				
			}
			
			
			
			
			
			
			
		};
		addMouseListener(listener);
		addMouseMotionListener(listener);
		addMouseWheelListener(listener);
		addKeyListener(keyboard);
		
		
	}
	
	
	public void paint(Graphics g) {
		stillPainting = true;
		g.setColor(new Color(100,160,200));
		g.fillRect(0, 0, 1150, 650);
		g.setColor(new Color(180,220,250));
		g.fillRect(13, 13, 1124, 624);
		controller.drawGridLines(g);
		
		
	
		
		controller.drawPoints(g);
		stillPainting = false;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
