package com.michni.bioinformatics;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

import static com.michni.bioinformatics.hammingdistance.HammingDistanceSearch.getDistanceAndRCKmers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.michni.bioinformatics.hammingdistance.HammingDistanceSearch.getDistanceKmers;


public class HammingDistancedKmersTest {

    @Test
    void distancedKmersTest_01() {
        String text1 = "ACGTTGCATGTCGCATGATGCATGAGAGCT";
        List<String> kmers = getDistanceKmers(text1, 4, 1);
        int i=0;
        List<String> expectedList =  new LinkedList<>();
        expectedList.add("ATGC");
        expectedList.add("ATGT");
        expectedList.add("GATG");
        assertEquals(kmers.size(), expectedList.size());
        assertTrue(kmers.containsAll(expectedList));
        assertTrue(expectedList.containsAll(kmers));
    }


    @Test
    void distancedKmersTest_02() throws Exception {
        Path path = FileSystems.getDefault().getPath(
                "test_data",
                "mismatched_distanced",
                "data_1.txt");

        Data data = getData(path);

        List<String> kmers = getDistanceKmers(data.text, data.kmer_size, data.distance);
        assertEquals(kmers.size(), data.expectedList.size());
        assertTrue(kmers.containsAll(data.expectedList));
        assertTrue(data.expectedList.containsAll(kmers));
    }

    @Test
    void distancedKmersTest_03() throws Exception {
        Path path = FileSystems.getDefault().getPath(
                "test_data",
                "mismatched_distanced",
                "data_2.txt");

        Data data = getData(path);

        List<String> kmers = getDistanceKmers(data.text, data.kmer_size, data.distance);
        assertEquals(kmers.size(), data.expectedList.size());
        assertTrue(kmers.containsAll(data.expectedList));
        assertTrue(data.expectedList.containsAll(kmers));
    }


    @Test
    void distancedKmersAndRCKmersTest_01() {
        String text1 = "ACGTTGCATGTCGCATGATGCATGAGAGCT";
        List<String> kmers = getDistanceAndRCKmers(text1, 4, 1);
        int i=0;
        List<String> expectedList =  new LinkedList<>();
        expectedList.add("ATGT");
        expectedList.add("ACAT");
        assertEquals(kmers.size(), expectedList.size());
        assertTrue(kmers.containsAll(expectedList));
        assertTrue(expectedList.containsAll(kmers));
    }




    @Test
    void distancedKmersAndRCKmersTest_02() throws Exception {
        Path path = FileSystems.getDefault().getPath(
                "test_data",
                "mismatched_distanced",
                "distanced_kmers_rckmers_1.txt");

        Data data = getData(path);

        List<String> kmers = getDistanceAndRCKmers(data.text, data.kmer_size, data.distance);
        assertEquals(kmers.size(), data.expectedList.size());
        assertTrue(kmers.containsAll(data.expectedList));
        assertTrue(data.expectedList.containsAll(kmers));
    }

    @Test
    void distancedKmersAndRCKmersTest_03() throws Exception {
        Path path = FileSystems.getDefault().getPath(
                "test_data",
                "mismatched_distanced",
                "distanced_kmers_rckmers_2.txt");

        Data data = getData(path);

        List<String> kmers = getDistanceAndRCKmers(data.text, data.kmer_size, data.distance);
        assertEquals(kmers.size(), data.expectedList.size());
        assertTrue(kmers.containsAll(data.expectedList));
        assertTrue(data.expectedList.containsAll(kmers));
    }

    public Data getData(Path path) throws Exception {
//        String workingDir = System.getProperty("user.dir");
//        Path path = FileSystems.getDefault().getPath("test_data", "mismatched_distanced", "data1.txt");
        Path absPath = path.toAbsolutePath();
        String text = null;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(absPath.toString()))) {
            String line = br.readLine();
            String[] tokens = line.split("=");
            text = tokens[1];

            line = br.readLine();
            tokens = line.split("=");
            int kmer_size = Integer.parseInt(tokens[1].trim());

            line = br.readLine();
            tokens = line.split("=");
            int distance = Integer.parseInt(tokens[1]);

            line = br.readLine();
            tokens = line.split("=");
            String expected = tokens[1];
            String[] expectedArray = expected.split(" +");
            List<String> expectedList = Arrays.asList(expectedArray);

            Data data = new Data(text, kmer_size, distance, expectedList);

            return data;

        } catch (Exception e) {
            throw e;
        } finally{}
    }


    public static class Data{
        String text;
        int kmer_size;
        int distance;
        List<String> expectedList;

        public Data(String text, int kmer_size, int distance, List<String> expectedList) {
            this.text = text;
            this.kmer_size = kmer_size;
            this.distance = distance;
            this.expectedList = expectedList;
        }
    }
}
