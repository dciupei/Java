import java.util.ArrayList;


public class ImageVerticalSeamGraph implements WeightedGraph{

	private PPM image;
	public ImageVerticalSeamGraph(PPM image){
		this.image = image;
	}
	@Override
	public int numVerts() {
		
		return image.width() * image.height() + 2;
	}

	@Override
	public Iterable<Integer> adjacents(int v) {
		int W = image.width();
		int H = image.height();
		
		ArrayList<E> adj = new Arraylist<E>();
		if (v< numVerts()){
			int row = v / W;
			int col = v % H;
			if (row < H-1){
				if (col > 0)
					adj.add((row+1)*W + col - 1);
				adj.add((row + 1)*W + col + 1);
			
			}else{
				adj.add(W*H + 1); // end node
			}
		}else if(v == W*H){
			for (int col = 0; col < W; col++)
				adj.add(col);
		}
		return adj;
	}

	@Override
	public int weight(int u, int v) {
		// TODO Auto-generated method stub
		return 0;
	}

}
