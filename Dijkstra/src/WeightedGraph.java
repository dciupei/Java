
public interface WeightedGraph {
	public int numVerts();
	Iterable<Integer> adjacents(int v);
	int weight(int u, int v);
}
