import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class HashFrequencyTable<K> implements FrequencyTable<K>, Iterable<K>{

	private ArrayList<Entry> table; 
	private float maxLoadFactor; 
	private int Entries;

	private class Entry {
		public K key;
		public int count;
		public Entry(K k) {key=k; count=1;}
	}


	public HashFrequencyTable(int initialCapacity, float maxLoadFactor) {
		//creates the table with the initial capacity and fill with null references
		this.maxLoadFactor = maxLoadFactor;
		int sz = nextPowerOfTwo(initialCapacity);
		table = new ArrayList<Entry>(sz);
		for (int i = 0; i < sz; i++){
			table.add(null);
		}
		Entries = 0;
	}



	private float loadFactor() {
		return (float) Entries / table.size(); 
	}


	private void doubleSizeAndRehash() {
		int sz = 0;
		ArrayList<Entry> oldTable = table;	//fills the table with null
		sz = nextPowerOfTwo(oldTable.size() + 1);
		table = new ArrayList<Entry>(sz);
		for (int i = 0; i < sz; i++){
			table.add(null);
		}
		Entries = 0;
		for (int i = 0; i < oldTable.size(); i++) {//loops through old table
			Entry e = oldTable.get(i);//gets the element from old table
			if (e != null){//if it is not null
				while (e.count > 0 && e != null) {//if the count of the element is more then 0 and is not null goes back to click
					click(e.key); 
					e.count--;//decrements the count
				}
			}
		}
	}


	private static int nextPowerOfTwo(int n) {
		int e = 1;
		while ((1 << e) < n)
			e++;
		return 1 << e;
	}


	@Override
	public void click(K key) {
		int i = 0;
		int k = 0;
		int N = table.size();
		int hash = key.hashCode() & (N - 1);
		if (hash < 0){  //if the hash is less then 0
			hash = hash + N;
		}
		k = hash;
		Entry e = table.get(k);
		while (e != null) {//if element is not null
			if (key.equals(e.key)){// the key being passed equals the table key
				e.count++;//increment the count
				return;
			}
			i++;
			k = (hash + (i * (i + 1) / 2)) & (N - 1); //Quadratic probe 
			e = table.get(k); 
		}
		table.set(k, new Entry(key));//puts the new key in the table at k
		Entries++;//increments the entries
		
		if (loadFactor() >= this.maxLoadFactor){//checks to see if table is getting full
			doubleSizeAndRehash();//if so rehashs

		}

	}

	
	@Override
	public int count(K key) {
		int N = table.size();
		int hash = key.hashCode() & N - 1;  
		Entry e; 
		for(int i = 0; i < N; i++) {//loops though table
			int k = (hash + i * (i + 1) / 2) & (N - 1);//quadratic probing
			e = table.get(k);
			if(e == null) {//if the element is null returns 0
				return 0; 
			}
			else if (key.equals(e.key)) {//if not returns the count
				return e.count; 
			}
		}
		return 0; 
	}

	public Iterator<K> iterator() {
		return new TableIterator();
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

	public static void main(String[] args) {
		String hamlet =
				"To be or not to be that is the question " +
						"Whether 'tis nobler in the mind to suffer " +
						"The slings and arrows of outrageous fortune ";
		String words[] = hamlet.split("\\s+");
		HashFrequencyTable<String> table = new HashFrequencyTable<String>(10, 0.95F);
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > 0) {
				table.click(words[i]);


			}
		}
		table.dump(System.out);
	}

	public void dump(PrintStream str) {  
			for (int i = 0; i < table.size(); i++){//loops through table
			if(table.get(i) == null)//if i from the table is equal to null
				str.println(i + ": null" ); // prints null
			else //will print the key and the count
				str.println(i + ": key = " + "'" + table.get(i).key + "', " + "count" + " = " + table.get(i).count);
		}

	}
}


