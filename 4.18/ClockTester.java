/*
 * Allows user to view a ticking clock
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.GregorianCalendar;

public class ClockTester {
	private static ClockIcon clock;
	private static JLabel label;
	private static JTextArea currentTime;
	private static GregorianCalendar now;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		f.setLayout(new BorderLayout());
		
		now  = new GregorianCalendar();
		clock = new ClockIcon(500, now);
		label = new JLabel(clock);
		
		int h = now.get(GregorianCalendar.HOUR);
		int m = now.get(GregorianCalendar.MINUTE);
		int s = now.get(GregorianCalendar.SECOND);
		currentTime = new JTextArea(h + ":" + m + ":" + s);
		
		f.add(label, BorderLayout.CENTER);
		f.add(currentTime, BorderLayout.SOUTH);
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				now  = new GregorianCalendar();
				clock.setCurrentTime(now);;
				label.repaint();
				
				int h = now.get(GregorianCalendar.HOUR);
				int m = now.get(GregorianCalendar.MINUTE);
				int s = now.get(GregorianCalendar.SECOND);
				currentTime.setText(h + ":" + m + ":" + s);
			}
		};
		
		Timer t = new Timer(1000,listener);
		t.start();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
	    f.setVisible(true);
	}
}
