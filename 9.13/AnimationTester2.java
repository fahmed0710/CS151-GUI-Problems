/*
 * Modified animation program from Chapter 4 so that 
 * various cars are moving at different speeds.
 * Separate threads are used for each car.
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.awt.FlowLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class AnimationTester2 {
	private static final int ICON_WIDTH = 1000, ICON_HEIGHT = 500, CAR_WIDTH = 100;
	
	/*
	 * Static factory method to create a runnable to move a shape
	 */
	public static Runnable createMover(MoveableShape shape, JLabel label, int moveAmt, int delay) {
		return() -> {
			while(true) {
				shape.translate(moveAmt, 0);
				label.repaint();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(300,500);
		
		final MoveableShape car1 = new CarShape(0,0,CAR_WIDTH);
		final MoveableShape car2 = new CarShape(100,100,CAR_WIDTH);
		final MoveableShape car3 = new CarShape(200,300,CAR_WIDTH);
		
		final ShapeIcon icon1 = new ShapeIcon(car1, ICON_WIDTH, ICON_HEIGHT);
		final ShapeIcon icon2 = new ShapeIcon(car2, ICON_WIDTH, ICON_HEIGHT);
		final ShapeIcon icon3 = new ShapeIcon(car3, ICON_WIDTH, ICON_HEIGHT);

		final JLabel label1 = new JLabel(icon1);
		final JLabel label2 = new JLabel(icon2);
		final JLabel label3 = new JLabel(icon3);
		
		f.setLayout(new FlowLayout());
		f.add(label1);
		f.add(label2);
		f.add(label3);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		
		final int DELAY = 100;
		
		Runnable r1 = AnimationTester2.createMover(car1, label1, 1, DELAY);
		Runnable r2 = AnimationTester2.createMover(car2, label2, 1, DELAY);
		Runnable r3 = AnimationTester2.createMover(car3, label3, 1, DELAY);
		
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(r1);
		service.execute(r2);
		service.execute(r3);
		service.shutdown();
	}
}
