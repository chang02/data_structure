import java.util.ArrayList;
public class HashTable {
	AVLTree[] table;

	public HashTable() {
		this.table = new AVLTree[100];
	}
	public void insert(String s, Pair p) {
		char arr[] = s.toCharArray();
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum = sum + (int)arr[i];
		}
		int index = sum % 100;
		if(this.table[index] == null) {
			 AVLTree t = new AVLTree();
			 t.root = t.insert(t.root, s, p);
			 this.table[index] = t;
		}
		else {
			this.table[index].root = this.table[index].insert(this.table[index].root, s, p);
		}
	}
	public void print_index(int index) {
		if(this.table[index] == null) {
			System.out.println("EMPTY");
		}
		else {
			ArrayList<String> keys = new ArrayList<String>();
			keys = this.table[index].getKeys(keys, this.table[index].root);
			for(int i=0;i<keys.size()-1;i++){
				System.out.print(keys.get(i) + " ");
			}
			System.out.println(keys.get(keys.size()-1));
		}
	}
	public void search(String s) {
		ArrayList<Pair> result_l = new ArrayList<Pair>();
		for(int i=0;i<s.length()-6+1;i++) {
			String temp_str = s.substring(i,i+6);
			char[] temp_arr = temp_str.toCharArray();
			int sum = 0;
			for(int j=0;j<temp_arr.length;j++) {
				sum = sum + (int)temp_arr[j];
			}
			int index = sum % 100;
			if(this.table[index] == null){
				System.out.println("(0, 0)");
				return;
			}
			ArrayList<Pair> l = new ArrayList<Pair>();
			l = this.table[index].search(temp_str);
			if(i == 0) {
				result_l.addAll(l);
			}
			else {
				result_l = this.compare(result_l, l, i);
			}
		}
		if(result_l.size() == 0){
			System.out.println("(0, 0)");
			return;
		}
		for(int i=0;i<result_l.size()-1;i++){
			System.out.print("(" + result_l.get(i).x + ", " + result_l.get(i).y + ") ");
		}
		System.out.println("(" + result_l.get(result_l.size()-1).x + ", " + result_l.get(result_l.size()-1).y + ")");
	}
	public ArrayList<Pair> compare(ArrayList<Pair> l1, ArrayList<Pair> l2, int dif){
		ArrayList<Pair> result_l = new ArrayList<Pair>();
		for(int i=0;i<l1.size();i++) {
			for(int j=0;j<l2.size();j++){
				if(l1.get(i).x == l2.get(j).x && l1.get(i).y + dif == l2.get(j).y){
					result_l.add(l1.get(i));
					break;
				}
			}
		}
		return result_l;
	}
}
