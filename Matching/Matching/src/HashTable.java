import java.util.ArrayList;
public class HashTable {
	static AVLTree[] table;
	
	public HashTable() {
		table = new AVLTree[100];
	}
	public static void insert(String s, Pair p) {
		char arr[] = s.toCharArray();
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum = sum + (int)arr[i];
		}
		int index = sum % 100;
		if(table[index] == null) {
			 AVLTree t = new AVLTree();
			 t.insert(t.root, s, p);
			 table[index] = t;
		}
		else {
			table[index].insert(table[index].root, s, p);
		}
	}
}
