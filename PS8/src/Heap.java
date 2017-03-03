import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//David Ciupei
//PS8
//using the 1 day late pass

public class Heap {
//initialization of variables
	int [] heap;
	int size;
	int pos;
	int parent = pos / 2;
	int leftChild = 2 * pos + 1;
	int rightChild = (2 * pos) + 2;
	int n;
	
	public Heap() {
		//heap with 100000 slots
		heap = new int[100000];
	}
	public void add(int e) { 
		//adds element to the end of the heap
		heap[size++] = e;//increments and sets the size of the heap to e
		int i = size - 1;
		int j = (i-1) / 2;
		while (i > 0 && heap[j] > e){
			heap[i] = heap[j];
			i = j;
			
		}
		heap[i] = e;//sets index to the specified element
		
	}
	public long removeMin() { 
		int min;
		int temp;
		min = heap[0];
		
		heap[0] = heap[size - 1];//replace root with last element
		size--;
		if (leftChild < n && heap[leftChild] < heap[0] )//finds smallest child
			min = leftChild;//sets min as left child
		
		else
			min = 0;
		if (rightChild < n && heap[rightChild] < heap[min])//same for right side
			min = rightChild;
		if (min != 0){
			//exchanges the values
			temp = heap[0];
			heap[0] = heap[min];
			heap[min] = temp;
			
		}
		
		return min;//returns the minimum value
		}
	
		public static void main(String[] args) throws FileNotFoundException 
		{
			Scanner scan = new Scanner (new File("/Users/dciupei/Downloads/ps8/values.txt"));
			Heap heap = new Heap();
			heap.add(5);
			heap.add(7);
			heap.add(9);
			heap.removeMin();
			String str;
			System.out.println("Reading....");
			
			
			while (scan.hasNext())
			{
				str = scan.next();
				heap.add((Integer.parseInt(str)));
			}
			
			
			while (scan.hasNext())
			{
				str = scan.next();
				heap.removeMin();
			}
		
		}
}
