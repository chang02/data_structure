import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Subway {
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
		}
		catch (IOException e){
			System.exit(1);
		}
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		 
			String line = in.readLine();
			findpath(line.split(" ")[0], line.split(" ")[1]);
		}
		catch(IOException e){
			System.exit(1);
		}
		
	}
	public static void findpath(String nodename1, String nodename2){
		
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
				tempedge1.transfer = true;
				tempedge2.prevnode = tempnode;
				tempedge2.nextnode = station;
				tempedge2.transfer = true;
				
				station.outedge.add(tempedge1);
				station.inedge.add(tempedge2);
				tempnode.outedge.add(tempedge2);
				tempnode.inedge.add(tempedge1);
				
				node.put(tempkey, tempnode);
			}
			node.put(key, station);
			
			
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
		
		node1.outedge.add(edge);
		node2.inedge.add(edge);
		
		node.put(node1key, node1);
		node.put(node2key, node2);
	}
}
