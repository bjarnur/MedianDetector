package com.bjagud;

/**
 * This heap-like data structure will allow us to sort input
 * as we read it in. Sorting will require no additional memory, 
 * but accessing the median instantly will not be possible. 
 * 
 * This implementation uses an array to represent the heap, this
 * felt the most straight-forward, since we are working with 
 * integers, and not complex data types. 
 * 
 * In the current implementation the has one method that provides
 * access to the median value. The downside of this method is that 
 * it pops off half of the que, making it useless for further
 * operations. A possible improvement is to add a method that 
 * sorts the heap completely, and then offers direct access to 
 * the median index. 
 * 
 * A big part of this implementation, is inspired by the 
 * Priority Queues chapter in Sedgewick's and Wayne's 
 * Algorithms book. 
 * 
 * @author bjarni
 */
public class StatsHeap {

	private int size;
	private int currentBottom = 0;
	
	/**
	 Array representation of a heap. Index 0 will be left unused, 
	 this is a standard that makes heap operations more comfortable. 
	 At any time we know that the element at index K has children 
	 located at indices 2K and 2K + 1. */
	private int heapArray[];
	
	
	StatsHeap(int size) {
		this.size = size + 1;
		this.heapArray = new int[this.size];
	}
	
	/**
	 Insert the given element into the heap */
	public void insert(int num) {
		
		currentBottom++;
		heapArray[currentBottom] = num;
		swim(currentBottom);
	}
	
	/**
	 Get the max element without removing it from the heap */
	public int peakMax() {
		return heapArray[1];
	}
	
	/**
	 Pops half of the queue and returns the median value */
	public int popForMedian() {
		
		int median = -1;
		int medianIndex = currentBottom/2;
		for(int i = 0; i <= medianIndex; i++) {
			median = delMax();
		}
		return median;
	}
	
	/**
	 By iterating through the heap top-down and sinking every
	 element this method will result in the array being strictly
	 sorted. This enables us to access the median directly. */
	public void heapSort() {
		
		int iterator = currentBottom;	
		while(iterator > 1) {
			exch(1, iterator);
			iterator--;
			sink(1, iterator);
		}
	}
	
	public int getMedian() {
		return 0;
	}
	
	/**
	 Pop the max element of the heap, returns max element */
	public int delMax() {
		
		//Get max and sent it to bottom
		int max = heapArray[1];	
		exch(1, currentBottom);
				
		//Remove bottom (prev. max) from queue
		heapArray[currentBottom] = -1;
		currentBottom--;
		
		//Sink switch element
		sink(1);
		return max;
	}
	
	/**
	 The node at the given index "swims up" until it's 
	 located in the correct place in the heap with relation
	 to its parent. */
	private void swim(int node) {
				
		while(node > 1) {		
			
			int parent = node/2;
			if(!(heapArray[parent] < heapArray[node])) {
				//Parent is larger, swim no more
				break;
			}
			
			//Just keep swimming
			exch(node, parent);
			node = parent;
		}
	}
	
	/**
	 The node at the given index, is "sunk" (i.e. moved lower
	 down in the heap) until it is in the correct place relative
	 to its children. */
	private void sink(int node) {		
		sink(node, currentBottom);
	}
	
	/**
	 For heap sort we need to be able to explicitly define the bottom */
	private void sink(int node, int bottom) {
		while(node * 2 <= bottom) {
			
			int child = node * 2;		
			if(child < bottom && heapArray[child] < heapArray[child + 1]) {
				//Child has a larger sibling
				child++;
			}						
			
			if(!(heapArray[node] < heapArray[child])) {
				//Node is in correct place relative to children
				break;
			}
			
			//Parent node sinks down
			exch(node, child);
			node = child;
		}
	}	
	
	/**
	 Exchanges the elements at indices a and b */
	private void exch(int a, int b) {		
		int temp = heapArray[a];
		heapArray[a] = heapArray[b];
		heapArray[b] = temp;
	}
	
	public int[] getHeapArray() {
		return heapArray;
	}
}
