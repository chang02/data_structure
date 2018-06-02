
public class Edge {
	Node prevnode;
	Node nextnode;
	String line;
	boolean transfer;
	int weight;
	public Edge(String line) {
		this.line = line;
		this.transfer = false;
		this.weight = 0;
		prevnode = new Node(null, null);
		nextnode = new Node(null, null);
	}
}
