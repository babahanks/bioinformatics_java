package com.michni.bioinformatics.multithreaded;

import com.michni.bioinformatics.Kmer;

import java.util.Map;

public class ShardedKmers {

    int shardStartIndex;
    Map<String, Kmer> map;

    public ShardedKmers(
            int shardStartIndex,
            Map<String, Kmer> map) {
        this.shardStartIndex = shardStartIndex;
        this.map = map;
    }

    public int getShardStartIndex() {
        return shardStartIndex;
    }

    public void setShardStartIndex(int shardStartIndex) {
        this.shardStartIndex = shardStartIndex;
    }

    public Map<String, Kmer> getMap() {
        return map;
    }

    public void setMap(Map<String, Kmer> map) {
        this.map = map;
    }
}
