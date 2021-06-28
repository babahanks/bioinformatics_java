package com.michni.bioinformatics.hammingdistance;

import com.michni.algorithm.Utility;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import static com.michni.bioinformatics.Algorithms.getReverseComplement;


public class HammingDistanceSearch {

    public static List<String> getDistanceKmers(
        String text,
        int kmer_size,
        int max_distance)
    {
        int end_index = text.length() - kmer_size + 1;

        Map<String, Integer> frequencyMap = new HashMap<>();

        for (int i=0; i<end_index; i++) {
            String kmer = text.substring(i, i + kmer_size);
            List<String> neighborhood = HammingDistance.getNeighborhood(kmer, max_distance);

            for (String neighbor : neighborhood) {
                Integer count = frequencyMap.get(neighbor);

                if (count == null) {
                    frequencyMap.put(neighbor, 1);
                } else {
                    count = count + 1;
                    frequencyMap.put(neighbor, count);
                }
            }
        }

        List<String> maxList = Utility.getMaxFrequencyKeys(frequencyMap);
        return maxList;
    }


    public static List<String> getDistanceAndRCKmers(
        String text,
        int kmer_size,
        int max_distance)
    {
        int end_index = text.length() - kmer_size + 1;

        Map<String, Integer> frequencyMap = new HashMap<>();

        for (int i=0; i<end_index; i++) {
            String kmer = text.substring(i, i + kmer_size);
            List<String> neighborhood = HammingDistance.getNeighborhood(kmer, max_distance);

            for (String neighbor : neighborhood) {
                Integer count = frequencyMap.get(neighbor);

                if (count == null) {
                    frequencyMap.put(neighbor, 1);
                } else {
                    count = count + 1;
                    frequencyMap.put(neighbor, count);
                }
            }

            String kmerRC = new String(getReverseComplement(kmer.toCharArray()));
            neighborhood = HammingDistance.getNeighborhood(kmerRC, max_distance);

            for (String neighbor : neighborhood) {
                Integer count = frequencyMap.get(neighbor);

                if (count == null) {
                    frequencyMap.put(neighbor, 1);
                } else {
                    count = count + 1;
                    frequencyMap.put(neighbor, count);
                }
            }

        }

        List<String> maxList = Utility.getMaxFrequencyKeys(frequencyMap);
        return maxList;
    }


    public static void main(String[] args) {


        String text1 = "ACGTTGCATGTCGCATGATGCATGAGAGCT";
        List<String> max = getDistanceAndRCKmers(text1,4, 1);


//        Set<String> kmers = getDistanceKmers(text1, 4, 1);

//        String text2 = "CACAGTAGGCGCCGGCACACACAGCCCCGGGCCCCGGGCCGCCCCGGGCCGGCGGCCGCCGGCGCCGGCACACCGGCACAGCCGTACCGGCACAGTAGTACCGGCCGGCCGGCACACCGGCACACCGGGTACACACCGGGGCGCACACACAGGCGGGCGCCGGGCCCCGGGCCGTACCGGGCCGCCGGCGGCCCACAGGCGCCGGCACAGTACCGGCACACACAGTAGCCCACACACAGGCGGGCGGTAGCCGGCGCACACACACACAGTAGGCGCACAGCCGCCCACACACACCGGCCGGCCGGCACAGGCGGGCGGGCGCACACACACCGGCACAGTAGTAGGCGGCCGGCGCACAGCC";
//        List<String> kmers2 = getDistanceKmers(text2, 10, 2);

        String text3 = "CCGCGAGCTGCTCTACCCTCCGCCTGCTCGACTACCCGCGAGCTGCTGCTCTACCCTCCTCCGCTACGCTCCGCTACCTACCTACCCGGCTCCTCCTCCGGCTCGACCTCGAGCTGCTCTACCGACCTCTACGCTGCTCGAGCTCCTGCTGCTCCTCCTGCTCTACCTACCGAGCTCCTCCGGCTCTACCTACCTACCCTGCTCCTCCTCCGGCTCCGCCTCCGGCTCCGCCGCCTCCTCGAGCTCGACCTCCGCCTCCTCCGCTACCTACCGAGCTCCGGCTCTACCTACGCTCCGCTACCCGCCGCCTCCTCCGCCTCGACCGCGAGCTCCTCCGCGACTACCGACCTCTACCCT";
        List<String> kmers3 = getDistanceKmers(text3, 6, 2);

        kmers3.stream().forEach(kmer->System.out.print(kmer + " "));
        //GCACACAGAC GCGCACACAC


        int i=0;

    }
}

