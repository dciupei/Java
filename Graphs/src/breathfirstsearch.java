import java.util.LinkedList;



public class breathfirstsearch {
	
	public static Iterable<Integer> BFS(Graph G, int s, int t){
		boolean visited[] = new boolean[G.numVerts()];
		int previous[] = new int[G.numVerts()];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.addLast(s);
		previous[s] = -1;
		while(!queue.isEmpty()){
			int v = queue.removeFirst();
			visited[v] = true;
			System.out.println((v / 96)+ "," + (v % 96) + "qsize = " + queue.size());
			if (v == t){
				LinkedList<Integer> path = new LinkedList<Integer>();
				for (int x = t; x != s; x = previous[x])
					path.addFirst(x);
				path.addFirst(s);
				return path;
			}
			for (int w : G.adjacents(v)) //loop for w to go through all adjacent
				if (!visited[w]){
					previous[w] = v;
					queue.addLast(w);
				}
		}
		
		
		return null;
	}
	
	public static void main(String args[]){
		PPM image = null;
		try{
		image = new PPM("world.ppm");
		} catch (Exception e){
			System.err.println(e.getMessage());
			System.exit(1);
		}
		int W = image.width();
		int H = image. height();
		UndirectedGraph G = new UndirectedGraph(W*H);
		byte pixel[] = new byte[3];
		for (int row = 0; row < image.height(); row++){
			for (int col = 0; col < image.width(); col++){
				image.readPixel(row,col, pixel);
				if (pixel[0] < 40) continue;
				if (col < W-1){
					image.readPixel(row, col+1,pixel );
					if (pixel[0] > 40 )
						G.addEdge(row*W + col, row*W + col +1);
					
				}
				if (row < H - 1) {
					image.readPixel(row + 1, col,pixel );
					if (pixel[0] > 40 )
						G.addEdge(row*W + col, (row+1)*W + col +1);
					
				}
			}
		}
		Iterable<Integer> path = BFS(G, 0, W*H - 1);
		if (path == null){
			System.err.println("no path found!");
			System.exit(-1);
		}
		pixel[0] = (byte) 255; pixel[1] = pixel[2] = 0;
		for(int p : path){
			int row = p / W;
			int col = p % W;
			image.writePixel(row,col,pixel);
		}
		try{
			image.write("dfs.ppm");
		}catch(Exception e){
			System.err.println("no path found!");
			System.exit(-1);
		}
	}
}


