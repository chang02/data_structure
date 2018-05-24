public class AVLTree {
	private TreeNode root;
	
	public AVLTree(){
		this.root = null;
	}
	public void checkbalance(TreeNode node, String key) {
		if(node.getHeightDiff() > 1) {
			if(key.compareTo(node.getLeft().getKey()) < 0) {
				node = this.rrotate(node);
			}
			else if(key.compareTo(node.getLeft().getKey()) > 0) {
				node = this.lrotate(node.getLeft());
				node = this.rrotate(node);
			}
		}
		else if(node.getHeightDiff() < -1) {
			if(key.compareTo(node.getLeft().getKey()) > 0) {
				node = this.lrotate(node);
			}
			if(key.compareTo(node.getLeft().getKey()) < 0) {
				node = this.rrotate(node.getRight());
				node = this.lrotate(node);
			}
		}
		else {
			return;
		}
	}
	public void insert(TreeNode node, String key, Pair p) {
		if(node == null) {
			TreeNode newNode = new TreeNode(key, p);
			node = newNode;
			return;
		}
		else if(key.compareTo(node.getKey()) == 0) {
			node.getList().add(p);
			return;
		}
		else if(key.compareTo(node.getKey()) > 0) {
			this.insert(node.getRight(), key, p);
		}
		else if(key.compareTo(node.getKey()) < 0) {
			this.insert(node.getLeft(), key, p);
		}
		this.checkbalance(node, key);
	}
	public TreeNode rrotate(TreeNode node) {
		boolean isroot;
		if(node == this.root)
			isroot = true;
		else
			isroot = false;
		
		TreeNode a = node.getLeft();
		TreeNode b = a.getRight();
		
		a.setRight(node);
		node.setLeft(b);
		
		if(isroot)
			this.root = a; 
		
		return a;
	}
	public TreeNode lrotate(TreeNode node) {
		boolean isroot;
		if(node == this.root)
			isroot = true;
		else
			isroot = false;
		
		TreeNode a = node.getRight();
		TreeNode b = a.getLeft();
		
		a.setLeft(node);
		node.setRight(b);
		
		if(isroot)
			this.root = a;
		
		return a;
	}
}
