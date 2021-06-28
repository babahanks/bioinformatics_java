package com.michni.bioinformatics.multithreaded.tasks;

import com.michni.bioinformatics.Algorithms;
import com.michni.bioinformatics.Kmer;
import com.michni.bioinformatics.multithreaded.Multithreaded;
import com.michni.bioinformatics.multithreaded.ShardedKmers;

import java.util.Map;
import java.util.concurrent.Callable;

public class GetKmersTask implements Callable<ShardedKmers> {

    private final Multithreaded multithreaded;
    char[] array;
    int kmer_size;
    int startIndex;
    int endIndex;

    public GetKmersTask(Multithreaded multithreaded,
                        char[] array,
                        int kmer_size,
                        int startIndex,
                        int endIndex) {
        this.multithreaded = multithreaded;
        this.array = array;
        this.kmer_size = kmer_size;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public ShardedKmers call() throws Exception {

        Map<String, Kmer> map = Algorithms.getKmers_N(array, startIndex, endIndex, kmer_size);

        return new ShardedKmers(startIndex, map);
    }
}
