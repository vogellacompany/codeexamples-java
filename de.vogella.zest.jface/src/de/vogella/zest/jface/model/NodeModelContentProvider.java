package de.vogella.zest.jface.model;

import java.util.ArrayList;
import java.util.List;

public class NodeModelContentProvider {
	private List<MyConnection> connections;
	private List<MyNode> nodes;

	public NodeModelContentProvider() {
		// Image here a fancy DB access
		// Now create a few nodes
		nodes = new ArrayList<MyNode>();
		MyNode node = new MyNode("1", "Hamburg");
		nodes.add(node);
		node = new MyNode("2", "Frankfurt");
		nodes.add(node);
		node = new MyNode("3", "Berlin");
		nodes.add(node);
		node = new MyNode("4", "Munich");
		nodes.add(node);
		node = new MyNode("5", "Eppelheim");
		nodes.add(node);
		node = new MyNode("6", "Ahrensboek");
		nodes.add(node);

		connections = new ArrayList<MyConnection>();
		MyConnection connect = new MyConnection("1", "1", nodes.get(0),
				nodes.get(1));
		connections.add(connect);
		connect = new MyConnection("2", "2", nodes.get(0), nodes.get(4));
		connections.add(connect);
		connect = new MyConnection("3", "3", nodes.get(2), nodes.get(1));
		connections.add(connect);
		connect = new MyConnection("4", "3", nodes.get(1), nodes.get(3));
		connections.add(connect);

		// Because we are lasy we save the info about the connections in the
		// nodes

		for (MyConnection connection : connections) {
			connection.getSource().getConnectedTo()
					.add(connection.getDestination());
		}
	}

	public List<MyNode> getNodes() {
		return nodes;
	}
}
