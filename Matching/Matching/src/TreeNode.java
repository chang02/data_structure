import java.util.LinkedList;

public class TreeNode {
	private String key;
	private LinkedList<Pair> l;
	private TreeNode right;
	private TreeNode left;
	public TreeNode(String key, Pair p) {
		this.key = key;
		this.l = new LinkedList<Pair>();
		l.add(p);
		this.right = null;
		this.left = null;
	}
	public TreeNode getLeft() {
		return this.left;
	}
	public TreeNode getRight() {
		return this.right;
	}
	public void setLeft(TreeNode l) {
		this.left = l;
	}
	public void setRight(TreeNode r) {
		this.right = r;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		return this.key;
	}
	public LinkedList<Pair> getList(){
		return this.l;
	}
	public int compareTo(TreeNode c) {
		return this.key.compareTo(c.getKey());
	}
	public int getHeight() {
		if(this.left != null && this.right != null) {
			return getMax(this.left.getHeight(), this.right.getHeight()) + 1; 
		}
		else if(this.left == null && this.right == null) {
			return 1;
		}
		else if(this.left == null && this.right != null) {
			return this.right.getHeight() + 1;
		}
		else {
			return this.left.getHeight() + 1;
		}
	}
	public int getHeightDiff() {
		if(this.left != null && this.right != null) {
			return this.left.getHeight() - this.right.getHeight(); 
		}
		else if(this.left == null && this.right == null) {
			return 0;
		}
		else if(this.left == null && this.right != null) {
			return 0 - this.right.getHeight();
		}
		else {
			return this.left.getHeight();
		}
	}
	public int getMax(int a, int b) {
		if(a > b)
			return a;
		else 
			return b;
	}
}
