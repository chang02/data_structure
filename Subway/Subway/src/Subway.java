import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Stack;

public class Subway {
	private static final String Iterator = null;
	public static void main(String[] args) {
		HashMap<String, ArrayList<String>> name = new HashMap<String, ArrayList<String>>();
		HashMap<String, Node> node = new HashMap<String, Node>();
		try{
			String filename = args[0];
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line;
			int flag = 0;
			while ((line = in.readLine()) != null) {
			  if(line.compareTo("") == 0 && flag == 0){
				  flag = 1;
				  continue;
			  }
			  if(flag == 0){
				  makenode(node, name, line);
			  }
			  else if(flag == 1){
				  makeedge(node, name, line);
			  }
			}
			in.close();
			ArrayList<String> t = name.get("Á¾·Î3°¡");
			for(int i=0;i<t.size();i++) {
				System.out.println(t.get(i));
			}
		}
		catch (IOException e){
			System.exit(1);
		}
		try{
			while(true) {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		 
				String line = in.readLine();
				if(line.compareTo("QUIT") == 0)
					break;
				if(line.split(" ").length == 2)
					findpath(node, name, line.split(" ")[0], line.split(" ")[1], 0);
				else if(line.split(" ").length == 3 && line.split(" ")[2].compareTo("!") == 0)
					findpath(node, name, line.split(" ")[0], line.split(" ")[1], 1);
			}
		}
		catch(IOException e){
			System.exit(1);
		}
		
	}
	
	public static void findpath(HashMap<String, Node> node, HashMap<String, ArrayList<String>> name, String nodename1, String nodename2, int v){
		ArrayList<String> keys1 = name.get(nodename1);
		ArrayList<String> keys2 = name.get(nodename2);
		initialize_all_nodes(node);
		ArrayList<Node> temp_result1 = dijkstra(node.get(keys1.get(0)), node.get(keys2.get(0)), v);
		long temp_degree = temp_result1.get(0).degree;
		for(int i=0;i<keys1.size();i++) {
			for(int j=0;j<keys2.size();j++) {
				if(i==0 && j==0)
					continue;
				initialize_all_nodes(node);
				Node start = node.get(keys1.get(i));
				Node end = node.get(keys2.get(j));
				ArrayList<Node> temp_result2 = new ArrayList<Node>();
				temp_result2 = dijkstra(start, end, v);
				if(temp_degree > temp_result2.get(0).degree) {
					temp_result1 = temp_result2;
					temp_degree = temp_result1.get(0).degree;
				}
			}
		}
		Node temp = temp_result1.get(temp_result1.size()-1);
		for(int i=temp_result1.size()-2;i>=0;i--) {
			if(temp_result1.get(i).name.compareTo(temp.name) == 0) {
				if(temp_result1.get(i).line.compareTo(temp.line) == 0) {
					System.out.print(temp.name + " ");
				}
				else {
					System.out.print("[" + temp.name + "] ");
				}
				i--;
			}
			else {
				System.out.print(temp.name + " ");
			}
			temp = temp_result1.get(i);
		}
		System.out.println(temp.name);
		System.out.println(temp_degree);
	}
	public static ArrayList<Node> compare(ArrayList<Node> a, ArrayList<Node> b){
		if(a.get(0).degree < b.get(0).degree)
			return a;
		else
			return b;
	}
	public static ArrayList<Node> dijkstra(Node node, Node end, int v) {
		node.finished = true;
		node.degree = 0;
		Node curr = node;
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		while(curr != end) {
			for(Edge edge: curr.outedge) {
				if(edge.nextnode.finished == false) {
					if(v == 0) {
						if(curr.degree + edge.weight < edge.nextnode.degree) {
							edge.nextnode.degree = curr.degree + edge.weight;
							edge.nextnode.from = curr;
						}
						q.offer(edge.nextnode);
					}
					else {
						if(curr.degree + edge.weight2 < edge.nextnode.degree) {
							edge.nextnode.degree = curr.degree + edge.weight2;
							edge.nextnode.from = curr;
						}
						q.offer(edge.nextnode);
					}
				}
			}
			curr = q.poll();
			curr.finished = true;
		}
		ArrayList<Node> a = new ArrayList<Node>();
		while(curr != null){
			a.add(curr);
			curr = curr.from;
		}
		return a;
	}
	public static void initialize_all_nodes(HashMap<String, Node> node) {
		Iterator iterator = node.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry a = (Map.Entry) iterator.next();
			String tmpkey = (String) a.getKey();
			Node tmpnode = (Node) a.getValue();
			tmpnode.initialize();
			node.put(tmpkey, tmpnode);
		}
	}
	public static void makenode(HashMap<String, Node> node, HashMap<String, ArrayList<String>> name, String line){
		String[] line_arr = line.split(" ");
		String key = line_arr[0];
		String stationname = line_arr[1];
		String linenumber = line_arr[2];
		if(name.containsKey(stationname)){ //transfer
			ArrayList<String> l = name.get(stationname);
			
			Node station = new Node(stationname, linenumber);
			for(int i=0;i<l.size();i++){
				String tempkey = l.get(i);
				Node tempnode = node.get(tempkey);
				Edge tempedge1 = new Edge(null);
				Edge tempedge2 = new Edge(null);
				
				tempedge1.prevnode = station;
				tempedge1.nextnode = tempnode;
				tempedge1.weight = 5;
				tempedge1.weight2 = (long)Integer.MAX_VALUE * 1000000;
				tempedge2.prevnode = tempnode;
				tempedge2.nextnode = station;
				tempedge2.weight = 5;
				tempedge2.weight2 = (long)Integer.MAX_VALUE * 1000000;
				
				station.outedge.add(tempedge1);
				station.inedge.add(tempedge2);
				tempnode.outedge.add(tempedge2);
				tempnode.inedge.add(tempedge1);
				
				node.put(tempkey, tempnode);
			}
			node.put(key, station);
			l.add(key);
			name.put(stationname, l);
		}
		else{
			Node station = new Node(stationname, linenumber);
			node.put(key, station);
			ArrayList<String> l = new ArrayList<String>();
			l.add(key);
			name.put(stationname, l);
		}
	}
	public static void makeedge(HashMap<String, Node> node, HashMap<String, ArrayList<String>> name, String line){
		String[] line_arr = line.split(" ");
		String node1key = line_arr[0];
		String node2key = line_arr[1];
		int weight = Integer.parseInt(line_arr[2]);
		Node node1 = node.get(node1key);
		Node node2 = node.get(node2key);
		Edge edge = new Edge(node1.line);
		
		edge.prevnode = node1;
		edge.nextnode = node2;
		edge.weight = weight;
		edge.weight2 = weight;
		
		node1.outedge.add(edge);
		node2.inedge.add(edge);
		
		node.put(node1key, node1);
		node.put(node2key, node2);
	}
}
