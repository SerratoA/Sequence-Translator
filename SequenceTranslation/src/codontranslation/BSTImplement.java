package codontranslation;

public class BSTImplement<K extends Comparable<? super K>, V> {

	private Node<K,V> root; // root of BST
	private int size;

	private static class Node<K extends Comparable<? super K>,V> {
		public K key;       			
		public V val;             		
		public Node<K,V> left, right;  

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}
	}

	public BSTImplement() {
		this.root = null;
		this.size = 0;
	}

	public int size() { 
		return this.size; 
	}
	
	public boolean contains(K key) {
		return get(key) != null;
	}


	public V get(K key) { return get(root, key); }

	private V get(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}


	public void put(K key, V val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node<K,V> put(Node<K,V> x, K key, V val) {
		if (x == null) {
			this.size++;
			return new Node<>(key, val);
		}
		int cmp = key.compareTo(x.key);
		if      (cmp < 0)
			x.left  = put(x.left,  key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val   = val;
		return x;
	}

	public void delete(K key) {
		root = delete(root, key);
	}

	private Node<K,V> delete(Node<K,V> x, K key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left,  key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			this.size--;
		
			if (x.right == null && x.left == null){
				return null;
			} 
			else if (x.right == null) {
				return x.left;
			} 
			else if (x.left == null) {
				return x.right;
			} 
			else {
				Node<K,V> leftTreeMaxNode = findMax(x.left);
				x.key = leftTreeMaxNode.key;
				x.val = leftTreeMaxNode.val;
				x.left = delete(x.left, leftTreeMaxNode.key);
			}
		}
		return x;
	}

	private Node<K,V> findMax(Node<K,V> x) {
		Node<K,V> temp = x;
		while (temp.right != null) {
			temp = temp.right;
		}
		return temp;
	}


	
	public int countValue(V value) {
		return countValue(root, value);
	}
	
	private int countValue(Node<K,V> x, V value) {
		if (x == null) return 0;
		
		int count = 0;
		if (x.val.equals(value)) count++;
		count += countValue(x.left, value);
		count += countValue(x.right, value);
		return count;

	}

	public int height() {
		return height(root);
	}

	private int height(Node<K,V> x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}
}

	