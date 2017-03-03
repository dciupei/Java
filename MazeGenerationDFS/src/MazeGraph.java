//David Ciupei
//CS 223
//Project# 4: MazeDFS

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;



public class MazeGraph implements Graph {
	private int[][] cells;
	final int EAST = 1; // 0001
	final int NORTH = 2; // 0010
	final int WEST = 4; // 0100
	final int SOUTH = 8; // 1000
	private int width, height;


	public MazeGraph(int W, int H) {
		width = W; height = H;
		cells = new int[H][W];
		for (int r = 0; r < H; r++){
			for (int c = 0; c < W; c++){
				cells[r][c] = r*W + c;

			}
		}
	}


	@Override
	public int numVerts() {
		return width*height;
	}

	@Override
	public ArrayList<Integer> adjacents(int v) {
		ArrayList<Integer> adjacents = new ArrayList<Integer>();
		
		//recover the grid position (r,c)
		int r = v / width;
		int c = v % width;

		if (c - 1 >= 0){//WEST wall check
			adjacents.add(cells[r][c-1]);
			} 
		if (c + 1 <= (width - 1)){//EAST wall check
			adjacents.add(cells[r][c + 1]);
			} 
		if (r - 1 >= 0){//NORTH wall check
			adjacents.add(cells[r-1][c]);
			} 
		if (r + 1 <= (height - 1)){//SOUTH wall check
			adjacents.add(cells[r+1][c]);
			}


		return adjacents;
	}

	public int[] mazeDFS(Graph G, int s){
		Random rdm = new Random();
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[G.numVerts()];
		for (int v = 0; v < G.numVerts(); v++){
			visited[v] = false;
		}
		int[] parent = new int[G.numVerts()];
		visited[s] = true;
		parent[s] = -1;
		int visitCount = 1;
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		int w = 0;
		while (visitCount < G.numVerts()) {
			ArrayList<Integer> unvisited = new ArrayList<Integer>();
			neighbors = G.adjacents(s);
			for (int i = 0; i < neighbors.size(); i++){    

				w = neighbors.get(i);	

				if (visited[w] == false){
					unvisited.add(w);
				}

			}
			if (!unvisited.isEmpty()) { //if any unvisited neighbors 
				int z = rdm.nextInt(unvisited.size());
				w = unvisited.get(z); //random unvisited neighbor
				visited[w] = true;
				visitCount++;
				parent[w] = s;
				stack.push(w);
				s = w;
			} else {
				s = stack.pop(); // backtrack
			}
		}
		return parent;

	}

	//	
	public int[][] Mazearator(int[] parent){

		int[][] cells = new int[height][width];
		for (int r = 0; r < height; r++){
			for (int c = 0; c < width; c++){
				cells[r][c] = 0xF;	 // bit code = 1111
			}
		}

		for (int r = 0; r < height; r++){
			for (int c = 0; c < width; c++){
				int v = r*width + c;
				int w = parent[v];

				if (w >= 0){		// v has parent w
					int r0 = w / width;
					int c0 = w % width;

					if (c0 == c + 1){				
						cells[r][c] &= ~EAST;	// clear EAST bit (~EAST = 1110)
						cells[r0][c0] &= ~WEST;}	// clear WEST bit (~WEST = 1011)

					if (c0 == c - 1){				
						cells[r][c] &= ~WEST;	 // clear WEST bit (~WEST = 1011)
						cells[r0][c0] &= ~EAST;}	// clear EAST bit (~EAST = 1110)

					if (r0 == r - 1){				
						cells[r][c] &= ~NORTH;	// clear NORTH bit (~NORTH = 0010)
						cells[r0][c0] &= ~SOUTH;}	// clear SOUTH bit (~SOUTH = 1000)

					if (r0 == r + 1){				
						cells[r][c] &= ~SOUTH;	// clear SOUTH bit (~SOUTH = 1000)
						cells[r0][c0] &= ~NORTH;}	//// clear NORTH bit (~NORTH = 0010)
				} 
			}
		}
		
		int startRow = 4;
		int exitRow = 4;
		cells[startRow][0] &= ~WEST;	// entry point for the maze
		cells[exitRow][width - 1] &= ~EAST;	// exit point for the maze
		
		return cells;			
	}

	public static void main(String[] args) {
		int W, H;
		String fname;
		if (args.length != 3) {
			W = 16;	//width of the maze
			H = 16;	// height of the maze
			fname = "maze16x16.txt";	//name of the text file
		} else {
			W = Integer.parseInt(args[0]);
			H = Integer.parseInt(args[1]);
			if (W < 5 || H < 5) {
				System.err.println("bogus size!");
				return;
			}
			fname = args[2];
		}try {
			MazeGraph Maze = new MazeGraph(W,H);

			int start = 0; //starting point

			int[] path = Maze.mazeDFS(Maze, start);//pass the maze through DFS
			int[][] cells = Maze.Mazearator(path); //then create the maze using the Mazearator
			PrintStream ps = new PrintStream(fname);
			ps.println(W + " " + H);	//prints out the width and height
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					ps.print(cells[r][c] + " ");	////prints out the rows and columns of the cell
				}
				ps.println();
			}
			ps.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
