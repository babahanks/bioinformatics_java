package com.michni.bioinformatics.hammingdistance;

import java.util.LinkedList;
import java.util.List;

public class HammingDistance {

    public static boolean isDistanceLessThan(
        Character[] array1,
        Character[] array2,
        int distance)
    {
        if (array1.length != array2.length) {
            throw new IllegalArgumentException("sizes do not match");
        }

        int count = 0;
        for (int i=0; i<array1.length; i++){
            if (array1[i] != array2[i]){
                count += 1;

                if (count >= distance) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isDistanceLessThan(
            String pattern1,
            String pattern2,
            int distance)
    {
        if (pattern1.length() != pattern2.length()) {
            throw new IllegalArgumentException("sizes do not match");
        }

        int count = 0;

        for (int i=0; i<pattern1.length(); i++){
            char a1 = pattern1.charAt(i);
            char a2 = pattern2.charAt(i);
            if (a1 != a2){
                count = count +  1;
                if (count >= distance) {
                    return false;
                }
            }
        }
        return true;
    }


    public static List<String> getNeighborhood(
            String pattern,
            int distance)
    {
        List<String> neighborhood =new LinkedList<String>();
        if (distance == 0) {
            neighborhood.add(pattern);
            return neighborhood;
        }

        if (pattern.length() == 1) {
            neighborhood.add("A") ;
            neighborhood.add("T") ;
            neighborhood.add("C") ;
            neighborhood.add("G") ;

            return neighborhood;
        }

        String suffixPattern = pattern.substring(1);
        List<String> suffixNeighbor = getNeighborhood(suffixPattern, distance);
        String[] neucleotides = new String[]{"A", "T", "C", "G"};

        for (String neighbor: suffixNeighbor) {
            if (isDistanceLessThan(suffixPattern, neighbor, distance)){
                for (String c : neucleotides){
                    neighborhood.add(c+neighbor);
                }
            }
            else {
                String firstSymbol = pattern.substring(0,1);
                neighborhood.add(firstSymbol+neighbor);
            }
        }
        return neighborhood;
    }


//    public List<Character[]> getNeighborhood(
//        Character[] pattern,
//        int distance)
//    {
//        List<Character[]> neighborhood =new LinkedList<Character[]>();
//        if (distance == 0) {
//            neighborhood.add(pattern);
//            return neighborhood;
//        }
//
//        if (pattern.length == 1) {
//            neighborhood.add(new Character[]{'A', 'T', 'C', 'G'}) ;
//            return neighborhood;
//        }
//
//        Character[] suffixPattern = new Character[pattern.length -1];
//
//        List<Character[]> suffixNeighbor = getNeighborhood(suffixPattern, distance);
//        Character[] neucleotides = new Character[]{'A', 'T', 'C', 'G'};
//        for (Character[] neighbor: suffixNeighbor) {
//            if (isDistanceLessThan(suffixPattern, neighbor, distance)){
//                for (Character c : neucleotides){
//
//                }
//            }
//        }
//    }


    public static void main(String[] args) {
        List<String> list = getNeighborhood("AC", 1);
        List<String> list2 = getNeighborhood("ACG", 1);
        List<String> list3 = getNeighborhood("ACG", 2);
        List<String> list4 = getNeighborhood("GGCCCAGAG", 3);

        int i=0;
    }

}
