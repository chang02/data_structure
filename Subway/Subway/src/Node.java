import java.util.LinkedList;
public class Node {
	String name;
	String line;
	LinkedList<Edge> outedge;
	LinkedList<Edge> inedge;
	
	public Node(String name, String line){
		this.name = name;
		this.line = line;
		outedge = new LinkedList<Edge>();
		inedge = new LinkedList<Edge>();
		
	}
}
