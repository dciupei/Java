//David Ciupei
//CS223


import java.util.Vector;


public class SplaySymbolTable<K extends Comparable<K>,V> implements SymbolTable<K,V> {
	public class Node {
		public K key;
		public V val;
		public Node left, right;
		public Node parent; // null for root
		public Node(K k, V v) {
			key = k; val = v;
		} 


	}
	public int rotations;
	public int comparisons;

	private Node root;



	public SplaySymbolTable() {root = null;}

	/*	private void splay(Stack<Node> path) {
		assert !path.isEmpty();
		Node x = path.pop();
		comparisons++;
		while (path.size() >= 2) {
			Node parent = path.pop();
			Node grandparent = path.pop();
			if (grandparent.left == parent) {
				if (parent.left == x) { // zig-zig
					x = rotR(rotR(grandparent));
				} else { // zig-zag
					grandparent.left = rotL(parent);
					x = rotR(grandparent);

				}
			} else {
				if (parent.left == x) { // zag-zig
					grandparent.right = rotR(parent);
					x = rotL(grandparent);

				} else { // zag-zag
					x = rotL(rotL(grandparent));  

				} 

			}
			 if (path.isEmpty()) // re-attach splayed subtree
	        root = x;
	      else if (path.peek().right == g)
	        path.peek().right = x;
	      else
	        path.peek().left = x;
	    }
			comparisons++;
		}
		if (!path.isEmpty())
			root = (root.left == x) ? rotR(root) : rotL(root);
			comparisons++; 
	}*/
	private void splay(Node path) {
		assert path != null;
		comparisons++;
		while (path.parent != null && path.parent.parent != null) {
			Node p = path.parent;
			Node g = p.parent;
			if (g.left == p) {
				if (p.left == path) { // zig-zig
					rotR(g);
					rotR(p);
				} else { // zig-zag
					rotL(p);
					rotR(g);
				}
			} else {
				if (p.left == path) { // zag-zig
					rotR(p);
					rotL(g);

				} else { // zag-zag
					rotL(g);
					rotL(p);

				} 

			}
			comparisons++;
		}
		if (path != root){
			if (root.left == path){ //zig
				rotR(root);
			}else{//zag
				rotL(root);
			}

		}
		comparisons++;
	}


	private Node rotL( Node p ){
		Node x = p.right;
		p.right = x.left;
		if ( x.left != null ){
			x.left.parent = p;
		}
		x.parent = p.parent;
		if ( p.parent == null ){ //if P was the root of the entire tree
			root = x;
		}
		else if ( p.parent.left == p ){//if P was the left child of parent
			p.parent.left = x;
		}
		else{
			p.parent.right = x;//if P was the right child
		}

		x.left = p;
		p.parent = x;
		rotations++; //increments number of rotations
		return x;
	}

	private  Node rotR( Node p ){
		Node x = p.left;
		p.left = x.right;
		if ( x.right != null ){
			x.right.parent = p;
		}
		x.parent = p.parent;
		if ( p.parent == null ){ //if P was the root of the entire tree
			root = x;
		}
		else if ( p.parent.right == p ){ //if P was the right child of its parent
			p.parent.right = x;
		}
		else{//P was the left child
			p.parent.left = x;
		}
		x.right = p;
		p.parent = x;
		rotations++;//increments the number of rotations
		return x;
	}

	@Override
	public void insert(K key, V val) {
		if (root == null) {
			root = new Node(key, val);
			return;
		}
		Node x = root;
		while (true) {
			int cmp = key.compareTo(x.key);
			if (cmp == 0) {
				x.key = key; x.val = val;
				splay(x);
				break;
			}
			if (cmp < 0) {
				if (x.left == null) {
					Node n = new Node(key,val);
					n.parent = x;
					x.left = n;
					splay(n);
					break;
				}
				x = x.left;	
			}

			else {
				if (cmp > 0) {
					if (x.right == null){
						Node n = new Node(key, val);
						n.parent = x;
						x.right = n;
						splay(n);
						break;
					}
				}
				x = x.right;

			}

		}

	}


	@Override
	public V search(K key) {
		if (root == null) 
			return null;
		Node x = root;
		V val = null;
		while (true) {
			assert x != null;
			//path.push(x);
			int cmp = key.compareTo(x.key);
			if (cmp == 0) {
				val = x.val;
				splay(x);
				break;
			}
			if (cmp < 0) {
				if (x.left == null){
					splay(x);
					break;
				}
				x = x.left;
			} else {
				if (x.right == null){
					splay(x);
					break;
				}
				x = x.right;
			}
		}
		return val;

	}


	private void serializeAux(Node tree, Vector<String> vec) {
		if (tree == null)
			vec.addElement(null);
		else {
			vec.addElement(tree.key.toString() + ":black");
			serializeAux(tree.left, vec);
			serializeAux(tree.right, vec);
		}
	}
	public Vector<String> serialize() {
		Vector<String> vec = new Vector<String>();
		serializeAux(root, vec);
		return vec;
	}

}
