package algorithm.graph;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class UTest {
    public UTest() {
    }
    @Test
    public static void UTestAll() throws Exception {
        for (int num = 1; num <= 50; num++) {
            Graph g = new Graph();
            g.inputFile(num);
            g.searchAlgorithm();
            ArrayList<Integer> myAnswer = g.outputWaysToUnitTest();
      //      BufferedReader sc = new BufferedReader(new FileReader("tests/answers/answer" + num + ".txt"));
            ArrayList<Integer> realAnswer = new ArrayList<>();
            Scanner sc = new Scanner(new File("tests/answers/answer" + num + ".txt"));
            while(sc.hasNextInt()) {
                realAnswer.add(sc.nextInt());
            }
            Integer [] ra = realAnswer.toArray(new Integer[0]);
            Integer [] ma = myAnswer.toArray(new Integer[0]);
            if (Arrays.equals(ra,ma)) System.out.println("Test # " + num + " is OK");
            else System.out.println("Test # " + num + " is FAIL");
            assertArrayEquals(ra, ma);
        }
    }
}
