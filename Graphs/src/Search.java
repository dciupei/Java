import java.util.Stack;


public class Search {

	public static Iterable<Integer> DFS(Graph G, int s, int t){
		boolean visited[] = new boolean[G.numVerts()];
		int previous[] = new int[G.numVerts()];
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(s);
		previous[s] = -1;
		while(!stack.isEmpty()){
			int v = stack.pop();
			visited[v] = true;
			System.out.println((v/96)+ "," + (v%96));
			if (v == t){
				Stack<Integer> path = new Stack<Integer>();
				for (int x = t; x != s; x = previous[x])
					path.push(x);
				path.push(s);
				return path;
			}
			for (int w : G.adjacents(v)) //loop for w to go through all adjacents
				if (!visited[w]){
					previous[w] = v;
					stack.push(w);
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
		Iterable<Integer> path = DFS(G, 0, W*H - 1);
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
