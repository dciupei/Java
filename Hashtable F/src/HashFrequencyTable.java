import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;


public class HashFrequencyTable<K> implements FrequencyTable<K>, Iterable<K>{

	private ArrayList<Entry> table; 
	private float maxLoadFactor; 
	private int Entries = 0;

	private class Entry {
		public K key;
		public int count;
		public Entry(K k) {key=k; count=1;}
	}


	
	



	public HashFrequencyTable(int initialCapacity, float maxLoadFactor) {
		this.maxLoadFactor =  maxLoadFactor;
		int sz = nextPowerOfTwo(initialCapacity);
		table = new ArrayList<Entry>(sz);
		for (int i = 0; i < sz; i++);
			table.add(null);
			//Entries = 0;
	}



	private float loadFactor() {
		return (float) Entries / table.size(); 
	}




	/*private void insert(K key) {
		int h = key.hashCode();
		int i = 0; 
		int k = key.hashCode() % table.size(); 
		Entry element;
		while((element = table.get(k)) != null) {
			if (key.equals(element.key)){
				element.key = key;
				return;
			}
			k = (h + i * (i + 1) / 2) & (table.size() - 1);
			i++; 
		}
		element = new Entry(key);
		//Entry element = new Entry(key);
		table.set(k, element); 
		Entries++; 
		if(loadFactor() > this.maxLoadFactor) ;
			   doubleRehash(); 
		//return; 
	}*/




/*	private void doubleRehash() {
		ArrayList<Entry> oldTable = table; 
		int sz = nextPowerOfTwo(table.size());
		table = new ArrayList<Entry>(sz);
		for(int i = 0; i < sz; i++) {
			table.add(null); 
		}
		//Entries = 0; 
		for(int i = 0; i < oldTable.size(); i++) {
			Entry element = oldTable.get(i); 
			if(element != null) {
		int l = hash(oldTable.get(i).key);
		table.set(l, oldTable.get(i));
			////insert(element.key); dont use this
		}
		}
	}*/

	/*public int hash(K key){
		int N = table.size();//
		int h = key.hashCode() & N-1; // error
		int i = 0;//
		int k =  (h+i *((i+1)/2)) & (N-1);// formula!!!
		while(i < table.size()){
			if (table.get(k) == null){
				return k;
			}
			else if(table.get(k)!=null){
				if(table.get(k).key.equals(key))//
					return k;

			}

			k =(h + i * (i+1)/2) & (N-1); // formula
			i++;
		}
		return -1;
	}*/


	private static int nextPowerOfTwo(int n) {
		int e = 1;
		while ((1 << e) < n)
			e++;
		return 1 << e;
	}

	



	private class TableIterator implements Iterator<K> {
		private int i;
		public TableIterator() {i = 0;}
		public boolean hasNext() {
			while (i < table.size() && table.get(i) == null)
				i++;
			return i < table.size();
		}
		public K next() {
			return table.get(i++).key;
		}
		public void remove() {
			throw new UnsupportedOperationException("Remove not supported");
		}
	}



	public Iterator<K> iterator() {
		return new TableIterator();
	}




	@Override
	public void click(K key) {
		//int h = key.hashCode() & (N-1)
		//int i = 0
		//while (true){
		//int k = (h + i) & (N-1) change h + i to quadratic 
		//Entry e = table.get(k)
		//if (e == null)
		//table.put(k, new Entry (key, 1))
		//return}
		//if (key.equals(e,key)){
		//e.count++;
		//return}
		//i++
		
		int h = key.hashCode() & (table.size() - 1);
		int i = 0;
		while (i < table.size()){
			int k = (h + i * (i + 1) / 2) & (table.size()-1);
			Entry e = table.get(k);
			if (e == null){
				table.set(k, new Entry (key));
				return;
				
			}
			if (key.equals(e.key)){
				e.count++;
				 
				return;
			}
			
			i++;
		
					
		}
	}
		 
		
			


	@Override
	public int count(K key) {
		int h = key.hashCode() & table.size()-1;
		int c = 0; 
		Entry e; 
		for(int i = 0; i < table.size(); i++) {
			int k = (h + i * (i + 1) / 2) & (table.size() - 1);
			e = table.get(k);
			if(e == null) {
				return c; 
			}
			else if (key.equals(e.key)){
				return e.count;
		}
		c = e.count;
	}
		return c; 
	}


	public static void main(String[] args) {
		String hamlet =
				"To be or not to be that is the question " +
						"Whether 'tis nobler in the mind to suffer " +
						"The slings and arrows of outrageous fortune ";
		String words[] = hamlet.split("\\s+");
		HashFrequencyTable<String> table = new HashFrequencyTable<String>(10, 0.95F);
		for (int i = 0; i < words.length; i++){
			if (words[i].length() > 0)
				table.click(words[i]);
		}
		table.dump(System.out);

	}
	
	public void dump(PrintStream str) { 
		
		int i =0;
		while(i < table.size()){
			if (table.get(i) == null)
				str.println(i + ": null");
			else
				str.println(i + ": key = "+"" + table.get(i).key +"',"+ " count"+ " = " + table.get(i).count);
			i++;
		}
	}
}

