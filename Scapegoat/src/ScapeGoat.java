//David Ciupei
//ScapeGoat Tree Project

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class ScapeGoat {
	
	static class Node { 
        public int key;
        public Node left, right, parent;
        public Node(int k) {
            key = k;
            left = null;
            right = null;
            parent = null;
        }
    }
	
	static class Scape_Goat_Tree {
		public Node root;
		public float alpha;
		public int size, max_size;
		public Scape_Goat_Tree(float a, int k){
			root = new Node(k);
	    	alpha = a;
	    	size = 0;
	    	max_size = 0;	
		}
		
		
		private int SizeNode(Node node){
			if (node == null)
				return 0;
			return (SizeNode(node.left) + SizeNode(node.right) + 1);
		}
		
		
		public void insert(int key) {
			int depth = 0;
			
		    root = insert(root, new Node(key));
		    
		    //check balance of the tree
		    int Balance = (int) Math.floor(Math.log(size)/Math.log(1.0/alpha));
			if (depth > Balance){
				rebuildTreeInsert(key);
			}
		}


		private Node insert(Node currentParent, Node newNode) {

		    if (currentParent == null) {
		        return newNode;
		    } else if (newNode.key > currentParent.key) {
		        currentParent.right = insert(currentParent.right, newNode);
		    } else if (newNode.key < currentParent.key) {
		        currentParent.left = insert(currentParent.left, newNode);
		    }

		    return currentParent;
		}
		
		public void rebuildTreeInsert(int key){
			Node scapegoat = find_Scape_Goat(root, key);
			Node scape_Goat_Parent = scapegoat.parent;
			if( scapegoat == root){
				root = rebuildTree(scapegoat);
				root.parent = null;
				max_size = size;
			}
			else {
				scapegoat = rebuildTree(scapegoat);
				if(scapegoat.key > scape_Goat_Parent.key){
					scape_Goat_Parent.right = scapegoat;
					scapegoat.parent = scape_Goat_Parent;
				}
				else{
					scape_Goat_Parent.left = scapegoat;
					scapegoat.parent = scape_Goat_Parent;
				}
			}
		}
		

		
		public boolean search(int key){
			Node current = root;
			while(current!=null){
				if(current.key == key){
					return true;
				}else if(current.key > key){
					current = current.left;
				}else{
					current = current.right;
				}
			}
			return false;
		}
		
		
		
		public boolean delete (int key){
			Node current = root;
			if(current == null){
				return false;
			}
			while(current != null){
				if(key == current.key){
					// right child 
					if(current.left == null){
						if(current.parent == null)
							root = current.right;
						else if(current == current.parent.left)
							current.parent.left = current.right;
						else
							current.parent.right = current.right;
						if(current.right != null)
							current.right.parent = current.parent;
					
					}
					// left child 
					else if(current.right == null){
						if(current.parent == null)
							root = current.left;
						else if(current == current.parent.left)
							current.parent.left = current.left;
						else
							current.parent.right = current.left;
						if(current.left != null)
							current.left.parent = current.parent;
					}
					else{
						//two children
						twoChildren(current);			
					}
					size -= 1;
					if(size <= alpha * max_size){
						root = rebuildTree(root);			//rebuild tree
						root.parent = null;
						max_size = size;
					}
					return true;				//deleted node
				}
				else if(key > current.key)
					current = current.right;
				else
					current = current.left;
			}
			return false;
		}
		
		public void twoChildren(Node current){
			Node min_Node = current.right;
			while(min_Node.left != null){
				min_Node = min_Node.left;
			}
			if(min_Node.parent != current){
				
				if(min_Node.parent == null)
					root = min_Node.right;
				else if(min_Node == min_Node.parent.left)
					min_Node.parent.left = min_Node.right;
				else
					min_Node.parent.right = min_Node.right;
				if(min_Node.right != null)
					min_Node.right.parent = min_Node.parent;
				min_Node.right = current.right;
				min_Node.right.parent = min_Node;
				
				if(current.parent == null)
					root = min_Node;
				else if(current == current.parent.left)
					current.parent.left = min_Node;
				else
					current.parent.right = min_Node;
				if(min_Node != null)
					min_Node.parent = current.parent;
				
				min_Node.left = current.left;
				min_Node.left.parent = min_Node;
			}
			else{
				if(current.parent == null)
					root = min_Node;
				else if(current == current.parent.left)
					current.parent.left = min_Node;
				else
					current.parent.right = min_Node;
				if(min_Node != null)
					min_Node.parent = current.parent;

			}
		}
		
		private Node find_Scape_Goat(Node x, int key){
			Node current = x;
			Float scape = alpha*SizeNode(current);
			if (scape > alpha*SizeNode(current.left) || scape > alpha*SizeNode(current.right)){
					return current;
			}
			else{
				if (key > current.key)
					return find_Scape_Goat(current.right, key);
				else
					return find_Scape_Goat(current.left, key);
			}
		}
		
		
		// rebuild tree from scapegoat
		private Node rebuildTree(Node scapegoat){
			ArrayList<Node> array = new ArrayList<Node>();
			ArrayList<Node> inorder = in_Order(scapegoat, array);
			Node newNode = Binary_Array(inorder, 0, inorder.size()-1);
			return newNode;
		}
		
		private ArrayList<Node> in_Order(Node node, ArrayList<Node> array){
			if(node == null)
				return null;
			in_Order(node.left, array);
			array.add(node);
			in_Order(node.right, array);
			return array;
		}
		
		private Node Binary_Array(ArrayList<Node> array, int start, int end){
			if (start > end)
				return null;
			int mid = (int) Math.floor((start+end)/2);
			Node node = array.get(mid);
			node.left = Binary_Array(array, start, mid-1);
			if (node.left != null)
				node.left.parent = node;
			node.right = Binary_Array(array, mid+1, end);
			if (node.right != null)
				node.right.parent = node;
			return node;
		}
		
		// serialize from Dr.Cochran's TreePrinter.java
	    private void serialize(Node tree, Vector<String> vec) {
	        if (tree == null)
	            vec.addElement(null);
	        else {
	            vec.addElement(Integer.toString(tree.key));
	            serialize(tree.left, vec);
	            serialize(tree.right, vec);
	        }
	    }
	        
	    public Vector<String> serialize() {
	        Vector<String> vec = new Vector<>();
	        serialize(root, vec);
	        return vec;
	    }
		
	}
    
    public static void main(String[] args) throws IOException{
    	FileInputStream tree_File = new FileInputStream("tree.txt");
    	BufferedReader buf = new BufferedReader(new InputStreamReader(tree_File));    	
    	String strLine;
   
    	strLine = buf.readLine();
    	String [] splitLine = strLine.split("\\s+");
    	
    	if (splitLine[0].equals("BuildTree")){
    		float alpha = Float.parseFloat(splitLine[1]);
    		int Key_Build = Integer.parseInt(splitLine[2]);
    		Scape_Goat_Tree tree = new Scape_Goat_Tree(alpha, Key_Build);
    	
    		while ((strLine = buf.readLine()) != null){
    			int key;
    			String[] Tree_File_Key = strLine.split("\\s+");	
    			
    			// insert
    			if (Tree_File_Key[0].equals("Insert")){
    				key = Integer.parseInt(Tree_File_Key[1]);
    				tree.insert(key);
    			}
    			
    			//delete
    			else if (Tree_File_Key[0].equals("Delete")){
    				key = Integer.parseInt(Tree_File_Key[1]);
    				if(tree.delete(key)){
    					System.out.println(key + " is deleted!");
    				}else{
    					System.out.println(key + " was not able to be deleted!");
    				}
    			}
    			
    			//search
    			else if (Tree_File_Key[0].equals("Search")){
    				key = Integer.parseInt(Tree_File_Key[1]);
    				if(tree.search(key)){
    					System.out.println(key + " is found!");
    				}else{
    					System.out.println(key + " is not found!");
    				}
    			}
    			
    			
    			// prints tree
    			else if (Tree_File_Key[0].equals("Print")){
    	    		Vector<String> treeP = tree.serialize();
    	            TreePrinter treePrinter = new TreePrinter(treeP);
    	            FileOutputStream out  = new FileOutputStream("ScapeGoatTree.svg");
    	            PrintStream ps = new PrintStream(out);
    	            treePrinter.printSVG(ps);
    	            System.out.println("Tree has been printed to file ScapeGoatTree.svg!");
    			}
    			
    			//done
       			else if (Tree_File_Key[0].equals("Done")){
    				buf.close();
    				System.out.println("Done");
    				System.exit(1);
    			}
    		}
    		buf.close();
    		
    	}
    	
    }
}