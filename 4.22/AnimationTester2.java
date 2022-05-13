import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class AnimationTester2 {
	private static final int ICON_WIDTH = 500, ICON_HEIGHT = 100, CAR_WIDTH = 100;
	
	public static void main(String[] args){
		JFrame f = new JFrame();

	    MoveableShape shape =  new CarShape(0, 0, CAR_WIDTH);
	    
	    ShapeIcon icon = new ShapeIcon(shape, ICON_WIDTH, ICON_HEIGHT);

	    final JLabel label = new JLabel(icon);
	    
	    f.setLayout(new FlowLayout());
	     
	    f.add(label);      
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.pack();
	    f.setVisible(true);

	    final int DELAY = 100;
	    
	    Timer t = new Timer(DELAY, new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		if(((CarShape)shape).x == f.getBounds().width) {
	    			((CarShape)shape).x = 0;
	    			((CarShape)shape).y = 0;
	    		}
	    		shape.translate(10, 0);
	            label.repaint(); 
	         }
	       });
	    t.start();  
	}
}
