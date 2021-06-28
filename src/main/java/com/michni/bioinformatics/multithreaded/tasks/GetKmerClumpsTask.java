package com.michni.bioinformatics.multithreaded.tasks;

import com.michni.bioinformatics.Algorithms;
import com.michni.bioinformatics.Kmer;
import com.michni.bioinformatics.multithreaded.KmerClumps;

import java.util.List;
import java.util.concurrent.Callable;

public class GetKmerClumpsTask implements Callable<KmerClumps> {

    Kmer kmer;
    int k_kmerSize;
    int L_windowSize;
    int t_requiredFrequecy;

    public GetKmerClumpsTask( Kmer kmer,
                             int k_kmerSize,
                             int l_windowSize,
                             int t_requiredFrequecy) {
        this.kmer = kmer;
        this.k_kmerSize = k_kmerSize;
        L_windowSize = l_windowSize;
        this.t_requiredFrequecy = t_requiredFrequecy;
    }

    @Override
    public KmerClumps call() throws Exception {

        List<List<Integer>> clumps = Algorithms.findClumps( kmer.getIndexes(),
                                                            k_kmerSize,
                                                            L_windowSize,
                                                            t_requiredFrequecy);
        return new KmerClumps(kmer.getKmerString(), clumps);
    }

}
