public class Pair {
	int x;
	int y;
	public Pair(int a, int b) {
		this.x = a;
		this.y = b;
	}
	public int compareTo(Pair p) {
		if(this.x == p.x)
			return 0;
		else
			return -1;
	}
}
