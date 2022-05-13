/*
 * Allows user to change the size of a car icon by sliding values
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class SliderTester {
	private static JSlider slider;
	private static JLabel label;
	private static CarIcon car;
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(300,300);
		
		f.setLayout(new BorderLayout());
		
		slider = new JSlider(0,300);
		slider.addChangeListener(createSliderListener());
		
		car = new CarIcon(200);
		label = new JLabel(car);
		
		f.add(slider, BorderLayout.NORTH);
		f.add(label, BorderLayout.CENTER);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}
	
	public static ChangeListener createSliderListener() {
		return new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				car.setIconWidth(slider.getValue());
				label.repaint();
			}
		};
	}
}
