/*
 * A class that tests the MVC model
 * 
 * @author Fariha Ahmed
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MVCTester {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		Model m = new Model();
		JTextArea textArea = new JTextArea(20,40);
		JTextField textField = new JTextField();
		
		// View
		m.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				textArea.setText(m.getText());
			}
		});
		
		// Controller
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = textField.getText();
				m.addText(s);
			}
		});
		
		f.setLayout(new BorderLayout());
		
		f.add(addButton, BorderLayout.NORTH);
		f.add(textArea, BorderLayout.CENTER);
		f.add(textField, BorderLayout.SOUTH);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
	    f.setVisible(true);
	}
}
