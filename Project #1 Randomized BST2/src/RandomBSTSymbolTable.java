//David Ciupei
//Project#1

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Vector;


public class RandomBSTSymbolTable<K extends Comparable<K>, V>
	implements SymbolTable<K, V> {

        //a nested node class of the key and val
		private class Node{
			 public K key; V val;
			 public Node left , right;
			 public int N;
			 public Node(K k, V v){
			    key = k; val = v;
				left = right = null;
				N=1;
			}
			
		}
		//int r = (int) (RNG.nextDouble()*100);

		private Node root;
		
		//keeps track of the size of the tree
		private int size(Node tree) {return (tree == null) ? 0 : tree.N;}
		
		//sets the root to null
		public RandomBSTSymbolTable(){root = null;}

		@Override
		public V search(K key) {
			return searchRandom(root, key);

		}
        
		
		private V searchRandom(Node tree, K key){
			if (tree == null)
				return null;
			int cmp = key.compareTo(tree.key);
			if (cmp == 0)
				return tree.val;
			return (cmp < 0) ? searchRandom(tree.left, key) : searchRandom(tree.right,key);//if (cmp < 0) is true use first argument if not use second argument. 
		}


		@Override
		//the wacked node is the node that was removed
		public V remove(K key) {
			Node wacked = new Node(null, null);
			root = RandomRemove(root, key, wacked);
			return wacked.val;
			}
		
		//after the base case it will find the node and wack it(remove it) and join
		//the two subtrees. If not it will check the left and right side of the tree
		//recursively 
		private Node RandomRemove(Node tree, K key, Node wacked) {
			if (tree == null)
				return null;
			int cmp = key.compareTo(tree.key);
			if (cmp == 0) {
				wacked.key = tree.key;
				wacked.val = tree.val;
				return join(tree.left,tree.right);//joins the two sub trees
				//if (tree.left == null)
				//	return tree.right;
				//else if (tree.right == null)
				//	return tree.left;
				//else {
					//Node node = new Node(null, null);
					//tree.right = removeMin(tree.right, node);
					//tree.key = node.key;
					//tree.val = node.val;
					//return tree;
				//}
			} else if (cmp < 0) {
				tree.left = RandomRemove(tree.left, key, wacked);
			} else {
				tree.right = RandomRemove(tree.right, key, wacked);
			}
			return tree;
		}  
		
		//after removing the node this method will join the two subtrees
		private Node join(Node X, Node Y) {
			if (X == null) return Y;
			if (Y == null) return X;
			if (RNG.nextDouble()*(X.N + Y.N) < X.N) { // flip a weighted coin
				X.right = join(X.right, Y);
				X.N = 1 + size(X.left) + size(X.right);
				return X;
			} else {
				Y.left = join(X, Y.left);
				Y.N = 1 + size(Y.left) + size(Y.right);
				return Y;
			}
		}
		private static Random RNG = new Random(1234);


		private Node insertRandom(Node tree,K key, V val) {
			if (tree == null) return new Node(key, val); // N = 1 (set in constructor)
			if (RNG.nextDouble()*(tree.N+1) < 1.0) // with probability 1/(N+1)
				return insertRoot(tree, key, val); // insert new node at root
			int cmp = key.compareTo(tree.key); // else recurse down tree
			if (cmp == 0) {
				tree.key = key; tree.val = val; // replace payload (N unchanged)
			} else if (cmp < 0) {
				tree.left = insertRandom(tree.left, key, val); // insert into left tree
				tree.N = 1 + tree.left.N + size(tree.right); // update N
			} else {
				tree.right = insertRandom(tree.right, key, val); // insert into right tree
				tree.N = 1 + size(tree.left) + tree.right.N; // update N
			}
			return tree;


		}
		
		//inserts the node at the root by using right and left rotations
		private Node insertRoot(Node tree, K key, V val){
			if (tree == null)
				return new Node(key, val);
			int cmp = key.compareTo(tree.key);
			if (cmp == 0) {
				tree.key = key;
				tree.val = val;
			}else
				if (cmp < 0){
					tree.left = insertRoot(tree.left, key, val);
					tree = rotateRight(tree);
					tree.N = 1 + size(tree.left) + size(tree.right);//updates the size of the  tree
				}
				else{
					tree.right = insertRoot(tree.right, key, val);
					tree = rotateLeft(tree);
					tree.N = 1 + size(tree.left) + size(tree.right);//updates the size of the tree
				}
			return tree;
		}

		private Node rotateRight(Node tree){
			Node root = tree.left;
			tree.left = root.right;
			root.right = tree;
			//tree.N = 1 + size(tree.left) + size(tree.right);
			//root.N = 1 + size(root.left) + tree.N;
			return root;
		}

		private Node rotateLeft(Node tree){
			Node root = tree.right;
			tree.right = root.left;
			root.left = tree;
			//tree.N = 1 + size(tree.left) + size(tree.right);
			//root.N = 1 + size(root.right) + tree.N;
			return root;
		}


		@Override
		public void insert(K key, V val) {
			root = insertRandom(root, key, val);

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
		public static void main(String args[]) {

			RandomBSTSymbolTable<Integer,Integer> symtab = new RandomBSTSymbolTable<Integer,Integer>();
			
			//Random RNG = new Random(1234);
			for (int i = 0; i < 100; i++) {
	//			int r = (int) (RNG.nextDouble()*100);
				symtab.insert(i, i);
			}
			
			symtab.remove(65);
			symtab.remove(26);
			symtab.remove(64);
			
//			int keys[] = {6, 1, 9, 3, 4, 5, 1, 10, 12, 0, 8, 2};
//			for (int i = 0; i < keys.length; i++)
//				symtab.insert(keys[i], keys[i]);
			
//			Integer val = symtab.search(5);
//			if (val == null)
//				System.out.println("not found!");
//			else
//				System.out.println("found!");
			
			Vector<String> st = symtab.serialize();
			TreePrinter treePrinter = new TreePrinter(st);
			try {
				FileOutputStream out = new FileOutputStream("tree.svg");
				PrintStream ps = new PrintStream(out);
				treePrinter.printSVG(ps);
			} catch (FileNotFoundException e) {}
			
}
	
}
