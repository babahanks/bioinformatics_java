package com.michni.bioinformatics;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Kmer implements Comparable<Kmer>{

    String kmerString;

    List<Integer> indexes = new LinkedList<>();

    public Kmer(String kmerString, List indexes) {
        this.kmerString = kmerString.toUpperCase(Locale.ROOT);
        this.indexes = indexes;
    }

    public String getKmerString() {
        return kmerString;
    }


    public List<Integer> getIndexes() {
        return indexes;
    }


    public void addIndex(int index){
        this.indexes.add(index) ;
    }

    @Override
    public int compareTo(Kmer o) {
        return Integer.compare(this.indexes.size(), o.indexes.size());
    }

    @Override
    public boolean equals(Object anObject){
        if (this == anObject) {
            return true;
        }

        if ( !(anObject instanceof Kmer)) {
            return false;
        }

        Kmer kmer = (Kmer)anObject;

        if (!this.kmerString.equals(kmer.kmerString)) {
            return false;
        }

        if (this.indexes.size() != kmer.indexes.size()) {
            return false;
        }

        for (int i=0; i< this.indexes.size(); i++){
            if (this.indexes.get(i) != kmer.indexes.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.kmerString.hashCode();
    }

}