package bfs_dfs;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the number of vertices for graph (order of graph): ");
		int v = input.nextInt();
		System.out.println("Enter the number of edges for graph (size of graph): ");
		int e = input.nextInt();
		Graph g = new Graph(v, e);
		Graph g2 = g;
		Graph g3 = g2;
		System.out.println(g);
		System.out.println("Part A of programming assignment.\nPlease enter a starting vertex for BFS and DFS:");
		int startVertex = input.nextInt();
		System.out.println("***********************************************");
		System.out.println("BFS ran on graph: \nVertices reachable from vertex " + startVertex + "\n");
		g.BFS(g.getNode(startVertex));
		System.out.println("\nReached all possible vertices from vertex " + startVertex + ".\n");
		for (Node t : g.getNodelist()) {
			t.setParent(null);
		}
		System.out.println("***********************************************");
		System.out.println("\nDFS_visit ran on graph: ");
		g2.DFS(g2.getNode(startVertex), g2.getNodelist());
		System.out.println("\nTimes for each vertex after running DFS\nStarting from vertex " + startVertex + "\n");
		System.out.println("Value:\tStart time:\tEnd time:");
		for (Node x : g2.getNodelist()) {
			System.out.println(x.getValue() + "\t" + x.start_time + "\t\t" + x.end_time);
		}
		g3.resetTimer();
		for (Node t : g3.getNodelist()) {
			t.setParent(null);
			t.start_time = null;
			t.end_time = null;
		}
		System.out.println("\n***********************************************");
		System.out.println("Part B of programming assignment.");
		System.out.println("Possibility of topological sorting being determined");
		g3.DAGdfs(g3.getNode(startVertex), g3.getNodelist());
	}
}
