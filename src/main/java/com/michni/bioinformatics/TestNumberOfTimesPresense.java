package com.michni.bioinformatics;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.michni.bioinformatics.Algorithms;
import com.michni.bioinformatics.Kmer;

public class TestNumberOfTimesPresense {

    public static void main(String[] args){
        String text = "aactctatacctcctttttgtcgaatttgtgtgatttatagagaaaatcttattaactga" +
                "aactaaaatggtaggtttggtggtaggttttgtgtacattttgtagtatctgatttttaa" +
                "ttacataccgtatattgtattaaattgacgaacaattgcatggaattgaatatatgcaaa" +
                "acaaacctaccaccaaactctgtattgaccattttaggacaacttcagggtggtaggttt" +
                "ctgaagctctcatcaatagactattttagtctttacaaacaatattaccgttcagattca" +
                "agattctacaacgctgttttaatgggcgttgcagaaaacttaccacctaaaatccagtat" +
                "ccaagccgatttcagagaaacctaccacttacctaccacttacctaccacccgggtggta" +
                "agttgcagacattattaaaaacctcatcagaagcttgttcaaaaatttcaatactcgaaa" +
                "cctaccacctgcgtcccctattatttactactactaataatagcagtataattgatctga";

        Map<String, Kmer> kmersmap = Algorithms.getKmers_N(text.toCharArray(), 9);
        List<Kmer> list = Algorithms.getByNumberOfTimePresence(kmersmap, 2);
        for (Kmer kmer: list) {
            System.out.print("\n" + kmer.kmerString +"  " + kmer.indexes.size() + "  : ");
            for (int i = 0; i < kmer.indexes.size(); i++) {
                System.out.print(kmer.indexes.get(i) + "  ");
            }
        }
        List<Set<Kmer>> reverseComplements = Algorithms.getReverseComplements(list);

        for (Set<Kmer> set: reverseComplements) {
            System.out.print("\nReverse complements: ");
            for (Kmer kmer: set) {
                System.out.print(String.format("\t %s (%d)", kmer.kmerString, kmer.indexes.size()));
            }
        }
        int i=0;

    }
}
