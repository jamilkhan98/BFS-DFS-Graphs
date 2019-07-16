package bfs_dfs;

import java.util.*;

public class Graph {
	private int time = 0;
	private int V;
	private int E;
	private LinkedList<Node>[] adjacency;
	static Node[] nodeArray;
	boolean cycle;
	private Stack<Node> stack = new Stack<Node>();

	/**
	 * overloaded constructor for graph given size and order
	 * @param vertices - order of graph (number of vertices in graph)
	 * @param edges - size of graph (number of edges in graph)
	 */
	Graph(int vertices, int edges) {
		V = vertices;
		E = edges;
		adjacency = new LinkedList[V];
		nodeArray = new Node[V];

		// creates an adjacency list and a node list
		// that is equivalent to order of the graph
		for (int i = 0; i < V; i++) {
			adjacency[i] = new LinkedList();
			nodeArray[i] = new Node(i);
		}

		// randomly creates directed edges for nodes in the graph
		// iterates until equivalent to size of the graph
		for (int x = 0; x < E; x++) {
			// process of choosing two random nodes
			// with which to create edges between
			int firstNode = (int) (Math.random() * V);
			int secondNode = (int) (Math.random() * V);
			Node testNode = nodeArray[secondNode];
			// ensures that vertices do not have duplicate edges
			while (adjacency[firstNode].contains(testNode) || firstNode == secondNode) {
				if (adjacency[firstNode].contains(testNode)) {
					secondNode = (int) (Math.random() * V);
					testNode = nodeArray[secondNode];
				}
				firstNode = (int) (Math.random() * V);
			}
			// creates directed edge between a and testNode
			// and records it in adjacency array
			addEdge(adjacency, firstNode, testNode);
		}

	}

	/**
	 * adds edge between vertex and another node
	 * @param adjacency - LinkedList adjacency matrix of nodes in graph
	 * @param vertex - vertex in matrix to add edge on
	 * @param node - node added to adjacency matrix of a vertex
	 */
	public void addEdge(LinkedList<Node>[] adjacency, int vertex, Node node) {
		adjacency[vertex].add(node);
	}

	/**
	 * retrieves adjacency matrix of graph
	 * @return - adjacency matrix of graph
	 */
	public LinkedList<Node>[] getAdjacentList() {
		return adjacency;
	}

	/**
	 * retrieves value of node
	 * @param nodeValue - node of value to retrieve
	 * @return - value of node
	 */
	public Node getNode(int nodeValue) {
		return nodeArray[nodeValue];
	}

	/**
	 * gets list of nodes in graph
	 * @return - returns list of nodes in graph
	 */
	public Node[] getNodelist() {
		return nodeArray;
	}

	/**
	 * resets timer of graph
	 * used when runnning DFS
	 */
	public void resetTimer() {
		time = 0;
	}

	/**
	 * algorithm to find shortest path from a starting vertex
	 * to all reachable vertices within the graph
	 * @param v - starting node from which to traverse graph
	 */
	public void BFS(Node v) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(v);
		v.setDist(0);
		v.setParent(-1);
		while (queue.size() > 0) {
			Node newNode = queue.poll();
			System.out.println("Value: " + newNode.getValue() + " Distance: " + newNode.getDistance());
			for (Node N : adjacency[newNode.getValue()]) {
				if (N.getParent() == null) {
					N.setDist(newNode.getDistance() + 1);
					N.setParent(newNode.getValue());
					queue.add(N);
				}
			}
		}
	}

	/**
	 * algorithm to visit all nodes of a graph
	 * @param node - starting node from which to traverse graph
	 * @param nodes - list of nodes in graph to visit
	 */
	public void DFS(Node node, Node[] nodes) {
		int value = node.getValue();
		for (int i = 0; i < nodes.length; i++, value++) {
			Node newNode = nodes[value % nodes.length];
			System.out.println("\nVertices visited through vertex: " + newNode.getValue());
			if (newNode.getParent() == null) {
				newNode.setParent(newNode.getValue());
				DFSvisit(newNode);
			}
		}
	}

	/**
	 * visits node in graph and sets start and end time of visitation
	 * @param n - node in graph which is being traversed
	 */
	public void DFSvisit(Node n) {
		time += 1;
		n.start_time = time;
		for (Node adj : adjacency[n.getValue()]) {
			if (adj.start_time == null) {
				adj.setParent(n.getValue());
				DFSvisit(adj);
			} else if (adj.start_time != null && adj.end_time == null) {
				cycle = true;
			}
		}
		time += 1;
		n.end_time = time;
		System.out.println("Value: " + n.getValue() + " Start: " + n.start_time + " End: " + n.end_time);
	}

	/**
	 * algorithm to visit nodes in graph to determine if topological sort possible
	 * @param node - node from which to start traversal of graph
	 * @param nodes - list of nodes in graph
	 */
	public void DAGdfs(Node node, Node[] nodes) {
		int value = node.getValue();
		for (int i = 0; i < nodes.length; i++, value++) {
			Node newNode = nodes[value % nodes.length];
			if (newNode.getParent() == null) {
				newNode.setParent(-1);
				stack.push(newNode);
				DAGdfs_visit(newNode);
			}
		}
		if (cycle == true) {
			System.out.println("Cycle detected! Topological order is impossible!");
		} else {
			System.out.println("\nTopological Sorting Possible:\nThe topological order of the graph is: ");
			System.out.println("Value\tStart Time:\tEnd Time");
			while (stack.size() > 0) {
				Node stackNode = stack.pop();
				System.out.println(stackNode.getValue() + "\t" + stackNode.start_time + "\t\t" + stackNode.end_time);
			}
		}
	}

	/**
	 * visits nodes in graph and determines if cycle occurs within graph
	 * @param n - node from which to traverse graph
	 */
	public void DAGdfs_visit(Node n) {
		time += 1;
		n.start_time = time;
		for (Node adj : adjacency[n.getValue()]) {
			if (adj.start_time == null) {
				adj.setParent(n.getValue());
				stack.push(adj);
				DAGdfs_visit(adj);
			} else if (adj.start_time != null && adj.end_time == null) {
				cycle = true;
			}
		}
		time += 1;
		n.end_time = time;
	}

	/**
	 * method to print graph adjacency matrix
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("\nAdjacency matrix of graph created: ");
		s.append("\nNode Value:\tAdjacent Nodes\n");
		for (int v = 0; v < V; v++) {
			s.append(v + "\t\t ");
			for (Node w : adjacency[v]) {
				s.append(w + " ");
			}
			s.append("\n************************************\n");
		}
		return s.toString();
	}
}