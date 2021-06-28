package com.michni.algorithm;

import java.util.LinkedList;
import java.util.List;

public class Permutations2<T> {

	List<T> permutations = new LinkedList<>();
	
	
	public List<T> generate(T[] array) {
		generate(array.length, array);
		
		return this.permutations;
	}
	
	public void generate(int k, T[] array) {
						
		System.out.println("k: " + k);
		
		if (k==1) {
			permutations.add((T) array);
			System.out.println(permutationPrint(array));			
		}
		else {
			generate(k-1, array);
			
			
			
			for (int i=0; i<k-1; i+= 1) {
				
				if (k%2==0) { //even
					swap(array, i, k-1);
				}
				else {
					swap(array, 0, k-1);
				}
				
				generate(k-1, array);
			}
		}		
	}
	
	public  void swap(T[] array, int index1, int index2) {
		
		T temp = array[index1];
		
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	public  String  permutationPrint(T[] array) {
		
		StringBuilder b = new StringBuilder();
		
		for (T t : array) {
			b.append(t);
			b.append(",");
		}
		return b.toString();		
	}
	
	public static void main(String[] args) {
		
//		Permutations2<String> p = new Permutations2<>();
//		p.generate(4, new String[]{"A", "T", "C", "G"});
		Permutations2<Character> p2 = new Permutations2<>();
		List l = p2.generate(new Character[]{'A', 'B'});
		int i=0;
		p2.generate(4, new Character[]{'A', 'B', 'C', 'D'});
		p2.generate(4, new Character[]{'A', 'B', 'C', 'D'});


        
	}
	

}
