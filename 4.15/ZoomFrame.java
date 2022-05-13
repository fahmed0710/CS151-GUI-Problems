/*
 * Allows user to change the size of a car icon by pressing a button
 *
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ZoomFrame extends JFrame{
	private static CarIcon car;
	private static JLabel label;
	
	/*
	 * Constructs a new zoom frame object
	 */
	public ZoomFrame() {
		setSize(300,200);
		
		JButton zoomIn = new JButton("Zoom In");
		JButton zoomOut = new JButton("Zoom Out");
		
		zoomIn.addActionListener(createZoomButtonListener(1.1));
		zoomOut.addActionListener(createZoomButtonListener(0.9));
		
		car = new CarIcon(100);
	    label = new JLabel(car);
		 
		setLayout(new BorderLayout());
		 
		add(label, BorderLayout.CENTER);
		add(zoomIn, BorderLayout.NORTH);
		add(zoomOut, BorderLayout.SOUTH);
	}
	
	/*
	 * Creates an action listener for a button that changes the width of an icon
	 * @param factor the factor to multiply to find the new width
	 */
	public static ActionListener createZoomButtonListener(final double factor) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int newWidth = (int)(factor * car.getIconWidth());
				if(newWidth < 10)
					newWidth = 10;
				car.setIconWidth(newWidth);
				label.repaint();
			}
		};
	}
}
