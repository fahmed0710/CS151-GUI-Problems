// A class that implements an Observer object that displays a bar chart view of a data model.
// Modified from textbook 

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class BarFrame extends JFrame implements ChangeListener{
	private double max;
	private ArrayList<Double> a;
	private DataModel dataModel;
	private static final int ICON_WIDTH = 200, ICON_HEIGHT = 200;
	
	/*
	 * Constructs a BarFrame object
	 * @param dataModel the data that is displayed in the bar chart
	 */
	public BarFrame(DataModel dataModel) {
		this.dataModel = dataModel;
		a = dataModel.getData();
		
		setLocation(0,200);
		setLayout(new BorderLayout());
		
		Icon barIcon = new Icon() {
			public int getIconWidth() { return ICON_WIDTH; }
			
			public int getIconHeight() { return ICON_HEIGHT; }
			
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D)g;
				
				g2.setColor(Color.RED);
				
				max = (a.get(0)).doubleValue();
				for(Double v : a) {
					double val = v.doubleValue();
					if(val > max)
						max = val;
				}
				
				double barHeight = getIconHeight() / a.size();
				
				int i = 0;
				for(Double v : a) {
					double value = v.doubleValue();
					double barLength = getIconWidth() * value / max;
					
					Rectangle2D.Double rectangle = new Rectangle2D.Double(0, barHeight * i,barLength, barHeight);
					i++;
					g2.fill(rectangle);
				}
			}
		};
		
		MouseListener m = new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				int barPressed = a.size() * e.getY() / getHeight();
				dataModel.update(barPressed, max * e.getX() / getWidth());
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
		};
		
		JLabel label = new JLabel(barIcon);
		add(label);
		label.addMouseListener(m);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	/*
	 * Called when the data in the model is changed
	 * @param e the event representing the change
	 */
	public void stateChanged(ChangeEvent e) {
		a = dataModel.getData();
		repaint();
	}
}