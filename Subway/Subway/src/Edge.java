
public class Edge {
	Node prevnode;
	Node nextnode;
	String line;
	long weight;
	long weight2;
	public Edge(String line) {
		this.line = line;
		this.weight = 0;
		this.weight2 = 0;
		prevnode = new Node(null, null);
		nextnode = new Node(null, null);
	}
}
