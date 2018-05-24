import java.util.ArrayList;
public class HashTable {
	AVLTree[] table;
	
	public HashTable() {
		table = new AVLTree[100];
	}
	public static void insert(String s) {
		char arr[] = s.toCharArray();
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum = sum + (int)arr[i];
		}
		int index = sum % 100;
	}
}
