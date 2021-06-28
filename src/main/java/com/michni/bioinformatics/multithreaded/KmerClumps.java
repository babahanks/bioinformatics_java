package com.michni.bioinformatics.multithreaded;

import java.util.List;

public class KmerClumps {

    String kmerName;
    List<List<Integer>> listOfClumps;

    public KmerClumps(String kmerName, List<List<Integer>> listOfClumps) {
        this.kmerName = kmerName;
        this.listOfClumps = listOfClumps;
    }
}
