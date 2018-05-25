public class HashTable {
	private AVLTree[] table;
	
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
			 t.insert(t.root, s, p);
			 this.table[index] = t;
		}
		else {
			this.table[index].insert(this.table[index].root, s, p);
		}
	}
	public void print(int index){
		this.table[index].preprint(this.table[index].root);
	}
}
