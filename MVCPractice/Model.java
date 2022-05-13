/*
 * A class that models the model portion of a MVC model
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class Model {
	private ArrayList<ChangeListener> listeners;
	private ArrayList<String> text;
	
	/*
	 * Constructs a new model object
	 */
	public Model() {
		listeners = new ArrayList<ChangeListener>();
		text = new ArrayList<String>();
	}
	
	/*
	 * Adds a change listener to the model object
	 * 
	 * @param listener the change listener to add
	 */
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
	
	/*
	 * Returns textInField in a formatted string
	 * 
	 * @return all the text the user has so far
	 */
	public String getText() {
		String t = "";
		for(String s: text) {
			t += s + "\n";
		}
		return t;
	}

	/*
	 * Adds a new string to the text
	 * 
	 * @param str the string to add
	 */
	public void addText(String s) {
		text.add(s);
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener l : listeners) {
			l.stateChanged(e);
		}
	}
}
