/*
 * A class that defines a clock icon 
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockIcon implements Icon{
	private int width;
	private GregorianCalendar currentTime;
	
	/*
	 * Constructs a clock of a given width
	 * @param w the clock's width
	 * @param c the calendar to set the clock to
	 */
	public ClockIcon(int w, GregorianCalendar c) {
		width = w;
		currentTime = c;
	}
	
	/*
	 * Changes the width of the clock
	 * @param w the clock's new width
	 */
	public void setIconWidth(int w) {
		width = w;
	}
	
	/*
	 * Changes the current time of the calendar
	 * @param c the clock's new current time 
	 */
	public void setCurrentTime(GregorianCalendar c) {
		currentTime = c;
	}
	
	
	public int getIconWidth() {
		return width;
	}
	
	public int getIconHeight() {
		return width;
	}
	
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		
		int radius = width / 2;
		Point2D.Double center = new Point2D.Double(x + width / 2, y + width / 2);
		
		Ellipse2D.Double face = new Ellipse2D.Double(x, y, width, width);
		g2.setColor(Color.WHITE);
		g2.fill(face);
		
		g2.setColor(Color.BLACK);
		
		// Get the current seconds, minutes, and hours
		int seconds = currentTime.get(GregorianCalendar.SECOND);
		int minutes = currentTime.get(GregorianCalendar.MINUTE);
		int hours = currentTime.get(Calendar.HOUR);
	    hours = hours % 12; 
	    hours = hours * 5; 
	    hours = hours + (int) (5 * minutes / 60.0);  
		
		// Draw the hour hand
		double hourLength = 0.55 * radius;
		double hourAngle = Math.PI * 6 * hours / 180;
		double hourX = center.x + hourLength * Math.sin(hourAngle);
		double hourY = center.y - hourLength * Math.cos(hourAngle);
		Point2D.Double hourEnd = new Point2D.Double(hourX, hourY);
		Line2D.Double hourHand = new Line2D.Double(center, hourEnd);
		g2.draw(hourHand);
		
		// Draw the minute hand
		double minuteLength = 0.65 * radius;
		double minuteAngle = Math.PI * 6 * minutes / 180;
		double minuteX = center.x + minuteLength * Math.sin(minuteAngle);
		double minuteY = center.y - minuteLength * Math.cos(minuteAngle);
		Point2D.Double minuteEnd = new Point2D.Double(minuteX, minuteY);
		Line2D.Double minuteHand = new Line2D.Double(center, minuteEnd);
		g2.draw(minuteHand);
		
		// Draw the second hand
		double secondLength = 0.75 * radius;
		double secondAngle = Math.PI * 6 * seconds / 180;
		double secondX = center.x - secondLength * Math.cos(secondAngle);
		double secondY = center.y + secondLength * Math.sin(secondAngle);
		Point2D.Double secondEnd = new Point2D.Double(secondY, secondX);
		Line2D.Double secondHand = new Line2D.Double(center,secondEnd);
		g2.setColor(Color.RED);
		g2.draw(secondHand);
	}
}
