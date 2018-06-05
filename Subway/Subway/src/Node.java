import java.util.LinkedList;
public class Node implements Comparable<Node> {
	String name;
	String line;
	LinkedList<Edge> outedge;
	LinkedList<Edge> inedge;
	
	boolean finished;
	long degree;
	Node from;
	
	public Node(String name, String line){
		this.name = name;
		this.line = line;
		outedge = new LinkedList<Edge>();
		inedge = new LinkedList<Edge>();
		
		finished = false;
		degree = (long)Integer.MAX_VALUE * (long)Integer.MAX_VALUE;
		from = null;
	}
	public void initialize() {
		finished = false;
		degree = (long)Integer.MAX_VALUE * (long)Integer.MAX_VALUE;
		from = null;
	}
	
	@Override
	public int compareTo(Node target) {
		if(this.degree > target.degree) {
			return 1;
		}
		else if (this.degree < target.degree) {
			return -1;
		}
		else
			return 0;
	}
}
