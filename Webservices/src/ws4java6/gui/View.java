package ws4java6.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class View extends JFrame {
	private JTable mytable;

	public View() {
		mytable = new JTable();
		JScrollPane scrollPane = new JScrollPane(mytable);
		mytable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		getContentPane().add(scrollPane);
		pack();
		setSize(800, 640);
	}

	public void setModel(MyModel model) {
		mytable.setModel(model);
	}
}
