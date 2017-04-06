package com.bjagud;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * The objective is to take in a range of integers, between
 * 1 and 1000, and to return the median value. The problem 
 * description mentions that amount of data will be huge, so 
 * we'll aim to optimize for memory usage.
 * 
 * We allow ourselves to assume that we know the sizes of the 
 * input beforehand, and I have included the size of each input
 * as the first number in each input file. 
 * 
 * @author bjarni
 */
public class Stats {

	public static void main(String args[]) {
		
		int tests[] = {1, 2, 3, 4, 5, 6, 7};
		
		for(int t : tests) {
			System.out.println("Median from test " + t + ": " + getMedian(t));
		}
	
		InputStream iss = Stats.class.getResourceAsStream("test7.txt");
		Scanner sscanner = new Scanner(iss).useDelimiter(", ");
		

		int conut = 0;
		while(sscanner.hasNext()) {
			sscanner.nextInt();
			conut++;
		}
		System.out.println(conut);
		
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
