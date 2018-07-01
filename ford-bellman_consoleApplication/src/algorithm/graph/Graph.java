package algorithm.graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Class for storing the graph
 */
class Graph{
    /**
     *
     * Class for storing the edges of the graph.
     */
    public class ElementGraphWay {
        int from;
        int to;
        int l;
    }

    /**
     * Class for storing the vertices of the graph.
     */
    private class WaysPoint {
    }

    private Vector<ElementGraphWay> list = new Vector<>();
    private Vector<Integer> ways = new Vector<>();
    private Vector<Integer> road = new Vector<>();
    private Vector<WaysPoint> visual = new Vector<>();
    private int n; // number of vertex
    private int m = 0;// number of edges
    private int v = 1;// the vertex from where the algorithm begins
    private int negativeCircle = 0;

    /**
     * Reading the graph from the file
     */
    void inputFile(int num) {
        negativeCircle = 0;
        list.clear();
        visual.clear();
        Scanner sc;
        try {
            sc = new Scanner(new File("tests/INPUT" + num + ".TXT"));
            try {
                if (sc.hasNextInt()) {
                    n = sc.nextInt();
                } else System.out.println("There is not enough data in the file");
                if (sc.hasNextInt()) {
                    m = sc.nextInt();
                } else System.out.println("There is not enough data in the file");
                for (int j = 0; j < m; j++) {
                    ElementGraphWay Q = new ElementGraphWay();
                    if (sc.hasNextInt()) {
                        Q.from = sc.nextInt();
                        Q.from--;
                    }
                    if (sc.hasNextInt()) {
                        Q.to = sc.nextInt();
                        Q.to--;
                    }
                    if (sc.hasNextInt()) {
                        Q.l = sc.nextInt();
                    }
                    list.add(Q);

                }
            } catch (Exception ex) {
                System.out.println("File is empty!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File is not exist!");
        }

    }

    /**
     *
     * Search for the shortest paths from a given vertex.
     *
     * */
    void searchAlgorithm() {
        int inf = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ways.add(inf);
            road.add(-1);
        }
        v--;
        ways.set(v, 0);
        /*System.out.print((v + 1) + "|\t");
        for (int k = 1; k <= n; k++) {
            System.out.print(k + "|\t");
        }*/
        //System.out.print("\n\t");
        /*for (int k = 0; k < n; k++) {
            if (ways.elementAt(k) == inf)
                System.out.print("∞" + "\t");
            else {
                if (ways.elementAt(k) > 9)
                    System.out.print(ways.elementAt(k) + "\t");
                else System.out.print(ways.elementAt(k) + "\t");
            }
        }*/
        //System.out.print("\n");
        for (int i = 1; i <= n; ++i) {//number of vertexes
            for (int j = 0; j < m; ++j) {//int m = 0;//количество ветвей
                if ((ways.elementAt(list.elementAt(j).from) < inf) & ((ways.elementAt(list.elementAt(j).from) + list.elementAt(j).l) < ways.elementAt(list.elementAt(j).to))) {
                    if (i == n) {
                        negativeCircle = 1;
                        System.out.print("The graph has negative cycles");
                        System.out.print("The graph has negative cycles");
                        return;
                    } else {
                        ways.set(list.elementAt(j).to, (ways.elementAt(list.elementAt(j).from) + list.elementAt(j).l));
                        road.set(list.elementAt(j).to, list.elementAt(j).from);
                    //    System.out.print("\t");
                    /*    for (int k = 0; k < n; k++) {
                            if (ways.elementAt(k) == inf)
                                System.out.print("∞"+"\t");
                            else {
                                if (ways.elementAt(k) > 99)
                                    System.out.print(ways.elementAt(k) + "\t");
                                else System.out.print(ways.elementAt(k) + "\t");
                            }
                        }*/
                      //  System.out.print("\n");

                    }
                }
            }

        }
    }



    /**
     * Output ways
     */
    ArrayList<Integer> outputWaysToUnitTest() {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(0);
        if (negativeCircle == 0) {
            Vector<Integer> path = new Vector<>();
            for (int j = 0; j < n; j++) {
                if (j != (v)) {
                    if (ways.elementAt(j) == Integer.MAX_VALUE) {
         //               System.out.print(30000 + " ");
                        result.add(30000);
                    } else {
                        path.clear();
                        for (int cur = j; cur != -1; cur = road.elementAt(cur))
                            path.add(cur);
       //                 System.out.print(ways.elementAt(j) + " ");
                        result.add(ways.elementAt(j));
                        //for (int i = path.size() - 1; i >= 1; i--) {
                          //  int l = (path.elementAt(i) + 1);
                         //   System.out.print(l + "->");
                     //   }
                        //System.out.print((path.elementAt(0) + 1) + "\n");
                        //path.clear();
                    }
                }
            }
        }
        return result;
    }
    void outputWays() {
     //   Integer [] result = new Integer[0];
        if (negativeCircle == 0) {
            Vector<Integer> path = new Vector<>();
            for (int j = 0; j < n; j++) {
                if (j != (v)) {
                    if (ways.elementAt(j) == Integer.MAX_VALUE) {
                        System.out.print("Way from the top " + (v + 1) + " to the top " + (j + 1) + ": NO\n");
                    } else {
                        path.clear();
                        for (int cur = j; cur != -1; cur = road.elementAt(cur))
                            path.add(cur);
                        System.out.print("Way from the top " + (v + 1) + " to the top " + (j + 1) + ": " + ways.elementAt(j) + "\nShortest way: ");
                        for (int i = path.size() - 1; i >= 1; i--) {
                            int l = (path.elementAt(i) + 1);
                            System.out.print(l + "->");
                        }
                        System.out.print((path.elementAt(0) + 1) + "\n");
                        path.clear();
                    }
                }
            }
        }
    }
}