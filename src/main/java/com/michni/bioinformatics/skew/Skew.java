package com.michni.bioinformatics.skew;

import com.michni.bioinformatics.Algorithms;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Skew {

    public int findMinSkewIndex(char[] array){

        int[] skewAtIndex = new int[array.length+1];
        skewAtIndex[0] = 0;

        int minIndex = 0;
        int minValue = 0;
        for (int i=1; i<array.length; i++){
            if (array[i] == 'G') {
                skewAtIndex[i] = skewAtIndex[i-1] + 1;
            }
            else if (array[i] == 'C') {
                skewAtIndex[i] = skewAtIndex[i - 1] - 1;
                if (skewAtIndex[i + 1] < minValue) {
                    minValue = skewAtIndex[i + 1];
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public Set<Integer> findMinSkewsIndices(char[] array){
        Set<Integer> minIndices = new HashSet<>();

        int currentSkew = 0;
        int currentMinValue = 0;

        for (int i=0; i<array.length; i++){
            char c = array[i];

            if (c == 'G') {
                currentSkew = currentSkew + 1;
            }
            else if (c == 'C') {
                currentSkew = currentSkew - 1;
                if (currentSkew == currentMinValue) {
                    minIndices.add(i+1);
                }
                else if (currentSkew < currentMinValue) {
                    currentMinValue = currentSkew;
                    minIndices.clear();
                    minIndices.add(i+1);
                }
            }
        }
        return minIndices;
    }

    public List<Integer> findMinSkewsIndices2(char[] array){
        List<Integer> minIndices = new LinkedList<>();

        int currentSkew = 0;
        int currentMinValue = 0;

        for (int i=0; i<array.length; i++){
            char c = array[i];

            if (c == 'G') {
                currentSkew = currentSkew + 1;
            }
            else if (c == 'C') {
                currentSkew = currentSkew - 1;
                if (currentSkew == currentMinValue) {
                    minIndices.add(i+1);
                }
                else if (currentSkew < currentMinValue) {
                    currentMinValue = currentSkew;
                    minIndices.clear();
                    minIndices.add(i+1);
                }
            }
        }
        return minIndices;
    }

    public static void main(String[] args) throws IOException {
         char[] charArray = Algorithms.getChars("/Users/msalman/Downloads/dataset_7_10-2.txt");
        //char[] array = "TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT".toCharArray();

        Skew skew = new Skew();
        List<Integer> minIndices = skew.findMinSkewsIndices2(charArray);

        for (Integer i : minIndices) {
            System.out.print(" " + i );
        }

        int i=0;
    }

//    public static void main(String[] args){
//        char[] array = "TAAAGACTGCCGAGAGGCCAACACGAGTGCTAGAACGAGGGGCGTAAACGCGGGTCCGAT".toCharArray();
//
//        Skew skew = new Skew();
//        List<Integer> minIndices = skew.findMinSkewsIndices2(array);
//
//        for (Integer i : minIndices) {
//            System.out.print(" " + i );
//        }
//
//        int i=0;
//    }

}
