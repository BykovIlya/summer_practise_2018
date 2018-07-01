package algorithm.graph;

import java.util.Scanner;

/**
 *  Implementation of the Ford-Bellman algorithm
 * @author  Bykov
 * @author Lavrenkova
 * @author Kuharev
 * @version 1.0 Console Application
 */

public class Main {

    public static void main(String[] args) {
	// write your code here
        Graph g = new Graph();
        System.out.println("Enter number of test (from 1 to 50): ");
        Scanner _sc = new Scanner(System.in);
        String num = _sc.nextLine();
        g.inputFile(num);
        g.searchAlgorithm();
        g.outputWays();
    }
}

