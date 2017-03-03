//David Ciupei
//PS8
//using the 1 day late pass
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class SlowPQ {
	int [] array;
	int size;
	
	
	public SlowPQ() { 
	 array =  new int [100000];
	 size = 0;
	}
	public void add( int e ) {	
		//adds the specified number being passed in to the array and 
		//increments the size each time
		array[size] = e;
		size++;
		}
	
	public int removeMin() { 
		int minval = array[0];//sets first element to smallest
		for (int i = 0; i < size; i++){//walks through array
			if (array[i] < minval ){//checks to see if elements in index is less then the first index
				minval = array[i];//sets the new minimum value
				array[i] = array[i+1];
			}
			
		}
		return minval; 
		
	}
	public static void main(String args[]) throws FileNotFoundException{
		SlowPQ test = new SlowPQ();
		Scanner s = new Scanner (new File("/Users/dciupei/Downloads/ps8/values.txt"));
		String str;
		test.add(4);
		test.add(7);
		test.add(9);
		test.removeMin();
		for( int i = 0; i < test.size; i++){
			System.out.println(test);
		}
		while (s.hasNext())
		{
			str = s.next();
			test.add((Integer.parseInt(str)));
		}
		
		
		while (s.hasNext())
		{
			str = s.next();
			test.removeMin();
		}
		//int i = 0;
		//while(s.hasNextLine());{
			//i = s.nextInt();
			//test.add(i);
			//System.out.println(i);
		//}
		
	}
}
