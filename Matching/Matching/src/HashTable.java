import java.util.ArrayList;
public class HashTable {
	AVLTree[] table = new AVLTree[100];
	
	public HashTable() {
		return;
	}
	public void insert(String s, Pair p) {
		char arr[] = s.toCharArray();
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum = sum + (int)arr[i];
		}
		int index = sum % 100;
		System.out.println(index);
		if(table[index] == null) {
			 AVLTree t = new AVLTree();
			 t.root = t.insert(t.root, s, p);
			 table[index] = t;
		}
		else {
			table[index].root = table[index].insert(table[index].root, s, p);
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
}
