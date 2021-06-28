package com.michni.bioinformatics.multithreaded;


import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import com.michni.bioinformatics.Algorithms;
import com.michni.bioinformatics.Kmer;
import com.michni.bioinformatics.multithreaded.tasks.GetKmerClumpsTask;
import com.michni.bioinformatics.multithreaded.tasks.GetKmersTask;


public class Multithreaded {

    public Map<String, KmerClumps> getClumps(
            String fileName,
            int kmer_size,
            int l_clumpSize,
            int t_requiredFrequecy,
            int shardSize)
            throws  IOException,
            InterruptedException,
            ExecutionException
    {

        char[] charArray = Algorithms.getChars(fileName);
        long start = System.currentTimeMillis();

        List<GetKmersTask> tasks = new LinkedList<>();

        for (int i=0; i+shardSize <= charArray.length -1; i=i+shardSize){
            tasks.add(new GetKmersTask(this, charArray, kmer_size, i, i+shardSize));
        }

        ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(tasks.size());

        List<Future<ShardedKmers>> futures = WORKER_THREAD_POOL.invokeAll(tasks);

        Map<Integer, Map<String, Kmer>> shardsMap = new HashMap<>();
        for (Future<ShardedKmers> future: futures) {
            shardsMap.put(future.get().getShardStartIndex(), future.get().getMap());
        }

        Map<String, Kmer> joinedMap = joinMaps(shardsMap, shardSize, charArray.length);

        List<GetKmerClumpsTask> getKmerClumpTasks = new LinkedList<>();

        int numberOfKmers = joinedMap.size();

        for (String kmerName: joinedMap.keySet()) {
             getKmerClumpTasks.add(new GetKmerClumpsTask(
                                            joinedMap.get(kmerName),
                                            kmer_size,
                                            l_clumpSize,
                                            t_requiredFrequecy ));
        }
        //WORKER_THREAD_POOL = Executors.newFixedThreadPool(getKmerClumpTasks.size());
        WORKER_THREAD_POOL = Executors.newFixedThreadPool(10);

        List<Future<KmerClumps>> kmerClumpFutures = WORKER_THREAD_POOL.invokeAll(getKmerClumpTasks);

        Map<String, KmerClumps> kmerClumpMap = new HashMap();
        for (Future<KmerClumps> future: kmerClumpFutures) {
            KmerClumps clumps = future.get();
            if (clumps.listOfClumps.size() > 0) {
                kmerClumpMap.put(clumps.kmerName, clumps);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("time (ms): " + (end - start) + "  total: " + kmerClumpMap.keySet().size());

        return kmerClumpMap;

    }




    public Map<String, Kmer> joinMaps(Map<Integer, Map<String, Kmer>> shardsMap, int shardSize, int arraySize)
    throws  ExecutionException,
            InterruptedException
    {
        Map<String, Kmer> bigMap = new HashMap<>();

        for (int i=0; i+shardSize <= arraySize -1; i=i+shardSize) {
            Map<String, Kmer> kmerMap = shardsMap.get(i);

            for (String kmerName : kmerMap.keySet()) {
                Kmer kmer = bigMap.get(kmerName);

                if (kmer == null) {
                    bigMap.put(kmerName, kmerMap.get(kmerName));
                }
                else {
                    kmer.getIndexes().addAll(kmerMap.get(kmerName).getIndexes());
                }
            }
        }
        return bigMap;
    }


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Multithreaded m = new Multithreaded();
        Map map = m.getClumps("/Users/msalman/Downloads/E_coli.txt",
                            9,
                            500,
                            3,
                            100000);
        int i=0;
    }

}
