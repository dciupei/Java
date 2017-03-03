//David Ciupei
//PS4 

public class OrderedArrayList<T extends Comparable<T>> {
 
	/** This is an array of Objects of type T */
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	/**
	 * Construct an OrderedArrayList with 10 empty slots. Note the recipe for 
	 * creating an array with the generic type. You'll want to reuse this 
	 * recipe in the insert() method.  You'll also possibly want to tweak this 
	 * constructor a bit if you add other instance variables.
	 */
	public OrderedArrayList() {
		array = (T[]) new Comparable[10];
		size = 0;
	}

	@SuppressWarnings("unchecked")
	/**
	 * _Part 1: Implement this method._
	 * 
	 * Inserts a new item in the OrderedArrayList. This method should ensure
	 * that the list can hold the new item, and grow the backing array if
	 * necessary. If the backing array must grow to accommodate the new item, it
	 * should grow by a factor of 2. The new item should be placed in sorted
	 * order using insertion sort. Note that the new item should be placed
	 * *after* any other equivalent items that are already in the list.
	 * 
	 * @return the index at which the item was placed.
	 */
	public int insert(T item) {
		//if (size == array.length -1){
		//T[] newarray = (T[]) new Comparable[array.length*2];
		//for (int i = 0; array.length; i++){
		//     newarray[i] = array[i];
		//}
		//  array = newarray;
		//int i = size; 
		//while (i>o && item.compareTo(array[i-1])<0){
		//array[i] = array[i-1];
		//i--;
		//}
		//array[i] = item;
		//size++;
		//return i;
		//}
		// TODO: implement this
		size++;//will grow the array by 1 since its inserting a new item
		T[] list;//new list
		if (size>array.length){
		list = (T[]) new Comparable[array.length*2];
		}//if the size is greater then the length of the array then it will grow the backing
		//array by a factor of 2.
		else{
			list = (T[]) new Comparable[array.length];
		}//if the size is smaller then the length of the array then it will leave 
		//the array alone
		int index = 0;//initializes the index
		for (int i = 1;i < size - 1;i++){
			T key = array[i];//this is going to go through the array and insert the
			                 //new item into the right place and to see if the key  is equal
			                 //to the object in the array
			if (key.compareTo(item)<=0){//is going to compare the key to the item and if it is 
				//0 or less then the item will be inserted
				list[i]=key;
			}else{
				list[i] = key; //if it is greater then 0 then it will insert the item there
				index = i;
				for (int j=i-1;j<size;j++){
					list[j] = array[j];//this for loop will just insert the rest of the items
					//into the array 
				}
				break;//will just exit the for loop
			}
			
			
			
		}
		array = list;//sets the array equal to the list
		
		return index;
		
}
	//my insertion sort part does not seem to be working properly and not sure why.
	

	/**
	 * _Part 2: Implement this method._
	 * 
	 * @return the number of items in the list.
	 */
	public int size() {
		// TODO: implement this 
	
		return size;
	}

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Gets an item from the ordered array list. You can assume that this method
	 * will only be called with valid values of index. Specifically, values that
	 * are in the range 0 - (size-1). To impress your friends and build your
	 * street cred, consider adding checks that the index supplied is in fact in
	 * bounds. If it is not, you can raise an IndexOutOfBoundsException.
	 * 
	 * @param index
	 *            the index to get an item from
	 * @return an item at the specified index
	 */
	public T get(int index) {
		// TODO: implement this
		if (index >= 0 && index <= size - 1){//if the index is greater or equal to 0 
			//and if the index is less then or equal to size - -1 then it will return 
			//the index in the array
			return array[index];
			// only needed "return array[index];
		}
		else{
			throw new IndexOutOfBoundsException("Index is to large!");
			//saying that the index is to large
		}
	}
	

	/**
	 * _Part 3: Implement this method._
	 * 
	 * Counts the items in the ordered array list that are equal to the item at
	 * the specified index. Be sure to take advantage of the fact that the list
	 * is sorted here. You should not have to run through the entire list to
	 * make this count.
	 * 
	 * @param index
	 *            an index in the range 0..(size-1)
	 * @return the number of items in the list equal to the item returned by
	 *         get(index)
	 */
	public int countEquivalent(int index) {
		//int i = index;
		//int left;
		//T obj = array[index];
		//while(i>= 0 && array[i].compareTo(obj) == 0){
		//i--;
		//}
		//left = i +1;
		//i = index;
		//while (i< size && array[i].compareTo(obj) == 0)
		//i++;
		//}
		//return i - left;
		//
		// TODO: implement this
		int i = index; 
		int count = 0;
		while(array[i].equals(array[index])){
			i++;
			count++;
		}
		return count;
	}

	/**
	 * _Part 4: Implement this method._
	 * 
	 * Finds the location of the first object that is equal to the specified
	 * object. Linear search is sufficient here, but feel free to leverage your
	 * binary search code too.
	 * 
	 * @param obj
	 *            an object to search for in the list
	 * @return the first index of an object equal to the one specified, or -1 if
	 *         no such object is found.
	 */
	public int find(T obj) {
		// TODO: implement this
		// linear search was implemented here and is saying if the object in the 
		//array is equal to the new object that it will return that object
		// if not it will return -1
		for(int i = 0; i < size-1; i++){
			if (array[i].equals(obj))
				return i;
			}
		
		return -1;
		
		}
		
	

	/**
	 * _Part 5: Implement this method._
	 * 
	 * Removes all the objects equal to the specified object.
	 * 
	 * @param obj
	 *            an object equal to the one(s) you'd like to remove
	 * @return the number of objects removed
	 */
	public int remove(T obj) {
		// TODO: implement this
		
		int duplicate = 0;
	for (int i = 0; i<size - 1; i++){
		if (array[i].compareTo(obj)==0);
			size = size - duplicate;
		}
	
		return duplicate;
	}
	
	/**
	 * This method is included for testing purposes.
	 * Typically, you would not want to expose a private instance variable
	 * as we are doing here. However, it does have value when the code is 
	 * going through a testing phase. Do not modify or remove this method.
	 * Some WebCAT tests may rely upon it.
	 */
	public Comparable<T>[] dbg_getBackingStore() { return array; }
}
