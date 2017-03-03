import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class UndirectedGraph implements Graph{

	private ArrayList<Set<Integer>> adj;
	
	public  UndirectedGraph( int V){
		adj = new ArrayList<Set<Integer>>(V);
		for (int i = 0; i < V; i++)
			adj.add(new TreeSet<Integer>());
	}
	
	@Override
	public int numVerts() {
		return adj.size();
	}
	
	public void addEdge(int s, int d){
		adj.get(s).add(d);
		adj.get(d).add(s);
	}

	@Override
	public Iterable<Integer> adjacents(int s) {
		return adj.get(s);
	}

}
