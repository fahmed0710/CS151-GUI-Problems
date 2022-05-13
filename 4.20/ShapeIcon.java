// An icon that contains a moveable shape
// Borrowed from textbook
 
import java.awt.*;
import javax.swing.*;

public class ShapeIcon implements Icon {
	private int width, height;
	private MoveableShape[] shapes;
	
	public ShapeIcon(MoveableShape[] shapes, int width, int height) {
		this.shapes = shapes;
		this.width = width;
		this.height = height;
	}
	
	public int getIconWidth() {
		return width;
	}
	
	public int getIconHeight() {
		return height;
	}
	
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D)g;
		for(MoveableShape s : shapes) {
			s.draw(g2);
		}
	}
}

