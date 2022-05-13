/*
 * Implements an animation that moves car shapes
 * 
 * @Fariha Ahmed
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AnimationTester1 {
	private static final int ICON_WIDTH = 500, ICON_HEIGHT = 100, CAR_WIDTH = 100;
	
	public static void main(String[] args){
		JFrame f = new JFrame();

	    MoveableShape[] shapes = new MoveableShape[10];
	    for(int i = 0; i < shapes.length; i++) {
	    	shapes[i] = new CarShape(0, 0, CAR_WIDTH);
	    	shapes[i].translate(i * 150,0);
	    }

	    ShapeIcon icon = new ShapeIcon(shapes, ICON_WIDTH, ICON_HEIGHT);

	    final JLabel label = new JLabel(icon);
	    
	    f.setLayout(new FlowLayout());
	     
	    f.add(label);      
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setVisible(true);

	    final int DELAY = 100;
	    
	    Timer t = new Timer(DELAY, new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		for(MoveableShape s : shapes) {
	    			s.translate(10, 0);
	    		}
	            label.repaint(); 
	         }
	       });
	    t.start();  
	}
}
