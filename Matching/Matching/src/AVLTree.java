import java.util.ArrayList;

public class AVLTree {
	TreeNode root;
	
	public AVLTree(){
		this.root = null;
	}
	public ArrayList<Pair> search(String key){
		ArrayList<Pair> l = new ArrayList<Pair>();
		TreeNode curr = this.root;
		while(curr != null) {
			if(curr.getKey().compareTo(key) == 0)
				break;
			else if(curr.getKey().compareTo(key) < 0)
				curr = curr.getRight();
			else
				curr = curr.getLeft();
		}
		if(curr == null){
			return l;
		}
		for(int i=0;i<curr.getList().size();i++) {
			l.add(curr.getList().get(i));
		}
		return l;
	}
	public TreeNode checkbalance(TreeNode node, String key) {
		if(node.getHeightDiff() > 1) {
			if(key.compareTo(node.getLeft().getKey()) < 0) {
				node = this.rrotate(node);
			}
			else if(key.compareTo(node.getLeft().getKey()) > 0) {
				node.setLeft(this.lrotate(node.getLeft()));
				node = this.rrotate(node);
			}
		}
		else if(node.getHeightDiff() < -1) {
			if(key.compareTo(node.getRight().getKey()) > 0) {
				node = this.lrotate(node);
			}
			else if(key.compareTo(node.getRight().getKey()) < 0) {
				node.setRight(this.rrotate(node.getRight()));
				node = this.lrotate(node);
			}
		}
		return node;
	}
	public TreeNode insert(TreeNode node, String key, Pair p) {
		if(node == null) {
			TreeNode newNode = new TreeNode(key, p);
			return newNode;
		}
		else if(key.compareTo(node.getKey()) == 0) {
			node.getList().add(p);
			return node;
		}
		else if(key.compareTo(node.getKey()) > 0) {
			node.setRight(this.insert(node.getRight(), key, p));
		}
		else if(key.compareTo(node.getKey()) < 0) {
			node.setLeft(this.insert(node.getLeft(), key, p));
		}
		node = this.checkbalance(node, key);
		
		return node;
	}
	public TreeNode rrotate(TreeNode node) {
		TreeNode a = node.getLeft();
		TreeNode b = a.getRight();
		
		a.setRight(node);
		node.setLeft(b);
			
		return a;
	}
	public TreeNode lrotate(TreeNode node) {		
		TreeNode a = node.getRight();
		TreeNode b = a.getLeft();
		
		a.setLeft(node);
		node.setRight(b);
		
		return a;
	}
	public ArrayList<String> getKeys(ArrayList<String> keys, TreeNode node) {
		if(node == null) {
			return keys;
		}
		keys.add(node.getKey());
		this.getKeys(keys, node.getLeft());
		this.getKeys(keys, node.getRight());
		return keys;
	}
}
