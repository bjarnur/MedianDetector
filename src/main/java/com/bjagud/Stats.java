package com.bjagud;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The objective is to take in an array of integers, between
 * 1 and 1000, and to return the median value. The problem 
 * description mentions that amount of data will be huge, so 
 * we'll aim to optimize for memory usage.
 * 
 * Liberty is taken to assume that we know the size of the 
 * input beforehand, so I have included the size of each input
 * as the first number in each input file. 
 * 
 * @author bjarni
 */
public class Stats {

	/*
	 	CURRENT APPROACH:
	 	Read integers one-by-one and insert into a heap-like structure. Once
	 	all numbers are inserted a method is called that sorts the heap and 
	 	fetches the median directly from the middle index. Alternatively it's
	 	possible to call a method that pops half of the queue and returns the 
	 	index, but this is a more naive approach. 
	 	
	 	CURRENT IMPLEMENTATAION:
	 	Heap is represented as an array with a specific size.
	 	
	 	CURRENT PERFORMANCE:
	 	Solution is optimized for memory usage, all integers are read directly
	 	into file, and no extra space (beyond the size of the input) is used,
	 	so space usage is O(1).  	 	
	 	Running time for a single insert is O(log(N)), so feeding all elements 
	 	into the queue has running time of O(N log(N)). Both alternatives for
	 	fetching the median have a running time of O(N log(N)).
	 	
	 	POSSIBLE IMPROVEMENTS:
	 	Make the array that represents the heap dynamically sized, so that it 
	 	will grow or shrink depending on number of elements. 
	 	Initialize StatsHeap with an array directly, this will save the time 
	 	spent feeding elements into the heap. After the heap has been initialized
	 	with an unordered array heap-sort will still work.
	 */
	
	public static void main(String args[]) {
		
		int tests[] = {1, 2, 3, 4, 5, 6, 7};
		
		for(int t : tests) {
			System.out.println("Median from test " + t + ": " + getMedian(t));
		}
	}
	
	private static int getMedian(int testNumber) {
		
		String fileNameTemplate = "test%s.txt";
		String fileName = String.format(fileNameTemplate, testNumber);
		
		InputStream is = Stats.class.getResourceAsStream(fileName);		
		Scanner scanner = new Scanner(is);
		scanner.useDelimiter(", ");
		
		//Assuming first digit in file represents size
		int size = scanner.nextInt();
		StatsHeap heap = new StatsHeap(size);
		
		while(scanner.hasNext()) {
			heap.insert(scanner.nextInt());
		}
		
		scanner.close();
		return heap.getMedian();
	}	
}
