package com.michni.bioinformatics;


import java.io.*;
import java.util.List;

public class Tests {


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/msalman/Downloads/Vibrio_cholerae.txt");
        String text = null;

        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            text = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        long fileread = System.currentTimeMillis();
        String pattern = "CTTGATCAT";
        List<Integer> indexes = Algorithms.patternIndexes(text.toCharArray(), pattern.toCharArray(), 0);
        System.out.print("CTTGATCAT " + indexes.size() +"  : ");
        for (int i=0; i< indexes.size(); i++) {
            System.out.print(indexes.get(i) + "  ");
        }
        long processed = System.currentTimeMillis();
        File outputFile = new File("/Users/msalman/Downloads/Vibrio_cholerae_output.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i=0; i< indexes.size(); i++) {
                bw.write(indexes.get(i) + "  ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long fileWritten = System.currentTimeMillis();

//        System.out.println("\n\nFile reading: " + (fileread - start));
//        System.out.println("Processing: " + (processed - fileread));
//        System.out.println("File written: " + (fileWritten - processed));
//
//        System.out.println(("total time: " + (fileWritten -  start)));

        pattern = "ATGATCAAG";
        indexes = Algorithms.patternIndexes(text.toCharArray(), pattern.toCharArray(), 0);
        System.out.print("\nATGATCAAG  " + indexes.size() +"  : ");
        for (int i=0; i< indexes.size(); i++) {
            System.out.print(indexes.get(i) + "  ");
        }


    }

}
