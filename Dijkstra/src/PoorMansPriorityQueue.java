import java.util.TreeSet;


public class PoorMansPriorityQueue<E extends Comparable<E>, P extends Comparable<P>> {

	private class Pair implements Comparable<Pair>{
		public E elem;
		public P priority;
		public Pair(E e, P p){
			elem = e;
			priority = p;
		}
		public int compareTo(Pair other){
			int cmp = priority.compareTo(other.priority);
			if (cmp == 0)
				cmp = elem.compareTo(other.elem);
			return cmp;
		}
		
	}
	
	private TreeSet<Pair> set;
	
	public PoorMansPriorityQueue(){
		set = new TreeSet<Pair>();
	}
	
	public void insert(E elem, P priority){
		set.add(new Pair(elem, priority));
	}
	
	public boolean isEmpty() {return set.isEmpty();}
	
	public E deleteMin(){
		Pair p = set.first();
		set.remove(p);
		return p.elem;
	}
	
	public void decreaseKey(E elem, P oldPriority, P newPriority){
		Pair p = new Pair (elem, oldPriority);
		set.remove(p);
		p.priority = newPriority;
		set.add(p);
	}
	
}
