// A class for testing an implementation of the Observer pattern
// Borrowed from textbook

import java.util.ArrayList;

public class ObserverTester {
	/*
	 * Creates a DataModel and attaches barchart and textfield listeners
	 * @param args unused
	 */
	public static void main(String[] args) {
		ArrayList<Double> data = new ArrayList<Double>();
		
		data.add(new Double(33.0));
		data.add(new Double(44.0));
		data.add(new Double(22.0));
		data.add(new Double(22.0));
		
		DataModel model = new DataModel(data);
		
		BarFrame barFrame = new BarFrame(model);
		TextFrame frame = new TextFrame(model);
	
		model.attach(barFrame);
		model.attach(frame);
	}
}
