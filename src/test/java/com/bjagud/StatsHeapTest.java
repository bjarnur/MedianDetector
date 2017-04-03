package com.bjagud;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class StatsHeapTest {

	private static int input1[] = {4, 1, 6, 3, 7, 8, 7};
	private static int input2[] = {1, 1, 1, 2, 3, 3, 3};
	private static int input3[] = {14, 81, 59, 17, 80, 53, 77, 93, 48, 50, 18, 83, 6, 31, 10, 74, 83, 70, 98, 69, 31, 88, 37,
					98, 9, 91, 9, 10, 99, 78, 31, 56, 83, 71, 90, 51, 22, 59, 44, 76, 18, 29, 58, 87, 84, 39, 73, 68,
					85, 55, 91, 70, 51, 54, 26, 98, 94, 87, 8, 27, 14, 87, 86, 44, 65, 98, 69, 57, 17, 75, 70, 62,
					55, 78, 59, 98, 66, 68, 21, 28, 39, 15, 46, 90, 92, 30, 30, 33, 51, 71, 2, 10, 13, 62, 55, 39,
					14, 96, 48, 94};
	
	private static List<HeapValidator> validators;
	
	@Before
	public void setup() {

		validators = new ArrayList<HeapValidator>();
		validators.add(new HeapValidator(input1, 8, 1));
		validators.add(new HeapValidator(input2, 3, 1));
		validators.add(new HeapValidator(input3, 99, 2));
	}
	
	@Test
	public void testInsert() {
		
		for(HeapValidator validator : validators) {			
			StatsHeap heap = validator.getHeap();
			Assert.assertEquals(validator.getMaxNum(), heap.peakMax());
		}
	}
	
	@Test
	public void testDelMax() {
		
		for(HeapValidator validator : validators) {
			
			StatsHeap heap = validator.getHeap();
			int size = validator.getInput().length;
			
			int minVal = heap.peakMax();
			for(int i = 1; i <= size; i++) {
				minVal = heap.delMax();
			}
			
			Assert.assertEquals(validator.getMinNum(), minVal);
		}
	}
	
	/**
	 * Inner class holds input and respective expected values. 
	 * 
	 * @author bjarni
	 */
	private class HeapValidator {
		
		private int[] input;
		private int maxNum;
		private int minNum;
		private StatsHeap heap;
		
		public HeapValidator(int[] input, int max, int min) {
			this.input = input;
			this.maxNum = max;
			this.minNum = min;
			
			heap = new StatsHeap(input.length);
			for(int i : input) {
				heap.insert(i);
			}
		}
		
		public int[] getInput() {
			return input;
		}
		
		public int getMaxNum() {
			return maxNum;
		}
		
		public int getMinNum() {
			return minNum;
		}
		
		public StatsHeap getHeap() {
			return heap;
		}
	}
}
