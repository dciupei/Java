//David Ciupei
//PS5

public class MoveToFrontList {

	private StringCountElement head; // the head reference
	private StringCountElement tail; // the tail reference
	private int size; // the size of the list (number of valid items)
	

	/**
	 * 
	 * _Part 1: Implement this constructor._
	 * 
	 * Creates a new, initially empty MoveToFontList. This list should be a
	 * linked data structure.
	 */
	public MoveToFrontList() {
		// TODO: implement this
		size = 0;
		head = null;
		tail = null;
	}

	/**
	 * This method increments the count associated with the specified string
	 * key. If no corresponding key currently exists in the list, a new list
	 * element is created for that key with the count of 1. When this method
	 * returns, the key will have rank 0 (i.e., the list element associated with
	 * the key will be at the front of the list)
	 * 
	 * @param key
	 *            the string whose count should be incremented
	 * @return the new count associated with the key
	 */
	public int incrementCount(String key) {
		StringCountElement s = find(key);
		if (s != null) {
			// found the key, splice it out and increment the count
			spliceOut(s);
			s.count++;
		} else {
			// need to create a new element
			s = new StringCountElement();
			s.key = key;
			s.count = 1;
			size++;
		}
		// move it to the front
		spliceIn(s, 0);
		return s.count;
	}

	/**
	 * 
	 * @return the number of items in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * _Part 2: Implement this method._
	 * 
	 * Find the list element associated with the specified string. That is, find
	 * the StringCountElement that a key equal to the one specified
	 * 
	 * @param key
	 *            the key to look for
	 * @return a StringCountElement in the list with the specified key or null
	 *         if no such element exists.
	 */
	public StringCountElement find(String key) {
		// TODO: implement this
		//if (head.key.equals(key)){
			//return head;
	//	}
		StringCountElement c = head; // new element head
		while (c != null){ //while loop if if the head is not null
			if (c.key.equals(key) ){return c;}//compares the key with the key from node
				c = c.next;//the new head will equal the next 
		}
		
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Compute the rank of the specified key. Rank is similar to position, so
	 * the first element in the list will have rank 0, the second element will
	 * have rank 1 and so on. However, an item that does not exist in the list
	 * also has a well defined rank, which is equal to the size of the list. So,
	 * the rank of any item in an empty list is 0.
	 * 
	 * @param key
	 *            the key to look for
	 * @return the rank of that item in the rank 0...size() inclusive.
	 */
	public int rank(String key) {
		// TODO: implement this
		//StringCountElement r = head;
		StringCountElement h = head;
		
		
		if (head == null){
			return size;
		}
		
		int rank = 0;//start the rank at 0 
		while(head != null){
			if (h.key.equals(key)){
				return 0;
		}//if the key does not equal null while loop
			h = h.next;;//increments the rank
			return rank;//returns the rank
			
		}
		
		
		return size;
	}
	
	
	


	/**
	 * _Part 4: Implement this method._
	 * 
	 * Splice an element into the list at a position such that it will obtain
	 * the desired rank. The element should either be new, or have been spliced
	 * out of the list prior to being spliced in. That is, it should be the case
	 * that: s.next == null && s.prev == null
	 * 
	 * @param s
	 *            the element to be spliced in to the list
	 * @param desiredRank
	 *            the desired rank of the element
	 */
	public void spliceIn(StringCountElement s, int desiredRank) {
			// TODO: implement this
		s = new StringCountElement();//makes a new element 
		if (head == null){//if statement if the list is full
			return;
		}
		while (s.next == null && s.prev == null){//while loop to check the list where the next 
			//and previous node is equals to null
			s.prev.next = s.next;//splices in the new element
		}
		size++;//increment the size
		
		return;
	}

	/**
	 * _Part 5: Implement this method._
	 * 
	 * Splice an element out of the list. When the element is spliced out, its
	 * next and prev references should be set to null so that it can safely be
	 * splicedIn later. Splicing an element out of the list should simply remove
	 * that element while maintaining the integrity of the list.
	 * 
	 * @param s
	 *            the element to be spliced out of the list
	 */
	public void spliceOut(StringCountElement s) {
		// TODO: implement this
		StringCountElement current = new StringCountElement();//creates a new element current
		s.prev = null;//sets the previous to null
		current = head;//sets the current to head
		while (current != null){//while loop if the current is in the list
			if (current.key.equals(s)){//if current equals the specified item
				current = current.next;//sets the new current to the next
				if (s.prev == null)//if statement if the previous node equals null
					s.prev = current;//sets the previous to the current
				else s.prev.next = current;//if not it goes back 2 and sets that to the current
				s.count--;//decreases the count
			}
			else{
				s.prev = current;//the previous node is the current
				current = current.next;//the current is set to the next node
			}
			}
		}
	
	

}
