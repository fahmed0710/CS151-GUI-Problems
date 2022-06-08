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

public class AnimationTester1 {	
private static final int ICON_WIDTH = 400, ICON_HEIGHT = 100, CAR_WIDTH = 100;
	
	public static Runnable mover(MoveableShape s, JLabel l, int moveAmt, int delay) {
		return() -> {
			while(true) {
				s.translate(moveAmt, 0);
				l.repaint();
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame();

		final MoveableShape shape1 = new CarShape(0, 0, CAR_WIDTH);
		final MoveableShape shape2 = new CarShape(0, 25, CAR_WIDTH);
		final MoveableShape shape3 = new CarShape(0, 50, CAR_WIDTH);

		ShapeIcon icon1 = new ShapeIcon(shape1, ICON_WIDTH, ICON_HEIGHT);
		ShapeIcon icon2 = new ShapeIcon(shape2, ICON_WIDTH, ICON_HEIGHT);
		ShapeIcon icon3 = new ShapeIcon(shape3, ICON_WIDTH, ICON_HEIGHT);

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

//		Thread t1 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					shape1.translate(10,0);
//					f.repaint();
//					try {
//						Thread.sleep(150);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		
//		Thread t2 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					shape2.translate(10,0);
//					f.repaint();
//					try {
//						Thread.sleep(20);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//
//		Thread t3 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					shape3.translate(10,0);
//					f.repaint();
//					try {
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
//		
//		t1.start();// start the animation
//		t2.start();
//		t3.start();
		
		Runnable r1 = AnimationTester1.mover(shape1, label1, 10, 150);
		Runnable r2 = AnimationTester1.mover(shape2, label2, 10, 50);
		Runnable r3 = AnimationTester1.mover(shape3, label3, 10, 20);
		
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(r1);
		service.execute(r2);
		service.execute(r3);
		
		service.shutdown();
	   }
	}