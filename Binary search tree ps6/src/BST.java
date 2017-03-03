//David Ciupei
// BST PS6
/**
 * A Binary Search Tree is a Binary Tree with the following
 * properties:
 * 
 * For any node x: (1) the left child of x compares "less than" x; 
 * and (2) the right node of x compares "greater than or equal to" x
 *
 *
 * @param <T> the type of data object stored by the BST's Nodes
 */
public class BST<T extends Comparable<T>> {

	/**
	 * The root Node is a reference to the 
	 * Node at the 'top' of a QuestionTree
	 */
	private Node<T> root;
	
	
	/**
	 * Construct a BST
	 */
	public BST() {
		root = null;
	}
	
	/**
	 * @return the root of the BST
	 */
	public Node<T> getRoot() { 
		return root;
	}
	
	/**
	 * _Part 1: Implement this method._
	 *
	 * Add a new piece of data into the tree. To do this, you must:
	 * 
	 * 1) If the tree has no root, create a root node 
	 *    with the supplied data
	 * 2) Otherwise, walk the tree from to the bottom (to a leaf) as though
	 *    searching for the supplied data. Then:
	 * 3) Add a new Node to the leaf where the search ended.
	 *
	 * @param data - the data to be added to the tree
	 */
	public void add(T data) {
		// TODO: implement this
		if (root == null){//checks to see if there is a root

			root = new Node<T>(data);//makes a new node with the data provided to the root
		}
			else{	
				Node<T> currentNode = root;
				while (true){
					if (data.compareTo(currentNode.data) < 0){
						if (currentNode.left != null){//if the node to the left is not empty
							currentNode = currentNode.left;//sets the left node to the new current node
						}


						else{
							currentNode.left = new Node<T>(data);//if there is no left the data is inserted to the left node
							currentNode.left.parent = currentNode;
							return;

						}
					}


					if (data.compareTo(currentNode.data)>=0){//checks to see if the specified data is greater then the current node data
						if (currentNode.right != null){//checks to see if the right element has data
							currentNode = currentNode.right;//sets the right node it the current node

						}

						else{
							currentNode.right = new Node<T>(data);//inserts a new node with the data to the right node
							currentNode.right.parent = currentNode;
							return;
						}

					}
				}
			}
	}




	/**
	 * _Part 2: Implement this method._
	 * 
	 * Find a Node containing the specified key and
	 * return it.  If such a Node cannot be found,
	 * return null.  This method works by walking
	 * through the tree starting at the root and
	 * comparing each Node's data to the specified 
	 * key and then branching appropriately.
	 * 
	 * @param key - the data to find in the tree
	 * @return a Node containing the key data, or null.
	 */
	public Node<T> find(T key) {
		Node<T> currentNode = root;//creates a new local variable referencing the root
		while(currentNode != null){//while loop checking if there is data in the node
			if (key.compareTo(currentNode.data)==0){// checks to see if the current node data is equal to the specified key
				return currentNode;
			}

				if (key.compareTo(currentNode.data)<0){// checks to see if the key is less then the current node data
					currentNode = currentNode.left;// sets the left node to the current node

				}
				else{
					if (key.compareTo(currentNode.data)>0){// checks to see if the key is greater then the current node data
						currentNode = currentNode.right;// sets the right node to the current node
	
					}
				}
		}
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 *  
	 * @return the Node with the largest value in the BST
	 */
	public Node<T> maximum() {
		// TODO: implement this
		Node<T> max = root;//makes a new local variable equal to the root 
		while(max.right != null){//loop if the right node is not empty
			max = max.right;//sets the new max to the right node
		}
		return max;
	}
	
	/**
	 * _Part 4: Implement this method._
	 *  
	 * @return the Node with the smallest value in the BST
	 */
	public Node<T> minimum() {
		// TODO: implement this
		//same as for the max but goes to the left now to get the minimum value
		Node<T> min = root;
		while(min.left != null){
			min = min.left;
		}
		return min;
	}
	

	
	/**
	 * _Part 5: Implement this method._
	 *  
	 * Searches for a Node with the specified key.
	 * If found, this method removes that node
	 * while maintaining the properties of the BST.
	 *  
	 * @return the Node that was removed or null.
	 */
	public Node<T> remove(T data) {
		// TODO: implement this
		Node<T> currentNode = root;
		Node<T> parent = root;
		if (root == null){
			return null;
		}
		//a loop to see if the current node is not empty and if the data in the loop is not equal
		//to the data given
		while(currentNode != null && currentNode.data != data){
			parent = currentNode;//will set the parent to the current node that it is on
			if (data.compareTo(currentNode.data)<0){//compares the data given to the current node data
				currentNode = currentNode.left;// if less then it will set the new current node to the left node

			}else{
				currentNode = currentNode.right;// if not it will set it to the right node
			}
			if (currentNode == null){//checks to see if the current node exists 
				return null;
			}
			
		}
		// this case is if there is no left and right node
		if (currentNode.left == null && currentNode.right == null){
			if (parent.left == currentNode){//if the parents left node is equal to the current node
				parent.left = null;//it will set the left node of the parent to null
			}
			else{ parent.right = null;//if not it will set the parents right node to null
			return currentNode;
			}
		}	
		//this case is if there is a right node but not a left node
		if (currentNode.left == null && currentNode.right != null){
			if (parent.left == currentNode){//if the parents left node equal to the current node
				parent.left = currentNode.right;//it will set the parent left node to the current node right node
			}
			else{ parent.right = currentNode.right;// if not it will set the parents right node to the current nodes right
			return currentNode;
			}
		}
		// in the case is if there is a left node and not a right node
		if (currentNode.left != null && currentNode.right == null){
			if (parent.left == currentNode){// checks to see if the parents left node is equal to the current node
				parent.left = currentNode.left;// if so then the parents left node is set to the current nodes left node
			}
			else{ parent.right = currentNode.left;// if not the parents right node is set to the current nodes left node
			return currentNode;
			}
		}

		return currentNode;
	}
	
	/**
	 * 
	 * The Node class for our BST.  The BST
	 * as defined above is constructed from zero or more
	 * Node objects. Each object has between 0 and 2 children
	 * along with a data member that must implement the
	 * Comparable interface.
	 * 
	 * @param <T>
	 */
	public static class Node<T extends Comparable<T>> {
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
		private T data;
		
		private Node(T d) {
			data = d;
			parent = null;
			left = null;
			right = null;
		}
		public Node<T> getParent() { return parent; }
		public Node<T> getLeft() { return left; }
		public Node<T> getRight() { return right; }
		public T getData() { return data; }
	}
}
