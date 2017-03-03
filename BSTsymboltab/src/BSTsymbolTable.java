
public class BSTsymbolTable<K extends Comparable<K>, V> implements SymbolTable<K,V> {

	
	private class Node{
		public K key;
		public V val;
		public Node left, right;
		public Node(K k, V v){
			key=k; 
			val=v;
			left = right = null;}
	}
	
	private Node root;
	public BSTsymbolTable() {
		root = null;
		}
	
	@Override
	public void insert(K key, V val) {
		
		root = insertAux (root,key,val);
		
	}
	private Node insertAux(Node tree, K key, V val){
		if (tree == null)
			return new Node(key,val);
		int cmp = tree.key.compareTo(key);
		if (cmp == 0){
			tree.key = key;
			tree.val = val;
			
		}else if (cmp<0){
			tree.left = insertAux(tree.left,key,val);//recursion
		}else {//cmp > 0
			tree.right = insertAux(tree.right,key,val);//recursion
		}
		return tree;	
	}

	@Override
	public V search(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	private V searchAux(Node tree, K key){
		if (tree == null)
			return null;
		int cmp = tree.key.compareTo(key);
		if (cmp == 0)
			return tree.val;
		return (cmp < 0) ? searchAux(tree.left, key) : searchAux(tree.right,key);//if (cmp < 0) is true use first argument if not use second argument. 
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

}
