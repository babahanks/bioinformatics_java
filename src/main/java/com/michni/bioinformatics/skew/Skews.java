package com.michni.bioinformatics.skew;

public class Skews {

    int maxSkewIndex;
    int minSkewIndex;

    public Skews(int maxSkewIndex, int minSkewIndex) {
        this.maxSkewIndex = maxSkewIndex;
        this.minSkewIndex = minSkewIndex;
    }

    public int getMaxSkewIndex() {
        return maxSkewIndex;
    }

    public int getMinSkewIndex() {
        return minSkewIndex;
    }

}
